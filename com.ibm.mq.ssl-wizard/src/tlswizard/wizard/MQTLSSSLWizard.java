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


/******************************************************************/
/*                                                                */
/*  IBM MQ TLS/SSL Wizard  -originally by Ian Vanstone            */ 
/*                                                                */
/*    It is customary to swear about the previous programmer, so  */
/*  please remember the name above and use it well as you venture */
/*                        south.                                  */
/*                                                                */
/******************************************************************/
/*  Change history:                                               */
/*                                                                */
/*  170805 - V1.0 - Initial creation                              */
/*  -      - V1.1 -                                               */
/*  -   06 - V1.2 - Client support, etc                           */
/*  271106 - V1.3 - Fixes                                         */
/*  011209 - V2.0 - Fixes + separate CA machine                   */
/*  270110 - V2.0.0.1 - SSLPEER quotes and italic font fix        */
/*  081214 - V2.0.0.2 - Release to GitHub                         */
/*                                                                */
/*  See tlswizard.odp for function change history                 */
/*                                                                */
/******************************************************************/

package tlswizard.wizard;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;

//import sun.net.www.content.image.gif;

public class MQTLSSSLWizard extends JFrame  implements ActionListener
                                                 , ComponentListener
                                                 , KeyListener {
  private static final int VERSION = 2002;

  /****************************************************************/
  /* RC constants                                                 */
  /****************************************************************/
  private static final int RC_OK     = 0;
  private static final int RC_FAILED = 1;
  private static final int RC_WRONG_VERSION = 2;

  /****************************************************************/
  /* Page number constants                                        */
  /****************************************************************/
  private static final int PAGE_SNDR        = 1;
  private static final int PAGE_SNDRZOS     = 2;
  private static final int PAGE_SNDRUNIXWIN = 3;
  private static final int PAGE_SNDRCAPI    = 4; 
  private static final int PAGE_RCVR        = 5; 
  private static final int PAGE_RCVRZOS     = 6; 
  private static final int PAGE_RCVRUNIXWIN = 7; 
  private static final int PAGE_RCVRCAPI    = 8; 
  private static final int PAGE_CHL         = 9; 
  private static final int PAGE_CAMODEL     = 10;
  private static final int PAGE_CAWHERE     = 11;
  private static final int PAGE_CEAU        = 12;
  private static final int PAGE_CEAUZOS     = 13;
  private static final int PAGE_CEAUUNIXWIN = 14;
  private static final int PAGE_CEAUCAPI    = 15;
  private static final int PAGE_CEAUCERT    = 16;
  private static final int PAGE_SNDRCERT    = 17;
  private static final int PAGE_RCVRCERT    = 18;
  private static final int PAGE_GENERATE    = 19;
  private static final int PAGE_SUMMARY     = 20;
  private int currentPage;               // Current page 
                                         //      (Note: The previous comment has been
                                         //       nominated for comment of the year 2004)  

  /****************************************************************/
  /* Radio button constants                                       */
  /****************************************************************/
  private static final String CHOICE_SS = "CSS";
  private static final String CHOICE_CA = "CCA";
  private static final String CHOICE_CAI = "CAI";
  private static final String CHOICE_CAX = "CAX";
  private static final String CHOICE_CLIENT = "CSCLIENT";
  private static final String CHOICE_SERVER = "CSSERVER";
  private static final String CHOICE_JAVA = "JAVA";
  private static final String CHOICE_OTHER = "OTHER";

  /****************************************************************/
  /* Menus                                                        */
  /****************************************************************/
  private JMenuBar menuBar;
  private JMenu	   menuFile;
  private JMenu	   menuEdit;
  private JMenu	   menuHelp;
  private JMenuItem menuFileNew;
  private JMenuItem menuFileOpen;
  private JMenuItem menuFileSaveAs;
  private JMenuItem menuFileExit;
  private JMenuItem menuEditCopy;
  private JMenuItem menuHelpHelp;
  private JMenuItem menuHelpAbout;

  /****************************************************************/
  /* Page panels                                                  */
  /****************************************************************/
  private JPanel    pnlNAV         ; 
  private JPanel    pnlTXT         ; 

  private JPanel    pnl1SNDR         ; 
  private JPanel    pnl1SNDRZOS      ; 
  private JPanel    pnl1SNDRUNIXWIN  ; 
  private JPanel    pnl1SNDRCAPI     ; 
  private JPanel    pnl1RCVR        ; 
  private JPanel    pnl1RCVRZOS     ; 
  private JPanel    pnl1RCVRUNIXWIN ; 
  private JPanel    pnl1RCVRCAPI    ; 
  private JPanel    pnl1CHL         ; 
  private JPanel    pnl1CAMODEL     ; 
  private JPanel    pnl1CAWHERE     ; 
  private JPanel    pnl1CEAU          ; 
  private JPanel    pnl1CEAUZOS       ; 
  private JPanel    pnl1CEAUUNIXWIN   ; 
  private JPanel    pnl1CEAUCAPI      ; 
  private JPanel    pnl1CEAUCERT      ; 
  private JPanel    pnl1SNDRCERT     ; 
  private JPanel    pnl1RCVRCERT    ; 

  private JPanel    pnl2SNDR         ; 
  private JPanel    pnl2SNDRZOS      ; 
  private JPanel    pnl2SNDRUNIXWIN  ; 
  private JPanel    pnl2SNDRCAPI     ; 
  private JPanel    pnl2RCVR        ; 
  private JPanel    pnl2RCVRZOS     ; 
  private JPanel    pnl2RCVRUNIXWIN ; 
  private JPanel    pnl2RCVRCAPI    ; 
  private JPanel    pnl2CHL         ; 
  private JPanel    pnl2CAMODEL     ; 
  private JPanel    pnl2CAWHERE     ; 
  private JPanel    pnl2CEAU          ; 
  private JPanel    pnl2CEAUZOS       ; 
  private JPanel    pnl2CEAUUNIXWIN   ; 
  private JPanel    pnl2CEAUCAPI      ; 
  private JPanel    pnl2CEAUCERT      ; 
  private JPanel    pnl2SNDRCERT     ; 
  private JPanel    pnl2RCVRCERT    ; 

  private JPanel    pnlGENERATE    ; 
  private JPanel    pnlSUMMARY     ; 

  /****************************************************************/
  /* Images                                                       */
  /****************************************************************/
  private ImageIcon icon01SNDR1_Q       ;
  private ImageIcon icon02SNDR1_C       ;
  private ImageIcon icon03SNDR2_Q       ;
  private ImageIcon icon04SNDR2_C       ;
  private ImageIcon icon05RCVR1_Q        ;
  private ImageIcon icon06RCVR1_C        ;
  private ImageIcon icon07RCVR2_Q        ;
  private ImageIcon icon08RCVR2_C        ;
  private ImageIcon icon09CHL_Q         ;
  private ImageIcon icon10CHL_C         ;
  private ImageIcon icon11SNDRCERT_Q    ;
  private ImageIcon icon12SNDRCERT_C    ;
  private ImageIcon icon13RCVRCERT_Q_N  ;
  private ImageIcon icon14RCVRCERT_C_N  ;
  private ImageIcon icon15RCVRCERT_Q_Y  ;
  private ImageIcon icon16RCVRCERT_C_Y  ;
  private ImageIcon icon17CAMODEL_Q_N   ;
  private ImageIcon icon18CAMODEL_C_N   ;
  private ImageIcon icon19CAMODEL_Q_Y   ;
  private ImageIcon icon20CAMODEL_C_Y   ;
  private ImageIcon icon21CAWHERE_Q_N_E ;
  private ImageIcon icon22CAWHERE_C_N_E ;
  private ImageIcon icon23CAWHERE_Q_Y_E ;
  private ImageIcon icon24CAWHERE_C_Y_E ;
  private ImageIcon icon25CAWHERE_Q_N_I ;
  private ImageIcon icon26CAWHERE_C_N_I ;
  private ImageIcon icon27CAWHERE_Q_Y_I ;
  private ImageIcon icon28CAWHERE_C_Y_I ;
  private ImageIcon icon29CEAU1_Q       ;
  private ImageIcon icon30CEAU1_C       ;
  private ImageIcon icon31CEAU2_Q       ;
  private ImageIcon icon32CEAU2_C       ;
  private ImageIcon icon33CEAUCERT_Q    ;
  private ImageIcon icon34CEAUCERT_C    ;

  private JLabel lbl01SNDR1_Q       ;
  private JLabel lbl02SNDR1_C       ;
  private JLabel lbl03SNDR2_Qa       ;
  private JLabel lbl04SNDR2_Ca       ;
  private JLabel lbl03SNDR2_Qb       ;
  private JLabel lbl04SNDR2_Cb       ;
  private JLabel lbl03SNDR2_Qc       ;
  private JLabel lbl04SNDR2_Cc       ;
  private JLabel lbl05RCVR1_Q        ;
  private JLabel lbl06RCVR1_C        ;
  private JLabel lbl07RCVR2_Qa        ;
  private JLabel lbl08RCVR2_Ca        ;
  private JLabel lbl07RCVR2_Qb        ;
  private JLabel lbl08RCVR2_Cb        ;
  private JLabel lbl07RCVR2_Qc        ;
  private JLabel lbl08RCVR2_Cc        ;
  private JLabel lbl09CHL_Q         ;
  private JLabel lbl10CHL_C         ;
  private JLabel lbl11SNDRCERT_Q    ;
  private JLabel lbl12SNDRCERT_C    ;
  private JLabel lbl13RCVRCERT_Q_N  ;
  private JLabel lbl14RCVRCERT_C_N  ;
  private JLabel lbl15RCVRCERT_Q_Y  ;
  private JLabel lbl16RCVRCERT_C_Y  ;
  private JLabel lbl17CAMODEL_Q_N   ;
  private JLabel lbl18CAMODEL_C_N   ;
  private JLabel lbl19CAMODEL_Q_Y   ;
  private JLabel lbl20CAMODEL_C_Y   ;
  private JLabel lbl21CAWHERE_Q_N_E ;
  private JLabel lbl22CAWHERE_C_N_E ;
  private JLabel lbl23CAWHERE_Q_Y_E ;
  private JLabel lbl24CAWHERE_C_Y_E ;
  private JLabel lbl25CAWHERE_Q_N_I ;
  private JLabel lbl26CAWHERE_C_N_I ;
  private JLabel lbl27CAWHERE_Q_Y_I ;
  private JLabel lbl28CAWHERE_C_Y_I ;
  private JLabel lbl29CEAU1_Q       ;
  private JLabel lbl30CEAU1_C       ;
  private JLabel lbl31CEAU2_Qa       ;
  private JLabel lbl32CEAU2_Ca       ;
  private JLabel lbl31CEAU2_Qb       ;
  private JLabel lbl32CEAU2_Cb       ;
  private JLabel lbl31CEAU2_Qc       ;
  private JLabel lbl32CEAU2_Cc       ;
  private JLabel lbl33CEAUCERT_Q    ;
  private JLabel lbl34CEAUCERT_C    ;


  /****************************************************************/
  /* Page widgets                                                 */
  /****************************************************************/
  // All pages
  private JButton    btnPrev = new JButton("Previous");
  private JButton    btnNext = new JButton("Next");
  private JTextField txtMsg = new JTextField(48);

  // Page
  private JLabel     lblPAGE_SNDRTitle = new JLabel("SSL Client Properties:"); 
  private JRadioButton radCSClient = new JRadioButton("Client User");
  private JRadioButton radCSServer = new JRadioButton("QMGR Name");
  private ButtonGroup grpCS = new ButtonGroup();
  private JRadioButton radJOJava = new JRadioButton("Java/JMS");
  private JRadioButton radJOOther = new JRadioButton("C");
  private ButtonGroup grpJO = new ButtonGroup();
  private JTextField txtSNDRUser = new JTextField("",48);
  private JTextField txtSNDRQmgrName = new JTextField("",48);
  private JLabel     lblSNDRHost = new JLabel("Host:"); 
  private JTextField txtSNDRHost = new JTextField("",48);
  private JLabel     lblSNDRPlatform = new JLabel("Platform:"); 
  private JComboBox  chcSNDRPlatform = new JComboBox();
  
  // Page
  private JLabel     lblPAGE_SNDRZOSTitle = new JLabel("SSL Client z/OS Properties:"); 
  private JLabel     lblSNDRTasks = new JLabel("SSL Tasks:"); 
  private JTextField txtSNDRTasks = new JTextField("", 5);
  private JLabel     lblSNDRID = new JLabel("Chinit ID:"); 
  private JTextField txtSNDRID = new JTextField("", 48);
  private JLabel     lblSNDRKeyringZOS = new JLabel("Keyring:"); 
  private JTextField txtSNDRKeyringZOS = new JTextField("",48);
  
  // Page
  private JLabel     lblPAGE_SNDRUNIXWINTitle = new JLabel("SSL Client UNIX/Windows Properties:"); 
  private JLabel     lblSNDRKeyringUNIXWIN = new JLabel("Key Database:"); 
  private JTextField txtSNDRKeyringUNIXWIN = new JTextField("",100);
  private JLabel     lblSNDRCommand = new JLabel("Command:"); 
  private JComboBox  chcSNDRCommand = new JComboBox();
  private JButton    btnSNDRBrowse = new JButton("Browse");
          
  // Page 
  private JLabel     lblPAGE_SNDRCAPITitle = new JLabel("SSL Client gsk7capicmd Properties:"); 
  private JLabel     lblSNDRFips = new JLabel("FIPS:"); 
  private JCheckBox  cbxSNDRFips = new JCheckBox();
  private JLabel     lblSNDRSigalg = new JLabel("Sigalg:"); 
  private JComboBox  chcSNDRSigalg = new JComboBox();

  // Page 
  private JLabel     lblPAGE_RCVRTitle = new JLabel("SSL Server Properties:"); 
  private JLabel     lblRCVRQmgrName = new JLabel("QMGR Name:"); 
  private JTextField txtRCVRQmgrName = new JTextField("",48);
  private JLabel     lblRCVRHost = new JLabel("Host:"); 
  private JTextField txtRCVRHost = new JTextField("",48);
  private JLabel     lblRCVRPort = new JLabel("Port:"); 
  private JTextField txtRCVRPort = new JTextField("",48);
  private JLabel     lblRCVRPlatform = new JLabel("Platform:"); 
  private JComboBox  chcRCVRPlatform = new JComboBox();
          
  // Page 
  private JLabel     lblPAGE_RCVRZOSTitle = new JLabel("SSL Server z/OS Properties:"); 
  private JLabel     lblRCVRTasks = new JLabel("SSL Tasks:"); 
  private JTextField txtRCVRTasks = new JTextField("",5);
  private JLabel     lblRCVRID = new JLabel("Chinit ID:"); 
  private JTextField txtRCVRID = new JTextField("", 48);
  private JLabel     lblRCVRKeyringZOS = new JLabel("Keyring:"); 
  private JTextField txtRCVRKeyringZOS = new JTextField("",48);
          
  // Page 
  private JLabel     lblPAGE_RCVRUNIXWINTitle = new JLabel("SSL Server UNIX/Windows Properties:"); 
  private JLabel     lblRCVRKeyringUNIXWIN = new JLabel("Key Database:"); 
  private JTextField txtRCVRKeyringUNIXWIN = new JTextField("",100);
  private JLabel     lblRCVRCommand = new JLabel("Command:"); 
  private JComboBox  chcRCVRCommand = new JComboBox();
  private JButton    btnRCVRBrowse = new JButton("Browse");
          
  // Page 
  private JLabel     lblPAGE_RCVRCAPITitle = new JLabel("SSL Server gsk7capicmd Properties:"); 
  private JLabel     lblRCVRFips = new JLabel("FIPS:"); 
  private JCheckBox  cbxRCVRFips = new JCheckBox();
  private JLabel     lblRCVRSigalg = new JLabel("Sigalg:"); 
  private JComboBox  chcRCVRSigalg = new JComboBox();

  // Page 
  private JLabel     lblPAGE_CHLTitle = new JLabel("Channel Properties:"); 
  private JLabel     lblChannel = new JLabel("Channel name:"); 
  private JTextField txtChannel = new JTextField("",48);
  private JLabel     lblQmgrFips = new JLabel("SSLFIPS:"); 
  private JCheckBox  cbxQmgrFips = new JCheckBox();
  private JLabel     lblCipherSpec = new JLabel("SSLCIPH:"); 
  private JComboBox  chcCipherSpec =  new JComboBox();
  private JLabel     lblClientAuth = new JLabel("SSLCAUTH:"); 
  private JRadioButton radAuReq = new JRadioButton("Authenticate SSL client and server");
  private JRadioButton radAuOpt = new JRadioButton("Authenticate SSL server only");
  private ButtonGroup grpAu = new ButtonGroup();
          
  // Page 
  private JLabel     lblPAGE_CAMODELTitle = new JLabel("Certificate setup choice:"); 
  private JRadioButton radTypeSS = new JRadioButton("Self signed certificates");
  private JRadioButton radTypeCA = new JRadioButton("CA signed certificates");
  private ButtonGroup grpCA = new ButtonGroup();

  // Page 
  private JLabel     lblPAGE_CAWHERETitle = new JLabel("CA setup choice:"); 
  private JRadioButton radTypeCAX = new JRadioButton("Use an existing CA");
  private JRadioButton radTypeCAI = new JRadioButton("Create a CA");
  private ButtonGroup grpIX = new ButtonGroup();

  // Page
  private JLabel     lblPAGE_CEAUTitle = new JLabel("CA System:"); 
  private JLabel     lblCEAUHost = new JLabel("Host:"); 
  private JTextField txtCEAUHost = new JTextField("",48);
  private JLabel     lblCEAUPlatform = new JLabel("Platform:"); 
  private JComboBox  chcCEAUPlatform = new JComboBox();

  // Page 
  private JLabel     lblPAGE_CEAUZOSTitle = new JLabel("CA z/OS Properties:"); 
  private JLabel     lblCEAUID = new JLabel("CA ID:"); 
  private JTextField txtCEAUID = new JTextField("", 48);
  private JLabel     lblCEAUKeyringZOS = new JLabel("Keyring:"); 
  private JTextField txtCEAUKeyringZOS = new JTextField("",48);

  // Page 
  private JLabel     lblPAGE_CEAUUNIXWINTitle = new JLabel("CA UNIX/Windows Properties:"); 
  private JLabel     lblCEAUKeyringUNIXWIN = new JLabel("Key Database:"); 
  private JTextField txtCEAUKeyringUNIXWIN = new JTextField("",100);
  private JLabel     lblCEAUCommand = new JLabel("Command:"); 
  private JComboBox  chcCEAUCommand = new JComboBox();
  private JButton    btnCEAUBrowse = new JButton("Browse");

  // Page 
  private JLabel     lblPAGE_CEAUCAPITitle = new JLabel("CA gsk7capicmd Properties:"); 
  private JLabel     lblCEAUFips = new JLabel("FIPS:"); 
  private JCheckBox  cbxCEAUFips = new JCheckBox();
  private JLabel     lblCEAUSigalg = new JLabel("Sigalg:"); 
  private JComboBox  chcCEAUSigalg = new JComboBox();

  // Page 
  private JLabel     lblPAGE_CEAUCERTTitle = new JLabel("CA Certificate Properties:"); 
  private JLabel     lblCEAULabel = new JLabel("Cert. label:"); 
  private JTextField txtCEAULabel = new JTextField("",48);
  private JLabel     lblCEAUDNCN = new JLabel("Common Name:"); 
  private JTextField txtCEAUDNCN =  new JTextField("",48);
  private JLabel     lblCEAUDNOU = new JLabel("Org. Unit(s):"); 
  private JTextField txtCEAUDNOU = new JTextField("",48);
  private JLabel     lblCEAUDNO = new JLabel("Org.:"); 
  private JTextField txtCEAUDNO = new JTextField("",48);
  private JLabel     lblCEAUDNL = new JLabel("Locality:"); 
  private JTextField txtCEAUDNL = new JTextField("",48);
  private JLabel     lblCEAUDNST = new JLabel("State:"); 
  private JTextField txtCEAUDNST = new JTextField("",48);
  private JLabel     lblCEAUDNC = new JLabel("Country:"); 
  private JTextField txtCEAUDNC = new JTextField("",48);
  private JLabel     lblCEAUExpiry = new JLabel("Expiry:"); 
  private JTextField txtCEAUExpiry = new JTextField("",48);

  // Page 
  private JLabel     lblPAGE_SNDRCERTTitle = new JLabel("SSL Client Certificate Properties:"); 
  private JLabel     lblSNDRLabel = new JLabel("Cert. label:"); 
  private JTextField txtSNDRLabel = new JTextField("",48);
  private JLabel     lblSNDRDNCN = new JLabel("Common Name:"); 
  private JComboBox  chcSNDRDNCN =  new JComboBox();
  private JLabel     lblSNDRDNOU = new JLabel("Org. Unit(s):"); 
  private JTextField txtSNDRDNOU = new JTextField("",48);
  private JLabel     lblSNDRDNO = new JLabel("Org.:"); 
  private JTextField txtSNDRDNO = new JTextField("",48);
  private JLabel     lblSNDRDNL = new JLabel("Locality:"); 
  private JTextField txtSNDRDNL = new JTextField("",48);
  private JLabel     lblSNDRDNST = new JLabel("State:"); 
  private JTextField txtSNDRDNST = new JTextField("",48);
  private JLabel     lblSNDRDNC = new JLabel("Country:"); 
  private JTextField txtSNDRDNC = new JTextField("",48);
  private JLabel     lblSNDRExpiry = new JLabel("Expiry:"); 
  private JTextField txtSNDRExpiry = new JTextField("",48);
          
  // Page 
  private JLabel     lblPAGE_RCVRCERTTitle = new JLabel("SSL Server Certificate Properties:"); 
  private JLabel     lblRCVRLabel = new JLabel("Cert. label:"); 
  private JTextField txtRCVRLabel = new JTextField("",48);
  private JLabel     lblRCVRDNCN = new JLabel("Common Name:"); 
  private JComboBox  chcRCVRDNCN =  new JComboBox();
  private JLabel     lblRCVRDNOU = new JLabel("Org. Unit(s):");                       
  private JTextField txtRCVRDNOU = new JTextField("",48);  
  private JLabel     lblRCVRDNO = new JLabel("Org.:");                       
  private JTextField txtRCVRDNO = new JTextField("",48);  
  private JLabel     lblRCVRDNL = new JLabel("Locality:");                  
  private JTextField txtRCVRDNL = new JTextField("",48);              
  private JLabel     lblRCVRDNST = new JLabel("State:");                  
  private JTextField txtRCVRDNST = new JTextField("",48);              
  private JLabel     lblRCVRDNC = new JLabel("Country:");                   
  private JTextField txtRCVRDNC = new JTextField("",48);                  
  private JLabel     lblRCVRExpiry = new JLabel("Expiry:"); 
  private JTextField txtRCVRExpiry = new JTextField("",48);
          
  // Page 
  private JLabel     lblGenerating = new JLabel("Generating commands. Please wait..."); 
          
  // Page 
  private JLabel     lblPAGE_SUMMARYTitle = new JLabel("Generated instructions"); 
  private JEditorPane instructionsPane = new JEditorPane("text/html","");
  private JScrollPane spnSummary = new JScrollPane(instructionsPane);
  private JButton    btnSave = new JButton("Save instructions");
          
          
  /****************************************************************/
  /* Global vars                                                  */
  /****************************************************************/
  private String SCertLabel, RCertLabel;         // Certificate labels
  
  /****************************************************************/
  /* Function: main                                               */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  public static void main(String args[]) {
    String args0 = new String();

    if (args.length > 0) {
      args0 = args[0];
    }
    MQTLSSSLWizard anInstance = new MQTLSSSLWizard(args0);
    anInstance.setSize(540,675);
    anInstance.setVisible(true);
  }

  /****************************************************************/
  /* Function: MQTLSSSLWizard                                       */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  public MQTLSSSLWizard(String filename) {
   
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setupScreen();
    if (!filename.equals("")) {
      fileLoad(filename);
    }
    else {
      resetFields();
    }

  }

  void eventNext() {
    int strlen;
    String strKRSuffix = new String();
    String saveCmd = new String();

    if (currentPage == PAGE_SNDR) {
      String ms;
      ms = mangleStr( txtSNDRQmgrName.getText() );

      if (chcCEAUPlatform.getSelectedIndex() == -1) {
        chcCEAUPlatform.setSelectedItem(MQTLSSSLWizardGlobal.PLATFORM_ZOS);
      }

      if ((radCSClient.isSelected()) && (txtSNDRUser.getText().equals(""))) {
        txtSNDRQmgrName.requestFocus();
        msg("Enter Client User");
        return;
      }
      if ((radCSServer.isSelected()) && (txtSNDRQmgrName.getText().equals(""))) {
        txtSNDRQmgrName.requestFocus();
        msg("Enter QMGR Name");
        return;
      }
      if (txtSNDRHost.getText().equals("")) {
        txtSNDRHost.requestFocus();
        msg("Enter Host");
        return;
      }
      if (chcSNDRPlatform.getSelectedIndex() == -1) {
        chcSNDRPlatform.requestFocus();
        msg("Enter Platform");
        return;
      }
      if ((radCSClient.isSelected()) && (chcSNDRPlatform.getSelectedItem().equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS))) {
        chcSNDRPlatform.requestFocus();
        msg("Client is incompatible with z/OS platform");
        return;
      }
      if (radCSClient.isSelected()) {
        if (radJOJava.isSelected()) {
          strKRSuffix = "jks";
        }
        else {
          strKRSuffix = "kdb";
        }

        if (chcSNDRPlatform.getSelectedItem().equals(MQTLSSSLWizardGlobal.PLATFORM_UNIX) && txtSNDRKeyringUNIXWIN.getText().equals("")) {
          txtSNDRKeyringUNIXWIN.setText("/var/mqm/" +  txtSNDRUser.getText() + "." + strKRSuffix);
        }
        else if (chcSNDRPlatform.getSelectedItem().equals(MQTLSSSLWizardGlobal.PLATFORM_WIN) && txtSNDRKeyringUNIXWIN.getText().equals("")) {
          txtSNDRKeyringUNIXWIN.setText("C:\\Program Files\\IBM\\WebSphere MQ\\" +  txtSNDRUser.getText() + "." + strKRSuffix);
        }
      }
      else {
        //msg(txtSNDRKeyringUNIXWIN.getText().substring(3,16));
        //msg(txtSNDRKeyringUNIXWIN.getText().substring(0,14));
        if ((chcSNDRPlatform.getSelectedItem().equals(MQTLSSSLWizardGlobal.PLATFORM_UNIX)) && 
            ((txtSNDRKeyringUNIXWIN.getText().equals("")) ||
             ((txtSNDRKeyringUNIXWIN.getText().length() > 16) &&
              (txtSNDRKeyringUNIXWIN.getText().substring(3,16).equals("Program Files"))
             )
            )
           ) {
          txtSNDRKeyringUNIXWIN.setText("/var/mqm/qmgrs/" + ms + "/ssl/" +  txtSNDRQmgrName.getText() + ".kdb");
        }
        else if ((chcSNDRPlatform.getSelectedItem().equals(MQTLSSSLWizardGlobal.PLATFORM_WIN)) && 
                 ((txtSNDRKeyringUNIXWIN.getText().equals("")) ||
                  ((txtSNDRKeyringUNIXWIN.getText().length() > 14) && 
                   (txtSNDRKeyringUNIXWIN.getText().substring(0,14).equals("/var/mqm/qmgrs"))
                  )
                 )
                ) {
          txtSNDRKeyringUNIXWIN.setText("C:\\Program Files\\IBM\\WebSphere MQ\\Qmgrs\\" + ms + "\\ssl\\" +  txtSNDRQmgrName.getText() + ".kdb");
        }
      }
      if (chcSNDRPlatform.getSelectedItem().equals(MQTLSSSLWizardGlobal.PLATFORM_WIN)) {
        if (chcSNDRCommand.getSelectedIndex() == -1) {
          chcSNDRCommand.setSelectedItem(MQTLSSSLWizardGlobal.COMMAND_CAPI);
        }
        saveCmd = (String)chcSNDRCommand.getSelectedItem();
        chcSNDRCommand.removeAllItems();
        chcSNDRCommand.addItem(MQTLSSSLWizardGlobal.COMMAND_WIN);
        if (!(radCSClient.isSelected() && radJOJava.isSelected())) chcSNDRCommand.addItem(MQTLSSSLWizardGlobal.COMMAND_CAPI);
        chcSNDRCommand.setSelectedItem(MQTLSSSLWizardGlobal.COMMAND_CAPI);
        chcSNDRCommand.setSelectedItem(saveCmd);   /* This leaves the previous value if saveCmd is not in list */
      } else if (chcSNDRPlatform.getSelectedItem().equals(MQTLSSSLWizardGlobal.PLATFORM_UNIX)) {
        if (chcSNDRCommand.getSelectedIndex() == -1) {
          chcSNDRCommand.setSelectedItem(MQTLSSSLWizardGlobal.COMMAND_CAPI);
        }
        saveCmd = (String)chcSNDRCommand.getSelectedItem();
        chcSNDRCommand.removeAllItems();
        chcSNDRCommand.addItem(MQTLSSSLWizardGlobal.COMMAND_UNIX);
        if (!(radCSClient.isSelected() && radJOJava.isSelected())) chcSNDRCommand.addItem(MQTLSSSLWizardGlobal.COMMAND_CAPI);
        chcSNDRCommand.setSelectedItem(MQTLSSSLWizardGlobal.COMMAND_CAPI);
        chcSNDRCommand.setSelectedItem(saveCmd);   /* This leaves the previous value if saveCmd is not in list */
      }
    }
    else if (currentPage == PAGE_SNDRZOS) {
      if (txtSNDRKeyringZOS.getText().equals("")) {
        txtSNDRKeyringZOS.requestFocus();
        msg("Enter Keyring");
        return;
      }
      if (txtSNDRTasks.getText().equals("")) {
        txtSNDRTasks.requestFocus();
        msg("Enter SSL Tasks");
        return;
      }
      if (txtSNDRID.getText().equals("")) {
        txtSNDRID.requestFocus();
        msg("Enter Chinit ID");
        return;
      }
    }
    else if (currentPage == PAGE_SNDRUNIXWIN) {
      if (txtSNDRKeyringUNIXWIN.getText().equals("")) {
        txtSNDRKeyringUNIXWIN.requestFocus();
        msg("Enter Key Database");
        return;
      }
      if (chcSNDRCommand.getSelectedIndex() == -1) {
        chcSNDRCommand.requestFocus();
        msg("Enter Command");
        return;
      }

      strlen = txtSNDRKeyringUNIXWIN.getText().length(); 

      if (radCSClient.isSelected()) {
        if (radJOJava.isSelected()) {
          if (!txtSNDRKeyringUNIXWIN.getText().substring(strlen-4,strlen).equals(".jks")) {
            txtSNDRKeyringUNIXWIN.requestFocus();
            msg("Key database file for a Java client must end in .jks");
            return;
          }
        }
        else {
          if (!txtSNDRKeyringUNIXWIN.getText().substring(strlen-4,strlen).equals(".kdb")) {
            txtSNDRKeyringUNIXWIN.requestFocus();
            msg("Key database file for a C client must end in .kdb");
            return;
          }
        }
      }
      else if ((!txtSNDRKeyringUNIXWIN.getText().substring(strlen-4,strlen).equals(".kdb")) &&
               (!txtSNDRKeyringUNIXWIN.getText().substring(strlen-4,strlen).equals(".jks"))) {
        txtSNDRKeyringUNIXWIN.requestFocus();
        msg("Key database file must end in .kdb or .jks");
        return;
      }
    }
    else if (currentPage == PAGE_SNDRCAPI) {
      if (chcSNDRSigalg.getSelectedIndex() == -1) {
        chcSNDRSigalg.requestFocus();
        msg("Enter Sigalg");
        return;
      }
    }
    else if (currentPage == PAGE_RCVR) {
      String ms;
      ms = mangleStr( txtRCVRQmgrName.getText() );

      if (txtRCVRQmgrName.getText().equals("")) {
        txtRCVRQmgrName.requestFocus();
        msg("Enter QMGR Name");
        return;
      }
      if (txtRCVRHost.getText().equals("")) {
        txtRCVRHost.requestFocus();
        msg("Enter Host");
        return;
      }
      if (txtRCVRPort.getText().equals("")) {
        txtRCVRHost.requestFocus();
        msg("Enter Port");
        return;
      }
      if (chcRCVRPlatform.getSelectedIndex() == -1) {
        chcRCVRPlatform.requestFocus();
        msg("Enter Platform");
        return;
      }
      if (radCSServer.isSelected()) {
        if (txtSNDRQmgrName.getText().equals(txtRCVRQmgrName.getText())) {
          txtRCVRQmgrName.requestFocus();
          msg("Receiver QMGR Name must not match the Sender QMGR Name");
          return;
        }
      }
      if ( (txtSNDRHost.getText().equals(txtRCVRHost.getText())) && 
           (!chcSNDRPlatform.getSelectedItem().equals(chcRCVRPlatform.getSelectedItem())) ) {
        chcRCVRPlatform.requestFocus();
        msg("If Hosts match, so must Platform ");
        return;
      }
      if (chcRCVRPlatform.getSelectedItem().equals(MQTLSSSLWizardGlobal.PLATFORM_UNIX) && txtRCVRKeyringUNIXWIN.getText().equals("")) {
        txtRCVRKeyringUNIXWIN.setText("/var/mqm/qmgrs/" + ms + "/ssl/" +  txtRCVRQmgrName.getText() + ".kdb");
      }
      else if (chcRCVRPlatform.getSelectedItem().equals(MQTLSSSLWizardGlobal.PLATFORM_WIN) && txtRCVRKeyringUNIXWIN.getText().equals("")) {
        txtRCVRKeyringUNIXWIN.setText("C:\\Program Files\\IBM\\WebSphere MQ\\Qmgrs\\" + ms + "\\ssl\\" +  txtRCVRQmgrName.getText() + ".kdb");
      }
      if (chcRCVRPlatform.getSelectedItem().equals(MQTLSSSLWizardGlobal.PLATFORM_WIN)) {
        if (chcRCVRCommand.getSelectedIndex() == -1) {
          chcRCVRCommand.setSelectedItem(MQTLSSSLWizardGlobal.COMMAND_CAPI);
        }
        saveCmd = (String)chcRCVRCommand.getSelectedItem();
        chcRCVRCommand.removeAllItems();
        chcRCVRCommand.addItem(MQTLSSSLWizardGlobal.COMMAND_WIN);
        chcRCVRCommand.addItem(MQTLSSSLWizardGlobal.COMMAND_CAPI);
        chcRCVRCommand.setSelectedItem(MQTLSSSLWizardGlobal.COMMAND_CAPI);
        chcRCVRCommand.setSelectedItem(saveCmd);   /* This leaves the previous value if saveCmd is not in list */
      } else if (chcRCVRPlatform.getSelectedItem().equals(MQTLSSSLWizardGlobal.PLATFORM_UNIX)) {
        if (chcRCVRCommand.getSelectedIndex() == -1) {
          chcRCVRCommand.setSelectedItem(MQTLSSSLWizardGlobal.COMMAND_CAPI);
        }
        saveCmd = (String)chcRCVRCommand.getSelectedItem();
        chcRCVRCommand.removeAllItems();
        chcRCVRCommand.addItem(MQTLSSSLWizardGlobal.COMMAND_UNIX);
        chcRCVRCommand.addItem(MQTLSSSLWizardGlobal.COMMAND_CAPI);
        chcRCVRCommand.setSelectedItem(MQTLSSSLWizardGlobal.COMMAND_CAPI);
        chcRCVRCommand.setSelectedItem(saveCmd);   /* This leaves the previous value if saveCmd is not in list */
      }
    }
    else if (currentPage == PAGE_RCVRZOS) {
      if (txtRCVRKeyringZOS.getText().equals("")) {
        txtRCVRKeyringZOS.requestFocus();
        msg("Enter Keyring");
        return;
      }
      if (txtRCVRTasks.getText().equals("")) {
        txtRCVRTasks.requestFocus();
        msg("Enter SSL Tasks");
        return;
      }
      if (txtRCVRID.getText().equals("")) {
        txtRCVRID.requestFocus();
        msg("Enter Chinit ID");
        return;
      }
      if ((chcSNDRPlatform.getSelectedItem().equals(chcRCVRPlatform.getSelectedItem())) && 
          (txtSNDRHost.getText().equals(txtRCVRHost.getText())) &&
          (txtSNDRKeyringZOS.getText().equals(txtRCVRKeyringZOS.getText()))
         ){
        txtRCVRKeyringZOS.requestFocus();
        msg("SSL client and SSL server keyrings must differ");
        return;
      }
      if (txtChannel.getText().equals("")) {
        if (radCSClient.isSelected()) {
          txtChannel.setText("SSL.SVRCONN");
        }
        else {
          txtChannel.setText(txtSNDRQmgrName.getText() + ".TO." + txtRCVRQmgrName.getText());
        }
      }
    }
    else if (currentPage == PAGE_RCVRUNIXWIN) {
      if (txtRCVRKeyringUNIXWIN.getText().equals("")) {
        txtRCVRKeyringUNIXWIN.requestFocus();
        msg("Enter Key Database");
        return;
      }
      strlen = txtRCVRKeyringUNIXWIN.getText().length(); 
      if (!txtRCVRKeyringUNIXWIN.getText().substring(strlen-4,strlen).equals(".kdb")) {
        txtRCVRKeyringUNIXWIN.requestFocus();
        msg("Key database file must end in .kdb");
        return;
      }
      if ((chcSNDRPlatform.getSelectedItem().equals(chcRCVRPlatform.getSelectedItem())) && 
          (txtSNDRHost.getText().equals(txtRCVRHost.getText())) &&
          (txtSNDRKeyringUNIXWIN.getText().equals(txtRCVRKeyringUNIXWIN.getText()))
         ){
        txtRCVRKeyringUNIXWIN.requestFocus();
        msg("SSL client and SSL server key databases must differ");
        return;
      }
      if (txtRCVRKeyringUNIXWIN.getText().equals("")) {
        txtRCVRKeyringUNIXWIN.requestFocus();
        msg("Enter Key database");
        return;
      }
      if (chcRCVRCommand.getSelectedIndex() == -1) {
        chcRCVRCommand.requestFocus();
        msg("Enter Command");
        return;
      }
      if (txtChannel.getText().equals("")) {
        if (radCSClient.isSelected()) {
          txtChannel.setText("SSL.SVRCONN");
        }
        else {
          txtChannel.setText(txtSNDRQmgrName.getText() + ".TO." + txtRCVRQmgrName.getText());
        }
      }
    } 
    else if (currentPage == PAGE_RCVRCAPI) {
      if (chcRCVRSigalg.getSelectedIndex() == -1) {
        chcRCVRSigalg.requestFocus();
        msg("Enter Sigalg");
        return;
      }
    }
    else if (currentPage == PAGE_CHL) {
      if (txtChannel.getText().equals("")) {
        txtChannel.requestFocus();
        msg("Enter Channel");
        return;
      }
      if (chcCipherSpec.getSelectedIndex() == -1) {
        chcCipherSpec.requestFocus();
        msg("Enter CipherSpec");
        return;
      }
      // Calculate the names of certs and keyrings
      calcCertNames();
      txtSNDRLabel.setText(SCertLabel);
      txtRCVRLabel.setText(RCertLabel);

      if (chcSNDRDNCN.getItemCount() < 1) {
        if (radCSClient.isSelected()) {
          chcSNDRDNCN.addItem(txtSNDRUser.getText());
        }
        else {
          chcSNDRDNCN.addItem(txtSNDRQmgrName.getText());
        }
        chcSNDRDNCN.addItem(SCertLabel);
      }
      if (chcRCVRDNCN.getItemCount() < 1) {
        chcRCVRDNCN.addItem(txtRCVRQmgrName.getText());
        chcRCVRDNCN.addItem(RCertLabel);
      }
    }
    else if (currentPage == PAGE_CEAU) {
      if (txtCEAUHost.getText().equals("")) {
        txtCEAUHost.requestFocus();
        msg("Enter Host");
        return;
      }
      if (chcCEAUPlatform.getSelectedIndex() == -1) {
        chcCEAUPlatform.requestFocus();
        msg("Enter Platform");
        return;
      }
      if (!chcCEAUPlatform.getSelectedItem().equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
        if (chcCEAUPlatform.getSelectedItem().equals(MQTLSSSLWizardGlobal.PLATFORM_UNIX) && txtCEAUKeyringUNIXWIN.getText().equals("")) {
          txtCEAUKeyringUNIXWIN.setText("/var/mqm/wmqca.kdb");
        }
        else if (chcCEAUPlatform.getSelectedItem().equals(MQTLSSSLWizardGlobal.PLATFORM_WIN) && txtCEAUKeyringUNIXWIN.getText().equals("")) {
          txtCEAUKeyringUNIXWIN.setText("C:\\Program Files\\IBM\\WebSphere MQ\\wmqca.kdb");
        }
      }
      if (chcCEAUPlatform.getSelectedItem().equals(MQTLSSSLWizardGlobal.PLATFORM_WIN)) {
        if (chcCEAUCommand.getSelectedIndex() == -1) {
          chcCEAUCommand.setSelectedItem(MQTLSSSLWizardGlobal.COMMAND_CAPI);
        }
        saveCmd = (String)chcCEAUCommand.getSelectedItem();
        chcCEAUCommand.removeAllItems();
        chcCEAUCommand.addItem(MQTLSSSLWizardGlobal.COMMAND_WIN);
        chcCEAUCommand.addItem(MQTLSSSLWizardGlobal.COMMAND_CAPI);
        chcCEAUCommand.setSelectedItem(MQTLSSSLWizardGlobal.COMMAND_CAPI);
        chcCEAUCommand.setSelectedItem(saveCmd);   /* This leaves the previous value if saveCmd is not in list */
      } else if (chcCEAUPlatform.getSelectedItem().equals(MQTLSSSLWizardGlobal.PLATFORM_UNIX)) {
        if (chcCEAUCommand.getSelectedIndex() == -1) {
          chcCEAUCommand.setSelectedItem(MQTLSSSLWizardGlobal.COMMAND_CAPI);
        }
        saveCmd = (String)chcCEAUCommand.getSelectedItem();
        chcCEAUCommand.removeAllItems();
        chcCEAUCommand.addItem(MQTLSSSLWizardGlobal.COMMAND_UNIX);
        chcCEAUCommand.addItem(MQTLSSSLWizardGlobal.COMMAND_CAPI);
        chcCEAUCommand.setSelectedItem(MQTLSSSLWizardGlobal.COMMAND_CAPI);
        chcCEAUCommand.setSelectedItem(saveCmd);   /* This leaves the previous value if saveCmd is not in list */
      }
    }
    else if (currentPage == PAGE_CEAUZOS) {
      if (txtCEAUKeyringZOS.getText().equals("")) {
        txtCEAUKeyringZOS.requestFocus();
        msg("Enter Keyring");
        return;
      }
      if (txtCEAUID.getText().equals("")) {
        txtCEAUID.requestFocus();
        msg("Enter CA ID");
        return;
      }
    }
    else if (currentPage == PAGE_CEAUUNIXWIN) {
      if (txtCEAUKeyringUNIXWIN.getText().equals("")) {
        txtCEAUKeyringUNIXWIN.requestFocus();
        msg("Enter Key Database");
        return;
      }
      if (chcCEAUCommand.getSelectedIndex() == -1) {
        chcCEAUCommand.requestFocus();
        msg("Enter Command");
        return;
      }
    }
    else if (currentPage == PAGE_CEAUCAPI) {
      if (chcCEAUSigalg.getSelectedIndex() == -1) {
        chcCEAUSigalg.requestFocus();
        msg("Enter Sigalg");
        return;
      }
    }
    else if (currentPage == PAGE_SNDRCERT) {
      if ((chcSNDRDNCN.getSelectedIndex() == -1) &&
          (((String)(chcSNDRDNCN.getSelectedItem())).equals(""))) {
        chcSNDRDNCN.requestFocus();
        msg("Enter Common Name");
        return;
      }
      /*if (txtSNDRDNOU.getText().equals("")) {
        txtSNDRDNOU.requestFocus();
        msg("Enter Org. Unit(s)");
        return;
      }
      if (txtSNDRDNO.getText().equals("")) {
        txtSNDRDNO.requestFocus();
        msg("Enter Org.");
        return;
      }
      if (txtSNDRDNC.getText().equals("")) {
        txtSNDRDNC.requestFocus();
        msg("Enter Country");
        return;
      }*/
      if (txtSNDRExpiry.getText().equals("")) {
        txtSNDRExpiry.requestFocus();
        msg("Enter Expiry");
        return;
      }
    }
    else if (currentPage == PAGE_CEAUCERT) {
      if (txtCEAULabel.getText().equals("")) {
        txtCEAULabel.requestFocus();
        msg("Enter Cert. label");
        return;
      }
      if (txtCEAULabel.getText().indexOf(' ') != -1) {
        txtCEAULabel.requestFocus();
        msg("Cert. label cannot contain spaces");
        return;
      }
      if (txtCEAUDNCN.getText().equals("")) {
        txtCEAUDNCN.requestFocus();
        msg("Enter Common Name");
        return;
      }
      /*if (txtCEAUDNOU.getText().equals("")) {
        txtCEAUDNOU.requestFocus();
        msg("Enter Org. Unit(s)");
        return;
      }
      if (txtCEAUDNO.getText().equals("")) {
        txtCEAUDNO.requestFocus();
        msg("Enter Org.");
        return;
      }
      if (txtCEAUDNL.getText().equals("")) {
        txtCEAUDNL.requestFocus();
        msg("Enter Locality");
        return;
      }
      if (txtCEAUDNST.getText().equals("")) {
        txtCEAUDNST.requestFocus();
        msg("Enter State");
        return;
      }
      if (txtCEAUDNC.getText().equals("")) {
        txtCEAUDNC.requestFocus();
        msg("Enter Country");
        return;
      }*/
      if (txtCEAUExpiry.getText().equals("")) {
        txtCEAUExpiry.requestFocus();
        msg("Enter Expiry");
        return;
      }

      int iExpCEAU = Integer.parseInt(txtCEAUExpiry.getText());
      int iExpSNDR = Integer.parseInt(txtSNDRExpiry.getText());
      int iExpRCVR = Integer.parseInt(txtRCVRExpiry.getText());
      if ( (iExpCEAU <= iExpSNDR) || (iExpCEAU <= iExpRCVR) ) {
        txtCEAUExpiry.requestFocus();
        msg("CA Expiry must be more than that of the SSL Client and SSL Server");
        return;
      }

    }
    else if (currentPage == PAGE_RCVRCERT) {
      if ((chcRCVRDNCN.getSelectedIndex() == -1) &&
          (((String)(chcRCVRDNCN.getSelectedItem())).equals(""))) {
        chcRCVRDNCN.requestFocus();
        msg("Enter Common Name");
        return;
      }
      /*if (txtRCVRDNOU.getText().equals("")) {
        txtRCVRDNOU.requestFocus();
        msg("Enter Org. Unit(s)");
        return;
      }
      if (txtRCVRDNO.getText().equals("")) {
        txtRCVRDNO.requestFocus();
        msg("Enter Org.");
        return;
      }
      if (txtRCVRDNL.getText().equals("")) {
        txtRCVRDNL.requestFocus();
        msg("Enter Locality");
        return;
      }
      if (txtRCVRDNST.getText().equals("")) {
        txtRCVRDNST.requestFocus();
        msg("Enter State");
        return;
      }
      if (txtRCVRDNC.getText().equals("")) {
        txtRCVRDNC.requestFocus();
        msg("Enter Country");
        return;
      }*/
      if (txtRCVRExpiry.getText().equals("")) {
        txtRCVRExpiry.requestFocus();
        msg("Enter Expiry");
        return;
      }
    }

    switch(currentPage) {
    case PAGE_SNDR :
      if (chcSNDRPlatform.getSelectedItem().equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
        currentPage = PAGE_SNDRZOS;
      }
      else {
        currentPage = PAGE_SNDRUNIXWIN;
      }
      break;
    case PAGE_SNDRZOS:     
      currentPage = PAGE_RCVR;
      break;
    case PAGE_SNDRUNIXWIN: 
      if (chcSNDRCommand.getSelectedItem().equals(MQTLSSSLWizardGlobal.COMMAND_CAPI)) {
        currentPage = PAGE_SNDRCAPI;
      } else {
        currentPage = PAGE_RCVR;
      }
      break;
    case PAGE_SNDRCAPI: 
      currentPage = PAGE_RCVR;
      break;
    case PAGE_RCVR:       
      if (chcRCVRPlatform.getSelectedItem().equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
        currentPage = PAGE_RCVRZOS;
      }
      else {
        currentPage = PAGE_RCVRUNIXWIN;
      }
      break;
    case PAGE_RCVRZOS:
      currentPage = PAGE_CHL;
      break;
    case PAGE_RCVRUNIXWIN: 
      if (chcRCVRCommand.getSelectedItem().equals(MQTLSSSLWizardGlobal.COMMAND_CAPI)) {
        currentPage = PAGE_RCVRCAPI;
      } else {
        currentPage = PAGE_CHL;
      }
      break;
    case PAGE_RCVRCAPI: 
      currentPage = PAGE_CHL;
      break;
    case PAGE_CHL:          
      if (radAuReq.isSelected())
      {
        currentPage = PAGE_SNDRCERT ;
      }
      else 
      {
        currentPage = PAGE_RCVRCERT ;
      }
      break;
    case PAGE_SNDRCERT:    
      currentPage = PAGE_RCVRCERT;
      break;
    case PAGE_RCVRCERT:  
      currentPage = PAGE_CAMODEL ;
      break;
    case PAGE_CAMODEL:  
      if (radTypeCA.isSelected())
      {
        currentPage = PAGE_CAWHERE;
      }
      else 
      {
        currentPage = PAGE_GENERATE;
      }
      break;
    case PAGE_CAWHERE:  
      if (radTypeCAX.isSelected())
      {
        currentPage = PAGE_GENERATE;
      }
      else 
      {
        currentPage = PAGE_CEAU;
      }
      break;
    case PAGE_CEAU:   
      if (chcCEAUPlatform.getSelectedItem().equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
        currentPage = PAGE_CEAUZOS;
      }
      else {
        currentPage = PAGE_CEAUUNIXWIN;
      }
      break;
    case PAGE_CEAUZOS:   
      currentPage = PAGE_CEAUCERT ;
      break;
    case PAGE_CEAUUNIXWIN: 
      if (chcCEAUCommand.getSelectedItem().equals(MQTLSSSLWizardGlobal.COMMAND_CAPI)) {
        currentPage = PAGE_CEAUCAPI;
      } else {
        currentPage = PAGE_CEAUCERT;
      }
      break;
    case PAGE_CEAUCAPI: 
      currentPage = PAGE_CEAUCERT;
      break;
    case PAGE_CEAUCERT:   
      currentPage = PAGE_GENERATE ;
      break;
    }

    

    if (currentPage == PAGE_GENERATE) {
      setPage(currentPage);
      msg("");

      // Get data from input fields
      MQTLSSSLWizardParms200 p = new MQTLSSSLWizardParms200();
      p =   getParms();

      // Generate instructions
      showInstructions(p);


      currentPage = PAGE_SUMMARY ;
      setSize(540,675);
      //setSize(540,750);
      //btnPrev.setBounds(10,10,100,20);
      setResizable(true);
    }


    setPageGraphic(currentPage);



    instructionsPane.setCaretPosition(0);
    setPage(currentPage);
    msg("");
  }

  void eventPrev() {
    menuEditCopy.setEnabled(false);

    switch(currentPage) {
    case PAGE_SNDRZOS:     
      currentPage = PAGE_SNDR;
      break;
    case PAGE_SNDRUNIXWIN: 
      currentPage = PAGE_SNDR;
      break;
    case PAGE_SNDRCAPI: 
      currentPage = PAGE_SNDRUNIXWIN;
      break;
    case PAGE_RCVR:       
      if (chcSNDRPlatform.getSelectedItem().equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
        currentPage = PAGE_SNDRZOS;
      }
      else {
        if (chcSNDRCommand.getSelectedItem().equals(MQTLSSSLWizardGlobal.COMMAND_CAPI)) {
          currentPage = PAGE_SNDRCAPI;
        } else {
          currentPage = PAGE_SNDRUNIXWIN;
        }
      }
      break;
    case PAGE_RCVRZOS:
      currentPage = PAGE_RCVR;
      break;
    case PAGE_RCVRUNIXWIN: 
      currentPage = PAGE_RCVR;
      break;
    case PAGE_RCVRCAPI: 
      currentPage = PAGE_RCVRUNIXWIN;
      break;
    case PAGE_CHL:          
      if (chcRCVRPlatform.getSelectedItem().equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
        currentPage = PAGE_RCVRZOS;
      }
      else {
        if (chcRCVRCommand.getSelectedItem().equals(MQTLSSSLWizardGlobal.COMMAND_CAPI)) {
          currentPage = PAGE_RCVRCAPI;
        } else {
          currentPage = PAGE_RCVRUNIXWIN;
        }
      }
      break;
    case PAGE_SNDRCERT:   
      currentPage = PAGE_CHL;
      break;
    case PAGE_RCVRCERT:  
      if (radAuReq.isSelected())
      {
        currentPage = PAGE_SNDRCERT ;
      }
      else 
      {
        currentPage = PAGE_CHL ;
      }
      break;
    case PAGE_CAMODEL:   
      currentPage = PAGE_RCVRCERT ;
      break;
    case PAGE_CAWHERE:   
      currentPage = PAGE_CAMODEL ;
      break;
    case PAGE_CEAU:   
      currentPage = PAGE_CAWHERE ;
      break;
    case PAGE_CEAUZOS:   
      currentPage = PAGE_CEAU ;
      break;
    case PAGE_CEAUUNIXWIN:   
      currentPage = PAGE_CEAU ;
      break;
    case PAGE_CEAUCAPI: 
      currentPage = PAGE_CEAUUNIXWIN;
      break;
    case PAGE_CEAUCERT:   
      if (chcCEAUPlatform.getSelectedItem().equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
        currentPage = PAGE_CEAUZOS;
      }
      else {
        if (chcCEAUCommand.getSelectedItem().equals(MQTLSSSLWizardGlobal.COMMAND_CAPI)) {
          currentPage = PAGE_CEAUCAPI;
        } else {
          currentPage = PAGE_CEAUUNIXWIN;
        }
      }
      break;
    case PAGE_SUMMARY:  
      setSize(540,675);
      setResizable(false);
      //btnPrev.setBounds(10,280,100,20);

      if (radTypeCA.isSelected())
      {
        if (radTypeCAX.isSelected())
        {
          currentPage = PAGE_CAWHERE;
        }
        else 
        {
          currentPage = PAGE_CEAUCERT;
        }
      }
      else 
      {
        currentPage = PAGE_CAMODEL;
      }
      break;
    }

    setPage(currentPage);
    msg("");
  }

  String mangleStr(String inStr) {
    String outStr;

    outStr = inStr.replaceAll("\\.","!");
    outStr = outStr.replaceAll("/","&");

    return outStr;
  }

  void eventRBrowse() {
    JFileChooser dd = new JFileChooser(txtRCVRKeyringUNIXWIN.getText());
    dd.setFileSelectionMode(JFileChooser.FILES_ONLY);
    dd.addChoosableFileFilter(new WMQSSLWizardFilter(WMQSSLWizardFilter.FT_KDB));

    int returnVal = dd.showOpenDialog(this);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      txtRCVRKeyringUNIXWIN.setText(dd.getSelectedFile().getAbsolutePath());
    }
  }
  void eventSave() {
    fileSaveReport();
  }
  void eventSBrowse() {
    JFileChooser dd = new JFileChooser(txtSNDRKeyringUNIXWIN.getText());
    dd.setFileSelectionMode(JFileChooser.FILES_ONLY);
    dd.addChoosableFileFilter(new WMQSSLWizardFilter(WMQSSLWizardFilter.FT_KDB));

    int returnVal = dd.showOpenDialog(this);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      txtSNDRKeyringUNIXWIN.setText(dd.getSelectedFile().getAbsolutePath());
    }
  }
    
  /****************************************************************/
  /* Function: actionPerformed                                    */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  public void actionPerformed(ActionEvent ev) {

    if (ev.getSource() == btnSNDRBrowse) {
      eventSBrowse();
    }
    else if (ev.getSource() == btnRCVRBrowse) {
      eventRBrowse();
    }
    else if (ev.getSource() == btnPrev) {
      eventPrev();
    }
    else if (ev.getSource() == btnNext) {
      eventNext();
    }
    else if (ev.getSource() == btnSave) {
      eventSave(); 
    }
    else if (ev.getSource() == menuFileSaveAs) {
      fileSaveData();
    }
    else if (ev.getSource() == menuFileNew) {
      fileNew();
    }
    else if (ev.getSource() == menuFileOpen) {
      fileOpenData();
    }
    else if (ev.getSource() == menuFileExit) {
      System.exit(0);
    }
    else if (ev.getSource() == menuEditCopy) {
      copySummary();
    }
    else if (ev.getSource() == menuHelpHelp) {
      WMQSSLWizardHelpDialog hd = new WMQSSLWizardHelpDialog("MQ TLS/SSL Wizard Help", "For help see PDF provided with SupportPac", this);
      hd = null;
    }
    else if (ev.getSource() == menuHelpAbout) {
      WMQSSLWizardHelpDialog hd = new WMQSSLWizardHelpDialog("About MQ TLS/SSL Wizard","IBM MQ TLS/SSL Wizard version 2.0.0.1 (" + VERSION + ")", this);
      hd = null;
    }
    else if ((ev.getSource() == radCSClient) || (ev.getSource() == radCSServer)) {
      setPlatforms();
      setCSeditable();
    }
    else if ((ev.getSource() == radTypeCAI  ) || (ev.getSource() == radTypeCAX)) {
      setPAGE_CAWHEREVisible();
    }
    else if (ev.getSource() == cbxQmgrFips) {
      setCipherSpec();
    }


  }

  /****************************************************************/
  /* Function: setCSeditable                                      */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void setCSeditable() {
    if (radCSClient.isSelected()) {
      txtSNDRUser.setEditable(true);
      txtSNDRQmgrName.setEditable(false);
      radJOJava.setEnabled(true);
      radJOOther.setEnabled(true);
      lbl01SNDR1_Q.setVisible(false);
      lbl02SNDR1_C.setVisible(true);
    }
    else {
      txtSNDRQmgrName.setEditable(true);
      txtSNDRUser.setEditable(false);
      radJOJava.setEnabled(false);
      radJOOther.setEnabled(false);
      lbl01SNDR1_Q.setVisible(true);
      lbl02SNDR1_C.setVisible(false);
    }
  }

  /****************************************************************/
  /* Function: setPlatforms                                       */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void setPlatforms() {
    String savePlatform = new String("");

    // Save existing 
    if (chcSNDRPlatform.getSelectedIndex() != -1) {
      savePlatform = (String)chcSNDRPlatform.getSelectedItem();
    }

    // Set platforms for all
    chcSNDRPlatform.removeAllItems();
    chcSNDRPlatform.addItem(MQTLSSSLWizardGlobal.PLATFORM_WIN);
    chcSNDRPlatform.addItem(MQTLSSSLWizardGlobal.PLATFORM_UNIX);
    chcSNDRPlatform.setSelectedIndex(-1);

    // Add Z if not a client
    if (!radCSClient.isSelected()) {
      chcSNDRPlatform.addItem(MQTLSSSLWizardGlobal.PLATFORM_ZOS);
    }

    chcSNDRPlatform.setSelectedItem(savePlatform);   /* This leaves the previous value if savePlatform is not in list */
  }

  /****************************************************************/
  /* Function: setCipherSpec                                      */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void setCipherSpec() {
    String saveCipherSpec = new String("");

    // Save existing 
    if (chcCipherSpec.getSelectedIndex() != -1) {
      saveCipherSpec = (String)chcCipherSpec.getSelectedItem();
    }

    // Set cipherspecs for all
    chcCipherSpec.removeAllItems();
    chcCipherSpec.addItem("TLS_RSA_WITH_AES_128_CBC_SHA");
    chcCipherSpec.addItem("TLS_RSA_WITH_AES_256_CBC_SHA");
    chcCipherSpec.addItem("TLS_RSA_WITH_3DES_EDE_CBC_SHA");
    chcCipherSpec.addItem("FIPS_WITH_3DES_EDE_CBC_SHA");
    chcCipherSpec.setSelectedIndex(-1);

    // Add non fips if SSLFIPS is unchecked
    if (!cbxQmgrFips.isSelected()) {
      chcCipherSpec.addItem("NULL_SHA");
      chcCipherSpec.addItem("RC4_SHA_US");
      chcCipherSpec.addItem("DES_SHA_EXPORT");
      chcCipherSpec.addItem("RC4_56_SHA_EXPORT1024");
      chcCipherSpec.addItem("DES_SHA_EXPORT1024");
      chcCipherSpec.addItem("TRIPLE_DES_SHA_US");
      chcCipherSpec.addItem("RC4_56_SHA_EXPORT1024");
      chcCipherSpec.addItem("TLS_RSA_WITH_DES_CBC_SHA"); 
      chcCipherSpec.addItem("FIPS_WITH_DES_CBC_SHA");
    }

    // Set back to saved
    chcCipherSpec.setSelectedItem(saveCipherSpec);   /* This leaves the previous value if saveCipherSpec is not in list */
  }

  /****************************************************************/
  /* Function: setPAGE_CAWHEREVisible                             */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void setPAGE_CAWHEREVisible() {
    if (radCSClient.isSelected()) {
      if (radAuReq.isSelected()) {
        if (radTypeCAI.isSelected()) {
          lbl21CAWHERE_Q_N_E.setVisible(false);
          lbl22CAWHERE_C_N_E.setVisible(false);
          lbl23CAWHERE_Q_Y_E.setVisible(false);
          lbl24CAWHERE_C_Y_E.setVisible(false);
          lbl25CAWHERE_Q_N_I.setVisible(false);
          lbl26CAWHERE_C_N_I.setVisible(false);
          lbl27CAWHERE_Q_Y_I.setVisible(false);
          lbl28CAWHERE_C_Y_I.setVisible(true);
        }
        else {
          lbl21CAWHERE_Q_N_E.setVisible(false);
          lbl22CAWHERE_C_N_E.setVisible(false);
          lbl23CAWHERE_Q_Y_E.setVisible(false);
          lbl24CAWHERE_C_Y_E.setVisible(true);
          lbl25CAWHERE_Q_N_I.setVisible(false);
          lbl26CAWHERE_C_N_I.setVisible(false);
          lbl27CAWHERE_Q_Y_I.setVisible(false);
          lbl28CAWHERE_C_Y_I.setVisible(false);
        }
      }
      else {
        if (radTypeCAI.isSelected()) {
          lbl21CAWHERE_Q_N_E.setVisible(false);
          lbl22CAWHERE_C_N_E.setVisible(false);
          lbl23CAWHERE_Q_Y_E.setVisible(false);
          lbl24CAWHERE_C_Y_E.setVisible(false);
          lbl25CAWHERE_Q_N_I.setVisible(false);
          lbl26CAWHERE_C_N_I.setVisible(true);
          lbl27CAWHERE_Q_Y_I.setVisible(false);
          lbl28CAWHERE_C_Y_I.setVisible(false);
        }
        else {
          lbl21CAWHERE_Q_N_E.setVisible(false);
          lbl22CAWHERE_C_N_E.setVisible(true);
          lbl23CAWHERE_Q_Y_E.setVisible(false);
          lbl24CAWHERE_C_Y_E.setVisible(false);
          lbl25CAWHERE_Q_N_I.setVisible(false);
          lbl26CAWHERE_C_N_I.setVisible(false);
          lbl27CAWHERE_Q_Y_I.setVisible(false);
          lbl28CAWHERE_C_Y_I.setVisible(false);
        }
      }
    } 
    else {
      if (radAuReq.isSelected()) {
        if (radTypeCAI.isSelected()) {
          lbl21CAWHERE_Q_N_E.setVisible(false);
          lbl22CAWHERE_C_N_E.setVisible(false);
          lbl23CAWHERE_Q_Y_E.setVisible(false);
          lbl24CAWHERE_C_Y_E.setVisible(false);
          lbl25CAWHERE_Q_N_I.setVisible(false);
          lbl26CAWHERE_C_N_I.setVisible(false);
          lbl27CAWHERE_Q_Y_I.setVisible(true);
          lbl28CAWHERE_C_Y_I.setVisible(false);
        }
        else {
          lbl21CAWHERE_Q_N_E.setVisible(false);
          lbl22CAWHERE_C_N_E.setVisible(false);
          lbl23CAWHERE_Q_Y_E.setVisible(true);
          lbl24CAWHERE_C_Y_E.setVisible(false);
          lbl25CAWHERE_Q_N_I.setVisible(false);
          lbl26CAWHERE_C_N_I.setVisible(false);
          lbl27CAWHERE_Q_Y_I.setVisible(false);
          lbl28CAWHERE_C_Y_I.setVisible(false);
        }
      }
      else {
        if (radTypeCAI.isSelected()) {
          lbl21CAWHERE_Q_N_E.setVisible(false);
          lbl22CAWHERE_C_N_E.setVisible(false);
          lbl23CAWHERE_Q_Y_E.setVisible(false);
          lbl24CAWHERE_C_Y_E.setVisible(false);
          lbl25CAWHERE_Q_N_I.setVisible(true);
          lbl26CAWHERE_C_N_I.setVisible(false);
          lbl27CAWHERE_Q_Y_I.setVisible(false);
          lbl28CAWHERE_C_Y_I.setVisible(false);
        }
        else {
          lbl21CAWHERE_Q_N_E.setVisible(true);
          lbl22CAWHERE_C_N_E.setVisible(false);
          lbl23CAWHERE_Q_Y_E.setVisible(false);
          lbl24CAWHERE_C_Y_E.setVisible(false);
          lbl25CAWHERE_Q_N_I.setVisible(false);
          lbl26CAWHERE_C_N_I.setVisible(false);
          lbl27CAWHERE_Q_Y_I.setVisible(false);
          lbl28CAWHERE_C_Y_I.setVisible(false);
        }
      }
    }
  }

  /****************************************************************/
  /* Function: setPAGE_CAMODELVisible                             */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void setPageGraphic(int currentPage) {

    // Display the correct graphic
    switch(currentPage) {
    case PAGE_SNDR :
      if (radCSClient.isSelected()) {
        lbl01SNDR1_Q.setVisible(false);    
        lbl02SNDR1_C.setVisible(true);    
      } 
      else {
        lbl01SNDR1_Q.setVisible(true);    
        lbl02SNDR1_C.setVisible(false);    
      }
      break;
    case PAGE_SNDRZOS:     
      if (radCSClient.isSelected()) {
        lbl03SNDR2_Qa.setVisible(false);    
        lbl04SNDR2_Ca.setVisible(true);    
      } 
      else {
        lbl03SNDR2_Qa.setVisible(true);    
        lbl04SNDR2_Ca.setVisible(false);    
      }
      break;
    case PAGE_SNDRUNIXWIN: 
      if (radCSClient.isSelected()) {
        lbl03SNDR2_Qb.setVisible(false);    
        lbl04SNDR2_Cb.setVisible(true);    
      } 
      else {
        lbl03SNDR2_Qb.setVisible(true);    
        lbl04SNDR2_Cb.setVisible(false);    
      }
      break;
    case PAGE_SNDRCAPI: 
      if (radCSClient.isSelected()) {
        lbl03SNDR2_Qc.setVisible(false);    
        lbl04SNDR2_Cc.setVisible(true);    
      } 
      else {
        lbl03SNDR2_Qc.setVisible(true);    
        lbl04SNDR2_Cc.setVisible(false);    
      }
      break;
    case PAGE_RCVR:       
      if (radCSClient.isSelected()) {
        lbl05RCVR1_Q.setVisible(false);    
        lbl06RCVR1_C.setVisible(true);    
      } 
      else {
        lbl05RCVR1_Q.setVisible(true);    
        lbl06RCVR1_C.setVisible(false);    
      }
      break;
    case PAGE_RCVRZOS:
      if (radCSClient.isSelected()) {
        lbl07RCVR2_Qa.setVisible(false);    
        lbl08RCVR2_Ca.setVisible(true);    
      } 
      else {
        lbl07RCVR2_Qa.setVisible(true);    
        lbl08RCVR2_Ca.setVisible(false);    
      }
      break;
    case PAGE_RCVRUNIXWIN: 
      if (radCSClient.isSelected()) {
        lbl07RCVR2_Qb.setVisible(false);    
        lbl08RCVR2_Cb.setVisible(true);    
      } 
      else {
        lbl07RCVR2_Qb.setVisible(true);    
        lbl08RCVR2_Cb.setVisible(false);    
      }
      break;
    case PAGE_RCVRCAPI: 
      if (radCSClient.isSelected()) {
        lbl07RCVR2_Qc.setVisible(false);    
        lbl08RCVR2_Cc.setVisible(true);    
      } 
      else {
        lbl07RCVR2_Qc.setVisible(true);    
        lbl08RCVR2_Cc.setVisible(false);    
      }
      break;
    case PAGE_CHL:          
      if (radCSClient.isSelected()) {
        lbl09CHL_Q.setVisible(false);    
        lbl10CHL_C.setVisible(true);    
      } 
      else {
        lbl09CHL_Q.setVisible(true);    
        lbl10CHL_C.setVisible(false);    
      }
      break;
    case PAGE_SNDRCERT:    
      if (radCSClient.isSelected()) {
        lbl11SNDRCERT_Q.setVisible(false);    
        lbl12SNDRCERT_C.setVisible(true);    
      } 
      else {
        lbl11SNDRCERT_Q.setVisible(true);    
        lbl12SNDRCERT_C.setVisible(false);    
      }
      break;
    case PAGE_RCVRCERT:  
      if (radCSClient.isSelected()) {
        if (radAuReq.isSelected()) {
          lbl13RCVRCERT_Q_N.setVisible(false);    
          lbl14RCVRCERT_C_N.setVisible(false);    
          lbl15RCVRCERT_Q_Y.setVisible(false);    
          lbl16RCVRCERT_C_Y.setVisible(true);    
        }
        else {
          lbl13RCVRCERT_Q_N.setVisible(false);    
          lbl14RCVRCERT_C_N.setVisible(true);    
          lbl15RCVRCERT_Q_Y.setVisible(false);    
          lbl16RCVRCERT_C_Y.setVisible(false);    
        }
      } 
      else {
        if (radAuReq.isSelected()) {
          lbl13RCVRCERT_Q_N.setVisible(false);    
          lbl14RCVRCERT_C_N.setVisible(false);    
          lbl15RCVRCERT_Q_Y.setVisible(true);    
          lbl16RCVRCERT_C_Y.setVisible(false);    
        }
        else {
          lbl13RCVRCERT_Q_N.setVisible(true);    
          lbl14RCVRCERT_C_N.setVisible(false);    
          lbl15RCVRCERT_Q_Y.setVisible(false);    
          lbl16RCVRCERT_C_Y.setVisible(false);    
        }
      }
      break;
    case PAGE_CAMODEL:  
      if (radCSClient.isSelected()) {
        if (radAuReq.isSelected()) {
          lbl17CAMODEL_Q_N.setVisible(false);    
          lbl18CAMODEL_C_N.setVisible(false);    
          lbl19CAMODEL_Q_Y.setVisible(false);    
          lbl20CAMODEL_C_Y.setVisible(true);    
        }
        else {
          lbl17CAMODEL_Q_N.setVisible(false);    
          lbl18CAMODEL_C_N.setVisible(true);    
          lbl19CAMODEL_Q_Y.setVisible(false);    
          lbl20CAMODEL_C_Y.setVisible(false);    
        }
      } 
      else {
        if (radAuReq.isSelected()) {
          lbl17CAMODEL_Q_N.setVisible(false);    
          lbl18CAMODEL_C_N.setVisible(false);    
          lbl19CAMODEL_Q_Y.setVisible(true);    
          lbl20CAMODEL_C_Y.setVisible(false);    
        }
        else {
          lbl17CAMODEL_Q_N.setVisible(true);    
          lbl18CAMODEL_C_N.setVisible(false);    
          lbl19CAMODEL_Q_Y.setVisible(false);    
          lbl20CAMODEL_C_Y.setVisible(false);    
        }
      }
      break;
    case PAGE_CAWHERE:  
      setPAGE_CAWHEREVisible(); 
      break;
    case PAGE_CEAU:   
      if (radCSClient.isSelected()) {
        lbl29CEAU1_Q.setVisible(false);    
        lbl30CEAU1_C.setVisible(true);    
      } 
      else {
        lbl29CEAU1_Q.setVisible(true);    
        lbl30CEAU1_C.setVisible(false);    
      }
      break;
    case PAGE_CEAUZOS:   
      if (radCSClient.isSelected()) {
        lbl31CEAU2_Qa.setVisible(false);    
        lbl32CEAU2_Ca.setVisible(true);    
      } 
      else {
        lbl31CEAU2_Qa.setVisible(true);    
        lbl32CEAU2_Ca.setVisible(false);    
      }
      break;
    case PAGE_CEAUUNIXWIN: 
      if (radCSClient.isSelected()) {
        lbl31CEAU2_Qb.setVisible(false);    
        lbl32CEAU2_Cb.setVisible(true);    
      } 
      else {
        lbl31CEAU2_Qb.setVisible(true);    
        lbl32CEAU2_Cb.setVisible(false);    
      }
      break;
    case PAGE_CEAUCAPI: 
      if (radCSClient.isSelected()) {
        lbl31CEAU2_Qc.setVisible(false);    
        lbl32CEAU2_Cc.setVisible(true);    
      } 
      else {
        lbl31CEAU2_Qc.setVisible(true);    
        lbl32CEAU2_Cc.setVisible(false);    
      }
      break;
    case PAGE_CEAUCERT:   
      if (radCSClient.isSelected()) {
        lbl33CEAUCERT_Q.setVisible(false);    
        lbl34CEAUCERT_C.setVisible(true);    
      } 
      else {
        lbl33CEAUCERT_Q.setVisible(true);    
        lbl34CEAUCERT_C.setVisible(false);    
      }
      break;
    }

  }

  /****************************************************************/
  /* Function: calcCertNames                                      */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void calcCertNames() {
    // Sender cert label
    if (radCSClient.isSelected()) {
      SCertLabel = "ibmwebspheremq" + txtSNDRUser.getText().toLowerCase();
    }
    else {
      if (chcSNDRPlatform.getSelectedItem().equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
        SCertLabel = "ibmWebSphereMQ" + txtSNDRQmgrName.getText().toUpperCase();
      }
      else {
        SCertLabel = "ibmwebspheremq" + txtSNDRQmgrName.getText().toLowerCase();
      }
    }

    // Receiver cert label
    if (chcRCVRPlatform.getSelectedItem().equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
      RCertLabel = "ibmWebSphereMQ" + txtRCVRQmgrName.getText().toUpperCase();
    }
    else {
      RCertLabel = "ibmwebspheremq" + txtRCVRQmgrName.getText().toLowerCase();
    }
  }
  
  /****************************************************************/
  /* Function: componentHidden                                    */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  public void componentHidden(ComponentEvent e) { 
  }
  /****************************************************************/
  /* Function: componentMoved                                     */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  public void componentMoved(ComponentEvent e) {
  }
  /****************************************************************/
  /* Function: componentResized                                   */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  public void componentResized(ComponentEvent e) {
    //pnlMain.setBounds(0,0,this.getWidth(),this.getHeight());
    pnlSUMMARY.setBounds(0,0,this.getWidth() - 15, this.getHeight() - 90);
    spnSummary.setBounds(10,45,this.getWidth() - 25, this.getHeight() - 135);
    instructionsPane.setBounds(10,45,this.getWidth() - 25, this.getHeight() - 135);
    pnlTXT.setBounds(0,this.getHeight() - 75,this.getWidth() - 5, 20);
    this.repaint();
  }
  /****************************************************************/
  /* Function: componentShown                                     */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  public void componentShown(ComponentEvent e) {
  }
 
  /****************************************************************/
  /* Function: componentShown                                     */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  public void copySummary() {
    if (currentPage == PAGE_SUMMARY) {
      if (instructionsPane.getSelectedText() == null) {
        instructionsPane.selectAll();
        instructionsPane.copy();
        instructionsPane.select(0,0);
        msg("Summary copied to clipboard");
      }
      else {   
        instructionsPane.copy();
        msg("Selected Summary copied to clipboard");
      }
    }
  }

  /****************************************************************/
  /* Function: createMenuItem                                     */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  public JMenuItem createMenuItem( JMenu menu, String sText,
  				   ImageIcon image, int acceleratorKey,
  				   String sToolTip ) {
    // Create the item
    JMenuItem menuItem = new JMenuItem();

    // Add the item test
    menuItem.setText( sText );

    // Add the optional icon
    if( image != null )
    	menuItem.setIcon( image );

    // Add the accelerator key
    if( acceleratorKey > 0 )
    	menuItem.setMnemonic( acceleratorKey );

    // Add the optional tool tip text
    if( sToolTip != null )
    	menuItem.setToolTipText( sToolTip );

    // Add an action handler to this menu item
    menuItem.addActionListener( this );

    menu.add( menuItem );

    return menuItem;
  }


  /****************************************************************/
  /* Function: fileLoad                                           */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void fileLoad(String filename) {
    MQTLSSSLWizardParms200 parms200 = new MQTLSSSLWizardParms200();
    ObjectInputStream ois = null;
    File tfile=new File(filename);

    msg("Loading file: " + filename);
    if (tfile.exists()) {
      try {
        ois = new ObjectInputStream(new FileInputStream(tfile));

        //try {
          parms200 = (MQTLSSSLWizardParms200)ois.readObject();
          msg("File loaded: " + filename);
        //}
        //catch(ClassCastException ex) {
        //  ois = new ObjectInputStream(new FileInputStream(tfile));
        //  parms200 = (MQTLSSSLWizardParms200)ois.readObject();
        //  parms201.copyIn(parms200);
        //  msg("File loaded and migrated: " + filename);
        //}*/

        ois.close();
      }
      catch(ClassNotFoundException cnfe)
      {
        msg("Open File Failed");
        cnfe.printStackTrace();
        try { if (ois != null) ois.close();} catch (Exception e) { }
        return;
      }
      catch(FileNotFoundException fne)
      {
        msg("Open File Failed");
        fne.printStackTrace();
        return;
      }
      catch(IOException e)
      {
        msg("Open File Failed");
        e.printStackTrace();
        return;
      }
     

      setParms(parms200);
    }
    else {
      msg("File " + filename + " does not exist");
    }

    currentPage = PAGE_SNDR;
    setPage(currentPage);	
  }

  /****************************************************************/
  /* Function: fileOpenData                                       */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void fileOpenData() {
    String filename = new String();
    JFileChooser dd = new JFileChooser();
    int returnVal = dd.showOpenDialog(this);
    
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      filename = dd.getSelectedFile().getAbsolutePath();
      fileLoad(filename);
    }
  }

  /****************************************************************/
  /* Function: fileNew                                            */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void fileNew() {
    final JOptionPane optionPane = new JOptionPane(
                    "New file will clear all data. Are you sure you wish to continue?",
                    JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.YES_NO_OPTION);
    
    final JDialog dialog = new JDialog(this, 
                                 "Click a button",
                                 true);
    dialog.setContentPane(optionPane);
    dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    dialog.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent we) {
        msg("New file cancelled");
        dialog.setVisible(false);
      }
    });
     
    optionPane.addPropertyChangeListener(
      new PropertyChangeListener() {
        public void propertyChange(PropertyChangeEvent e) {
          String prop = e.getPropertyName();
    
          if (dialog.isVisible() 
           && (e.getSource() == optionPane)
           && (prop.equals(JOptionPane.VALUE_PROPERTY))) {
             //If you were going to check something
             //before closing the window, you'd do
             //it here.
             dialog.setVisible(false);
        }
      }
    });
    optionPane.setValue("Not set");

    dialog.pack();
    dialog.setVisible(true);
    
    if (!optionPane.getValue().equals("Not set")) {
      int value = ((Integer)optionPane.getValue()).intValue();
      if (value == JOptionPane.YES_OPTION) {
        resetFields();
        setPage(PAGE_SNDR);
        msg("New file");
      } else if (value == JOptionPane.NO_OPTION) {
        msg("New file cancelled");
      }
    }
  }


  /****************************************************************/
  /* Function: fileSaveData                                       */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void fileSaveData() {
    MQTLSSSLWizardParms200 parms = new MQTLSSSLWizardParms200();
    parms = getParms();


    String filename = new String();
    JFileChooser dd = new JFileChooser();
    int returnVal = dd.showSaveDialog(this);
    
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      filename= dd.getSelectedFile().getAbsolutePath();
  
      try {
        File tfile=new File(filename);
        FileOutputStream fos = new FileOutputStream(tfile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(parms);
        oos.close();
        msg("File Saved");
      }
      catch (Exception e) {
        msg("File Save Failed");
        e.printStackTrace();
      }
    }
  }


  /****************************************************************/
  /* Function: getParms                                           */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private MQTLSSSLWizardParms200 getParms() {
    MQTLSSSLWizardParms200 p = new MQTLSSSLWizardParms200();

    if (radCSClient.isSelected()) {
      p.pageSNDRradCS = true;
    }
    else {
      p.pageSNDRradCS = false;
    }
    if (radJOJava.isSelected()) {
      p.pageSNDRradJO = true;
    }
    else {
      p.pageSNDRradJO = false;
    }

    p.pageSNDRtxtSNDRUser      = txtSNDRUser.getText();
    p.pageSNDRtxtSNDRQmgrName  = txtSNDRQmgrName.getText();
    p.pageSNDRtxtSNDRHost      = txtSNDRHost.getText();    
    p.pageSNDRchcSNDRPlatform  = (String)chcSNDRPlatform.getSelectedItem();

    p.pageSNDRZOStxtSNDRTasks       = txtSNDRTasks.getText();
    p.pageSNDRZOStxtSNDRID          = txtSNDRID.getText();
    p.pageSNDRZOStxtSNDRKeyringZOS  = txtSNDRKeyringZOS.getText();

    p.pageSNDRUNIXWINtxtSNDRKeyringUNIXWIN  = txtSNDRKeyringUNIXWIN.getText(); 
    p.pageSNDRUNIXWINchcSNDRCommand  = (String)chcSNDRCommand.getSelectedItem(); 

    if (cbxSNDRFips.isSelected()) {
      p.pageSNDRCAPIcbxSNDRFips = true;
    }
    else {
      p.pageSNDRCAPIcbxSNDRFips = false;
    }
    p.pageSNDRCAPIchcSNDRSigalg  = (String)chcSNDRSigalg.getSelectedItem(); 

    p.pageRCVRtxtRCVRQmgrName  = txtRCVRQmgrName.getText(); 
    p.pageRCVRtxtRCVRHost      = txtRCVRHost.getText(); 
    p.pageRCVRtxtRCVRPort      = txtRCVRPort.getText(); 
    p.pageRCVRchcRCVRPlatform  = (String)chcRCVRPlatform.getSelectedItem(); 

    p.pageRCVRZOStxtRCVRTasks       = txtRCVRTasks.getText(); 
    p.pageRCVRZOStxtRCVRID          = txtRCVRID.getText(); 
    p.pageRCVRZOStxtRCVRKeyringZOS  = txtRCVRKeyringZOS.getText(); 

    p.pageRCVRUNIXWINtxtRCVRKeyringUNIXWIN = txtRCVRKeyringUNIXWIN.getText(); 
    p.pageRCVRUNIXWINchcRCVRCommand  = (String)chcRCVRCommand.getSelectedItem(); 

    if (cbxRCVRFips.isSelected()) {
      p.pageRCVRCAPIcbxRCVRFips = true;
    }
    else {
      p.pageRCVRCAPIcbxRCVRFips = false;
    }
    p.pageRCVRCAPIchcRCVRSigalg  = (String)chcRCVRSigalg.getSelectedItem(); 

    p.pageCHLtxtChannel      = txtChannel.getText(); 
    if (cbxQmgrFips.isSelected()) {
      p.pageCHLcbxQmgrFips = true;
    }
    else {
      p.pageCHLcbxQmgrFips = false;
    }
    p.pageCHLchcCipherSpec   = (String)chcCipherSpec.getSelectedItem(); 
    if (radAuReq.isSelected()) {
      p.pageCHLradAuReq = true;
    }
    else {
      p.pageCHLradAuReq = false;
    }

    p.pageSNDRCERTtxtSNDRLabel  = txtSNDRLabel.getText(); 
    p.pageSNDRCERTchcSNDRDNCN   = (String)chcSNDRDNCN.getSelectedItem(); 
    p.pageSNDRCERTtxtSNDRDNOU   = txtSNDRDNOU.getText(); 
    p.pageSNDRCERTtxtSNDRDNO    = txtSNDRDNO.getText(); 
    p.pageSNDRCERTtxtSNDRDNL    = txtSNDRDNL.getText(); 
    p.pageSNDRCERTtxtSNDRDNST   = txtSNDRDNST.getText(); 
    p.pageSNDRCERTtxtSNDRDNC    = txtSNDRDNC.getText(); 
    p.pageSNDRCERTtxtSNDRExpiry = txtSNDRExpiry.getText(); 

    p.pageRCVRCERTtxtRCVRLabel = txtRCVRLabel.getText(); 
    p.pageRCVRCERTchcRCVRDNCN  = (String)chcRCVRDNCN.getSelectedItem(); 
    p.pageRCVRCERTtxtRCVRDNOU  = txtRCVRDNOU.getText(); 
    p.pageRCVRCERTtxtRCVRDNO   = txtRCVRDNO.getText(); 
    p.pageRCVRCERTtxtRCVRDNL   = txtRCVRDNL.getText(); 
    p.pageRCVRCERTtxtRCVRDNST  = txtRCVRDNST.getText(); 
    p.pageRCVRCERTtxtRCVRDNC   = txtRCVRDNC.getText(); 
    p.pageRCVRCERTtxtRCVRExpiry= txtRCVRExpiry.getText(); 

    if (radTypeSS.isSelected()) {
      p.pageCEAUMODELradType = true;
    }
    else {
      p.pageCEAUMODELradType = false;
    }

    if (radTypeCAI.isSelected()) {
      p.pageCEAUWHEREradType = true;
    }
    else {
      p.pageCEAUWHEREradType = false;
    }

    p.pageCEAUtxtCEAUHost      = txtCEAUHost.getText(); 
    p.pageCEAUchcCEAUPlatform  = (String)chcCEAUPlatform.getSelectedItem(); 

    p.pageCEAUZOStxtCEAUID         = txtCEAUID.getText(); 
    p.pageCEAUZOStxtCEAUKeyringZOS = txtCEAUKeyringZOS.getText(); 

    p.pageCEAUUNIXWINtxtCEAUKeyringUNIXWIN= txtCEAUKeyringUNIXWIN.getText(); 
    p.pageCEAUUNIXWINchcCEAUCommand  = (String)chcCEAUCommand.getSelectedItem(); 

    if (cbxCEAUFips.isSelected()) {
      p.pageCEAUCAPIcbxCEAUFips = true;
    }
    else {
      p.pageCEAUCAPIcbxCEAUFips = false;
    }
    p.pageCEAUCAPIchcCEAUSigalg  = (String)chcCEAUSigalg.getSelectedItem(); 

    p.pageCEAUCERTtxtCEAULabel   = txtCEAULabel.getText(); 
    p.pageCEAUCERTtxtCEAUDNCN   = txtCEAUDNCN.getText(); 
    p.pageCEAUCERTtxtCEAUDNOU   = txtCEAUDNOU.getText(); 
    p.pageCEAUCERTtxtCEAUDNO    = txtCEAUDNO.getText(); 
    p.pageCEAUCERTtxtCEAUDNL    = txtCEAUDNL.getText(); 
    p.pageCEAUCERTtxtCEAUDNST   = txtCEAUDNST.getText(); 
    p.pageCEAUCERTtxtCEAUDNC    = txtCEAUDNC.getText(); 
    p.pageCEAUCERTtxtCEAUExpiry = txtCEAUExpiry.getText(); 

    return p;
  }

  /****************************************************************/
  /* Function: setParms                                           */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void setParms(MQTLSSSLWizardParms200 p) {

    if (p.pageSNDRradCS == true) {
      radCSClient.setSelected(true);
    }
    else {
      radCSServer.setSelected(true);
    }
    if (p.pageSNDRradJO == true) {
      radJOJava.setSelected(true);
    }
    else {
      radJOOther.setSelected(true);
    }

    txtSNDRUser.setText(p.pageSNDRtxtSNDRUser)                 ;
    txtSNDRQmgrName.setText(p.pageSNDRtxtSNDRQmgrName)         ;
    txtSNDRHost.setText(p.pageSNDRtxtSNDRHost)                 ;
    chcSNDRPlatform.setSelectedItem(p.pageSNDRchcSNDRPlatform) ;

    txtSNDRTasks.setText(p.pageSNDRZOStxtSNDRTasks);
    txtSNDRID.setText(p.pageSNDRZOStxtSNDRID);
    txtSNDRKeyringZOS.setText(p.pageSNDRZOStxtSNDRKeyringZOS);

    txtSNDRKeyringUNIXWIN.setText(p.pageSNDRUNIXWINtxtSNDRKeyringUNIXWIN); 
    chcSNDRCommand.setSelectedItem(p.pageSNDRUNIXWINchcSNDRCommand); 

    if (p.pageSNDRCAPIcbxSNDRFips == true) {
      cbxSNDRFips.setSelected(true);
    }
    chcSNDRSigalg.setSelectedItem(p.pageSNDRCAPIchcSNDRSigalg); 

    txtRCVRQmgrName.setText(p.pageRCVRtxtRCVRQmgrName); 
    txtRCVRHost.setText(p.pageRCVRtxtRCVRHost); 
    txtRCVRPort.setText(p.pageRCVRtxtRCVRPort); 
    chcRCVRPlatform.setSelectedItem(p.pageRCVRchcRCVRPlatform); 

    txtRCVRTasks.setText(p.pageRCVRZOStxtRCVRTasks); 
    txtRCVRID.setText(p.pageRCVRZOStxtRCVRID); 
    txtRCVRKeyringZOS.setText(p.pageRCVRZOStxtRCVRKeyringZOS); 

    txtRCVRKeyringUNIXWIN.setText(p.pageRCVRUNIXWINtxtRCVRKeyringUNIXWIN); 
    chcRCVRCommand.setSelectedItem(p.pageRCVRUNIXWINchcRCVRCommand); 

    if (p.pageRCVRCAPIcbxRCVRFips == true) {
      cbxRCVRFips.setSelected(true);
    }
    chcRCVRSigalg.setSelectedItem(p.pageRCVRCAPIchcRCVRSigalg); 

    txtChannel.setText(p.pageCHLtxtChannel); 
    if (p.pageCHLcbxQmgrFips == true) {
      cbxQmgrFips.setSelected(true);
    }
    chcCipherSpec.setSelectedItem(p.pageCHLchcCipherSpec); 
    if (p.pageCHLradAuReq == true) {
      radAuReq.setSelected(true);
    }
    else {
      radAuOpt.setSelected(true);
    }


    txtSNDRLabel.setText(p.pageSNDRCERTtxtSNDRLabel); 
    chcSNDRDNCN.setSelectedItem(p.pageSNDRCERTchcSNDRDNCN); 
    txtSNDRDNOU.setText(p.pageSNDRCERTtxtSNDRDNOU); 
    txtSNDRDNO.setText(p.pageSNDRCERTtxtSNDRDNO); 
    txtSNDRDNL.setText(p.pageSNDRCERTtxtSNDRDNL); 
    txtSNDRDNST.setText(p.pageSNDRCERTtxtSNDRDNST); 
    txtSNDRDNC.setText(p.pageSNDRCERTtxtSNDRDNC); 
    txtSNDRExpiry.setText(p.pageSNDRCERTtxtSNDRExpiry); 

    txtRCVRLabel.setText(p.pageRCVRCERTtxtRCVRLabel); 
    chcRCVRDNCN.setSelectedItem(p.pageRCVRCERTchcRCVRDNCN); 
    txtRCVRDNOU.setText(p.pageRCVRCERTtxtRCVRDNOU); 
    txtRCVRDNO.setText(p.pageRCVRCERTtxtRCVRDNO); 
    txtRCVRDNL.setText(p.pageRCVRCERTtxtRCVRDNL); 
    txtRCVRDNST.setText(p.pageRCVRCERTtxtRCVRDNST); 
    txtRCVRDNC.setText(p.pageRCVRCERTtxtRCVRDNC); 
    txtRCVRExpiry.setText(p.pageRCVRCERTtxtRCVRExpiry); 

    if (p.pageCEAUMODELradType == true) {
      radTypeSS.setSelected(true);
    }
    else {
      radTypeCA.setSelected(true);
    }

    if (p.pageCEAUWHEREradType == true) {
      radTypeCAI.setSelected(true);
    }
    else {
      radTypeCAX.setSelected(true);
    }

    txtCEAUHost.setText(p.pageCEAUtxtCEAUHost); 
    chcCEAUPlatform.setSelectedItem(p.pageCEAUchcCEAUPlatform); 

    txtCEAUID.setText(p.pageCEAUZOStxtCEAUID); 
    txtCEAUKeyringZOS.setText(p.pageCEAUZOStxtCEAUKeyringZOS); 

    txtCEAUKeyringUNIXWIN.setText(p.pageCEAUUNIXWINtxtCEAUKeyringUNIXWIN); 
    chcCEAUCommand.setSelectedItem(p.pageCEAUUNIXWINchcCEAUCommand); 

    if (p.pageCEAUCAPIcbxCEAUFips == true) {
      cbxCEAUFips.setSelected(true);
    }
    chcCEAUSigalg.setSelectedItem(p.pageCEAUCAPIchcCEAUSigalg); 

    txtCEAULabel.  setText(p.pageCEAUCERTtxtCEAULabel); 
    txtCEAUDNCN.  setText(p.pageCEAUCERTtxtCEAUDNCN); 
    txtCEAUDNOU.  setText(p.pageCEAUCERTtxtCEAUDNOU); 
    txtCEAUDNO.   setText(p.pageCEAUCERTtxtCEAUDNO ); 
    txtCEAUDNL.   setText(p.pageCEAUCERTtxtCEAUDNL ); 
    txtCEAUDNST.  setText(p.pageCEAUCERTtxtCEAUDNST); 
    txtCEAUDNC.   setText(p.pageCEAUCERTtxtCEAUDNC ); 
    txtCEAUExpiry.setText(p.pageCEAUCERTtxtCEAUExpiry); 

  }

  /****************************************************************/
  /* Function: fileSaveReport                                     */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void fileSaveReport() {
    String filename = new String();
    JFileChooser dd = new JFileChooser();
    dd.addChoosableFileFilter(new WMQSSLWizardFilter(WMQSSLWizardFilter.FT_HTML));

    int returnVal = dd.showSaveDialog(this);
    
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      filename= dd.getSelectedFile().getAbsolutePath();
      if (!filename.endsWith(".html")) {
        filename = filename + ".html";
      }
    
      int ch;
  
      try {
        File tfile=new File(filename);
        FileWriter a = new FileWriter(tfile);
        
        a.write(instructionsPane.getText());
        a.close();
        msg("File saved");
      }
      catch (Exception e) {
        msg("File Save Failed");
        e.printStackTrace();
      }
    }
  }

  /****************************************************************/
  /* Function: showInstructions                                   */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void showInstructions(MQTLSSSLWizardParms200 p) {
    String i = new String();

    i = "<html><title>IBM MQ TLS/SSL Wizard "+ VERSION + " Output</title><body>";

    i = i.concat(p.generateIntructions());

    i = i.concat("</body></html>");
    instructionsPane.setText(i);
  }


  /****************************************************************/
  /* Function: keyPressed                                         */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  public void keyPressed(KeyEvent ev) {
    if (ev.getKeyCode() == KeyEvent.VK_ENTER) {
      if (ev.getSource() == btnSNDRBrowse) {
        eventSBrowse();
      }
      else if (ev.getSource() == btnRCVRBrowse) {
        eventRBrowse();
      }
      else if (ev.getSource() == btnPrev) {
        eventPrev();
      }
      else if (ev.getSource() == btnNext) {
        eventNext();
      }
      else if (ev.getSource() == btnSave) {
        eventSave(); 
      }
    }
    else if (ev.getKeyCode() == KeyEvent.VK_C) {
      if (ev.isControlDown()) {
        copySummary();
      }
    }
    else if (ev.getKeyCode() == KeyEvent.VK_S) {
      if (ev.isControlDown()) {
        fileSaveData();
      }
    }
    else if (ev.getKeyCode() == KeyEvent.VK_O) {
      if (ev.isControlDown()) {
        fileOpenData();
      }
    }
  }
  
  /****************************************************************/
  /* Function: keyReleased                                        */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  public void keyReleased(KeyEvent e) {
  }
  
  /****************************************************************/
  /* Function: keyTyped                                           */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  public void keyTyped(KeyEvent e) {
  }
 
  /****************************************************************/
  /* Function: msg                                                */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void msg(String str) {
    txtMsg.setText(str);
    if (!str.equals("")) System.out.println("MSG: " + str );

  }

  /****************************************************************/
  /* Function: resetFields                                        */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void resetFields() {
    radCSServer.setSelected(true);
    radJOJava.setSelected(true);
    txtSNDRUser.setText("");
    txtSNDRQmgrName.setText("");
    txtSNDRHost.setText("");
    chcSNDRPlatform.setSelectedIndex(-1);

    txtSNDRTasks.setText("");
    txtSNDRID.setText("");
    txtSNDRKeyringZOS.setText("");

    txtSNDRKeyringUNIXWIN.setText("");
    chcSNDRCommand.setSelectedIndex(-1);

    cbxSNDRFips.setSelected(true);
    chcSNDRSigalg.setSelectedItem(MQTLSSSLWizardGlobal.SIGALG_SHA1);

    txtRCVRQmgrName.setText("");
    txtRCVRHost.setText("");
    txtRCVRPort.setText("");
    chcRCVRPlatform.setSelectedIndex(-1);
    
    txtRCVRTasks.setText("");
    txtRCVRID.setText("");
    txtRCVRKeyringZOS.setText("");

    txtRCVRKeyringUNIXWIN.setText("");
    chcRCVRCommand.setSelectedIndex(-1);
    
    cbxRCVRFips.setSelected(true);
    chcRCVRSigalg.setSelectedItem(MQTLSSSLWizardGlobal.SIGALG_SHA1);

    txtChannel.setText("");
    chcCipherSpec.setSelectedIndex(-1);
    radAuReq.setSelected(true);
    
    chcSNDRDNCN.removeAllItems();
    txtSNDRDNOU.setText("");
    txtSNDRDNO.setText("");
    txtSNDRDNL.setText("");
    txtSNDRDNST.setText("");
    txtSNDRDNC.setText("");
    txtSNDRExpiry.setText("365");
    
    chcRCVRDNCN.removeAllItems();
    txtRCVRDNOU.setText("");    
    txtRCVRDNO.setText("");    
    txtRCVRDNL.setText("");          
    txtRCVRDNST.setText("");          
    txtRCVRDNC.setText("");             
    txtRCVRExpiry.setText("365");

    radTypeSS.setSelected(true);

    radTypeCAX.setSelected(true);

    txtCEAUHost.setText("");
    chcCEAUPlatform.setSelectedIndex(-1);

    txtCEAUID.setText("");
    txtCEAUKeyringZOS.setText("");

    txtCEAUKeyringUNIXWIN.setText("");
    chcCEAUCommand.setSelectedIndex(-1);

    cbxCEAUFips.setSelected(true);
    chcCEAUSigalg.setSelectedItem(MQTLSSSLWizardGlobal.SIGALG_SHA1);

    txtCEAULabel.setText("");
    txtCEAUDNCN.setText("");
    txtCEAUDNOU.setText("");
    txtCEAUDNO .setText("");
    txtCEAUDNL .setText("");
    txtCEAUDNST.setText("");
    txtCEAUDNC .setText("");
    txtCEAUExpiry.setText("366");

  }


  /****************************************************************/
  /* Function: setPage                                            */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void setPage(int page) {
    pnl1SNDR         .setVisible(false);
    pnl1SNDRZOS      .setVisible(false);
    pnl1SNDRUNIXWIN  .setVisible(false);
    pnl1SNDRCAPI     .setVisible(false);
    pnl1RCVR        .setVisible(false);
    pnl1RCVRZOS     .setVisible(false);
    pnl1RCVRUNIXWIN .setVisible(false);
    pnl1RCVRCAPI    .setVisible(false);
    pnl1CHL         .setVisible(false);
    pnl1CAMODEL     .setVisible(false);
    pnl1CAWHERE     .setVisible(false);
    pnl1CEAU          .setVisible(false);
    pnl1CEAUZOS       .setVisible(false);
    pnl1CEAUUNIXWIN   .setVisible(false);
    pnl1CEAUCAPI      .setVisible(false);
    pnl1CEAUCERT      .setVisible(false);
    pnl1SNDRCERT     .setVisible(false);
    pnl1RCVRCERT    .setVisible(false);

    pnl2SNDR         .setVisible(false);
    pnl2SNDRZOS      .setVisible(false);
    pnl2SNDRUNIXWIN  .setVisible(false);
    pnl2SNDRCAPI     .setVisible(false);
    pnl2RCVR        .setVisible(false);
    pnl2RCVRZOS     .setVisible(false);
    pnl2RCVRUNIXWIN .setVisible(false);
    pnl2RCVRCAPI    .setVisible(false);
    pnl2CHL         .setVisible(false);
    pnl2CAMODEL     .setVisible(false);
    pnl2CAWHERE     .setVisible(false);
    pnl2CEAU          .setVisible(false);
    pnl2CEAUZOS       .setVisible(false);
    pnl2CEAUUNIXWIN   .setVisible(false);
    pnl2CEAUCAPI      .setVisible(false);
    pnl2CEAUCERT      .setVisible(false);
    pnl2SNDRCERT     .setVisible(false);
    pnl2RCVRCERT    .setVisible(false);

    pnlGENERATE    .setVisible(false);
    pnlSUMMARY     .setVisible(false);

    btnPrev.setVisible(false);  
    btnNext.setVisible(false);  
    if (page > PAGE_SNDR) {
      btnPrev.setVisible(true);  
    }
    if (page < PAGE_SUMMARY) {
      btnNext.setVisible(true);  
      pnlNAV.setBounds(0,300,540,20);
    }
    else {
      pnlNAV.setBounds(0,10,115,20);
    }

    btnPrev.requestFocus();
    if (page == PAGE_SNDR) {
      setCSeditable();
      btnNext.requestFocus();
      pnl1SNDR.setVisible(true);
      pnl2SNDR.setVisible(true);
    }
    else if (page == PAGE_SNDRZOS) {
      pnl1SNDRZOS.setVisible(true);
      pnl2SNDRZOS.setVisible(true);
    }
    else if (page == PAGE_SNDRUNIXWIN) {
      pnl1SNDRUNIXWIN.setVisible(true);
      pnl2SNDRUNIXWIN.setVisible(true);
    }
    else if (page == PAGE_SNDRCAPI) {
      pnl1SNDRCAPI.setVisible(true);
      pnl2SNDRCAPI.setVisible(true);
    }
    else if (page == PAGE_RCVR) {
      pnl1RCVR.setVisible(true);
      pnl2RCVR.setVisible(true);
    }
    else if (page == PAGE_RCVRZOS) {
      pnl1RCVRZOS.setVisible(true);
      pnl2RCVRZOS.setVisible(true);
    }
    else if (page == PAGE_RCVRUNIXWIN) {
      pnl1RCVRUNIXWIN.setVisible(true);
      pnl2RCVRUNIXWIN.setVisible(true);
    }
    else if (page == PAGE_RCVRCAPI) {
      pnl1RCVRCAPI.setVisible(true);
      pnl2RCVRCAPI.setVisible(true);
    }
    else if (page == PAGE_CHL) {
      pnl1CHL.setVisible(true);
      pnl2CHL.setVisible(true);
    }
    else if (page == PAGE_SNDRCERT) {
      pnl1SNDRCERT.setVisible(true);
      pnl2SNDRCERT.setVisible(true);
    }
    else if (page == PAGE_RCVRCERT) {
      if (radAuReq.isSelected()) {
        lbl13RCVRCERT_Q_N.setVisible(false);    
        lbl14RCVRCERT_C_N.setVisible(false);    
        lbl15RCVRCERT_Q_Y.setVisible(true);    
        lbl16RCVRCERT_C_Y.setVisible(true);    
      }
      else {
        lbl13RCVRCERT_Q_N.setVisible(true);    
        lbl14RCVRCERT_C_N.setVisible(true);    
        lbl15RCVRCERT_Q_Y.setVisible(false);    
        lbl16RCVRCERT_C_Y.setVisible(false);    
      }

      pnl1RCVRCERT.setVisible(true);
      pnl2RCVRCERT.setVisible(true);
    }
    else if (page == PAGE_CAMODEL) {
      pnl1CAMODEL.setVisible(true);
      pnl2CAMODEL.setVisible(true);
    }
    else if (page == PAGE_CAWHERE) {
      pnl1CAWHERE.setVisible(true);
      pnl2CAWHERE.setVisible(true);
    }
    else if (page == PAGE_CEAUCERT) {
      pnl1CEAUCERT.setVisible(true);
      pnl2CEAUCERT.setVisible(true);
    }
    else if (page == PAGE_CEAU) {
      pnl1CEAU.setVisible(true);
      pnl2CEAU.setVisible(true);
    }
    else if (page == PAGE_CEAUZOS) {
      pnl1CEAUZOS.setVisible(true);
      pnl2CEAUZOS.setVisible(true);
    }
    else if (page == PAGE_CEAUUNIXWIN) {
      pnl1CEAUUNIXWIN.setVisible(true);
      pnl2CEAUUNIXWIN.setVisible(true);
    }
    else if (page == PAGE_CEAUCAPI) {
      pnl1CEAUCAPI.setVisible(true);
      pnl2CEAUCAPI.setVisible(true);
    }
    else if (page == PAGE_GENERATE) {
      pnlGENERATE.setVisible(true);
    }
    else if (page == PAGE_SUMMARY) {
      pnlSUMMARY.setVisible(true);
      menuEditCopy.setEnabled(true);
    }
  }

  /****************************************************************/
  /* Function: setupScreen                                        */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private JPanel setupPanel(JPanel _p, int x, int y, int w, int h) {
    JPanel p = new JPanel();
    p.setLayout( null );
    p.setBounds(x,y,w,h);
    //p.setBackground(Color.orange);
    getContentPane().add( p );
    return p;
  }

  /****************************************************************/
  /* Function: setupScreen                                        */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void setupScreen() {

    this.getContentPane().setLayout( null );

    pnlNAV           = setupPanel(pnlNAV         ,0,300,540,20);
    pnlTXT           = setupPanel(pnlTXT         ,0,365,540,20);

    pnl1SNDR          = setupPanel(pnl1SNDR        ,0,0,540,295);
    pnl1SNDRZOS       = setupPanel(pnl1SNDRZOS     ,0,0,540,295);
    pnl1SNDRUNIXWIN   = setupPanel(pnl1SNDRUNIXWIN ,0,0,540,295);
    pnl1SNDRCAPI      = setupPanel(pnl1SNDRCAPI    ,0,0,540,295);
    pnl1RCVR          = setupPanel(pnl1RCVR        ,0,0,540,295);
    pnl1RCVRZOS       = setupPanel(pnl1RCVRZOS     ,0,0,540,295);
    pnl1RCVRUNIXWIN   = setupPanel(pnl1RCVRUNIXWIN ,0,0,540,295);
    pnl1RCVRCAPI      = setupPanel(pnl1RCVRCAPI    ,0,0,540,295);
    pnl1CHL           = setupPanel(pnl1CHL         ,0,0,540,295);
    pnl1CAMODEL       = setupPanel(pnl1CAMODEL     ,0,0,540,295);
    pnl1CAWHERE       = setupPanel(pnl1CAWHERE     ,0,0,540,295);
    pnl1CEAU          = setupPanel(pnl1CEAU        ,0,0,540,295);
    pnl1CEAUZOS       = setupPanel(pnl1CEAUZOS     ,0,0,540,295);
    pnl1CEAUUNIXWIN   = setupPanel(pnl1CEAUUNIXWIN ,0,0,540,295);
    pnl1CEAUCAPI      = setupPanel(pnl1CEAUCAPI    ,0,0,540,295);
    pnl1CEAUCERT      = setupPanel(pnl1CEAUCERT    ,0,0,540,295);
    pnl1SNDRCERT      = setupPanel(pnl1SNDRCERT    ,0,0,540,295);
    pnl1RCVRCERT      = setupPanel(pnl1RCVRCERT    ,0,0,540,295);

    pnl2SNDR          = setupPanel(pnl2SNDR        ,12,332,540,275);
    pnl2SNDRZOS       = setupPanel(pnl2SNDRZOS     ,12,332,540,275);
    pnl2SNDRUNIXWIN   = setupPanel(pnl2SNDRUNIXWIN ,12,332,540,275);
    pnl2SNDRCAPI      = setupPanel(pnl2SNDRCAPI    ,12,332,540,275);
    pnl2RCVR          = setupPanel(pnl2RCVR        ,12,332,540,275);
    pnl2RCVRZOS       = setupPanel(pnl2RCVRZOS     ,12,332,540,275);
    pnl2RCVRUNIXWIN   = setupPanel(pnl2RCVRUNIXWIN ,12,332,540,275);
    pnl2RCVRCAPI      = setupPanel(pnl2RCVRCAPI    ,12,332,540,275);
    pnl2CHL           = setupPanel(pnl2CHL         ,12,332,540,275);
    pnl2CAMODEL       = setupPanel(pnl2CAMODEL     ,12,332,540,275);
    pnl2CAWHERE       = setupPanel(pnl2CAWHERE     ,12,332,540,275);
    pnl2CEAU          = setupPanel(pnl2CEAU        ,12,332,540,275);
    pnl2CEAUZOS       = setupPanel(pnl2CEAUZOS     ,12,332,540,275);
    pnl2CEAUUNIXWIN   = setupPanel(pnl2CEAUUNIXWIN ,12,332,540,275);
    pnl2CEAUCAPI      = setupPanel(pnl2CEAUCAPI    ,12,332,540,275);
    pnl2CEAUCERT      = setupPanel(pnl2CEAUCERT    ,12,332,540,275);
    pnl2SNDRCERT      = setupPanel(pnl2SNDRCERT    ,12,332,540,275);
    pnl2RCVRCERT      = setupPanel(pnl2RCVRCERT    ,12,332,540,275);

    pnlGENERATE      = setupPanel(pnlGENERATE    ,0,0,540,595);
    pnlSUMMARY       = setupPanel(pnlSUMMARY     ,0,0,540,595);

    setTitle("IBM MQ TLS/SSL Wizard ");
    
    // Create the menu bar
    menuBar = new JMenuBar();

    // Set this instance as the application's menu bar
    setJMenuBar( menuBar );

    // Create the file menu
    menuFile = new JMenu( "File" );
    menuFile.setMnemonic('F');
    menuBar.add( menuFile );

    // Create the edit menu
    menuEdit = new JMenu( "Edit" );
    menuEdit.setMnemonic('E');
    menuBar.add( menuEdit );

    // Create the help menu
    menuHelp = new JMenu( "Help" );
    menuHelp.setMnemonic('H');
    menuBar.add( menuHelp );
    
    // Create the file menu
    // Build a file menu items
    menuFileNew = createMenuItem( menuFile, "New",
                                   new ImageIcon( "new.gif" ), 'N',
                                   "Clear fields" );
    menuFileOpen = createMenuItem( menuFile, "Open...",
    				   new ImageIcon( "open.gif" ), 'O',
    				   "Open a new file" );
    menuFileSaveAs = createMenuItem( menuFile, 
    				     "Save As...", null, 'A',
    				     "Save this data to a new file" );
    menuFileExit = createMenuItem( menuFile, 
    				   "Exit", null, 'x',
    				   "Exit the program" );

    // Create the edit menu
    menuEditCopy = createMenuItem( menuEdit, "Copy",
    				   new ImageIcon( "copy.gif" ), 'C',
    				   "Copy" );

    // Create the help menu
    menuHelpHelp = createMenuItem( menuHelp, "Help",
    				   new ImageIcon( "help.gif" ), 'P',
    				   "Help" );
    menuHelpAbout = createMenuItem( menuHelp, "About",
    				   new ImageIcon( "about.gif" ), 'T',
    				   "About" );
    
    grpCA.add(radTypeSS);
    grpCA.add(radTypeCA);
    radTypeSS.setSelected(true);

    grpIX.add(radTypeCAI);
    grpIX.add(radTypeCAX);
    radTypeCAX.setSelected(true);
    radTypeCAI.addActionListener(this);   
    radTypeCAX.addActionListener(this);   

    grpCS.add(radCSClient);
    grpCS.add(radCSServer);
    radCSServer.setSelected(true);
    radCSClient.addActionListener(this);   
    radCSServer.addActionListener(this);   

    grpJO.add(radJOJava);
    grpJO.add(radJOOther);
    radJOJava.setSelected(true);
    radJOJava.addActionListener(this);   
    radJOOther.addActionListener(this);   

    cbxQmgrFips.addActionListener(this);   
     
    setPlatforms();
    
    chcRCVRPlatform.addItem(MQTLSSSLWizardGlobal.PLATFORM_ZOS);
    chcRCVRPlatform.addItem(MQTLSSSLWizardGlobal.PLATFORM_UNIX);
    chcRCVRPlatform.addItem(MQTLSSSLWizardGlobal.PLATFORM_WIN);

    chcCEAUPlatform.addItem(MQTLSSSLWizardGlobal.PLATFORM_ZOS);
    chcCEAUPlatform.addItem(MQTLSSSLWizardGlobal.PLATFORM_UNIX);
    chcCEAUPlatform.addItem(MQTLSSSLWizardGlobal.PLATFORM_WIN);

    chcSNDRCommand.addItem(MQTLSSSLWizardGlobal.COMMAND_UNIX);
    chcSNDRCommand.addItem(MQTLSSSLWizardGlobal.COMMAND_WIN);
    chcSNDRCommand.addItem(MQTLSSSLWizardGlobal.COMMAND_CAPI);

    chcRCVRCommand.addItem(MQTLSSSLWizardGlobal.COMMAND_UNIX);
    chcRCVRCommand.addItem(MQTLSSSLWizardGlobal.COMMAND_WIN);
    chcRCVRCommand.addItem(MQTLSSSLWizardGlobal.COMMAND_CAPI);

    chcCEAUCommand.addItem(MQTLSSSLWizardGlobal.COMMAND_UNIX);
    chcCEAUCommand.addItem(MQTLSSSLWizardGlobal.COMMAND_WIN);
    chcCEAUCommand.addItem(MQTLSSSLWizardGlobal.COMMAND_CAPI);

    chcSNDRSigalg.addItem(MQTLSSSLWizardGlobal.SIGALG_SHA1);
    chcSNDRSigalg.addItem(MQTLSSSLWizardGlobal.SIGALG_SHA224);
    chcSNDRSigalg.addItem(MQTLSSSLWizardGlobal.SIGALG_SHA256);
    chcSNDRSigalg.addItem(MQTLSSSLWizardGlobal.SIGALG_SHA384);
    chcSNDRSigalg.addItem(MQTLSSSLWizardGlobal.SIGALG_SHA512);

    chcRCVRSigalg.addItem(MQTLSSSLWizardGlobal.SIGALG_SHA1);
    chcRCVRSigalg.addItem(MQTLSSSLWizardGlobal.SIGALG_SHA224);
    chcRCVRSigalg.addItem(MQTLSSSLWizardGlobal.SIGALG_SHA256);
    chcRCVRSigalg.addItem(MQTLSSSLWizardGlobal.SIGALG_SHA384);
    chcRCVRSigalg.addItem(MQTLSSSLWizardGlobal.SIGALG_SHA512);

    chcCEAUSigalg.addItem(MQTLSSSLWizardGlobal.SIGALG_SHA1);
    chcCEAUSigalg.addItem(MQTLSSSLWizardGlobal.SIGALG_SHA224);
    chcCEAUSigalg.addItem(MQTLSSSLWizardGlobal.SIGALG_SHA256);
    chcCEAUSigalg.addItem(MQTLSSSLWizardGlobal.SIGALG_SHA384);
    chcCEAUSigalg.addItem(MQTLSSSLWizardGlobal.SIGALG_SHA512);

    setCipherSpec();
    grpAu.add(radAuReq);
    grpAu.add(radAuOpt);
    radAuReq.setSelected(true);
    

    txtSNDRLabel.setEditable(false);
    txtRCVRLabel.setEditable(false);


    txtMsg.setEditable(false);
    txtMsg.setFocusable(false);
    instructionsPane.setEditable(false);

    chcSNDRDNCN.setEditable(true);
    chcRCVRDNCN.setEditable(true);


    btnPrev.addActionListener(this);
    btnNext.addActionListener(this);
    btnSNDRBrowse.addActionListener(this);
    btnRCVRBrowse.addActionListener(this);
    btnSave.addActionListener(this);

    btnPrev.addKeyListener(this);
    btnNext.addKeyListener(this);
    btnSNDRBrowse.addKeyListener(this);
    btnRCVRBrowse.addKeyListener(this);
    btnSave.addKeyListener(this);
    
    txtMsg.setBounds(0,0,535,20);
    //txtMsg.setForeground(Color.red);
    
    btnPrev.setBounds(10,0,100,20);
    btnNext.setBounds(125,0,100,20);
    btnSave.setBounds(380,10,145,20);   
    
    lblPAGE_SNDRTitle.setBounds(10,5,200,20);
    txtSNDRUser.setBounds(125,40,398,20);
    radCSClient.setBounds(10,40,100,20);
    radCSServer.setBounds(10,130,100,20); 
    radJOJava.setBounds(125,70,100,20);
    radJOOther.setBounds(250,70,100,20); 
    txtSNDRQmgrName.setBounds(125,130,398,20);
    lblSNDRHost.setBounds(20,190,100,20);
    txtSNDRHost.setBounds(125,190,398,20);
    lblSNDRPlatform.setBounds(20,220,100,20);
    chcSNDRPlatform.setBounds(125,220,175,20);
    chcSNDRPlatform.setBackground(Color.white);

    lblPAGE_SNDRZOSTitle.setBounds(10,5,200,20);
    lblSNDRKeyringZOS.setBounds(20,40,100,20);   
    txtSNDRKeyringZOS.setBounds(125,40,398,20); 
    lblSNDRTasks.setBounds(20,70,100,20);   
    txtSNDRTasks.setBounds(125,70,100,20); 
    lblSNDRID.setBounds(20,100,100,20);  
    txtSNDRID.setBounds(125,100,398,20); 

    lblPAGE_SNDRUNIXWINTitle.setBounds(10,5,250,20);
    lblSNDRKeyringUNIXWIN.setBounds(20,40,100,20);   
    txtSNDRKeyringUNIXWIN.setBounds(125,40,300,20); 
    lblSNDRCommand.setBounds(20,70,100,20);   
    chcSNDRCommand.setBounds(125,70,300,20); 
    chcSNDRCommand.setBackground(Color.white);
    btnSNDRBrowse.setBounds(440,40,85,20);  

    lblPAGE_SNDRCAPITitle.setBounds(10,5,250,20);
    lblSNDRFips.setBounds(20,40,100,20);   
    cbxSNDRFips.setBounds(125,40,300,20); 
    lblSNDRSigalg.setBounds(20,70,100,20);   
    chcSNDRSigalg.setBounds(125,70,300,20); 
    chcSNDRSigalg.setBackground(Color.white);

    lblPAGE_RCVRTitle.setBounds(10,5,200,20);      
    lblRCVRQmgrName.setBounds(20,40,100,20);     
    txtRCVRQmgrName.setBounds(125,40,398,20);   
    lblRCVRHost.setBounds(20,70,100,20);         
    txtRCVRHost.setBounds(125,70,398,20);       
    lblRCVRPort.setBounds(20,100,100,20);
    txtRCVRPort.setBounds(125,100,398,20);
    lblRCVRPlatform.setBounds(20,130,100,20);   
    chcRCVRPlatform.setBounds(125,130,175,20); 
    chcRCVRPlatform.setBackground(Color.white);
    
    lblPAGE_RCVRZOSTitle.setBounds(10,5,200,20);
    lblRCVRKeyringZOS.setBounds(20,40,100,20);     
    txtRCVRKeyringZOS.setBounds(125,40,398,20);   
    lblRCVRTasks.setBounds(20,70,100,20);       
    txtRCVRTasks.setBounds(125,70,100,20);     
    lblRCVRID.setBounds(20,100,100,20);  
    txtRCVRID.setBounds(125,100,398,20); 
    
    lblPAGE_RCVRUNIXWINTitle.setBounds(10,5,250,20);
    lblRCVRKeyringUNIXWIN.setBounds(20,40,100,20);   
    txtRCVRKeyringUNIXWIN.setBounds(125,40,300,20); 
    lblRCVRCommand.setBounds(20,70,100,20);   
    chcRCVRCommand.setBounds(125,70,300,20); 
    chcRCVRCommand.setBackground(Color.white);
    btnRCVRBrowse.setBounds(440,40,85,20);  

    lblPAGE_RCVRCAPITitle.setBounds(10,5,250,20);
    lblRCVRFips.setBounds(20,40,100,20);   
    cbxRCVRFips.setBounds(125,40,300,20); 
    lblRCVRSigalg.setBounds(20,70,100,20);   
    chcRCVRSigalg.setBounds(125,70,300,20); 
    chcRCVRSigalg.setBackground(Color.white);

    lblPAGE_CHLTitle.setBounds(10,5,200,20);      
    lblChannel.setBounds(20,40,100,20);
    txtChannel.setBounds(125,40,398,20);
    lblQmgrFips.setBounds(20,70,100,20);   
    cbxQmgrFips.setBounds(125,70,300,20); 
    lblCipherSpec.setBounds(20,100,100,20);
    chcCipherSpec.setBounds(125,100,300,20);
    chcCipherSpec.setBackground(Color.white);
    lblClientAuth.setBounds(20,130,100,20);
    radAuReq.setBounds(121,130,250,20);
    radAuOpt.setBounds(121,150,250,20);
    
    lblPAGE_CAMODELTitle.setBounds(10,5,200,20);      
    radTypeSS.setBounds(20,40,300,20);
    radTypeCA.setBounds(20,70,300,20);

    lblPAGE_CAWHERETitle.setBounds(10,5,200,20);      
    radTypeCAX.setBounds(20,40,300,20);
    radTypeCAI.setBounds(20,70,300,20);

    lblPAGE_CEAUTitle.setBounds(10,5,200,20);      
    lblCEAUHost    .setBounds(20,40,100,20);  
    txtCEAUHost    .setBounds(125,40,300,20); 
    lblCEAUPlatform.setBounds(20,70,100,20); 
    chcCEAUPlatform.setBounds(125,70,175,20); 
    chcCEAUPlatform.setBackground(Color.white);

    lblPAGE_CEAUZOSTitle.setBounds(10,5,200,20);
    lblCEAUKeyringZOS.setBounds(20,40,100,20);     
    txtCEAUKeyringZOS.setBounds(125,40,398,20);   
    lblCEAUID.setBounds(20,70,100,20);  
    txtCEAUID.setBounds(125,70,398,20); 

    lblPAGE_CEAUUNIXWINTitle.setBounds(10,5,250,20);
    lblCEAUKeyringUNIXWIN.setBounds(20,40,100,20);   
    txtCEAUKeyringUNIXWIN.setBounds(125,40,300,20); 
    lblCEAUCommand.setBounds(20,70,100,20);   
    chcCEAUCommand.setBounds(125,70,300,20); 
    chcCEAUCommand.setBackground(Color.white);
    btnCEAUBrowse.setBounds(440,40,85,20);  

    lblPAGE_CEAUCAPITitle.setBounds(10,5,250,20);
    lblCEAUFips.setBounds(20,40,100,20);   
    cbxCEAUFips.setBounds(125,40,300,20); 
    lblCEAUSigalg.setBounds(20,70,100,20);   
    chcCEAUSigalg.setBounds(125,70,300,20); 
    chcCEAUSigalg.setBackground(Color.white);

    lblPAGE_CEAUCERTTitle.setBounds(10,5,200,20);      
    lblCEAULabel.setBounds(20,40,100,20);      
    txtCEAULabel.setBounds(125,40,398,20);    
    lblCEAUDNCN.setBounds(20,70,100,20);    
    txtCEAUDNCN.setBounds(125,70,398,20);  
    lblCEAUDNOU.setBounds(20,100,100,20);    
    txtCEAUDNOU.setBounds(125,100,398,20);  
    lblCEAUDNO.setBounds(20,130,100,20);    
    txtCEAUDNO.setBounds(125,130,398,20);  
    lblCEAUDNL.setBounds(20,160,100,20);    
    txtCEAUDNL.setBounds(125,160,398,20);  
    lblCEAUDNST.setBounds(20,190,100,20);    
    txtCEAUDNST.setBounds(125,190,398,20);  
    lblCEAUDNC.setBounds(20,220,100,20);    
    txtCEAUDNC.setBounds(125,220,398,20);  
    lblCEAUExpiry.setBounds(20,250,100,20);    
    txtCEAUExpiry.setBounds(125,250,398,20);  

    lblPAGE_SNDRCERTTitle.setBounds(10,5,200,20);      
    lblSNDRLabel.setBounds(20,40,100,20);     
    txtSNDRLabel.setBounds(125,40,398,20);   
    lblSNDRDNCN.setBounds(20,70,100,20);     
    chcSNDRDNCN.setBounds(125,70,398,20);   
    chcSNDRDNCN.setBackground(Color.white);
    lblSNDRDNOU.setBounds(20,100,100,20);    
    txtSNDRDNOU.setBounds(125,100,398,20);  
    lblSNDRDNO.setBounds(20,130,100,20);    
    txtSNDRDNO.setBounds(125,130,398,20);  
    lblSNDRDNL.setBounds(20,160,100,20);    
    txtSNDRDNL.setBounds(125,160,398,20);  
    lblSNDRDNST.setBounds(20,190,100,20);    
    txtSNDRDNST.setBounds(125,190,398,20);  
    lblSNDRDNC.setBounds(20,220,100,20);    
    txtSNDRDNC.setBounds(125,220,398,20);  
    lblSNDRExpiry.setBounds(20,250,100,20);    
    txtSNDRExpiry.setBounds(125,250,398,20);  
            
    lblPAGE_RCVRCERTTitle.setBounds(10,5,200,20);        
    lblRCVRLabel.setBounds(20,40,100,20);     
    txtRCVRLabel.setBounds(125,40,398,20);   
    lblRCVRDNCN.setBounds(20,70,100,20);    
    chcRCVRDNCN.setBounds(125,70,398,20);  
    chcRCVRDNCN.setBackground(Color.white);
    lblRCVRDNOU.setBounds(20,100,100,20);    
    txtRCVRDNOU.setBounds(125,100,398,20);  
    lblRCVRDNO.setBounds(20,130,100,20);    
    txtRCVRDNO.setBounds(125,130,398,20);  
    lblRCVRDNL.setBounds(20,160,100,20);    
    txtRCVRDNL.setBounds(125,160,398,20);  
    lblRCVRDNST.setBounds(20,190,100,20);    
    txtRCVRDNST.setBounds(125,190,398,20);  
    lblRCVRDNC.setBounds(20,220,100,20);    
    txtRCVRDNC.setBounds(125,220,398,20);      
    lblRCVRExpiry.setBounds(20,250,100,20);    
    txtRCVRExpiry.setBounds(125,250,398,20);  

    lblGenerating.setBounds(70,146,398,20);      

    lblPAGE_SUMMARYTitle.setBounds(140,5,340,35);  
    lblPAGE_SUMMARYTitle.setFont(new Font("Helvetica",Font.ITALIC + Font.BOLD,20));
    spnSummary.setBounds(20,220,515,365);
    instructionsPane.setBounds(20,220,515,365); 

    icon01SNDR1_Q       = new ImageIcon("images\\01SNDR1_Q.GIF           ");
    icon02SNDR1_C       = new ImageIcon("images\\02SNDR1_C.GIF           ");
    icon03SNDR2_Q       = new ImageIcon("images\\03SNDR2_Q.GIF           ");
    icon04SNDR2_C       = new ImageIcon("images\\04SNDR2_C.GIF           ");
    icon05RCVR1_Q       = new ImageIcon("images\\05RCVR1_Q.GIF            ");
    icon06RCVR1_C       = new ImageIcon("images\\06RCVR1_C.GIF            ");
    icon07RCVR2_Q       = new ImageIcon("images\\07RCVR2_Q.GIF            ");
    icon08RCVR2_C       = new ImageIcon("images\\08RCVR2_C.GIF            ");
    icon09CHL_Q         = new ImageIcon("images\\09CHL_Q.GIF             ");
    icon10CHL_C         = new ImageIcon("images\\10CHL_C.GIF             ");
    icon11SNDRCERT_Q    = new ImageIcon("images\\11SNDRCERT_Q.GIF        ");
    icon12SNDRCERT_C    = new ImageIcon("images\\12SNDRCERT_C.GIF        ");
    icon13RCVRCERT_Q_N  = new ImageIcon("images\\13RCVRCERT_Q_N.GIF      ");
    icon14RCVRCERT_C_N  = new ImageIcon("images\\14RCVRCERT_C_N.GIF      ");
    icon15RCVRCERT_Q_Y  = new ImageIcon("images\\15RCVRCERT_Q_Y.GIF      ");
    icon16RCVRCERT_C_Y  = new ImageIcon("images\\16RCVRCERT_C_Y.GIF      ");
    icon17CAMODEL_Q_N   = new ImageIcon("images\\17CAMODEL_Q_N.GIF       ");
    icon18CAMODEL_C_N   = new ImageIcon("images\\18CAMODEL_C_N.GIF       ");
    icon19CAMODEL_Q_Y   = new ImageIcon("images\\19CAMODEL_Q_Y.GIF       ");
    icon20CAMODEL_C_Y   = new ImageIcon("images\\20CAMODEL_C_Y.GIF       ");
    icon21CAWHERE_Q_N_E = new ImageIcon("images\\21CAWHERE_Q_N_E.GIF     ");
    icon22CAWHERE_C_N_E = new ImageIcon("images\\22CAWHERE_C_N_E.GIF     ");
    icon23CAWHERE_Q_Y_E = new ImageIcon("images\\23CAWHERE_Q_Y_E.GIF     ");
    icon24CAWHERE_C_Y_E = new ImageIcon("images\\24CAWHERE_C_Y_E.GIF     ");
    icon25CAWHERE_Q_N_I = new ImageIcon("images\\25CAWHERE_Q_N_I.GIF     ");
    icon26CAWHERE_C_N_I = new ImageIcon("images\\26CAWHERE_C_N_I.GIF     ");
    icon27CAWHERE_Q_Y_I = new ImageIcon("images\\27CAWHERE_Q_Y_I.GIF     ");
    icon28CAWHERE_C_Y_I = new ImageIcon("images\\28CAWHERE_C_Y_I.GIF     ");
    icon29CEAU1_Q       = new ImageIcon("images\\29CEAU1_Q.GIF           ");
    icon30CEAU1_C       = new ImageIcon("images\\30CEAU1_C.GIF           ");
    icon31CEAU2_Q       = new ImageIcon("images\\31CEAU2_Q.GIF           ");
    icon32CEAU2_C       = new ImageIcon("images\\32CEAU2_C.GIF           ");
    icon33CEAUCERT_Q    = new ImageIcon("images\\33CEAUCERT_Q.GIF        ");
    icon34CEAUCERT_C    = new ImageIcon("images\\34CEAUCERT_C.GIF        ");

    lbl01SNDR1_Q        = new JLabel();
    lbl02SNDR1_C        = new JLabel();
    lbl03SNDR2_Qa       = new JLabel();
    lbl04SNDR2_Ca       = new JLabel();
    lbl03SNDR2_Qb       = new JLabel();
    lbl04SNDR2_Cb       = new JLabel();
    lbl03SNDR2_Qc       = new JLabel();
    lbl04SNDR2_Cc       = new JLabel();
    lbl05RCVR1_Q        = new JLabel();
    lbl06RCVR1_C        = new JLabel();
    lbl07RCVR2_Qa       = new JLabel();
    lbl08RCVR2_Ca       = new JLabel();
    lbl07RCVR2_Qb       = new JLabel();
    lbl08RCVR2_Cb       = new JLabel();
    lbl07RCVR2_Qc       = new JLabel();
    lbl08RCVR2_Cc       = new JLabel();
    lbl09CHL_Q          = new JLabel();
    lbl10CHL_C          = new JLabel();
    lbl11SNDRCERT_Q     = new JLabel();
    lbl12SNDRCERT_C     = new JLabel();
    lbl13RCVRCERT_Q_N   = new JLabel();
    lbl14RCVRCERT_C_N   = new JLabel();
    lbl15RCVRCERT_Q_Y   = new JLabel();
    lbl16RCVRCERT_C_Y   = new JLabel();
    lbl17CAMODEL_Q_N    = new JLabel();
    lbl18CAMODEL_C_N    = new JLabel();
    lbl19CAMODEL_Q_Y    = new JLabel();
    lbl20CAMODEL_C_Y    = new JLabel();
    lbl21CAWHERE_Q_N_E  = new JLabel();
    lbl22CAWHERE_C_N_E  = new JLabel();
    lbl23CAWHERE_Q_Y_E  = new JLabel();
    lbl24CAWHERE_C_Y_E  = new JLabel();
    lbl25CAWHERE_Q_N_I  = new JLabel();
    lbl26CAWHERE_C_N_I  = new JLabel();
    lbl27CAWHERE_Q_Y_I  = new JLabel();
    lbl28CAWHERE_C_Y_I  = new JLabel();
    lbl29CEAU1_Q        = new JLabel();
    lbl30CEAU1_C        = new JLabel();
    lbl31CEAU2_Qa        = new JLabel();
    lbl32CEAU2_Ca        = new JLabel();
    lbl31CEAU2_Qb        = new JLabel();
    lbl32CEAU2_Cb        = new JLabel();
    lbl31CEAU2_Qc        = new JLabel();
    lbl32CEAU2_Cc        = new JLabel();
    lbl33CEAUCERT_Q     = new JLabel();
    lbl34CEAUCERT_C     = new JLabel();

    lbl01SNDR1_Q       .setIcon(icon01SNDR1_Q        );
    lbl02SNDR1_C       .setIcon(icon02SNDR1_C        );
    lbl03SNDR2_Qa      .setIcon(icon03SNDR2_Q        );
    lbl04SNDR2_Ca      .setIcon(icon04SNDR2_C        );
    lbl03SNDR2_Qb      .setIcon(icon03SNDR2_Q        );
    lbl04SNDR2_Cb      .setIcon(icon04SNDR2_C        );
    lbl03SNDR2_Qc      .setIcon(icon03SNDR2_Q        );
    lbl04SNDR2_Cc      .setIcon(icon04SNDR2_C        );
    lbl05RCVR1_Q       .setIcon(icon05RCVR1_Q         );
    lbl06RCVR1_C       .setIcon(icon06RCVR1_C         );
    lbl07RCVR2_Qa      .setIcon(icon07RCVR2_Q         );
    lbl08RCVR2_Ca      .setIcon(icon08RCVR2_C         );
    lbl07RCVR2_Qb      .setIcon(icon07RCVR2_Q         );
    lbl08RCVR2_Cb      .setIcon(icon08RCVR2_C         );
    lbl07RCVR2_Qc      .setIcon(icon07RCVR2_Q         );
    lbl08RCVR2_Cc      .setIcon(icon08RCVR2_C         );
    lbl09CHL_Q         .setIcon(icon09CHL_Q          );
    lbl10CHL_C         .setIcon(icon10CHL_C          );
    lbl11SNDRCERT_Q    .setIcon(icon11SNDRCERT_Q     );
    lbl12SNDRCERT_C    .setIcon(icon12SNDRCERT_C     );
    lbl13RCVRCERT_Q_N  .setIcon(icon13RCVRCERT_Q_N   );
    lbl14RCVRCERT_C_N  .setIcon(icon14RCVRCERT_C_N   );
    lbl15RCVRCERT_Q_Y  .setIcon(icon15RCVRCERT_Q_Y   );
    lbl16RCVRCERT_C_Y  .setIcon(icon16RCVRCERT_C_Y   );
    lbl17CAMODEL_Q_N   .setIcon(icon17CAMODEL_Q_N    );
    lbl18CAMODEL_C_N   .setIcon(icon18CAMODEL_C_N    );
    lbl19CAMODEL_Q_Y   .setIcon(icon19CAMODEL_Q_Y    );
    lbl20CAMODEL_C_Y   .setIcon(icon20CAMODEL_C_Y    );
    lbl21CAWHERE_Q_N_E .setIcon(icon21CAWHERE_Q_N_E  );
    lbl22CAWHERE_C_N_E .setIcon(icon22CAWHERE_C_N_E  );
    lbl23CAWHERE_Q_Y_E .setIcon(icon23CAWHERE_Q_Y_E  );
    lbl24CAWHERE_C_Y_E .setIcon(icon24CAWHERE_C_Y_E  );
    lbl25CAWHERE_Q_N_I .setIcon(icon25CAWHERE_Q_N_I  );
    lbl26CAWHERE_C_N_I .setIcon(icon26CAWHERE_C_N_I  );
    lbl27CAWHERE_Q_Y_I .setIcon(icon27CAWHERE_Q_Y_I  );
    lbl28CAWHERE_C_Y_I .setIcon(icon28CAWHERE_C_Y_I  );
    lbl29CEAU1_Q       .setIcon(icon29CEAU1_Q        );
    lbl30CEAU1_C       .setIcon(icon30CEAU1_C        );
    lbl31CEAU2_Qa       .setIcon(icon31CEAU2_Q        );
    lbl32CEAU2_Ca       .setIcon(icon32CEAU2_C        );
    lbl31CEAU2_Qb       .setIcon(icon31CEAU2_Q        );
    lbl32CEAU2_Cb       .setIcon(icon32CEAU2_C        );
    lbl31CEAU2_Qc       .setIcon(icon31CEAU2_Q        );
    lbl32CEAU2_Cc       .setIcon(icon32CEAU2_C        );
    lbl33CEAUCERT_Q    .setIcon(icon33CEAUCERT_Q     );
    lbl34CEAUCERT_C    .setIcon(icon34CEAUCERT_C     );

    lbl01SNDR1_Q      .setBounds(0,0,510,256); 
    lbl02SNDR1_C      .setBounds(0,0,510,256); 
    lbl03SNDR2_Qa     .setBounds(0,0,510,256); 
    lbl04SNDR2_Ca     .setBounds(0,0,510,256); 
    lbl03SNDR2_Qb     .setBounds(0,0,510,256); 
    lbl04SNDR2_Cb     .setBounds(0,0,510,256); 
    lbl03SNDR2_Qc     .setBounds(0,0,510,256); 
    lbl04SNDR2_Cc     .setBounds(0,0,510,256); 
    lbl05RCVR1_Q      .setBounds(0,0,510,256); 
    lbl06RCVR1_C      .setBounds(0,0,510,256); 
    lbl07RCVR2_Qa     .setBounds(0,0,510,256); 
    lbl08RCVR2_Ca     .setBounds(0,0,510,256); 
    lbl07RCVR2_Qb     .setBounds(0,0,510,256); 
    lbl08RCVR2_Cb     .setBounds(0,0,510,256); 
    lbl07RCVR2_Qc     .setBounds(0,0,510,256); 
    lbl08RCVR2_Cc     .setBounds(0,0,510,256); 
    lbl09CHL_Q        .setBounds(0,0,510,256); 
    lbl10CHL_C        .setBounds(0,0,510,256); 
    lbl11SNDRCERT_Q   .setBounds(0,0,510,256); 
    lbl12SNDRCERT_C   .setBounds(0,0,510,256); 
    lbl13RCVRCERT_Q_N .setBounds(0,0,510,256); 
    lbl14RCVRCERT_C_N .setBounds(0,0,510,256); 
    lbl15RCVRCERT_Q_Y .setBounds(0,0,510,256); 
    lbl16RCVRCERT_C_Y .setBounds(0,0,510,256); 
    lbl17CAMODEL_Q_N  .setBounds(0,0,510,256); 
    lbl18CAMODEL_C_N  .setBounds(0,0,510,256); 
    lbl19CAMODEL_Q_Y  .setBounds(0,0,510,256); 
    lbl20CAMODEL_C_Y  .setBounds(0,0,510,256); 
    lbl21CAWHERE_Q_N_E.setBounds(0,0,510,256); 
    lbl22CAWHERE_C_N_E.setBounds(0,0,510,256); 
    lbl23CAWHERE_Q_Y_E.setBounds(0,0,510,256); 
    lbl24CAWHERE_C_Y_E.setBounds(0,0,510,256); 
    lbl25CAWHERE_Q_N_I.setBounds(0,0,510,256); 
    lbl26CAWHERE_C_N_I.setBounds(0,0,510,256); 
    lbl27CAWHERE_Q_Y_I.setBounds(0,0,510,256); 
    lbl28CAWHERE_C_Y_I.setBounds(0,0,510,256); 
    lbl29CEAU1_Q      .setBounds(0,0,510,256); 
    lbl30CEAU1_C      .setBounds(0,0,510,256); 
    lbl31CEAU2_Qa      .setBounds(0,0,510,256); 
    lbl32CEAU2_Ca      .setBounds(0,0,510,256); 
    lbl31CEAU2_Qb      .setBounds(0,0,510,256); 
    lbl32CEAU2_Cb      .setBounds(0,0,510,256); 
    lbl31CEAU2_Qc      .setBounds(0,0,510,256); 
    lbl32CEAU2_Cc      .setBounds(0,0,510,256); 
    lbl33CEAUCERT_Q   .setBounds(0,0,510,256); 
    lbl34CEAUCERT_C   .setBounds(0,0,510,256); 

    lbl01SNDR1_Q      .setBorder(new LineBorder(Color.black, 1));
    lbl02SNDR1_C      .setBorder(new LineBorder(Color.black, 1));
    lbl03SNDR2_Qa     .setBorder(new LineBorder(Color.black, 1));
    lbl04SNDR2_Ca     .setBorder(new LineBorder(Color.black, 1));
    lbl03SNDR2_Qb     .setBorder(new LineBorder(Color.black, 1));
    lbl04SNDR2_Cb     .setBorder(new LineBorder(Color.black, 1));
    lbl03SNDR2_Qc     .setBorder(new LineBorder(Color.black, 1));
    lbl04SNDR2_Cc     .setBorder(new LineBorder(Color.black, 1));
    lbl05RCVR1_Q      .setBorder(new LineBorder(Color.black, 1));
    lbl06RCVR1_C      .setBorder(new LineBorder(Color.black, 1));
    lbl07RCVR2_Qa     .setBorder(new LineBorder(Color.black, 1));
    lbl08RCVR2_Ca     .setBorder(new LineBorder(Color.black, 1));
    lbl07RCVR2_Qb     .setBorder(new LineBorder(Color.black, 1));
    lbl08RCVR2_Cb     .setBorder(new LineBorder(Color.black, 1));
    lbl07RCVR2_Qc     .setBorder(new LineBorder(Color.black, 1));
    lbl08RCVR2_Cc     .setBorder(new LineBorder(Color.black, 1));
    lbl09CHL_Q        .setBorder(new LineBorder(Color.black, 1));
    lbl10CHL_C        .setBorder(new LineBorder(Color.black, 1));
    lbl11SNDRCERT_Q   .setBorder(new LineBorder(Color.black, 1));
    lbl12SNDRCERT_C   .setBorder(new LineBorder(Color.black, 1));
    lbl13RCVRCERT_Q_N .setBorder(new LineBorder(Color.black, 1));
    lbl14RCVRCERT_C_N .setBorder(new LineBorder(Color.black, 1));
    lbl15RCVRCERT_Q_Y .setBorder(new LineBorder(Color.black, 1));
    lbl16RCVRCERT_C_Y .setBorder(new LineBorder(Color.black, 1));
    lbl17CAMODEL_Q_N  .setBorder(new LineBorder(Color.black, 1));
    lbl18CAMODEL_C_N  .setBorder(new LineBorder(Color.black, 1));
    lbl19CAMODEL_Q_Y  .setBorder(new LineBorder(Color.black, 1));
    lbl20CAMODEL_C_Y  .setBorder(new LineBorder(Color.black, 1));
    lbl21CAWHERE_Q_N_E.setBorder(new LineBorder(Color.black, 1));
    lbl22CAWHERE_C_N_E.setBorder(new LineBorder(Color.black, 1));
    lbl23CAWHERE_Q_Y_E.setBorder(new LineBorder(Color.black, 1));
    lbl24CAWHERE_C_Y_E.setBorder(new LineBorder(Color.black, 1));
    lbl25CAWHERE_Q_N_I.setBorder(new LineBorder(Color.black, 1));
    lbl26CAWHERE_C_N_I.setBorder(new LineBorder(Color.black, 1));
    lbl27CAWHERE_Q_Y_I.setBorder(new LineBorder(Color.black, 1));
    lbl28CAWHERE_C_Y_I.setBorder(new LineBorder(Color.black, 1));
    lbl29CEAU1_Q      .setBorder(new LineBorder(Color.black, 1));
    lbl30CEAU1_C      .setBorder(new LineBorder(Color.black, 1));
    lbl31CEAU2_Qa      .setBorder(new LineBorder(Color.black, 1));
    lbl32CEAU2_Ca      .setBorder(new LineBorder(Color.black, 1));
    lbl31CEAU2_Qb      .setBorder(new LineBorder(Color.black, 1));
    lbl32CEAU2_Cb      .setBorder(new LineBorder(Color.black, 1));
    lbl31CEAU2_Qc      .setBorder(new LineBorder(Color.black, 1));
    lbl32CEAU2_Cc      .setBorder(new LineBorder(Color.black, 1));
    lbl33CEAUCERT_Q   .setBorder(new LineBorder(Color.black, 1));  
    lbl34CEAUCERT_C   .setBorder(new LineBorder(Color.black, 1));  



    pnlNAV.add(btnPrev);      
    pnlNAV.add(btnNext);      
    pnlTXT.add(txtMsg);    
                 
    pnl1SNDR.add(lblPAGE_SNDRTitle);
    pnl1SNDR.add(radCSClient); 
    pnl1SNDR.add(radCSServer); 
    pnl1SNDR.add(radJOJava); 
    pnl1SNDR.add(radJOOther); 
    pnl1SNDR.add(txtSNDRUser); 
    pnl1SNDR.add(txtSNDRQmgrName); 
    pnl1SNDR.add(lblSNDRHost);     
    pnl1SNDR.add(txtSNDRHost);     
    pnl1SNDR.add(lblSNDRPlatform); 
    pnl1SNDR.add(chcSNDRPlatform); 
    pnl2SNDR.add(lbl01SNDR1_Q);
    pnl2SNDR.add(lbl02SNDR1_C);

    pnl1SNDRZOS.add(lblPAGE_SNDRZOSTitle);
    pnl1SNDRZOS.add(lblSNDRKeyringZOS);  
    pnl1SNDRZOS.add(txtSNDRKeyringZOS);  
    pnl1SNDRZOS.add(lblSNDRTasks);    
    pnl1SNDRZOS.add(txtSNDRTasks); 
    pnl1SNDRZOS.add(lblSNDRID);
    pnl1SNDRZOS.add(txtSNDRID);
    pnl2SNDRZOS.add(lbl03SNDR2_Qa);
    pnl2SNDRZOS.add(lbl04SNDR2_Ca);
    
    pnl1SNDRUNIXWIN.add(lblPAGE_SNDRUNIXWINTitle);
    pnl1SNDRUNIXWIN.add(lblSNDRKeyringUNIXWIN);    
    pnl1SNDRUNIXWIN.add(txtSNDRKeyringUNIXWIN); 
    pnl1SNDRUNIXWIN.add(lblSNDRCommand);    
    pnl1SNDRUNIXWIN.add(chcSNDRCommand); 
    pnl1SNDRUNIXWIN.add(btnSNDRBrowse);
    pnl2SNDRUNIXWIN.add(lbl03SNDR2_Qb);
    pnl2SNDRUNIXWIN.add(lbl04SNDR2_Cb);
    
    pnl1SNDRCAPI.add(lblPAGE_SNDRCAPITitle);
    pnl1SNDRCAPI.add(lblSNDRFips);    
    pnl1SNDRCAPI.add(cbxSNDRFips); 
    pnl1SNDRCAPI.add(lblSNDRSigalg);    
    pnl1SNDRCAPI.add(chcSNDRSigalg); 
    pnl2SNDRCAPI.add(lbl03SNDR2_Qc);
    pnl2SNDRCAPI.add(lbl04SNDR2_Cc);

    pnl1RCVR.add(lblPAGE_RCVRTitle);
    pnl1RCVR.add(lblRCVRQmgrName); 
    pnl1RCVR.add(txtRCVRQmgrName); 
    pnl1RCVR.add(lblRCVRHost);     
    pnl1RCVR.add(txtRCVRHost);     
    pnl1RCVR.add(lblRCVRPort);     
    pnl1RCVR.add(txtRCVRPort);     
    pnl1RCVR.add(lblRCVRPlatform); 
    pnl1RCVR.add(chcRCVRPlatform); 
    pnl2RCVR.add(lbl05RCVR1_Q); 
    pnl2RCVR.add(lbl06RCVR1_C); 
    
    pnl1RCVRZOS.add(lblPAGE_RCVRZOSTitle);
    pnl1RCVRZOS.add(lblRCVRKeyringZOS);  
    pnl1RCVRZOS.add(txtRCVRKeyringZOS);  
    pnl1RCVRZOS.add(lblRCVRTasks);    
    pnl1RCVRZOS.add(txtRCVRTasks);    
    pnl1RCVRZOS.add(lblRCVRID);
    pnl1RCVRZOS.add(txtRCVRID);
    pnl2RCVRZOS.add(lbl07RCVR2_Qa); 
    pnl2RCVRZOS.add(lbl08RCVR2_Ca); 
    
    pnl1RCVRUNIXWIN.add(lblPAGE_RCVRUNIXWINTitle);
    pnl1RCVRUNIXWIN.add(lblRCVRKeyringUNIXWIN);    
    pnl1RCVRUNIXWIN.add(txtRCVRKeyringUNIXWIN); 
    pnl1RCVRUNIXWIN.add(lblRCVRCommand);    
    pnl1RCVRUNIXWIN.add(chcRCVRCommand); 
    pnl1RCVRUNIXWIN.add(btnRCVRBrowse);
    pnl2RCVRUNIXWIN.add(lbl07RCVR2_Qb); 
    pnl2RCVRUNIXWIN.add(lbl08RCVR2_Cb); 
    
    pnl1RCVRCAPI.add(lblPAGE_RCVRCAPITitle);
    pnl1RCVRCAPI.add(lblRCVRFips);    
    pnl1RCVRCAPI.add(cbxRCVRFips); 
    pnl1RCVRCAPI.add(lblRCVRSigalg);    
    pnl1RCVRCAPI.add(chcRCVRSigalg); 
    pnl2RCVRCAPI.add(lbl07RCVR2_Qc); 
    pnl2RCVRCAPI.add(lbl08RCVR2_Cc); 

    pnl1CHL.add(lblPAGE_CHLTitle);
    pnl1CHL.add(lblChannel);   
    pnl1CHL.add(txtChannel);   
    pnl1CHL.add(lblQmgrFips);    
    pnl1CHL.add(cbxQmgrFips); 
    pnl1CHL.add(lblClientAuth);
    pnl1CHL.add(radAuReq);
    pnl1CHL.add(radAuOpt);
    pnl1CHL.add(lblCipherSpec);
    pnl1CHL.add(chcCipherSpec);
    pnl2CHL.add(lbl09CHL_Q);
    pnl2CHL.add(lbl10CHL_C);
    
    pnl1SNDRCERT.add(lblPAGE_SNDRCERTTitle);
    pnl1SNDRCERT.add(lblSNDRLabel);
    pnl1SNDRCERT.add(txtSNDRLabel);
    pnl1SNDRCERT.add(lblSNDRDNCN);
    pnl1SNDRCERT.add(chcSNDRDNCN);
    pnl1SNDRCERT.add(lblSNDRDNOU);
    pnl1SNDRCERT.add(txtSNDRDNOU);
    pnl1SNDRCERT.add(lblSNDRDNO );
    pnl1SNDRCERT.add(txtSNDRDNO );
    pnl1SNDRCERT.add(lblSNDRDNL );
    pnl1SNDRCERT.add(txtSNDRDNL );
    pnl1SNDRCERT.add(lblSNDRDNST );
    pnl1SNDRCERT.add(txtSNDRDNST );
    pnl1SNDRCERT.add(lblSNDRDNC );
    pnl1SNDRCERT.add(txtSNDRDNC );
    pnl1SNDRCERT.add(lblSNDRExpiry );
    pnl1SNDRCERT.add(txtSNDRExpiry );
    pnl2SNDRCERT.add(lbl11SNDRCERT_Q );
    pnl2SNDRCERT.add(lbl12SNDRCERT_C );
    
    pnl1RCVRCERT.add(lblPAGE_RCVRCERTTitle);
    pnl1RCVRCERT.add(lblRCVRLabel);
    pnl1RCVRCERT.add(txtRCVRLabel);
    pnl1RCVRCERT.add(lblRCVRDNCN);
    pnl1RCVRCERT.add(chcRCVRDNCN);
    pnl1RCVRCERT.add(lblRCVRDNOU);
    pnl1RCVRCERT.add(txtRCVRDNOU);
    pnl1RCVRCERT.add(lblRCVRDNO );
    pnl1RCVRCERT.add(txtRCVRDNO );
    pnl1RCVRCERT.add(lblRCVRDNL );
    pnl1RCVRCERT.add(txtRCVRDNL );
    pnl1RCVRCERT.add(lblRCVRDNST );
    pnl1RCVRCERT.add(txtRCVRDNST );
    pnl1RCVRCERT.add(lblRCVRDNC );
    pnl1RCVRCERT.add(txtRCVRDNC );
    pnl1RCVRCERT.add(lblRCVRExpiry );
    pnl1RCVRCERT.add(txtRCVRExpiry );
    pnl2RCVRCERT.add(lbl13RCVRCERT_Q_N );
    pnl2RCVRCERT.add(lbl14RCVRCERT_C_N );
    pnl2RCVRCERT.add(lbl15RCVRCERT_Q_Y );
    pnl2RCVRCERT.add(lbl16RCVRCERT_C_Y );

    pnl1CAMODEL.add(lblPAGE_CAMODELTitle);
    pnl1CAMODEL.add(radTypeSS);   
    pnl1CAMODEL.add(radTypeCA);   
    pnl2CAMODEL.add(lbl17CAMODEL_Q_N );   
    pnl2CAMODEL.add(lbl18CAMODEL_C_N );   
    pnl2CAMODEL.add(lbl19CAMODEL_Q_Y );   
    pnl2CAMODEL.add(lbl20CAMODEL_C_Y );   

    pnl1CAWHERE.add(lblPAGE_CAWHERETitle);
    pnl1CAWHERE.add(radTypeCAI);   
    pnl1CAWHERE.add(radTypeCAX);   
    pnl2CAWHERE.add(lbl21CAWHERE_Q_N_E);   
    pnl2CAWHERE.add(lbl22CAWHERE_C_N_E);   
    pnl2CAWHERE.add(lbl23CAWHERE_Q_Y_E);   
    pnl2CAWHERE.add(lbl24CAWHERE_C_Y_E);   
    pnl2CAWHERE.add(lbl25CAWHERE_Q_N_I);   
    pnl2CAWHERE.add(lbl26CAWHERE_C_N_I);   
    pnl2CAWHERE.add(lbl27CAWHERE_Q_Y_I);   
    pnl2CAWHERE.add(lbl28CAWHERE_C_Y_I);   

    pnl1CEAU.add(lblPAGE_CEAUTitle);
    pnl1CEAU.add(lblCEAUHost ); 
    pnl1CEAU.add(txtCEAUHost ); 
    pnl1CEAU.add(lblCEAUPlatform); 
    pnl1CEAU.add(chcCEAUPlatform); 
    pnl2CEAU.add(lbl29CEAU1_Q ); 
    pnl2CEAU.add(lbl30CEAU1_C ); 

    pnl1CEAUZOS.add(lblPAGE_CEAUZOSTitle);
    pnl1CEAUZOS.add(lblCEAUKeyringZOS);  
    pnl1CEAUZOS.add(txtCEAUKeyringZOS);  
    pnl1CEAUZOS.add(lblCEAUID);
    pnl1CEAUZOS.add(txtCEAUID);
    pnl2CEAUZOS.add(lbl31CEAU2_Qa);
    pnl2CEAUZOS.add(lbl32CEAU2_Ca);

    pnl1CEAUUNIXWIN.add(lblPAGE_CEAUUNIXWINTitle);
    pnl1CEAUUNIXWIN.add(lblCEAUKeyringUNIXWIN);    
    pnl1CEAUUNIXWIN.add(txtCEAUKeyringUNIXWIN); 
    pnl1CEAUUNIXWIN.add(lblCEAUCommand);    
    pnl1CEAUUNIXWIN.add(chcCEAUCommand); 
    pnl1CEAUUNIXWIN.add(btnCEAUBrowse);
    pnl2CEAUUNIXWIN.add(lbl31CEAU2_Qb);
    pnl2CEAUUNIXWIN.add(lbl32CEAU2_Cb);

    pnl1CEAUCAPI.add(lblPAGE_CEAUCAPITitle);
    pnl1CEAUCAPI.add(lblCEAUFips);    
    pnl1CEAUCAPI.add(cbxCEAUFips); 
    pnl1CEAUCAPI.add(lblCEAUSigalg);    
    pnl1CEAUCAPI.add(chcCEAUSigalg); 
    pnl2CEAUCAPI.add(lbl31CEAU2_Qc);
    pnl2CEAUCAPI.add(lbl32CEAU2_Cc);

    pnl1CEAUCERT.add(lblPAGE_CEAUCERTTitle);
    pnl1CEAUCERT.add(lblCEAULabel);   
    pnl1CEAUCERT.add(txtCEAULabel);   
    pnl1CEAUCERT.add(lblCEAUDNCN);
    pnl1CEAUCERT.add(txtCEAUDNCN);
    pnl1CEAUCERT.add(lblCEAUDNOU);
    pnl1CEAUCERT.add(txtCEAUDNOU);
    pnl1CEAUCERT.add(lblCEAUDNO );
    pnl1CEAUCERT.add(txtCEAUDNO );
    pnl1CEAUCERT.add(lblCEAUDNL );
    pnl1CEAUCERT.add(txtCEAUDNL );
    pnl1CEAUCERT.add(lblCEAUDNST );
    pnl1CEAUCERT.add(txtCEAUDNST );
    pnl1CEAUCERT.add(lblCEAUDNC );
    pnl1CEAUCERT.add(txtCEAUDNC );
    pnl1CEAUCERT.add(lblCEAUExpiry );
    pnl1CEAUCERT.add(txtCEAUExpiry );
    pnl2CEAUCERT.add(lbl33CEAUCERT_Q );
    pnl2CEAUCERT.add(lbl34CEAUCERT_C );

    pnlGENERATE.add(lblGenerating);

    pnlSUMMARY.add(lblPAGE_SUMMARYTitle);
    pnlSUMMARY.add(btnSave);
    pnlSUMMARY.add(spnSummary);  

    msg("Welcome to the IBM MQ TLS/SSL Wizard ");
    this.addComponentListener(this);

    currentPage = PAGE_SNDR;
    setPage(currentPage);	

  }
} 

      


