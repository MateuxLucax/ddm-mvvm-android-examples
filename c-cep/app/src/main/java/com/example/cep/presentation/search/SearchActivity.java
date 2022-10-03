package com.example.cep.presentation.search;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.cep.R;
import com.example.cep.data.search.datasource.remote.RetrofitSearchDatasource;
import com.example.cep.data.search.repository.SearchCepRepository;
import com.example.cep.databinding.ActivitySearchBinding;
import com.example.cep.domain.usecase.search.SearchCepUseCase;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SearchCepViewModel viewModel = new SearchCepViewModel().setUseCase(
                new SearchCepUseCase(
                        new SearchCepRepository(
                                new RetrofitSearchDatasource()
                        )
                )
        );

        ActivitySearchBinding bind = DataBindingUtil.setContentView(this, R.layout.activity_search);
        bind.setVm(viewModel);
        bind.executePendingBindings();
    }
}