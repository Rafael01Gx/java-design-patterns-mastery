package com.rafaelmoraes.structural.proxy.example01;

public class ImagemPesada implements Imagem{

    private String arquivo;
    public ImagemPesada(String arquivo) {
        this.arquivo = arquivo;
        carregarDoDisco();
    }

    @Override
    public void exibir() {
        IO.println("Exibindo imagem com arquivo: " + this.arquivo);
    }

    public void carregarDoDisco(){
        IO.println("Carregando imagem com arquivo: " + this.arquivo);
    }
}
