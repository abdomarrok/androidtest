package com.marrok.myapplication;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrationActivity extends AppCompatActivity {
    public static final String URL_API_Test="https://998a-105-108-104-31.ngrok-free.app/";
    private TextView alreadyHave;
    private EditText userName,password,password2,email;
    private ProgressBar progressBar;
    private Button BtnRegister;
    Retrofit retrofit;

    private void initView() {
         alreadyHave = findViewById(R.id.already_hav);
         email=findViewById(R.id.rectangle4_email);
         userName=findViewById(R.id.rectangle4_username);
        password=findViewById(R.id.rectangle4_password);
        password2=findViewById(R.id.rectangle4_password2);
        progressBar=findViewById(R.id.progress_bar1);
        BtnRegister=findViewById(R.id.btnRegister);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initView();
        progressBar.setVisibility(View.GONE);
        setClicklistener();
    }

    private void registerUser() {
        String mail = email.getText().toString().trim();
        String username = userName.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String pass2 = password2.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            email.setError("pleas provide valid email");
            email.requestFocus();
            return;
        }
        if (pass.length() < 6) {
            password.setError("min pass length is 6");
            password.requestFocus();
            return;

        }

        if (mail.isEmpty()) {
            email.setError("Full Name is required");
            email.requestFocus();
            return;
        }
        if (username.isEmpty()) {
            userName.setError("username is required");
            userName.requestFocus();
            return;
        }
        if (pass.isEmpty()) {
            password.setError("password is required");
            password.requestFocus();
            return;
        }
        if ( !pass.equals(pass2) ) {
            password.setError("كلمة السر غير متطابقة");
            password.requestFocus();
            password2.setError("كلمة السر غير متطابقة");
            password2.requestFocus();
        }

        progressBar.setVisibility(View.VISIBLE);

        retrofit = new  Retrofit.Builder()
                .baseUrl(URL_API_Test)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitClient retrofitClient = retrofit.create(RetrofitClient.class);

        User user = new User(username, pass,mail);

        Call<User> signupUser= retrofitClient.SignUpNew(user);
        signupUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d(TAG, "onResponse: " + response);

                if (response.isSuccessful()) {

                    Toast.makeText(RegistrationActivity.this, "Register successful", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                } else {
                    // Handle the case when the response is not successful (e.g., invalid credentials)
                    Toast.makeText(RegistrationActivity.this, "Register failed failed", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse:rgister failed "+response.body());
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
                Toast.makeText(RegistrationActivity.this, "failed connection problem", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);

            }
        });


    }

        private void setClicklistener () {
            alreadyHave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                }
            });
            BtnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    registerUser();
                }
            });

        }


}