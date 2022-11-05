package com.elitechinc.imageclusstering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.elitechinc.imageclusstering.Classes.UserHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class registerActivity extends AppCompatActivity {


    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    FirebaseAuth mAuth;
    ProgressBar dialog;
    Button signup;
    EditText email, password, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();
        signup = findViewById(R.id.continueBtn);
        dialog = findViewById(R.id.mainprogress);
        username = findViewById(R.id.name);
        email = findViewById(R.id.emailsin);
        password = findViewById(R.id.passsin);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setVisibility(View.VISIBLE);
                String emailme = email.getText().toString();
                String passwordme = password.getText().toString();
                String usernamememe = username.getText().toString();
                if (emailme.isEmpty()) {
                    email.setError("Email");
                    dialog.setVisibility(View.GONE);
                } else if (passwordme.isEmpty()) {
                    password.setError("Password");
                    dialog.setVisibility(View.GONE);
                } else if (usernamememe.isEmpty()) {
                    username.setError("Username");
                } else {
                    reguser(emailme, passwordme);

                }
            }
        });

    }

    private void reguser(String emailme, String passwordme) {
        auth.createUserWithEmailAndPassword(emailme, passwordme).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String emailme = email.getText().toString();
                    String passwordme = password.getText().toString();
                    String usernamememe = username.getText().toString();
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("UsersML");
                    UserHelper userHelper = new UserHelper(usernamememe, emailme, auth.getCurrentUser().getUid());
                    databaseReference.setValue(userHelper);
                    startActivity(new Intent(registerActivity.this, homeActivity.class));
                    dialog.setVisibility(View.GONE);
                } else {
                    Toast.makeText(registerActivity.this, "Registration failed, try again later", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}