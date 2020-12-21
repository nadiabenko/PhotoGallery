package com.example.photogallery;

public class PhotosResponse {
    private PhotoPage photos;
    private String stat;

    public PhotoPage getPhotos() {
        return photos;
    }

    public void setPhotos(PhotoPage photos) {
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
