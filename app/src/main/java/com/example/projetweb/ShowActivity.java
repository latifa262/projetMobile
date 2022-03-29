package com.example.projetweb;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projetweb.adapter.myAdapter;
import com.example.projetweb.beans.Etudiant;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

public class ShowActivity extends AppCompatActivity {
    private static final String TAG = "RecycleActivity";
    private RecyclerView recycle;
    RequestQueue requestQueue;
    String loadUrl = "http://10.0.2.2/projet/ws/loadEtudiant.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        recycle = findViewById(R.id.recycle);


        loadData();
    }

    public void loadData() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST,
                loadUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response);
                Type type = new TypeToken<Collection<Etudiant>>() {
                }.getType();
                Collection<Etudiant> etudiants = new Gson().fromJson(response, type);
                recycle.setAdapter(new myAdapter(ShowActivity.this, (List<Etudiant>) etudiants));
                recycle.setLayoutManager(new LinearLayoutManager(ShowActivity.this));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(request);
    }
}
