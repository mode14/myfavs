//Master.java
//Class to display master song list with paging

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;

public class Master extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException {
        
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        
        /* Get Session */
        HttpSession s = req.getSession();

        try{
            String dbuser = this.getServletContext().getInitParameter("dbuser");
            String dbpassword = this.getServletContext().getInitParameter("dbpassword");
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection ("jdbc:mysql://localhost/project", dbuser, dbpassword);


            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM songs ORDER BY song_name");

            String table = "<table class=\"display\" id=\"zebra\"><thead><tr><th>Song</th><th>Artist</th><th>Album</th><th>Genre</th><th>Votes</th></tr></thead>";
            while(rs.next() )
            {
                table = table + "<tr>";
                if(s.getAttribute("login") != null && (String) s.getAttribute("login") == "go")
                {
                    if(s.getAttribute("admin") != null && (String) s.getAttribute("admin") == "1")
                        table = table + "<td width=\"25%\">" + rs.getString("song_name") + "<br /><a href=Update?song_id=" + rs.getString("song_id") + ">Update</a> | <a href=AddToPlayList?song_id=" + rs.getString("song_id") + "&user_id=" + s.getAttribute("user_id") + ">(Add to playlist)</a></td>";
                    else
                        table = table + "<td width=\"25%\">" + rs.getString("song_name") + "<br /><a href=AddToPlayList?song_id=" + rs.getString("song_id") + "&user_id=" + s.getAttribute("user_id") + ">(Add to playlist)</a></td>";
                }
                else
                {
                    table = table + "<td width=\"25%\">" + rs.getString("song_name") + "</td>";
                }
                table = table + "<td width=\"25%\">" + rs.getString("artist") + "</td>"
                + "<td width=\"25%\">" + rs.getString("album") + "</td>"
                + "<td width=\"15%\">" + rs.getString("genre") + "</td>"
                + "<td width=\"10%\">" + rs.getString("votes") + " <a href=\"Vote?song_id=" + rs.getString("song_id") + "\">Vote</a></td></tr>";
                    
            }
            table = table + "</table>";
            
            rs.close();
            stmt.close();
            conn.close();
            
            req.setAttribute("table", table);
            req.getRequestDispatcher("master.jsp").forward(req, res);
            
        }  catch (Exception e) {
            out.println(e.getMessage());
        }

    }
}