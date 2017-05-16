package com.baixiang.manage.controller;

import com.baixiang.model.BaseBean;
import com.baixiang.model.Response;
import com.baixiang.model.User;
import com.baixiang.repository.UserRepository;
import com.baixiang.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

/**
 * Created by shenjj on 2017/5/16.
 */

@RestController
public class ManageUserController {

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
        if (user.getId()!=0) {
            baseBean = new BaseBean(1);
        } else {
            baseBean = new BaseBean(2);
        }
        Response<BaseBean> response=new Response<>(baseBean,null);
        return response;
    }
}
