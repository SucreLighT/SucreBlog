package cn.sucrelt.sucreblog.controller.admin;

import cn.sucrelt.sucreblog.config.ConstantsConfig;
import cn.sucrelt.sucreblog.util.MyBlogUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


/**
 * @description:
 * @author: sucre
 * @date: 2021/01/09
 * @time: 13:22
 */

@Controller
@RequestMapping("/admin")
public class BlogController {

    @GetMapping("/blogs/edit")
    public String edit(HttpServletRequest request) {
        request.setAttribute("path", "edit");
        return "admin/edit";
    }

    @PostMapping("/blogs/md/uploadfile")
    public void uploadFileByEditorMd(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam(name = "editormd-image-file", required = true) MultipartFile file) throws IOException, URISyntaxException {
        // 获取文件前缀
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        // 生成文件名
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        StringBuilder tempName = new StringBuilder();
        tempName.append(simpleDateFormat.format(new Date())).append(r.nextInt()).append(suffixName);
        String newFileName = tempName.toString();

        // 文件上传位置
        File destFile = new File(ConstantsConfig.FILE_UPLOAD_DIC + newFileName);
        String fileUrl = MyBlogUtils.getHost(new URI(request.getRequestURL() + "")) + "/upload/" + newFileName;
        File fileDirectory = new File(ConstantsConfig.FILE_UPLOAD_DIC);
        try {
            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdir()) {
                    throw new IOException("文件夹创建失败,路径为：" + fileDirectory);
                }
            }
            file.transferTo(destFile);
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html");
            response.getWriter().write("{\"success\": 1, \"message\":\"success\",\"url\":\"" + fileUrl + "\"}");
        } catch (UnsupportedEncodingException e) {
            response.getWriter().write("{\"success\":0}");
        } catch (IOException e) {
            response.getWriter().write("{\"success\":0}");
        }
    }
}
