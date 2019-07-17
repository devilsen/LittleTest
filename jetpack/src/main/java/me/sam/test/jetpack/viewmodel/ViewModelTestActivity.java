package me.sam.test.jetpack.viewmodel;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import me.sam.test.jetpack.R;

/**
 * desc :
 * date : 2019-05-07
 *
 * @author : dongSen
 * <p>
 * https://mp.weixin.qq.com/s/3Q3aJ0mYUWU5bo8rZOhDcA
 */
public class ViewModelTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model_test);

        final TextView contentTextView = findViewById(R.id.text_view_content);

        final UserModel userModel = ViewModelProviders.of(this).get(UserModel.class);
        userModel.mUserLiveData.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                contentTextView.setText(user.age);
            }
        });

        findViewById(R.id.button_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userModel.doSomething();
            }
        });
    }


}
