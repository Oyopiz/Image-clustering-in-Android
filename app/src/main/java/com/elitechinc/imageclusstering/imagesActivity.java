package com.elitechinc.imageclusstering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RadioGroup;

import com.elitechinc.imageclusstering.Adapter.AllClusterAdapter;
import com.elitechinc.imageclusstering.Adapter.ClusterAdapter;
import com.elitechinc.imageclusstering.Classes.Cluster;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class imagesActivity extends AppCompatActivity {
    String key;
    String cluster;
    String url;
    FirebaseAuth mAuth;
    FirebaseDatabase rootNode;
    AllClusterAdapter adapter;
    DatabaseReference mbase, tenrec;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        key = getIntent().getStringExtra("key");
        cluster = getIntent().getStringExtra("cluster");
        url = getIntent().getStringExtra("url");
        mAuth = FirebaseAuth.getInstance();
        RecyclerView recyclerView = findViewById(R.id.recyclerV);
        rootNode = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mbase = FirebaseDatabase.getInstance().getReference("ALLIMG");
        Query query = mbase.orderByChild("cluster").equalTo(getIntent().getStringExtra("cluster"));
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        FirebaseRecyclerOptions<Cluster> options = new FirebaseRecyclerOptions.Builder<Cluster>().setQuery(query, Cluster.class).build();
        adapter = new AllClusterAdapter(options);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                mAuth.signOut();
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.Post:
                startActivity(new Intent(this, clusterActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onStart() {
        adapter.startListening();
        super.onStart();
    }

    @Override
    public void onStop() {
        adapter.stopListening();
        super.onStop();
    }
}