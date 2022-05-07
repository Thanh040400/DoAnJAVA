/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Color;
import javax.swing.JTextField;

/**
 *
 * @author DELL
 */
public class Validation {

    public static boolean check(JTextField field, StringBuffer sb, String msg) {
        boolean oke = false;

        if (field.getText().equals("")) {
            sb.append(msg).append("\n");
            field.setBackground(Color.red);
            oke = false;
        } else {
            field.setBackground(Color.white);
        }
        return oke;
    }

    public static boolean checkPhone(JTextField field, StringBuffer sb) {
        boolean oke = true;

        if (!check(field, sb, "Bạn chưa nhập số điện thoại !!!")) {
            return false;
        }
        try {
            String numberPhone = "\\d";
            
            
            String input = field.getText();

            oke = input.matches(numberPhone);
            
            if (oke) {
                sb.append("Số điện thoại không hợp lệ (SĐT phải có tối thiểu là 10 số và tối đa là 11 số)!!");
                field.setBackground(Color.red);
                oke = true;
            }
            else {
              field.setBackground(Color.white);
            }
        } catch (Exception e) {
            field.setBackground(Color.red);
            oke = false;
        }
        
        
        return oke;
    }

}
