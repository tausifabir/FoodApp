package com.example.foodapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;

    private TextView Sign_up, Forgot_pass;
    private EditText emailET, passET;
    private ImageView sign_inBtn;

    private String email,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Sign_up = findViewById(R.id.signupTV);
        Forgot_pass = findViewById(R.id.forgotTV);
        sign_inBtn = findViewById(R.id.signinBtn);

        emailET = findViewById(R.id.emailET);
        passET = findViewById(R.id.passET);


        SpannableString content = new SpannableString("Sign in");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        Sign_up.setText(content);

        SpannableString content1 = new SpannableString("ForgotPassword");
        content1.setSpan(new UnderlineSpan(), 0, content.length(), 1);
        Forgot_pass.setText(content1);


        mAuth = FirebaseAuth.getInstance();




    }

    @Override
    protected void onResume() {
        super.onResume();

        Sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });



        sign_inBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = emailET.getText().toString();
                pass = passET.getText().toString();
                mAuth.signInWithEmailAndPassword(email,pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    firebaseUser = mAuth.getCurrentUser();
                                    Toast.makeText(LoginActivity.this, "Signin Successfully", Toast.LENGTH_SHORT).show();
                                } }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                firebaseUser.sendEmailVerification()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "123456", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }






}