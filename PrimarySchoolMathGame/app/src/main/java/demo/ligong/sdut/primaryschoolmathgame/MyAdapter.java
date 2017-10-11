package demo.ligong.sdut.primaryschoolmathgame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

/**
 * Created by Lenovo on 2017/9/26.
 */

public class MyAdapter extends BaseAdapter {

    /** 数据集合 */
    List<Map<String,Object>> list;
    /** 反射器 */
    LayoutInflater inflater;
    /**
     * 构造器
     * @param context 上下文
     */
    public MyAdapter(Context context){
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
        View view = inflater.inflate(R.layout.item,null);

        ImageView logo = (ImageView) view.findViewById(R.id.logo);
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView version = (TextView) view.findViewById(R.id.version);
        TextView size = (TextView) view.findViewById(R.id.size);

        logo.setImageResource((Integer) list.get(position).get("logo"));
        title.setText((String) list.get(position).get("title"));
        version.setText((String) list.get(position).get("version"));
        size.setText((String) list.get(position).get("size"));

        Button btn = (Button) view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.i("spl", "点击");
             //   Toast.makeText(MyAdapter.this,"你删除了第"+position+"列表",Toast.LENGTH_SHORT).show();
                //list.remove(position);
                Toast.makeText(parent.getContext(),"我舍不得你，不要删除我好吗？",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}

