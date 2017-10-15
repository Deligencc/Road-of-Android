package sdut.ligong.mathhello;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewActivity extends Activity {
    private ListView listView;
    private Adapter adapter;
    List<Map<String, Object>> list;
    List<String> dataList = new ArrayList<String>();

    //定义一个HashMap，用来存放EditText的值，Key是position
    HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 10; i >= 1; i--) { //一共10组数据
            for (int j = 1; j <= i; j++) { //一共i
                map = new HashMap<String, Object>();
                map.put("title1", "" + i);
                map.put("title4", "" + j);
                map.put("title5", "" + (i - j));
                list.add(map);
            }
        }
        //加入数据
       // dataList = addData();

        //实例化ListView 并设置Adapter
        listView = (ListView)findViewById(R.id.listView);
        adapter = new Adapter();
        listView.setAdapter(adapter);
    }

    //往ListView 里面添加数据的方法
//    private List<String> addData(){
//        List<String> list = new ArrayList<String>();
//        for (int i = 0; i < 100; i++) {
//
//
//        }
//
//
//        return list;
//    }




    //自定义Adapter
    class Adapter extends BaseAdapter{

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Map<String, Object> str = list.get(position);
            convertView = LayoutInflater.from(getApplication()).inflate(R.layout.listview_item, null);

            final EditText editText = (EditText)convertView.findViewById(R.id.result_para2);
            TextView title = (TextView) convertView.findViewById(R.id.title_result);
            TextView para = (TextView) convertView.findViewById(R.id.result_para);
          //  editText.setText(str);

            //为editText设置TextChangedListener，每次改变的值设置到hashMap
            //我们要拿到里面的值根据position拿值
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void beforeTextChanged(CharSequence s, int start,
                                              int count,int after) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    //将editText中改变的值设置的HashMap中
                    hashMap.put(position, s.toString());
                }
            });
            title.setText((String) list.get(position).get("title1"));
            para.setText((String) list.get(position).get("title6"));

            //如果hashMap不为空，就设置的editText
            if(list.get(position) != null){
                editText.setText(hashMap.get(position));
            }


            return convertView;
        }

    }

}
