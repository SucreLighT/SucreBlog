package cn.sucrelt.sucreblog.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 拦截器类
 * 对于请求路径前缀为admin的路径，检测session中loginUser是否存在，
 * 如果不存在返回登录界面提示重新登录。
 * @author: sucre
 * @date: 2020/12/29
 * @time: 09:09
 */
@Component
public class AdminLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestServletPath = request.getServletPath();
        System.out.println("ServletPath：" + requestServletPath);
        if (requestServletPath.startsWith("/admin") && null == request.getSession().getAttribute("loginUser")) {
            request.getSession().setAttribute("errorMsg", "请重新登录！");
            response.sendRedirect(request.getContextPath() + "/admin/login");
            System.out.println("拦截成功，不放行！");
            return false;
        } else {
            request.getSession().removeAttribute("errorMsg");
            System.out.println("拦截器放行...");
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
