/*******************************************************************************
 * Copyright (c) 2005,2014 IBM Corporation and other Contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Ian Vanstone - Initial Contribution
 *******************************************************************************/
package tlswizard.wizard;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/******************************************************************/
/* Class: WMQSSLWizardHelpDialog                                  */
/* Description:                                                   */
/* Parms:                                                         */
/* Returns:                                                       */
/******************************************************************/
class WMQSSLWizardHelpDialog extends Dialog {

  Frame pf;
  JPanel aboutPanel = new JPanel();
  JLabel lblDetails;
 
  JLabel lblWebSite = new JLabel("  http://github.com/ibm-messaging/mq-tls-ssl-wizard  ",JLabel.CENTER);
  
  JPanel okPanel = new JPanel();
  JButton btnOK = new JButton("OK");

  /****************************************************************/
  /* Function: WMQSSLWizardHelpDialog                             */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  WMQSSLWizardHelpDialog(String Name, String details, Frame f ) {

    super(f, Name, false);
    pf = f;
    setTitle(Name);
    setLayout(new BorderLayout());
    aboutPanel.setLayout(new GridLayout(5,1));
    lblDetails = new JLabel(details, JLabel.CENTER);
    aboutPanel.add(lblDetails);
   
    aboutPanel.add(lblWebSite);
   
              
    btnOK.addActionListener (new ActionListener () {
      public void actionPerformed (ActionEvent e) {
        dispose();
      }
    }); // end btnOK.addActionListener
    addWindowListener(new WindowAdapter() {
       public void windowClosing(WindowEvent e) {
         dispose();
       }
    });
    
    //btnOK.setBackground(Color.white);
    okPanel.add(btnOK);
    aboutPanel.add(okPanel);
    setResizable(false);
    add("Center", aboutPanel);
    pack();
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    setLocation((d.width/2) - this.getSize().width/2,
                (d.height/2) - this.getSize().height/2);
    setVisible(true);
  } 
}
