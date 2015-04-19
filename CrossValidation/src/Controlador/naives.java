/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloPostgres;
import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrea
 */
public class naives {
    private ModeloPostgres modelo = new ModeloPostgres();
    ResultSet dataSet;//bd
    ArrayList<Paciente>  datosPacientes;
    // <editor-fold defaultstate="collapsed" desc="Your Fold Comment">
    // </editor-fold>
    
   public naives(){
       datosPacientes = new ArrayList<Paciente>();
       String comando = "SELECT * FROM datasetNormalizado" ;
       ResultSet resultado = modelo.buscarDatos(comando);
        if (resultado != null) {
           dataSet = resultado;
        }
   }
   
  // <editor-fold defaultstate="collapsed" desc="Algorítmo para encontrar los K-Vecinos">
    // k = número de vecinos
   public ArrayList<Paciente> kVecinos(int k, Paciente t) {
       ArrayList<Paciente> N = new ArrayList<Paciente>();
       int tam = 0;
       int i;
       double d1;
       double d2;
          // System.out.println("data set " +dataSet.getDouble("bp"));
            for(i = 0; i < datosPacientes.size(); i++){
                Paciente d = new Paciente();
               d = datosPacientes.get(i);
               // System.out.println(d.getBp() + " " + d.getDroga());
                if(tam < k){
                    System.out.println("tamaño if: " + tam);
                    N.add(tam, d);
                    tam ++;
                }
                else{
                    System.out.println("Al for " + k);
                    for (i = 0; i < tam; i++){
                        Paciente u = new Paciente();
                        u = N.get(i);
                        d1 = u.calcularDistanciaEuclidiana(t, u);
                         d2 = u.calcularDistanciaEuclidiana(t, d);
                       // System.out.println("D1: " + d1);  System.out.println("D2: " + d2);                       
                        if( d1 >=  d2){
                            N.remove(i);
                            N.add(d);
                        }
                    }
                }
            }
      
       System.out.println("VECIOSssssssssssss " + N.size());
       System.out.println(N);
       System.out.println("*********************************************************");
       return N;
   }
   
   public String claseElemento(ArrayList<Paciente> vecinos, Paciente p){
       double clase = -1;
       int tam = vecinos.size();
       int i;
       ArrayList<Paciente> drogaX = new ArrayList<Paciente>();
       ArrayList<Paciente> drogaY = new ArrayList<Paciente>();
       ArrayList<Paciente> drogaA = new ArrayList<Paciente>();
       ArrayList<Paciente> drogaB = new ArrayList<Paciente>();
       ArrayList<Paciente> drogaC = new ArrayList<Paciente>();
       
       ArrayList<Double> c = new ArrayList<Double>();
       System.out.println("ENEL FOR ***************************************");
       for(i = 0; i < tam; i++){
           System.out.println("droga: " + vecinos.get(i).droga);
           if(vecinos.get(i).droga.equals("drugX                              ")){
               drogaX.add(vecinos.get(i));
               System.out.println("If x");
           }
           else if(vecinos.get(i).droga.equals("drugY                              ")){
               drogaY.add(vecinos.get(i));
               System.out.println("If y");
           }
           else if(vecinos.get(i).droga.equals("drugA                              ")){
               drogaA.add(vecinos.get(i));
               System.out.println("If a");
           }
           else if(vecinos.get(i).droga.equals("drugB                              ")){
               drogaB.add(vecinos.get(i));
               System.out.println("If  b");
           }
           else if(vecinos.get(i).droga.equals("drugC                              ")){
               drogaC.add(vecinos.get(i));
               System.out.println("If c");
           }
       }
       System.out.println("*********************************************************");
       double distP;
       ArrayList distanciasPonderadas = new ArrayList();
       ArrayList<Double> valoresDist = new ArrayList<Double>();
       if(drogaA.size()>0){
           System.out.println("If A ");
           ArrayList distA = new ArrayList();
           distA.add(0, "drugA"); 
           distP = distanciaPonderada(drogaA,p);
           System.out.println("Distancia Ponderada : " + distP);
           distA.add(1,distP );
           distanciasPonderadas.add(distA);
           valoresDist.add((double) distA.get(1));
       }
       if(drogaB.size()>0){
             System.out.println("If B ");
            ArrayList distB = new ArrayList();
            distB.add(0, "drugB"); 
            distP = distanciaPonderada(drogaB,p);
           System.out.println("Distancia Ponderada : " + distP);
           distB.add(1,distP );
           // distB.add(1, distanciaPonderada(drogaB,p ));
            distanciasPonderadas.add(distB);
            valoresDist.add((double) distB.get(1));
       }
       if(drogaC.size()>0){
             System.out.println("If  C ");
           ArrayList distC = new ArrayList();
           distC.add(0, "drugC"); 
           distP = distanciaPonderada(drogaC,p);
           System.out.println("Distancia Ponderada : " + distP);
           distC.add(1,distP );
          // distC.add(1, distanciaPonderada(drogaC,p )); 
           distanciasPonderadas.add(distC);
           valoresDist.add((double) distC.get(1));
       }
       if(drogaX.size()>0){
             System.out.println("If  X ");
            ArrayList distX = new ArrayList();
           distX.add(0, "drugX");
           distP = distanciaPonderada(drogaX,p);
           System.out.println("Distancia Ponderada : " + distP);
           distX.add(1,distP ); 
           //distX.add(1, distanciaPonderada(drogaX,p ));
           distanciasPonderadas.add(distX);
           valoresDist.add((double) distX.get(1));
       }
       if(drogaY.size()>0){
             System.out.println("If Y ");
            ArrayList distY = new ArrayList();
           distY.add(0, "drugY"); 
           distP = distanciaPonderada(drogaY,p);
           System.out.println("Distancia Ponderada : " + distP);
           distY.add(1,distP );
           //distY.add(1, distanciaPonderada(drogaY,p ));
           distanciasPonderadas.add(distY);
           valoresDist.add((double) distY.get(1));
       }
       System.out.println("***************************************dist " + distanciasPonderadas.size());
       
       Collections.sort(valoresDist);
      double mayor = valoresDist.get(valoresDist.size()-1);
     System.out.println(distanciasPonderadas);
       
      for (int j = 0; j < distanciasPonderadas.size(); j++) {
           ArrayList aux = (ArrayList) distanciasPonderadas.get(j);
           System.out.println("Nuevoooooooo " +(Double)aux.get(1) + " " + mayor);
           if((Double)aux.get(1) == mayor){
               return (String)aux.get(0);
           }
       }
       return "No existe";       
   }
   
