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

public class Main2Activity extends Activity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private int currentPosition;
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    Map<String, Object> map = new HashMap<String, Object>();
    MathAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //1.拿到listview对象
        ListView lv = (ListView) this.findViewById(R.id.lv_item);

//        //2.数据源
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 10; i >= 1; i--) { //一共10组数据
            for (int j = 1; j <= i/2; j++) { //一共i
                map = new HashMap<String, Object>();
                    map.put("title1", "" + i);
                    map.put("title4", "" + j);
                    map.put("title5", "" + (i - j));
                list.add(map);
            }
        }
        if(MainActivity.mOutOfOrder){
            Collections.shuffle(list);
        }


//
//
//
//        //3.设置适配器
////        SimpleAdapter adapter = new SimpleAdapter(
////                this,
////                list,
////                R.layout.item,
////                new String[]{"logo","title","version","size"},
////                new int[]{R.id.logo,R.id.title,R.id.version,R.id.size}
////        );
//
         adapter = new MathAdapter(this);
        adapter.setList(list);
//
//
        //4.关联适配器
        lv.setAdapter(adapter);
//
//        //5
       lv.setOnItemClickListener(this);
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

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "长按" + position, Toast.LENGTH_SHORT).show();
        return true;
    }
}
