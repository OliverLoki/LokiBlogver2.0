package com.loki.intercetpor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.loki.dto.Result;
import com.loki.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //官方建议把Jwt放在请求头
        //获取请求头中的令牌
        //返回统一封装的结果
        Result result = new Result();
        String token = request.getHeader("token");
        try {
            //log.info("前端返回的令牌{{}}}",token);
            JwtUtil.verify(token);//验证令牌是否正确
            return true;//请求放行
        } catch (SignatureVerificationException e) {//签名不一致异常
            e.printStackTrace();
            result.setMsg("签名不一致异常");
        } catch (TokenExpiredException e) {//令牌过期异常
            e.printStackTrace();
            result.setMsg("令牌过期异常");
        } catch (AlgorithmMismatchException e) {//算法不匹配异常
            e.printStackTrace();
            result.setMsg("算法不匹配异常");
        }catch (InvalidClaimException e){//失效的payload异常
            e.printStackTrace();
            result.setMsg("失效的payload异常");
        }catch (Exception e){
            e.printStackTrace();
        }
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(result);


        return false;//为了用户友好度，需要返回给前端错误原因

    }
}
