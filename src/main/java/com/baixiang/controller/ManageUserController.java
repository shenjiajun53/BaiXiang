package com.baixiang.controller;

import com.baixiang.exception.UserNotFoundException;
import com.baixiang.model.*;
import com.baixiang.model.Error;
import com.baixiang.repository.UserRepository;
import com.baixiang.utils.FileUtil;
import com.mongodb.DuplicateKeyException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

/**
 * Created by shenjj on 2017/5/16.
 */


@ControllerAdvice
@RestController
public class ManageUserController {
    private static final Logger logger = LoggerFactory.getLogger(ManageUserController.class);

    @Autowired
    UserRepository userRepository;


    @RequestMapping(value = "/manage/sign_up")
    public ModelAndView signUp() {
        ModelAndView modelAndView = new ModelAndView("/manage/manage_sign_up");
        return modelAndView;
    }

    @RequestMapping(value = "/manage/sign_in")
    public ModelAndView signIn() {
        ModelAndView modelAndView = new ModelAndView("/manage/manage_sign_in");
        return modelAndView;
    }

    @RequestMapping(value = "/manage/sign_in", method = RequestMethod.POST)
    public Response<RedirectBean> signIn(@RequestParam(value = "userName") String userName,
                                         @RequestParam(value = "pass") String pass) {
        UsernamePasswordToken token = new UsernamePasswordToken(userName, pass, true);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            logger.info("对用户[" + userName + "]进行登录验证..验证开始");
            currentUser.login(token);
            logger.info("对用户[" + userName + "]进行登录验证..验证通过");
        } catch (IncorrectCredentialsException e) {
            throw e;
        } catch (AuthenticationException e) {
            throw new UserNotFoundException();
        }
        if (currentUser.isAuthenticated()) {
            RedirectBean redirectBean = new RedirectBean("/manage");
            Response<RedirectBean> response = new Response<>(redirectBean, null);
            return response;
        } else {
            throw new UserNotFoundException();
        }
    }

    @RequestMapping(value = "/manage/sign_up", method = RequestMethod.POST)
    public Response<BaseBean> signUp(@RequestParam(value = "userName") String userName,
                                     @RequestParam(value = "pass") String pass,
                                     @RequestParam(value = "userIntro") String userIntro,
                                     @RequestParam(value = "sex") String sex,
                                     @RequestParam(value = "avatar", required = false) MultipartFile avatar) {
        User user = new User(userName, pass);
        user.setSex(sex);
        user.setUserIntro(userIntro);
        if (null != avatar && !avatar.isEmpty()) {
            try {
                String staticPath = System.getProperty("user.dir") + "/src/main/webapp";
                String filePath = "/files/user/avatar/";
                FileUtil.createOrExistsDir(staticPath + filePath);

                String fileName = System.currentTimeMillis() + "-" + avatar.getOriginalFilename();
                File file = new File(staticPath + filePath + fileName);
                avatar.transferTo(file);
                user.setAvatarPath(filePath + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        userRepository.save(user);
        BaseBean baseBean;
        if (user.getId() != 0) {
            baseBean = new BaseBean(1);
        } else {
            baseBean = new BaseBean(2);
        }
        Response<BaseBean> response = new Response<>(baseBean, null);
        return response;
    }

    @RequestMapping(value = "/manage/logout", method = RequestMethod.GET)
    public String logout() {
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        return "redirect:/login";
    }


    @ExceptionHandler(value = IncorrectCredentialsException.class)
    public Response<Object> handleIncorrectCredentialsException() {
        System.out.println("handleUserNotFoundException!!!!!!!!!");
        Response<Object> response = new Response(null, new Error("1", "用户名或密码错误"));
        return response;
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public Response<Object> handleUserNotFoundException() {
        System.out.println("handleUserNotFoundException!!!!!!!!!");
        Response<Object> response = new Response(null, new Error("1", "没有此用户"));
        return response;
    }

    @ExceptionHandler(value = DuplicateKeyException.class)
    public Response<Object> handleDuplicateKeyException() {
        System.out.println("handleDuplicateKeyException!!!!!!!!!");
        Response<Object> response = new Response(null, new Error("1", "此用户名已被占用"));
        return response;
    }
}
