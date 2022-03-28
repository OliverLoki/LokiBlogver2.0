package com.loki;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loki.dto.Result;
import com.loki.pojo.User;
import com.loki.service.UserService;
import com.loki.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;

@Slf4j
public class JwtController {
    @Autowired
    UserService userService;

    @GetMapping("/login/{username}/{password}")
    public Result login(@PathVariable("username") String username,
                        @PathVariable("password") String password) {
        User u = userService.getOne(new QueryWrapper<User>().eq("username", username));

        if (u.getPassword().equals(password)) {//如果密码正确
            HashMap<String, String> payload = new HashMap<>();
            payload.put("role", u.getRole());
            payload.put("username", u.getUsername());
            //生成token
            String token = JwtUtil.getToken(payload);

            return Result.succ(200, "登录成功", token);
        } else {
            return Result.fail("用户名或密码错误");
        }
    }

    //模拟验签，做细粒度处理,如果这样的话
    @GetMapping("/test/{token}")
    public Result test(@PathVariable  String token) {
        log.info("===token", token);
        try {
            JwtUtil.verify(token);//验证令牌是否正确
            return Result.succ(200,"请求成功",userService.list());
        } catch (SignatureVerificationException e) {//签名不一致异常
            e.printStackTrace();
        } catch (TokenExpiredException e) {//令牌过期异常
            e.printStackTrace();
        } catch (AlgorithmMismatchException e) {//算法不匹配异常
            e.printStackTrace();
        }catch (InvalidClaimException e){//失效的payload异常
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail("错误啦，可以进一步做细粒度处理");
    }
}
