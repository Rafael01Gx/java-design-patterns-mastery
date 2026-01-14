package com.rafaelmoraes.structural.proxy.example01;

public class ProxyImagem implements Imagem {

    private ImagemPesada imagemPesada;

    private String arquivo;

    public ProxyImagem(String arquivo) {
        this.arquivo = arquivo;
    }

    @Override
    public void exibir(){
        if (this.imagemPesada == null) {
            imagemPesada = new ImagemPesada(this.arquivo);
        }
        imagemPesada.exibir();
    }
}
