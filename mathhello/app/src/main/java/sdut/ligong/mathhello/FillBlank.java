package sdut.ligong.mathhello;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FillBlank extends AppCompatActivity {
    ListView lv;
    Button btnLeft, btnRight;
    List<Map<String,String>> list;
    Map<String,String> map;

    View.OnClickListener cl;

    MoreAdapter ma;

    String data[] =new String[55];
    String dataLChild[] =new String[55];;
    String dataRChild[] =new String[55];;

    //用于显示每列5个Item项。
    int VIEW_COUNT = 5;

    //用于显示页号的索引
    int index = 0;
    /** Called when the activity is first created. */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showfillblank);
        initView();

        //设置ListView的Adapter
        ma = new MoreAdapter(this);
        lv.setAdapter(ma);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(),"答案是"+list.get(position).get("rchild"),Toast.LENGTH_SHORT).show();
            }
        });


        cl = new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                switch(v.getId()){
                    case R.id.btnLeft:
                        leftView();
                        break;

                    case R.id.btnRight:
                        rightView();
                        break;
                }
            }

        };
        //添加2个Button的监听事件。
        btnLeft.setOnClickListener(cl);
        btnRight.setOnClickListener(cl);

        //检查2个Button是否是可用的
        checkButton();

    }

    public void initView(){
        lv = (ListView)findViewById(R.id.list);

        btnLeft = (Button)findViewById(R.id.btnLeft);
        btnRight = (Button)findViewById(R.id.btnRight);
        int count = 0;
        list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <=i ; j++) {
               data[count] = i+"";
                dataLChild[count] = (i-j)+"";
               dataRChild[count] = j+"";

                map = new HashMap<String, String>();
                map.put("parent",data[count]);
                map.put("lchild",dataLChild[count]);
                map.put("rchild",dataRChild[count]);
                list.add(map);
                count++;
            }
        }
        Collections.shuffle(list);
        Log.d("count", String.valueOf(count));

    }

    //点击左边的Button，表示向前翻页，索引值要减1.
    public void leftView(){
        index--;

        //刷新ListView里面的数值。
        ma.notifyDataSetChanged();

        //检查Button是否可用。
        checkButton();
    }
    //点击右边的Button，表示向后翻页，索引值要加1.
    public void rightView(){
        ++index;

        //刷新ListView里面的数值。
        ma.notifyDataSetChanged();

        //检查Button是否可用。
        checkButton();
    }

    public void checkButton(){
        //索引值小于等于0，表示不能向前翻页了，以经到了第一页了。         //将向前翻页的按钮设为不可用。
        if(index <=0){
            btnLeft.setEnabled(false);
        }
        //值的长度减去前几页的长度，剩下的就是这一页的长度，如果这一页的长度比View_Count小，表示这是最后的一页了，后面在没有了。
        //将向后翻页的按钮设为不可用。
        else if(data.length - index*VIEW_COUNT <= VIEW_COUNT){
            btnRight.setEnabled(false);
        }

        //否则将2个按钮都设为可用的。
        else {
            btnLeft.setEnabled(true);
            btnRight.setEnabled(true);
        }

    }

    //ListView的Adapter，这个是关键的导致可以分页的根本原因。
    public class MoreAdapter extends BaseAdapter {
        Activity activity;
        LayoutInflater inflater;
        public MoreAdapter(Activity a){
            inflater = LayoutInflater.from(a);
        }


        //设置每一页的长度，默认的是View_Count的值。
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            //return data.length
            //ori表示到目前为止的前几页的总共的个数。
            int ori = VIEW_COUNT * index;

            //值的总个数-前几页的个数就是这一页要显示的个数，如果比默认的值小，说明这是最后一页，只需显示这么多就可以了
            if(data.length - ori < VIEW_COUNT ){
                return data.length - ori;
            }
            //如果比默认的值还要大，说明一页显示不完，还要用换一页显示，这一页用默认的值显示满就可以了。
            else {
                return VIEW_COUNT;
            }

        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            //return addTestView(position);

//            TextView tv = new TextView(activity);
//            tv.setGravity(Gravity.CENTER);
            View view = inflater.inflate(R.layout.item,null);

            TextView parent1 = (TextView) view.findViewById(R.id.parent1);
           // TextView parent2 = (TextView) view.findViewById(R.id.parent2);
            TextView lchild1 = (TextView) view.findViewById(R.id.lchild1);
//            TextView lchild2 = (TextView) view.findViewById(R.id.lchild2);
//            TextView rchild2 = (TextView) view.findViewById(R.id.rchild2);
            TextView rchild1 = (TextView) view.findViewById(R.id.rchild1);

           // parent1.setText(data[position+index*VIEW_COUNT]);
            parent1.setText(list.get(position+index*VIEW_COUNT).get("parent"));
//            parent2.setText(data[position+index*VIEW_COUNT]);
        //    lchild1.setText(dataLChild[position+index*VIEW_COUNT]);
            lchild1.setText(list.get(position+index*VIEW_COUNT).get("lchild"));
           // rchild1.setText(dataRChild[position+index*VIEW_COUNT]);
            rchild1.setText(list.get(position+index*VIEW_COUNT).get("rchild"));
//            lchild2.setText(dataLChild[position+index*VIEW_COUNT]);
//            rchild2.setText(dataRChild[position+index*VIEW_COUNT]);
            //TextView要显示的是当前的位置+前几页已经显示的位置个数的对应的位置上的值。
            //tv.setText();
            return view;

        }
    }
}