package us.supercheng.safe1pass.service;

import us.supercheng.safe1pass.view.RegisterView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hongyu on 7/16/17.
 */
public class RegisterViewService implements ActionListener {

    private CardLayout cardLayoutControl;
    private RegisterView selfView;

    public RegisterViewService ( RegisterView inSelfView, CardLayout incardLayoutControl) {
        this.selfView = inSelfView;
        this.cardLayoutControl = incardLayoutControl;
    }

    public void actionPerformed(ActionEvent e) {
        this.cardLayoutControl.show(this.selfView,"RegisterView");
    }
}