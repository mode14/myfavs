import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;

public class Search extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException {
            req.getRequestDispatcher("search.jsp").forward(req, res);
        }
        
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException {
        
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        
        /* Get Session */
        HttpSession s = req.getSession(true);


        try{
            if(req.getParameter("song_name") == "" && req.getParameter("artist") == "" && req.getParameter("album") == "" && req.getParameter("genre") == "")
            {
                req.getRequestDispatcher("search.jsp?error=1").forward(req, res);
            }
            
            String dbuser = this.getServletContext().getInitParameter("dbuser");
            String dbpassword = this.getServletContext().getInitParameter("dbpassword");
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection ("jdbc:mysql://localhost/project", dbuser, dbpassword);

            Statement stmt = conn.createStatement();
            
            String SQLQuery = "SELECT * FROM songs WHERE ";
            String highlight = "<script>$(document).ready(function(){highlightSearchTerms(\"";
            
            if(req.getParameter("song_name") != "")
            {
                SQLQuery = SQLQuery + "song_name LIKE \"%" + req.getParameter("song_name").trim() + "%\" AND ";
                highlight = highlight + req.getParameter("song_name").trim() + " ";
            }

            if(req.getParameter("artist") != "")
            {
                SQLQuery = SQLQuery + "artist LIKE \"%" + req.getParameter("artist").trim() + "%\" AND ";
                highlight = highlight + req.getParameter("artist").trim() + " ";
            }
            
            if(req.getParameter("album") != "")
            {
                SQLQuery = SQLQuery + "album LIKE \"%" + req.getParameter("album").trim() + "%\" AND ";
                highlight = highlight + req.getParameter("album").trim() + " ";
            }
            
            if(req.getParameter("genre") != "")
            {
                SQLQuery = SQLQuery + "genre LIKE \"%" + req.getParameter("genre").trim() + "%\" AND ";
                highlight = highlight + req.getParameter("genre").trim() + " ";
            }
            SQLQuery = SQLQuery + "song_name is not null ORDER BY song_name";
            highlight = highlight.trim() + "\");});</script>";

            ResultSet rs = stmt.executeQuery(SQLQuery);
            
            String table = "<table class=\"stripe\"><tr><th>Song</th><th>Artist</th><th>Album</th><th>Genre</th><th>Votes</th></tr>";
            int x = 0;
            
            if(!rs.next())
                req.getRequestDispatcher("search.jsp?error=1").forward(req, res);
            
            do
            {
                if(x % 2 == 0) { table = table + "<tr>";}
                else { table = table + "<tr class=\"alt\">"; }
                if(s.getAttribute("login") != null && (String) s.getAttribute("login") == "go")
                {
                    table = table + "<td>" + rs.getString("song_name") + " <a href=AddToPlayList?song_id=" + rs.getString("song_id") + "&user_id=" + s.getAttribute("user_id") + ">(Add to playlist)</a></td>";
                }
                else
                {
                    table = table + "<td>" + rs.getString("song_name") + "</td>";
                }
                table = table + "<td>" + rs.getString("artist") + "</td>"
                + "<td>" + rs.getString("album") + "</td>"
                + "<td>" + rs.getString("genre") + "</td>"
                + "<td>" + rs.getString("votes") + " <a href=\"Vote?song_id=" + rs.getString("song_id") + "\">Vote</a></td></tr>";
                    
                x++;
            } while (rs.next());
            
            table = table + "</table>";

            rs.close();
            stmt.close();
            conn.close();
            
            req.setAttribute("table", table);
            req.setAttribute("highlight", highlight);
            req.getRequestDispatcher("search_results.jsp").forward(req, res);
            

        }  catch (Exception e) {
            out.println(e.getMessage());
        }

    }
}