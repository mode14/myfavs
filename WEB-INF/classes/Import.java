//Import.java
//Used to import from iTunes XML file
//iTunes import modified from http://www.alloscomp.com/iTunesXMLParser.html

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;

import java.io.*;
import java.util.*;

import net.spy.memcached.*;
import java.net.InetSocketAddress;

public class Import extends HttpServlet {
        
    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException {
        
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        
        /* Get Session */
        HttpSession s = req.getSession(true);
        /* Make sure user is logged in */
        if(s.getAttribute("login") == null || (String) s.getAttribute("login") != "go")
        {
            req.getRequestDispatcher("login.jsp").forward(req, res);
        }
            
        try{

            String dbuser = this.getServletContext().getInitParameter("dbuser");
            String dbpassword = this.getServletContext().getInitParameter("dbpassword");
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection ("jdbc:mysql://localhost/project", dbuser, dbpassword);

		        BufferedReader in = new BufferedReader(new FileReader(this.getServletContext().getRealPath("/") + "/WEB-INF/Library.xml"));
		        String line = "";
		        boolean started = false;
		        String song_name = "";
		        String artist = "";
		        String album = "";
		        String genre = "";
		
		        while(line != null) {
				        line = in.readLine();
				        if(line.indexOf("<key>Tracks</key>") != -1) {
					          started = true;
					          in.readLine();
				        }
				        else if(!started);
				        else if(line.indexOf("<key>Playlists</key>") != -1)
					          break; // we're done with the songs
				        else if(line.indexOf("<dict>") != -1)
				        {
					          song_name = "";
					          artist = "";
					          album = "";
					          genre = "";
				        }
				        else if(line.indexOf("<key>Name</key>") != -1)
					          song_name = line.substring(line.indexOf("<string>") + 8, line.indexOf("</s"));
				        else if(line.indexOf("<key>Artist</key>") != -1)
					          artist = line.substring(line.indexOf("<string>") + 8, line.indexOf("</s"));
				        else if(line.indexOf("<key>Album</key>") != -1)
					          album = line.substring(line.indexOf("<string>") + 8, line.indexOf("</s"));
				        else if(line.indexOf("<key>Genre</key>") != -1)
					          genre = line.substring(line.indexOf("<string>") + 8, line.indexOf("</s"));
				        else if(line.indexOf("</dict>") != -1)
				        {
				            Statement stmt = conn.createStatement();
                    stmt.execute( "INSERT INTO songs VALUES(null, \"" + song_name + "\", \"" + artist + "\", \"" + album + "\", \"" + genre + "\", 0)" );
                    stmt.close();
                }
		        }

            in.close();
            conn.close();
            
            //reset memcache since we have new songs
            MemcachedClient c=new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
            c.delete("master");
            
            req.getRequestDispatcher("import_success.jsp").forward(req, res); 
            
        }  catch (Exception e) {
            out.println(e.getMessage());
        }

    }
}