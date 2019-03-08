package com.mouqukeji.hmdeer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.paolorotolo.appintro.AppIntro;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.ui.fragment.SlideFragment;
import com.mouqukeji.hmdeer.util.AppConstants;
import com.mouqukeji.hmdeer.util.SpUtils;

public class AppIntroActivity extends AppIntro {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        SpUtils.putString("isFirst","1",this);
        addSlide(SlideFragment.newInstance(R.layout.intro1));
        addSlide(SlideFragment.newInstance(R.layout.intro2));
        addSlide(SlideFragment.newInstance(R.layout.intro3));
        setVibrateIntensity(30);
        setIndicatorColor(getResources().getColor(R.color.blue), getResources().getColor(R.color.address_gray));
        setSkipText("跳过");
        setColorSkipButton(getResources().getColor(R.color.blue));
        setImageNextButton(getResources().getDrawable(R.drawable.mipmap_blue_next));
        setDoneText("完成");
        setColorDoneText(getResources().getColor(R.color.blue));
        setSeparatorColor(getResources().getColor(R.color.transparent));
    }

    @Override
    public void onSkipPressed() {
        //当执行跳过动作时触发
        Intent intent = new Intent(AppIntroActivity.this, SplashActivity.class);
        startActivity(intent);
        SpUtils.putBoolean(AppConstants.FIRST_OPEN, true, AppIntroActivity.this);
        finish();
    }

    @Override
    public void onNextPressed() {
        //当执行下一步动作时触发
    }

    @Override
    public void onDonePressed() {
        //当执行完成动作时触发
        Intent intent = new Intent(AppIntroActivity.this, SplashActivity.class);
        startActivity(intent);
        SpUtils.putBoolean(AppConstants.FIRST_OPEN, true, AppIntroActivity.this);
        finish();
    }

    @Override
    public void onSlideChanged() {
        //当执行变化动作时触发
    }
}
