package com.elitechinc.imageclusstering.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elitechinc.imageclusstering.Classes.Cluster;
import com.elitechinc.imageclusstering.R;
import com.elitechinc.imageclusstering.imagesActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class AllClusterAdapter extends FirebaseRecyclerAdapter<Cluster, AllClusterAdapter.clusterViewholder> {
    public AllClusterAdapter(@NonNull FirebaseRecyclerOptions<Cluster> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull clusterViewholder holder, int position, @NonNull Cluster model) {
        Picasso.get().load(model.getImgurl()).into(holder.img);
        holder.name.setText(model.getCluster());
        String keybro = getRef(position).getKey();
    }

    @NonNull
    @Override
    public clusterViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.allcluster, parent, false);
        return new AllClusterAdapter.clusterViewholder(view);
    }

    public class clusterViewholder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView img;

        public clusterViewholder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgRecom);
            name = itemView.findViewById(R.id.nameRecom);
        }
    }
}
