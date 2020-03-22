package me.sam.designpattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * desc : 动态代理
 * date : 2020/3/3 9:58 PM
 *
 * @author : dongSen
 */
public class DynamicProxy {

    public static void main(String[] args) {
        final RealSubject realSubject = new RealSubject();
        Subject proxySubject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(),
                new Class[]{Subject.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("In the proxy");
                        return method.invoke(realSubject, args);
                    }
                });

        proxySubject.doSomething();
        proxySubject.doAnything();
    }

}
