package me.sam.uitest.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import me.sam.uitest.R;

/**
 * desc :
 * date : 2019/4/19
 *
 * @author : dongSen
 */
public class ContactsActivity extends AppCompatActivity {

    static final String KEY_PHONE_NUMBER = "key_phone_number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        setResult(Activity.RESULT_OK, createResultData("123-345-6789"));
        finish();
    }

    @VisibleForTesting
    static Intent createResultData(String phoneNumber) {
        final Intent resultData = new Intent();
        resultData.putExtra(KEY_PHONE_NUMBER, phoneNumber);
        return resultData;
    }

}
