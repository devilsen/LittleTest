package me.sam.practice.view;

/**
 * desc :
 * date : 2019/3/27
 *
 * @author : dongSen
 */
public class Test {

    public static void main(String[] args) {
        testCeil();
    }


    private static void testCeil() {
        for (int i = 0; i < 10; i++) {
            double a = Math.random();
            double b = Math.ceil(a * 1000);
            double c = Math.pow(-1, b) * 20 * Math.random();

            System.out.println(a + "  " + b + "  " + c);
        }
    }
}
