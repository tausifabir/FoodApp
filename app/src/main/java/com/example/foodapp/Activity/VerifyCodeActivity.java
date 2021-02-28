package com.example.foodapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyCodeActivity extends AppCompatActivity {

    private TextView countDownTV;
    private EditText verifyCodeET;

    private String email,pass,phone;

    private Button verifyCodeBtn,resendCodeBtn;

    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;

    private  String mVerificationId;
    private  PhoneAuthProvider.ForceResendingToken mForceResendingToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_code);

        countDownTV = findViewById(R.id.countDownTV);
        verifyCodeBtn = findViewById(R.id.verifyCodeBtn);
        resendCodeBtn = findViewById(R.id.resendCodeBtn);
        verifyCodeET = findViewById(R.id.verifyCodeET);

       Intent intent = getIntent();

       String email = intent.getStringExtra("email");
       String pass = intent.getStringExtra("pass");
       String phone = "01679409720";

        mAuth = FirebaseAuth.getInstance();

       if(!phone.isEmpty()){

          PhoneAuthOptions options =
                   PhoneAuthOptions.newBuilder(mAuth)
                           .setPhoneNumber("+88"+phone)       // Phone number to verify
                           .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                           .setActivity(this)                 // Activity (for callback binding)
                           .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                           .build();
           PhoneAuthProvider.verifyPhoneNumber(options);
           startTimer();


       }else{
           Toast.makeText(this, "Invaild Phone Number", Toast.LENGTH_SHORT).show();
       }
    }

    public void verifyCode(View view) {

    }

    public void resendCode(View view) {

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                    signInWithPhoneAuthCredential(phoneAuthCredential);
                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    Toast.makeText(VerifyCodeActivity.this, ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();


                }

                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);

                    mVerificationId = s;
                    mForceResendingToken = forceResendingToken;

                    String firebaseCode = verifyCodeET.getText().toString();
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, firebaseCode);
                    signInWithPhoneAuthCredential(credential);

                    
                    verifyCodeBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                        }
                    });
                }


            };


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential){

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isComplete()){
                            Toast.makeText(VerifyCodeActivity.this, "Signup with"+email +"Successfully", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(VerifyCodeActivity.this, ""+task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }

                });
    }


    private void startTimer() {
        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {

                countDownTV.setVisibility(View.VISIBLE);

                countDownTV.setText("Send code again in: " + millisUntilFinished / 1000);

            }

            public void onFinish() {

                countDownTV.setVisibility(View.GONE);

                //countTimerText.setText("done!");
            }
        }.start();
    }


}