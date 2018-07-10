package com.test.devilsen.test.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * desc :
 * date : 2018/6/26
 *
 * @author : dongSen
 */
public class TestBean implements Parcelable{

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.age);
    }

    public TestBean() {
    }

    protected TestBean(Parcel in) {
        this.name = in.readString();
        this.age = in.readInt();
    }

    public static final Creator<TestBean> CREATOR = new Creator<TestBean>() {
        @Override
        public TestBean createFromParcel(Parcel source) {
            return new TestBean(source);
        }

        @Override
        public TestBean[] newArray(int size) {
            return new TestBean[size];
        }
    };
}
