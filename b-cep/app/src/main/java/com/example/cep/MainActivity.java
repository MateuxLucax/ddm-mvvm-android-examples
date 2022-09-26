package com.example.cep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.Toast;

import com.example.cep.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BuscaCepViewModel vm = new BuscaCepViewModel();

        ActivityMainBinding bind = DataBindingUtil.setContentView(this, R.layout.activity_main);
        bind.setVm(vm);
        bind.executePendingBindings();
    }
}