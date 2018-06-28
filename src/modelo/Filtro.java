/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Estehr Lara
 */
public class Filtro {


    private String carnet;

    private String nombres;

    private String apellidos;

    private String universidad;
    
    private int edad;

    private boolean estado;

    public Filtro() {

    }

    public Filtro(String carnet, String nombres, String apellidos, String universidad, int edad, boolean estado) {


        this.carnet = carnet;

        this.nombres = nombres;

        this.apellidos = apellidos;

        this.universidad = universidad;

        this.estado = estado;
    }

    public Filtro(String nombres, String apellidos, String universidad, int edad, boolean estado) {

        this.nombres = nombres;

        this.apellidos = apellidos;

        this.universidad = universidad;

        this.estado = estado;
    }

    public Filtro(String universidad, boolean estado) {

        this.universidad = universidad;

        this.estado = estado;

    }

    public Filtro(String carnet, String nombres, String apellidos, String universidad, String string3, int edad, boolean estado) {
         this.carnet = carnet;

        this.nombres = nombres;

        this.apellidos = apellidos;

        this.universidad = universidad;

        this.estado = estado;
    }

    public Filtro( String carnet, String nombres, String apellidos, int edad, String universidad, String string3, boolean estado) {
 

        this.carnet = carnet;

        this.nombres = nombres;

        this.apellidos = apellidos;

        this.universidad = universidad;

        this.estado = estado;
    }

  

    public String getUniversidad() {

        return universidad;

    }

    public void setUniversidad(String universidad) {

        this.universidad = universidad;

    }

    public String getCarnet() {

        return carnet;

    }

    public void setCarnet(String carnet) {

        this.carnet = carnet;

    }

    public String getNombres() {

        return nombres;

    }

    public void setNombres(String nombres) {

        this.nombres = nombres;

    }

    public String getApellidos() {

        return apellidos;

    }

    public void setApellidos(String apellidos) {

        this.apellidos = apellidos;

    }

    public int getEdad() {

        return edad;

    }

    public void setEdad(int edad) {

        this.edad = edad;

    }

    public boolean getEstado() {

        return estado;

    }

    public void setEstado(boolean estado) {

        this.estado = estado;

    }

}
