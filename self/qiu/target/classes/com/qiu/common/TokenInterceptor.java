package com.qiu.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 自定义拦截器
 * 自定义拦截器后，需要配置进Spring
 * <p>
 * 拦截器Interceptor可以拿到原始的HTTP请求和响应的信息，
 * 也可以拿到你真正处理请求方法的信息，但是拿不到传进参数的那个值。
 * <p>
 * 拦截顺序：filter—>Interceptor-->ControllerAdvice-->@Aspect -->Controller
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    /**
     * 在访问Controller某个方法之前这个方法会被调用。
     *
     * @param request
     * @param response
     * @param handler
     * @return false则表示不执行postHandle方法, true 表示执行postHandle方法
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        log.info("Token Interceptor preHandle {}","");


        //1.获取请求url
        String url = request.getRequestURL().toString();
        log.info("请求路径：{}", url); //请求路径：http://localhost:8080/login
        //2.判断请求url中是否包含login，如果包含，说明是登录操作，放行
        if (url.contains("/login")) {
            log.info("放行路径：" + url);
            return true;//结束当前方法的执行
        }

        String token = request.getHeader("Authorization");
        log.info("当前token为：" + token);
//        log.info("Token Interceptor preHandle token :{}",token);
//        log.info("Token Interceptor preHandle url {}",url);

        //spring boot 2.0对静态资源也进行了拦截，当拦截器拦截到请求之后，
        // 但controller里并没有对应的请求时，该请求会被当成是对静态资源的请求。
        // 此时的handler就是 ResourceHttpRequestHandler，就会抛出上述错误。
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            log.info("当前请求方法为：" + method.getName());
//            log.info("Token Interceptor preHandle getMethod {}",method.getName());
        } else if (handler instanceof ResourceHttpRequestHandler) {//静态资源
            ResourceHttpRequestHandler resourceHttpRequestHandler = (ResourceHttpRequestHandler) handler;
            log.info("当前请求方法为：" + resourceHttpRequestHandler.getMediaTypes());
//            log.info("Token Interceptor preHandle getMethod {}",resourceHttpRequestHandler.getMediaTypes());
        }

        //false则表示不执行postHandle方法,不执行下一步chain链，直接返回response
        return true;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     * preHandle方法处理之后这个方法会被调用，如果控制器Controller出现了异常，则不会执行此方法
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
////        log.info("Token Interceptor postHandle");
//        log.info("请求已处理！");
//    }


    /**
     * 不管有没有异常，这个afterCompletion都会被调用
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
////        log.info("Token Interceptor afterCompletion");
//        log.info("Token 拦截器 工作完成！");
//    }

}
