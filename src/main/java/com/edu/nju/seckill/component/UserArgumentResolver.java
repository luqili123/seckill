package com.edu.nju.seckill.component;

import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.domain.User;
import com.edu.nju.seckill.exception.TokenException;
import com.edu.nju.seckill.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.HashMap;
import java.util.Map;


/**
 * 用户参数解析器 从head中通过token解析出User信息
 */
@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

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
    /**
     * 判断是否进行参数解析
     * @param methodParameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return true;
    }

    /**
     * 具体通过head进行user参数解析
     * @param methodParameter
     * @param modelAndViewContainer
     * @param nativeWebRequest
     * @param webDataBinderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
        User user=null;
        String header=nativeWebRequest.getHeader(Authorization);
        if(header!=null&&!"".equals(header)){
            if(header.startsWith(StartWith)) {
                String token = header.substring(7);
                user = redisUtil.getUser(token);
            }
        }else
            throw new TokenException(CommonResult.unauthorized("header为空"));
        if(null==user){
//            user=new User();
//            user.setName("whn");
            throw new TokenException(CommonResult.unauthorized("token错误没有获取用户信息"));
        }
        return user;
    }
}
