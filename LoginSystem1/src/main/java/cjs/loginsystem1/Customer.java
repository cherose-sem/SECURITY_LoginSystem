/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cjs.loginsystem1;

/**
 *
 * @author Cherry Rose Semeña
 */
public class Customer {

    private int id;
    private String uname;
    private String pwd;

    public Customer(int id, String uname, String pw) {
        this.id = id;
        this.uname = uname;
        this.pwd = pw;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pw) {
        this.pwd = pw;
    }
    
    
    
}
