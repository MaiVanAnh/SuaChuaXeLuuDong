package vn.edu.vananh.suachuaxeluudong;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import vn.edu.vananh.suachuaxeluudong.function.ChucNangPhu;

public class DangKiActivity extends AppCompatActivity {

    private static final String TAG = "DangKiActivity";

    private EditText txtTaiKhoan;
    private EditText txtMatKhau;
    private EditText txtXacNhanMk;
    private EditText txtTen;
    private EditText txtDiaChi;
    private EditText txtSdt;
    private RadioGroup radioNgheNghiepGroup;
    private RadioButton radioNgheNghiepButton;
    private Button btnDangKi;
    private Button btnTroLai;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangki);
        mAuth = FirebaseAuth.getInstance();

        addControls();
        addSetup();
        addEvents();

    }

    private void addSetup() {

    }

    private void addEvents() {
        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangKi();
            }
        });

        btnTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void dangKi() {
        String notify = getResources().getString(R.string.thieuThongTin);
        Boolean isThoSuaXe;
        String taiKhoan = txtTaiKhoan.getText().toString();
        String matKhau = txtMatKhau.getText().toString();
        String xacNhanMK = txtXacNhanMk.getText().toString();
        String ten = txtTen.getText().toString();
        String diaChi = txtDiaChi.getText().toString();
        String sdt = txtSdt.getText().toString();

        int selectId = radioNgheNghiepGroup.getCheckedRadioButtonId();
        radioNgheNghiepButton = (RadioButton) findViewById(selectId);
        if (selectId == R.id.radioThosuaxe) {
            isThoSuaXe = true;
        } else {
            isThoSuaXe = false;
        }

        if (!taiKhoan.isEmpty() && !matKhau.isEmpty() && !xacNhanMK.isEmpty() && !ten.isEmpty() && !diaChi.isEmpty() && !sdt.isEmpty()) {

           if (ChucNangPhu.isEmailValid(taiKhoan)){
               Toast.makeText(this, getString(R.string.taiKhoanKhongHopLe), Toast.LENGTH_SHORT).show();
               txtTaiKhoan.requestFocus();
           } else {
               if (matKhau != xacNhanMK){
                   Toast.makeText(this, getResources().getString(R.string.matKhauKhongKhop), Toast.LENGTH_SHORT).show();
                   txtXacNhanMk.setText("");
                   txtXacNhanMk.requestFocus();
               }
               else {
                   // tạo tài khoản bên email và tạo thông tin bên database
                   taoTaiKhoan(taiKhoan, matKhau, ten, diaChi, sdt, isThoSuaXe);
               }
           }
        } else {
            // request focus to empty textfield
            Toast.makeText(this,notify, Toast.LENGTH_SHORT).show();
        }

    }

    private void taoTaiKhoan(String taiKhoan, String matKhau, String ten, String diaChi, String sdt, Boolean isThoSuaXe) {

        mAuth.createUserWithEmailAndPassword(taiKhoan, matKhau).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    String uuid = user.getUid();

                    // save user's infor data into realtime database

                    Intent intent = new Intent(DangKiActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


    private void addControls() {
        txtTaiKhoan = findViewById(R.id.txtTaiKhoan);
        txtMatKhau = findViewById(R.id.txtMatKhau);
        txtXacNhanMk = findViewById(R.id.txtXacnhanMk);
        txtTen = findViewById(R.id.txtTen);
        txtDiaChi = findViewById(R.id.txtTen);
        txtSdt = findViewById(R.id.txtSdt);
        radioNgheNghiepGroup = findViewById(R.id.radioNgheNghiepGroup);


        btnDangKi = findViewById(R.id.btnDangki);
        btnTroLai = findViewById(R.id.btnTrolai);
    }

}
