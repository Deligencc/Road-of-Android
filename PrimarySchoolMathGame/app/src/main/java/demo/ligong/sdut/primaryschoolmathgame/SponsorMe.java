package demo.ligong.sdut.primaryschoolmathgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Lenovo on 2017/9/27.
 */

public class SponsorMe extends Activity {
    TextView textView;
    TextView getTime;
     int score;
    String time;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
                    //在这里可以进行UI操作
                    textView.setText(score+"");
            getTime.setText("所用时间为: "+time);
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_show);
         textView = (TextView) findViewById(R.id.showMee);
         getTime = (TextView) findViewById(R.id.showTime);
        Intent intent = getIntent();
        int a = TestAdapter.total.length;
        score = 0;
        for (int i = 0; i <a; i++) {
            if(TestAdapter.total[i]==1){
                ++score;
            }
        }
        ImageView image = (ImageView) findViewById(R.id.img);
        image.setVisibility(View.VISIBLE);
        Animation loadAnimation;
        loadAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        image.startAnimation(loadAnimation);
        if(score>=0 && score <=5){
            loadAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha);
            image.startAnimation(loadAnimation);
            Toast.makeText(SponsorMe.this,"还要努力哦",Toast.LENGTH_LONG).show();
        }else if (score>=6 && score <=10){
            Toast.makeText(SponsorMe.this,"哇！没想到你这么厉害！",Toast.LENGTH_LONG).show();
            loadAnimation = AnimationUtils.loadAnimation(this, R.anim.continue_anim);
            image.startAnimation(loadAnimation);
        }else if (score>=11 && score <20){
            Toast.makeText(SponsorMe.this,"别骄傲，你是最棒的！",Toast.LENGTH_LONG).show();
            loadAnimation = AnimationUtils.loadAnimation(this, R.anim.scale);
            image.startAnimation(loadAnimation);
        }else if (score>=20 && score <30){
            Toast.makeText(SponsorMe.this,"你真厉害！",Toast.LENGTH_LONG).show();
            loadAnimation = AnimationUtils.loadAnimation(this, R.anim.translate);
            image.startAnimation(loadAnimation);
        }else if (score>=30 && score <40){
            Toast.makeText(SponsorMe.this,"你已经非常优秀啦！",Toast.LENGTH_LONG).show();
            loadAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
            image.startAnimation(loadAnimation);
        }else if (score>=40){
            Toast.makeText(SponsorMe.this,"我都找不到刺夸你啦！你太完美了！",Toast.LENGTH_LONG).show();
            loadAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_out);
            image.startAnimation(loadAnimation);
        }


           // image.setVisibility(View.GONE);




         time = intent.getStringExtra("time");


        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                handler.sendMessage(message); //将Message对象送出去
            }
        }).start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(SponsorMe.this,MainActivity.class);
        startActivity(intent);
    }
}
