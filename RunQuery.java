package RunQuery;
import ConnectionString.ConnectionString;
import java.sql.Connection;
import java.sql.PreparedStatement;
public class RunQuery {
    private  static Connection con;
    private static PreparedStatement ps;
    public static PreparedStatement Run(String Query)
    {
        try {
            con = ConnectionString.getConnection();
            ps = con.prepareStatement(Query);
        }
        catch(Exception e)
        {
            System.out.println("Error Occur : " + e.getMessage());
        }
        return ps;
    }
}
