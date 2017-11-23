/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coder.hms.utils;

import java.awt.Component;
import java.awt.ComponentOrientation;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class ChangeComponentOrientation {

    private JMenuBar theMenuBar;
    private JPanel thePanel;
    private JDialog theDialog;
    private JFrame theFrame;

    public void setTheDialog(JDialog dialog) {
        this.theDialog = dialog;
    }

    public void setTheFrame(JFrame frame) {
        this.theFrame = frame;
    }

    public void setThePanel(JPanel panel) {
        this.thePanel = panel;
    }

    public void setTheMenuBar(JMenuBar menuBar) {
        this.theMenuBar = menuBar;
    }

    
    public ChangeComponentOrientation() {
    }

    public void changeOrientationOfJDialogToRight() {
        theDialog.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        for (int i = 0; i < theDialog.getContentPane().getComponentCount(); i++) {
            if (theDialog.getContentPane().getComponent(i).getComponentOrientation().isLeftToRight()) {
                theDialog.getContentPane().getComponent(i).setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                theDialog.revalidate();
                theDialog.repaint();
            }
        }
    }

    public void changeOrientationOfJDialogToLeft() {
        theDialog.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        for (int i = 0; i < theDialog.getContentPane().getComponentCount(); i++) {
            if (!theDialog.getContentPane().getComponent(i).getComponentOrientation().isLeftToRight()) {
                theDialog.getContentPane().getComponent(i).setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                theDialog.revalidate();
                theDialog.repaint();
            }
        }

    }

    public void changeOrientationOfJFrameToRight() {
        theFrame.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        for (int i = 0; i < theFrame.getContentPane().getComponentCount(); i++) {
            if (theFrame.getContentPane().getComponent(i).getComponentOrientation().isLeftToRight()) {
                theFrame.getContentPane().getComponent(i).setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                theFrame.revalidate();
                theFrame.repaint();
            }
        }
    }

    public void changeOrientationOfJFrameToLeft() {
        theFrame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        for (int i = 0; i < theFrame.getContentPane().getComponentCount(); i++) {
            if (!theFrame.getContentPane().getComponent(i).getComponentOrientation().isLeftToRight()) {
                theFrame.getContentPane().getComponent(i).setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                theFrame.revalidate();
                theFrame.repaint();
            }
        }

    }
    
    public void changeOrientationOfJPanelToRight() {
        
        thePanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        Component panelComponents[] = thePanel.getComponents();
        
        for (int i = 0; i < panelComponents.length; i++) {
            
            if(panelComponents[i] instanceof JPanel) {
                JPanel panel = (JPanel) panelComponents[i];
                if(panel.getComponentOrientation().isLeftToRight()) {
                    panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                }
            }
            
            if(panelComponents[i] instanceof JButton) {
                JButton button = (JButton) panelComponents[i];
                if(button.getComponentOrientation().isLeftToRight()) {
                    button.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                }
            }
        }
    }

    public void changeOrientationOfJPanelToLeft() {
        
        thePanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        Component panelComponents[] = thePanel.getComponents();
        
        for (int i = 0; i < panelComponents.length; i++) {
            
            if(panelComponents[i] instanceof JPanel) {
                JPanel panel = (JPanel) panelComponents[i];
                if(!panel.getComponentOrientation().isLeftToRight()) {
                    panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                }
            }
            
            if(panelComponents[i] instanceof JButton) {
                JButton button = (JButton) panelComponents[i];
                if(!button.getComponentOrientation().isLeftToRight()) {
                    button.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                }
            }
        }
    }
    
      public void changeOrientationOfJMenubarToRight() {
          theMenuBar.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        for (int i = 0; i < theMenuBar.getComponentCount(); i++) {
            if (theMenuBar.getComponent(i).getComponentOrientation().isLeftToRight()) {
                theMenuBar.getComponent(i).applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                theMenuBar.revalidate();
                theMenuBar.repaint();
            }
        }
    }

    public void changeOrientationOfJMenubarToLeft() {
        theMenuBar.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        for (int i = 0; i < theMenuBar.getComponentCount(); i++) {
            if (!theMenuBar.getComponent(i).getComponentOrientation().isLeftToRight()) {
                theMenuBar.getComponent(i).applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                theMenuBar.revalidate();
                theMenuBar.repaint();
            }
        }

    }
}
