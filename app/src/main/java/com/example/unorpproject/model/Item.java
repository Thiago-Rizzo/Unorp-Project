package com.example.unorpproject.model;

public class Item {
    private long id;
    private String nome;
    private Long watts;
    private Long hrs;
    private Long qtd;
    private Long gasto;

    public Item() {
    }

    public Item(Long id, String nome, Long watts, Long hrs, Long qtd) {
        this.id = id;
        this.nome = nome;
        this.watts = watts;
        this.hrs = hrs;
        this.qtd = qtd;
        this.gasto = watts * hrs * qtd;
    }

    public Item(Long id, String nome, String watts, String hrs, String qtd) {
        this.id = id;
        this.nome = nome;
        this.watts = Long.parseLong(watts);
        this.hrs = Long.parseLong(hrs);
        this.qtd = Long.parseLong(qtd);
        this.gasto = this.watts * this.hrs * this.qtd;
    }

    public String getNome() {
        return nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getWatts() {
        return watts;
    }

    public void setWatts(Long watts) {
        this.watts = watts;
    }

    public Long getHrs() {
        return hrs;
    }

    public void setHrs(Long hrs) {
        this.hrs = hrs;
    }

    public Long getQtd() {
        return qtd;
    }

    public void setQtd(Long qtd) {
        this.qtd = qtd;
    }

    public Long getGasto() {
        return gasto;
    }

    public void setGasto(Long gasto) {
        this.gasto = gasto;
    }

    @Override
    public String toString() {
        return "Item{" +
                "nome='" + nome + '\'' +
                ", watts=" + watts +
                ", hrs=" + hrs +
                ", qtd=" + qtd +
                ", gasto=" + gasto +
                '}';
    }
}
