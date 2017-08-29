package com.baixiang.manageController;

import com.baixiang.exception.UserNotFoundException;
import com.baixiang.model.BaseBean;
import com.baixiang.model.Error;
import com.baixiang.model.RedirectBean;
import com.baixiang.model.Response;
import com.baixiang.model.User;
import com.baixiang.service.UserService;
import com.baixiang.utils.FileUtil;
import com.mongodb.DuplicateKeyException;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

/**
 * Created by shenjiajun on 2017/4/4.
 */

@ControllerAdvice
@RestController
public class ManageUserController {
    @Autowired
    UserService userService;

    @Autowired
    ApplicationContext applicationContext;

    private static Logger logger = Logger.getLogger(ManageUserController.class);

    @RequestMapping(value = "/api/SignIn", method = RequestMethod.POST)
    private Response<RedirectBean> signIn(@RequestParam(value = "userName") String userName,
                                          @RequestParam(value = "pass") String pass) {
        UsernamePasswordToken token = new UsernamePasswordToken(userName, pass, true);
        try {
            SecurityUtils.getSubject().login(token);
        } catch (IncorrectCredentialsException e) {
            throw e;
        } catch (AuthenticationException e) {
            throw new UserNotFoundException();
        }
        RedirectBean redirectBean = new RedirectBean("/");
        Response<RedirectBean> response = new Response<>(redirectBean, null);
        return response;
    }

    @RequestMapping(value = "/api/SignUp", method = RequestMethod.POST)
    public Response<BaseBean> signUp(@RequestParam(value = "userName") String userName,
                                     @RequestParam(value = "pass") String pass,
                                     @RequestParam(value = "userIntro") String userIntro,
                                     @RequestParam("avatar") MultipartFile avatar) {
        ModelAndView modelAndView = new ModelAndView("/index");

        System.out.printf("signUp!!!!");
        User user = new User(userName, pass);
        user.setUserIntro(userIntro);
        if (!avatar.isEmpty()) {
            try {
                String staticPath = System.getProperty("user.dir") + "/src/main/webapp";
                String filePath = "/files/avatar/";
                FileUtil.createOrExistsDir(staticPath + filePath);

                String fileName = System.currentTimeMillis() + "-" + avatar.getOriginalFilename();
                File file = new File(staticPath + filePath + fileName);
                avatar.transferTo(file);
                user.setAvatarPath(filePath + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        userService.save(user);
        BaseBean baseBean;
        if (user.getId() != 0) {
            baseBean = new BaseBean(1);
        } else {
            baseBean = new BaseBean(2);
        }
        return new Response<>(baseBean, null);
    }

    @RequestMapping("/api/SignOut")
    public Response<RedirectBean> signOut() {
        SecurityUtils.getSubject().logout();
        RedirectBean redirectBean = new RedirectBean("/");
        Response<RedirectBean> response = new Response<>(redirectBean, null);
        return response;
    }

    @RequestMapping(value = "/api/getUserInfo", method = RequestMethod.POST)
    public Response<User> getUserInfo() {
        Subject subject = SecurityUtils.getSubject();
        Long userId = (Long) subject.getPrincipal();
        System.out.printf("userId=" + userId);
        User user = userService.getById(userId);
        Response<User> response = new Response<>(user, null);
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

    @ExceptionHandler(value = DuplicateKeyException.class)
    public Response<Object> handleDuplicateKeyException() {
        System.out.println("handleDuplicateKeyException!!!!!!!!!");
        Response<Object> response = new Response(null, new Error("1", "此用户名已被占用"));
        return response;
    }


    @RequestMapping(value = "/api/SignInSuccess", method = RequestMethod.GET)
    public Response<RedirectBean> signInSuccess() {
        System.out.printf("signInSuccess");
        RedirectBean redirectBean = new RedirectBean("/");
        Response<RedirectBean> response = new Response<>(redirectBean, null);
        return response;
    }

    @RequestMapping(value = "/api/SignInFailed", method = RequestMethod.GET)
    public Response<RedirectBean> signInFailed() {
        RedirectBean redirectBean = new RedirectBean("/");
        Response<RedirectBean> response = new Response<>(null, new Error("1", "用户名或密码错误"));
        return response;
    }

    @RequestMapping(value = "/api/SignOutSuccess", method = RequestMethod.GET)
    public Response<RedirectBean> signOutSuccess() {
        RedirectBean redirectBean = new RedirectBean("/");
        Response<RedirectBean> response = new Response<>(redirectBean, null);
        return response;
    }
}
