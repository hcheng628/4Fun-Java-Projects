package us.supercheng.messenger.client.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cl799honchen on 7/3/2017.
 */
public class ClientMessengerView extends JFrame {

    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    private JButton btnSend;
    private JTextField txtSend;
    private JTextArea paneChatTxt;
    private JScrollPane scroll;

    public ClientMessengerView(){
        super("Bee Bee Messenger");
        GridBagConstraints layoutConstraints = new GridBagConstraints();
        JPanel panel = new JPanel(new GridBagLayout());

        this.btnSend = new JButton("Send");
        this.txtSend = new JTextField(43);
        this.paneChatTxt = new JTextArea(18,53);
        this.paneChatTxt.setEditable(false);
        this.scroll = new JScrollPane (this.paneChatTxt);
        this.scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        layoutConstraints.fill = GridBagConstraints.BOTH;
        // layoutConstraints.ipady = 372; //make this component tall
        layoutConstraints.gridwidth = 3;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        panel.add(this.scroll, layoutConstraints);

        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        layoutConstraints.ipady = 18;//reset to default
        layoutConstraints.anchor = GridBagConstraints.PAGE_END;
        layoutConstraints.weighty = 1.0;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 2;
        layoutConstraints.gridwidth = 2;//2 columns wide
        panel.add(this.txtSend, layoutConstraints);

//      layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
//      layoutConstraints.ipady = 0;//reset to default
        layoutConstraints.weighty = 1.0; //request any extra vertical space
        layoutConstraints.anchor = GridBagConstraints.PAGE_END; //bottom of space
        layoutConstraints.insets = new Insets(10,0,0,0); //top padding
        layoutConstraints.gridx = 2;//aligned with button 2
        layoutConstraints.gridwidth = 1;//2 columns wide
        layoutConstraints.gridy = 2; //third row
        panel.add(this.btnSend, layoutConstraints);

//        panel.add(paneChatTxt);

        //panel.setPreferredSize(new Dimension(640, 480));
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Chat Box"));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ClientMessengerView frame = new ClientMessengerView();
                frame.setVisible(true);
            }
        });
    }
}
