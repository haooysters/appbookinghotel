package com.example.thuephongkhachsan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class XacNhanThuePhong extends AppCompatActivity {
    String strHoTen, strDienThoai, strEmail ,strThanhToan;
    EditText edtHoTen, edtDienThoai, edtEmail;
    TextInputLayout layoutHoTen, layoutDienThoai, layoutEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_thue_phong);

        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayShowHomeEnabled(true);
        myActionBar.setIcon(R.mipmap.hotel);
        edtHoTen = findViewById(R.id.edt_hotenkh);
        edtDienThoai = findViewById(R.id.edt_dienthoai);
        edtEmail = findViewById(R.id.edt_email);
        layoutHoTen = findViewById(R.id.layout_hotenkh);
        layoutDienThoai = findViewById(R.id.layout_dienthoai);
        layoutEmail = findViewById(R.id.layout_email);

        Intent intent = getIntent();
        Bundle bundleNhan = intent.getExtras();
        String strDiaDiem, strLoaiPhong, strNgayBD, strThongTin;
        int dongia, songay, sotien;
        boolean buasang,xeduadon;
        strNgayBD = bundleNhan.getString("NgayBD", "");
        strDiaDiem = bundleNhan.getString("DiaDiem", "");
        strLoaiPhong = bundleNhan.getString("LoaiPhong", "");
        dongia = bundleNhan.getInt("DonGia", 0);
        songay = bundleNhan.getInt("SoNgay", 0);
        sotien = bundleNhan.getInt("SoTien", 0);
        buasang = bundleNhan.getBoolean("BuaSang", false);
        xeduadon = bundleNhan.getBoolean("XeDuaDon", false);
        strThongTin = "Thông tin đặt thuê phòng:";
        strThongTin += "\n Địa điểm: " + strDiaDiem;
        strThongTin += "\n Loại phòng: " + strLoaiPhong;
        strThongTin += "\n Đơn giá: " + dongia;
        strThongTin += "\n Ngày bắt đầu: " + strNgayBD;
        strThongTin += "\n Số ngày thuê: " + songay;
        if (buasang)
            strThongTin += "\n Bao gồm bữa sáng: Có";
        else
            strThongTin += "\n Bao gồm bữa sáng: Không";
        if (xeduadon)
            strThongTin += "\n Xe đưa đón sân bay: Có";
        else
            strThongTin += "\n Xe đưa đón sân bay: Không";
        strThongTin += "\n Số tiền: " + sotien;
        TextView txtThongTin = findViewById(R.id.txt_thongtin);
        txtThongTin.setText(strThongTin);

    }

    private void GoiEmail(String[] address, String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("message/rfc822");
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, address);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        try {
            startActivity(Intent.createChooser(intent,
                    "Chọn ứng dụng gởi Email:"));
        } catch (ActivityNotFoundException err){
            Toast.makeText(this, " Lỗi thực hiện gởi Email ",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void XacNhanThuePhong(View view) {
        strHoTen = edtHoTen.getText().toString().trim();
        if (strHoTen.length() == 0) {
            layoutHoTen.setError("Lỗi chưa nhập họ tên khách hàng");
            edtHoTen.requestFocus();
            return;
        } else
            layoutHoTen.setError(null);
        strDienThoai = edtDienThoai.getText().toString().trim();
        if (strDienThoai.length() == 0) {
            layoutDienThoai.setError("Lỗi chưa nhập số điện thoại");
            edtDienThoai.requestFocus();
            return;
        } else
            layoutDienThoai.setError(null);
        strEmail = edtEmail.getText().toString().trim();
        if (strEmail.length() == 0) {
            layoutEmail.setError("Lỗi chưa nhập email");
            edtEmail.requestFocus();
            return;
        } else
            layoutEmail.setError(null);
        RadioGroup grpThanhToan = findViewById(R.id.grp_thanhtoan);
        int id = grpThanhToan.getCheckedRadioButtonId();
        RadioButton rad = findViewById(id);
        strThanhToan = rad.getText().toString();
        Bundle bundleGoi = new Bundle();
        bundleGoi.putString("HoTen", strHoTen);
        bundleGoi.putString("DienThoai", strDienThoai);
        bundleGoi.putString("ThanhToan", strThanhToan);

        Intent intent = getIntent();
        Bundle bundleNhan = intent.getExtras();
        String strDiaDiem, strLoaiPhong, strNgayBD, strThongTin;
        int dongia, songay, sotien;
        boolean buasang,xeduadon;
        strNgayBD = bundleNhan.getString("NgayBD", "");
        strDiaDiem = bundleNhan.getString("DiaDiem", "");
        strLoaiPhong = bundleNhan.getString("LoaiPhong", "");
        dongia = bundleNhan.getInt("DonGia", 0);
        songay = bundleNhan.getInt("SoNgay", 0);
        sotien = bundleNhan.getInt("SoTien", 0);
        buasang = bundleNhan.getBoolean("BuaSang", false);
        xeduadon = bundleNhan.getBoolean("XeDuaDon", false);



        String strChuDe = "Thông tin đăng ký Oysters Booking Now";
        String strGoiMail = "Họ tên khách hàng: " + strHoTen;
        strGoiMail += "\nSố điện thoại: " + strDienThoai;
        strGoiMail += "\nĐịa điểm đặt phòng: " + strDiaDiem;
        strGoiMail += "\nLoại phòng: " + strLoaiPhong;
        strGoiMail += "\nĐơn giá: " + dongia;
        strGoiMail += "\nNgày bắt đầu: " + strNgayBD;
        strGoiMail += "\nSố ngày thuê: " + songay;
        if (buasang)
            strGoiMail += "\n Bao gồm bữa sáng: Có";
        else
            strGoiMail += "\n Bao gồm bữa sáng: Không";
        if (xeduadon)
            strGoiMail += "\n Xe đưa đón sân bay: Có";
        else
            strGoiMail += "\n Xe đưa đón sân bay: Không";
        strGoiMail += "\nHình thức : " + strThanhToan;
        strGoiMail += "\nSố tiền thanh toán: " + sotien;
        strGoiMail += "\n\nChúc quý khách vui vẻ!";
        GoiEmail(new String[] { strEmail }, strChuDe, strGoiMail);
    }

    public void HuyBo(View view) {
        finish();
    }
}