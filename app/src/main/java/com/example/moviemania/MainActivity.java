package com.example.moviemania;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Movie> movieList;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        movieList = new ArrayList<>();
        movieAdapter = new MovieAdapter(MainActivity.this, movieList);
        recyclerView.setAdapter(movieAdapter);

        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        fetchMovies();
    }

    private void fetchMovies() {
        String url = "https://moviefile.onrender.com/movies";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                movieList.clear(); // Clear the list to avoid duplications
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject movie = response.getJSONObject(i);
                        String title = movie.getString("title");
                        String poster = movie.getString("poster");
                        Double rating = movie.getDouble("rating");
                        String overview = movie.getString("overview");

                        Movie movies = new Movie(title, poster, overview, rating);
                        movieList.add(movies);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                movieAdapter.notifyDataSetChanged(); // Notify the adapter of data changes
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Ensure that a non-null string is passed to the Toast
                String errorMessage = error.getMessage() != null ? error.getMessage() : "An error occurred";
                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }
}
