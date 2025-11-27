package com.umc.study.global.annotation;

import com.umc.study.global.apiPayload.error.dto.ErrorCode;
import com.umc.study.global.apiPayload.error.exception.BusinessException;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class PageArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ValidPage.class) && 
               parameter.getParameterType().equals(Pageable.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String pageParam = webRequest.getParameter("page");
        
        if (pageParam == null) {
            throw new BusinessException(ErrorCode.BAD_REQUEST);
        }
        
        try {
            int page = Integer.parseInt(pageParam);
            if (page <= 0) {
                throw new BusinessException(ErrorCode.INVALID_PAGE_NUMBER);
            }
            return PageRequest.of(page - 1, 10);
        } catch (NumberFormatException e) {
            throw new BusinessException(ErrorCode.BAD_REQUEST);
        }
    }
}