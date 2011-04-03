//AddSong.java
//Class to add new song to master list

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;

public class AddSong extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException {
            req.getRequestDispatcher("add_song.jsp").forward(req, res); 
        }
        
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException {
        
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        
        /* Get Session */
        HttpSession s = req.getSession(true);
        /* Make sure user is logged in */
        if(s.getAttribute("login") == null || (String) s.getAttribute("login") != "go")
        {
            req.getRequestDispatcher("login.jsp").forward(req, res);
        }

        try{
            String dbuser = this.getServletContext().getInitParameter("dbuser");
            String dbpassword = this.getServletContext().getInitParameter("dbpassword");
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection ("jdbc:mysql://localhost/project", dbuser, dbpassword);

            Statement stmt = conn.createStatement();
            stmt.execute( "INSERT INTO songs VALUES(null, '" + req.getParameter("song_name") + "', '" + req.getParameter("artist") + "', '" + req.getParameter("album") + "', '" + req.getParameter("genre") + "', 0)" );
                      
            stmt.close();
            conn.close();
            
            req.getRequestDispatcher("add_song_success.jsp").forward(req, res); 
            
        }  catch (Exception e) {
            out.println(e.getMessage());
        }

    }
}