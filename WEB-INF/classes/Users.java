import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;

public class Users extends HttpServlet {

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
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE user_id IN (SELECT DISTINCT user_id from play_lists) ORDER BY full_name");

            String table = "<table class=\"stripe\"><tr><th>User</th></tr>";
            int x = 0;
            while(rs.next() )
            {
                if(x % 2 == 0) { table = table + "<tr>";}
                else { table = table + "<tr class=\"alt\">"; }
                table = table + "<td><a href=\"ViewPlayList?user_id=" + rs.getString("user_id") + "\">" + rs.getString("full_name") + "</a></td></tr>";
                    
                x++;
            }
            table = table + "</table>";

            rs.close();
            stmt.close();
            conn.close();
            
            req.setAttribute("table", table);                    
            req.getRequestDispatcher("users.jsp").forward(req, res);

        }  catch (Exception e) {
            out.println(e.getMessage());
        }

    }
}