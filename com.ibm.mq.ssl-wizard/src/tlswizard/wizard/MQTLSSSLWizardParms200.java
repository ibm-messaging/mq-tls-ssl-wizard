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

import java.io.Serializable;
import java.util.Calendar;
import java.util.StringTokenizer;


public class MQTLSSSLWizardParms200 implements Serializable {

  private String instructions;
  private String CAPlatform;
  private MQTLSSSLWizardGlobal GVars;

  /****************************************************************/
  /* Constructor                                                  */
  /****************************************************************/
  /*public WMQSSLWizardParms (int v) {
    init();
  }

  private init() {
    GVars = new MQTLSSSLWizardGlobal();
  }*/

  /****************************************************************/
  /* Field data                                                   */
  /****************************************************************/
  // Page SNDR
  //private JRadioButton radCSClient = new JRadioButton("Client User");
  //private JRadioButton radCSServer = new JRadioButton("QMGR Name");
  public boolean pageSNDRradCS;
  //private JRadioButton radJOJava = new JRadioButton("Java/JMS");
  //private JRadioButton radJOOther = new JRadioButton("Other");
  public boolean pageSNDRradJO;
  //private JTextField txtSNDRUser = new JTextField("",48);
  public String pageSNDRtxtSNDRUser;
  //private JTextField txtSNDRQmgrName = new JTextField("",48);
  public String pageSNDRtxtSNDRQmgrName;
  //private JTextField txtSNDRHost = new JTextField("",48);
  public String pageSNDRtxtSNDRHost;
  //private JComboBox  chcSNDRPlatform = new JComboBox();
  public String pageSNDRchcSNDRPlatform;

  // Page SNDRZOS
  //private JTextField txtSNDRTasks = new JTextField("", 5);
  public String pageSNDRZOStxtSNDRTasks;
  //private JTextField txtSNDRID = new JTextField("", 48);
  public String pageSNDRZOStxtSNDRID;
  //private JTextField txtSNDRKeyringZOS = new JTextField("",48);
  public String pageSNDRZOStxtSNDRKeyringZOS;

  // Page SNDRUNIXWIN
  //private JTextField txtSNDRKeyringUNIXWIN = new JTextField("",100);
  public String pageSNDRUNIXWINtxtSNDRKeyringUNIXWIN;
  //private JComboBox  chcSNDRCommand = new JComboBox();
  public String pageSNDRUNIXWINchcSNDRCommand;

  // Page SNDRCAPI
  //private JCheckBox  cbxSNDRFips = new JCheckBox();
  public boolean pageSNDRCAPIcbxSNDRFips;
  //private JComboBox  chcRCVRSigalg = new JComboBox();
  public String pageSNDRCAPIchcSNDRSigalg;

  // Page RCVR 
  //private JTextField txtRCVRQmgrName = new JTextField("",48);
  public String pageRCVRtxtRCVRQmgrName;
  //private JTextField txtRCVRHost = new JTextField("",48);
  public String pageRCVRtxtRCVRHost;
  //private JTextField txtRCVRPort = new JTextField("",48);
  public String pageRCVRtxtRCVRPort;
  //private JComboBox  chcRCVRPlatform = new JComboBox();
  public String pageRCVRchcRCVRPlatform;

  // Page RCVRZOS
  //private JTextField txtRCVRTasks = new JTextField("",5);
  public String pageRCVRZOStxtRCVRTasks;
  //private JTextField txtRCVRID = new JTextField("", 48);
  public String pageRCVRZOStxtRCVRID;
  //private JTextField txtRCVRKeyringZOS = new JTextField("",48);
  public String pageRCVRZOStxtRCVRKeyringZOS;

  // Page RCVRUNIXWIN
  //private JTextField txtRCVRKeyringUNIXWIN = new JTextField("",100);
  public String pageRCVRUNIXWINtxtRCVRKeyringUNIXWIN;
  //private JComboBox  chcRCVRCommand = new JComboBox();
  public String pageRCVRUNIXWINchcRCVRCommand;

  // Page RCVRCAPI
  //private JCheckBox  cbxRCVRFips = new JCheckBox();
  public boolean pageRCVRCAPIcbxRCVRFips;
  //private JComboBox  chcRCVRSigalg = new JComboBox();
  public String pageRCVRCAPIchcRCVRSigalg;

  // Page CHL
  //private JTextField txtChannel = new JTextField("",48);
  public String pageCHLtxtChannel;
  //private JCheckBox  cbxQmgrFips = new JCheckBox();
  public boolean pageCHLcbxQmgrFips;
  //private JComboBox  chcCipherSpec =  new JComboBox();
  public String pageCHLchcCipherSpec;
  //private bla  radAuReq =  new bla();
  public boolean pageCHLradAuReq;

  // Page SNDRCERT
  //private JTextField txtSNDRLabel = new JTextField("",48);
  public String pageSNDRCERTtxtSNDRLabel;
  //private JComboBox  chcSNDRDNCN =  new JComboBox();
  public String pageSNDRCERTchcSNDRDNCN;
  //private JTextField txtSNDRDNOU = new JTextField("",48);
  public String pageSNDRCERTtxtSNDRDNOU;
  //private JTextField txtSNDRDNO = new JTextField("",48);
  public String pageSNDRCERTtxtSNDRDNO;
  //private JTextField txtSNDRDNL = new JTextField("",48);
  public String pageSNDRCERTtxtSNDRDNL;
  //private JTextField txtSNDRDNST = new JTextField("",48);
  public String pageSNDRCERTtxtSNDRDNST;
  //private JTextField txtSNDRDNC = new JTextField("",48);
  public String pageSNDRCERTtxtSNDRDNC;
  //private JTextField txtSNDRExpiry = new JTextField("",48);
  public String pageSNDRCERTtxtSNDRExpiry;

  // Page RCVRCERT
  //private JTextField txtRCVRLabel = new JTextField("",48);
  public String pageRCVRCERTtxtRCVRLabel;
  //private JComboBox  chcRCVRDNCN =  new JComboBox();
  public String pageRCVRCERTchcRCVRDNCN;
  //private JTextField txtRCVRDNOU = new JTextField("",48);  
  public String pageRCVRCERTtxtRCVRDNOU;
  //private JTextField txtRCVRDNO = new JTextField("",48);  
  public String pageRCVRCERTtxtRCVRDNO;
  //private JTextField txtRCVRDNL = new JTextField("",48);              
  public String pageRCVRCERTtxtRCVRDNL;
  //private JTextField txtRCVRDNST = new JTextField("",48);              
  public String pageRCVRCERTtxtRCVRDNST;
  //private JTextField txtRCVRDNC = new JTextField("",48);                  
  public String pageRCVRCERTtxtRCVRDNC;
  //private JTextField txtRCVRExpiry = new JTextField("",48);
  public String pageRCVRCERTtxtRCVRExpiry;
    
  // Page CAMODEL
  //private JRadioButton radTypeSS = new JRadioButton("Create self signed certificates");
  //private JRadioButton radTypeCA = new JRadioButton("Create CA signed certificates");
  public boolean pageCEAUMODELradType;

  // Page CAWHERE
  //private JRadioButton radTypeCAX = new JRadioButton("Send certificate requests to a CA to be signed");
  //private JRadioButton radTypeCAI = new JRadioButton("Create a CA (on z/OS) and use it to sign certificates");
  public boolean pageCEAUWHEREradType;

  // Page CA
  //private JTextField txtCEAUHost = new JTextField("",48);
  public String pageCEAUtxtCEAUHost;
  //private JComboBox  chcCEAUPlatform = new JComboBox();
  public String pageCEAUchcCEAUPlatform;
  
  // Page CAZOS
  //private JTextField txtCEAUID = new JTextField("", 48);
  public String pageCEAUZOStxtCEAUID;
  //private JTextField txtCEAUKeyringZOS = new JTextField("",48);
  public String pageCEAUZOStxtCEAUKeyringZOS;

  // Page CAUNIXWIN
  //private JTextField txtCEAUKeyringUNIXWIN = new JTextField("",100);
  public String pageCEAUUNIXWINtxtCEAUKeyringUNIXWIN;
  //private JComboBox  chcCEAUCommand = new JComboBox();
  public String pageCEAUUNIXWINchcCEAUCommand;

