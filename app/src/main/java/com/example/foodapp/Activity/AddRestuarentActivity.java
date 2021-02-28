package com.example.foodapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddRestuarentActivity extends AppCompatActivity {

    private static final String TAG = "AddRestuarentActivity";
    private EditText resNameET,resAddressET,resOpenTimeET,resCloseTimeET,resDescripET;
    private Button resImageBTN;

    private String resName,resAddress,resOpenTime,resCloseTime,resDescrip;

    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restuarent);
        resNameET = findViewById(R.id.resNameET);
        resAddressET = findViewById(R.id.resAddressET);
        resOpenTimeET = findViewById(R.id.resOpenTimeET);
        resCloseTimeET = findViewById(R.id.resCloseTimeET);
        resDescripET = findViewById(R.id.resDescripET);
        resImageBTN = findViewById(R.id.resImageBTN);

    }

    @Override
    protected void onResume() {
        super.onResume();
        resName = resNameET.getText().toString();
        resAddress = resAddressET.getText().toString();
        resOpenTime = resOpenTimeET.getText().toString();
        resCloseTime = resCloseTimeET.getText().toString();
        resDescrip = resDescripET.getText().toString();


        // Create a new user with a first and last name
        final Map<String, Object> restuarents = new HashMap<>();
        restuarents.put("resName", resName);
        restuarents.put("resAddress", resAddress);
        restuarents.put("resOpenTime", resOpenTime);
        restuarents.put("resCloseTime", resCloseTime);
        restuarents.put("resDes", resDescrip);

        resImageBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("users").document("Restuarent")
                        .collection("res").document("resid").set(restuarents)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(AddRestuarentActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            }

                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddRestuarentActivity.this, ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });






    }


}