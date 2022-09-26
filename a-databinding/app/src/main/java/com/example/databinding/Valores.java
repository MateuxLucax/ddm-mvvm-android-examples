package com.example.databinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class Valores extends BaseObservable {

    private String texto;

    public Valores(String textoInicial) {
        setTexto(textoInicial);
    }

    @Bindable
    public String getTexto() {
        return texto;
    }

    @Bindable
    public String getNumeroPalavras() {
        String trimmed = texto.trim();
        if (trimmed.isEmpty()) {
            return "Não há palavras";
        }
        int num = trimmed.split("\\s+").length;
        return "Há " + num + " palavra" + (num > 1 ? "s" : "");
    }

    public void setTexto(String texto) {
        this.texto = texto;
        notifyPropertyChanged(BR.texto);
        notifyPropertyChanged(BR.numeroPalavras);
    }
}
