package me.sam.designpattern.proxy.statics;

/**
 * desc :
 * date : 2020/3/3 10:01 PM
 *
 * @author : dongSen
 */
public class ProxySubject implements Subject {

    @Override
    public void doSomething() {
        System.out.println("This is from proxy");
    }
}
