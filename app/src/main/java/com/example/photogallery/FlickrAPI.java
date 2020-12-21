package com.example.photogallery;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickrAPI {
    @GET("services/rest/?method=flickr.photos.getRecent&api_key=32a7a4891834457882ba9e7e56814fb8&extras=url_s&per_page=3&page=1&format=json&nojsoncallback=1")
            Call<PhotosResponse> getRecent();
            @GET("services/rest/?method=flickr.photos.search&api_key=32a7a4891834457882ba9e7e56814fb8&extras=url_s&per_page=3&page=1&format=json&nojsoncallback=1")
            Call<PhotosResponse> getSearchPhotos(@Query("text") String keyWord);

}
