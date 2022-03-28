package com.loki.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loki.dto.LoginDto;
import com.loki.dto.Result;
import com.loki.pojo.User;
import com.loki.service.UserService;
import com.loki.utils.JwtUtil;
import org.apache.shiro.util.Assert;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

import static com.loki.utils.MD5Util.convertMD5;
import static com.loki.utils.MD5Util.string2MD5;

@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    //localhost:8000/login
    public Result login(@Validated @RequestBody LoginDto loginDto,
                        HttpServletResponse response) {
        //通过不加密的账户查出账户
            User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        //TODO 断言异常好像不适用用实际环境？
        Assert.notNull(user,"用户不存在");
        //使用MD5工具类解密
        String s = string2MD5(loginDto.getPassword());
        if (user.getPassword().equalsIgnoreCase(s)) {//密码正确
            //向payload中传值
            HashMap<String, String> payload = new HashMap<>();
            payload.put("role", user.getRole());
            payload.put("username", user.getUsername());
            payload.put("avatar",user.getAvatar());
            payload.put("email",user.getEmail());
            payload.put("github",user.getGithub());
            payload.put("userId", String.valueOf(user.getUserId()));

            //生成token
            String token = JwtUtil.getToken(payload);
            //返回token
            response.setHeader("Authorization",token);
            response.setHeader("Access-control-Expose-Headers","Authorization");
            //返回用户信息
            //注意这里返回的是payload的信息
            return Result.succ(200, "登录成功", payload);
        }

        return Result.fail("密码不正确");

    }

   /* @GetMapping("/logout")
    public Result logout(){

    }*/
}
