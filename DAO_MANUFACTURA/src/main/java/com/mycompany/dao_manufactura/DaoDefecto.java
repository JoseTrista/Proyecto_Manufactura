/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao_manufactura;

import Interfaces.IDefecto;
import com.mycompany.dominiprueba.Defecto;
import com.mycompany.dominiprueba.Pieza;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
            String query = "INSERT INTO Defectos (numeroLote, detalles, defectos, id_pieza) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, defecto.getNumeroLote());
                pstmt.setString(2, defecto.getDetalles());
                pstmt.setString(3, defecto.getDefectos());
                pstmt.setInt(4, defecto.getId_pieza());
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
    
    @Override
    public List<Pieza> consultarTodasLasPiezas() {
        List<Pieza> piezas = new ArrayList<>();
        String query = "SELECT id, nombrePieza, precio FROM Pieza";
        try (Connection connection = obtenerConexion();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Pieza pieza = new Pieza();
                pieza.setId(rs.getLong("id"));
                pieza.setNombrePieza(rs.getString("nombrePieza"));
                pieza.setPrecio(rs.getInt("precio"));
                piezas.add(pieza);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar las piezas: " + e.getMessage());
        }
        return piezas;
    }

    @Override
    public int consultarNumPiezasPorDefecto(String defecto) {
        int numPiezas = 0;
        String query = "SELECT count(id) AS piezasR FROM Defectos WHERE defectos LIKE ?";
        try (Connection connection = obtenerConexion(); PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, "%"+defecto+"%");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                numPiezas = rs.getInt("piezasR");
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar las piezas: " + e.getMessage());
        }
        return numPiezas;    
    }

    @Override
    public double consultarCostosDefectos(String defecto) {
        double costosDefectos = 0;
        String query = "SELECT SUM(p.precio) AS costoTotal FROM Defectos d INNER JOIN Pieza p ON d.id_pieza = p.id WHERE d.defectos LIKE ?";
        try (Connection connection = obtenerConexion(); PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, "%" + defecto + "%");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                costosDefectos = rs.getDouble("costoTotal");
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar los costos de los defectos: " + e.getMessage());
        }
        return costosDefectos;
    }

    @Override
    public List<String> consultarDetallePiezas(String defecto) {
        List<String> detalles = new ArrayList<>();
        String query = "SELECT d.detalles FROM Defectos d WHERE d.defectos LIKE ?";
        try (Connection connection = obtenerConexion(); PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, "%" + defecto + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
               detalles.add(rs.getString("detalles"));
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar los detalles de las piezas: " + e.getMessage());
        }
        return detalles;
    }
    
    
}
