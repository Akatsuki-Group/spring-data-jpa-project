package org.example.common;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 实现WebMvcConfigurer
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
//    @Autowired
//    private MyPageableHandlerMethodArgumentResolver myPageableHandlerMethodArgumentResolver;
    @Autowired
    private UserInfoArgumentResolver userInfoArgumentResolver;

    @Autowired
    private MyWarpWithDataHandlerMethodReturnValueHandler myWarpWithDataHandlerMethodReturnValueHandler;
    /**
     * 覆盖这个方法，把我们自定义的myPageableHandlerMethodArgumentResolver加载到原始的mvc的resolvers里面去
     *
     * @param resolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        //resolvers.add(myPageableHandlerMethodArgumentResolver);
        resolvers.add(userInfoArgumentResolver);
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        handlers.add(myWarpWithDataHandlerMethodReturnValueHandler);
    }
    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;
    //由于HandlerMethodReturnValueHandler处理的优先级问题，我们通过如下方法，把我们自定义的myWarpWithDataHandlerMethodReturnValueHandler放到第一个；
    @PostConstruct
    public void init() {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = Lists.newArrayList(myWarpWithDataHandlerMethodReturnValueHandler);
        returnValueHandlers.addAll(requestMappingHandlerAdapter.getReturnValueHandlers());
        requestMappingHandlerAdapter.setReturnValueHandlers(returnValueHandlers);
    }
}