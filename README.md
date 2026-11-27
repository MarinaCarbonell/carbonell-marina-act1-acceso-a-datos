# Actividad Evaluable 1 Acceso a Datos

Creamos una aplicación que para leer los datos de una base de datos llamada "Almacen_ue". Las tablas de las bases de datos y sus columnas las obtenemos del enunciado.

En la aplicación encontramos dos paquetes: **model** y **repository** y una clase Main desde la que se ejecuta la aplicación. Todo ello hace referencia a una estructura básica DAO. Además como es un proyecto Maven, hemos agregado las dependencias en el pom.xml.

En el paquete model encontramos clases. Cada una de ella se llama igual que las tablas de la base se datos y en cada una de ellas encontramos atributos que se llaman igual que las tablas de cada una de las clases. Además en cada una de ellas importamos la librería "Lombok" para poder usar los getter y los setter.

En el paquete repository encontramos tres clases : 

- Almacen DAO
- ConexionBD
- ConsumirAPI

En la clase **ConexionBD** aplicamos el Singleton de una manera sencilla. Creamos además tres constantes donde guardamos la url donde esta guardada la BD, root o usuario, contraseña o un cifrado. Además creamos dos métodos: uno para conectar la base de datos en caso de que no lo esté y otro para desconectarla cuando se haya acabado su ejecución.

En la clase **Consumir API** importamos la libreria .org JSON ya que la API es un JSON. En esta clase, pasamos los datos de la API a la memoria RAM en una lista.

En la clase **AlmacenDAO** es donde guardamos la lógica del proyecto. En ella, arrancamos la base de datos e insertamos los productos de la lista creada en Consumir API a la base de datos. Para ello usamos PrepareStatement, Statement y ResultSet. Preparamos además las distintas consultas que nos pide el enunciado.

Desde la clase **Main**, ejecutamos todo el programa.

