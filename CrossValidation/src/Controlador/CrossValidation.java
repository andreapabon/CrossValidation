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

    private ModeloPostgres modelo = new ModeloPostgres();
    private ResultSet dataSetCross;
    private ResultSet datos1;

    ArrayList<Paciente> datosEntrenamiento;
    ArrayList<ArrayList> folders;
    // <editor-fold defaultstate="collapsed" desc="Folders">
    ArrayList<Paciente> datosP1;
    ArrayList<Paciente> datosP2;
    ArrayList<Paciente> datosP3;
    ArrayList<Paciente> datosP4;
    ArrayList<Paciente> datosP5;
    ArrayList<Paciente> datosP6;
    ArrayList<Paciente> datosP7;
    ArrayList<Paciente> datosP8;
    ArrayList<Paciente> datosP9;
    ArrayList<Paciente> datosP10;
    ArrayList<Paciente> datosP11;
    ArrayList<Paciente> datosP12;
    ArrayList<Paciente> datosP13;
    ArrayList<Paciente> datosP14;
    ArrayList<Paciente> datosP15;
    ArrayList<Paciente> datosP16;
    ArrayList<Paciente> datosP17;
    ArrayList<Paciente> datosP18;
    ArrayList<Paciente> datosP19;
    ArrayList<Paciente> datosP20;
    // </editor-fold>

    public ResultSet getDatos1() {
        return datos1;
    }

    public void setDatos1(ResultSet datos1) {
        this.datos1 = datos1;
    }

    ArrayList<Paciente> arrayPacientes = new ArrayList<Paciente>();

    public CrossValidation(ResultSet dataset) {
        this.dataSetCross = dataset;
    }

    public CrossValidation() throws SQLException {
        folders = new ArrayList<ArrayList>();
        datosP1 = new ArrayList<Paciente>();
        datosEntrenamiento = new ArrayList<Paciente>();
        datosP2 = new ArrayList<Paciente>();
        datosP3 = new ArrayList<Paciente>();
        datosP4 = new ArrayList<Paciente>();
        datosP5 = new ArrayList<Paciente>();
        datosP6 = new ArrayList<Paciente>();
        datosP7 = new ArrayList<Paciente>();
        datosP8 = new ArrayList<Paciente>();
        datosP9 = new ArrayList<Paciente>();
        datosP10 = new ArrayList<Paciente>();
        datosP11 = new ArrayList<Paciente>();
        datosP12 = new ArrayList<Paciente>();
        datosP13 = new ArrayList<Paciente>();
        datosP14 = new ArrayList<Paciente>();
        datosP15 = new ArrayList<Paciente>();
        datosP16 = new ArrayList<Paciente>();
        datosP17 = new ArrayList<Paciente>();
        datosP18 = new ArrayList<Paciente>();
        datosP19 = new ArrayList<Paciente>();
        datosP20 = new ArrayList<Paciente>();

        // 1. Desordenar los datos
        String comando = "SELECT * FROM datasetnormalizado ORDER BY RANDOM()";
        ResultSet resultado = modelo.buscarDatos(comando);
        if (resultado != null) {
            dataSetCross = resultado; // Se tienen los datos normalizados y en desorden
            System.out.println("entro");
            double edad;
            double genero;
            double colesterol;
            double sodio;
            double potasio;
            double bp;
            String droga;
            Paciente p;
            int contador = 1;

            while (resultado.next()) {
                edad = resultado.getDouble("edad");
                genero = resultado.getDouble("sexo");
                colesterol = resultado.getDouble("colesterol");
                sodio = resultado.getDouble("sodio");
                potasio = resultado.getDouble("potasio");
                bp = resultado.getDouble("bp");
                droga = resultado.getString("drogra");
                p = new Paciente(edad, genero, bp, colesterol, sodio, potasio, droga);

                if (contador <= 10) {
                    datosP1.add(p);

                } else if (contador <= 20) {
                    datosP2.add(p);
                } else if (contador <= 30) {
                    datosP3.add(p);
                } else if (contador <= 40) {
                    datosP4.add(p);
                } else if (contador <= 50) {
                    datosP5.add(p);
                } else if (contador <= 60) {
                    datosP6.add(p);
                } else if (contador <= 70) {
                    datosP7.add(p);
                } else if (contador <= 80) {
                    datosP8.add(p);
                } else if (contador <= 90) {
                    datosP9.add(p);
                } else if (contador <= 100) {
                    datosP10.add(p);
                } else if (contador <= 110) {
                    datosP11.add(p);
                } else if (contador <= 120) {
                    datosP12.add(p);
                } else if (contador <= 130) {
                    datosP13.add(p);
                } else if (contador <= 140) {
                    datosP14.add(p);
                } else if (contador <= 150) {
                    datosP15.add(p);
                } else if (contador <= 160) {
                    datosP16.add(p);
                } else if (contador <= 170) {
                    datosP17.add(p);
                } else if (contador <= 180) {
                    datosP18.add(p);
                } else if (contador <= 190) {
                    datosP19.add(p);
                } else if (contador <= 200) {
                    datosP20.add(p);
                }
                contador++;
            }// end while
            folders.add(datosP1);
            folders.add(datosP2);
            folders.add(datosP3);
            folders.add(datosP4);
            folders.add(datosP5);
            folders.add(datosP6);
            folders.add(datosP7);
            folders.add(datosP8);
            folders.add(datosP9);
            folders.add(datosP10);
            folders.add(datosP11);
            folders.add(datosP12);
            folders.add(datosP13);
            folders.add(datosP14);
            folders.add(datosP15);
            folders.add(datosP16);
            folders.add(datosP17);
            folders.add(datosP18);
            folders.add(datosP19);
            folders.add(datosP20);

        }

    }

    public void llenarDatosEntrenamiento(int indiceFolderPrueba) {
        for (int i = 0; i < 20; i++) {
            if (i != indiceFolderPrueba) {
                datosEntrenamiento.addAll(folders.get(i));
               // datosEntrenamiento.
            }
        }
       /* System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("Tamañano lista entrenamiento: " + datosEntrenamiento.size());
        for(int j = 0; j < datosEntrenamiento.size(); j++){
            System.out.println("Elemento: " + datosEntrenamiento.get(j));
        }
         System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");*/
    }

    public int algoritmoCross(int numVecinos) {
        
        //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        //System.out.println("Lista de Datos paciente Naives " + n.datosPacientes.get(0).getDroga());
        System.out.println("!!!!!!!!!!!!!!!!!!! Inicio Algoritmo Cross !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        int aciertos = 0;
        for (int i = 0; i < 20; i++) {
            System.out.println("Desde el for Algoriiiiiitmooooooooooooooooooo " + i);
            datosEntrenamiento = new ArrayList<Paciente>();
            llenarDatosEntrenamiento(i);
            naives n = new naives(datosEntrenamiento);
            ArrayList<Paciente> prueba = new ArrayList<Paciente>();
            prueba = folders.get(i);
            for (int j = 0; j < folders.size(); j++) {
                Paciente p = new Paciente();
                p = prueba.get(i);
                ArrayList<Paciente> listapaciente = n.kVecinos(numVecinos, p);
                String droga = n.claseElemento(listapaciente, p);
                if (droga.equals(p.getDroga())) {
                    aciertos++;
                }

            }
        }
        return aciertos;
    }

    public int crossValidation(int maxVecinos) {
        ArrayList<Integer> aciertos = new ArrayList<Integer>();
        System.out.println("!!!!!!!!!!!!!!! Entra a CrossValidation con vecinos: " + maxVecinos);
        for (int i = 1; i <= maxVecinos; i++) {
            System.out.println("Desde el for Crosssssssssssssssssssssssssss " + i);
            aciertos.add(algoritmoCross(i));
        }
        int mayor = buscarMayor(aciertos);
        System.out.println("!!!!!!!!!!!!!!!Fin CrossValidation!!!!!!!!!!!!!!!!!!!!");
        return aciertos.get(mayor);
    }

    private int buscarMayor(ArrayList<Integer> aciertos) {
        int mayor = 0;
        int pos = 0;
        for (int i = 0; i < aciertos.size(); i++) {
            if (aciertos.get(i) > mayor) {
                mayor = aciertos.get(i);
                pos = i;
            }
        }
        return pos + 1;
    }

}
