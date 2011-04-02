import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class Stats extends HttpServlet {

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
            
            //find most popular song
            ResultSet rs = stmt.executeQuery("SELECT song_name, max(votes) FROM songs");
            if(rs.next())
                req.setAttribute("most_popular_song", rs.getString("song_name"));
            rs.close();
            
            //get total songs
            ResultSet rs2 = stmt.executeQuery("SELECT count(*) as count FROM songs");
            if(rs2.next())
                req.setAttribute("total_songs", rs2.getString("count"));
            rs2.close();
            
            //get total users
            ResultSet rs3 = stmt.executeQuery("SELECT count(*) as count FROM users");
            if(rs3.next())
                req.setAttribute("total_users", rs3.getString("count"));
            rs3.close();
                        
            //calculate genre stats
            ResultSet rs4 = stmt.executeQuery("SELECT genre, count(*) as count FROM songs GROUP BY genre");
            String labels = "";
            String values = "";
            //format google charts api url, first entry has no | or ,
            if(rs4.next())
            {
                //make sure its safe for url
                String label = URLEncoder.encode(rs4.getString("genre"), "UTF-8");
                labels = label;
                values = rs4.getString("count");            
            }            
            while(rs4.next() )
            {
                //make sure its safe for url
                String label = URLEncoder.encode(rs4.getString("genre"), "UTF-8");
                labels = labels + "|" + label;
                values = values + "," + rs4.getString("count");
            }     
            rs4.close();
            String genres_url = "http://chart.apis.google.com/chart?chco=FF0000|0000FF|00FF00|FFC6A5|FFFF42|DEF3BD|00A5C6|DEBDDE&chd=t:" + values + "&chs=700x300&cht=p3&chl=" + labels + "&chtt=Genre%20Stats&.png";
            req.setAttribute("genres_url", genres_url);
            
            
            //calculate user playlist sizes
            ResultSet rs5 = stmt.executeQuery("SELECT full_name, count(*) as count FROM play_lists NATURAL JOIN users GROUP BY full_name");
            labels = "";
            values = "";
            //format google charts api url, first entry has no | or ,
            if(rs5.next())
            {
                //make sure its safe for url
                String label = URLEncoder.encode(rs5.getString("full_name"), "UTF-8");
                labels = label;
                values = rs5.getString("count");            
            }            
            while(rs5.next() )
            {
                //make sure its safe for url
                String label = URLEncoder.encode(rs5.getString("full_name"), "UTF-8");
                labels = labels + "|" + label;
                values = values + "," + rs5.getString("count");
            }     
            rs5.close();
            String url_users = "http://chart.apis.google.com/chart?chco=FF0000|0000FF|00FF00|FFC6A5|FFFF42|DEF3BD|00A5C6|DEBDDE&chd=t:" + values + "&chs=700x300&cht=p3&chl=" + labels + "&chtt=User%20Playlist%20Size%20Stats&.png";
            req.setAttribute("users_url", url_users);
                                    
            stmt.close();
            conn.close();
            
            req.getRequestDispatcher("stats.jsp").forward(req, res);

        }  catch (Exception e) {
            out.println(e.getMessage());
        }

    }
}