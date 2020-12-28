package cn.sucrelt.sucreblog.controller.common;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * @description: 设置验证码的控制器类
 * @author: sucre
 * @date: 2020/12/28
 * @time: 15:12
 */

@Controller
@RequestMapping("/common")
public class VerifyCodeController {

    @Resource
    private DefaultKaptcha defaultKaptcha;

    @GetMapping("/kaptcha")
    public void setDefaultKaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception{
        byte[] captcha;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            // 生成验证码文字
            String verifyCode = defaultKaptcha.createText();
            // 将生成的验证码保存在session中
            request.getSession().setAttribute("verifyCode", verifyCode);
            BufferedImage bufferedImage = defaultKaptcha.createImage(verifyCode);
            ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 设置返回信息
        captcha = byteArrayOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        ServletOutputStream servletOutputStream = response.getOutputStream();
        servletOutputStream.write(captcha);
        servletOutputStream.flush();
        servletOutputStream.close();
    }
}
