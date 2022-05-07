/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doan_java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Admin
 */
public class ConnectToServer {
    
     public Connection conn;
     public   Statement myStmt=null;
     public   ResultSet myRs=null;
    public ConnectToServer(){
    try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
              String connectionUrl = "jdbc:sqlserver://DESKTOP-RCFES7K\\SQLEXPRESS:1433;databaseName=QLLinhKienPC_Laptop_java"; 
              String user = "sa";
              String pass = "123";
              Connection con = DriverManager.getConnection(connectionUrl, user, pass);
              if(con !=null){
                  System.out.println("ket noi thanh cong");
              }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new ConnectToServer();
    }
}
    

