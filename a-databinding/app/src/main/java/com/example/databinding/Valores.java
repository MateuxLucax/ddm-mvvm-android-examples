package com.example.databinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.util.regex.PatternSyntaxException;

public class Valores extends BaseObservable {

    private String texto;
    private String numeroPalavras;

    public Valores(String textoInicial) {
        setTexto(textoInicial);
    }

    @Bindable
    public String getTexto() {
        return texto;
    }

    @Bindable
    public String getNumeroPalavras() {
        return this.numeroPalavras;
    }

    @Bindable
    public String getTextoUpper() {
        return texto.toUpperCase();
    }

    public void setTexto(String texto) {
        this.texto = texto;
        this.numeroPalavras = "" + texto.trim().split("\\s+").length;
        notifyPropertyChanged(BR.texto);
        notifyPropertyChanged(BR.textoUpper);
        notifyPropertyChanged(BR.numeroPalavras);
    }
}
