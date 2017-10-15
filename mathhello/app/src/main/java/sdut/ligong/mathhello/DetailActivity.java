package sdut.ligong.mathhello;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Lenovo on 2017/9/26.
 */

public class DetailActivity extends Activity {

    public static Long delay;

    private TextView para1, para2, operator, para_1, para_2, operator_1, paraa1, paraa2, operatorr1;

    private TextView see1, see2, see3;

    private EditText remainder1, remainder2, remainder3;

    private EditText result, result_1, resultt1;

    private Button mSubmit, mSubmit_1, mSubmitt1;

    private int location = 0;

    private double sum = 0;

    private int sum2 = 0;

    private int temp;

    private int number[] = new int[20];

    private boolean mOverHundred = false;

    private boolean mIsDivision = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置全屏
        setContentView(R.layout.main8);
        // 初始化控件
        para1 = (TextView) findViewById(R.id.para1);
        para2 = (TextView) findViewById(R.id.para2);
        operator = (TextView) findViewById(R.id.operator);
        result = (EditText) findViewById(R.id.result);
        mSubmit = (Button) findViewById(R.id.submit);

        para_1 = (TextView) findViewById(R.id.para_1);
        para_2 = (TextView) findViewById(R.id.para_2);
        operator_1 = (TextView) findViewById(R.id.operator_1);
        result_1 = (EditText) findViewById(R.id.result_1);
        mSubmit_1 = (Button) findViewById(R.id.submit_1);

        paraa1 = (TextView) findViewById(R.id.paraa1);
        paraa2 = (TextView) findViewById(R.id.paraa2);
        operatorr1 = (TextView) findViewById(R.id.operatorr1);
        resultt1 = (EditText) findViewById(R.id.resultt1);
        mSubmitt1 = (Button) findViewById(R.id.submitt1);

        see1 = (TextView) findViewById(R.id.see1);
        see2 = (TextView) findViewById(R.id.see2);
        see3 = (TextView) findViewById(R.id.see3);

        remainder1 = (EditText) findViewById(R.id.remainder1);
        remainder2 = (EditText) findViewById(R.id.remainder2);
        remainder3 = (EditText) findViewById(R.id.remainder3);




        final String index = getIntent().getStringExtra("index"); //获得元素位置
        String title = getIntent().getStringExtra("title");
        location = Integer.parseInt(index);

        String range = title.substring(0, 2);
        String ran = title.substring(2, 3);
        String infer = title.substring(4, 5);
        String inf = title.substring(5, 6);
        // Toast.makeText(DetailActivity.this, "inf is "+inf, Toast.LENGTH_SHORT).show();


