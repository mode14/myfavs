//AddToPlayList.java
//Class to add song to play list

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;

public class AddToPlayList extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
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
            stmt.execute( "INSERT INTO play_lists VALUES('" + req.getParameter("user_id") + "', '" + req.getParameter("song_id") + "')" );            
            
            req.getRequestDispatcher("add_to_play_list.jsp").forward(req, res); 
            
            stmt.close();
            conn.close();

        }  catch (Exception e) {
            out.println(e.getMessage());
        }

    }
}