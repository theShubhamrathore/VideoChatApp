package com.example.srvideocall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class signup extends AppCompatActivity {

    FirebaseAuth auth;
    EditText emailbox,passworbox,namebox;
    Button loginbtn,createbtn;

    FirebaseFirestore database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();

        database = FirebaseFirestore.getInstance();

        auth = FirebaseAuth.getInstance();

        emailbox = findViewById(R.id.emailbox);
        passworbox = findViewById(R.id.passwordbox);
        loginbtn = findViewById(R.id.loginbtn);
        createbtn = findViewById(R.id.createbtn);
        namebox = findViewById(R.id.namebox);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(signup.this,loginactivity.class));

            }
        });


        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,pass,name;

                email = emailbox.getText().toString();
                pass = passworbox.getText().toString();
                name = namebox.getText().toString();
                users users = new users();
                users.setEmail(email);
                users.setPass(pass);
                users.setName(name);



               auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){

                        database.collection("Users")
                                .document().set(users).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                startActivity(new Intent(signup.this,loginactivity.class));
                            }
                        });

                        Toast.makeText(signup.this, "Account is Create Successful", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(signup.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }

                   }
               }) ;


            }
        });



    }
}