import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;

public class AutoComplete extends HttpServlet {

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
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT " + req.getParameter("field") + " FROM songs WHERE " + req.getParameter("field") + " LIKE \"%" + req.getParameter("q") + "%\"");            

            while(rs.next() )
            {
                out.println(rs.getString(req.getParameter("field")));
            }
            
            rs.close();
            stmt.close();
            conn.close();
        }  catch (Exception e) {
            out.println(e.getMessage());
        }

    }
}