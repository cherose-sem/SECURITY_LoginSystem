/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cjs.loginsystem1;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Cherry Rose Semeña
 */
@WebServlet(name = "MyServlet", urlPatterns = {"/MyServlet"})
public class FrontControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");

//        DBMS db = new DBMS();
        DB db = new DB();
        HttpSession session = request.getSession(true);
        String do_this = request.getParameter("do_this");
        if (do_this == null) {
            response.sendRedirect("index.html");
            return;
        }
        ArrayList<Customer> users = new ArrayList();
        String userName = (String) request.getParameter("name");
        String pw = (String) request.getParameter("pwd");
        System.out.println("USER INPUT " + userName + " " + pw);
        switch (do_this) {
            case "logIn":
                db.checkUser2(userName, pw);
                Customer user = db.getUser(userName, pw);
                if (user != null) {
                    System.out.println("SUCCESS");
                    String msg = "WELCOME " + user.getUname().toUpperCase() + "! ;)";
                    session.setAttribute("msg", msg);
                    response.sendRedirect("welcome.jsp");
                } else {
                    System.out.println("FAIL");
                    String msg = "Invalid User/Password.";
                    session.setAttribute("msg", msg);
                    response.sendRedirect("error.jsp");
                }
                break;

            case "register":
                if(db.saveUser(userName, pw)){
                    response.sendRedirect("login.jsp");
                }else{
                    String msg = "Something went wrong.";
                    session.setAttribute("msg", msg);
                    response.sendRedirect("error.jsp");
                }
                break;

            case "search":
                users = db.readDataCust();
                session.setAttribute("users", users);
                for (Customer user1 : users) {
                    System.out.println("USERS" + user1);
                }
                response.sendRedirect("index.html");
                break;
        }

    }

    private void forward(HttpServletRequest req, HttpServletResponse res, String path) throws IOException, ServletException {
        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher(path);
        rd.forward(req, res);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FrontControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FrontControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
