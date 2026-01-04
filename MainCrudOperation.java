
package MainCrud;
import RunQuery.RunQuery;
import PanelCode.PanelCode;
import javaapplication.LibrarianView;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
public class MainCrudOperation {
    private static PreparedStatement ps;
    private static ResultSet rs;
    static int rowCount=0;
    public static int Insert(String BookName, String Category, int quantity) {
        int ch = 0;
        try {
            ps= RunQuery.Run("select max(Book_id) from Book");
            rs=ps.executeQuery();
            rs.next();
            int c=rs.getInt(1);
            ps = RunQuery.Run("insert into Book(Book_id,Book_Name,Category,Quantity) values(?,?,?,?)");
            ps.setInt(1, c+1);
            ps.setString(2, BookName);
            ps.setString(3, Category);
            ps.setInt(4, quantity);
            ch = ps.executeUpdate();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ch;
    }
    public static int DeleteBook(int bookId)
    {

        try
        {
            ps=RunQuery.Run("delete from book where book_id =?");
            ps.setInt(1,bookId);
            rowCount=ps.executeUpdate();
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return rowCount;
    }

    public static ResultSet Search(int BookID)
    {
        try
        {
            ps=RunQuery.Run("select * from book where book_id = ?");
            ps.setInt(1,BookID);
            rs=ps.executeQuery();
            rs.next();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    public static int DeleteCategory(String BookCat)
    {
        try
        {
            ps=RunQuery.Run("delete from book where category = ?");
            ps.setString(1,BookCat);
            rowCount=ps.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return rowCount;
    }

    public static int BookReturn(int BookId , String Enroll)
    {
        try
        {
            ps=RunQuery.Run("delete from issue where bookid = ? and Enroll = ? ");
            ps.setInt(1,BookId);
            ps.setString(2,Enroll);
            rowCount=ps.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return rowCount;

    }

    public static ResultSet Display()
    {
        try
        {
            ps=RunQuery.Run("SELECT * FROM book");
            rs=ps.executeQuery();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return rs;
    }
    public static void SendRequest(int BookId,String username)
    {
        try
        {
            // ps=RunQuery.Run("select stu_id from student where Enroll  =(select username from log_table where log_id =(select max(log_id) from log_table)) ");
            // rs=ps.executeQuery();
            // rs.next();
            // int stu = rs.getInt("stu_id");
            // ps=RunQuery.Run("insert into request (Book_id,stu_id) values(?,?)");
            // ps.setInt(1,BookId);
            // ps.setInt(2, stu);
            // ps.executeUpdate();

            ps=RunQuery.Run("Select stu_id from student where Enroll=?");
            ps.setString(1, username);
            rs=ps.executeQuery();
            rs.next();
            int stu = rs.getInt("stu_id");
            ps=RunQuery.Run("insert into request (Book_id,stu_id) values(?,?)");
            ps.setInt(1,BookId);
            ps.setInt(2, stu);
            ps.executeUpdate();
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static void InsertLog(String username,String password)
    {
        try
        {
            ps=RunQuery.Run("insert into Log_table (username,password) values(?,?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static <T>void IssuedBookSearch(T id)
    {
        
    }


    public static ResultSet DisplayRequest()
    {
        try
        {
            ps=RunQuery.Run("select Request_id,Name,stu_id,Book_Name,Book_id from student natural join request natural join book");
            rs=ps.executeQuery();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    public static void AcceptRequest(int Book)
    {
        try
        {
            LocalDate currentDate = LocalDate.now();
            ps=RunQuery.Run("select book_id,stu_id from request where book_id = ?");
            ps.setInt(1,Book);
            rs=ps.executeQuery();
            rs.next();
            ps=RunQuery.Run("Delete from request where book_id =? ");
            ps.setInt(1,Book);
            ps.executeUpdate();
            ps=RunQuery.Run("insert into issued (stu_id,book_id,date) values(?,?,?)");
            ps.setInt(1,rs.getInt("stu_id"));
            ps.setInt(2,rs.getInt("Book_id"));
            ps.setDate(3, java.sql.Date.valueOf(currentDate));
            ps.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static ResultSet DisplayCat()
    {
        try
        {
            ps=RunQuery.Run(" select distinct category from book");
            rs=ps.executeQuery();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return rs;

    }

    public static void Issue(int id, String enroll)
    {

        try
        {
            ps=RunQuery.Run("delete from issued where book_id =? and stu_id =(select stu_id from student where Enroll = ?)");
            ps.setInt(1,id);
            ps.setString(2,enroll);
            ps.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    public static ResultSet DisplayUserBook()
    {
        try
        {
            ps=RunQuery.Run("select stu_id from student where Enroll  =(select username from log_table where log_id =(select max(log_id) from log_table)) ");
            rs=ps.executeQuery();
            rs.next();
            int stu = rs.getInt("stu_id");
            ps=RunQuery.Run("select Book_id,Book_Name,Category from book where book_id in (select book_id from issued where stu_id = ?)");
            ps.setInt(1, stu);
            rs=ps.executeQuery();
        }
        catch(Exception e)
    {
        System.out.println(e.getMessage());
    }
    return rs;
    }
    public static ResultSet DisplayId()
    {
        try
        {
            ps=RunQuery.Run("select book_id from book");
            rs=ps.executeQuery();

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return rs;

    }
    public static void Update(int Book,String Cat,int Quan,String Name)
    {
        try
        {
            ps=RunQuery.Run("Update book set Category = ? ,Quantity = ? , Book_Name = ? where Book_id =?");
            ps.setString(1, Cat);
            ps.setString(3, Name);
            ps.setInt(2, Quan);
            ps.setInt(4, Book);
            ps.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println("sadasd");
            e.printStackTrace();
        }
    }

    public static ResultSet AuthenticationUser(String username,String password)
    {
        try
        {
            ps=RunQuery.Run("Select count(*) from student where Enroll =? and password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs=ps.executeQuery();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return rs;
    }
    public static ResultSet AuthenticationAdmin(String username,String password)
    {
        try
        {
            ps=RunQuery.Run("Select count(*) from librarian where userid =? and password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs=ps.executeQuery();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return rs;
    }
}
