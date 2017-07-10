package us.supercheng.lightsqlclient.view;

import us.supercheng.lightsqlclient.service.DBClientPaneService;
import us.supercheng.lightsqlclient.util.DBHelper;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.Vector;

/**
 * Created by hongyu on 7/8/17.
 */
public class DBClientPaneView extends JPanel{
    private Vector<Button> buttons;
    private JTextArea sqlTextArea;
    private JTable queryTable;
    private DBClientPaneService dbClientPaneService;
    private DBHelper dbHelper;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    public  DBClientPaneView(DBHelper inDBHelper){
        this.dbHelper = inDBHelper;
        this.buttons = new Vector<Button>();
        this.buttons.add(new Button("Select Query"));
        this.buttons.add(new Button("Execute Query"));
        this.sqlTextArea = new JTextArea(10,50);
        this.sqlTextArea.setText("select count(1) as 'Count' from employees;");
        this.dbClientPaneService = new DBClientPaneService(this.dbHelper, this);
        this.queryTable = new JTable();
        queryTable.setMinimumSize(new Dimension(530,630));
        this.queryTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.scrollPane = new JScrollPane(this.queryTable);
        this.tableModel = (DefaultTableModel) this.queryTable.getModel();
        this.setPreferredSize(new Dimension(550,650));
        this.add(this.sqlTextArea, BorderLayout.NORTH);
        this.add(this.buttons.get(0),BorderLayout.CENTER);
        this.add(this.buttons.get(1),BorderLayout.LINE_END);
        this.add(scrollPane,BorderLayout.SOUTH);
        this.buttons.get(0).addActionListener(this.dbClientPaneService);
        this.buttons.get(1).addActionListener(this.dbClientPaneService);
    }

    public JTextArea getSqlTextArea() {
        return sqlTextArea;
    }

    public void setSqlTextArea(JTextArea inSqlTextArea) {
        this.sqlTextArea = inSqlTextArea;
    }

    public JTable getQueryTable() {
        return queryTable;
    }

    public void setQueryTable(JTable inQueryTable) {
        this.queryTable = inQueryTable;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

}