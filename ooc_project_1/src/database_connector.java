import java.sql.*;
public class  database_connector{
    public  static  Statement st;
    public  static  Connection con;
    protected Connection connection() throws Exception{
        String url="jdbc:mysql://localhost:3306/miniproject";
        String uname="root";
        String pass="mysql@#$6550";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        return con;
/*      Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);

 */

    }
/*  public void input_con_signUP(String database,String user,String passWord)throws  Exception
    {
        String url="jdbc:mysql://localhost:3306/"+database;
        String uname="root";
        String pass="mysql@#$6550";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        String query="insert into passwords values(?,?)";
        PreparedStatement st=con.prepareStatement(query);
        st.setString(1,user);
        st.setString(2,passWord);
        st.executeUpdate();
        st.close();
        con.close();
    }

 */
}