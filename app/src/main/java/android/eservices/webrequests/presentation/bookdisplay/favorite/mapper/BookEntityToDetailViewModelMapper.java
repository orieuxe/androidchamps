package android.eservices.webrequests.presentation.bookdisplay.favorite.mapper;

import android.eservices.webrequests.data.entity.BookEntity;
import android.eservices.webrequests.presentation.bookdisplay.favorite.adapter.BookDetailViewModel;
import android.text.Html;

import java.util.ArrayList;
import java.util.List;

public class BookEntityToDetailViewModelMapper {

    private BookDetailViewModel map(BookEntity bookEntity) {
        BookDetailViewModel bookItemViewModel = new BookDetailViewModel();
        bookItemViewModel.setBookTitle(bookEntity.getTitle());
        bookItemViewModel.setBookId(bookEntity.getId());
        bookItemViewModel.setIconUrl(bookEntity.getThumbUrl());
        if (bookEntity.getDescription() != null) {
            bookItemViewModel.setBookDescription(Html.fromHtml(bookEntity.getDescription()).toString());
        }
        bookItemViewModel.setBookAuthors(bookEntity.getAuthors());
        //TODO improve display ...
        bookItemViewModel.setBookPublishedDate("Published in " + bookEntity.getPublishedDate());

        String language = "This book is in " + languageMapper(bookEntity.getLanguage());
        bookItemViewModel.setBookLanguage(language);
        return bookItemViewModel;
    }

    public List<BookDetailViewModel> map(List<BookEntity> bookList) {
        List<BookDetailViewModel> bookItemViewModelList = new ArrayList<>();
        for (BookEntity book : bookList) {
            bookItemViewModelList.add(map(book));
        }
        return bookItemViewModelList;
    }

    private String languageMapper(String input) {
        switch (input) {
            case "en":
                return "English";
            case "fr":
                return "French";
            default:
                return "Unknown (" + input + ")";
        }
    }
}
