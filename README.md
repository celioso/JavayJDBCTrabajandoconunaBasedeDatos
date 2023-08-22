# Java y JDBC Trabajando con una Base de Datos

### Ingresar a MySQL por terminal

```cmd
C:\Users\celio>cd ..

C:\Users>cd ...

C:\Users>cd ..

C:\>dir
 El volumen de la unidad C no tiene etiqueta.
 El número de serie del volumen es: 186D-3D01

 Directorio de C:\

24/10/2022  18:11    <DIR>          Games
18/12/2022  15:38    <DIR>          inetpub
09/08/2023  15:07    <DIR>          Intel
24/10/2022  07:52    <DIR>          OneDriveTemp
07/05/2022  00:24    <DIR>          PerfLogs
20/06/2023  19:36    <DIR>          Program Files
11/01/2023  21:21    <DIR>          Program Files (x86)
19/06/2023  17:12    <DIR>          ProgramData
24/10/2022  07:24    <DIR>          Recovery
24/10/2022  07:52    <DIR>          Users
09/08/2023  15:06    <DIR>          Windows
               0 archivos              0 bytes
              11 dirs  227.313.102.848 bytes libres

C:\>cd "Program Files\MySQL\MySQL Server 8.0"\bin

C:\Program Files\MySQL\MySQL Server 8.0\bin>mysql -u root -p
Enter password: **********
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 23
Server version: 8.0.31 MySQL Community Server - GPL

Copyright (c) 2000, 2022, Oracle and/or its affiliates.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql>

mysql> used control_de_stock;
```

## crear base de datos 

```sql
create database control_de_stock;
```
**crear elementos en la tabla**

```sql
mysql> create table producto (
    -> id INT AUTO_INCREMENT,
    -> nombre VARCHAR(50) NOT NULL,
    -> descripcion VARCHAR(255),
    -> cantidad INT NOT NULL DEFAULT 0,
    -> PRIMARY KEY(id)
    -> )Engine=InnoDB;
Query OK, 0 rows affected (0.02 sec)
```

**Insertar datos**

```sql
mysql> select * from producto;
Empty set (0.00 sec)

mysql> INSERT INTO producto(nombre, descripcion, cantidad) VALUE ('Mesa','Mesa de 4 lugares', 10);
Query OK, 1 row affected (0.00 sec)

mysql> SELECT * FROM producto;
+----+--------+-------------------+----------+
| id | nombre | descripcion       | cantidad |
+----+--------+-------------------+----------+
|  1 | Mesa   | Mesa de 4 lugares |       10 |
+----+--------+-------------------+----------+
1 row in set (0.00 sec)

mysql>
```

Insertar mas datos

```sql
mysql> INSERT INTO producto(nombre, descripcion, cantidad) VALUE ('Celular','Celular Samsung', 50);
Query OK, 1 row affected (0.01 sec)

mysql> SELECT * FROM producto;
+----+---------+-------------------+----------+
| id | nombre  | descripcion       | cantidad |
+----+---------+-------------------+----------+
|  1 | Mesa    | Mesa de 4 lugares |       10 |
|  2 | Celular | Celular Samsung   |       50 |
+----+---------+-------------------+----------+
2 rows in set (0.00 sec)
```
## Preparando el ambiente

Para asegurar la compatibilidad de su proyecto inicial, disponibilizamos para download el proyecto creado inicialmente por el instructor.

