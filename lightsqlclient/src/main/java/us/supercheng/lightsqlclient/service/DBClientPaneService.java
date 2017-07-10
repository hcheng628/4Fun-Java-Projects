package us.supercheng.lightsqlclient.service;

import us.supercheng.lightsqlclient.util.DBHelper;
import us.supercheng.lightsqlclient.view.DBClientPaneView;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

/**
 * Created by hongyu on 7/9/17.
 */
public class DBClientPaneService implements ActionListener {
    private final String Select_Btn_Label = "Select Query";
    private final String Execute_Btn_Label = "Execute Query";

    private DBHelper dbHelper;
    private DBClientPaneView dbClientPaneView;

    public DBClientPaneService(DBHelper inDBHelper, DBClientPaneView inDBClientPaneView){
        this.dbHelper = inDBHelper;
        this.dbClientPaneView = inDBClientPaneView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Button button = (Button) e.getSource();
        if(button.getLabel().equals(Select_Btn_Label)){
            try{
                this.updateJTable(this.dbHelper.selectQuery(this.dbClientPaneView.getSqlTextArea().getText()));
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }else if(button.getLabel().equals(Execute_Btn_Label)){
            try{
                this.updateJTable(this.dbHelper.executeQuery(this.dbClientPaneView.getSqlTextArea().getText()));
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    private void updateJTable(ResultSet rs) throws Exception{
        this.dbClientPaneView.getTableModel().setColumnCount(0);
        this.dbClientPaneView.getTableModel().setRowCount(0);

        for(int i=0;i<rs.getMetaData().getColumnCount();i++){
            this.dbClientPaneView.getTableModel().addColumn(rs.getMetaData().getColumnName(i+1));
        }

        int rowcount = 0;
        if (rs.last()) {
            rowcount = rs.getRow();
            rs.beforeFirst(); // not rs.first() bc the rs.next() below will move on, missing the first element
        }

        while(rs.next()){
            int index = 0;
            String [] record = new String[rs.getMetaData().getColumnCount()];;
            for(int cols=0;cols < rs.getMetaData().getColumnCount();cols++){
                record[cols] = rs.getString(cols+1);
                // System.out.print("[" + cols + "]" + record[cols]);
            }
            this.dbClientPaneView.getTableModel().addRow(record);
            index++;
        }
    }

    private void updateJTable(int count) throws Exception{
        this.dbClientPaneView.getTableModel().setColumnCount(0);
        this.dbClientPaneView.getTableModel().setRowCount(0);
        this.dbClientPaneView.getTableModel().addColumn("Result");
        String [] resultRow = {count + ""};
        this.dbClientPaneView.getTableModel().addRow(resultRow);
    }
}
