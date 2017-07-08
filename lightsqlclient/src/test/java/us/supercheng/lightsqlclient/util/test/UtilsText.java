package us.supercheng.lightsqlclient.util.test;

import org.junit.Test;
import java.sql.*;

/**
 * Created by hongyu on 7/8/17.
 */
public class UtilsText {
    @Test
    public void connectMySQL_Test(){
        Connection conn = null;
        try{
            // Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/information_schema?serverTimezone=UCT&useSSL=false", "root","root");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM views");
            while(rs.next()){
                System.out.print(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
                //System.out.println(rs);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            if(conn != null){
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
