
    /**
     * Created by tong on 4/4/17.
     */


    import java.io.FileNotFoundException;
    import java.io.FileReader;
    import java.io.IOException;
    import java.sql.*;

    import java.sql.Connection;
    import java.sql.Date;
    import java.sql.DriverManager;
    import java.sql.Statement;
    import java.text.ParseException;
    import java.text.SimpleDateFormat;
    import java.util.*;


    public class PostgreSQLJDBC {
        private final String url="jdbc:postgresql://localhost:5432/project1";
        private final String user="ricedb";
        private final String password="zt940107";
        private Connection conn=null;
        public void connect() throws  SQLException{
            try {
                Class.forName("org.postgresql.Driver");
                conn=DriverManager.getConnection(url,user,password);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.getClass().getName()+": "+e.getMessage());
                System.exit(0);
            }

        }




        public static void main(String args[]) throws FileNotFoundException {
            PostgreSQLJDBC project = new PostgreSQLJDBC();
            try {
                project.connect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            InsertDataFromFile insertFromfile=new InsertDataFromFile(project.conn);
            insertFromfile.LoadData("/Users/user/Desktop/COMP533/assignments/A6/Assignment6_Data_from_ERD.csv");

        }
    }