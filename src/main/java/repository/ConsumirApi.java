package repository;

import com.mysql.cj.xdevapi.JsonArray;
import model.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.*;
import java.net.*;

public class ConsumirApi {

    //https://dummyjson.com/products --> lista de datos de la API desordenada
    //Pasar los datos de la API a memoria ram (ArrayList)
    // Ingresar los datos de la ram a tu BD

    //Crear un metodo para obtener los datos y meterlo en una lista
    public static List<Producto> obtenerDatos(String url){
        //Creo la lista
        List<Producto> lista = new ArrayList<>();

        //aqui intentar acceder a lo datos del JSON
        try(InputStream entrada = new URI(url).toURL().openStream()) {
            JSONTokener tokener = new JSONTokener(entrada);
            JSONObject root = new JSONObject(tokener);
            //aqui creamos una lista con los productos de la API
            JSONArray listaProductos = root.getJSONArray("products");

            for(int i = 0; i < listaProductos.length(); i++){
                JSONObject item = listaProductos.getJSONObject(i);
                //Aqui añadimos los productos de la lista de la API en otra lista de texto plano
                lista.add(new Producto(
                        item.getInt("id"),
                        item.getString("title"),
                        item.getString("description"),
                        item.getInt("stock"),
                        item.getDouble("price")
                ));
            }
        } catch (Exception e) {
            System.out.println("Error al consumir api" + e.getMessage());
        }
    return lista;
    }

}
