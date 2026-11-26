package repository;
import model.*;
import java.sql.*;
import java.util.List;


public class AlmacenDAO {
    //clase que sirve para meter y sacar datos que vienen del JSON y queremos poner el la BD

    //Paso 1: Arrancar la BD
    //Si la BD no esta arrancada, conectar() devuelve null, lanzamos un error claro
    private Connection conexion() throws SQLException{
        Connection conn = ConexionBD.conectar();
        if(conn == null){
            throw new SQLException("Conexion fallida");
        }
        return conn;
    }

    //Paso 2: Insertar productos del JSON (Usando PreparedStatement)
    public void insertarProductos(List<Producto> lista) {
        //Usamos inser ingore into y no solo insert ignore para que si en cada vuelta que da el método se encuentra cn
        //que ese ID ya está ingresado, no lo meta. Así evitamos duplicidades
        String sql = "INSERT IGNORE INTO productos (id, nombre, descripcion, cantidad, precio) VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement ps = conexion().prepareStatement(sql)) {
            //creamos una variable que nos va a servir para controlar cuantos objetos se han insertado
            //en el enunciado pide saber cuantos datos se han ingresado
            int insertados = 0;
            for(Producto p : lista){
                ps.setInt(1, p.getId());
                ps.setString(2, p.getNombre());
                ps.setString(3, p.getDescripcion());
                ps.setInt(4, p.getCantidad());
                ps.setDouble(5, p.getPrecio());
                insertados += ps.executeUpdate();
            }
            System.out.println("Productos insertados en BD: " +  insertados);

        } catch (Exception e) {
            System.out.println("Error al insertar productos en BD: " + e.getMessage());
        }

    }
    //Paso 3: Prepara y ejecutar las distintas consultas que nos piden.
    //Consulta 1: Insertar empleados y pedidos
    //En este caso usamos Statement.
    //En Statement metemos los datos directamente y ejecuta la consulta.
    //PreparedStatement prepara la consulta, le metemos los datos manualmente y ejecutamos la consulta
    public void insertarEmpleadosyPedidos(){
        try(Statement st = conexion().createStatement()) {
        //Empleados de pruebas
            String sqlEmpleados = "INSERT IGNORE INTO empleados (id, nombre, puesto) VALUES "
                    + "(1, 'Alexander Martinez', 'Jefe de Almacen'), "
                    + "(2, 'Carlos Ruiz', 'Encargado de Compras'), "
                    + "(3, 'Lucia Gomez', 'Supervisora de Logistica')";
            st.executeUpdate(sqlEmpleados);
            // Pedidos asociados a productos existentes (IDs 1, 2, 3) y empleados
            String sqlPedidos = "INSERT IGNORE INTO pedidos (id, id_producto, id_empleado, fecha) VALUES "
                    + "(1, 1, 1, NOW()), "
                    + "(2, 2, 2, NOW()), "
                    + "(3, 3, 3, NOW())";
            st.executeUpdate(sqlPedidos);
            System.out.println("Empleados y pedidos insertados correctamente mediante Statement.");

        }catch (SQLException e){
            System.out.println("Error al insertar pedidos en BD: " + e.getMessage());
        }
    }

    //Consulta 2: Insertar productos favoritos cuyo valor es mayor a 1000
    public void insertaProductosFavoritos(){
        String sql = "INSERT INTO productos_fav(id_producto) SELECT id FROM productos WHERE precio>1000 AND id NOT IN (SELECT id_producto FROM productos_fav)";
        try(Statement st = conexion().createStatement()){
            int filas = st.executeUpdate(sql);
            System.out.println("Se han añadido los productos favoritos y filas nuevas para ellos" + filas);
        }catch (SQLException e){
            System.out.println("Error al insertar los productos favoritos en BD: " + e.getMessage());
        }
    }

    //Consulta 3: Mostrar todos los productos por consola
    //Aqui usamos ResultSet porque guarda la respuesta de la consulta con los datos y nos permite mostrarlo

    public void mostrarTodosProductos() {
        String sql = "SELECT * FROM productos";
        System.out.println("\n--- LISTADO DE TODOS LOS PRODUCTOS ---");

        try (Statement stmt = conexion().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // %d , %s , %f , %d, id, nombre, precio, cantidad
                // d = ENTEROS, S = STING , F= FLOAT O DOBLE
                // %-3d dejame 3 espacios desde donde comienza la escritura
                //    1/
                // %-30.30s dejame 30 espacios desde donde comienza pero a la vez mio espacio es de 30
                //                       /
                // %d

                System.out.printf("ID: %-3d | %-30.30s | Precio: %8.2f € | Stock: %-4d%n",
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("cantidad"));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar productos: " + e.getMessage());
        }
    }

    //Consulta 4: mostrar productos con precio < a 600
    public void mostrarProductosMenores600() {
        String sql = "SELECT * FROM productos WHERE precio < 600 ORDER BY precio ASC";
        System.out.println("\n--- PRODUCTOS CON PRECIO MENOR A 600 € ---");

        try (Statement stmt = conexion().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.printf("ID: %-3d | %-30.30s | Precio: %8.2f €%n",
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar productos < 600: " + e.getMessage());
        }
    }

    //Consulta 5: mostrar productos favoritos con sus datos
    public void mostrarFavoritosConDatos() {
        String sql = "SELECT pf.id AS id_fav, p.id AS id_producto, p.nombre, p.precio, p.cantidad "
                + "FROM productos_fav pf "
                + "INNER JOIN productos p ON pf.id_producto = p.id";

        System.out.println("\n--- PRODUCTOS FAVORITOS CON SUS DATOS (JOIN) ---");

        try (Statement stmt = conexion().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("FavID: %-2d | ProdID: %-3d | %-30.30s | Precio: %8.2f € | Stock: %-4d%n",
                        rs.getInt("id_fav"),
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("cantidad"));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar favoritos detallados: " + e.getMessage());
        }
    }




}
