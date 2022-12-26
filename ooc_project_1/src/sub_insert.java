import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class sub_insert {
    public static void main(String[] args) {
        try {
            /*//database_connector c = new database_connector();
            //Connection con = c.connection();
            database_connector c = new database_connector();
            String year="1st_year";
            String sec="A";
            Connection con = c.connection();
            String query1="select usn from student_data where year='"+year+"' and section='"+sec+"';";

            Statement st=con.createStatement();
            ResultSet rs =st.executeQuery(query1);
            while(rs.next()) {
                String n = rs.getString(1);
                System.out.println("n:" + n);
            }*/
            database_connector c = new database_connector();
            Connection con = c.connection();
            String query1="select count(usn) from student_data where year='"+"1st_year"+"' and section='"+"A"+"';";
            Statement st=con.createStatement();
            ResultSet rs =st.executeQuery(query1);
            rs.next();
            int n=rs.getInt(1);
            System.out.println("n:"+n);
            String[] lst=new String[n+1];
            String query2="select usn from student_data where year='"+"1st_year"+"' and section='"+"A"+"';";
            //st=con.createStatement();
            ResultSet rk =st.executeQuery(query2);
            int i=0;
            while(rk.next())
            {
                lst[i]=rk.getString(1);
                System.out.println(lst[i]);
                i++;
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
//            String query="insert into student_data value (?,?,?);";
//            PreparedStatement st=con.prepareStatement(query);
//            FileReader usn = new FileReader("4_USN_A.txt");
//            BufferedReader br = new BufferedReader(usn);
//            String line;
//
//            while ((line = br.readLine())!= null) {
//                //PreparedStatement st=con.prepareStatement(query);
//                st.setString(1,line);
//                st.setString(2,"4th_year");
//                st.setString(3,"A");
//                st.executeUpdate();
//            }