package demo.ligong.sdut.primaryschoolmathgame;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;


public class Main4Activity extends Activity implements LoadListView.ILoadListener {
    ArrayList<ApkEntity> apk_list = new ArrayList<ApkEntity>();
    ArrayList<ApkEntity> apk_list1 = new ArrayList<ApkEntity>();
    public static int count = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
       getData();
        showListView(apk_list);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(Main4Activity.this,"答案是"+apk_list1.get(position).getInfo(),Toast.LENGTH_SHORT).show();
            }
        });
    }



    Math4Adapter adapter;
    LoadListView listview;
    private void showListView(ArrayList<ApkEntity> apk_list) {
        if (adapter == null) {
            listview = (LoadListView) findViewById(R.id.listview);
            listview.setInterface(this);
            adapter = new Math4Adapter(this, apk_list);
            listview.setAdapter(adapter);
        } else {
            adapter.onDateChange(apk_list);
        }
    }

    private void getData() {
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <=i/2 ; j++) {
                ApkEntity entity = new ApkEntity();
                entity.setName(""+i);
                entity.setInfo(""+(i-j));
                entity.setDes(""+j);
                apk_list1.add(entity);
            }
        }
        Collections.shuffle(apk_list1);
        for (int i = 0; i < 6; i++) {
            apk_list.add(apk_list1.get(i));
        }
    }
    private void getLoadData() {
        if(count+6<=apk_list1.size()){
            for (int i = count; i < count+6; i++) {
                apk_list.add(apk_list1.get(i));
            }
        }else {
            Toast.makeText(Main4Activity.this,"你已经做完了今天的题，你真棒！",Toast.LENGTH_SHORT).show();
        }

        if(count+6<=apk_list1.size()){
            count+=6;
        }
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
//                //更新listview显示；
                showListView(apk_list);
//                //通知listview加载完毕
listview.loadComplete();
            }
        }, 1000);
    }
}
