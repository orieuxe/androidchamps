package android.eservices.webrequests.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookSearchResponse {

    @SerializedName("items")
    List<Book> bookList;

    int totalItems;

    public List<Book> getBookList() {
        return bookList;
    }

    public int getTotalItems() {
        return totalItems;
    }


}
