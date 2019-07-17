package me.sam.test.jetpack.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * desc :
 * date : 2019-05-07
 *
 * @author : dongSen
 */
public class UserModel extends ViewModel {

    public final MutableLiveData<User> mUserLiveData = new MutableLiveData<>();

    public UserModel() {
        //模拟从网络加载用户信息
        mUserLiveData.postValue(new User(1, "name1"));
    }

    //模拟 进行一些数据骚操作
    public void doSomething() {
        User user = mUserLiveData.getValue();
        if (user != null) {
            user.age = 15;
            user.name = "name15";
            mUserLiveData.setValue(user);
        }
    }

}
