package com.example.soccernews.ui.news;

import static com.example.soccernews.ui.news.NewsViewModel.State.DOING;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.soccernews.MainActivity;
import com.example.soccernews.R;
import com.example.soccernews.databinding.FragmentNewsBinding;
import com.example.soccernews.ui.adapter.NewsAdapter;
import com.google.android.material.snackbar.Snackbar;

public class NewsFragment extends Fragment {

    //NewsFragment responsável apenas pela UI
    private FragmentNewsBinding binding;
    private NewsViewModel newsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        newsViewModel = new ViewModelProvider( this ).get( NewsViewModel.class );

        binding = FragmentNewsBinding.inflate( inflater, container, false );
        View root = binding.getRoot();

        binding.rvNews.setLayoutManager( new LinearLayoutManager( getContext() ) );

        observeNews();
        observeStates();

        binding.srlNews.setOnRefreshListener( newsViewModel::findNews );//Chama o findNews para atualizar

        return root;
    }

    private void observeNews() {//Observa as noticias
        newsViewModel.getNews().observe( getViewLifecycleOwner(), news -> {
            binding.rvNews.setAdapter( new NewsAdapter( news, newsViewModel::saveNews ) );
        } );
    }

    private void observeStates() {//Observa os status
        newsViewModel.getState().observe( getViewLifecycleOwner(), state -> {
            switch (state) {
                case DOING:
                    binding.srlNews.setRefreshing( true );
                    break;
                case DONE:
                    binding.srlNews.setRefreshing( false );
                    break;
                case ERROR:
                    binding.srlNews.setRefreshing( false );
                    Snackbar.make( binding.srlNews, R.string.error_network, Snackbar.LENGTH_SHORT ).show();
            }
        } );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