[Descargue los archivos en Github](https://github.com/alura-es-cursos/1846-Java-y-JDBC-Trabajando-con-una-Base-de-Datos/tree/aula-1 "Descargue los archivos en Github") o haga clic [aquí](https://github.com/alura-es-cursos/1846-Java-y-JDBC-Trabajando-con-una-Base-de-Datos/archive/refs/tags/aula-1.zip "aquí") para descargarlos directamente.

## Lo que aprendimos

Lo que aprendimos en esta aula:

- Para acceder a una base de datos necesitamos del driver de conexión;
 + Un driver es simplemente una librería .jar.
- **JDBC **significa Java DataBase Connectivity;
 + El JDBC define una capa de abstracción entre la aplicación y el driver de la base de datos.
 + Esta capa es compuesta de interfaces que el driver implementa.
- Para abrir una conexión con la base de datos debemos utilizar el método `getConnection` de la clase `DriverManager`;
 + El método `getConnection` recibe tres parámetros. Son ellos la URL de conexión JDBC, el usuario y la contraseña.

  ## Proyecto del aula anterior
 
 ¿Comenzando en esta etapa? Aquí puedes descargar los archivos del proyecto que hemos avanzado hasta el aula anterior.

[Descargue los archivos en Github](https://github.com/alura-es-cursos/1846-Java-y-JDBC-Trabajando-con-una-Base-de-Datos/tree/aula-1 "Descargue los archivos en Github") o haga clic [aquí](https://github.com/alura-es-cursos/1846-Java-y-JDBC-Trabajando-con-una-Base-de-Datos/archive/refs/tags/aula-1.zip "aquí") para descargarlos directamente.


## Modificando un registro con Statement

En el vídeo anterior agregamos un registro con un error de escritura. En lugar de escribir "cuchara" fue escrito "cucaracha". Para modificar el valor de este registro es posible hacer doble click en la columna que queremos actualizar, escribir el nuevo valor y hacer click en el botón `Modificar`.

Ahora es tu turno de poner en práctica lo que aprendimos en las clases anteriores para completar la funcionalidad de modificar un registro del depósito.

Lo primero que tenemos que hacer aquí es arreglar el problema de `java.lang.ClassCastException` que vimos en la clase anterior cambiando el código con problemas por:

```java
Integer id = Integer.valueOf(modelo.getValueAt(tabla.getSelectedRow(), 0).toString());
```
También tenemos que agregar el campo de `cantidad`, que no está presente en la lógica pero es importante que también pueda ser modificado.

```java
Integer cantidad = Integer.valueOf(modelo.getValueAt(tabla.getSelectedRow(), 3).toString());
```

Luego, tenemos que ver cómo está configurado el evento del `botonModificar` en el método `configurarAccionesDelFormulario()`. Vemos dos métodos conocidos: `limpiarTabla()` y `cargarTabla()`. Vamos a ver el detalle del método `modificar()`.

Aquí estamos recibiendo los valores de la fila elegida y enviando como parámetro en el método `productoController.modificar(nombre, descripcion, id)`. Este es el método que debemos completar.

La lógica para hacer un `UPDATE` es muy similar a la lógica del `DELETE`, empezamos tomando la conexión de la `ConnectionFactory` y creando un `Statement statement = con.createStatement()`.

Luego ejecutamos el método `execute` con el código SQL de `UPDATE`, cerramos la conexión y retornamos la cantidad de líneas modificadas. No olvidemos de las comillas simples para modificar valores del tipo `String`.

```java
statement.execute("UPDATE PRODUCTO SET "
    + " NOMBRE = '" + nombre + "'"
    + ", DESCRIPCION = '" + descripcion + "'"
    + ", CANTIDAD = " + cantidad
    + "WHERE ID = " + id);
```

No podemos dejar pasar el detalle de la `SQLException` y, por fin, vamos a mostrar un cartel informando cuántos registros fueron modificados con éxito. Parecido con el cartel de eliminar registros.

Así quedan los códigos de resultado:

```java
// Clase ProductoController
public int modificar(String nombre, String descripcion, Integer cantidad, Integer id) throws SQLException {
    ConnectionFactory factory = new ConnectionFactory();
    Connection con = factory.recuperaConexion();
    Statement statement = con.createStatement();
    statement.execute("UPDATE PRODUCTO SET "
            + " NOMBRE = '" + nombre + "'"
            + ", DESCRIPCION = '" + descripcion + "'"
            + ", CANTIDAD = " + cantidad
            + " WHERE ID = " + id);

    int updateCount = statement.getUpdateCount();

    con.close();   

    return updateCount;
}
// Clase ControlDeStockFrame
private void modificar() {
    if (tieneFilaElegida()) {
        JOptionPane.showMessageDialog(this, "Por favor, elije un item");
        return;
    }

    Optional.ofNullable(modelo.getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn()))
            .ifPresentOrElse(fila -> {
                Integer id = Integer.valueOf(modelo.getValueAt(tabla.getSelectedRow(), 0).toString());
                String nombre = (String) modelo.getValueAt(tabla.getSelectedRow(), 1);
                String descripcion = (String) modelo.getValueAt(tabla.getSelectedRow(), 2);
                Integer cantidad = Integer.valueOf(modelo.getValueAt(tabla.getSelectedRow(), 3).toString());

                int filasModificadas;

                try {
                    filasModificadas = this.productoController.modificar(nombre, descripcion, cantidad, id);
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }

                JOptionPane.showMessageDialog(this, String.format("%d item modificado con éxito!", filasModificadas));
            }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
}
```

## Lo que aprendimos

Lo que aprendimos en esta aula:

- Para simplificar y encapsular la creación de la conexión debemos utilizar una clase `ConnectionFactory`;
 + Esta clase sigue el estándar de creación Factory Method, que encapsula la creación de un objeto.
- Podemos utilizar la interfaz `java.sql.Statement` para ejecutar un comando SQL en la aplicación;

 + El método execute envía el comando para la base de datos.
 + A depender del comando SQL, podemos recuperar la clave primaria o los registros buscados.