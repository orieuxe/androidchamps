package android.eservices.webrequests.presentation.viewmodel;

import android.eservices.webrequests.data.api.model.BookSearchResponse;
import android.eservices.webrequests.data.repository.bookdisplay.BookDisplayRepository;
import android.eservices.webrequests.presentation.bookdisplay.search.adapter.BookItemViewModel;
import android.eservices.webrequests.presentation.bookdisplay.search.mapper.BookToViewModelMapper;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class BookSearchViewModel extends ViewModel {
    private BookDisplayRepository bookDisplayRepository;
    private CompositeDisposable compositeDisposable;
    private BookToViewModelMapper bookToViewModelMapper;

    public BookSearchViewModel(BookDisplayRepository bookDisplayRepository) {
        this.bookDisplayRepository = bookDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.bookToViewModelMapper = new BookToViewModelMapper();
    }

    private MutableLiveData<List<BookItemViewModel>> books = new MutableLiveData<List<BookItemViewModel>>();
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<Boolean>();

    public MutableLiveData<List<BookItemViewModel>> getBooks() {
        return books;
    }

    public MutableLiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }

    public void searchBooks(String keywords) {
        isDataLoading.postValue(true);
        compositeDisposable.clear();
        compositeDisposable.add(bookDisplayRepository.getBookSearchResponse(keywords)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<BookSearchResponse>() {

                    @Override
                    public void onSuccess(BookSearchResponse bookSearchResponse) {
                        books.setValue(bookToViewModelMapper.map(bookSearchResponse.getBookList()));
                        isDataLoading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                        //Yet, do not do nothing in this app
                        System.out.println(e.toString());
                        isDataLoading.setValue(false);
                    }
                }));
    }

    public void cancelSubscription() {
        compositeDisposable.clear();
        isDataLoading.setValue(false);
    }

}/*
    @Override
    public void addBookToFavorite(String bookId) {
        compositeDisposable.add(bookDisplayRepository.addBookToFavorites(bookId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        view.onBookAddedToFavorites();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    @Override
    public void removeBookFromFavorites(String bookId) {
        compositeDisposable.add(bookDisplayRepository.removeBookFromFavorites(bookId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        view.onBookRemovedFromFavorites();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }
*/