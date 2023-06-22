package com.example.proyectomovil.entidades;

public class Examenes {

    private String materia;
    private String fecha;
    private String hora;

    public Examenes(String materia, String fecha, String hora) {
        this.materia = materia;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getMateria() {
        return materia;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }
}
