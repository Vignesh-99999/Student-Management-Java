package ConnectionString;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionString {
    static Connection con =null;
    public static Connection getConnection()
    {
        try
        {
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/VSLIBRARY", "root", "");
        }
        catch(Exception e)
        {
            System.out.println("Error Occur : " +e.getMessage());
        }

      return con;
    }
}
