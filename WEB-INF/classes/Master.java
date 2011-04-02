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


            Statement stmt2 = conn.createStatement();
            ResultSet rs2 = stmt2.executeQuery("SELECT count(*) as total from songs");
            int total = 0;
            if(rs2.next())
            {
                total = rs2.getInt("total");
            }
            stmt2.close();
            rs2.close();
            
            String page_links = "";
            int current_page = current_page = Integer.parseInt(req.getParameter("page"));
            int offset = current_page * 20 - 20;
            int max_displayed = offset + 20;
            if(total > max_displayed && current_page > 1)
            {
                page_links = "<a href=\"Master?page=" + (current_page - 1) + "\">&lt;&lt; Previous 20</a> | <a href=\"Master?page=" + (current_page + 1) + "\">Next 20 &gt;&gt;</a>";
            }
            else if(total < max_displayed && current_page > 1)
            {
                page_links = "<a href=\"Master?page=" + (current_page - 1) + "\">&lt;&lt; Previous 20</a>";
            }
            else if(total > max_displayed)
            {
                page_links = "<a href=\"Master?page=" + (current_page + 1) + "\">Next 20 &gt;&gt;</a>";
            }
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM songs ORDER BY song_name LIMIT 20 OFFSET " + offset);

            String table = "<table class=\"stripe\"><tr><th>Song</th><th>Artist</th><th>Album</th><th>Genre</th><th>Votes</th></tr>";
            int x = 0;
            while(rs.next() )
            {
                if(x % 2 == 0) { table = table + "<tr>";}
                else { table = table + "<tr class=\"alt\">"; }
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
                    
                x++;
            }
            table = table + "</table>";
            
            rs.close();
            stmt.close();
            conn.close();
            
            req.setAttribute("table", table);
            req.setAttribute("page_links", page_links);
            req.getRequestDispatcher("master.jsp").forward(req, res);
            
        }  catch (Exception e) {
            out.println(e.getMessage());
        }

    }
}