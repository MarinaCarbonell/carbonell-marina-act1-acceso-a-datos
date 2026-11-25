package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductoFavorito {
    //atributos de la base de datos
    private int id;
    private int id_producto;
}
