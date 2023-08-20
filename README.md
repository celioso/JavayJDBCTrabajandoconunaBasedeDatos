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



