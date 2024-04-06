/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao_manufactura;

import Interfaces.IDefecto;
import com.mycompany.dominiprueba.Defecto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author IVAN
 */
public class DaoDefecto implements IDefecto{
    
    private String url;
    private String usuario;
    private String contraseña;
    
    public DaoDefecto (String host, String puerto, String baseDatos, String usuario, String contraseña) {
        this.url = "jdbc:mysql://" + host + ":" + puerto + "/" + baseDatos;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    private Connection obtenerConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("error");
        }
        return DriverManager.getConnection(url, usuario, contraseña);
    }
    
    @Override
    public Defecto agregarDefecto(Defecto defecto) {
      try (Connection connection = obtenerConexion()) {
            String query = "INSERT INTO Defectos (numeroLote, detalles, defectos) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, defecto.getNumeroLote());
                pstmt.setString(2, defecto.getDetalles());
                pstmt.setString(3, defecto.getDefectos());
                pstmt.executeUpdate();

                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        defecto.setId(generatedKeys.getLong(1));
                    } else {
                        throw new SQLException("No se pudo obtener el ID del defecto insertado.");
                    }
                }
            }
            return defecto;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
