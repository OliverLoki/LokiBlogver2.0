package com.loki.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
/*  整体思路
 *      客户端通过用户名和密码向服务器发送请求登陆
 *      服务器收到请求数据，在数据库进行查询验证
 *      如果验证成功，服务器签发一个Token给客户端
 *      客户端可以将Token存放到LocalStorage或者Cookie里
 *      客服端设置监听，每次跳转路由，就判断 LocalStroage 中有无 Token ，没有就跳转到登录页面，有则跳转到对应路由页面
 *      在Axios每次调后端接口，都要在请求头中加Token
 *      在后端设置拦截器，用户登录后的每次请求都会经过这个拦截器校验Token是否有效
 *      如果验证成功，则继续执行请求，返回请求到的数据
 *
我们 POST 用户名与密码到 /login 进行登入，如果成功返回一个加密 token，失败的话直接返回 401 错误。
之后用户访问每一个需要权限的网址请求必须在 header 中添加 Authorization 字段，
例如 Authorization: token ，token 为密钥。
后台会进行 token 的校验，如果有误会直接返回 401。
 */
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.interfaces.Claim;

import java.util.Calendar;
import java.util.Map;

public class JwtUtil {

    /**
     * 密钥
     */
    private static String SECRET = "privatekey#^&^%!save";

    /**
     * 传入payload信息获取token
     *
     * @param map payload
     * @return token
     */
    public static String getToken(Map<String, String> map) {
        JWTCreator.Builder builder = JWT.create();

        //payload
        //通过map集合生成token的对象
        map.forEach(builder::withClaim);

        Calendar instance = Calendar.getInstance();

        instance.add(Calendar.DATE, 3); //默认3天过期

        builder.withExpiresAt(instance.getTime());//指定令牌的过期时间
        //指定加密方式
        return builder.sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 验证token
     */
    public static DecodedJWT verify(String token) {
        //如果有任何验证异常，此处都会抛出异常
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }

    /**
     * 获取token中的payload
     * Payload
     * Payload 部分也是一个 JSON 对象，用来存放实际需要传递的数据。JWT 规定了7个官方字段，供选用。
     */
    public static Map<String, Claim> getPayloadFromToken(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token).getClaims();
    }
}