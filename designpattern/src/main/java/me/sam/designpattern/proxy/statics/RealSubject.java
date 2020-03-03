package me.sam.designpattern.proxy.statics;

/**
 * desc :
 * date : 2020/3/3 10:00 PM
 *
 * @author : dongSen
 */
public class RealSubject implements Subject {

    private Subject mSubject;

    RealSubject(Subject subject) {
        this.mSubject = subject;
    }

    @Override
    public void doSomething() {
        mSubject.doSomething();
        System.out.println("This is from real.");
    }
}
