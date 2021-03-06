package net.wutnews.app.app.act.menu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lidroid.xutils.util.LogUtils;

import net.wutnews.app.R;
import net.wutnews.app.app.act.app.AppBaseAct;
import net.wutnews.app.app.act.news.NewsAct;
import net.wutnews.app.app.act.subscribe.SubscribeAct;
import net.wutnews.app.app.entiy.DBUserinfo;
import net.wutnews.app.app.util.dbUtil;

/**
 * Created by Pan on 2015/1/25 0025.
 */
public class MenuAct extends AppBaseAct implements View.OnClickListener {

    private LinearLayout ll_center, ll_about, ll_collection;
    private ImageView iv_img, iv_cache,iv_night;
    private TextView  tv_clear;
    private DBUserinfo userInfo;
    public static Handler mHandler;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitleBar("设置");
        setBottomBar();
        userInfo = getUserinfo(this);
        findView();

        if (userInfo.isNotWifiImg()) {
            iv_img.setImageResource(R.drawable.setting_open);
        }
        if (userInfo.isNotWifiNewsCache()) {
            iv_cache.setImageResource(R.drawable.setting_open);
        }

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                finish();
                super.handleMessage(msg);
            }
        };
    }

    private void findView() {
        ll_center = (LinearLayout) findViewById(R.id.ll_center);
        ll_center.setOnClickListener(this);
        ll_about = (LinearLayout) findViewById(R.id.ll_about);
        ll_about.setOnClickListener(this);
        ll_collection = (LinearLayout) findViewById(R.id.ll_collection);
        ll_collection.setOnClickListener(this);
        iv_img = (ImageView) findViewById(R.id.iv_img);
        iv_img.setOnClickListener(this);
        iv_cache = (ImageView) findViewById(R.id.iv_cache);
        iv_cache.setOnClickListener(this);
        iv_night = (ImageView) findViewById(R.id.iv_night);
        iv_night.setOnClickListener(this);
        tv_clear = (TextView) findViewById(R.id.tv_clear);
        tv_clear.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            default:
                break;
            case R.id.ll_center:
                startActivity(new Intent(this, UserCenter.class));

                break;
            case R.id.ll_about:
                startActivity(new Intent(this, AboutAct.class));


                break;
            case R.id.ll_collection:


                break;
            case R.id.tv_clear:


                break;
            case R.id.iv_img:
                DBUserinfo userInfo_IMG = getUserinfo(this);
                if (userInfo_IMG.isNotWifiImg()) {
                    userInfo_IMG.setNotWifiImg(false);
                    iv_img.setImageResource(R.drawable.setting_close);
                } else {
                    userInfo_IMG.setNotWifiImg(true);
                    iv_img.setImageResource(R.drawable.setting_open);
                }
                dbUtil.updateByWhere(this, userInfo_IMG, "id = '" + userInfo.getId() + "'");

                break;
            case R.id.iv_cache:
                DBUserinfo userInfo_CACHE = getUserinfo(this);
                if (userInfo_CACHE.isNotWifiNewsCache()) {
                    userInfo_CACHE.setNotWifiNewsCache(false);
                    iv_cache.setImageResource(R.drawable.setting_close);
                } else {
                    userInfo_CACHE.setNotWifiNewsCache(true);
                    iv_cache.setImageResource(R.drawable.setting_open);
                }
                dbUtil.updateByWhere(this, userInfo_CACHE, "id = '" + userInfo.getId() + "'");
                break;
            case R.id.iv_night:
                DBUserinfo userInfo_NIGHT = getUserinfo(this);
                if (userInfo_NIGHT.isNightMode()) {
                    userInfo_NIGHT.setNightMode(false);
                    iv_night.setImageResource(R.drawable.setting_close);
                } else {
                    userInfo_NIGHT.setNightMode(true);
                    iv_night.setImageResource(R.drawable.setting_open);
                }
                dbUtil.updateByWhere(this, userInfo_NIGHT, "id = '" + userInfo.getId() + "'");

                break;

        }

    }

    private RadioButton rb1, rb2;

    private void setBottomBar() {
        rb1 = (RadioButton) findViewById(R.id.RadioButton00);
        rb2 = (RadioButton) findViewById(R.id.RadioButton01);

        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuAct.this, NewsAct.class);
                startActivity(intent);
                finish();
            }
        });

        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuAct.this, SubscribeAct.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
