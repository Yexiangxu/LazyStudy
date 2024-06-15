package lazyxu;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
//https://blog.csdn.net/qq_32106517/article/details/74788648
public class Pox implements InvocationHandler {
    private  Object target;

    public Pox(Object object){
        this.target=object;
    }
    public Object getInstance() {
        Object o = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
        return o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName()+"执行前");
        Object object = method.invoke(target, args);
        System.out.println(object);
        System.out.println(method.getName() + "方法执行后");
        return object;
    }
}
