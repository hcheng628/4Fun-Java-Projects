package us.supercheng.lightsqlclient.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by hongyu on 7/8/17.
 */
public class DBHelper {
    private Connection conn;
    private Statement statement;
    private String dbURL;
    private String dbPort;
    private String dbUsername;
    private String dbPassword;
    private String dbDB_SchemaName;
    private String dbDriverName;

    public boolean isConnectionFlag() {
        return connectionFlag;
    }

    private boolean connectionFlag;

    public DBHelper(String inDBUsername, String inDBPassword, String inDB_SchemaName, String inDBURL, String inDBPort, String inDBDriverName){
        this.dbUsername = inDBUsername;
        this.dbPassword = inDBPassword;
        this.dbDB_SchemaName = inDB_SchemaName;
        this.dbURL = inDBURL;
        this.dbPort = inDBPort;
        this.dbDriverName = inDBDriverName;
        this.conn = null;
        this.statement = null;
    }

    @Override
    public String toString() {
        return "DBHelper{" +
                "conn=" + conn +
                ", statement=" + statement +
                ", dbURL='" + dbURL + '\'' +
                ", dbPort='" + dbPort + '\'' +
                ", dbUsername='" + dbUsername + '\'' +
                ", dbPassword='" + dbPassword + '\'' +
                ", dbDB_SchemaName='" + dbDB_SchemaName + '\'' +
                ", dbDriverName='" + dbDriverName + '\'' +
                ", connectionFlag=" + connectionFlag +
                '}';
    }

    public boolean connectDB(){

        try{
            this.conn = DriverManager.getConnection("jdbc:" + this.dbDriverName +
                            "://" + this.dbURL + ":" + this.dbPort + "/" + this.dbDB_SchemaName + "?serverTimezone=UCT&useSSL=false",
                    this.dbUsername,this.dbPassword);



            this.statement = conn.createStatement();
            this.connectionFlag = true;
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public boolean disconnectDB(){
        try{
            if(this.conn != null){
                this.conn.close();
            }
            this.connectionFlag = false;
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            this.connectionFlag = false;
            return false;
        }
    }

    public int executeQuery(String inQuery) throws Exception {
        return this.statement.executeUpdate(inQuery);
    }

    public ResultSet selectQuery(String inQuery) throws Exception {
        return this.statement.executeQuery(inQuery);
    }

    public Connection getConnection(){
        return this.conn;
    }
}