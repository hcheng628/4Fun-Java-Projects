package us.supercheng.lightsqlclient.entity;

import javax.swing.*;
import java.util.Hashtable;
import java.util.Set;

/**
 * Created by cl799honchen on 7/10/2017.
 */
public class PanelManager {
    private Hashtable<String, JPanel> panelTable;

    public PanelManager(){
        this.panelTable = new Hashtable<String,JPanel>();
    }

    public void addToPanelList(String inPanelName, JPanel inJPanel){
        this.panelTable.put(inPanelName,inJPanel);
    }

    public Hashtable<String, JPanel> getPanelTable() {
        return panelTable;
    }

    public void setPanelList(Hashtable<String, JPanel> inPanelTable) {
        this.panelTable = inPanelTable;
    }

    public void addAllPanestoJFrame(JFrame inFrame){
        Set<String> keys = this.panelTable.keySet();
        for(String eachKey : keys){
            inFrame.add(this.panelTable.get(eachKey));
        }
    }
}