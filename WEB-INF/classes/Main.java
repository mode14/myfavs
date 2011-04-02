import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;

public class Main extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException {
        
        res.setContentType("text/html");
        
        /* Get Session */
        HttpSession s = req.getSession(true);
        
        req.getRequestDispatcher("main.jsp").forward(req, res);       

    }
}