  // Page CAEUCAPI
  //private JCheckBox  cbxCEAUFips = new JCheckBox();
  public boolean pageCEAUCAPIcbxCEAUFips;
  //private JComboBox  chcCEAUSigalg = new JComboBox();
  public String pageCEAUCAPIchcCEAUSigalg;

  // Page CACERT
  //private JTextField txtCEAULabel = new JTextField("",48);
  public String pageCEAUCERTtxtCEAULabel;
  //private JTextField txtCEAUDNCN =  new JTextField("",48);
  public String pageCEAUCERTtxtCEAUDNCN;
  //private JTextField txtCEAUDNOU = new JTextField("",48);
  public String pageCEAUCERTtxtCEAUDNOU;
  //private JTextField txtCEAUDNO = new JTextField("",48);
  public String pageCEAUCERTtxtCEAUDNO;
  //private JTextField txtCEAUDNL = new JTextField("",48);
  public String pageCEAUCERTtxtCEAUDNL;
  //private JTextField txtCEAUDNST = new JTextField("",48);
  public String pageCEAUCERTtxtCEAUDNST;
  //private JTextField txtCEAUDNC = new JTextField("",48);
  public String pageCEAUCERTtxtCEAUDNC;
  //private JTextField txtCEAUExpiry = new JTextField("",48);
  public String pageCEAUCERTtxtCEAUExpiry;
  public boolean pageCHLcbxClientAuth;

  /****************************************************************/
  /* Other data                                                   */
  /****************************************************************/

  /****************************************************************/
  /* Function: generate                                           */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  public String generateIntructions() {
    instructions = "";

    /************************************************************************/
    /* If required, create internal CA certificate                          */
    /************************************************************************/
    if ((pageCEAUMODELradType == false) && (pageCEAUWHEREradType == true)) {
      genCreateCA();     
    }

    /************************************************************************/
    /* Create SSL client + server keyrings/keydatabases                     */
    /************************************************************************/
    genCreateKeyrings(); 

    /************************************************************************/
    /* If not self-signed, populate keydatabases with public CA certs       */
    /************************************************************************/
    if (pageCEAUMODELradType == false) genGetPublicCA();

    /************************************************************************/
    /* Create SSL client + server certificates (includes signing)           */
    /************************************************************************/
    genCreateCerts();
    
    /************************************************************************/
    /* If required, populate keydatabases with public self-signed certs     */
    /************************************************************************/
    if (pageCEAUMODELradType == true) genGetPublicSelfSigned();

    /************************************************************************/
    /* WebSphere MQ admin                                                   */
    /************************************************************************/
    genWmqAdmin();

    /************************************************************************/
    /* Final steps                                                          */
    /************************************************************************/
    genFinalWmqSteps();

    if (pageSNDRradCS == true) genClientSamples(); 


    return instructions;
  }

  /****************************************************************/
  /* Function: genCreateCA                                        */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void genCreateCA() {
    instructionsTitleAdd("SSL CA certificate setup on " + pageCEAUtxtCEAUHost); 
    if (pageCEAUchcCEAUPlatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
      genCreateCAZOS();
    }
    else {
      genCreateCAUNIXWIN();
    }
  }

  /****************************************************************/
  /* Function: genCreateCAUNIXWIN                                    */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void genCreateCAUNIXWIN() {
    String ins = new String();

    instructionsAdd(pageCEAUUNIXWINchcCEAUCommand + " -keydb -create -db \"" + pageCEAUUNIXWINtxtCEAUKeyringUNIXWIN + "\" -pw capass -type cms -expire " + pageCEAUCERTtxtCEAUExpiry + " -stash");

    // Create cert and CA
    ins = pageCEAUUNIXWINchcCEAUCommand;
    ins = ins.concat(" -cert -create -db \"");
    ins = ins.concat(pageCEAUUNIXWINtxtCEAUKeyringUNIXWIN);
    ins = ins.concat("\" -pw capass -label ");
    ins = ins.concat(pageCEAUCERTtxtCEAULabel);
    ins = ins.concat(" -dn \"");
    ins = ins.concat(getDN(pageCEAUCERTtxtCEAUDNCN,
                           pageCEAUCERTtxtCEAUDNOU,
                           pageCEAUCERTtxtCEAUDNO ,
                           pageCEAUCERTtxtCEAUDNL ,
                           pageCEAUCERTtxtCEAUDNST,
                           pageCEAUCERTtxtCEAUDNC)); 
    ins = ins.concat("\" -expire " + pageCEAUCERTtxtCEAUExpiry);
    if (pageCEAUUNIXWINchcCEAUCommand.equals(MQTLSSSLWizardGlobal.COMMAND_CAPI)) {
      ins = ins + doCapi(pageCEAUCAPIcbxCEAUFips, pageCEAUCAPIchcCEAUSigalg);
    }
    instructionsAdd(ins);  
    instructionsAdd(pageCEAUUNIXWINchcCEAUCommand + " -cert -list -db \"" + pageCEAUUNIXWINtxtCEAUKeyringUNIXWIN + "\" -pw capass");
  }

