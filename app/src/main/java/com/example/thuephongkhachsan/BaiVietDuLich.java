package com.example.thuephongkhachsan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class BaiVietDuLich extends AppCompatActivity {

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_viet_du_lich);

        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayShowHomeEnabled(true);
        myActionBar.setIcon(R.mipmap.hotel);

        List<Posts> countries = getListData();
        this.recyclerView = (RecyclerView) this.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new CustomRecyclerViewAdapter(this, countries));

        // RecyclerView scroll vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
    private  List<Posts> getListData() {
        List<Posts> list = new ArrayList<Posts>();
        Posts ct = new Posts("Cẩm nang du lịch Cần Thơ chi tiết từ A - Z", "ct");
        Posts dl = new Posts("Trải Nghiệm Đu Dây High Rope Course Đà Lạt", "dl");
        Posts ha = new Posts("8 địa điểm du lịch Hội An và điều bí ẩn hớp hồn khách du lịch", "ha");
        Posts hl = new Posts("Top 5 du thuyền vịnh Bái Tử Long nổi tiếng bạn không nên bỏ qua", "hl");
        Posts pq = new Posts("13 Địa Điểm Du Lịch Phú Quốc Cực Mới Mẻ", "pq");

        list.add(ct);
        list.add(dl);
        list.add(ha);
        list.add(hl);
        list.add(pq);

        return list;
    }
}