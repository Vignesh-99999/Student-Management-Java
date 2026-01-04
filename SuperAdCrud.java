package SuperAdCrud;


import RunQuery.RunQuery;
import PanelCode.PanelCode;
import javaapplication.LibrarianView;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import javax.swing.JOptionPane;


public class SuperAdCrud {
    private static PreparedStatement ps;
    private static ResultSet rs;
    static int rowCount=0;
    public static int InsertStu(String StuNam, String Enroll, String pass,String Cours ) {
        int ch = 0;
        try {
            ps= RunQuery.Run("select max(stu_id) from student");
            rs=ps.executeQuery();
            rs.next();
            int c=rs.getInt(1);
            ps = RunQuery.Run("INSERT INTO student(stu_id,Name, Enroll, Password, Course) VALUES (?,?,?,?,?)");
            ps.setInt(1, c+1);
            ps.setString(2, StuNam);
            ps.setString(3, Enroll);
            ps.setString(4, pass);
            ps.setString(5, Cours);
            ch = ps.executeUpdate();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ch;
    }
    public static int InsertLib(String LibNam, String userid, String pass ) {
        int ch = 0;
        try {
            ps= RunQuery.Run("select max(Librarian_id) from librarian");
            rs=ps.executeQuery();
            rs.next();
            int c=rs.getInt(1);
            ps = RunQuery.Run("INSERT INTO librarian(Librarian_id, Name, UserId, Password) VALUES (?,?,?,?)");
            ps.setInt(1, c+1);
            ps.setString(2, LibNam);
            ps.setString(3, userid);
            ps.setString(4, pass);
            ch = ps.executeUpdate();
            
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ch;
    }
    


}
