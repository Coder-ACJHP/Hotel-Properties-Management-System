/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coder.hms.utils;

import java.awt.Color;
import java.awt.KeyEventDispatcher;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JButton;

/**
 *
 * @author dell-pc
 */
public class CustomKeyDispatcher implements KeyEventDispatcher {

    private JButton capslockBtn;
    
    
    public CustomKeyDispatcher(JButton theButton) {
        this.capslockBtn = theButton;
    }

    
    
    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            boolean isOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);

            if (isOn) {
                capslockBtn.setBackground(Color.RED);
                capslockBtn.revalidate();
                capslockBtn.repaint();
            }

        } else if (e.getID() == KeyEvent.KEY_RELEASED) {
            boolean isOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);

            if (isOn == false) {
                capslockBtn.setBackground(capslockBtn.getForeground());
                capslockBtn.revalidate();
                capslockBtn.repaint();
            }

        }
        return false;
    }

}