        int fanwei = 10;
        if (!ran.equals(0) && !inf.equals("加") && !inf.equals("减") && !inf.equals("乘") && !inf.equals("除")) {
            mOverHundred = false;
            mIsDivision = false;
            fanwei = Integer.parseInt(range);

            for (int i = 0; i < 10; i++) {
                number[i] = (int) (fanwei*Math.random());
            }

            see1.setVisibility(View.GONE);
            see2.setVisibility(View.GONE);
            see3.setVisibility(View.GONE);
            remainder1.setVisibility(View.GONE);
            remainder2.setVisibility(View.GONE);
            remainder3.setVisibility(View.GONE);
            if (infer.equals("加")) {
                para1.setText(number[0] + "");
                para2.setText(number[1] + "");
                operator.setText("+");
                number[11] = number[0] + number[1];

                para_1.setText(number[2] + "");
                para_2.setText(number[3] + "");
                operator_1.setText("+");
                number[12] = number[2] + number[3];

                paraa1.setText(number[4] + "");
                paraa2.setText(number[5] + "");
                operatorr1.setText("+");
                number[13] = number[4] + number[5];

            } else if (infer.equals("减")) {
                if (number[0] < number[1]) {
                    temp = number[0];
                    number[0] = number[1];
                    number[1] = temp;
                }
                if (number[2] < number[3]) {
                    temp = number[2];
                    number[2] = number[3];
                    number[3] = temp;
                }
                if (number[4] < number[5]) {
                    temp = number[4];
                    number[4] = number[5];
                    number[5] = temp;
                }
                para1.setText(number[0] + "");
                para2.setText(number[1] + "");
                operator.setText("-");
                number[11] = number[0] - number[1];

                para_1.setText(number[2] + "");
                para_2.setText(number[3] + "");
                operator_1.setText("-");
                number[12] = number[2] - number[3];

                paraa1.setText(number[4] + "");
                paraa2.setText(number[5] + "");
                operatorr1.setText("-");
                number[13] = number[4] - number[5];

            } else if (infer.equals("乘")) {
                para1.setText(number[0] + "");
                para2.setText(number[1] + "");
                operator.setText("*");
                number[11] = number[0] * number[1];

                para_1.setText(number[2] + "");
                para_2.setText(number[3] + "");
                operator_1.setText("*");
                number[12] = number[2] * number[3];

                paraa1.setText(number[4] + "");
                paraa2.setText(number[5] + "");
                operatorr1.setText("*");
                number[13] = number[4] * number[5];
            } else if (infer.equals("除")) {
                mIsDivision = true;
                see1.setVisibility(View.VISIBLE);
                see2.setVisibility(View.VISIBLE);
                see3.setVisibility(View.VISIBLE);
                remainder1.setVisibility(View.VISIBLE);
                remainder2.setVisibility(View.VISIBLE);
                remainder3.setVisibility(View.VISIBLE);
                if (number[0] < number[1]) {
                    temp = number[0];
                    number[0] = number[1];
                    number[1] = temp;
                }
                if (number[2] < number[3]) {
                    temp = number[2];
                    number[2] = number[3];
                    number[3] = temp;
                }
                if (number[4] < number[5]) {
                    temp = number[4];
                    number[4] = number[5];
                    number[5] = temp;
                }
                para1.setTextSize(30);
                para2.setTextSize(30);
                para1.setText(number[0] + "");
                para2.setText(number[1] + "");
                operator.setText("÷");
                number[11] = number[0] / number[1];
                number[14] = number[0] % number[1];

                para_1.setTextSize(30);
                para_2.setTextSize(30);
                para_1.setText(number[2] + "");
                para_2.setText(number[3] + "");
                operator_1.setText("÷");
                number[12] = number[2] / number[3];
                number[15] = number[2] % number[3];

                paraa1.setTextSize(30);
                paraa2.setTextSize(30);
                paraa1.setText(number[4] + "");
                paraa2.setText(number[5] + "");
                operatorr1.setText("÷");
                number[13] = number[4] / number[5];
                number[16] = number[4] % number[5];

            }
        } else { //一百
            mOverHundred = true;
            mIsDivision = false;
            see1.setVisibility(View.GONE);
            see2.setVisibility(View.GONE);
            see3.setVisibility(View.GONE);
            remainder1.setVisibility(View.GONE);
            remainder2.setVisibility(View.GONE);
            remainder3.setVisibility(View.GONE);

            for (int i = 0; i < 10; i++) {
                number[i] = (int) (100*Math.random());
            }
            if (inf.equals("加")) {
                para1.setText(number[0] + "");
                para2.setText(number[1] + "");
                operator.setText("+");
                number[11] = number[0] + number[1];

                para_1.setText(number[2] + "");
                para_2.setText(number[3] + "");
                operator_1.setText("+");
                number[12] = number[2] + number[3];


                paraa1.setText(number[4] + "");
                paraa2.setText(number[5] + "");
                operatorr1.setText("+");
                number[13] = number[4] + number[5];

            } else if (inf.equals("减")) {
                if (number[0] < number[1]) {
                    temp = number[0];
                    number[0] = number[1];
                    number[1] = temp;
                }
                if (number[2] < number[3]) {
                    temp = number[2];
                    number[2] = number[3];
                    number[3] = temp;
                }
                if (number[4] < number[5]) {
                    temp = number[4];
                    number[4] = number[5];
                    number[5] = temp;
                }
                para1.setText(number[0] + "");
                para2.setText(number[1] + "");
                operator.setText("-");
                number[11] = number[0] - number[1];

                para_1.setText(number[2] + "");
                para_2.setText(number[3] + "");
                operator_1.setText("-");
                number[12] = number[2] - number[3];

                paraa1.setText(number[4] + "");
                paraa2.setText(number[5] + "");
                operatorr1.setText("-");
                number[13] = number[4] - number[5];

            } else if (inf.equals("乘")) {
                para1.setText(number[0] + "");
                para2.setText(number[1] + "");
                operator.setText("*");
                number[11] = number[0] * number[1];

                para_1.setText(number[2] + "");
                para_2.setText(number[3] + "");
                operator_1.setText("*");
                number[12] = number[2] * number[3];

                paraa1.setText(number[4] + "");
                paraa2.setText(number[5] + "");
                operatorr1.setText("*");
                number[13] = number[4] * number[5];
            } else if (inf.equals("除")) {
                mIsDivision = true;
                see1.setVisibility(View.VISIBLE);
                see2.setVisibility(View.VISIBLE);
                see3.setVisibility(View.VISIBLE);
                remainder1.setVisibility(View.VISIBLE);
                remainder2.setVisibility(View.VISIBLE);
                remainder3.setVisibility(View.VISIBLE);
                if (number[0] < number[1]) {
                    temp = number[0];
                    number[0] = number[1];
                    number[1] = temp;
                }
                if (number[2] < number[3]) {
                    temp = number[2];
                    number[2] = number[3];
                    number[3] = temp;
                }
                if (number[4] < number[5]) {
                    temp = number[4];
                    number[4] = number[5];
                    number[5] = temp;
                }
                para1.setTextSize(30);
                para2.setTextSize(30);
                para1.setText(number[0] + "");
                para2.setText(number[1] + "");
                operator.setText("÷");
                number[11] = number[0] / number[1];
                number[14] = number[0] % number[1];

                para_1.setTextSize(30);
                para_2.setTextSize(30);
                para_1.setText(number[2] + "");
                para_2.setText(number[3] + "");
                operator_1.setText("÷");
                number[12] = number[2] / number[3];
                number[15] = number[2] % number[3];

                paraa1.setText(number[4] + "");
                paraa2.setText(number[5] + "");
                operatorr1.setText("÷");
                number[13] = number[4] / number[5];
                number[16] = number[4] % number[5];

            }
        }


