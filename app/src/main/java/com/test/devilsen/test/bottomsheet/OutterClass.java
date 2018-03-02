package com.test.devilsen.test.bottomsheet;

/**
 * desc :
 * date : 2017/12/14
 *
 * @author : dongSen
 */
public class OutterClass {

    private String name;

    public class Inner {
        public void list() {
            System.out.println("outter name is " + name);
        }
    }

}
