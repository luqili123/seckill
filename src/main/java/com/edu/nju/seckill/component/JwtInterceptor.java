package com.edu.nju.seckill.component;

import com.edu.nju.seckill.exception.TokenException;
import com.edu.nju.seckill.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 * @author lql
 * @date 2020/1/16 12:18
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    /***
     * 请求头中包含token的标签名
     */
    private static final String Authorization="Authorization";
    /***
     * token以什么开头
     */
    private static final String StartWith="Bearer ";

    @Autowired
    private RedisUtil redisUtil;

    /***
     * 负责把请求头中包含的token的令牌进行解析验证
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws TokenException {
        if(request.getMethod().equals("OPTIONS")){
            return true;
        }
        //1.获取请求头
        String header=request.getHeader(Authorization);
        //1.1包含token，则进行解析
        if(header!=null&&!"".equals(header)){
            //1.2,若以 Bearer空格 开头，则进行解析
            if(header.startsWith(StartWith)) {
                String token=header.substring(7);
                //1.3 获取token之后，与redis数据进行对比，查看是否存在该token
                if(redisUtil.hasKey(token)) {
                    return true;
                }
                // TODO 无法被全局异常处理器捕获异常
                throw new TokenException("登录失效啦，请重新登录");
            }
        } else {
            throw new TokenException("请先登录");
        }
        return false;
    }

}
