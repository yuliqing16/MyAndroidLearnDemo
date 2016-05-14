package com.example.ylq17.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ylq17.myapplication.Modle.DemoItem;
import com.example.ylq17.myapplication.activitys.Demo_LayoutInflater_;
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
        allDemos.add(new DemoItem(MainActivity_.class, "Text Button Style and click!"));
        allDemos.add(new DemoItem(Demo_LayoutInflater_.class, "Text Button Style and click!"));
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
