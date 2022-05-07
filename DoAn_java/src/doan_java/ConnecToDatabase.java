/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doan_java;

import java.sql.*;
/**
 *
 * @author admin
 */
public class ConnecToDatabase {
     public Connection conn;
     public   Statement myStmt=null;
     public   ResultSet myRs=null;
     
    public Connection KetNoiCSDL(){
        String user="sa";
        String pass="123456";
        try{
            conn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLLinhKienPC_Laptop_java",user,pass);
            System.out.println("Thanh Cong");
            return conn;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return conn;
    }

}