/******************************************************************/
/* Class: WMQSSLWizardFilter                                      */
/* Description:                                                   */
/* Parms:                                                         */
/* Returns:                                                       */
/******************************************************************/
class WMQSSLWizardFilter extends FileFilter {
    public final static int FT_HTML = 0;
    public final static int FT_KDB = 1;

    private int filterType;

    WMQSSLWizardFilter(int type ) 
    {
      filterType = type;
    }

    //Accept all directories and html/htm files.
    public boolean accept(File f)
    {
      if (f.isDirectory()) 
      {
        return true;
      }

      String extension = getExtension(f);
      if (extension != null) 
      {
        if (filterType == FT_HTML) 
        {
          if (extension.equals("html") ||
              extension.equals("htm")) 
          {
            return true;
          } 
          else 
          {
            return false;
          }
        }
        else if (filterType == FT_KDB) 
        {
          if (extension.equals("kdb")) 
          {
            return true;
          } 
          else 
          {
            return false;
          }
        }
      }

      return false;
    }

    public static String getExtension(File f) 
    {
      String ext = null;
      String s = f.getName();
      int i = s.lastIndexOf('.');

      if (i > 0 &&  i < s.length() - 1) 
      {
        ext = s.substring(i+1).toLowerCase();
      }
      return ext;
    }

    //The description of this filter
    public String getDescription() {
      if (filterType == FT_HTML) 
      {
        return "HTML files";
      }
      else if (filterType == FT_KDB) 
      {
        return "Key database files";
      }
      return " ";
    }
}

