package edu.umg.programacion2.clase07.biblioteca.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConexionDB {

    public static Connection getConexion() {
        Properties props = new Properties();
        
        // Carga el archivo database.properties desde src/main/resources
        try (InputStream input = ConexionDB.class.getClassLoader().getResourceAsStream("database.properties")) {
            
            if (input == null) {
                System.err.println("No se encontró el archivo database.properties en src/main/resources");
                return null;
            }

            props.load(input);

            // Obtiene los datos y establece la conexión
            return DriverManager.getConnection(
                props.getProperty("db.url"),
                props.getProperty("db.user"),
                props.getProperty("db.password")
            );

        } catch (Exception e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}