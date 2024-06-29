package com.example.moviemania;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

   private Context context;
   private List<Movie> moviesList;



    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        moviesList = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Movie movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.rating.setText(movie.getRating().toString());
        holder.overview.setText(movie.getOverview());
        Glide.with(context).load(movie.getPoster()).into(holder.imageView);

    }



    @Override
    public int getItemCount() {
        return moviesList.size();
    }




    public class MovieViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title, rating, overview;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            title = itemView.findViewById(R.id.title_tv);
            rating = itemView.findViewById(R.id.rating);
            overview = itemView.findViewById(R.id.overview_tv);
        }
    }
}
