package cn.sucrelt.sucreblog.controller.admin;

import cn.sucrelt.sucreblog.entity.AdminUser;
import cn.sucrelt.sucreblog.service.AdminUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
     * 请求首页的方法
     *
     * @param request
     * @return
     */
    @GetMapping({"", "/", "index", "/index.html"})
    public String index(HttpServletRequest request) {
        request.setAttribute("path", "index");

        return "admin/index";
    }

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
        session.setAttribute("errorMsg", "");
        //1.信息校验
        if (verifyCode.isEmpty()) {
            session.setAttribute("errorMsg", "验证码不能为空！");
            return "admin/login";
        }
        if (userName.isEmpty() || password.isEmpty()) {
            session.setAttribute("errorMsg", "用户名或密码不能为空！");
            return "admin/login";
        }
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if (kaptchaCode.isEmpty() || kaptchaCode.isEmpty()) {
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
            session.setAttribute("errorMsg", "登陆失败，用户名或密码错误！");
            return "admin/login";
        }
    }

    /**
     * Get方式请求profile界面的方法，该界面用于修改密码和用户名
     *
     * @param request
     * @return
     */
    @GetMapping("/profile")
    public String profile(HttpServletRequest request) {
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        AdminUser adminUser = adminUserService.getUserById(loginUserId);

        if (adminUser == null) {
            return "admin/login";
        }

        request.setAttribute("path", "profile");
        request.setAttribute("loginUserName", adminUser.getLoginUserName());
        request.setAttribute("nickName", adminUser.getNickName());
        return "admin/profile";
    }

    /**
     * 修改密码
     *
     * @param request
     * @param originalPassword
     * @param newPassword
     * @return
     */
    @PostMapping("/profile/password")
    @ResponseBody
    public String updatePassword(HttpServletRequest request, @RequestParam("originalPassword") String originalPassword,
                                 @RequestParam("newPassword") String newPassword) {
        if (originalPassword.isEmpty() || newPassword.isEmpty()) {
            return "参数不能为空";
        }
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        if (adminUserService.updatePassword(loginUserId, originalPassword, newPassword)) {
            //修改成功后清空session中的数据，前端控制跳转至登录页
            request.getSession().removeAttribute("loginUserId");
            request.getSession().removeAttribute("loginUser");
            request.getSession().removeAttribute("errorMsg");
            return "success";
        } else {
            return "修改失败";
        }
    }

    @PostMapping("/profile/name")
    @ResponseBody
    public String updateUserName(HttpServletRequest request, @RequestParam("loginUserName") String loginUserName,
                                 @RequestParam("nickName") String nickName) {
        if (loginUserName.isEmpty() || nickName.isEmpty()) {
            return "参数不能为空";
        }
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        if (adminUserService.updateUserName(loginUserId, loginUserName, nickName)) {
            return "success";
        } else {
            return "修改失败";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("loginUserId");
        request.getSession().removeAttribute("loginUser");
        request.getSession().removeAttribute("errorMsg");
        return "admin/login";
    }
}
