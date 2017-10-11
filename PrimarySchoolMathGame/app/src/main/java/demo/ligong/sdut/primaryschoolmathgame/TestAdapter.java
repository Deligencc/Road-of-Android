package demo.ligong.sdut.primaryschoolmathgame;


import android.content.Context;
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

/**
 * Created by Lenovo on 2017/10/3.
 */

    public class TestAdapter extends BaseAdapter {

    /** 数据集合 */
    List<Map<String,Object>> list;

    int num1,num2,num3;

    static int[] para;
    static int[] total;
    static int[] para2;

    private long delay;
    /** 反射器 */
    LayoutInflater inflater;
    /**
     * 构造器
     * @param context 上下文
     */
    public TestAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }
    /**
     * 传入数据集合
     * @param list
     */
    public void setList(List<Map<String, Object>> list) {
        this.list = list;
        total = new int[list.size()];
        para = new int[list.size()];
        para2 = new int[list.size()];

    }

    public void onDateChange(List<Map<String, Object>>  apk_list) {
        this.list = apk_list;
        this.notifyDataSetChanged();
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

        final TextView textView1,textView2,textView3,textView4,textView6,textView7,textView8,textView9;
        final  EditText textView5;
        textView1 = (TextView) view.findViewById(R.id.sum);
        textView4 = (TextView) view.findViewById(R.id.sum1);
        textView5 = (EditText) view.findViewById(R.id.sum2);




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
                delay = 3000L;
                try {
                    TestActivity.hashMap.put(position,s.toString());
                    num3 = Integer.parseInt(s.toString());
                    num1 = Integer.parseInt(textView1.getText().toString());
                    num2 = Integer.parseInt(textView4.getText().toString());
                    int num4 = num1 - num2;
                    if(num4==num3){
                      //  TestActivity.score+=2;
                        total[position] = 1;
                       // Toast.makeText(parent.getContext(),"分数现在是"+TestActivity.score,Toast.LENGTH_SHORT).show();
                    }
                  //  Toast.makeText(parent.getContext(),"num1 = "+num1+" num2 = "+num2+" num3 ="+num3+" num4 = "+num4+"  \nscore = "+TestActivity.score,Toast.LENGTH_LONG).show();
//                    String content = null;
//                    content = editText.getText().toString();
//                    String content2 = textView1.getText().toString();
//                    String content3 = textView4.getText().toString();
//
//                    delay = Long.parseLong(content);
//                    if (content.equals(null)) {
//                        content = -100 + "";
//                    }
//                    int mResult = Integer.parseInt(content2)-Integer.parseInt(content3);
//                    int mResult1 = Integer.parseInt(content);
//
//                    if (mResult == mResult1) {
//                        textView1.setTextColor(Color.GREEN);
//                        textView4.setTextColor(Color.GREEN);
//                        textView5.setTextColor(Color.GREEN);
//
//                    } else {
//                        textView1.setTextColor(Color.RED);
//                        textView4.setTextColor(Color.RED);
//                        textView5.setTextColor(Color.RED);
//                    }

                }catch (NumberFormatException e){
                    e.printStackTrace();
                }
                delay = delay;
            }
        });
        textView1.setText((String)(list.get(position).get("title1")));

        textView4.setText((String)(list.get(position).get("title4")));



        if(TestActivity.hashMap.get(position)!=null){
            textView5.setText(TestActivity.hashMap.get(position).toString());
        }

        return view;
    }
    }