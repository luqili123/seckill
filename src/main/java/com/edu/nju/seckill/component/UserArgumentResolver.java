package com.edu.nju.seckill.component;

import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.domain.User;
import com.edu.nju.seckill.domain.dto.CurrentUser;
import com.edu.nju.seckill.exception.TokenException;
import com.edu.nju.seckill.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;



/**
 * 用户参数解析器 从head中通过token解析出User信息
 * @author lqllq whn
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

    /***
     * redis工具类
     */
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 判断是否进行参数解析：如果方法参数包含User对象，则进行解析
     * @param methodParameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        boolean flag=methodParameter.getParameterType().isAssignableFrom(CurrentUser.class);
        return flag;
    }

    /**
     * 获取head中的token信息，并将解析出的信息赋值给user
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
                                  WebDataBinderFactory webDataBinderFactory) throws TokenException {
        CurrentUser currentUser=null;
        String header=nativeWebRequest.getHeader(Authorization);
        if(header!=null&&!"".equals(header)) {
            if(header.startsWith(StartWith)) {
                currentUser=new CurrentUser();
                String token = header.substring(7);
                System.out.println(token);
                currentUser.setToken(token);
                currentUser.setUser(redisUtil.getUser(token));
                System.out.println(currentUser);
            }
        }
        else {
            throw new TokenException("header为空");
        }
        if(null==currentUser){
            throw new TokenException("token错误没有获取用户信息");
        }
        return currentUser;
    }
}
