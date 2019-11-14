package com.test.devilsen.test;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.test.devilsen.test.aidl.client.ClientActivity;
import com.test.devilsen.test.alarm.AlarmActivity;
import com.test.devilsen.test.chart.ChartTestActivity;
import com.test.devilsen.test.handlertest.HandlerTestActivity;
import com.test.devilsen.test.managertest.WindowManagerTestActivity;
import com.test.devilsen.test.notification.ProgressActivity;
import com.test.devilsen.test.parcelable.ParcelableActivity;
import com.test.devilsen.test.parcelable.TestBean;
import com.test.devilsen.test.screen.activity.FullScreenActivity;
import com.wuba.view.HalfCircle.TestActivity;
import com.wuba.view.applist.RecyclerTestActivity;
import com.wuba.view.badgeicon.BadgeTestActivity;
import com.wuba.view.banner.BannerTestActivity;
import com.wuba.view.bottomsheet.BottomSheetActivity;
import com.wuba.view.doubanAnim.AnimActivity;
import com.wuba.view.image.HeartTestActivity;
import com.wuba.view.image.ImageTestActivity;
import com.wuba.view.imkeyboard.ImKeyboardActivity;
import com.wuba.view.loading.LoadingTestActivity;
import com.wuba.view.midautumn.PropagandaActivity;
import com.wuba.view.recyclerview.ItemAnimationTestActivity;
import com.wuba.view.scrollnumber.ScrollActivity;
import com.wuba.view.textview.TextViewTestActivity;
import com.wuba.view.viewpager.ViewPagerActivity;
import com.wuba.view.viewtest.ViewTestActivity;
import com.wuba.view.watermark.WaterMarkActivity;

import me.sam.practice.view.ViewMainActivity;

