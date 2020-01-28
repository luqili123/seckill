package com.edu.nju.seckill.component;


import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.domain.User;
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
 * @author lqllq
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
     * 判断是否进行参数解析：如果使用的注解使@CurrenUser,被注解的对象为User对象，则进行解析
     * @param methodParameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        boolean flag=methodParameter.getParameterType().isAssignableFrom(User.class);
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
        User user=null;
        String header=nativeWebRequest.getHeader(Authorization);
        if(header!=null&&!"".equals(header)){
            if(header.startsWith(StartWith)) {
                String token = header.substring(7);
                user = redisUtil.getUser(token);
                System.out.println(user);
            }
        }else {
            throw new TokenException(CommonResult.unauthorized("header为空"));
        }
        if(null==user){
            throw new TokenException(CommonResult.unauthorized("token错误没有获取用户信息"));
        }
        return user;
    }
}
