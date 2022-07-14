package com.example.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.soccernews.domain.News;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends ViewModel {

    private MutableLiveData<List<News>> news;

    public NewsViewModel() {
        this.news = new MutableLiveData<>();

        //TODO Remover Mock de notícias
        List<News> news = new ArrayList<>();
        news.add(new News( "Ferroviária tem um desfalque importante",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's " ));
        news.add(new News( "Ferrinha Joga no Sábado",
                "It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged" ));
        news.add(new News( "Copa do Mundo Feminina Está Terminando",
                "It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passagesu"));
        this.news.setValue( news );
    }

    public LiveData<List<News>> getNews() {
        return news;
    }
}