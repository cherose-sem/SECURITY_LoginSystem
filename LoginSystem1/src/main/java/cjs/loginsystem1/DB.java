package cjs.loginsystem1;

import static cjs.loginsystem1.DBMS.dbuser;
import static cjs.loginsystem1.DBMS.driver;
import static cjs.loginsystem1.DBMS.pwd;
import static cjs.loginsystem1.DBMS.url;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Cherry Rose Semeña
 */
public class DB {

    public static final String driver = "com.mysql.jdbc.Driver";
    public static final String url = "jdbc:mysql://localhost/test";
    public static final String dbuser = "root";
    public static final String pw = "cjs110292";

    Customer myuser = null;
    ArrayList<Customer> customersList = new ArrayList();

    public void checkUser(String user, String pwd) {
        System.out.println("READING DB");

        String s = "";
        String p = "";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, dbuser, pw);

            Statement st = conn.createStatement();
            String query = "SELECT * FROM  users where userName='" + user + "' and pwd='" + pwd + "'";
            System.out.println("QUERY " + query);
            System.out.println(query);
            ResultSet res = st.executeQuery(query);
            while (res.next()) {
                s = res.getString("userName");
                p = res.getString("pwd");
                System.out.println("RESULT: " + s + p);
                int id = res.getInt("id");
                myuser = new Customer(id, s, p);
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void checkUser2(String user, String pwd) {
        System.out.println("READING DB");

        String s = "";
        String p = "";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, dbuser, pw);

            PreparedStatement pst = conn.prepareStatement("SELECT * FROM  users where userName=? and pwd=?");
            pst.setString(1, user);
            pst.setString(2, pwd);
            System.out.println("QUERY " + pst);
            System.out.println(pst);
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                s = res.getString("userName");
                p = res.getString("pwd");
                System.out.println("RESULT: " + s + p);
                int id = res.getInt("id");
                myuser = new Customer(id, s, p);
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Customer getUser(String userName, String p) {
        return myuser;
    }

    public void checkUser3(String user, String pwd) {
        System.out.println("READING DB");

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, dbuser, pw);
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM  users2 where userName=?");
            pst.setString(1, user);
            System.out.println(pst);
            ResultSet res = pst.executeQuery();
            byte[] s = null;
            String p = "";
            String u = "";
            int id =  0;
            while (res.next()) {
                u = res.getString("userName");
                s = res.getBytes("salt");
                p = res.getString("pwHash");
                id = res.getInt("id");
            }
            String securePassword = getSecurePassword(pw, s);
            System.out.println("1" + securePassword + "2" + p);
            if(securePassword.equals(p)){
                System.out.println("SUCCESS 1");
                myuser = new Customer(id, u, p);
            }else{
                System.out.println("FAIL 1");
                myuser = null;
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean saveUser(String uname, String pw) throws NoSuchAlgorithmException {
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, dbuser, pwd);
            String sqlString = "INSERT INTO users2(userName,pwHash,salt) VALUES(?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setString(1, uname);
            byte[] salt = getSalt();
            String securePassword = getSecurePassword(pw, salt);
            stmt.setBytes(3, salt);
            stmt.setString(2, securePassword);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public ArrayList<Customer> readDataCust() {
        System.out.println("READING DB");
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, dbuser, pwd);
            Statement stmt = conn.createStatement();
            String sqlString = "Select * from users";
            ResultSet rs = stmt.executeQuery(sqlString);
            while (rs.next()) {
                int id = rs.getInt("id");
                String un = rs.getString("userName");
                String pw = rs.getString("pwd");
                customersList.add(new Customer(id, un, pw));
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }

        return customersList;
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException {
        //Always use a SecureRandom generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        //Create array for salt
        byte[] salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);
        //return salt
        return salt;
    }

    private static String getSecurePassword(String passwordToHash, byte[] salt) {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(salt);
            //Get the hash's bytes
            byte[] bytes = md.digest(passwordToHash.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
