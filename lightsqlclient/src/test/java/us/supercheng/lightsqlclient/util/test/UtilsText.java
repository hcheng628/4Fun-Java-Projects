package us.supercheng.lightsqlclient.util.test;

import org.junit.Before;
import org.junit.Test;
import us.supercheng.lightsqlclient.util.DBHelper;

import java.sql.*;

/**
 * Created by hongyu on 7/8/17.
 */
public class UtilsText {

    private DBHelper dbClient;

    @Before
    public void warmUp(){
        this.dbClient = new DBHelper("root","root","employees","127.0.0.1","3306","mysql");
    }

    @Test
    public void connectMySQL_Test(){
        Connection conn = null;
        try{
            // Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employees?serverTimezone=UCT&useSSL=false", "root","root");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM employees limit 10");
            while(rs.next()){
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
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

    @Test
    public void testSelectStatement() throws Exception{
        ResultSet rs = this.dbClient.selectQuery("SELECT * FROM employees limit 10");
        int colCount = rs.getMetaData().getColumnCount();
        while(rs.next()){
            for(int i=0;i<colCount;i++){
                System.out.print( rs.getMetaData().getColumnName(i+1) + ": " + rs.getString(i+1) + " ");
            }
            System.out.println();
        }
    }
}
