package com.example.cep;

import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.InverseMethod;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuscaCepViewModel extends BaseObservable {

    private String cepDigitado = "";
    private Cep cep;
    private String erro;

    public BuscaCepViewModel() { }

    @Bindable
    public String getCepDigitado() {
        return cepDigitado;
    }

    public void setCepDigitado(String cepDigitado) {
        this.cepDigitado = cepDigitado;
        notifyPropertyChanged(BR.cepDigitado);
        notifyPropertyChanged(BR.formatoCepValido);
    }

    @Bindable
    public String getLogradouro() {
        return cep == null ? "" : cep.getLogradouro();
    }

    @Bindable
    public boolean getFormatoCepValido() {
        return cepDigitado != null && cepDigitado.length() == 8;
    }

    @Bindable
    public String getErro() {
        return this.erro;
    }

    @Bindable
    public String getBairro() {
        return cep == null ? "" : cep.getBairro();
    }

    @Bindable
    public String getCidade() {
        return cep == null ? "" : (cep.getLocalidade() + " - " + cep.getUf());
    }

    public void setErro(String erro) {
        this.erro = erro;
        notifyPropertyChanged(BR.erro);
    }

    public void setCep(Cep cep) {
        this.cep = cep;
        notifyPropertyChanged(BR.logradouro);
        notifyPropertyChanged(BR.bairro);
        notifyPropertyChanged(BR.cidade);
        notifyPropertyChanged(BR.cep);
    }

    @Bindable
    public String getCep() {
        return cep == null ? "" : cep.getCep();
    }

    public void buscarCep(View view) {
        setErro(null);

        Handler handler = new Handler();

        CepService cepService = new RetrofitInitializer().getCep();
        cepService.select(this.cepDigitado).enqueue(new Callback<Cep> () {

            public void onResponse(Call<Cep> call, Response<Cep> response) {
                Cep body = response.body();
                if (body == null) {
                    // Não deveria acontecer pois botão só fica enabled com CEP no formato
                    setErro("CEP fora do formato");
                } else {
                    if (body.getErro()) {
                        setErro("CEP inválido");
                    } else {
                        setCep(body);
                    }
                }
            }

            public void onFailure(Call<Cep> call, Throwable t) {
                handler.post(() -> Toast.makeText(view.getContext(), "Erro ("+t.getMessage()+")", Toast.LENGTH_LONG).show());
            }
        });
    }
}
