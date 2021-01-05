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

    @GetMapping("/categories")
    public String categoryPage(HttpServletRequest request) {
        request.setAttribute("path", "categories");
        return "admin/category";
    }

    /**
     * 展示分类列表
     *
     * @param params
     * @return
     */
    @GetMapping("/categories/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (params.get("page") != null || params.get("limit") != null) {
            return ResultGenerator.generateFailureResult("参数异常！");
        }
        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
        return ResultGenerator.generateSuccessResult(categoryService.getCategoryPage(pageQueryUtil));
    }

    /**
     * 添加分类
     *
     * @param categoryName
     * @param categoryIcon
     * @return
     */
    @PostMapping(value = "/categories/save")
    @ResponseBody
    public Result addCategory(@RequestParam("categoryName") String categoryName,
                              @RequestParam("categoryIcon") String categoryIcon) {
        if (categoryName.isEmpty()) {
            return ResultGenerator.generateFailureResult("请输入分类名称！");
        }
        if (categoryIcon.isEmpty()) {
            return ResultGenerator.generateFailureResult("请选择分类图标！");
        }
        if (categoryService.addCategory(categoryName, categoryIcon)) {
            return ResultGenerator.generateSuccessResult();
        } else {
            return ResultGenerator.generateFailureResult("添加失败！");
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
    @PostMapping(value = "/categories/update")
    @ResponseBody
    public Result updateCategory(@RequestParam("categoryId") Integer categoryId,
                                 @RequestParam("categoryName") String categoryName,
                                 @RequestParam("categoryIcon") String categoryIcon) {
        if (categoryName.isEmpty()) {
            return ResultGenerator.generateFailureResult("请输入分类名称！");
        }
        if (categoryIcon.isEmpty()) {
            return ResultGenerator.generateFailureResult("请选择分类图标！");
        }
        if (categoryService.updateCategory(categoryId, categoryName, categoryIcon)) {
            return ResultGenerator.generateSuccessResult();
        } else {
            return ResultGenerator.generateFailureResult("修改失败！");
        }
    }

    /**
     * 删除分类
     *
     * @param ids
     * @return
     */
    @PostMapping(value = "/categories/delete")
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.generateFailureResult("参数异常！");
        }
        if (categoryService.deleteCategories(ids)) {
            return ResultGenerator.generateSuccessResult();
        } else {
            return ResultGenerator.generateFailureResult("删除失败");
        }
    }
}
