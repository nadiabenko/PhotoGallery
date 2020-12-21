package com.example.photogallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {
    private List<Photo> list;
    private ImageView image;
    private TextView image_name;
    PhotosDao photoDao;
    Context context;
    RecyclerView recyclerView;

    public PhotoAdapter(List<Photo> items, Context contexts, PhotosDao photoD, RecyclerView recyclerVieww) {
        list = items;
        this.context = contexts;
        photoDao=photoD;
        recyclerView=recyclerVieww;
    }

    public class PhotoHolder extends RecyclerView.ViewHolder{
        public PhotoHolder(View item){
            super(item);

            image = item.findViewById(R.id.photo_view);
            image_name = item.findViewById(R.id.kay_photo);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View activ) {

                    int itemPos= recyclerView.getChildLayoutPosition(activ);

                    Photo photo = list.get(itemPos);
                    photoDao.insertPhoto(photo);
                }
            });
        }
    }

    @NonNull
    @Override
    public PhotoAdapter.PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item,parent,false);
        return new PhotoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoHolder holder, int position) {


        String text = list.get(position).getOwner();
        image_name.setText(text);
        Picasso.with(context).load(list.get(position).getUrl_s()).into(image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
