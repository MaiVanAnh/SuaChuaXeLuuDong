package vn.edu.vananh.suachuaxeluudong;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import vn.edu.vananh.suachuaxeluudong.function.ChucNangPhu;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EditText txtTaiKhoan, txtMatKhau;
    private TextView txtDangKi, txtQuenMK;
    private Button btnDangNhap;
    Intent intent;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {


        txtDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, DangKiActivity.class);
                startActivity(intent);
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignIn();
            }
        });



    }

    private void startSignIn() {

        String taiKhoan = txtTaiKhoan.getText().toString();
        String matKhau  = txtMatKhau.getText().toString();

        if (!taiKhoan.isEmpty() && !matKhau.isEmpty()) {
            if (ChucNangPhu.isEmailValid(taiKhoan) && matKhau.length() >= 6) {
                mAuth.signInWithEmailAndPassword(taiKhoan, matKhau).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isComplete()) {
                            // Sign in success go to new activity
                            Log.d(TAG, "signInWithEmail: success");
                            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                            startActivity(intent);
                        } else {
                            // sign in fail

                            Toast.makeText(MainActivity.this, getString(R.string.saiTaiKhoanOrMk), Toast.LENGTH_SHORT).show();
                            Log.w(TAG, "signInWithEmail: failure", task.getException());

                        }
                    }
                });
            } else {
                Toast.makeText(this, getString(R.string.saiTaiKhoanOrSaiMK), Toast.LENGTH_SHORT).show();
            }


        } else {

            if (taiKhoan.isEmpty() && matKhau.isEmpty()) {
                Toast.makeText(this, getString(R.string.kiemTraThongTin), Toast.LENGTH_SHORT).show();
                txtTaiKhoan.requestFocus();
            } else if (!taiKhoan.isEmpty() && matKhau.isEmpty()) {
                Toast.makeText(this, getString(R.string.chuaNhapMatKhau), Toast.LENGTH_SHORT).show();
                txtMatKhau.requestFocus();
            } else if (taiKhoan.isEmpty() && !matKhau.isEmpty()) {
                Toast.makeText(this, getString(R.string.chuaNhapTaiKhoan), Toast.LENGTH_SHORT).show();
                txtTaiKhoan.requestFocus();
            }
        }
    }

    private void addControls() {
        txtTaiKhoan = findViewById(R.id.txtTaiKhoan);
        txtMatKhau = findViewById(R.id.txtMatKhau);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        txtDangKi = findViewById(R.id.txtDangKi);
        txtQuenMK = findViewById(R.id.txtQuenMatKhau);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
}
