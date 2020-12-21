package com.example.photogallery;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class PhotoGallery extends AppCompatActivity {

    PhotoAdapter adapter;
    Context context;
     RecyclerView recyclerView;
    PhotosResponse resps;
    PhotosDao photoDao;
    TextView image_name;
    ImageView image;
    PhotosDB db;
    List<Photo> photos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity);
        recyclerView = findViewById(R.id.RV_DESKTOP);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        Retrofit retrofit = ServiceAPI.getRetrofit();
        context=this;

        db = Room.databaseBuilder(context,PhotosDB.class,"database").allowMainThreadQueries().build();
        photoDao = db.photoDao();

        image_name = findViewById(R.id.kay_photo);
        image = findViewById(R.id.photo_view);

        retrofit.create(FlickrAPI.class).getRecent().enqueue(new Callback<PhotosResponse>() {
            @Override
            public void onResponse(Call<PhotosResponse> call, retrofit2.Response<PhotosResponse> response) {
                resps = response.body();
                List<Photo> photos = resps.getPhotos().getPhoto();
                adapter = new PhotoAdapter(photos,context, photoDao,recyclerView);
                recyclerView.setAdapter(adapter);
                Toast.makeText(com.example.photogallery.PhotoGallery.this, "Ok",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PhotosResponse> call, Throwable t) {
                Toast.makeText(com.example.photogallery.PhotoGallery.this, "Error",Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView)item.getActionView();
        final RecyclerView recyclerView = findViewById(R.id.RV_DESKTOP);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        Retrofit retrofit = ServiceAPI.getRetrofit();
        context=this;

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                retrofit.create(FlickrAPI.class).getSearchPhotos(query).enqueue(new Callback<PhotosResponse>() {
                    @Override
                    public void onResponse(Call<PhotosResponse> call, retrofit2.Response<PhotosResponse> response) {
                        resps = response.body();
                        List<Photo> photos = resps.getPhotos().getPhoto();
                        adapter = new PhotoAdapter(photos,context,photoDao,recyclerView);
                        recyclerView.setAdapter(adapter);
                        Toast.makeText(com.example.photogallery.PhotoGallery.this, "Ok",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<PhotosResponse> call, Throwable t) {
                        Toast.makeText(com.example.photogallery.PhotoGallery.this, "Error",Toast.LENGTH_SHORT).show();
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }


        });
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Retrofit retrofit = ServiceAPI.getRetrofit();
        int id = item.getItemId();
        if(id == R.id.OnliDB){
            retrofit.create(FlickrAPI.class).getRecent().enqueue(new Callback<PhotosResponse>() {
                // retrofit.create(FlicksAPI.class).getSearchPhotos("auto").enqueue(new Callback<PhotosResponse>() {
                @Override
                public void onResponse(Call<PhotosResponse> call, retrofit2.Response<PhotosResponse> response) {
                    resps = response.body();
                    List<Photo> photos = resps.getPhotos().getPhoto();
                    adapter = new PhotoAdapter(photos,context,photoDao,recyclerView);
                    recyclerView.setAdapter(adapter);
                    Toast.makeText(com.example.photogallery.PhotoGallery.this, "Ok",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<PhotosResponse> call, Throwable t) {
                    Toast.makeText(com.example.photogallery.PhotoGallery.this, "Error",Toast.LENGTH_SHORT).show();
                }
            });
        }
        if(id == R.id.LocalDB){
            photos = photoDao.LoadAll();
            adapter = new PhotoAdapter(photos,context,photoDao,recyclerView);
            recyclerView.setAdapter(adapter);
            Toast.makeText(com.example.photogallery.PhotoGallery.this, "Local base",Toast.LENGTH_SHORT).show();
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

}