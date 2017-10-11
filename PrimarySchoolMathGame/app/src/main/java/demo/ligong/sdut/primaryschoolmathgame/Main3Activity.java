package demo.ligong.sdut.primaryschoolmathgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main3Activity extends Activity implements AdapterView.OnItemClickListener {

    private int currentPosition;
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    Map<String, Object> map = new HashMap<String, Object>();
 static    HashMap<Integer,String> hash3Map = new HashMap<>();
    Math2Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        HashMap<Integer,String> hash3Map = new HashMap<>();
        //1.拿到listview对象
        ListView lv = (ListView) this.findViewById(R.id.lv_item3);

//        //2.数据源
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 10; i >= 1; i--) { //一共10组数据
            for (int j = 1; j <= i/2; j++) { //一共i
                map = new HashMap<String, Object>();
                    map.put("title1", "" + i);
                    map.put("title4", "" + j);
                    map.put("title5", "");
                list.add(map);
            }
        }
        if(MainActivity.mOutOfOrder){
            Collections.shuffle(list);
        }


       adapter = new Math2Adapter(this);
       adapter.setList(list);
//
//
        //4.关联适配器
       lv.setAdapter(adapter);
//
//        //5
  //     lv.setOnItemClickListener(this);
//        lv.setOnItemLongClickListener(this);


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(this,"点击"+position,Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent();
//        intent.setClass(this, DetailActivity.class);
        currentPosition= position; //获取单击ListView 中tem的位置


        //1.获得所点击行的数据(Map)
        HashMap<String, Object> itemMap
                = (HashMap<String, Object>) parent.getItemAtPosition(position);
//        TextView textView = (TextView) itemMap.get("title4");
//        textView.setVisibility(View.VISIBLE);
        Toast.makeText(this,"答案是 "+itemMap.get("title5").toString(),Toast.LENGTH_SHORT).show();

//更新ListView
//        Map<String, Object>map=list.get(currentPosition);
//        itemMap.put("title",textView);//将新的文件名添加到Map以替换旧文件名
//        list.set(currentPosition, map);//替换listItems中原来的map
//        adapter.notifyDataSetChanged();//通知SimpleAdapter数据改变

//        intent.putExtra("index", "" + position);
//        intent.putExtra("title", "" + itemMap.get("title"));
       // startActivity(intent);
    }


}
