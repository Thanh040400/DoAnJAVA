/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doan_java;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author DELL
 */
public class ConnectToSQL {
    public ConnectToSQL(){
    try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
              String connectionUrl = "jdbc:sqlserver://DESKTOP-8I56L51:1433;databaseName=QLLinhKienPC_Laptop_java"; 
              String user = "sa";
              String pass = "123456";
              Connection con = DriverManager.getConnection(connectionUrl, user, pass);
              if(con !=null){
                  System.out.println("ket noi thanh cong");
              }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new ConnectToSQL();
    }
}
