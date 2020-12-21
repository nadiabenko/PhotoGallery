package com.example.photogallery;
import java.util.List;

public class PhotoPage {
    private Integer page;
    private Integer pages;
    private Integer perpage;
    private Integer total;
    private List<com.example.photogallery.Photo> photo = null;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getPerpage() {
        return perpage;
    }

    public void setPerpage(Integer perpage) {
        this.perpage = perpage;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<com.example.photogallery.Photo> getPhoto() {
        return photo;
    }

    public void setPhoto(List<com.example.photogallery.Photo> photo) {
        this.photo = photo;
    }
}