        TextView info = (TextView) findViewById(R.id.info);
        info.setText("等级:" + index + " " + title);

        result.addTextChangedListener(new TextWatcher() {
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
                    String content = null;
                    content = result.getText().toString();

                    delay = Long.parseLong(content);
                    if (content.equals(null)) {
                        content = -100 + "";
                    }
                    int mResult = Integer.parseInt(content);
                    if(mIsDivision){
                        String content1 = null;
                        content1 = remainder1.getText().toString();
                        int mResult1 = Integer.parseInt(content1);
                        if (mResult == number[11] && mResult1 == number[14]) {
                            para1.setTextColor(Color.rgb(27,129,62));
                            para2.setTextColor(Color.rgb(27,129,62));
                            operator.setTextColor(Color.rgb(27,129,62));
                            result.setTextColor(Color.rgb(27,129,62));

                           Toast.makeText(DetailActivity.this, "恭喜你答对了，你是最棒的！", Toast.LENGTH_SHORT).show();
                        } else {
                            para1.setTextColor(Color.RED);
                            para2.setTextColor(Color.RED);
                            operator.setTextColor(Color.RED);
                            result.setTextColor(Color.RED);
                        //    Toast.makeText(DetailActivity.this, "相信我，你离成功只差一步！", Toast.LENGTH_SHORT).show();
                            result.setText(null);
                        }

                    }else {
                        if (mResult == number[11]) {
                            para1.setTextColor(Color.rgb(27,129,62));
                            para2.setTextColor(Color.rgb(27,129,62));
                            operator.setTextColor(Color.rgb(27,129,62));
                            result.setTextColor(Color.rgb(27,129,62));
                          //  Toast.makeText(DetailActivity.this, "你真聪明", Toast.LENGTH_SHORT).show();
                        } else {
                            para1.setTextColor(Color.RED);
                            para2.setTextColor(Color.RED);
                            operator.setTextColor(Color.RED);
                            result.setTextColor(Color.RED);
                          //  Toast.makeText(DetailActivity.this, "快接近正确答案咯！再接再厉", Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (NumberFormatException e){
                    e.printStackTrace();
                }
                DetailActivity.delay = delay;

            }
        });


