package demo.ligong.sdut.primaryschoolmathgame;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private Button okButton;

    private Button helpButton;

    private Button mode;

    public static boolean mOutOfOrder = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //1.拿到listview对象
        ListView lv = (ListView) this.findViewById(R.id.lv_main);

        helpButton = (Button) findViewById(R.id.help);

        mode = (Button) findViewById(R.id.mode);

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                TestActivity.hashMap.clear();
                startActivity(intent);
            }
        });
        mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Button but = new Button(MainActivity.this);
                but.setWidth(100);
                but.setHeight(20);
                but.setText("练习模式选择");
                but.setBackgroundResource(R.drawable.btn_selector_yellow);
                LinearLayout rl = (
                        LinearLayout
                        ) findViewById(R.id.mylayout);
                rl.addView(but);
                but.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                        builder.setItems(getResources().getStringArray(R.array.ItemArray), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                // TODO 自动生成的方法存根
                                System.out.println(arg1);
//                                if (arg1 == 0) {
//                                    AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
//                                    builder2.setTitle("小同学");
//                                    builder2.setMessage("你个小P孩子");
//                                    builder2.setPositiveButton("我就是", new DialogInterface.OnClickListener() {
//
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            // TODO 自动生成的方法存根
//                                            dialog.dismiss();
//
//                                        }
//                                    });
//                                    builder2.show();
//                                }
                                if(arg1 == 0){
                                    mOutOfOrder = true; //口算乱序
                                    Intent intent = new Intent(MainActivity.this, Main4Activity.class);
                                    startActivity(intent);
                                }
                                if(arg1 ==1){
                                    mOutOfOrder = true; //填空乱序
                                    Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                                    Main3Activity.hash3Map.clear();
                                    startActivity(intent);
                                }

                                if (arg1 == 2) {
                                    mOutOfOrder = false; //口算
                                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                                   startActivity(intent);
                                }
                                if (arg1 == 3) {
                                    mOutOfOrder = false; //填空
                                    Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                                    Main3Activity.hash3Map.clear();
                                   startActivity(intent);
                                }

                                if(arg1 == 4){
                                 //   mOutOfOrder = true;
                                    Intent intent = new Intent(MainActivity.this, Main4Activity.class);
                                    startActivity(intent);
                                }
                                if(arg1 == 5){
                                    //   mOutOfOrder = true;


                                }
                                but.setVisibility(View.GONE); //让对话框消失
                                arg0.dismiss();
                            }
                        });

                        builder.show();
                    }
                });



            }
        });

        //2.数据源
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("logo", R.drawable.da1);
        map.put("title", "10以内加法");
        map.put("version", "版本: 2.0.0");
        map.put("size", "难度: ☆");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.drawable.da1);
        map.put("title", "20以内加法");
        map.put("version", "版本: 2.0.0");
        map.put("size", "难度: ☆☆");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.drawable.da1);
        map.put("title", "100以内加法");
        map.put("version", "版本: 2.0.0");
        map.put("size", "难度: ☆☆☆");
        list.add(map);


        map = new HashMap<String, Object>();
        map.put("logo", R.drawable.duo2);
        map.put("title", "10以内减法");
        map.put("version", "版本: 2.0.0");
        map.put("size", "难度: ☆");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.drawable.duo2);
        map.put("title", "20以内减法");
        map.put("version", "版本: 2.0.0");
        map.put("size", "难度: ☆☆");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.drawable.duo2);
        map.put("title", "100以内减法");
        map.put("version", "版本: 2.0.0");
        map.put("size", "难度: ☆☆☆");
        list.add(map);


        map = new HashMap<String, Object>();
        map.put("logo", R.drawable.duo3);
        map.put("title", "10以内乘法");
        map.put("version", "版本: 2.0.0");
        map.put("size", "难度: ☆☆☆");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.drawable.duo3);
        map.put("title", "20以内乘法");
        map.put("version", "版本: 2.0.0");
        map.put("size", "难度: ☆☆☆☆");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.drawable.duo3);
        map.put("title", "100以内乘法");
        map.put("version", "版本: 2.0.0");
        map.put("size", "难度: ☆☆☆☆☆");
        list.add(map);


