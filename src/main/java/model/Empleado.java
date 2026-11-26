package model;

//De todo tus objetos o entidades les crear el constructor en automatico
import lombok.AllArgsConstructor;
// Genera en automatico Get and Set
import lombok.Data;
// Genera constructores vacios
import lombok.NoArgsConstructor;

//importamos los metodos de la libreria lombok paraa poder usarlos
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {
    //Creamos atributos que conas columnas de la BD de esa tabla (clase=tabla)
    private int id;
    private String nombre;
    private String puesto;
}
