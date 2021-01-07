package cn.sucrelt.sucreblog.controller.admin;

import cn.sucrelt.sucreblog.service.TagService;
import cn.sucrelt.sucreblog.util.PageQueryUtil;
import cn.sucrelt.sucreblog.util.Result;
import cn.sucrelt.sucreblog.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @description:
 * @author: sucre
 * @date: 2021/01/07
 * @time: 15:06
 */

@Controller
@RequestMapping("/admin")
public class TagController {

    @Resource
    private TagService tagService;

    @GetMapping("/tags")
    public String tagPage(HttpServletRequest request) {
        request.setAttribute("path", "tags");
        return "admin/tag";
    }

    @GetMapping("/tags/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (params.get("page") == null || params.get("limit") == null) {
            return ResultGenerator.generateFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.generateSuccessResult(tagService.getBlogTagPage(pageUtil));
    }

    @PostMapping("/tags/save")
    @ResponseBody
    public Result addTag(@RequestParam("tagName") String tagName) {
        if (tagName.isEmpty()) {
            return ResultGenerator.generateFailResult("参数异常！");
        }
        if (tagService.addTag(tagName)) {
            return ResultGenerator.generateSuccessResult();
        } else {
            return ResultGenerator.generateFailResult("添加失败，标签名已存在！");
        }
    }

    @PostMapping("/tags/delete")
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.generateFailResult("参数异常！");
        }
        if (tagService.deleteTags(ids)) {
            return ResultGenerator.generateSuccessResult();
        } else {
            return ResultGenerator.generateFailResult("删除失败，有关联文章！");
        }
    }

}
