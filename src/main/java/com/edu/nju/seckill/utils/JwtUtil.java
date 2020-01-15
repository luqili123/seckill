package com.edu.nju.seckill.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *  Jwt工具类
 * @author lql
 * @date 2020/1/15 14:32
 */
public class JwtUtil {

    /***
     * 签名
     */
    private static final String secert="seckill";

    /***
     * 过期时间
     */
    private static final long expire=1800000;

    /***
     *  设置算法种类
     */
    private static final Algorithm ALGORITHM=Algorithm.HMAC256(secert);


    /***
     * 生成token，携带用户电话和密码信息
     * @param phone
     * @return
     */
    public  String generate(String phone){
        Map<String,Object> header=new HashMap<String, Object>(2) ;
        header.put("alg", "HS256");
        header.put("typ", "JWT");
        String token= JWT.create()
                .withHeader(header)
                .withSubject("login")
                .withAudience("client")
                .withClaim("phone",phone)
                .withExpiresAt(new Date(System.currentTimeMillis()+expire))
                .sign(ALGORITHM);
        return token;
    }


    /***
     * 解析token
     * @param token
     * @return
     */
    public static HashMap<String, String > verifier(String token){
        JWTVerifier jwtVerifier=JWT.require(ALGORITHM)
                .build();
        DecodedJWT decodedJWT=jwtVerifier.verify(token);
        Map<String, Claim> map=decodedJWT.getClaims();
        HashMap<String,String> hashMap=new HashMap<>();
        for (Map.Entry<String, Claim> entry : map.entrySet()) {
            hashMap.put(entry.getKey(),entry.getValue().asString());
        }
        return hashMap;
    }
}
