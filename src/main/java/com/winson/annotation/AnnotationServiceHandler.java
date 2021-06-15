package com.winson.annotation;

import java.lang.reflect.*;

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
                Object arg = args[index];
                if (parameter.isAnnotationPresent(NotEmpty.class)) {
                    if (arg == null) {
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
                if (parameter.isAnnotationPresent(ValidClass.class)) {
                    ValidClass validClass = parameter.getAnnotation(ValidClass.class);

                    Type type = parameter.getParameterizedType();
                    Class pc = Class.forName(type.getTypeName());
                    Class tc = validClass.targetClass();
                    Field[] fields = tc.getFields();
                    if (fields != null) {
                        for (Field field : fields) {
                            Field pf = pc.getDeclaredField(field.getName());
                            pf.setAccessible(true);
                            Object av = pf.get(arg);
                            if (field.isAnnotationPresent(NotEmpty.class)) {
                                if (av == null) {
                                    throw new IllegalArgumentException(String.format("参数%s,字段%s不能为空", parameter.getName(), pf.getName()));
                                }
                            }
                            if (field.isAnnotationPresent(MaxLength.class)) {
                                MaxLength aml = field.getAnnotation(MaxLength.class);
                                if (av != null && av.toString().length() > aml.value()) {
                                    throw new IllegalArgumentException(String.format("参数%s,字段%s长度不能大于%s", parameter.getName(), pf.getName(), aml.value()));
                                }

                            }
                        }
                    }
                }

                index++;
            }
        }
        Object result = method.invoke(target, args);
        return result;
    }

}
