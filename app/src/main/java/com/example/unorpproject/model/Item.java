package com.example.unorpproject.model;

public class Item {

    private String nome;
    private Double watts;
    private Double hrs;
    private Double qtd;
    private Double gasto;

    public Item() {

    }

    public Item(String nome, Double watts, Double hrs, Double qtd) {

        this.nome = nome;
        this.watts = watts;
        this.hrs = hrs;
        this.qtd = qtd;
        this.gasto = watts * hrs * qtd;
    }

    public Item(String nome, String watts, String hrs, String qtd) {

        this.nome = nome;
        this.watts = Double.parseDouble(watts);
        this.hrs = Double.parseDouble(hrs);
        this.qtd = Double.parseDouble(qtd);
        this.gasto = this.watts * this.hrs * this.qtd;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getWatts() {
        return watts;
    }

    public void setWatts(Double watts) {
        this.watts = watts;
    }

    public Double getHrs() {
        return hrs;
    }

    public void setHrs(Double hrs) {
        this.hrs = hrs;
    }

    public Double getQtd() {
        return qtd;
    }

    public void setQtd(Double qtd) {
        this.qtd = qtd;
    }

    public Double getGasto() {
        return gasto;
    }

    public void setGasto(Double gasto) {
        this.gasto = gasto;
    }
}
