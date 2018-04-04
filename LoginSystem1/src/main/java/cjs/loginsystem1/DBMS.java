/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cjs.loginsystem1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Cherry Rose Semeña
 */
public class DBMS {
    public static final String driver = "com.mysql.jdbc.Driver";
    public static final String url="jdbc:mysql://localhost/test";
    public static final String dbuser = "root";
    public static final String pwd = "cjs110292";
    
    ArrayList<Customer> customersList = new ArrayList();
    boolean isUser = false;
    Customer user;
    
    public void readDataCust(){
        System.out.println("READING DB");
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url,dbuser,pwd);
            Statement stmt = conn.createStatement();
            String sqlString = "Select * from users";
            ResultSet rs = stmt.executeQuery(sqlString);
            while(rs.next()){
                int id = rs.getInt("id");
                String un = rs.getString("userName");
                String pw = rs.getString("pwd");
                customersList.add(new Customer(id,un,pw));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException|SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean checkUser(String userName,String pw){
        for (Customer cust : customersList) {
            System.out.println("USER IS " + cust.getUname() + cust.getPwd());
            if(cust.getUname().equals(userName) && cust.getPwd().equals(pw)){
                isUser = true;
            }
        }
        System.out.println("IS USER" + isUser);
        return isUser;
    }

    public Customer getUser(String userName,String pw){
        for (Customer cust : customersList) {
            if(cust.getUname().equals(userName) && cust.getPwd().equals(pw)){
                user = new Customer(cust.getId(),cust.getUname(),cust.getPwd());
            }
        }
        return user;
    }
    
}
