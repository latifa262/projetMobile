package com.example.projetweb.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.projetweb.R;
import com.example.projetweb.beans.Etudiant;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class myAdapter extends RecyclerView.Adapter<myAdapter.EtudiantViewHolder> {
    private static final String TAG = "myAdapter";
    private List<Etudiant> etudiants;
    private LayoutInflater inflater;
    private Context context;

    public myAdapter(Context context, List<Etudiant> etudiants) {
        this.etudiants = etudiants;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public EtudiantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item, parent, false);
        return new EtudiantViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EtudiantViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.id.setText(etudiants.get(position).getId()+"");
        holder.nom.setText(etudiants.get(position).getNom());
        holder.prenom.setText(etudiants.get(position).getPrenom());
        holder.ville.setText("Ville : " + etudiants.get(position).getVille());
        holder.sexe.setText("Sexe : " + etudiants.get(position).getSexe());
        if(etudiants.get(position).getImg() == null) {
            String link = "android.resource://com.example.projetweb/drawable/avatar";
            Glide
                    .with(context)
                    .load(Uri.parse(link))
                    .apply(RequestOptions.fitCenterTransform())
                    .into(holder.image);
        }else {
            byte[] decodedString = Base64.decode(etudiants.get(position).getImg(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            Glide
                    .with(context)
                    .load(decodedByte)
                    .apply(RequestOptions.fitCenterTransform())
                    .into(holder.image);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return etudiants.size();
    }

    public class EtudiantViewHolder extends RecyclerView.ViewHolder{
        CircleImageView image;
        TextView nom, prenom, ville, sexe, id;
        public EtudiantViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            nom = itemView.findViewById(R.id.nom);
            prenom = itemView.findViewById(R.id.prenom);
            ville = itemView.findViewById(R.id.ville);
            sexe = itemView.findViewById(R.id.sexe);
            id = itemView.findViewById(R.id.idE);
        }
    }
}