//        map = new HashMap<String, Object>();
//        map.put("logo", R.drawable.duo4);
//        map.put("title", "10以内除法");
//        map.put("version", "版本: 2.0.0");
//        map.put("size", "难度: ☆☆☆");
//        list.add(map);
//
//        map = new HashMap<String, Object>();
//        map.put("logo", R.drawable.duo4);
//        map.put("title", "20以内除法");
//        map.put("version", "版本: 2.0.0");
//        map.put("size", "难度: ☆☆☆☆");
//        list.add(map);
//
//        map = new HashMap<String, Object>();
//        map.put("logo", R.drawable.duo4);
//        map.put("title", "100以内除法");
//        map.put("version", "版本: 2.0.0");
//        map.put("size", "难度: ☆☆☆☆☆");
//        list.add(map);


//        map = new HashMap<String, Object>();
//        map.put("logo", R.drawable.ic_15);
//        map.put("title", "30以内加法");
//        map.put("version", "版本: 2.3.0");
//        map.put("size", "大小: 45.53M");
//        list.add(map);
//
//        map = new HashMap<String, Object>();
//        map.put("logo", R.drawable.ic_2);
//        map.put("title", "30以内减法");
//        map.put("version", "版本: 2.4.1");
//        map.put("size", "大小: 84.24M");
//        list.add(map);
//
//        map = new HashMap<String, Object>();
//        map.put("logo", R.drawable.ic_4);
//        map.put("title", "30以内乘法");
//        map.put("version", "版本: 6.2.0");
//        map.put("size", "大小: 11.74M");
//        list.add(map);
//
//        map = new HashMap<String, Object>();
//        map.put("logo", R.drawable.ic_15);
//        map.put("title", "30以内除法");
//        map.put("version", "版本: 2.3.0");
//        map.put("size", "大小: 45.53M");
//        list.add(map);
//
//
//        map = new HashMap<String, Object>();
//        map.put("logo", R.drawable.ic_15);
//        map.put("title", "50以内加法");
//        map.put("version", "版本: 2.3.0");
//        map.put("size", "大小: 45.53M");
//        list.add(map);
//
//        map = new HashMap<String, Object>();
//        map.put("logo", R.drawable.ic_2);
//        map.put("title", "50以内减法");
//        map.put("version", "版本: 2.4.1");
//        map.put("size", "大小: 84.24M");
//        list.add(map);
//
//        map = new HashMap<String, Object>();
//        map.put("logo", R.drawable.ic_4);
//        map.put("title", "50以内乘法");
//        map.put("version", "版本: 6.2.0");
//        map.put("size", "大小: 11.74M");
//        list.add(map);
//
//        map = new HashMap<String, Object>();
//        map.put("logo", R.drawable.ic_15);
//        map.put("title", "50以内除法");
//        map.put("version", "版本: 2.3.0");
//        map.put("size", "大小: 45.53M");
//        list.add(map);


        //3.设置适配器
//        SimpleAdapter adapter = new SimpleAdapter(
//                this,
//                list,
//                R.layout.item,
//                new String[]{"logo","title","version","size"},
//                new int[]{R.id.logo,R.id.title,R.id.version,R.id.size}
//        );

        MyAdapter adapter = new MyAdapter(this);
        adapter.setList(list);


        //4.关联适配器
        lv.setAdapter(adapter);

        //5
        lv.setOnItemClickListener(this);
        lv.setOnItemLongClickListener(this);


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(this,"点击"+position,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setClass(this, DetailActivity.class);

        //1.获得所点击行的数据(Map)
        HashMap<String, Object> itemMap
                = (HashMap<String, Object>) parent.getItemAtPosition(position);
        intent.putExtra("index", "" + position);
        intent.putExtra("title", "" + itemMap.get("title"));
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "长按" + position, Toast.LENGTH_SHORT).show();
        return true;
    }


}

