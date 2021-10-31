package com.example.tarea13.configuracion;

public class Transacciones {
    public  static final String NameDatabase="Tarea1.3";
    public static final String tablaPersonas = "personas";

    public static final String id = "id";
    public static final String nombres = "nombres";
    public static final String apellidos = "apellidos";
    public static final String edad = "edad";
    public static final String correo = "correo";
    public static final String direccion = "direccion";

    public static final String CreateTablePersonas = "CREATE TABLE personas(id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "nombres TEXT, apellidos TEXT, edad INTEGER, correo TEXT, direccion TEXT)";
    public static final String DROPTablePersonas= "DROP TABLE IF EXIST personas";

}