   public double distanciaPonderada(ArrayList<Paciente> lista, Paciente pacActual){
       double dist = 0;
       double distEuclidiana = 0;
       Paciente p = new Paciente();
       for(int i = 0; i < lista.size(); i++){
           p = lista.get(i);
           dist = 1/p.calcularDistanciaEuclidiana(p, pacActual);
           distEuclidiana = distEuclidiana +  dist;
       }
       return distEuclidiana;
   }
   // </editor-fold>
 
   // <editor-fold defaultstate="collapsed" desc="Algorítmo para Normalizar un Paciente">
   public Paciente normalizarPaciente(double edadP, String sex, String bpP, String colesterolP, double naP, double kP ){
        double sodio;
        double edad;
        double sexo;
        double bp;
        double colesterol;
        double potasio; 
        String droga; 
        double minEdad = minimoParametro("edad");
        double maxEdad = maximoParametro("edad");
        double minSodio = minimoParametro("sodio"); 
        double maxSodio = maximoParametro("sodio");
        double minPotasio = minimoParametro("potasio"); 
        double maxPotasio = maximoParametro("potasio"); 
        
        edad = min_max(minEdad, maxEdad, edadP);
        System.out.println("Min edad: " + minEdad + " maxEdad "+ maxEdad);
        System.out.println("Edad en normalizar: " + edad);
         if(sex == "F")
            sexo = 1;
        else 
            sexo = 0;
        if(bpP== "LOW") 
            bp = 0.33;
        else {
            if(bpP == "NORMAL")
                bp = 0.66;
            else 
                bp = 0.99;
        }
        if(colesterolP == "NORMAL")
            colesterol = 1;
        else 
            colesterol = 0;
        sodio = min_max(minSodio, maxSodio, naP);
        potasio = min_max(minPotasio, maxPotasio, kP);
        droga = "";
        
        Paciente p = new Paciente(edad, sexo,bp, colesterol, sodio, potasio,  "");
       return p;
   }
    
    public double maximoParametro(String columna){
        String comando = "SELECT max("+ columna +") as maximo FROM dataset; " ;
        ResultSet resultado = modelo.buscarDatos(comando);
        double valor = 0;
            try {
                if (resultado != null) {
                    while (resultado.next()) {
                        valor = resultado.getDouble("maximo");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(naives.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Desde maximoParametro: ************************"+ valor);
        return valor;
    }
    
    public double minimoParametro(String columna){
        String comando = "SELECT min("+ columna +") as minimo FROM dataset; " ;
        ResultSet resultado = modelo.buscarDatos(comando);
        double valor = 0;
            try {
                if (resultado != null) {
                    while (resultado.next()) {
                        valor = resultado.getDouble("minimo");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(naives.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Desde minimoParametro: ************************"+ valor);
        return valor;
    }
    
    public double min_max(double min, double max, double x){
        double numerador = x - min;
        double denominador = max - min;
        System.out.println("Num " + numerador + " Den: " + denominador);
        return numerador / denominador; 
    }
    // </editor-fold>
    
}
