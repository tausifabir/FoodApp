package com.example.foodapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;


    private EditText emailET, passET,phoneET;
    private Button registerBtn;


    private String email,pass,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailET = findViewById(R.id.emailET);
        passET = findViewById(R.id.passET);
        phoneET = findViewById(R.id.phoneET);
        registerBtn = findViewById(R.id.registerBtn);

        mAuth = FirebaseAuth.getInstance();
    }

    public void registerNewUser(View view) {



            email = emailET.getText().toString();
            pass = passET.getText().toString();
            phone = phoneET.getText().toString();

            Intent intent = new Intent(RegisterActivity.this,VerifyCodeActivity.class)
                    .putExtra("email",email)
                    .putExtra("pass",pass)
                    .putExtra("phone",phone);
            startActivity(intent);







       /* mAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            firebaseUser = mAuth.getCurrentUser();
                            Toast.makeText(RegisterActivity.this, "Successfully Signup", Toast.LENGTH_SHORT).show();
                            emailET.setText("");
                            passET.setText("");
                            phoneET.setText("");


                        } }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}