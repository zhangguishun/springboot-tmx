package com.example.uniappspringboot.Util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.uniappspringboot.Domain.User;


import java.util.Date;



public class TokenUtils {
    private static final long EXPIRE_TIME= 10*60*60*1000;
    private static final String TOKEN_SECRET="txdy";  //密钥盐
    /**
     * 签名生成
     * @param user
     * @return
     */
    public static String sign(User user){
        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("id", user.getId())
                    .withClaim("openid",user.getOpenid())
                    .withClaim("username",user.getUsername())
                    .withClaim("telephon",user.getTelephon())
                    .withClaim("qqmailbox",user.getQqmailbox())
                    .withClaim("address",user.getAddress())
                    .withClaim("lntroduction",user.getLntroduction())
                    .withClaim("permissions",user.getPermissions())
                    .withClaim("sex",user.getSex())
                    .withExpiresAt(expiresAt)
                    // 使用了HMAC256加密算法。
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }
    /**
     * 签名验证
     * @param token
     * @return
     */
    public static boolean verify(String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
          //  System.out.println(jwt.getExpiresAt());
         //   System.out.println(jwt.getClaim("id"));

            return true;
        } catch (Exception e){
            return false;
        }
    }
}
