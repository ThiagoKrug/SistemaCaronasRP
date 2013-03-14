package com.model.entity;

/**
 *
 * @author thiago
 */
public class Evento {
    
    private static String[] cores = new String[]{
        "blue",
        "green",
        "red",
        "orange",
        "gray",
        "purple",
        "darkgreen",
        "darkcyan",
        "black",
        "indigo",
        "brown",
        "olive"
    };
    private static int i = 0;
    
    private String titulo;
    private String inicio;
    private String fim;
    private String cor;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFim() {
        return fim;
    }

    public void setFim(String fim) {
        this.fim = fim;
    }
    
    public static String getCores() {
        if (cores.length == i) {
            i = 0;
        }
        String cor = cores[i];
        i++;
        return cor;
    }

    /**
     * @return the cor
     */
    public String getCor() {
        return cor;
    }

    /**
     * @param cor the cor to set
     */
    public void setCor(String cor) {
        this.cor = cor;
    }
    
}
