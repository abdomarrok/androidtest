package com.marrok.myapplication;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String URL_API_Test="https://6ff5-105-235-134-190.ngrok-free.app/";
    TextView register,forgot_pass;
    private EditText username,password;
    private Button signInBtn;
    private ProgressBar progressBar;
    Retrofit retrofit;

    private void initView() {
        forgot_pass=findViewById(R.id.Forgot_pass);
        signInBtn=findViewById(R.id.btnLogin);
        username=findViewById(R.id.rectangle4_username_login);
        password=findViewById(R.id.rectangle4_password_login);
        register=findViewById(R.id.Register_txt);
        progressBar=findViewById(R.id.progress_bar);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        progressBar.setVisibility(View.GONE);
        setOnClickListner();



    }

    private void setOnClickListner() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RegistrationActivity.class));
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogIn();
            }
        });

       
    }

    private void userLogIn() {
        String mail=username.getText().toString().trim();
        String pass=password.getText().toString().trim();

        if(pass.length()<6){
            password.setError("min pass length is 6");
            password.requestFocus();
            return;

        }

        if(mail.isEmpty()){
            username.setError("username is required");
            username.requestFocus();
            return;
        }

        if(pass.isEmpty()){
            password.setError("password is required");
            password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        retrofit = new  Retrofit.Builder()
                .baseUrl(URL_API_Test)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        RetrofitClient retrofitClient = retrofit.create(RetrofitClient.class);

        User user = new User(mail, pass);


        Call<User> loginUser= retrofitClient.LoginUser(user);

        loginUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d(TAG, "onResponse: " + response);

                if (response.isSuccessful()) {
                    User userResponse = response.body();

                    // You can now use the token and user details as needed
                    Log.d(TAG, "Token: " + userResponse.getToken());
                    Log.d(TAG, "User ID: " + userResponse.getUser().getId());
                    Log.d(TAG, "Username: " + userResponse.getUser().getUsername());
                    Log.d(TAG, "Email: " + userResponse.getUser().getEmail());

                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                } else {
                    // Handle the case when the response is not successful (e.g., invalid credentials)
                    Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse: login failed "+response.body());
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
                Toast.makeText(MainActivity.this, "failed connection problem", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);

            }
        });



       /* Call<User> createUser = retrofitClient.SignUpNew(user);
        createUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                int responseCode = response.code();
                Log.d("TAG", "onResponse: Signup response code: " + responseCode);

                if (response.isSuccessful()) {
                    // Request successful
                    User user = response.body();
                    Log.d("TAG", "onResponse: Signup successful " + user);
                } else {
                    // Request failed
                    String errorMessage = response.message();
                    Log.d("TAG", "onResponse: Signup failed: " + errorMessage);
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("TAG", " onFailure Signup failed: " + t.getMessage());
            }
        });*/





    }


}
