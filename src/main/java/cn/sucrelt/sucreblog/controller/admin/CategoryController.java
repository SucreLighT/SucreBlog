package cn.sucrelt.sucreblog.controller.admin;

import cn.sucrelt.sucreblog.service.CategoryService;
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
 * @date: 2020/12/31
 * @time: 14:17
 */

@Controller
@RequestMapping("/admin")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    /**
     * GET方式请求分类界面
     *
     * @param request
     * @return
     */
    @GetMapping("/categories")
    public String categoryPage(HttpServletRequest request) {
        request.setAttribute("path", "categories");
        return "admin/category";
    }


    /**
     * GET方式请求分类界面的分类列表数据
     *
     * @param params
     * @return
     */
    @GetMapping("/categories/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (params.get("page") == null || params.get("limit") == null) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(categoryService.getBlogCategoryPage(pageUtil));
    }


    /**
     * 添加分类
     *
     * @param categoryName
     * @param categoryIcon
     * @return
     */
    @PostMapping("/categories/save")
    @ResponseBody
    public Result addCategory(@RequestParam("categoryName") String categoryName,
                              @RequestParam("categoryIcon") String categoryIcon) {
        if (categoryName.isEmpty()) {
            return ResultGenerator.genFailResult("请输入分类名称！");
        }
        if (categoryIcon.isEmpty()) {
            return ResultGenerator.genFailResult("请选择分类图标！");
        }
        if (categoryService.addCategory(categoryName, categoryIcon)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("添加失败，分类名已存在！");
        }
    }


    /**
     * 修改分类
     *
     * @param categoryId
     * @param categoryName
     * @param categoryIcon
     * @return
     */
    @PostMapping("/categories/update")
    @ResponseBody
    public Result updateCategory(@RequestParam("categoryId") Integer categoryId,
                                 @RequestParam("categoryName") String categoryName,
                                 @RequestParam("categoryIcon") String categoryIcon) {
        if (categoryName.isEmpty()) {
            return ResultGenerator.genFailResult("请输入分类名称！");
        }
        if (categoryIcon.isEmpty()) {
            return ResultGenerator.genFailResult("请选择分类图标！");
        }
        if (categoryService.updateCategory(categoryId, categoryName, categoryIcon)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败，分类名已存在！");
        }
    }


    /**
     * 删除分类
     *
     * @param ids
     * @return
     */
    @PostMapping("/categories/delete")
    @ResponseBody
    public Result deleteCategories(@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (categoryService.deleteCategories(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败！");
        }
    }
}
