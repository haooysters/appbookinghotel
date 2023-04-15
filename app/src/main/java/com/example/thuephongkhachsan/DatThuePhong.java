package com.example.thuephongkhachsan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatThuePhong extends AppCompatActivity {

    String arrLoaiPhong[] = { "Phòng Tiêu Chuẩn", "Phòng Giường Đôi Hạng Tiết Kiệm", "Phòng Deluxe Giường Đôi",
            "Phòng Superior Giường Đôi", "Bungalow Nhìn ra vườn" };
    String arrDiaDiem[] = {"Cần Thơ", "Hồ Chí Minh", "Đà Lạt",
    "Vũng Tàu" , "Hội An"};
    int arrDonGia[] = {400, 700, 800, 1000, 1300};
    Spinner spnLoaiPhong, spnDiaDiem;
    String strLoaiPhong, strNgayBD, strDiaDiem;
    int dongia, songay, sotien, giamgia;
    boolean buasang,xeduadon;
    EditText edtDonGia, edtSoTien;
    TextView txtNgayBD;
    TextInputLayout layoutSoNgay, layoutMaGiamGia;
    TextInputEditText edtSoNgay, edtMaGiamGia;
    CheckBox chkBuaSang, chkXeDuaDon;
    DatePickerDialog.OnDateSetListener myDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_thue_phong);

        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayShowHomeEnabled(true);
        myActionBar.setIcon(R.mipmap.hotel);
        txtNgayBD = findViewById(R.id.txt_ngaybd);
        edtDonGia = findViewById(R.id.edt_dongia);
        edtSoNgay = findViewById(R.id.edt_songay);
        edtSoTien = findViewById(R.id.edt_sotien);
        layoutSoNgay = findViewById(R.id.layout_songay);
        edtMaGiamGia = findViewById(R.id.edt_magiamgia);
        layoutMaGiamGia = findViewById(R.id.layout_magiamgia);

        chkBuaSang = findViewById(R.id.chk_buasang);
        chkXeDuaDon = findViewById(R.id.chk_xeduadon);
        spnLoaiPhong = findViewById(R.id.spn_loaiphong);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrLoaiPhong);
        adapter.setDropDownViewResource(
                android.R.layout.simple_list_item_single_choice);
        spnLoaiPhong.setAdapter(adapter);
        spnLoaiPhong.setOnItemSelectedListener(new ChonLoaiPhong());

        spnDiaDiem = findViewById(R.id.spn_diadiem);
        ArrayAdapter<String> adapterdiadiem = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrDiaDiem);
        adapterdiadiem.setDropDownViewResource(
                android.R.layout.simple_list_item_single_choice);
        spnDiaDiem.setAdapter(adapterdiadiem);
        spnDiaDiem.setOnItemSelectedListener(new ChonDiaDiem());

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        strNgayBD = dateFormat.format(calendar.getTime());
        txtNgayBD.setText("Ngày bắt đầu: " + strNgayBD);
    }
    private class ChonLoaiPhong implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int position, long id) {
            strLoaiPhong = arrLoaiPhong[position];
            dongia = arrDonGia[position];
            edtDonGia.setText("Đơn giá: " + dongia);
        }
        public void onNothingSelected(AdapterView<?> parent){
            strLoaiPhong = "";
            dongia = 0;
        }
    }

    private class ChonDiaDiem implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int position, long id) {
            strDiaDiem = arrDiaDiem[position];

        }
        public void onNothingSelected(AdapterView<?> parent){
            strDiaDiem = "";
        }
    }

    public void ChonNgay(View view) {
        Calendar calendar = Calendar.getInstance();
        int iniYear = calendar.get(Calendar.YEAR);
        int iniMonth = calendar.get(Calendar.MONTH);
        int iniDay = calendar.get(Calendar.DAY_OF_MONTH);
        myDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker,
                                  int year, int month, int dayOfMonth) {month = month + 1;
                strNgayBD = dayOfMonth + "/" + month + "/" + year;
                txtNgayBD.setText("Ngày bắt đầu: " + strNgayBD);
            }
        };
        DatePickerDialog dialog = new DatePickerDialog(this,
                myDateSetListener, iniYear, iniMonth, iniDay);
        dialog.setTitle("Chọn ngày bắt đầu thuê");
        dialog.show();
    }
    public boolean TinhTien(View view) {
        String strSoNgay = edtSoNgay.getText().toString().trim();
        if (strSoNgay.length() == 0 ||
                Integer.parseInt(strSoNgay) == 0) {
            layoutSoNgay.setError("Lỗi nhập số ngày thuê phòng");
            edtSoNgay.requestFocus();
            return false;
        } else {
            layoutSoNgay.setError(null);
            songay = Integer.parseInt(strSoNgay);
        }
        sotien = songay * dongia;

        buasang = chkBuaSang.isChecked();
        xeduadon = chkXeDuaDon.isChecked();

        String strGiamGia = edtMaGiamGia.getText().toString().trim();

        if(strGiamGia.equals("ctu")) {
            if (xeduadon && buasang) {
                sotien = ((sotien + songay * 30 + 50) * 90) / 100;
                edtSoTien.setText("Số tiền: " + sotien);
            } else if (buasang) {
                sotien = ((sotien + songay * 30) * 90) / 100;
                edtSoTien.setText("Số tiền: " + sotien);
            } else if (xeduadon) {
                sotien = ((sotien + 50) * 90) / 100;;
                edtSoTien.setText("Số tiền: " + sotien);
            } else
                edtSoTien.setText("Số tiền: " + sotien * 90 /100);
        }else {

            if (xeduadon && buasang) {
                sotien = sotien + songay * 30 + 50;
                edtSoTien.setText("Số tiền: " + sotien);
            } else if (buasang) {
                sotien = sotien + songay * 30;
                edtSoTien.setText("Số tiền: " + sotien);
            } else if (xeduadon) {
                sotien = sotien + 50;
                edtSoTien.setText("Số tiền: " + sotien);
            }
            else
                edtSoTien.setText("Số tiền: " + sotien);
        }


        return true;
    }
    public void DatThue(View view) {
        if (!TinhTien(view))
            return;
        Bundle bundleGoi = new Bundle();
        bundleGoi.putString("DiaDiem", strDiaDiem);
        bundleGoi.putString("LoaiPhong", strLoaiPhong);
        bundleGoi.putInt("DonGia", dongia);
        bundleGoi.putString("NgayBD", strNgayBD);
        bundleGoi.putInt("SoNgay", songay);
        bundleGoi.putBoolean("BuaSang", buasang);
        bundleGoi.putBoolean("XeDuaDon", xeduadon);
        bundleGoi.putInt("SoTien", sotien);
        Intent intent = new Intent(this, XacNhanThuePhong.class);
        intent.putExtras(bundleGoi);
        startActivity(intent);
    }
    public void DongActivity(View view) {
        finish();
    }
}