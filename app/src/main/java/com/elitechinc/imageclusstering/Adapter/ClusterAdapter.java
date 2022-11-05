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

public class ClusterAdapter extends FirebaseRecyclerAdapter<Cluster, ClusterAdapter.clusterViewholder> {
    public ClusterAdapter(@NonNull FirebaseRecyclerOptions<Cluster> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull clusterViewholder holder, int position, @NonNull Cluster model) {
        Picasso.get().load(model.getImgurl()).into(holder.img);
        holder.name.setText(model.getCluster());
        String keybro = getRef(position).getKey();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), imagesActivity.class);
                intent.putExtra("key", keybro);
                intent.putExtra("cluster", model.getCluster());
                intent.putExtra("url", model.getImgurl());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public clusterViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend, parent, false);
        return new ClusterAdapter.clusterViewholder(view);
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
