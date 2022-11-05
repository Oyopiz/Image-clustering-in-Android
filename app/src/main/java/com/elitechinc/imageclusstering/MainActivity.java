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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText email, password;
    Button login;
    DatabaseReference reference;
    FirebaseDatabase rootNode;
    FirebaseAuth mAuth;
    //String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);
        checkuser();
        progressBar = findViewById(R.id.progresslogin);
        login = findViewById(R.id.btnlogin);
        rootNode = FirebaseDatabase.getInstance();
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String emailme = email.getText().toString();
                String passwordme = password.getText().toString();
                if (emailme.isEmpty()) {
                    email.setError("Email");
                    progressBar.setVisibility(View.GONE);
                } else if (passwordme.isEmpty()) {
                    password.setError("Password");
                    progressBar.setVisibility(View.GONE);
                } else if (passwordme.length() < 6) {
                    password.setError("Password too short");
                    progressBar.setVisibility(View.GONE);
                } else {
                    Login(emailme, passwordme);
                }
            }
        });
    }

    private void checkuser() {
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(this, homeActivity.class));
        } else {
            Toast.makeText(MainActivity.this, "Login or create account", Toast.LENGTH_SHORT).show();
        }

    }


    private void Login(String emailme, String passwordme) {
        mAuth.signInWithEmailAndPassword(emailme, passwordme).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    startActivity(new Intent(MainActivity.this, homeActivity.class));
                    Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void reg(View view) {
        startActivity(new Intent(this, registerActivity.class));
    }

    public void forgot(View view) {
        startActivity(new Intent(this, forgotActivity.class));
    }

}
