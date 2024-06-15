//package com.lazyxu.base.utils;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.Modifier;
//import java.lang.reflect.Proxy;
//import java.util.ArrayDeque;
//import java.util.Collections;
//import java.util.Deque;
//
//import retrofit2.Platform;
//
//
//public class CheckUtil {
//    /**
//     * retrofit核心是动态代理
//     * create为retrofit最核心代码
//     * 首先对apiservice接口做校验，必须是接口，而且不是泛型
//     *
//     * 动态代理
//     * 在运行时生成，不是在编译的时候
//     *
//     * adapter把数据库数据转为展示数据
//     *
//     * Executor跟thread差不多，性能更好更方便
//     */
//    private void validateServiceInterface(Class<?> service) {
//        if (!service.isInterface()) {
//            throw new IllegalArgumentException("API declarations must be interfaces.");
//        }
//
//        Deque<Class<?>> check = new ArrayDeque<>(1);
//        check.add(service);
//        while (!check.isEmpty()) {
//            Class<?> candidate = check.removeFirst();
//            //getTypeParameters <T>泛型，校验不能是泛型
//            if (candidate.getTypeParameters().length != 0) {
//                StringBuilder message =
//                        new StringBuilder("Type parameters are unsupported on ").append(candidate.getName());
//                if (candidate != service) {
//                    message.append(" which is an interface of ").append(service.getName());
//                }
//                throw new IllegalArgumentException(message.toString());
//            }
//            //getInterfaces 该接口父类
//            Collections.addAll(check, candidate.getInterfaces());
//        }
//
//        if (validateEagerly) {//激进验证
//            Platform platform = Platform.get();
//            for (Method method : service.getDeclaredMethods()) {
//                if (!platform.isDefaultMethod(method) && !Modifier.isStatic(method.getModifiers())) {
//                    loadServiceMethod(method);
//                }
//            }
//        }
//
//    }
//
//    public <T> T create(final Class<T> service) {
//        validateServiceInterface(service);
//        return (T)
//                Proxy.newProxyInstance(
//                        service.getClassLoader(),
//                        new Class<?>[] {service},
//                        new InvocationHandler() {
//                            private final Platform platform = Platform.get();
//                            private final Object[] emptyArgs = new Object[0];
//
//                            @Override
//                            public @Nullable Object invoke(Object proxy, Method method, @Nullable Object[] args)//invoke里面又是动态代理的核心
//                                    throws Throwable {
//                                // If the method is a method from Object then defer to normal invocation.
//                                if (method.getDeclaringClass() == Object.class) {
//                                    return method.invoke(this, args);
//                                }
//                                args = args != null ? args : emptyArgs;
//                                return platform.isDefaultMethod(method)
//                                        ? platform.invokeDefaultMethod(method, service, proxy, args)
//                                        : loadServiceMethod(method).invoke(args);
//                            }
//                        });
//    }
//}
