package me.sam.practice.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * desc : 关于反射
 * date : 2019-04-29
 *
 * @author : dongSen
 * https://juejin.im/post/598ea9116fb9a03c335a99a4
 */
public class Test {

    public static void main(String[] args) {
        Class<?> c = String.class;
        try {
            Constructor<?> constructor = c.getConstructor(String.class);
            Object instance = constructor.newInstance("123");
            System.out.println(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Test test = new Test();
        test.testReflectMethod();
        test.testReflectFiled();
        test.invokeMethod();
    }

    private void testReflectMethod() {
        try {
            Class<?> c = MethodClass.class;
            Method[] methods = c.getMethods();
            Method[] declaredMethods = c.getDeclaredMethods();
            Method addMethod = c.getMethod("add", int.class, int.class);

            System.out.println("methods:");
            for (Method method : methods) {
                System.out.println(method);
            }

            System.out.println("declaredMethod:");
            for (Method declaredMethod : declaredMethods) {
                System.out.println(declaredMethod);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void testReflectFiled() {
        try {
            Class<?> c = MethodClass.class;
            Object object = c.newInstance();
            Field[] methods = c.getFields();
            Field[] declaredMethods = c.getDeclaredFields();

            System.out.println("fields:");
            for (Field method : methods) {
                System.out.println(method);
            }

            System.out.println("declaredFields:");
            for (Field declaredMethod : declaredMethods) {
                System.out.println(declaredMethod);
            }

            Field d1 = c.getDeclaredField("d");
            d1.setAccessible(true);
            d1.set(object, 5);
            System.out.println("setField: " + object);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void invokeMethod() {
        try {
            Class c = MethodClass.class;
            Object instance = c.newInstance();
            Method addMethod = c.getMethod("add", int.class, int.class);
            Object result = addMethod.invoke(instance, 1, 4);
            System.out.println("result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
