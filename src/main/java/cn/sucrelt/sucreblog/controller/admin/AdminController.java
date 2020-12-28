package cn.sucrelt.sucreblog.controller.admin;

import cn.sucrelt.sucreblog.entity.AdminUser;
import cn.sucrelt.sucreblog.service.AdminUserService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @description:
 * @author: sucre
 * @date: 2020/12/28
 * @time: 10:16
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminUserService adminUserService;

    /**
     * Get方式请求登录的方法，不执行登录逻辑，直接返回登录界面
     *
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    /**
     * Post方式请求登录的方法
     * 1. 校验用户名、密码以及验证码数据
     * 2. 封装AdminUser对象并存入session中
     * 3. 跳转页面
     *
     * @param userName
     * @param password
     * @param verifyCode
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        @RequestParam("verifyCode") String verifyCode,
                        HttpSession session) {
        session.setAttribute("errorMsg","");
        //1.信息校验
        if (!StringUtils.hasLength(verifyCode)) {
            session.setAttribute("errorMsg", "验证码不能为空！");
            return "admin/login";
        }
        if (!StringUtils.hasLength(userName) || !StringUtils.hasLength(password)) {
            session.setAttribute("errorMsg", "用户名或密码不能为空！");
            return "admin/login";
        }
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if (!StringUtils.hasLength(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
            session.setAttribute("errorMsg", "验证码错误！");
            return "admin/login";
        }

        //2.封装数据
        AdminUser adminUser = adminUserService.login(userName, password);
        if (adminUser != null) {
            session.setAttribute("loginUser", adminUser.getNickName());
            session.setAttribute("loginUserId", adminUser.getAdminUserId());
            //session过期时间为2小时
            session.setMaxInactiveInterval(60 * 60 * 2);
            return "redirect:/admin/index";
        } else {
            session.setAttribute("errorMsg", "登陆失败！");
            return "admin/login";
        }
    }
}
