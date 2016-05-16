package com.example.ylq17.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ylq17.myapplication.Modle.DemoItem;
import com.example.ylq17.myapplication.activitys.Demo_CanvasActivity_;
import com.example.ylq17.myapplication.activitys.Demo_LayoutInflater_;
import com.example.ylq17.myapplication.activitys.Demo_MyProcessBarViewActivity;
import com.example.ylq17.myapplication.activitys.Demo_MyProcessBarViewActivity_;
import com.example.ylq17.myapplication.activitys.Demo_MySimpleImageViewActivity;
import com.example.ylq17.myapplication.activitys.Demo_MySimpleImageViewActivity_;
import com.example.ylq17.myapplication.activitys.Demo_MySimpleTextViewActivity_;
import com.example.ylq17.myapplication.activitys.MainActivity_;
import com.example.ylq17.myapplication.util.CommonAdapter;
import com.example.ylq17.myapplication.util.ViewHolder;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.start_activity)
public class StartActivity extends AppCompatActivity {
    @ViewById
    ListView list_demos;

    List<DemoItem> allDemos;

    void initDemos() {
        allDemos = new ArrayList<>();
        allDemos.add(new DemoItem(MainActivity_.class, "MainActivity_"));
        allDemos.add(new DemoItem(Demo_LayoutInflater_.class, "Demo_LayoutInflater_ 2016年5月13日 21:37:20"));
        allDemos.add(new DemoItem(Demo_MySimpleTextViewActivity_.class, "Demo_MySimpleTextViewActivity_ 2016年5月14日 21:38:06"));
        allDemos.add(new DemoItem(Demo_MySimpleImageViewActivity_.class, "Demo_MySimpleImageViewActivity_ 2016年5月15日 12:33:26"));
        allDemos.add(new DemoItem(Demo_MyProcessBarViewActivity_.class, "Demo_MyProcessBarViewActivity_ 2016年5月15日 14:58:02"));
        allDemos.add(new DemoItem(Demo_CanvasActivity_.class, "Demo_CanvasActivity_ 2016年5月16日 22:16:02"));
    }
    @AfterViews
    void init() {
        initDemos();
        list_demos.setAdapter(new CommonAdapter<DemoItem>(this,allDemos, R.layout.item_demo_activity) {
            @Override
            public void convert(ViewHolder holder, DemoItem demoItem) {
                holder.setText(R.id.tv_name, demoItem.getStrName());
            }
        });

        list_demos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(StartActivity.this, ((DemoItem)((CommonAdapter)list_demos.getAdapter()).getItem(position)).getClassName());
                startActivity(intent);
            }
        });
    }
}
