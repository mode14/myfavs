import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;

public class Update extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
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

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM songs WHERE song_id='" + req.getParameter("song_id") + "'");
            if(rs.next())
            {
                req.setAttribute("song_id", req.getParameter("song_id")); 
                req.setAttribute("song_name", rs.getString("song_name")); 
                req.setAttribute("artist", rs.getString("artist")); 
                req.setAttribute("album", rs.getString("album")); 
                req.setAttribute("genre", rs.getString("genre")); 
            }
            stmt.close();
            rs.close();
            req.getRequestDispatcher("update.jsp").forward(req, res); 
            
        }  catch (Exception e) {
            out.println(e.getMessage());
        }

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

            Statement stmt = conn.createStatement();
            stmt.execute( "UPDATE songs SET song_name = '" + req.getParameter("song_name") + "', artist = '" + req.getParameter("artist") + "', album = '" + req.getParameter("album") + "', genre = '" + req.getParameter("genre") + "' WHERE song_id = '" + req.getParameter("song_id") + "'" );
                      
            stmt.close();
            conn.close();
            
            req.getRequestDispatcher("update_success.jsp").forward(req, res); 
            
        }  catch (Exception e) {
            out.println(e.getMessage());
        }

    }
}