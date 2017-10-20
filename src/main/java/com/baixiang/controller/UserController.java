package com.baixiang.controller;

import com.baixiang.exception.UserNotFoundException;
import com.baixiang.model.jpa.User;
import com.baixiang.model.mongo.Role;
import com.baixiang.model.response.Error;
import com.baixiang.model.response.RedirectBean;
import com.baixiang.model.response.Response;
import com.baixiang.model.response.UserBean;
import com.baixiang.service.UserService;
import com.baixiang.utils.FileUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

import static com.baixiang.utils.Urls.API_USER_SIGN_IN;
import static com.baixiang.utils.Urls.API_USER_SIGN_UP;
import static com.baixiang.utils.Urls.SIGN_OUT;

/**
 * Created by shenjj on 2017/5/16.
 */


@ControllerAdvice
@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;


    @RequestMapping(value = API_USER_SIGN_IN, method = RequestMethod.POST)
    public Response<RedirectBean> signIn(@RequestParam(value = "userName") String userName,
                                         @RequestParam(value = "pass") String pass) {
        UsernamePasswordToken token = new UsernamePasswordToken(userName, pass, true);
        token.setRememberMe(true);
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.getSession().setTimeout(-1000L);
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
            RedirectBean redirectBean = new RedirectBean(1, "/manage");
            Response<RedirectBean> response = new Response<>(redirectBean, null);
            return response;
        } else {
            throw new UserNotFoundException();
        }
    }

    @RequestMapping(value = API_USER_SIGN_UP, method = RequestMethod.POST)
    public Response<RedirectBean> signUp(@RequestParam(value = "userName") String userName,
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
        user.addRole(Role.BangZhu);
        user = userService.save(user);
        RedirectBean redirectBean;
        if (user.getId() != 0) {
            logger.info("保存用户成功");
            return signIn(userName, pass);
        } else {
            redirectBean = new RedirectBean(2, "");
            Response<RedirectBean> response = new Response<>(redirectBean, new Error("1", "注册失败"));
            return response;
        }
    }

    @RequestMapping(value = SIGN_OUT, method = RequestMethod.GET)
    public ModelAndView signOut() {
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        return modelAndView;
    }

    @RequestMapping(value = "/api/getUserInfo", method = RequestMethod.POST)
    public Response getUserInfo() {
        Response<UserBean> response = new Response<>(userService.getUserBeanBySession(), null);
        return response;
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

    @ExceptionHandler(value = ConstraintViolationException.class)
    public Response<Object> handleDuplicateKeyException() {
        System.out.println("handleDuplicateKeyException!!!!!!!!!");
        Response<Object> response = new Response(null, new Error("1", "此用户名已被占用"));
        return response;
    }
}
