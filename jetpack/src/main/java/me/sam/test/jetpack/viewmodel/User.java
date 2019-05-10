package me.sam.test.jetpack.viewmodel;

/**
 * desc :
 * date : 2019-05-07
 *
 * @author : dongSen
 */
public class User {
    public int age;
    public String name;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
