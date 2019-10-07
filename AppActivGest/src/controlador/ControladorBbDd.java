package controlador;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;


public class ControladorBbDd {

    private Connection conexion = null;
    private String centro = "";
    protected static ObjectContainer db4o;
    private static Properties config;

    public ControladorBbDd(String centro) {
        this.centro = centro;
        // en caso de que el centro civico sea ....
        switch (this.centro) {

            case "Hegoalde":

                try {
                    // cargo el driver para operar en sqlite y obtener conexion
                    Class.forName("org.sqlite.JDBC");
                    this.conexion = DriverManager.getConnection("jdbc:sqlite:hegoalde.db");
                    if (conexion != null) {
                        System.out.println("Conectado");
                    }
                } catch (Exception ex) {
                    System.err.println("No se ha podido conectar a la base de datos\n" + ex.getMessage());
                }

                break;
            case "Ibaiondo":

                String url = "jdbc:postgresql://0.0.0.0:5433/Ibaiondo";
                String user = "abcde";
                String passw = "abcde";
                try {
                    Class.forName("org.postgresql.Driver");
                    this.conexion = DriverManager.getConnection(url,user,passw);
                } catch (SQLException e) {
                    javax.swing.JOptionPane.showMessageDialog(null ,"Ha ocurrido un problema \n"+e.getMessage());
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    javax.swing.JOptionPane.showMessageDialog(null ,"Ha ocurrido un problema \n"+e.getMessage());
                    e.printStackTrace();
                }
                break;



            case "arriaga":

                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/actigest", "root", "");
                    System.out.println("Conexión establecida con arriaga correctamente.");

                }catch (Exception e) {
                    System.err.println("No se ha podido conectar a la base de datos Arriaga\n"+e.getMessage());
                }


                break;
            default:
        }
    }

    public static ObjectContainer obtenerDb4o() {

        String BDIparralde = config.getProperty("db4o");

        ControladorBbDd.db4o = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), BDIparralde);

        return db4o;

    }

    // seccion getters
    public Connection getConexion() {
        return conexion;
    }
}
