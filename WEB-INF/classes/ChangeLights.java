import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class ChangeLights extends HttpServlet
{
    public void doGet (HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException
    {
        HttpSession s = req.getSession(true);

        res.setContentType ("text/html");
        if (s.getAttribute("lights") == "off")
        {
            s.setAttribute ("lights","");
        }
        else
        {
            s.setAttribute ("lights","off");
        }
        res.sendRedirect(s.getAttribute("url").toString());

    }
}

