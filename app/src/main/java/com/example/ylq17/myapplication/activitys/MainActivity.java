package com.example.ylq17.myapplication.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ylq17.myapplication.BuildConfig;
import com.example.ylq17.myapplication.R;
import com.google.gson.Gson;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @ViewById(R.id.tv_met)
    TextView tv_met;
    @AfterViews
    void pps() {
        Toast.makeText(this,"aaaaaaaaaaaaa", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Tpsld", Toast.LENGTH_SHORT).show();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        String str_opt = "分辨率为:" + dm.widthPixels + "*" + dm.heightPixels;

        tv_met.setText(str_opt);

    }
    @Click(R.id.button)
    void BtVlock(View v){
        if (BuildConfig.DEBUG) Log.d("MainActivity", v.toString());
        ((AppCompatButton)v).setText("Sddd");
        tv_met.setText("Yor click");
    }

}
