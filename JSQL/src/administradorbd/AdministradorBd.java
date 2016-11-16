/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administradorbd;

import static java.lang.Math.exp;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author LUXS
 */
public class AdministradorBd {
 
   Connection conexion = null;
    Statement sentencia = null;
    String cadena = "org.sqlite.JDBC";
    String url = "jdbc:sqlite:pyme.sqlite";
    
    
   public void crearproducto()
    {
        try
        {
            Class.forName(cadena);
            conexion = DriverManager.getConnection(url);
            sentencia = conexion.createStatement();
            String sql = "CREATE TABLE PRODUCTO" + 
                    "(ID        INT     PRIMARY KEY NOT NULL,"+ 
                    " NOMBRE    TEXT    NOT NULL," +
                    " PRECIO  INT)";  
            sentencia.executeUpdate(sql);
            sentencia.close();
            conexion.close();
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.err.println("Error: " + e);
            System.exit(0);
        }
    }

 
   
    public void insertar(String[] datos)
    {
        try
        {
            Class.forName(cadena);
            conexion = DriverManager.getConnection(url);
            conexion.setAutoCommit(false);
            sentencia = conexion.createStatement();
            String sql = "INSERT INTO PRODUCTO (ID, NOMBRE,PRECIO) " +
                    "VALUES ("+Integer.parseInt(datos[0])+",'"+datos[1]+"',"+Integer.parseInt(datos[2])+")";
            
            
            sentencia.executeUpdate(sql);
            sentencia.close();
            conexion.commit();
            conexion.close();
        }
        
        catch(ClassNotFoundException | SQLException e)
        {
            
            System.err.println("Error: " + e);
            System.exit(0);
        }
    }
   
    public void mostrarDatos(JTable tabla_producto)
    {
        
        try
        {
            Class.forName(cadena);
            conexion = DriverManager.getConnection("jdbc:sqlite:pyme.sqlite");
            conexion.setAutoCommit(false);
            sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM  PRODUCTO ORDER BY ID ASC");
            int fila = 0;
            while(resultado.next())
            {
              
               tabla_producto.setValueAt(resultado.getInt("ID"), fila, 0);
               tabla_producto.setValueAt(resultado.getString("NOMBRE"), fila, 1);
               tabla_producto.setValueAt(resultado.getInt("PRECIO"), fila, 2);
               fila++;
            }
            
            resultado.close();
            sentencia.close();
            conexion.close();
             
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.err.println("Error : " + e.getMessage());
        }
        
    }
    
    public void update(String [] datos)
    {
        try
        {
            Class.forName(cadena);
            conexion = DriverManager.getConnection(url);
            conexion.setAutoCommit(false);
            sentencia = conexion.createStatement();
            String sql = "UPDATE PRODUCTO SET " +
                    "NOMBRE = '"+datos[1]+"',PRECIO="+datos[2]+" WHERE ID ="+datos[0]+"";
            
            sentencia.executeUpdate(sql);
            sentencia.close();
            conexion.commit();
            conexion.close();
        }
        
        catch(ClassNotFoundException | SQLException e)
        {
            
            System.err.println("Error: " + e);
            System.exit(0);
        }
    } 
    public void Borrar(String [] datos)
    {
        try
        {
            Class.forName(cadena);
            conexion = DriverManager.getConnection(url);
            conexion.setAutoCommit(false);
            sentencia = conexion.createStatement();
            String sql = "DELETE FROM PRODUCTO WHERE ID = "+datos[0]+" ";
            
            sentencia.executeUpdate(sql);
            sentencia.close();
            conexion.commit();
            conexion.close();
        }
        
        catch(ClassNotFoundException | SQLException e)
        {
            
            System.err.println("Error: " + e);
            System.exit(0);
        }
    }
public void Buscardato(JTable tabla_producto,String []datos)
    {
        
        try
        {
            Class.forName(cadena);
            conexion = DriverManager.getConnection("jdbc:sqlite:pyme.sqlite");
            conexion.setAutoCommit(false);
            sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM  PRODUCTO WHERE ID= "+datos[0]+"");
            int fila = 0;
          
            while(resultado.next())
            {  
               tabla_producto.setValueAt(resultado.getInt("ID"), fila, 0);
               tabla_producto.setValueAt(resultado.getString("NOMBRE"), fila, 1);
               tabla_producto.setValueAt(resultado.getInt("PRECIO"), fila, 2);
               fila++;
            }
            
            resultado.close();
            sentencia.close();
            conexion.close();
             
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.err.println("Error : " + e.getMessage());
        }
    }
public void ValidaExpediente(String [] datos) throws ClassNotFoundException {
        
    try{
            Class.forName(cadena);
            conexion = DriverManager.getConnection("jdbc:sqlite:pyme.sqlite");
            conexion.setAutoCommit(false);
            sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM  PRODUCTO WHERE ID= "+datos[0]+"");
                              
            while(resultado.next()){
                String aux = resultado.getString("ID");
                //System.out.println("Expediente: " + exp);
                if ( !datos.equals(aux) ){                
                    JOptionPane.showMessageDialog(null, "Expediente existe: " + aux, "Reingrese", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            
            resultado.close();            
             sentencia.close();
             conexion.close();
                
    } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    
}



