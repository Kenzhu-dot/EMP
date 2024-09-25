package com.situ.emsvue.util;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {

    private static final String KEY = "sun";

    //接收业务数据,生成token并返回
    public static String createToken(Map<String, Object> claims) {
        return JWT.create()
                .withClaim("claims", claims) //有效载荷
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 *10* 60 )) //失效时间
                .sign(Algorithm.HMAC256(KEY)); //秘钥
    }

    //接收token,验证token,并返回业务数据
    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }
}
