package demo.ligong.sdut.primaryschoolmathgame;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestActivity extends AppCompatActivity implements View.OnClickListener, LoadListView.ILoadListener {

 static    HashMap<Integer,String> hashMap = new HashMap<>();
  static   List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
   static Map<String, Object> map = new HashMap<String, Object>();
    static int score = 0;
    private Button mEnd;
    LinearLayout layout;
    private TextView mScore;
    LoadListView listview;
     Chronometer timer;
    TestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mEnd = (Button) findViewById(R.id.end);
        timer = (Chronometer) findViewById(R.id.timer);
        layout = (LinearLayout) findViewById(R.id.showMe);
        mScore = (TextView) findViewById(R.id.showMee);
         listview = (LoadListView) findViewById(R.id.test_show);
        //2.数据源

        getData();



        //showListView(list);



//        TestAdapter adapter = new TestAdapter(this);
//        adapter.setList(list);
//
//        listView.setAdapter(adapter);
        mEnd.setOnClickListener(this);
        timer.setBase(SystemClock.elapsedRealtime());//计时器清零
        int hour = (int) ((SystemClock.elapsedRealtime() - timer.getBase()) / 1000 / 60);
        timer.setFormat("0"+String.valueOf(hour)+":%s");
        timer.start();

    }

    @Override
    public void onClick(View v) {
        timer.stop();
     Intent intent = new Intent(TestActivity.this,SponsorMe.class);
       intent.putExtra("score",score);
        intent.putExtra("time",timer.getText().toString());
     startActivity(intent);

    }
    public void btnClick(View view) {
        timer.setBase(SystemClock.elapsedRealtime());//计时器清零
        int hour = (int) ((SystemClock.elapsedRealtime() - timer.getBase()) / 1000 / 60);
        timer.setFormat("0"+String.valueOf(hour)+":%s");
        timer.start();
    }
    public void stopClick(View view) {
     timer.stop();
    }


    private void showListView( List<Map<String, Object>> list) {
        if (adapter == null) {
            TestAdapter adapter = new TestAdapter(this);
        adapter.setList(list);
            listview.setInterface(this);
            listview.setAdapter(adapter);
//            adapter.setList(apk_list);
//            listview.setAdapter(adapter);
        } else {
            adapter.onDateChange(list);
        }
    }
    private void getData() {
         list = new ArrayList<Map<String, Object>>();
       map = new HashMap<String, Object>();
        for (int i = 10; i >= 1; i--) { //一共10组数据
            for (int j = 1; j <= i; j++) { //一共i
                map = new HashMap<String, Object>();
                map.put("title1", "" + i);
                map.put("title4", "" + j);
                map.put("title5", "");
                list.add(map);
            }
        }

        Collections.shuffle(list);
//        for (int i = 0; i < 10; i++) {
//            ApkEntity entity = new ApkEntity();
//            entity.setName("测试程序");
//            entity.setInfo("50w用户");
//            entity.setDes("这是一个神奇的应用！");
//            apk_list.add(entity);
//        }
    }

    private void getLoadData() {
        for (int i = 10; i >= 1; i--) { //一共10组数据
            for (int j = 1; j <= i; j++) { //一共i
                map = new HashMap<String, Object>();
                map.put("title1", "" + i);
                map.put("title4", "" + j);
                map.put("title5", "");
                list.add(map);
            }
        }
        Collections.shuffle(list);
    }
    @Override
    public void onLoad() {
        // TODO Auto-generated method stub
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                //获取更多数据
                getLoadData();
                //更新listview显示；
                showListView(list);
                //通知listview加载完毕
                listview.loadComplete();
            }
        }, 2000);
    }

}
