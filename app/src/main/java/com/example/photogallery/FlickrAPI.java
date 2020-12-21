package com.example.photogallery;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickrAPI {
    @GET("services/rest/?method=flickr.photos.getRecent&api_key=30f73b5de236fe4fcd78f3b6c91e8ad2&extras=url_s&per_page=2&page=2&format=json&nojsoncallback=1")
            Call<PhotosResponse> getRecent();
            @GET("services/rest/?method=flickr.photos.search&api_key=30f73b5de236fe4fcd78f3b6c91e8ad2&extras=url_s&per_page=2&page=2&format=json&nojsoncallback=1")
            Call<PhotosResponse> getSearchPhotos(@Query("text") String keyWord);

}
