package com.saaweel;

import java.util.Date;

public class Noticia {
    private String titular;
    private String cuerpo;
    private Date fecha;

    public Noticia(String titular, String cuerpo, Date fecha) {
        this.titular = titular;
        this.cuerpo = cuerpo;
        this.fecha = fecha;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
