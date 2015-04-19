/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloPostgres;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.spi.DirStateFactory;

/**
 *
 * @author ingesis
 */
public class CrossValidation {
    
    private ResultSet dataSetCross;
    private ResultSet datos1;

    public ResultSet getDatos1() {
        return datos1;
    }

    public void setDatos1(ResultSet datos1) {
        this.datos1 = datos1;
    }
    
    ArrayList<Paciente> arrayPacientes = new ArrayList<Paciente>();
    
    private ModeloPostgres modelo = new ModeloPostgres();
    
    public CrossValidation(ResultSet dataset) {
        this.dataSetCross = dataset;
    }
    
    public CrossValidation() {
        // 1. Desordenar los datos
        String comando = "SELECT * FROM datasetnormalizado ORDER BY RANDOM()";
        ResultSet resultado = modelo.buscarDatos(comando);
        if (resultado != null) {
            dataSetCross = resultado; // Se tienen los datos normalizados y en desorden
            System.out.println("entro");
            for(int i = 0; i < 10; i++){
                //datos1.insertRow();
            }
        }
        
    }
    
 
    
}
