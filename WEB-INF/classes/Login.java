//Login.java
//Class to handle login

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;

public class Login extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException {
            req.getRequestDispatcher("login.jsp").forward(req, res);        
        }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException {
        
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        
        /* Get Session */
        HttpSession s = req.getSession(true);


        try{
            String dbuser = this.getServletContext().getInitParameter("dbuser");
            String dbpassword = this.getServletContext().getInitParameter("dbpassword");
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection ("jdbc:mysql://localhost/project", dbuser, dbpassword);

            //Prepared statement for security
            Statement stmt = conn.createStatement();
            String selectStatement = "SELECT * FROM users where user= ? AND password=MD5(?)";
            PreparedStatement prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1, req.getParameter("user"));
            prepStmt.setString(2, req.getParameter("password"));
            
            ResultSet rs = prepStmt.executeQuery();
            //ResultSet rs = stmt.executeQuery();            

            if( rs.next() ) 
            {            
                if(rs.getString("admin").equals("1") )
                {
                    s.setAttribute("admin", "1");
                }
                s.setAttribute("login", "go");
                s.setAttribute("full_name", rs.getString("full_name"));   
                s.setAttribute("user_id", rs.getString("user_id"));                     
                res.sendRedirect("Main");
            }
            else
            {
                req.getRequestDispatcher("login.jsp?error=1").forward(req, res); 
            }
            
            rs.close();
            prepStmt.close();
            conn.close();

        }  catch (Exception e) {
            out.println(e.getMessage());
        }

    }
}