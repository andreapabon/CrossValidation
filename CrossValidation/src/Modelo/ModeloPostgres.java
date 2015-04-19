/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Andre
 */
public class ModeloPostgres {

    public Connection conexion = null;
    public String login = "postgres";
    public String bd = "clasificacion";
    public String password = "123";
    //public final String CONTROLADOR_JDBC = "org.postgresql.Driver";

    public final String CONTROLADOR_JDBC = "org.postgresql.Driver";
    public final String URL_BASEDEDATOS = "jdbc:postgresql://127.0.0.1:5432/" + bd;
    Statement snt;

    public void agregarDatos(String comando) {
        try // todo bien
        {
            // Se registra el Driver de Postgres
            Class.forName(CONTROLADOR_JDBC);
            conexion = DriverManager.getConnection(URL_BASEDEDATOS, login, password);
            Statement estado = conexion.createStatement();
            estado.execute(comando);
            estado.close();
            conexion.close();
        } catch (SQLException e) // error de conexion
        {
            JOptionPane.showMessageDialog(null, "Hubo un problema con la conexion y no pudo completar la acción solicitada.", "Verifique",JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModeloPostgres.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public ResultSet buscarDatos(String comando) {
        try // todo bien
        {
            // Se registra el Driver de Postgres
            Class.forName(CONTROLADOR_JDBC);
            conexion = DriverManager.getConnection(URL_BASEDEDATOS, login, password);
            //JOptionPane.showMessageDialog(null, "YA ESTAS CONECTADO  A LA BASE DE DATOS", "Bienvenido", JOptionPane.INFORMATION_MESSAGE);
            Statement estado = conexion.createStatement();
            ResultSet resultado;
            resultado = estado.executeQuery(comando);
            conexion.close();
            if (!resultado.wasNull()) {
                return resultado;
            }
            estado.close();

        } catch (Exception e) // error de conexion
        {
            JOptionPane.showMessageDialog(null, "Hubo un problema con la conexion y no se encontro lo solicitado", "Verifique", JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
            //System.exit(0);
        }
        return null;
    }

    public void eliminarTodosClientes(String comando) {
        try // todo bien
        {
            // Se registra el Driver de Postgres
            Class.forName(CONTROLADOR_JDBC);
            conexion = DriverManager.getConnection(URL_BASEDEDATOS, login, password);
            // JOptionPane.showMessageDialog(null, "YA ESTAS CONECTADO  A LA BASE DE DATOS", "Bienvenido", JOptionPane.INFORMATION_MESSAGE);
            Statement estado = conexion.createStatement();
            estado.execute(comando);
            conexion.close();

        } catch (Exception e) // error de conexion
        {
            JOptionPane.showMessageDialog(null, "Hubo un problema con la conexion y no pudo completar la acción solicitada.", "Verifique", JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
        }
    }

    public void crearBackup(String ruta) {
        try {
            String path = ruta + "/clientespreferenciales.backup";
            System.out.println(path);
            Runtime r = Runtime.getRuntime();

            //PostgreSQL variables
            final ArrayList<String> baseCmds = new ArrayList<String>();
            baseCmds.add("C:/Program Files/PostgreSQL/9.3/bin/pg_dump");
            baseCmds.add("-i");
            baseCmds.add("-h");
            baseCmds.add("localhost");
            baseCmds.add("-p");
            baseCmds.add("5432");
            baseCmds.add("-U");
            baseCmds.add(login);
            baseCmds.add("-F");
            baseCmds.add("c");
            baseCmds.add("-b");
            baseCmds.add("-v");
            baseCmds.add("-f");

            baseCmds.add(path);
            baseCmds.add(bd);
            Process p;
            ProcessBuilder pb;

            /**
             * Ejecucion del proceso de respaldo
             */
            r = Runtime.getRuntime();
            pb = new ProcessBuilder(baseCmds);
            pb.environment().put("PGPASSWORD", password);
            pb.redirectErrorStream(true);
            p = pb.start();
            JOptionPane.showMessageDialog(null, "Su archivo de respaldo ClientesPreferenciales.backup fue Exitosamente creado", "Felicitaciones", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            //System.exit(0);
        }
    }
    
    public void restaurarBD(String ruta) {
        try {
            System.out.println(ruta);
            //crearBD(bd);
            String path = ruta ;
            Runtime r = Runtime.getRuntime();

            //PostgreSQL variables
            final ArrayList<String> baseCmds = new ArrayList<String>();
            baseCmds.add("C:/Program Files/PostgreSQL/9.3/bin/pg_restore");
            baseCmds.add("--host");
            baseCmds.add("localhost");
            baseCmds.add("--port");
            baseCmds.add("5432");
            baseCmds.add("--username");
            baseCmds.add(login);
            baseCmds.add("--dbname");
            baseCmds.add(bd);
            baseCmds.add("--verbose");
            baseCmds.add(path);
            Process p;
            ProcessBuilder pb;

            /**
             * Ejecucion del proceso de respaldo
             */
            r = Runtime.getRuntime();
            pb = new ProcessBuilder(baseCmds);
            pb.environment().put("PGPASSWORD", password);
            pb.redirectErrorStream(true);
            p = pb.start();
            JOptionPane.showMessageDialog(null, "La base de datos ha sido  Exitosamente restaurada", "Felicitaciones", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            //System.exit(0);
        }
    }
    
    public void crearBD(String nombreBD){
        try{
            Class.forName(CONTROLADOR_JDBC);
            conexion = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/", login, password);
            System.out.println("paso conexion");
            Statement estado = conexion.createStatement();
            String comando = "CREATE DATABASE " +  nombreBD ;
            estado.execute(comando);
            estado.close();
            conexion.close();
            } catch (Exception e) {
            e.printStackTrace();
            //System.exit(0);
        }
        
    }
    
    public void actualizar(String comando) {
        try // todo bien
        {
            // Se registra el Driver de Postgres
            Class.forName(CONTROLADOR_JDBC);
            conexion = DriverManager.getConnection(URL_BASEDEDATOS, login, password);
            Statement estado = conexion.createStatement();
            estado.execute(comando);
            estado.close();
            conexion.close();            
        } catch (Exception e) // error de conexion
        {
            JOptionPane.showMessageDialog(null, "Hubo un problema con la conexion y no pudo completar la acción solicitada.", "Verifique",JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
        }
    }
}
