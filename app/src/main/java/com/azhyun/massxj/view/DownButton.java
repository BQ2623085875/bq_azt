package com.azhyun.massxj.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.Button;

import com.azhyun.massxj.R;

/**
 * Created by tl on 2018/8/8.
 */

@SuppressLint("AppCompatCustomView")
public class DownButton extends Button {

    //总时长
    private long millisinfuture;

    //间隔时长
    private long countdowninterva;

    //默认背景颜色
    private int normalColor;

    //倒计时 背景颜色
    private int countDownColor;

    //是否结束
    private boolean isFinish;

    //定时器
    private CountDownTimer countDownTimer;

    public DownButton(Context context) {
        this(context,null);
    }

    public DownButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    @SuppressLint("ResourceAsColor")
    public DownButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CountDownButton,defStyleAttr,0);
        //设置默认时长
        millisinfuture = (long) typedArray.getInt(R.styleable.CountDownButton_millisinfuture,60000);
        //设置默认间隔时长
        countdowninterva = (long)typedArray.getInt(R.styleable.CountDownButton_countdowninterva,1000);
        //设置默认背景色
        normalColor = typedArray.getColor(R.styleable.CountDownButton_normalColor,android.R.color.white);
        //设置默认倒计时 背景色
        countDownColor = typedArray.getColor(R.styleable.CountDownButton_countDownColor,android.R.color.white);
        typedArray.recycle();
        //默认为已结束状态
        isFinish = true;
        //字体居中
        setGravity(Gravity.CENTER);
        //默认文字和背景色
        normalBackground();
        //设置定时器
        countDownTimer = new CountDownTimer(millisinfuture, countdowninterva) {
            @Override
            public void onTick(long millisUntilFinished) {
                //未结束
                isFinish = false;

                setText((Math.round((double) millisUntilFinished / 1000) - 1) + "秒");

                setBackgroundResource(countDownColor);
            }

            @Override
            public void onFinish() {
                //结束
                isFinish = true;

                normalBackground();
            }
        };
    }

    public void normalBackground(){

        setText("获取验证码");
        setBackgroundResource(normalColor);
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void cancel(){
        isFinish = true;
        countDownTimer.cancel();
    }

    public void start(){
        countDownTimer.start();
    }

}