  /****************************************************************/
  /* Function: genCreateCAZOS                                     */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void genCreateCAZOS() {
    String notafter = new String();
    int iexp = Integer.parseInt(pageCEAUCERTtxtCEAUExpiry);

    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DATE, iexp);
    notafter = cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.DAY_OF_MONTH);

    // Create Keyring
    instructionsAddCmp("RACDCERT ID(" + pageCEAUZOStxtCEAUID + ") +");
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;ADDRING(" + pageCEAUZOStxtCEAUKeyringZOS + ")"); 
    instructionsAddBr();

    // Create cert and CA
    instructionsAddCmp("RACDCERT CERTAUTH GENCERT +");
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;SUBJECTSDN(CN('" + pageCEAUCERTtxtCEAUDNCN + "') +");  
    if (!pageCEAUCERTtxtCEAUDNOU.equals("")) {
      instructionsAddCmp("&nbsp;&nbsp;&nbsp;OU('" +pageCEAUCERTtxtCEAUDNOU + "') +");                                               
    }

    instructionsAddCmp("&nbsp;&nbsp;&nbsp;O('" + pageCEAUCERTtxtCEAUDNO + "') +");                                               
    if (!pageCEAUCERTtxtCEAUDNL.equals("")) {
      instructionsAddCmp("&nbsp;&nbsp;&nbsp;L('" + pageCEAUCERTtxtCEAUDNL + "') +");                                           
    }
    if (!pageCEAUCERTtxtCEAUDNST.equals("")) {
      instructionsAddCmp("&nbsp;&nbsp;&nbsp;SP('" + pageCEAUCERTtxtCEAUDNST + "') +");                                           
    }
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;C('" + pageCEAUCERTtxtCEAUDNC + "')) +");                                              
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;NOTAFTER('" + notafter + "')");   
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;WITHLABEL('" + pageCEAUCERTtxtCEAULabel + "')");    
    instructionsAddBr();

    instructionsAddCmp("RACDCERT CERTAUTH +");
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;LIST(LABEL('" + pageCEAUCERTtxtCEAULabel + "'))");       
    instructionsAddBr();

    instructionsAddCmp("RACDCERT CERTAUTH +");
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;CONNECT(CERTAUTH  LABEL('" + pageCEAUCERTtxtCEAULabel + "') +");
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;RING(" + pageCEAUZOStxtCEAUKeyringZOS + ") USAGE(CERTAUTH))");       
    instructionsAddBr();

    instructionsAddCmp("RACDCERT CERTAUTH +");
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;LISTRING(" + pageCEAUZOStxtCEAUKeyringZOS + ")");  
    instructionsAddBr();
  }

  /****************************************************************/
  /* Function: genCreateKeyrings                                  */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void genCreateKeyrings() {
    boolean javaclient = false;

    /* Client side */
    if (pageSNDRchcSNDRPlatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
      instructionsTitleAdd("Create SSL client keyring on " + pageSNDRtxtSNDRHost); 
      genCreateKeyringZOS(pageSNDRZOStxtSNDRKeyringZOS, pageSNDRZOStxtSNDRID);
    }
    else {
      instructionsTitleAdd("Create SSL client key database on " + pageSNDRtxtSNDRHost); 
      if ((pageSNDRradCS == true) && (pageSNDRradJO == true)) javaclient = true;
      genCreateKeyringUNIXWIN(javaclient, 
                              pageSNDRUNIXWINchcSNDRCommand, 
                              pageSNDRUNIXWINtxtSNDRKeyringUNIXWIN, 
                              "clientpass",
                              pageSNDRCERTtxtSNDRExpiry,
                              pageSNDRCAPIcbxSNDRFips);
    }

    /* Server side */
    if (pageRCVRchcRCVRPlatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
      instructionsTitleAdd("Create SSL server keyring on " + pageRCVRtxtRCVRHost); 
      genCreateKeyringZOS(pageRCVRZOStxtRCVRKeyringZOS, pageRCVRZOStxtRCVRID);
    }
    else {
      instructionsTitleAdd("Create SSL server key database on " + pageRCVRtxtRCVRHost); 
      genCreateKeyringUNIXWIN(false , 
                              pageRCVRUNIXWINchcRCVRCommand, 
                              pageRCVRUNIXWINtxtRCVRKeyringUNIXWIN, 
                              "serverpass",
                              pageRCVRCERTtxtRCVRExpiry,
                              pageRCVRCAPIcbxRCVRFips);
    }
  }

  /****************************************************************/
  /* Function: genCreateKeyringZOS                                */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void genCreateKeyringZOS(String keyring, String id) {
    // Create Keyring
    instructionsAddCmp("RACDCERT ID(" + id + ") +");
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;ADDRING(" + keyring + ")"); 
    instructionsAddBr();
  }

  /****************************************************************/
  /* Function: genCreateKeyringUNIXWIN                            */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void genCreateKeyringUNIXWIN(boolean javaclient, String cmd, String db, String passwd, String exp, boolean fips) {
    String ins = new String();

    // Create Keyring
    if (javaclient == true) {
      ins = cmd + " -keydb -create -db \"" + db  + "\" -pw " + passwd + " -type jks ";
    }
    else {
      ins = cmd + " -keydb -create -db \"" + db  + "\" -pw " + passwd + " -type cms -expire " + exp + " -stash ";
    }

    if (cmd.equals(MQTLSSSLWizardGlobal.COMMAND_CAPI)) ins = ins + doCapi(fips, null);
    instructionsAdd(ins);
  }

  /****************************************************************/
  /* Function: genGetCA                                           */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void genGetPublicCA() {
    //Get CA cert onto client and server machines
    if (pageCEAUWHEREradType == true) {
      //Get from internal CA
      getGetPublicCAInternal();
    } 
    else {
      getGetPublicCAExternal();
    }

    //Add CA cert to the client and server databases
    genGetPublicCAAdds();
  }

  /****************************************************************/
  /* Function: getGetCAInternal                                   */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void getGetPublicCAInternal() {
    instructionsTitleAdd("Copy the public internal CA certificate from " + pageCEAUtxtCEAUHost + " to the SSL client and SSL server machines"); 

    //Export/Extract CA cert
    if (pageCEAUchcCEAUPlatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
      getGetPublicCAInternalZOS();
    }
    else {
      getGetPublicCAInternalUNIXWIN();
    }

  }

  /****************************************************************/
  /* Function: getGetCAInternalZOS                                */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void getGetPublicCAInternalZOS() {
    String ins = new String();
    String dsn = new String();

    dsn = zosdsn(pageCEAUCERTtxtCEAULabel);
    
    // Extract the CA cert
    instructionsAddCmp("RACDCERT CERTAUTH EXPORT( +");
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;LABEL('" + pageCEAUCERTtxtCEAULabel + "')) +");
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;DSN(" + dsn + ") +");       
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;FORMAT(CERTB64)");       
    instructionsAddBr();

    // Copy it to the SSL client machine
    if (!pageCEAUtxtCEAUHost.equals(pageSNDRtxtSNDRHost)) {
      ins = "FTP the dataset " + dsn + " in ASCII mode from " + pageCEAUtxtCEAUHost + " to " + pageSNDRtxtSNDRHost + "."; 
      if (!pageSNDRchcSNDRPlatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
        ins = ins + " Name the file " + pageCEAUCERTtxtCEAULabel + ".crt on " + pageSNDRtxtSNDRHost + "."; 
      }
      ins = ins + " Set RECFM to variable block before transfering files (Use FTP command 'quote site recfm=vb')."; 
      instructionsAdd(ins); 
    }
    // Copy it to the SSL server machine
    if (!pageCEAUtxtCEAUHost.equals(pageRCVRtxtRCVRHost)) {
      ins = "FTP the dataset " + dsn + " in ASCII mode from " + pageCEAUtxtCEAUHost + " to " + pageRCVRtxtRCVRHost + "."; 
      if (!pageSNDRchcSNDRPlatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
        ins = ins + " Name the file " + pageCEAUCERTtxtCEAULabel + ".crt on " + pageRCVRtxtRCVRHost + "."; 
      }
      ins = ins + " Set RECFM to variable block before transfering files (Use FTP command 'quote site recfm=vb')."; 
      instructionsAdd(ins); 
    }
  }

  /****************************************************************/
  /* Function: getGetCAInternalUNIXWIN                            */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void getGetPublicCAInternalUNIXWIN() {
    String ins = new String();
    String dsn = new String();

    dsn = zosdsn(pageCEAUCERTtxtCEAULabel);

    // Extract the CA cert
    ins = pageCEAUUNIXWINchcCEAUCommand + " -cert -extract -db \""+ pageCEAUUNIXWINtxtCEAUKeyringUNIXWIN + "\" -pw capass -label " + pageCEAUCERTtxtCEAULabel  
                      + " -target " + pageCEAUCERTtxtCEAULabel + ".crt -format ascii";
    if (pageCEAUUNIXWINchcCEAUCommand.equals(MQTLSSSLWizardGlobal.COMMAND_CAPI)) {
      ins = ins + doCapi(pageCEAUCAPIcbxCEAUFips, null);
    }
    instructionsAdd(ins); 

    // Copy it to the SSL client machine
    if (!pageCEAUtxtCEAUHost.equals(pageSNDRtxtSNDRHost)) {
      ins = "FTP " + pageCEAUCERTtxtCEAULabel + ".crt in ASCII mode from " + pageCEAUtxtCEAUHost + " to " + pageSNDRtxtSNDRHost + "."; 
      if (pageSNDRchcSNDRPlatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
        ins = ins + " Name the dataset " + dsn + " on " + pageSNDRtxtSNDRHost + "."; 
      }
      instructionsAdd(ins); 
    }
    // Copy it to the SSL server machine
    if (!pageCEAUtxtCEAUHost.equals(pageRCVRtxtRCVRHost)) {
      ins = "FTP " + pageCEAUCERTtxtCEAULabel + ".crt in ASCII mode from " + pageCEAUtxtCEAUHost + " to " + pageRCVRtxtRCVRHost + "."; 
      if (pageSNDRchcSNDRPlatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
        ins = ins + " Name the dataset " + dsn + " on " + pageRCVRtxtRCVRHost + "."; 
      }
      instructionsAdd(ins); 
    }
  }

  /****************************************************************/
  /* Function: getGetCAExternal                                   */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void getGetPublicCAExternal() {
    instructionsTitleAdd("Save the public CA certificate to the SSL client and SSL server machines"); 

    if (pageSNDRchcSNDRPlatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
      instructionsAdd("Store the public CA certificate supplied by your CA to a dataset <CA certificate dataset> on " + pageSNDRtxtSNDRHost + "."); 
    }
    else {
      instructionsAdd("Store the public CA certificate supplied by your CA to a file <CA certificate file> on " + pageSNDRtxtSNDRHost + "."); 
    }

    if (pageRCVRchcRCVRPlatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
      instructionsAdd("Store the public CA certificate supplied by your CA to a dataset <CA certificate dataset> on " + pageRCVRtxtRCVRHost + "."); 
    }
    else {
      instructionsAdd("Store the public CA certificate supplied by your CA to a file <CA certificate file> on " + pageRCVRtxtRCVRHost + "."); 
    }
  }

  /****************************************************************/
  /* Function: genGetCAPublicAdds                                 */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void genGetPublicCAAdds() {
    // SSL client side
    if (pageSNDRchcSNDRPlatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
      instructionsTitleAdd("Add the public CA certificate to SSL client keyring on " + pageSNDRtxtSNDRHost); 
      genGetCAAddZOS(pageSNDRZOStxtSNDRID, pageSNDRZOStxtSNDRKeyringZOS);
    }
    else {
      instructionsTitleAdd("Add the public CA certificate to SSL client key database on " + pageSNDRtxtSNDRHost); 
      genGetCAAddUNIXWIN(pageSNDRUNIXWINchcSNDRCommand, 
                         pageSNDRUNIXWINtxtSNDRKeyringUNIXWIN,
                         "clientpass",
                         pageSNDRCAPIcbxSNDRFips);
    }

    // SSL server side
    if (pageRCVRchcRCVRPlatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
      instructionsTitleAdd("Add the public CA certificate to SSL server keyring on " + pageRCVRtxtRCVRHost); 
      genGetCAAddZOS(pageRCVRZOStxtRCVRID, pageRCVRZOStxtRCVRKeyringZOS);
    }
    else {
      instructionsTitleAdd("Add the public CA certificate to SSL server key database on " + pageRCVRtxtRCVRHost); 
      genGetCAAddUNIXWIN(pageRCVRUNIXWINchcRCVRCommand, 
                         pageRCVRUNIXWINtxtRCVRKeyringUNIXWIN,
                         "serverpass",
                         pageRCVRCAPIcbxRCVRFips);
    }
  }

  /****************************************************************/
  /* Function: genGetCAAddZOS                                     */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void genGetCAAddZOS(String id, String keyring) {
    String dsn = new String();
    String lbl = new String();

    if (pageCEAUWHEREradType == true) {
      dsn = zosdsn(pageCEAUCERTtxtCEAULabel);
      lbl = pageCEAUCERTtxtCEAULabel;
    } else {
      dsn = "<CA certificate dataset>";
      lbl = "<CA certificate label>"; 
    }

    instructionsAddCmp("RACDCERT CERTAUTH +");
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;ADD(" + dsn + ") +");       
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;TRUST WITHLABEL('" + lbl + "') ");
    instructionsAddBr();

    instructionsAddCmp("RACDCERT CERTAUTH +");
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;LIST(LABEL('" + lbl + "'))");       
    instructionsAddBr();

    instructionsAddCmp("RACDCERT ID(" + id + ") +");
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;CONNECT(CERTAUTH  LABEL('" + lbl + "') +");
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;RING(" + keyring + ") USAGE(CERTAUTH))");       
    instructionsAddBr();

    instructionsAddCmp("RACDCERT ID(" + id + ") +");
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;LISTRING(" + keyring + ")");  
    instructionsAddBr();

  }

  /****************************************************************/
  /* Function: genGetCAAddUNIXWIN                                 */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void genGetCAAddUNIXWIN(String cmd, String db, String passwd, boolean fips) {
    String lbl = new String();
    String crt = new String();
    String ins = new String();

    if (pageCEAUWHEREradType == true) {
      lbl = pageCEAUCERTtxtCEAULabel;
      crt = pageCEAUCERTtxtCEAULabel + ".crt";
    } else {
      lbl = "<CA certifcate label>"; 
      crt = "<CA certifcate file>";
    }

    ins = cmd + " -cert -add -db \""+ db + "\" -pw " + passwd + " -label " 
                      + lbl + " -file " + crt + " -format ascii -trust enable";
    if (cmd.equals(MQTLSSSLWizardGlobal.COMMAND_CAPI)) ins = ins + doCapi(fips, null);
    instructionsAdd(ins);

    ins = cmd + " -cert -list -db \"" + db + "\" -pw " + passwd;
    if (cmd.equals(MQTLSSSLWizardGlobal.COMMAND_CAPI)) ins = ins + doCapi(fips, null);
    instructionsAdd(ins);

  }

  /****************************************************************/
  /* Function: genCreateCerts                                     */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void genCreateCerts() {


    if (pageCHLradAuReq == true) {
      instructionsTitleAdd("SSL client certificate setup on " + pageSNDRtxtSNDRHost); 
      if (pageSNDRchcSNDRPlatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
        genCreateCertZOS(pageSNDRCERTtxtSNDRLabel     ,
                         pageSNDRtxtSNDRQmgrName      ,
                         pageSNDRZOStxtSNDRKeyringZOS ,
                         pageSNDRtxtSNDRHost          ,
                         pageSNDRZOStxtSNDRID         ,
                         pageSNDRCERTchcSNDRDNCN      ,
                         pageSNDRCERTtxtSNDRDNOU      ,
                         pageSNDRCERTtxtSNDRDNO       ,
                         pageSNDRCERTtxtSNDRDNL       ,
                         pageSNDRCERTtxtSNDRDNST      ,
                         pageSNDRCERTtxtSNDRDNC       ,
                         pageSNDRCERTtxtSNDRExpiry
                        );
      }
      else {
        String certfilename = new String();
        if (pageSNDRradCS == true) {
          certfilename = pageSNDRtxtSNDRUser;
        } else {
          certfilename = pageSNDRtxtSNDRQmgrName;
        }

        genCreateCertUNIXWIN(pageSNDRCERTtxtSNDRLabel     ,
                             certfilename                 ,
                             pageSNDRUNIXWINtxtSNDRKeyringUNIXWIN ,
                             "clientpass",
                             pageSNDRtxtSNDRHost          ,
                             pageSNDRUNIXWINchcSNDRCommand ,
                             pageSNDRCERTchcSNDRDNCN      ,
                             pageSNDRCERTtxtSNDRDNOU      ,
                             pageSNDRCERTtxtSNDRDNO       ,
                             pageSNDRCERTtxtSNDRDNL       ,
                             pageSNDRCERTtxtSNDRDNST      ,
                             pageSNDRCERTtxtSNDRDNC       ,
                             pageSNDRCERTtxtSNDRExpiry    ,
                             pageSNDRCAPIcbxSNDRFips      ,
                             pageSNDRCAPIchcSNDRSigalg
                            );
      }
    }

    instructionsTitleAdd("SSL server certificate setup on " + pageRCVRtxtRCVRHost); 
    if (pageRCVRchcRCVRPlatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
      genCreateCertZOS(pageRCVRCERTtxtRCVRLabel     ,
                       pageRCVRtxtRCVRQmgrName      ,
                       pageRCVRZOStxtRCVRKeyringZOS ,
                       pageRCVRtxtRCVRHost          ,
                       pageRCVRZOStxtRCVRID         ,
                       pageRCVRCERTchcRCVRDNCN      ,
                       pageRCVRCERTtxtRCVRDNOU      ,
                       pageRCVRCERTtxtRCVRDNO       ,
                       pageRCVRCERTtxtRCVRDNL       ,
                       pageRCVRCERTtxtRCVRDNST      ,
                       pageRCVRCERTtxtRCVRDNC       ,
                       pageRCVRCERTtxtRCVRExpiry
                      );
    }
    else {
      genCreateCertUNIXWIN(pageRCVRCERTtxtRCVRLabel    ,
                           pageRCVRtxtRCVRQmgrName     ,
                           pageRCVRUNIXWINtxtRCVRKeyringUNIXWIN ,
                           "serverpass",
                           pageRCVRtxtRCVRHost         ,
                           pageRCVRUNIXWINchcRCVRCommand ,
                           pageRCVRCERTchcRCVRDNCN     ,
                           pageRCVRCERTtxtRCVRDNOU     ,
                           pageRCVRCERTtxtRCVRDNO      ,
                           pageRCVRCERTtxtRCVRDNL      ,
                           pageRCVRCERTtxtRCVRDNST     ,
                           pageRCVRCERTtxtRCVRDNC      ,
                           pageRCVRCERTtxtRCVRExpiry   ,
                           pageRCVRCAPIcbxRCVRFips     ,
                           pageRCVRCAPIchcRCVRSigalg
                          );
    }
  }

  /****************************************************************/
  /* Function: genCreateCertZOS                                   */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void genCreateCertZOS(String label  ,
                                String certfilename   ,   
                                String keyring,
                                String host   ,
                                String id     ,
                                String DNCN   ,
                                String DNOU   ,
                                String DNO    ,
                                String DNL    ,
                                String DNST   ,
                                String DNC    ,
                                String exp
                               ) {
    String ins = new String();
    String notafter = new String();
    int iexp = Integer.parseInt(exp);

    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DATE, iexp);
    notafter = cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.DAY_OF_MONTH);


    // Create cert
    instructionsAddCmp("RACDCERT ID(" + id + ") GENCERT +");
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;SUBJECTSDN(CN('" + DNCN + "') +");  
    if (!DNOU.equals("")) {
      instructionsAddCmp("&nbsp;&nbsp;&nbsp;OU('" + DNOU + "') +");                                               
    }
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;O('" + DNO + "') +");                                               
    if (!DNL.equals("")) {
      instructionsAddCmp("&nbsp;&nbsp;&nbsp;L('" + DNL + "') +");                                           
    }
    if (!DNST.equals("")) {
      instructionsAddCmp("&nbsp;&nbsp;&nbsp;SP('" + DNST + "') +");                                           
    }
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;C('" + DNC + "')) +");                                              
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;NOTAFTER('" + notafter + "')");   
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;WITHLABEL('" + label + "')");   
    instructionsAddBr();

    instructionsAddCmp("RACDCERT ID(" + id + ") +");
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;LIST(LABEL('" + label + "'))");       
    instructionsAddBr();

    if (pageCEAUMODELradType == true) {
      instructionsAddCmp("RACDCERT ID(" + id + ") +");
      instructionsAddCmp("&nbsp;&nbsp;&nbsp;CONNECT(ID(" +  id + ")  LABEL('" + label + "') +");
      instructionsAddCmp("&nbsp;&nbsp;&nbsp;RING(" + keyring + ") USAGE(PERSONAL))");  
      instructionsAddBr();

      instructionsAddCmp("RACDCERT ID(" + id + ") +");
      instructionsAddCmp("&nbsp;&nbsp;&nbsp;LISTRING(" + keyring + ")");  
      instructionsAddBr();
    }
    else {
      instructionsAddCmp("RACDCERT ID(" + id + ") GENREQ +");
      instructionsAddCmp("&nbsp;&nbsp;&nbsp;(LABEL('" + label + "')) +");
      instructionsAddCmp("&nbsp;&nbsp;&nbsp;DSN(" + certfilename + ")");
      instructionsAddBr();

      if (pageCEAUWHEREradType == true) {
        if (!host.equals(pageCEAUtxtCEAUHost)) {
          ins = "FTP " + certfilename + " in ASCII mode from " + host + " to " + pageCEAUtxtCEAUHost + "."; 
          if (!pageCEAUchcCEAUPlatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) { 
            ins = ins + " Name the file " + certfilename + ".req on " + pageCEAUtxtCEAUHost + "."; 
          }
          instructionsAdd(ins); 
        }

        genSignCert(certfilename);

        if (!host.equals(pageCEAUtxtCEAUHost)) {
          if (pageCEAUchcCEAUPlatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) { 
            instructionsAdd("<i>FTP " + certfilename + " in ASCII mode from " + pageCEAUtxtCEAUHost + " to " + host + ".</i>"); 
          }
          else {
            instructionsAdd("<i>FTP " + certfilename + ".crt in ASCII mode from " + pageCEAUtxtCEAUHost + " to " + host + ". Name the dataset " + certfilename + " on " + host + ".</i>"); 
          }
        }
      }
      else {
        instructionsAdd("<i>Now send the certificate request dataset to the CA to be signed.</i>");
        instructionsAdd("<i>Once the CA has returned the signed SSL client certificate, store it in a dataset named " + certfilename + ".</i>"); 
      }

      instructionsTitleAdd("Add the signed SSL client certificate on " + host); 

      instructionsAddCmp("RACDCERT ID(" + id + ") +");
      instructionsAddCmp("&nbsp;&nbsp;&nbsp;ADD(" + certfilename + ") +");       
      instructionsAddCmp("&nbsp;&nbsp;&nbsp;TRUST WITHLABEL('" + label + "')");
      instructionsAddBr();

      instructionsAddCmp("RACDCERT ID(" + id + ") +");
      instructionsAddCmp("&nbsp;&nbsp;&nbsp;LIST(LABEL('" + label + "'))");       
      instructionsAddBr();

      instructionsAddCmp("RACDCERT ID(" + id + ") +");
      instructionsAddCmp("&nbsp;&nbsp;&nbsp;CONNECT(ID(" +  id + ")  LABEL('" + label + "') +");
      instructionsAddCmp("&nbsp;&nbsp;&nbsp;RING(" + keyring + ") USAGE(PERSONAL))");  
      instructionsAddBr();

      instructionsAddCmp("RACDCERT ID(" + id + ") +");
      instructionsAddCmp("&nbsp;&nbsp;&nbsp;LISTRING(" + keyring + ")");  
      instructionsAddBr();
    }
  }

  /****************************************************************/
  /* Function: genCreateCertUNIXWIN                               */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void genCreateCertUNIXWIN(String label  ,
                                    String certfilename ,
                                    String db     ,
                                    String passwd ,
                                    String host   ,
                                    String cmd    ,
                                    String DNCN   ,
                                    String DNOU   ,
                                    String DNO    ,
                                    String DNL    ,
                                    String DNST   ,
                                    String DNC    ,
                                    String exp    ,
                                    boolean fips  , 
                                    String sigalg
                                   ) {
    String ins = new String();
    String dsn = new String();

    dsn = zosdsn(certfilename);

    ins = cmd;  
    if (pageCEAUMODELradType == true) {
      ins = ins.concat(" -cert -create -db \"");            
    } else {
      ins = ins.concat(" -certreq -create -db \"");            
    }

    ins = ins.concat(db);          
    ins = ins.concat("\" -pw " + passwd + " -label ");             
    ins = ins.concat(label);                            
    ins = ins.concat(" -dn \"");
    ins = ins.concat(getDN(DNCN,
                           DNOU,
                           DNO ,
                           DNL ,
                           DNST,
                           DNC)); 
    if (pageCEAUMODELradType == true) {
      ins = ins.concat("\" -expire " + exp + " ");               
    } else {
      ins = ins.concat("\" -file " + certfilename + ".req ");               
    }
    if (cmd.equals(MQTLSSSLWizardGlobal.COMMAND_CAPI)) ins = ins + doCapi(fips, sigalg);

    instructionsAdd(ins);                                             

    if (pageCEAUMODELradType == true) {
      ins = cmd + " -cert -list -db \"" + db + "\" -pw " + passwd;
      if (cmd.equals(MQTLSSSLWizardGlobal.COMMAND_CAPI)) ins = ins + doCapi(fips, null);
      instructionsAdd(ins);
    }
    else {

      if (pageCEAUWHEREradType == true) {
        if (!host.equals(pageCEAUtxtCEAUHost)) {
          ins = "FTP " + certfilename + ".req in ASCII mode from " + host + " to " + pageCEAUtxtCEAUHost + "."; 
          if (pageCEAUchcCEAUPlatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) { 
            ins = ins + " Name the dataset " + dsn + " on " + pageCEAUtxtCEAUHost + "."; 
          }
          instructionsAdd(ins); 
        }

        if (pageCEAUchcCEAUPlatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) { 
          genSignCert(dsn);
        }
        else {
          genSignCert(certfilename);
        }

        if (!host.equals(pageCEAUtxtCEAUHost)) {
          if (pageCEAUchcCEAUPlatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) { 
            instructionsAdd("<i>FTP " + dsn + " in ASCII mode from " + pageCEAUtxtCEAUHost + " to " + host + ". Name the file " + certfilename + ".crt on " + host + ". Set RECFM to variable block before transfering files (Use FTP command 'quote site recfm=vb').</i>"); 
          }
          else {
            instructionsAdd("<i>FTP " + certfilename + ".crt in ASCII mode from " + pageCEAUtxtCEAUHost + " to " + host + ".</i>"); 
          }
        }
      }
      else {
        instructionsAdd("<i>Now send the certificate request file to the CA to be signed.</i>");
        instructionsAdd("<i>Once the CA has returned the signed SSL client certificate, save it to the file " + certfilename + ".crt.</i>"); 
      }

      instructionsTitleAdd("Receive the signed SSL client certificate on " + host); 
      ins = cmd + " -cert -receive -db \""+ db + "\" -pw " + passwd + " -file " + certfilename + ".crt -format ascii ";
      if (cmd.equals(MQTLSSSLWizardGlobal.COMMAND_CAPI)) ins = ins + doCapi(fips, null);
      instructionsAdd(ins);

      ins = cmd + " -cert -list -db \"" + db + "\" -pw " + passwd;
      if (cmd.equals(MQTLSSSLWizardGlobal.COMMAND_CAPI)) ins = ins + doCapi(fips, null);
      instructionsAdd(ins);
    }
  }

  /****************************************************************/
  /* Function: genSignCert                                        */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void genSignCert(String certfilename) {
    instructionsTitleAdd("Sign the certificate request on " + pageCEAUtxtCEAUHost); 

    if (pageCEAUchcCEAUPlatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
      instructionsAdd("<i>NOTE: The " + certfilename + " dataset may require hex editing to remove x0D characters from the end of lines.</i>"); 
      instructionsAddCmp("RACDCERT CERTAUTH GENCERT(" + certfilename + ") +");
      instructionsAddCmp("&nbsp;&nbsp;&nbsp;SIGNWITH(CERTAUTH LABEL('" + pageCEAUCERTtxtCEAULabel + "'))");  
      instructionsAddBr();
    }
    else {
      String ins = new String();
      ins = pageCEAUUNIXWINchcCEAUCommand + " -cert -sign -file " + certfilename + ".req -db \""+ pageCEAUUNIXWINtxtCEAUKeyringUNIXWIN + "\" -pw capass -label "
                        + pageCEAUCERTtxtCEAULabel + " -target " + certfilename + ".crt -format ASCII -expire " + pageCEAUCERTtxtCEAUExpiry; 
      if (pageCEAUUNIXWINchcCEAUCommand.equals(MQTLSSSLWizardGlobal.COMMAND_CAPI)) {
        ins = ins + doCapi(pageCEAUCAPIcbxCEAUFips, pageCEAUCAPIchcCEAUSigalg);
      }
      instructionsAdd(ins); 
    }
  }

  /****************************************************************/
  /* Function: genGetPublicSelfSigned                             */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void genGetPublicSelfSigned() {
    //Process SSL client if client auth is set
    if (pageCHLradAuReq == true) {
      String certfilename = new String();

      if (pageSNDRradCS == true) {
        certfilename = pageSNDRtxtSNDRUser;
      } else {
        certfilename = pageSNDRtxtSNDRQmgrName;
      }

      instructionsTitleAdd("Copy the public SSL client certificate to the SSL server side"); 
      //SSL client side extract
      if (pageSNDRchcSNDRPlatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
        String dsn = new String();
        dsn = zosdsn(certfilename);
        genGetPublicSelfSignedExtractZOS(pageSNDRtxtSNDRHost, 
                                         pageRCVRtxtRCVRHost, 
                                         pageRCVRchcRCVRPlatform, 
                                         dsn,
                                         pageSNDRZOStxtSNDRID, 
                                         pageSNDRCERTtxtSNDRLabel);
      }
      else {
        genGetPublicSelfSignedExtractUNIXWIN(pageSNDRtxtSNDRHost, 
                                             pageRCVRtxtRCVRHost, 
                                             pageRCVRchcRCVRPlatform, 
                                             certfilename,
                                             pageSNDRUNIXWINchcSNDRCommand, 
                                             pageSNDRUNIXWINtxtSNDRKeyringUNIXWIN, 
                                             "clientpass",
                                             pageSNDRCERTtxtSNDRLabel,
                                             pageSNDRCAPIcbxSNDRFips);
      }

      //SSL server side add
      if (pageRCVRchcRCVRPlatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
        String dsn = new String();
        dsn = zosdsn(certfilename);
        genGetPublicSelfSignedAddZOS(pageRCVRZOStxtRCVRID, pageSNDRCERTtxtSNDRLabel, dsn);
      }
      else {
        genGetPublicSelfSignedAddUNIXWIN(pageRCVRUNIXWINchcRCVRCommand, 
                                         pageRCVRUNIXWINtxtRCVRKeyringUNIXWIN, 
                                         "serverpass",
                                         pageSNDRCERTtxtSNDRLabel, 
                                         certfilename,
                                         pageRCVRCAPIcbxRCVRFips);
      }
    }

    //Process SSL server
    instructionsTitleAdd("Copy the public SSL server certificate to the SSL client side"); 
    //SSL server side extract
    if (pageRCVRchcRCVRPlatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
      String dsn = new String();
      dsn = zosdsn(pageRCVRtxtRCVRQmgrName);
      genGetPublicSelfSignedExtractZOS(pageRCVRtxtRCVRHost, 
                                       pageSNDRtxtSNDRHost, 
                                       pageSNDRchcSNDRPlatform, 
                                       dsn,
                                       pageRCVRZOStxtRCVRID, 
                                       pageRCVRCERTtxtRCVRLabel);
    }
    else {
      genGetPublicSelfSignedExtractUNIXWIN(pageRCVRtxtRCVRHost, 
                                           pageSNDRtxtSNDRHost, 
                                           pageSNDRchcSNDRPlatform, 
                                           pageRCVRtxtRCVRQmgrName,
                                           pageRCVRUNIXWINchcRCVRCommand, 
                                           pageRCVRUNIXWINtxtRCVRKeyringUNIXWIN, 
                                           "serverpass",
                                           pageRCVRCERTtxtRCVRLabel,
                                           pageRCVRCAPIcbxRCVRFips);
    }

    //SSL client side add
    if (pageSNDRchcSNDRPlatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
      String dsn = new String();
      dsn = zosdsn(pageRCVRtxtRCVRQmgrName);
      genGetPublicSelfSignedAddZOS(pageSNDRZOStxtSNDRID, pageRCVRCERTtxtRCVRLabel, dsn);
    }
    else {
      genGetPublicSelfSignedAddUNIXWIN(pageSNDRUNIXWINchcSNDRCommand, 
                                       pageSNDRUNIXWINtxtSNDRKeyringUNIXWIN, 
                                       "clientpass",
                                       pageRCVRCERTtxtRCVRLabel, 
                                       pageRCVRtxtRCVRQmgrName,
                                       pageSNDRCAPIcbxSNDRFips);
    }

  }

  /****************************************************************/
  /* Function: genGetPublicSelfSignedExtractZOS                   */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void genGetPublicSelfSignedExtractZOS(String host, 
                                                String otherhost, 
                                                String otherplatform, 
                                                String certfilename,
                                                String id, 
                                                String label) {
    String ins = new String();

    instructionsAddCmp("RACDCERT ID(" + id + ") EXPORT( +");
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;LABEL('" + label + "')) +");
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;DSN(" + certfilename + ") +");       
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;FORMAT(CERTB64)");       
    instructionsAddBr();

    if (!host.equals(otherhost)) {
      ins = "FTP the dataset " + certfilename + " in ASCII mode from " + host + " to " + otherhost + "."; 
      if (!otherplatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
        ins = ins + " Name the file " + certfilename + ".crt on " + otherhost + "."; 
      }
      ins = ins + " Set RECFM to variable block before transfering files (Use FTP command 'quote site recfm=vb')."; 
      instructionsAdd(ins); 
    }
  }

  /****************************************************************/
  /* Function: genGetPublicSelfSignedExtractUNIXWIN               */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void genGetPublicSelfSignedExtractUNIXWIN(String host, 
                                                    String otherhost, 
                                                    String otherplatform, 
                                                    String certfilename,
                                                    String cmd, 
                                                    String db, 
                                                    String passwd,
                                                    String label,
                                                    boolean fips) {
    String ins = new String();

    ins = cmd + " -cert -extract -db \"" + db  + "\" -pw " + passwd + " -label " + label 
                    + " -target " + certfilename + ".crt -format ascii ";
    if (cmd.equals(MQTLSSSLWizardGlobal.COMMAND_CAPI)) ins = ins + doCapi(fips, null);
    instructionsAdd(ins);

    if (!host.equals(otherhost)) {
      ins = "FTP " + certfilename + ".crt in ASCII mode from " + host + " to " + otherhost + "."; 
      if (otherplatform .equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
        ins = ins + " Name the dataset " + certfilename + " on " + otherplatform + "."; 
      }
      instructionsAdd(ins); 
    }
  }

  /****************************************************************/
  /* Function: genGetPublicSelfSignedAddZOS                       */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void genGetPublicSelfSignedAddZOS(String id, 
                                            String label, 
                                            String certfilename) {
    instructionsAddCmp("RACDCERT ID(" + id + ") +");
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;ADD(" + certfilename + ") +");       
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;TRUST WITHLABEL('" + label + "')");
    instructionsAddBr();

    instructionsAddCmp("RACDCERT ID(" + id + ") +");
    instructionsAddCmp("&nbsp;&nbsp;&nbsp;LIST(LABEL('" + label + "'))");       
    instructionsAddBr();
  }

  /****************************************************************/
  /* Function: genGetPublicSelfSignedAddUNIXWIN                   */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void genGetPublicSelfSignedAddUNIXWIN(String cmd, 
                                                String db, 
                                                String passwd,
                                                String label, 
                                                String certfilename,
                                                boolean fips) {
    String ins = new String();

    ins = cmd + " -cert -add -db \"" + db + "\" -pw " + passwd + " -label " 
                    + label + " -file " + certfilename + ".crt -format ascii ";
    if (cmd.equals(MQTLSSSLWizardGlobal.COMMAND_CAPI)) ins = ins + doCapi(fips, null);
    instructionsAdd(ins);

    ins = cmd + " -cert -list -db \"" + db + "\" -pw " + passwd;
    if (cmd.equals(MQTLSSSLWizardGlobal.COMMAND_CAPI)) ins = ins + doCapi(fips, null);
    instructionsAdd(ins);
  }

  /****************************************************************/
  /* Function: genWmqAdmin                                        */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void genWmqAdmin() {
    genWmqAdminS();   
    genWmqAdminR();   
  }

  /****************************************************************/
  /* Function: genWmqAdminS                                       */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void genWmqAdminS() {
    String ins = new String();
    int strlen;

    if (pageSNDRradCS == false) {
      instructionsTitleAdd("MQSC commands for SSL client side queue manager " + pageSNDRtxtSNDRQmgrName );

      instructionsAdd("<i>NOTE: The step below is optional because SSLKEYR may already be set.</i>"); 
      if (pageSNDRchcSNDRPlatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
        instructionsAdd("ALTER QMGR SSLKEYR(" + pageSNDRZOStxtSNDRKeyringZOS  + ")");  
      }
      else {
        strlen = pageSNDRUNIXWINtxtSNDRKeyringUNIXWIN.length(); 
        instructionsAdd("ALTER QMGR SSLKEYR('" + pageSNDRUNIXWINtxtSNDRKeyringUNIXWIN.substring(0,strlen-4) + "')");  
      }

      // Sender SSL Tasks
      if (pageSNDRchcSNDRPlatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
        instructionsAdd("<i>NOTE: The step below is optional because SSLTASKS may already be set.</i>"); 
        instructionsAdd("ALTER QMGR SSLTASKS(" + pageSNDRZOStxtSNDRTasks + ")");  
      }

      // Sender SSLFIPS
      instructionsAdd("<i>NOTE: The step below is optional because SSLFIPS may already be set.</i>"); 
      if (pageCHLcbxQmgrFips == true) {        
        instructionsAdd("ALTER QMGR SSLFIPS(YES)");  
      }
      else {
        instructionsAdd("ALTER QMGR SSLFIPS(NO)");  
      }

      // Sender Channel
      ins = "DEFINE CHANNEL('" + pageCHLtxtChannel  + "') CHLTYPE(SDR) TRPTYPE(TCP) ";
      ins = ins + "XMITQ('" + pageRCVRtxtRCVRQmgrName  + "') CONNAME('" + pageRCVRtxtRCVRHost + "(" + pageRCVRtxtRCVRPort + ")') ";
      ins = ins + "SSLCIPH(" + pageCHLchcCipherSpec + ") ";
      ins = ins + "SSLPEER('" + getDN(pageRCVRCERTchcRCVRDNCN ,
                                     pageRCVRCERTtxtRCVRDNOU ,
                                     pageRCVRCERTtxtRCVRDNO  ,
                                     pageRCVRCERTtxtRCVRDNL  ,
                                     pageRCVRCERTtxtRCVRDNST ,
                                     pageRCVRCERTtxtRCVRDNC) + "') ";
      ins = ins + "REPLACE";
      instructionsAdd(ins);

      // Sender XMITQ
      instructionsAdd("DEFINE QL(" + pageRCVRtxtRCVRQmgrName + ") USAGE(XMITQ)");
    }
  }

  /****************************************************************/
  /* Function: genWmqAdminR                                       */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void genWmqAdminR() {
    int strlen;
    String ins = new String();

    instructionsTitleAdd("MQSC commands for SSL server side queue manager " + pageRCVRtxtRCVRQmgrName );

    instructionsAdd("<i>NOTE: The step below is optional because SSLKEYR may already be set.</i>"); 
    if (pageRCVRchcRCVRPlatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
      instructionsAdd("ALTER QMGR SSLKEYR(" + pageRCVRZOStxtRCVRKeyringZOS + ")");  
    }
    else {
      strlen = pageRCVRUNIXWINtxtRCVRKeyringUNIXWIN .length(); 
      instructionsAdd("ALTER QMGR SSLKEYR('" + pageRCVRUNIXWINtxtRCVRKeyringUNIXWIN .substring(0,strlen-4) + "')");  
    }

    // Receiver SSL Tasks
    if (pageRCVRchcRCVRPlatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
      instructionsAdd("<i>NOTE: The step below is optional because SSLTASKS may already be set.</i>"); 
      instructionsAdd("ALTER QMGR SSLTASKS(" + pageRCVRZOStxtRCVRTasks + ")");  
    }

    // Sender SSLFIPS
    instructionsAdd("<i>NOTE: The step below is optional because SSLFIPS may already be set.</i>"); 
    if (pageCHLcbxQmgrFips == true) {
      instructionsAdd("ALTER QMGR SSLFIPS(YES)");  
    }
    else {
      instructionsAdd("ALTER QMGR SSLFIPS(NO)");  
    }

    // Receiver Channel
    if (pageSNDRradCS == true) {
      ins = "DEFINE CHANNEL('" + pageCHLtxtChannel + "') CHLTYPE(SVRCONN) TRPTYPE(TCP) SSLCIPH(" + pageCHLchcCipherSpec + ")";
      if (pageCHLradAuReq == true) {
        ins = ins + " SSLCAUTH(REQUIRED)"; 
      }
      else {
        ins = ins + " SSLCAUTH(OPTIONAL)"; 
      }
      ins = ins + " REPLACE";
      instructionsAdd(ins); 
    }
    else {
      ins = "DEFINE CHANNEL('" + pageCHLtxtChannel + "') CHLTYPE(RCVR) TRPTYPE(TCP) SSLCIPH(" + pageCHLchcCipherSpec  + ") ";
      if (pageCHLradAuReq == true) {
        ins = ins + "SSLCAUTH(REQUIRED) "; 
        ins = ins + "SSLPEER('" + getDN(pageSNDRCERTchcSNDRDNCN ,
                                       pageSNDRCERTtxtSNDRDNOU ,
                                       pageSNDRCERTtxtSNDRDNO  ,
                                       pageSNDRCERTtxtSNDRDNL  ,
                                       pageSNDRCERTtxtSNDRDNST ,
                                       pageSNDRCERTtxtSNDRDNC) + "') ";
      }
      else {
        ins = ins + "SSLCAUTH(OPTIONAL) "; 
      }


      ins = ins + "REPLACE";
      instructionsAdd(ins); 
    }
  }

  /****************************************************************/
  /* Function: genFinalWmqSteps                                   */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void genFinalWmqSteps() {
    if (pageSNDRradCS == true) {
      instructionsTitleAdd("MQSC command for SSL server side queue manager " + pageRCVRtxtRCVRQmgrName);
    }
    else {
      instructionsTitleAdd("MQSC commands for both queue managers");
    }
    // Restart both chinits
    instructionsAdd("REFRESH SECURITY TYPE(SSL)"); 
    if (pageSNDRchcSNDRPlatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS) ||
        pageRCVRchcRCVRPlatform.equals(MQTLSSSLWizardGlobal.PLATFORM_ZOS)) {
      instructionsAdd("<i>NOTE: If there are no SSL tasks running, the z/OS channel initiator requires a restart.</i>"); 
    }

    if (pageSNDRradCS == false) {
      // Start channel
      instructionsTitleAdd("MQSC commands for SSL client side queue manager " +pageSNDRtxtSNDRQmgrName);
      instructionsAdd("START CHANNEL('" +  pageCHLtxtChannel  + "')"); 
    }
    else {
      // Start client.
      // This is done elsewhere.
    }
  }

  /****************************************************************/
  /* Function: genClientSamples                                   */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void genClientSamples() {
    if (pageSNDRradJO == true) {
      String strJArgs = pageRCVRtxtRCVRHost + " " 
                         + pageRCVRtxtRCVRPort  + " " 
                         + pageCHLtxtChannel + " " 
                         + pageRCVRtxtRCVRQmgrName + " "; 
      String strJCiph;

      if      (pageCHLchcCipherSpec.equals("NULL_SHA")) strJCiph = "SSL_RSA_WITH_NULL_SHA";
      else if (pageCHLchcCipherSpec.equals("RC4_SHA_US")) strJCiph = "SSL_RSA_WITH_RC4_128_SHA";  
      else if (pageCHLchcCipherSpec.equals("DES_SHA_EXPORT")) strJCiph = "SSL_RSA_WITH_DES_CBC_SHA";  
      else if (pageCHLchcCipherSpec.equals("RC4_56_SHA_EXPORT1024")) strJCiph = "SSL_RSA_EXPORT1024_WITH_RC4_56_SHA";   
      else if (pageCHLchcCipherSpec.equals("DES_SHA_EXPORT1024")) strJCiph = "SSL_RSA_EXPORT1024_WITH_DES_CBC_SHA";   
      else if (pageCHLchcCipherSpec.equals("TRIPLE_DES_SHA_US")) strJCiph = "SSL_RSA_WITH_3DES_EDE_CBC_SHA";
      else if (pageCHLchcCipherSpec.equals("RC4_56_SHA_EXPORT1024")) strJCiph = "SSL_RSA_EXPORT1024_WITH_RC4_56_SHA";   
      else if (pageCHLchcCipherSpec.equals("TLS_RSA_WITH_AES_128_CBC_SHA")) strJCiph = "SSL_RSA_WITH_AES_128_CBC_SHA";
      else if (pageCHLchcCipherSpec.equals("TLS_RSA_WITH_AES_256_CBC_SHA")) strJCiph = "SSL_RSA_WITH_AES_256_CBC_SHA";
      else if (pageCHLchcCipherSpec.equals("TLS_RSA_WITH_DES_CBC_SHA")) strJCiph = "SSL_RSA_WITH_DES_CBC_SHA";
      else if (pageCHLchcCipherSpec.equals("TLS_RSA_WITH_3DES_EDE_CBC_SHA")) strJCiph = "SSL_RSA_WITH_3DES_EDE_CBC_SHA";
      else if (pageCHLchcCipherSpec.equals("FIPS_WITH_DES_CBC_SHA")) strJCiph = "SSL_RSA_FIPS_WITH_DES_CBC_SHA";
      else if (pageCHLchcCipherSpec.equals("FIPS_WITH_3DES_EDE_CBC_SHA")) strJCiph = "SSL_RSA_FIPS_WITH_3DES_EDE_CBC_SHA";
      else strJCiph = pageCHLchcCipherSpec;

      strJArgs = strJArgs.concat(strJCiph + " \"" + pageSNDRUNIXWINtxtSNDRKeyringUNIXWIN + "\" clientpass");

      instructionsTitleAdd("Run the Java or JMS sample client on " + pageSNDRtxtSNDRHost);
      instructionsAdd("java SSLSample " + strJArgs); 
      instructionsAdd("java SSLSampleJMS " + strJArgs); 
    }
    else {
      String strCArgs = pageRCVRtxtRCVRHost + "(" + pageRCVRtxtRCVRPort  + ") "
                         + pageCHLtxtChannel + " " 
                         + pageRCVRtxtRCVRQmgrName + " " 
                         + pageCHLchcCipherSpec  + " \"" 
                         + pageSNDRUNIXWINtxtSNDRKeyringUNIXWIN;

      // C sample must not end in ".kdb"
      if (strCArgs.substring(strCArgs.length()-4,strCArgs.length()).equals(".kdb")) {
        strCArgs = strCArgs.substring(0,strCArgs.length()-4);
      }
      strCArgs = strCArgs + "\" ";

      instructionsTitleAdd("Run the C sample client on " + pageSNDRtxtSNDRHost);
      instructionsAdd("SSLSample.exe " + strCArgs); 
    }
  }

  /****************************************************************/
  /* Function: instructionsAddCmp                                 */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void instructionsAddCmp(String cmd) {
    instructions = instructions + cmd + "<br>";
  }

  /****************************************************************/
  /* Function: instructionsAdd                                    */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void instructionsAddBr() {
    instructions = instructions + "<br>";
  }

  /****************************************************************/
  /* Function: instructionsAdd                                    */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void instructionsAdd(String cmd) {
    instructions = instructions + cmd + "<br><br>";
  }

  /****************************************************************/
  /* Function: instructionsSpacer                                 */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void instructionsSpacer() {
    instructions = instructions + "</p><p>";
  }

  /****************************************************************/
  /* Function: instructionsTitleAdd                               */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private void instructionsTitleAdd(String title) {
    instructionsSpacer();
    instructions = instructions + "<h2>" + title + "</h2><p>";
  }

  /****************************************************************/
  /* Function: instructionsTitleAdd                               */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private String zosdsn(String str) {
    String dsn = new String();
    dsn = str;
    if (dsn.length() > 8) {
      dsn = dsn.substring(0,8);
    }
    dsn = dsn.toUpperCase();

    return dsn;
  }

  /****************************************************************/
  /* Function: doCapi                                             */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private String doCapi(boolean fips, String sigalg) {
    String ins = new String();

    if (fips == true) {
      ins = ins + " -fips";
    }
    if (sigalg != null) {
      ins = ins + " -sigalg " + sigalg;
    }

    return ins;
  }

  /****************************************************************/
  /* Function: instructionsTitleAdd                               */
  /* Description:                                                 */
  /* Parms:                                                       */
  /* Returns:                                                     */
  /****************************************************************/
  private String getDN(String pDNCN ,
                       String pDNOU ,
                       String pDNO  ,
                       String pDNL  ,
                       String pDNST ,
                       String pDNC) {

    String ins = new String();

    ins = "CN=" + pDNCN + ",";

    if (!pDNOU.equals("")) {
      StringTokenizer st = new StringTokenizer(pDNOU, ",");
      while (st.hasMoreTokens()) {
        ins = ins + "OU=";
        ins = ins + st.nextToken();
        ins = ins + ",";
      }
    } 

    ins = ins.concat("O=");
    ins = ins.concat(pDNO);
    ins = ins.concat(",");
    
    if (!pDNL.equals("")) {
      ins = ins.concat("L=");
      ins = ins.concat(pDNL);
      ins = ins.concat(",");
    }
    
    if (!pDNST.equals("")) {
      ins = ins.concat("ST=");
      ins = ins.concat(pDNST);
      ins = ins.concat(",");
    }
    
    ins = ins.concat("C=");
    ins = ins.concat(pDNC);

    return ins;
  }

}






