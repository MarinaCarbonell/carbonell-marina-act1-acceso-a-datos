package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Producto {

    //atributos de la base de datos

    private int id;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private double precio;
}
