package com.example.soccernews.ui.favorites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FavoritesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FavoritesViewModel() {
        mText = new MutableLiveData<>(); // Instância o fragmento
        mText.setValue( "This is favorites fragment" ); // O que é apresentado no fragmento
    }

    public LiveData<String> getText() {
        return mText;
    }
}