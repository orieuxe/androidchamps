package android.eservices.webrequests.data.api.model;

public class Book {

    private String id;
    private BookInfo volumeInfo;
    private boolean isFavorite;

    public String getId() {
        return id;
    }

    public BookInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setFavorite() {
        isFavorite = true;
    }

    public boolean isFavorite() {
        return isFavorite;
    }
}
