package com.example.moviemania;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Movie> movieList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        fetchMovies();

    }

    private void fetchMovies() {

        String url = "http://localhost:3000/movies";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i=0; i<response.length(); i++){
                    try {
                        JSONObject movie = response.getJSONObject(i);
                        String title = movie.getString("title");
                        String poster = movie.getString("poster");
                        Double rating = movie.getDouble("rating");
                        String overview = movie.getString("overview");

                        Movie movies = new Movie(title, poster, overview, rating);
                        movieList.add(movies);
                }
                    catch (Exception e){
                        e.printStackTrace();
                    }

                    MovieAdapter movieAdapter = new MovieAdapter(MainActivity.this, movieList);
                    recyclerView.setAdapter(movieAdapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
        }
        );

        requestQueue.add(jsonArrayRequest);
}}