package com.swagger.config;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

// 国际化解析器 自动配置
public class MyLocaleResolver implements LocaleResolver {

    // 解析请求
    @Override
    public Locale resolveLocale(HttpServletRequest request) {

        // 获取请求中的请求参数
        String language = request.getParameter("l");
        Locale locale = Locale.getDefault(); // 默认的如果没有就使用默认的

        // 如果不为空，则请求里携带了国际化的参数
        if (!StringUtils.isEmpty(language)){
            // zh_CN
            String[] split = language.split("_");

            // 国家，地区
            locale = new Locale(split[0], split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
