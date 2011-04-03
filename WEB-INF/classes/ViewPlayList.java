//ViewPlayList.java
//View a user's playlist

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;

public class ViewPlayList extends HttpServlet {

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
            ResultSet rs = stmt.executeQuery("SELECT * FROM play_lists NATURAL JOIN songs NATURAL JOIN users WHERE play_lists.user_id = '" + req.getParameter("user_id") + "' ORDER BY song_name");            

            String table = "<table class=\"stripe\"><tr><th>Song</th><th>Artist</th><th>Album</th><th>Genre</th><th>Votes</th></tr>";
            String full_name = null;
            int x = 0;
            while(rs.next() )
            {
                if(x % 2 == 0) { table = table + "<tr>";}
                else { table = table + "<tr class=\"alt\">"; }
                if(s.getAttribute("login") != null && (String) s.getAttribute("login") == "go")
                {
                    table = table + "<td width=\"25%\">" + rs.getString("song_name") + "<br /><a href=AddToPlayList?song_id=" + rs.getString("play_lists.song_id") + "&user_id=" + rs.getString("play_lists.user_id") + ">(Add to playlist)</a></td>";
                }
                else
                {
                    table = table + "<td width=\"25%\">" + rs.getString("song_name") + "</td>";
                }
                table = table + "<td width=\"25%\">" + rs.getString("artist") + "</td>"
                + "<td width=\"25%\">" + rs.getString("album") + "</td>"
                + "<td width=\"15%\">" + rs.getString("genre") + "</td>"
                + "<td width=\"10%\">" + rs.getString("votes") + " <a href=\"Vote?song_id=" + rs.getString("song_id") + "\">Vote</a></td></tr>";
                
                full_name = rs.getString("full_name");
                x++;
            }
            table = table + "</table>";
            
            rs.close();
            stmt.close();
            conn.close();
            
            req.setAttribute("table", table);     
            req.setAttribute("full_name", full_name );
            req.setAttribute("user_id", req.getParameter("user_id"));
            req.getRequestDispatcher("view_play_list.jsp").forward(req, res);

        }  catch (Exception e) {
            out.println(e.getMessage());
        }

    }
}