/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import com.mycompany.dominiprueba.Defecto;
import com.mycompany.dominiprueba.Pieza;
import java.util.List;

/**
 *
 * @author jctri
 */
public interface IDefecto {
    public Defecto agregarDefecto(Defecto defecto);
//    public List<Defecto> consultarTodoDefecto(String defecto);
    public List<Pieza> consultarTodasLasPiezas();
    public int consultarNumPiezasPorDefecto(String defecto);
    public double consultarCostosDefectos(String defecto);
    public List<String> consultarDetallePiezas(String defecto);
}