/**
 * ii.                                         ;9ABH,
 * SA391,                                    .r9GG35&G
 * &#ii13Gh;                               i3X31i;:,rB1
 * iMs,:,i5895,                         .5G91:,:;:s1:8A
 * 33::::,,;5G5,                     ,58Si,,:::,sHX;iH1
 * Sr.,:;rs13BBX35hh11511h5Shhh5S3GAXS:.,,::,,1AG3i,GG
 * .G51S511sr;;iiiishS8G89Shsrrsh59S;.,,,,,..5A85Si,h8
 * :SB9s:,............................,,,.,,,SASh53h,1G.
 * .r18S;..,,,,,,,,,,,,,,,,,,,,,,,,,,,,,....,,.1H315199,rX,
 * ;S89s,..,,,,,,,,,,,,,,,,,,,,,,,....,,.......,,,;r1ShS8,;Xi
 * i55s:.........,,,,,,,,,,,,,,,,.,,,......,.....,,....r9&5.:X1
 * 59;.....,.     .,,,,,,,,,,,...        .............,..:1;.:&s
 * s8,..;53S5S3s.   .,,,,,,,.,..      i15S5h1:.........,,,..,,:99
 * 93.:39s:rSGB@A;  ..,,,,.....    .SG3hhh9G&BGi..,,,,,,,,,,,,.,83
 * G5.G8  9#@@@@@X. .,,,,,,.....  iA9,.S&B###@@Mr...,,,,,,,,..,.;Xh
 * Gs.X8 S@@@@@@@B:..,,,,,,,,,,. rA1 ,A@@@@@@@@@H:........,,,,,,.iX:
 * ;9. ,8A#@@@@@@#5,.,,,,,,,,,... 9A. 8@@@@@@@@@@M;    ....,,,,,,,,S8
 * X3    iS8XAHH8s.,,,,,,,,,,...,..58hH@@@@@@@@@Hs       ...,,,,,,,:Gs
 * r8,        ,,,...,,,,,,,,,,.....  ,h8XABMMHX3r.          .,,,,,,,.rX:
 * :9, .    .:,..,:;;;::,.,,,,,..          .,,.               ..,,,,,,.59
 * .Si      ,:.i8HBMMMMMB&5,....                    .            .,,,,,.sMr
 * SS       :: h@@@@@@@@@@#; .                     ...  .         ..,,,,iM5
 * 91  .    ;:.,1&@@@@@@MXs.                            .          .,,:,:&S
 * hS ....  .:;,,,i3MMS1;..,..... .  .     ...                     ..,:,.99
 * ,8; ..... .,:,..,8Ms:;,,,...                                     .,::.83
 * s&: ....  .sS553B@@HX3s;,.    .,;13h.                            .:::&1
 * SXr  .  ...;s3G99XA&X88Shss11155hi.                             ,;:h&,
 * iH8:  . ..   ,;iiii;,::,,,,,.                                 .;irHA
 * ,8X5;   .     .......                                       ,;iihS8Gi
 * 1831,                                                 .,;irrrrrs&@
 * ;5A8r.                                            .:;iiiiirrss1H
 * :X@H3s.......                                .,:;iii;iiiiirsrh
 * r#h:;,...,,.. .,,:;;;;;:::,...              .:;;;;;;iiiirrss1
 * ,M8 ..,....,.....,,::::::,,...         .     .,;;;iiiiiirss11h
 * 8B;.,,,,,,,.,.....          .           ..   .:;;;;iirrsss111h
 * i@5,:::,,,,,,,,.... .                   . .:::;;;;;irrrss111111
 * 9Bi,:,,,,......                        ..r91;;;;;iirrsss1ss1111
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView test1;
    private TextView test2;
    private boolean flag;
    private Button testAnim;
    private Button pieChartTest;
    private Button horizontalChartTest;
    private Button chartTest;
    private Button vectorTest;
    private Button halfBtn;
    private Button viewPagerBtn;
    private Button scrollNumberBtn;
    private Button alarmBtn;
    private Button midAutumnBtn;
    private Button keyboardBtn;
    private Button badgeBtn;
    private Button editBtn;
    private Button dragBtn;
    private Button heartBtn;
    private Button notificationBtn;
    private Button viewTestBtn;
    private Button handlerBtn;
    private Button bottomSheetBtn;
    private Button waterMarkBtn;
    private Button parcelableTestBtn;
    private Button imInputTestBtn;
    private Button windowManagerTestBtn;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        test1 = (TextView) findViewById(R.id.test1);
        test2 = (TextView) findViewById(R.id.test2);
        testAnim = (Button) findViewById(R.id.test_anim);
        pieChartTest = (Button) findViewById(R.id.pie_chart_test);
        chartTest = (Button) findViewById(R.id.chart_test);
        horizontalChartTest = (Button) findViewById(R.id.horizontal_bar_chart_test);
        vectorTest = (Button) findViewById(R.id.vector_test);
        halfBtn = (Button) findViewById(R.id.half_circle_test);
        viewPagerBtn = (Button) findViewById(R.id.view_pager_test);
        scrollNumberBtn = (Button) findViewById(R.id.scroll_number_test);
        alarmBtn = (Button) findViewById(R.id.alarm_test);
        midAutumnBtn = (Button) findViewById(R.id.mid_autumn);
        keyboardBtn = (Button) findViewById(R.id.keyboard_test);
        badgeBtn = (Button) findViewById(R.id.badge_test);
        editBtn = (Button) findViewById(R.id.btn_person_edit);
        dragBtn = (Button) findViewById(R.id.btn_drag_recycler);
        heartBtn = (Button) findViewById(R.id.btn_heart_test);
        notificationBtn = (Button) findViewById(R.id.btn_progress_notification_test);
        viewTestBtn = (Button) findViewById(R.id.btn_view_test_test);
        handlerBtn = (Button) findViewById(R.id.btn_handler_test);
        handlerBtn = (Button) findViewById(R.id.btn_handler_test);
        bottomSheetBtn = findViewById(R.id.btn_bottom_sheet);
        waterMarkBtn = findViewById(R.id.btn_watermark);
        parcelableTestBtn = findViewById(R.id.btn_parcelable_test);
        imInputTestBtn = findViewById(R.id.btn_im_input_test);
        windowManagerTestBtn = findViewById(R.id.btn_window_manager_test);

        test1.setOnClickListener(this);
        test2.setOnClickListener(this);
        testAnim.setOnClickListener(this);
        pieChartTest.setOnClickListener(this);
        horizontalChartTest.setOnClickListener(this);
        chartTest.setOnClickListener(this);
        vectorTest.setOnClickListener(this);
        halfBtn.setOnClickListener(this);
        viewPagerBtn.setOnClickListener(this);
        scrollNumberBtn.setOnClickListener(this);
        alarmBtn.setOnClickListener(this);
        midAutumnBtn.setOnClickListener(this);
        keyboardBtn.setOnClickListener(this);
        badgeBtn.setOnClickListener(this);
        editBtn.setOnClickListener(this);
        dragBtn.setOnClickListener(this);
        heartBtn.setOnClickListener(this);
        notificationBtn.setOnClickListener(this);
        viewTestBtn.setOnClickListener(this);
        handlerBtn.setOnClickListener(this);
        bottomSheetBtn.setOnClickListener(this);
        waterMarkBtn.setOnClickListener(this);
        parcelableTestBtn.setOnClickListener(this);
        imInputTestBtn.setOnClickListener(this);
        windowManagerTestBtn.setOnClickListener(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test1:
//                progeress.setLinearProgress(true);
                testTost();
                break;
            case R.id.test2:
                break;
            case R.id.test_anim:
                Intent in = new Intent(this, AnimActivity.class);
                startActivity(in);
                break;
            case R.id.pie_chart_test:
//                Intent intent = new Intent(this, PieChatActivity.class);
//                startActivity(intent);
                break;
            case R.id.horizontal_bar_chart_test:
//                Intent intent1 = new Intent(this, HorizLineChatActivity.class);
//                startActivity(intent1);
                break;
            case R.id.chart_test:
                Intent intent2 = new Intent(this, ChartTestActivity.class);
                startActivity(intent2);
                break;
            case R.id.vector_test:
                Intent intent3 = new Intent(this, VectorTestActivity.class);
                startActivity(intent3);
                break;
            case R.id.half_circle_test:
                Intent intent4 = new Intent(this, TestActivity.class);
                startActivity(intent4);
                break;

            case R.id.view_pager_test:
                Intent intent5 = new Intent(this, ViewPagerActivity.class);
                startActivity(intent5);
                break;

            case R.id.scroll_number_test:
                Intent intent6 = new Intent(this, ScrollActivity.class);
                startActivity(intent6);
                break;

            case R.id.alarm_test:
                Intent intent7 = new Intent(this, AlarmActivity.class);
                startActivity(intent7);
                break;

            case R.id.mid_autumn:
                Intent intent8 = new Intent(this, PropagandaActivity.class);
                startActivity(intent8);
                break;

            case R.id.keyboard_test:
                Intent intent9 = new Intent(this, KeyboardTestActivity.class);
                startActivity(intent9);
                break;

            case R.id.badge_test:
                Intent intent10 = new Intent(this, BadgeTestActivity.class);
                startActivity(intent10);
                break;

            case R.id.btn_person_edit:
                Intent intent11 = new Intent(this, com.wuba.view.personedit.TestActivity.class);
                startActivity(intent11);
                break;

            case R.id.btn_drag_recycler:
                Intent intent12 = new Intent(this, RecyclerTestActivity.class);
                startActivity(intent12);
                break;

            case R.id.btn_heart_test:
                Intent intent13 = new Intent(this, HeartTestActivity.class);
                startActivity(intent13, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;

            case R.id.btn_progress_notification_test:
                Intent intent14 = new Intent(this, ProgressActivity.class);
                startActivity(intent14, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;

            case R.id.btn_view_test_test:
                Intent intent15 = new Intent(this, ViewTestActivity.class);
                startActivity(intent15);
                break;

            case R.id.btn_handler_test:
                Intent intent16 = new Intent(this, HandlerTestActivity.class);
                startActivity(intent16);
                break;

            case R.id.btn_bottom_sheet:
                Intent intent17 = new Intent(this, BottomSheetActivity.class);
                startActivity(intent17);
                break;

            case R.id.btn_watermark:
                Intent intent18 = new Intent(this, WaterMarkActivity.class);
                startActivity(intent18);
                break;

            case R.id.btn_parcelable_test:
                Intent intent19 = new Intent(this, ParcelableActivity.class);
                TestBean bean = new TestBean();
                bean.setName("aaa");
                bean.setAge(12);
                Log.e("data", bean.toString());
                intent19.putExtra("data", bean);
                startActivity(intent19);
                break;

            case R.id.btn_im_input_test:
                Intent intent20 = new Intent(this, ImKeyboardActivity.class);
                startActivity(intent20);
                break;

            case R.id.btn_window_manager_test:
                Intent intent21 = new Intent(this, WindowManagerTestActivity.class);
                startActivity(intent21);
                break;
        }
    }

    String msg = null;
    Context context;

    private void testTost() {
        context = this.getApplicationContext();
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public void itemAnimTest(View view) {
        Intent intent = new Intent(this, ItemAnimationTestActivity.class);
        startActivity(intent);
    }

    public void itemAidlTest(View view) {
        Intent intent = new Intent(this, ClientActivity.class);
        startActivity(intent);
    }

    public void openViewPractice(View view) {
        Intent intent = new Intent(this, ViewMainActivity.class);
        startActivity(intent);
    }

    public void fullScreen(View view) {
        Intent intent = new Intent(this, FullScreenActivity.class);
        startActivity(intent);
    }

    public void loadingTest(View view) {
        Intent intent = new Intent(this, LoadingTestActivity.class);
        startActivity(intent);
    }

    public void bannerText(View view) {
        Intent intent = new Intent(this, BannerTestActivity.class);
        startActivity(intent);
    }

    public void ImageTest(View view) {
        Intent intent = new Intent(this, ImageTestActivity.class);
        startActivity(intent);
    }

    public void TextViewTest(View view) {
        Intent intent = new Intent(this, TextViewTestActivity.class);
        startActivity(intent);
    }
}
