package com.edu.nju.seckill.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.edu.nju.seckill.domain.User;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;


/**
 * Jwt工具类
 *
 * @author lql
 * @date 2020/1/15 14:32
 */
@Component
public class JwtUtil {

    /***
     * 签名
     */
    private static final String SECRET = "seckill";

    /***
     * 设置算法种类
     */
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);


    /***
     * 生成token，携带用户电话信息
     * @param user
     * @return
     */
    public String generate(User user) {
        Map<String, Object> header = new HashMap<String, Object>(2);
        header.put("alg", "HS256");
        header.put("typ", "JWT");
        return JWT.create()
                .withHeader(header)
                .withSubject("login")
                .withAudience("client")
                .withClaim("name", user.getName())
                .withClaim("phone", user.getPhone())
                .withClaim("email", user.getEmail())
                .withClaim("addressId", user.getAddressId())
                .withClaim("role", user.getRole())
                .sign(ALGORITHM);
    }


    /***
     * 解析token
     * @param token
     * @return
     */
    public static HashMap<String, String> verifier(String token) throws Exception {

        JWTVerifier jwtVerifier = JWT.require(ALGORITHM)
                .build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Map<String, Claim> map = decodedJWT.getClaims();
        HashMap<String, String> hashMap = new HashMap<>();
        for (Map.Entry<String, Claim> entry : map.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue().asString());
        }
        return hashMap;
    }
}
