import model.*;
import repository.*;
import java.util.*;

public class Main {

    public static void main(String[] args)  {
    //Instanciar el DAO
        AlmacenDAO almacenDAO = new AlmacenDAO();

        //1. Ejecutamos el código para obtener productos de la API
        String url = "https://dummyjson.com/products?limit=0";
        System.out.println("Consumiendo la API");

        //ConsumirApi es un metodo que recibe como parametro una URL(STRING) y la cual
        //el metodo devuelve una lista(LIST O ARRAY)
        //Por ende yo creo una lista vacia que pertenece a producto que es mi entidad o objeto
        //Aqui ya esta los datos de la api en ram
        List<Producto> productos = ConsumirApi.obtenerDatos(url);
        System.out.println("-> " + productos.size() + " productos cargados en memoria RAM.");

        System.out.println("Insertar producots en la BD");
        almacenDAO.insertarProductos(productos);

        System.out.println("Insertando empleados y pedidos de prueba");
        almacenDAO.insertarEmpleadosyPedidos();

        System.out.println("Registrando productos favoritos con precio superior a 1000 euros");
        almacenDAO.insertaProductosFavoritos();

        //Consultas por consola

        //Mostrar todo los productos
        System.out.println("Mostrar todo los productos");
        almacenDAO.mostrarTodosProductos();
        System.out.println("/n");

        System.out.println("Productos con precio < 600");
        almacenDAO.mostrarProductosMenores600();

        System.out.println("Productos favoritos con sus datos");
        almacenDAO.mostrarFavoritosConDatos();

        System.out.println("Cerrando Base de datos");
        ConexionBD.desconectar();

    }

}
