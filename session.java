import javax.servlet.http.*; 
import javax.
  
@WebServlet("/api") 
public class session extends HttpServlet { 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) { 
        // Retrieve the HttpSession object 
        HttpSession session = request.getSession(); 
  
        // Use HttpSession methods 
        session.setAttribute("username", "tata"); 
        String value = (String) session.getAttribute("username"); 
    } 
}