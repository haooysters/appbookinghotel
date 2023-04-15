package com.example.thuephongkhachsan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class ActivityCustomGridView extends AppCompatActivity {
    private Integer[] Images = {R.drawable.datphong , R.drawable.baiviet
            , R.drawable.thoat};
    private String[] Texts = {"Đặt phòng", "Bài viết du lịch", "Thoát ứng dụng"};
    Class[] arrClasses = { DatThuePhong.class, BaiVietDuLich.class};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_grid_view);

        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayShowHomeEnabled(true);
        myActionBar.setIcon(R.mipmap.hotel);
        GridView gdvMenu = findViewById(R.id.gdv_menu);

        ImageTextAdapter adapter = new ImageTextAdapter(this,
                R.layout.custom_gridview_cell, Images, Texts);
        gdvMenu.setAdapter(adapter);
        gdvMenu.setOnItemClickListener(new ChonCongViec());
    }

    private class ChonCongViec implements
            AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> adp, View v,
                                int position, long id) {
            if (position == 2)
                finish();
            else {
                Intent intent = new Intent(
                        ActivityCustomGridView.this, arrClasses[position]);
                startActivity(intent);
            }
        }
    }
}