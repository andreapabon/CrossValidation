/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author Andrea
 */
public class Paciente {
    
    double edad;
    double genero;
    double colesterol;
    double sodio;
    double potasio;
    double bp;
    String droga;
    
    public Paciente(double edad, double genero, double bp, double colesterol, double sodio, double potasio, String droga) {
        this.edad = edad;
        this.genero = genero;
        this.colesterol = colesterol;
        this.sodio = sodio;
        this.potasio = potasio;
        this.bp = bp;
        this.droga = droga;
    }
    
    public Paciente(){
        
    }

    public double getEdad() {
        return edad;
    }

    public void setEdad(double edad) {
        this.edad = edad;
    }

    public double getGenero() {
        return genero;
    }

    public void setGenero(double genero) {
        this.genero = genero;
    }

    public double getColesterol() {
        return colesterol;
    }

    public void setColesterol(double colesterol) {
        this.colesterol = colesterol;
    }

    public double getSodio() {
        return sodio;
    }

    public void setSodio(double sodio) {
        this.sodio = sodio;
    }

    public double getPotasio() {
        return potasio;
    }

    public void setPotasio(double potasio) {
        this.potasio = potasio;
    }

    public double getBp() {
        return bp;
    }

    public void setBp(double bp) {
        this.bp = bp;
    }
     public void setDroga(String droga) {
        this.droga = droga;
    }
    public String getDroga() {
        return droga;
    }

    public double calcularDistanciaEuclidiana(Paciente p1, Paciente p2) {
      //  System.out.println("Calcular dis");
    // Declaro las variables para almacenar los valores que me llegan en el paciente 1
        double respuesta=0;
        double edad_uno=p1.edad;
        double genero1_uno=p1.genero;
        double colesterol_uno=p1.colesterol;
        double sodio_uno=p1.sodio;
        double potasio_uno=p1.potasio;
        double bp_uno=p1.bp;
    // Declaro las variables para almacenar los valores que me llegan en el paciente 2
        double edad_dos=p2.edad;
        double genero_dos=p2.genero;
        double colesterol_dos=p2.colesterol;
        double sodio_dos=p2.sodio;
        double potasio_dos=p2.potasio;
        double bp_dos=p2.bp;
        
        double sumatoria=0;
    // calculo la sumatoria (xi-yi)^2
        sumatoria=Math.pow((edad_uno-edad_dos), 2)+
                Math.pow((colesterol_uno-colesterol_dos), 2)+
                Math.pow((genero1_uno-genero_dos), 2)+
                Math.pow((sodio_uno-sodio_dos), 2)+
                Math.pow((potasio_uno-potasio_dos), 2)+
                Math.pow((bp_uno-bp_dos), 2);
        
        respuesta=Math.sqrt(sumatoria);
        return respuesta;

    }
    
    public int funcionDistanciaCategorias( String c1, String c2)
    {
        // comparo las cadenas c1 y c2 se son iguales devuelvo cero de lo contrario 1
        if(c1.equals(c2))
        {
            return 0;
        }
        else
        {
            return 1;
        }       
       
    }

}
