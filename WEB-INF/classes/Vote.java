//Vote.java
//Class to handle voting on songs

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;

import net.spy.memcached.*;
import java.net.InetSocketAddress;

public class Vote extends HttpServlet {

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
            ResultSet rs = stmt.executeQuery("SELECT * FROM songs WHERE song_id = '" + req.getParameter("song_id") + "'");

            int votes = 0;
            if( rs.next() )
            {
                votes = rs.getInt("votes");
            }
            
            rs.close();
            votes++;
            
            stmt.close();
            Statement stmt2 = conn.createStatement();
            stmt2.execute( "UPDATE songs set votes = " + votes + " WHERE song_id = '" + req.getParameter("song_id") + "'" );

            stmt2.close();
            conn.close();
            
            //delete memcache entry since votes are updated
            MemcachedClient c=new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
            c.delete("master");
                        
            req.getRequestDispatcher("vote_success.jsp").forward(req, res);
            
        }  catch (Exception e) {
            out.println(e.getMessage());
        }

    }
}