package com.yujy.travels.controller;

import com.yujy.travels.entity.Result;
import com.yujy.travels.entity.User;
import com.yujy.travels.service.UserService;
import com.yujy.travels.utils.ValidateImageCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("/user")
@CrossOrigin//允许跨域
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public Result login(@RequestBody User user, HttpServletRequest request) {
        Result result = new Result();
        try {
            User userDb = userService.login(user);
            //登录成功后
            request.getServletContext().setAttribute(userDb.getId(),userDb);
            result.setMsg("登录成功").setUserId(userDb.getId());
        } catch (Exception e) {
            result.setMsg("登录失败").setStatus(false);

        }
        return result;
    }

    /**
     * 用户注册
     *
     * @param code
     * @param user
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public Result register(String code, @RequestBody User user, HttpSession session) {
        Result result = new Result();
        //验证验证码
        String sessioncode = (String) session.getAttribute("code");
        try {
            userService.register(user);
            result.setMsg("注册成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg(e.getMessage()).setStatus(false);
        }
        return result;
    }

    /**
     * 获取验证码
     *
     * @param response
     * @param session
     */
    @GetMapping("/getImage")
    public void getImage(HttpServletResponse response, HttpSession session) throws IOException {
        //获取验证码
        String securityCode = ValidateImageCodeUtils.getSecurityCode();
        //验证码存入session
        session.setAttribute("code", securityCode);
        //生成图片
        BufferedImage image = ValidateImageCodeUtils.createImage(securityCode);
        //响应浏览器
        response.setContentType("image/png");
        ImageIO.write(image, "png", response.getOutputStream());
        //页面响应验证码

    }


}
