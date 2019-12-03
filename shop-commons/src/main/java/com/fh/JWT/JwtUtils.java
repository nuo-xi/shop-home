package com.fh.JWT;

import com.alibaba.fastjson.JSON;
import com.fh.enumUtil.EnumService;
import com.fh.enumUtil.ResponseServer;
import io.jsonwebtoken.*;
import sun.misc.BASE64Encoder;

import java.util.HashMap;
import java.util.Map;

/**
 * jwt技术
 */
public class JwtUtils {

    /**
     * 设置map参数
     * 获取token值
     *
     * @param paraMap  需要加密的map
     * @return
     */
    public static String createToken(Map<String, Object> paraMap) {
        /**
         * 设置header
         * */
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("alg", "HS256");
        headerMap.put("typ", "JWT");

        /**
         * 设置负载
         * */
        Map<String, Object> payload = new HashMap<>();
        /**
         * 设置加密参数
         * */
        payload.putAll(paraMap);
        Long iat = System.currentTimeMillis();
        /**
         * 设置jwt的失效时间
         */
        payload.put("exp", iat+1800000l);
        payload.put("iat", iat);
        /**
         * 生成token值
         * */
        String token = Jwts.builder()
                .setHeader(headerMap)
                .setPayload(JSON.toJSONString(payload))
                .signWith(SignatureAlgorithm.HS256, getSecretKey("userCodeKey"))
                .compact();
        return token;
    }

    /**加密 密钥
     * @param userCodeKey
     * @return
     */
    private static String getSecretKey(String userCodeKey) {
        return new BASE64Encoder().encode(userCodeKey.getBytes());
    }

    /**
     * 获取token值
     *
     * @param token
     * @return
     */
    public static ResponseServer resolverToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(getSecretKey("userCodeKey"))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException exp) {
            System.out.println("token超时，token失效了");
            return ResponseServer.error(EnumService.TOKEN_TIMEOUT);
        } catch (SignatureException sing) {
            System.out.println("token解析失败");
            return ResponseServer.error(EnumService.SAFETY_ERROR);
        }
        return ResponseServer.success(claims);
    }



}
