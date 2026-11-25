package model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    //atributos de la base de datos
    private int id;
    private int id_producto;
    private int id_empleado;
    private Date fecha;
}
