package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.registration.StoreDatabase.COLUMN_EMAIL;
import static com.example.registration.StoreDatabase.COLUMN_PASSWORD;
import static com.example.registration.StoreDatabase.TABLE_USER;

public class Login extends AppCompatActivity implements View.OnClickListener{
    EditText etEmail , etPassword;
    Button btn_LoginToEnter;

    StoreDatabase storeDatabase;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }
    public void initViews(){
        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);

        btn_LoginToEnter=findViewById(R.id.btn_LoginToEnter);
        btn_LoginToEnter.setOnClickListener(this);

        storeDatabase = new StoreDatabase(this);
        sqLiteDatabase = storeDatabase.getWritableDatabase();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_LoginToEnter :
                String userEmail = etEmail.getText().toString();
                String userPassword = etPassword.getText().toString();
                boolean LoginAccount = true;
                if(etEmail.getText().toString().isEmpty()){
                    etEmail.setError("Try again");
                    LoginAccount = false;
                }
                if(etPassword.getText().toString().isEmpty()){
                    etPassword.setError("Try again");
                    LoginAccount = false;
                }
                if(LoginAccount){
                    Cursor LoginCursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_USER + " WHERE "+COLUMN_EMAIL + "=? AND "+COLUMN_PASSWORD + "=?",new String[]{userEmail,userPassword});
                    if(LoginCursor!=null && LoginCursor.getCount()>0){
                        LoginCursor.moveToFirst();
                        Toast.makeText(this, "Welcome! "+userEmail, Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
                    }
                    /*if(userEmail=="Baglan04@gamil.com"&&userPassword=="123456789"){
                        Intent intent = new Intent(Login.this,Admin.class);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(Login.this,Homepage.class);
                        startActivity(intent);
                    }*/

                }
                break;






        }

    }
}
