package com.rafaelmoraes.structural.proxy.example01;

public class Main {
    static void main(){
        Imagem img = new ProxyImagem("13-01-2026_teste.jpg");

        img.exibir();

    }
}
