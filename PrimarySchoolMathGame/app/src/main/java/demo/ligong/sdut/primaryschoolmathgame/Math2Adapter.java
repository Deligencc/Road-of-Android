package demo.ligong.sdut.primaryschoolmathgame;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import static demo.ligong.sdut.primaryschoolmathgame.DetailActivity.delay;

/**
 * Created by Lenovo on 2017/9/28.
 */

public class Math2Adapter extends BaseAdapter {
    /** 数据集合 */
    List<Map<String,Object>> list;
    /** 反射器 */
    LayoutInflater inflater;
    /**
     * 构造器
     * @param context 上下文
     */
    public Math2Adapter(Context context){
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
        View view = inflater.inflate(R.layout.item10,null);

        final TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9;
        textView1 = (TextView) view.findViewById(R.id.sum);

        textView4 = (TextView) view.findViewById(R.id.sum1);
        textView5 = (TextView) view.findViewById(R.id.sum2);

        final EditText editText = (EditText) view.findViewById(R.id.sum2);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Main3Activity.hash3Map.put(position,s.toString());
                delay = 3000L;
                try {
                    String content = null;
                    content = editText.getText().toString();
                    String content2 = textView1.getText().toString();
                    String content3 = textView4.getText().toString();

                    delay = Long.parseLong(content);
                    if (content.equals(null)) {
                        content = -100 + "";
                    }
                    int mResult = Integer.parseInt(content2)-Integer.parseInt(content3);
                    int mResult1 = Integer.parseInt(content);

                        if (mResult == mResult1) {
                            textView1.setTextColor(Color.rgb(27,129,62));
                            textView4.setTextColor(Color.rgb(27,129,62));
                            textView5.setTextColor(Color.rgb(27,129,62));
                        } else {
                            textView1.setTextColor(Color.RED);
                            textView4.setTextColor(Color.RED);
                            textView5.setTextColor(Color.RED);
                        }

                }catch (NumberFormatException e){
                    e.printStackTrace();
                }
                delay = delay;
            }
        });
        if(Main3Activity.hash3Map.get(position)!=null){
            textView5.setTextColor(Color.BLACK);
            textView5.setText(Main3Activity.hash3Map.get(position).toString());
        }
        textView1.setTextColor(Color.BLACK);
        textView1.setText((String)(list.get(position).get("title1")));
        textView4.setTextColor(Color.BLACK);
        textView4.setText((String)(list.get(position).get("title4")));
        textView5.setTextColor(Color.BLACK);


        return view;
    }
}
