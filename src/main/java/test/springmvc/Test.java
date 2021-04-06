package test.springmvc;

import org.springframework.web.servlet.DispatcherServlet;

/**
 * @Author l
 * @Date 2021/3/8 23:58
 * @Version 1.0
 */
public class Test {
      /**
      * @Author: lcy
      * @Description //TODO
      * @Date 9:32 2021/3/9
       *
       * -------------------------Spring整合SpringMVC-----------------------------
       *
       *
       *
       *
       *
       * -------------------------SpringMVC处理请求流程----------------------------
       * 1）dispatchServlet,从HandlerMapping获取mappedHandler
       * 2）获取handlerAdapter
       * 3）调用拦截器的preHandle
       * 4）直接handlerAdapter.handle 生成ModelAndView
       * 5) 调用拦截器的postHandle
       * 6）进行调用viewResolve进行渲染
       * 7）调用拦截器的afterCompletion
       */



    public static void main(String[] args) {
        final DispatcherServlet dispatcherServlet = new DispatcherServlet();

    }
}
