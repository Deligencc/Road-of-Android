package demo.ligong.sdut.primaryschoolmathgame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by Lenovo on 2017/9/28.
 */

public class MathAdapter extends BaseAdapter {
    /** 数据集合 */
    List<Map<String,Object>> list;
    /** 反射器 */
    LayoutInflater inflater;
    /**
     * 构造器
     * @param context 上下文
     */
    public MathAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }
    /**
     * 传入数据集合
     * @param list
     */
    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view = inflater.inflate(R.layout.item9,null);

        TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9;
        textView1 = (TextView) view.findViewById(R.id.view01);
     //   textView2 = (TextView) view.findViewById(R.id.view02);
      //  textView3 = (TextView) view.findViewById(R.id.view03);
        textView4 = (TextView) view.findViewById(R.id.view11);
        textView5 = (TextView) view.findViewById(R.id.view12);
       // textView6 = (TextView) view.findViewById(R.id.view13);
//        textView8 = (TextView) view.findViewById(R.id.view15);
        //textView9 = (TextView) view.findViewById(R.id.view16);



//        ImageView logo = (ImageView) view.findViewById(R.id.logo);
//        TextView title = (TextView) view.findViewById(R.id.title);
//        TextView version = (TextView) view.findViewById(R.id.version);
//        TextView size = (TextView) view.findViewById(R.id.size);


        //logo.setImageResource((Integer) list.get(position).get("logo"));
        textView1.setText((String)(list.get(position).get("title1")));
       // textView2.setText((String)(list.get(position).get("title2")));
       // textView3.setText((String)(list.get(position).get("title3")));
        textView4.setText((String)(list.get(position).get("title4")));
        textView5.setText((String)(list.get(position).get("title5")));
       // textView6.setText((String)(list.get(position).get("title6")));
       // textView7.setText((String)(list.get(position).get("title7")));
      //  textView8.setText((String)(list.get(position).get("title8")));
       // textView9.setText((String)(list.get(position).get("title9")));

//        title.setText((String) list.get(position).get("title"));
//        version.setText((String) list.get(position).get("version"));
//        size.setText((String) list.get(position).get("size"));

//        Button btn = (Button) view.findViewById(R.id.btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Log.i("spl", "点击");
//                //   Toast.makeText(MyAdapter.this,"你删除了第"+position+"列表",Toast.LENGTH_SHORT).show();
//                //list.remove(position);
//                Toast.makeText(parent.getContext(),"我舍不得你，不要删除我好吗？",Toast.LENGTH_SHORT).show();
//            }
//        });

        return view;
    }
}
