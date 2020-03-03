package me.sam.designpattern.proxy.dynamic;

/**
 * desc :
 * date : 2020/3/3 10:00 PM
 *
 * @author : dongSen
 */
public class RealSubject implements Subject {

    @Override
    public void doSomething() {
        System.out.println("This is from real. I will do something");
    }

    @Override
    public void doAnything() {
        System.out.println("This is from real. I will do my best");
    }
}
