package android.eservices.webrequests.presentation.viewmodel;

import android.eservices.webrequests.data.entity.BookEntity;
import android.eservices.webrequests.data.repository.bookdisplay.BookDisplayRepository;
import android.eservices.webrequests.presentation.bookdisplay.favorite.adapter.BookDetailViewModel;
import android.eservices.webrequests.presentation.bookdisplay.favorite.mapper.BookEntityToDetailViewModelMapper;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class BookFavoriteViewModel extends ViewModel {

    private BookDisplayRepository bookDisplayRepository;
    private CompositeDisposable compositeDisposable;
    private BookEntityToDetailViewModelMapper bookEntityToDetailViewModelMapper;

    public BookFavoriteViewModel(BookDisplayRepository bookDisplayRepository) {
        this.bookDisplayRepository = bookDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.bookEntityToDetailViewModelMapper = new BookEntityToDetailViewModelMapper();
    }

    private MutableLiveData<List<BookDetailViewModel>> favorites;
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<Boolean>();
    final MutableLiveData<Event<String>> bookAddedEvent = new MutableLiveData<Event<String>>();
    final MutableLiveData<Event<String>> bookDeletedEvent = new MutableLiveData<Event<String>>();

    public MutableLiveData<Event<String>> getBookAddedEvent() {
        return bookAddedEvent;
    }

    public MutableLiveData<Event<String>> getBookDeletedEvent() {
        return bookDeletedEvent;
    }

    public MutableLiveData<List<BookDetailViewModel>> getFavorites() {
        isDataLoading.setValue(true);
        if (favorites == null) {
            favorites = new MutableLiveData<List<BookDetailViewModel>>();
            compositeDisposable.add(bookDisplayRepository.getFavoriteBooks()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new ResourceSubscriber<List<BookEntity>>() {

                        @Override
                        public void onNext(List<BookEntity> bookEntityList) {
                            isDataLoading.setValue(false);
                            favorites.setValue(bookEntityToDetailViewModelMapper.map(bookEntityList));
                            System.out.println("BIND FAVORITES");
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            isDataLoading.setValue(false);
                        }

                        @Override
                        public void onComplete() {
                            //Do Nothing
                            isDataLoading.setValue(false);
                        }
                    }));

        }
        return favorites;
    }

    public MutableLiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }

    public void addBookToFavorite(final String bookId) {
        compositeDisposable.add(bookDisplayRepository.addBookToFavorites(bookId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        bookAddedEvent.setValue(new Event<String>(bookId));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    public void removeBookFromFavorites(final String bookId) {
        compositeDisposable.add(bookDisplayRepository.removeBookFromFavorites(bookId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        bookDeletedEvent.setValue(new Event<String>(bookId));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }
}