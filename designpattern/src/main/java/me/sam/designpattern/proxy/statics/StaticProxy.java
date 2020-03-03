package me.sam.designpattern.proxy.statics;

/**
 * desc : 静态代理
 * date : 2020/3/3 9:57 PM
 *
 * @author : dongSen
 */
public class StaticProxy {

    public static void main(String[] args) {
        Subject proxy = new ProxySubject();
        Subject real = new RealSubject(proxy);
        real.doSomething();
    }

}