        result_1.addTextChangedListener(new TextWatcher() {
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
                    result_1.setVisibility(View.VISIBLE);
                    String content = null;
                    content = result_1.getText().toString();

                    delay = Long.parseLong(content);
                    if (content.equals(null)) {
                        content = -100 + "";
                    }
                    int mresult_1 = Integer.parseInt(content);
                    if(mIsDivision){
                        String content1 = null;
                        content1 = remainder1.getText().toString();
                        int mresult_11 = Integer.parseInt(content1);
                        if (mresult_1 == number[12] && mresult_11 == number[15]) {
                            para_1.setTextColor(Color.rgb(27,129,62));
                            para_2.setTextColor(Color.rgb(27,129,62));
                            operator_1.setTextColor(Color.rgb(27,129,62));
                            result_1.setTextColor(Color.rgb(27,129,62));
                            //  Toast.makeText(DetailActivity.this, "恭喜你答对了，你是最棒的！", Toast.LENGTH_SHORT).show();
                        } else {
                            para_1.setTextColor(Color.RED);
                            para_2.setTextColor(Color.RED);
                            operator_1.setTextColor(Color.RED);
                            result_1.setTextColor(Color.RED);
                            //    Toast.makeText(DetailActivity.this, "相信我，你离成功只差一步！", Toast.LENGTH_SHORT).show();
                            result_1.setText(null);
                        }

                    }else {
                        if (mresult_1 == number[12]) {
                            para_1.setTextColor(Color.rgb(27,129,62));
                            para_2.setTextColor(Color.rgb(27,129,62));
                            operator_1.setTextColor(Color.rgb(27,129,62));
                            result_1.setTextColor(Color.rgb(27,129,62));
                            //  Toast.makeText(DetailActivity.this, "你真聪明", Toast.LENGTH_SHORT).show();
                        } else {
                            para_1.setTextColor(Color.RED);
                            para_2.setTextColor(Color.RED);
                            operator_1.setTextColor(Color.RED);
                            result_1.setTextColor(Color.RED);
                            //  Toast.makeText(DetailActivity.this, "快接近正确答案咯！再接再厉", Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (NumberFormatException e){
                    e.printStackTrace();
                }
                DetailActivity.delay = delay;
                

            }
        });
//        mSubmit_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                String content = null;
//                content = result_1.getText().toString();
//                if (content.equals(null)) {
//                    content = -100 + "";
//                }
//                int mResult = Integer.parseInt(content);
//                if(!mIsDivision){
//                    if (mResult == number[12]) {
//                        result_1.setTextColor(Color.rgb(27,129,62));
//                        Toast.makeText(DetailActivity.this, "好棒呀你！又对了", Toast.LENGTH_SHORT).show();
//                    } else {
//                        result_1.setTextColor(Color.RED);
//                        Toast.makeText(DetailActivity.this, "加油加油，还需要努力！", Toast.LENGTH_SHORT).show();
//                    }
//                }else {
//                    String content1 = null;
//                    content1 = remainder2.getText().toString();
//                    int mResult1 = Integer.parseInt(content1);
//                    if (mResult == number[12] && mResult1 == number[15]) {
//                        result_1.setTextColor(Color.rgb(27,129,62));
//                        Toast.makeText(DetailActivity.this, "恭喜你又答对了天才！没错，就是你！", Toast.LENGTH_SHORT).show();
//                    } else {
//                        result_1.setTextColor(Color.RED);
//                        Toast.makeText(DetailActivity.this, "很遗憾！快接近正确答案咯！再接再厉", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//
//            }
//        });
//        mSubmitt1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                String content = null;
//                content = resultt1.getText().toString();
//                if (content.equals(null)) {
//                    content = -100 + "";
//                }
//                int mResult = Integer.parseInt(content);
//                if(mIsDivision){
//                    String content1 = null;
//                    content1 = remainder3.getText().toString();
//                    int mResult1 = Integer.parseInt(content1);
//                    if (mResult == number[13] && mResult1 == number[16]) {
//                        resultt1.setTextColor(Color.rgb(27,129,62));
//                        Toast.makeText(DetailActivity.this, "恭喜你答对了！我就知道你是最棒的！", Toast.LENGTH_SHORT).show();
//                    } else {
//                        resultt1.setTextColor(Color.RED);
//                        Toast.makeText(DetailActivity.this, "很遗憾，我相信你一定会的！", Toast.LENGTH_SHORT).show();
//                    }
//                }else {
//                    if (mResult == number[13]) {
//                        resultt1.setTextColor(Color.rgb(27,129,62));
//                        Toast.makeText(DetailActivity.this, "恭喜你答对了！我太佩服你了！", Toast.LENGTH_SHORT).show();
//                    } else {
//                        resultt1.setTextColor(Color.RED);
//                        Toast.makeText(DetailActivity.this, "很遗憾，但是失败是成功之母，加油！", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//            }
//        });


    }


    public void swap(Integer a, Integer b) {

        Integer temp = a;

        a = b;

        b = temp;

    }
}
