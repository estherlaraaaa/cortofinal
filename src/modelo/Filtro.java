/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author LN710Q
 */
public class Filtro {

    private String carnet;
    private String nombre;
    private String universidad;
    private int edad;
    private String apellidos;
    private boolean existencia;

    public Filtro() {
    }

    public Filtro(String carnet, String nombre, String universidad, int edad, String apellidos, boolean existencia) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.universidad = universidad;
        this.edad = edad;
        this.apellidos = apellidos;
        this.existencia = existencia;
    }

    public Filtro(String nombre, String universidad, int edad, String apellidos, boolean existencia) {
        this.nombre = nombre;
        this.universidad = universidad;
        this.edad = edad;
        this.apellidos = apellidos;
        this.existencia = existencia;
    }

    public Filtro(String universidad, int edad, String apellidos, boolean existencia) {
        this.universidad = universidad;
        this.edad = edad;
        this.apellidos = apellidos;
        this.existencia = existencia;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setcCarnet(String carnet) {
        this.carnet=carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String setApellidos() {
        return apellidos;
    }

    public void setapellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getApellidos() {
        return apellidos;
    }

    public boolean isExistencia() {
        return existencia;
    }

    public void setExistencia(boolean existencia) {
        this.existencia = existencia;
    }

    public boolean getExistencia() {
        return existencia;
    }

}
