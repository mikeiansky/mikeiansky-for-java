package com.winson.annotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author winson
 * @date 2021/6/15
 **/
public class AnnotationServiceHandler implements InvocationHandler {

    public Object target;

    public AnnotationServiceHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("annotation service handler invoke ... ");
        Parameter[] parameters = method.getParameters();
        if (parameters != null) {
            int index = 0;
            for (Parameter parameter : parameters) {
                System.out.println(parameter);
                if (parameter.isAnnotationPresent(NotEmpty.class)) {
                    if (args[index] == null) {
                        throw new IllegalArgumentException(String.format("参数%s不能为空", parameter.getName()));
                    }
                }
                if (parameter.isAnnotationPresent(MaxLength.class)) {
                    MaxLength maxLength = parameter.getAnnotation(MaxLength.class);
                    int length = maxLength.value();
                    if (args[index].toString().length() > length) {
                        throw new IllegalArgumentException(String.format("参数%s长度不能大于%s", parameter.getName(), length));
                    }
                }
                index++;
            }
        }
        Object result = method.invoke(target, args);
        return result;
    }

}
