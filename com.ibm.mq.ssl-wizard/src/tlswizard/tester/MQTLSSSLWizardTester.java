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
package tlswizard.tester;

import java.io.*;

import tlswizard.wizard.MQTLSSSLWizardParms200;

public class MQTLSSSLWizardTester {

  private static final int CONST_CLIENT = 0;
  private static final int CONST_SERVER = 1;

  private static final int CONST_WIN  = 0;
  private static final int CONST_UNIX = 1;
  private static final int CONST_ZOS  = 2;

  private static final int CONST_AUTHCLIENT   = 0;
  private static final int CONST_NOAUTHCLIENT = 1;

  private static final int CONST_CA = 0;
  private static final int CONST_SS = 1;

  private static final int CONST_CAINT = 0;
  private static final int CONST_CAEXT = 1;

  private static final int CONST_JAVA = 0;
  private static final int CONST_OTHER = 1;

  private static final int CONST_CMD  = 0;
  private static final int CONST_CAPI = 1;

  private static final int CONST_FIPSYES = 0;
  private static final int CONST_FIPSNO  = 1;

  private static final String PLATFORM_UNIX = "UNIX";
  private static final String PLATFORM_ZOS = "z/OS";
  private static final String PLATFORM_WIN = "Windows";

  private static String tname;
  private int    outputfilenum;
  private File tfile;
  private FileWriter a;

  public MQTLSSSLWizardTester(String arg) {
    String tname = new String();

    System.out.println("MQTLSSSLWizardTester starting " + arg);

    runTests(arg);

    System.out.println("MQTLSSSLWizardTester invocation complete");
  }

  public static void main(String args[]) {
    if (args.length != 1) {
      System.out.println ("Usage: java MQTLSSSLWizardTester testname");
      System.exit(0);
    }
    System.out.println ("MQTLSSSLWizardTester running testname " + args[0]);

    MQTLSSSLWizardTester w = new MQTLSSSLWizardTester(args[0]);
  }

  private void runTests(String arg) {
    MQTLSSSLWizardParms200 wp = new MQTLSSSLWizardParms200();
    String t = new String("");

    tname = arg;
    outputfilenum = 0;

    wp.pageSNDRtxtSNDRUser      = "ivans";
    wp.pageSNDRtxtSNDRQmgrName  = "QM1";
    wp.pageSNDRtxtSNDRHost      = "machine1";
    wp.pageRCVRtxtRCVRQmgrName = "QM2";
    wp.pageRCVRtxtRCVRHost     = "machine2";   
    wp.pageRCVRtxtRCVRPort     = "1414";

    try {
      //tfile=new File("C:\\mydocs\\source\\java\\sslwiz\\V2.0.6\\" + tname + ".html");
      tfile=new File(tname + ".html");
      a = new FileWriter(tfile);
      a.write("<html><title><MQTLSSSLWizardTester run " + tname + "><body>");
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    t = t.concat("<table>");
    TestClientServer(wp, t, "Client or Server? Client", CONST_CLIENT);
    TestClientServer(wp, t, "Client or Server? Server", CONST_SERVER);


    try {
      a.write("</body></html>");
      a.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }


  }

  private void TestClientServer(MQTLSSSLWizardParms200 wp, String _t, String title, int p) {
    String t = AddtextTitle(_t, title);

    if (p == CONST_CLIENT) {
      wp.pageSNDRradCS = true;
      wp.pageSNDRCERTtxtSNDRLabel = "ibmwebspheremqivans";
      TestKeyringSNDRJava(wp, t, "Client? Java", CONST_JAVA);
      TestKeyringSNDRJava(wp, t, "Client? Other", CONST_OTHER);
      
    } else {
      wp.pageSNDRradCS = false;
      wp.pageSNDRCERTtxtSNDRLabel = "ibmwebspheremqqm1";
      TestKeyringSNDRPlatform(wp, t, "SPlatform? Windows", CONST_WIN);
      TestKeyringSNDRPlatform(wp, t, "SPlatform? Unix", CONST_UNIX);
      wp.pageSNDRCERTtxtSNDRLabel = "ibmWebsphereMQQM1";
      TestKeyringSNDRPlatform(wp, t, "SPlatform? z/OS", CONST_ZOS);
    }
  }

  private void TestKeyringSNDRJava(MQTLSSSLWizardParms200 wp, String t, String title, int p) {
    AddtextTitle(t, title);

    if (p == CONST_JAVA) {
      wp.pageSNDRradJO = true;
      TestKeyringSNDRPlatform(wp, t, "SPlatform? Windows", CONST_WIN);
      TestKeyringSNDRPlatform(wp, t, "SPlatform? Unix", CONST_UNIX);
    } else {
      wp.pageSNDRradJO = false;
      TestKeyringSNDRPlatform(wp, t, "SPlatform? Windows", CONST_WIN);
      TestKeyringSNDRPlatform(wp, t, "SPlatform? Unix", CONST_UNIX);
    }
  }

  private void TestKeyringSNDRPlatform(MQTLSSSLWizardParms200 wp, String _t, String title, int p) {
    String t = AddtextTitle(_t, title);

    if (p == CONST_WIN) {
      wp.pageSNDRchcSNDRPlatform = PLATFORM_WIN;    
      wp.pageSNDRUNIXWINtxtSNDRKeyringUNIXWIN = "C:\\QMs\\QM1\\QM1.kdb";
      TestKeyringSNDRCommand(wp, t, "SCommand? gsk7cmd", CONST_CMD);
      TestKeyringSNDRCommand(wp, t, "SCommand? gsk7capicmd", CONST_CAPI);
    } else if (p == CONST_UNIX) {
      wp.pageSNDRchcSNDRPlatform = PLATFORM_UNIX;    
      wp.pageSNDRUNIXWINtxtSNDRKeyringUNIXWIN = "/var/qms/qm1/qm1.kdb";
      TestKeyringSNDRCommand(wp, t, "SCommand? gsk7cmd", CONST_CMD);
      TestKeyringSNDRCommand(wp, t, "SCommand? gsk7capicmd", CONST_CAPI);
    } else {
      wp.pageSNDRchcSNDRPlatform = PLATFORM_ZOS;    
      wp.pageSNDRZOStxtSNDRTasks              = "5";
      wp.pageSNDRZOStxtSNDRID                 = "qmuser1";
      wp.pageSNDRZOStxtSNDRKeyringZOS         = "QM1KEYR";
      TestKeyringRCVRPlatform(wp, t, "RPlatform? Windows", CONST_WIN);
      TestKeyringRCVRPlatform(wp, t, "RPlatform? Unix", CONST_UNIX);
      TestKeyringRCVRPlatform(wp, t, "RPlatform? z/OS", CONST_ZOS);
    }
  }

  private void TestKeyringSNDRCommand(MQTLSSSLWizardParms200 wp, String _t, String title, int p) {
    String t = AddtextTitle(_t, title);

    if (p == CONST_CMD) {
      wp.pageSNDRUNIXWINchcSNDRCommand = "gsk7cmd";    
      TestKeyringRCVRPlatform(wp, t, "RPlatform? Windows", CONST_WIN);
      TestKeyringRCVRPlatform(wp, t, "RPlatform? Unix", CONST_UNIX);
      TestKeyringRCVRPlatform(wp, t, "RPlatform? z/OS", CONST_ZOS);
    } else {
      wp.pageSNDRUNIXWINchcSNDRCommand = "gsk7capicmd";   
      wp.pageSNDRCAPIchcSNDRSigalg = "sha1";
      TestKeyringSNDRFips(wp, t, "SFips? Yes", CONST_FIPSYES);
      TestKeyringSNDRFips(wp, t, "SFips? No" , CONST_FIPSNO);
    }
  }

  private void TestKeyringSNDRFips(MQTLSSSLWizardParms200 wp, String _t, String title, int p) {
    String t = AddtextTitle(_t, title);

    if (p == CONST_FIPSYES) {
      wp.pageSNDRCAPIcbxSNDRFips = true;    
      TestKeyringRCVRPlatform(wp, t, "RPlatform? Windows", CONST_WIN);
      TestKeyringRCVRPlatform(wp, t, "RPlatform? Unix", CONST_UNIX);
      TestKeyringRCVRPlatform(wp, t, "RPlatform? z/OS", CONST_ZOS);
    } else {
      wp.pageSNDRCAPIcbxSNDRFips = false;    
      TestKeyringRCVRPlatform(wp, t, "RPlatform? Windows", CONST_WIN);
      TestKeyringRCVRPlatform(wp, t, "RPlatform? Unix", CONST_UNIX);
      TestKeyringRCVRPlatform(wp, t, "RPlatform? z/OS", CONST_ZOS);
    }
  }

  private void TestKeyringRCVRPlatform(MQTLSSSLWizardParms200 wp, String _t, String title, int p) {
    String t = AddtextTitle(_t, title);

    if (p == CONST_WIN) {
      wp.pageRCVRchcRCVRPlatform = PLATFORM_WIN;    
      wp.pageRCVRUNIXWINtxtRCVRKeyringUNIXWIN = "C:\\QMs\\QM2\\QM2.kdb";
      TestKeyringRCVRCommand(wp, t, "RCommand? gsk7cmd", CONST_CMD);
      TestKeyringRCVRCommand(wp, t, "RCommand? gsk7capicmd", CONST_CAPI);
    } else if (p == CONST_UNIX) {
      wp.pageRCVRchcRCVRPlatform = PLATFORM_UNIX;    
      wp.pageRCVRUNIXWINtxtRCVRKeyringUNIXWIN= "/var/qms/qm2/qm2.kdb";
      TestKeyringRCVRCommand(wp, t, "RCommand? gsk7cmd", CONST_CMD);
      TestKeyringRCVRCommand(wp, t, "RCommand? gsk7capicmd", CONST_CAPI);
    } else {
      wp.pageRCVRchcRCVRPlatform = PLATFORM_ZOS;    
      wp.pageRCVRZOStxtRCVRTasks              = "6";
      wp.pageRCVRZOStxtRCVRID                 = "qmuser2";
      wp.pageRCVRZOStxtRCVRKeyringZOS         = "QM2KEYR";
      TestKeyringAuthClient(wp, t, "AuthClient? Yes", CONST_AUTHCLIENT);
      TestKeyringAuthClient(wp, t, "AuthClient? No", CONST_NOAUTHCLIENT);
    }
  }

  private void TestKeyringRCVRCommand(MQTLSSSLWizardParms200 wp, String _t, String title, int p) {
    String t = AddtextTitle(_t, title);

    if (p == CONST_CMD) {
      wp.pageRCVRUNIXWINchcRCVRCommand = "gsk7cmd";    
      TestKeyringAuthClient(wp, t, "AuthClient? Yes", CONST_AUTHCLIENT);
      TestKeyringAuthClient(wp, t, "AuthClient? No", CONST_NOAUTHCLIENT);
    } else {
      wp.pageRCVRUNIXWINchcRCVRCommand = "gsk7capicmd";   
      wp.pageRCVRCAPIchcRCVRSigalg = "sha1";
      TestKeyringRCVRFips(wp, t, "RFips? Yes", CONST_FIPSYES);
      TestKeyringRCVRFips(wp, t, "RFips? No" , CONST_FIPSNO);
    }
  }

  private void TestKeyringRCVRFips(MQTLSSSLWizardParms200 wp, String _t, String title, int p) {
    String t = AddtextTitle(_t, title);

    if (p == CONST_FIPSYES) {
      wp.pageRCVRCAPIcbxRCVRFips = true;    
      TestKeyringAuthClient(wp, t, "AuthClient? Yes", CONST_AUTHCLIENT);
      TestKeyringAuthClient(wp, t, "AuthClient? No", CONST_NOAUTHCLIENT);
    } else {
      wp.pageRCVRCAPIcbxRCVRFips = false;    
      TestKeyringAuthClient(wp, t, "AuthClient? Yes", CONST_AUTHCLIENT);
      TestKeyringAuthClient(wp, t, "AuthClient? No", CONST_NOAUTHCLIENT);
    }
  }

  private void TestKeyringAuthClient(MQTLSSSLWizardParms200 wp, String _t, String title, int p) {
    String t = AddtextTitle(_t, title);

    wp.pageCHLtxtChannel = "CHL1";
    wp.pageCHLchcCipherSpec = "NULL_MD5";

    wp.pageSNDRCERTchcSNDRDNCN = "QM1";
    wp.pageSNDRCERTtxtSNDRDNOU = "FIT";
    wp.pageSNDRCERTtxtSNDRDNO  = "IBM";
    wp.pageSNDRCERTtxtSNDRDNL  = "Hursley";
    wp.pageSNDRCERTtxtSNDRDNST = "Hampshire";
    wp.pageSNDRCERTtxtSNDRDNC  = "England";
    wp.pageSNDRCERTtxtSNDRExpiry  = "365";

    wp.pageRCVRCERTchcRCVRDNCN = "QM2";         
    wp.pageRCVRCERTtxtRCVRDNOU = "Sales";         
    wp.pageRCVRCERTtxtRCVRDNO  = "UsefulCo";        
    wp.pageRCVRCERTtxtRCVRDNL  = "Edinburgh";    
    wp.pageRCVRCERTtxtRCVRDNST = "Edinburghshire";   
    wp.pageRCVRCERTtxtRCVRDNC  = "Scotland";    
    wp.pageRCVRCERTtxtRCVRExpiry  = "365";   

    if (p == CONST_AUTHCLIENT) {
      wp.pageCHLcbxClientAuth = true;
      TestKeyringAuthCA(wp, t, "CA/SelfSigned? CA", CONST_CA);
      TestKeyringAuthCA(wp, t, "CA/SelfSigned? SS", CONST_SS);
    } else { 
      wp.pageCHLcbxClientAuth = false;
      TestKeyringAuthCA(wp, t, "CA/SelfSigned? CA", CONST_CA);
      TestKeyringAuthCA(wp, t, "CA/SelfSigned? SS", CONST_SS);
    }
  }

  private void TestKeyringAuthCA(MQTLSSSLWizardParms200 wp, String _t, String title, int p) {
    String t = AddtextTitle(_t, title);

    if (p == CONST_CA) {
      wp.pageCEAUMODELradType = false;
      TestKeyringAuthCAInt(wp, t, "CA Internal/External? Internal", CONST_CAINT);
      TestKeyringAuthCAInt(wp, t, "CA Internal/External? External", CONST_CAEXT);
    } else {
      wp.pageCEAUMODELradType = true;
      TestEnd(wp, t);      
    }
  }

  private void TestKeyringAuthCAInt(MQTLSSSLWizardParms200 wp, String _t, String title, int p) {
    String t = AddtextTitle(_t, title);

    wp.pageCEAUtxtCEAUHost                  = "machine3";
    wp.pageCEAUCERTtxtCEAULabel              = "WMQCA";
    wp.pageCEAUCERTtxtCEAUDNCN              = "WMQCAName";
    wp.pageCEAUCERTtxtCEAUDNOU              = "Security";
    wp.pageCEAUCERTtxtCEAUDNO               = "SecureCo";
    wp.pageCEAUCERTtxtCEAUDNL               = "London";
    wp.pageCEAUCERTtxtCEAUDNST              = "Greater London";
    wp.pageCEAUCERTtxtCEAUDNC               = "England";
    wp.pageCEAUCERTtxtCEAUExpiry               = "365";

    if (p == CONST_CAINT) {
      wp.pageCEAUWHEREradType = true;
      TestKeyringCAPlatform(wp, t, "CAPlatform? Windows", CONST_WIN);
      TestKeyringCAPlatform(wp, t, "CAPlatform? Unix", CONST_UNIX);
      TestKeyringCAPlatform(wp, t, "CAPlatform? z/OS", CONST_ZOS);
    } else {
      wp.pageCEAUWHEREradType = false;
      TestEnd(wp, t);      
    }
  }

  private void TestKeyringCAPlatform(MQTLSSSLWizardParms200 wp, String _t, String title, int p) {
    String t = AddtextTitle(_t, title);
    System.out.println("00Hello " + p);

    if (p == CONST_WIN) {
      wp.pageCEAUchcCEAUPlatform              = PLATFORM_WIN;
      wp.pageCEAUUNIXWINtxtCEAUKeyringUNIXWIN = "C:\\SSL\\wmqca.kdb";
      TestKeyringCEAUCommand(wp, t, "CACommand? gsk7cmd", CONST_CMD);
      TestKeyringCEAUCommand(wp, t, "CACommand? gsk7capicmd", CONST_CAPI);
    } else if (p == CONST_UNIX) {
      wp.pageCEAUchcCEAUPlatform              = PLATFORM_UNIX;
      wp.pageCEAUUNIXWINtxtCEAUKeyringUNIXWIN = "/var/SSL/wmqca.kdb";
      TestKeyringCEAUCommand(wp, t, "CACommand? gsk7cmd", CONST_CMD);
      TestKeyringCEAUCommand(wp, t, "CACommand? gsk7capicmd", CONST_CAPI);
    } else {
      System.out.println("01Hello " + p);
      wp.pageCEAUchcCEAUPlatform              = PLATFORM_ZOS;
      wp.pageCEAUZOStxtCEAUID                 = "RACFUSR";
      wp.pageCEAUZOStxtCEAUKeyringZOS         = "CAKEYR";
      TestEnd(wp, t);      
    }
  }

  private void TestKeyringCEAUCommand(MQTLSSSLWizardParms200 wp, String _t, String title, int p) {
    String t = AddtextTitle(_t, title);

    if (p == CONST_CMD) {
      wp.pageCEAUUNIXWINchcCEAUCommand = "gsk7cmd";    
      TestEnd(wp, t);      
    } else {
      wp.pageCEAUUNIXWINchcCEAUCommand = "gsk7capicmd";   
      wp.pageCEAUCAPIchcCEAUSigalg = "sha1";
      TestKeyringCEAUFips(wp, t, "CAFips? Yes", CONST_FIPSYES);
      TestKeyringCEAUFips(wp, t, "CAFips? No" , CONST_FIPSNO);
    }
  }

  private void TestKeyringCEAUFips(MQTLSSSLWizardParms200 wp, String _t, String title, int p) {
    String t = AddtextTitle(_t, title);
    
    if (p == CONST_FIPSYES) {
      wp.pageRCVRCAPIcbxRCVRFips = true;    
      TestEnd(wp, t);      
    } else {
      wp.pageRCVRCAPIcbxRCVRFips = false;    
      TestEnd(wp, t);      
    }
  }

  private void TestEnd(MQTLSSSLWizardParms200 _wp, String _t) {
    outputfilenum++;

    MQTLSSSLWizardParms200 wp = calcCertNames(_wp);

    try {
      //String filename = new String("C:\\mydocs\\source\\java\\sslwiz\\V2.0.6\\" + tname + "_" + outputfilenum + ".html");
      String filename = new String(tname + "_" + outputfilenum + ".html");
      System.out.println ("File start " + filename);
      File tfile2=new File(filename);
      FileWriter a2 = new FileWriter(tfile2); 
      a2.write("<html><title><MQTLSSSLWizardTester test " + tname + "_" + outputfilenum + "><body>");
      a2.write("<p><a href=" + tname + ".html>" + tname + ".html</a></p>");


      a2.write("<h3>Test parms</h3>");
      a2.write("<table>");
      a2.write(_t);
      a2.write("</table>");


      a2.write("<h3>MQTLSSSLWizardParms200</h3>");
      a2.write("<table>");
      a2.write("<tr><td>pageSNDRradCS                          = " + wp.pageSNDRradCS                                  + "</td></tr>");
      a2.write("<tr><td>pageSNDRradJO                          = " + wp.pageSNDRradJO                                  + "</td></tr>");
      a2.write("<tr><td>pageSNDRtxtSNDRUser                    = " + wp.pageSNDRtxtSNDRUser                            + "</td></tr>");
      a2.write("<tr><td>pageSNDRtxtSNDRQmgrName                = " + wp.pageSNDRtxtSNDRQmgrName                        + "</td></tr>");
      a2.write("<tr><td>pageSNDRtxtSNDRHost                    = " + wp.pageSNDRtxtSNDRHost                            + "</td></tr>");
      a2.write("<tr><td>pageSNDRchcSNDRPlatform                = " + wp.pageSNDRchcSNDRPlatform                        + "</td></tr>");
      a2.write("<tr><td>pageSNDRZOStxtSNDRTasks                = " + wp.pageSNDRZOStxtSNDRTasks                        + "</td></tr>");
      a2.write("<tr><td>pageSNDRZOStxtSNDRID                   = " + wp.pageSNDRZOStxtSNDRID                           + "</td></tr>");
      a2.write("<tr><td>pageSNDRZOStxtSNDRKeyringZOS           = " + wp.pageSNDRZOStxtSNDRKeyringZOS                   + "</td></tr>");
      a2.write("<tr><td>pageSNDRUNIXWINtxtSNDRKeyringUNIXWIN   = " + wp.pageSNDRUNIXWINtxtSNDRKeyringUNIXWIN           + "</td></tr>");
      a2.write("<tr><td>pageRCVRtxtRCVRQmgrName                = " + wp.pageRCVRtxtRCVRQmgrName                        + "</td></tr>");
      a2.write("<tr><td>pageRCVRtxtRCVRHost                    = " + wp.pageRCVRtxtRCVRHost                            + "</td></tr>");
      a2.write("<tr><td>pageRCVRtxtRCVRPort                    = " + wp.pageRCVRtxtRCVRPort                            + "</td></tr>");
      a2.write("<tr><td>pageRCVRchcRCVRPlatform                = " + wp.pageRCVRchcRCVRPlatform                        + "</td></tr>");
      a2.write("<tr><td>pageRCVRZOStxtRCVRTasks                = " + wp.pageRCVRZOStxtRCVRTasks                        + "</td></tr>");
      a2.write("<tr><td>pageRCVRZOStxtRCVRID                   = " + wp.pageRCVRZOStxtRCVRID                           + "</td></tr>");
      a2.write("<tr><td>pageRCVRZOStxtRCVRKeyringZOS           = " + wp.pageRCVRZOStxtRCVRKeyringZOS                   + "</td></tr>");
      a2.write("<tr><td>pageRCVRUNIXWINtxtRCVRKeyringUNIXWIN   = " + wp.pageRCVRUNIXWINtxtRCVRKeyringUNIXWIN           + "</td></tr>");
      a2.write("<tr><td>pageCHLtxtChannel                      = " + wp.pageCHLtxtChannel                              + "</td></tr>");
      a2.write("<tr><td>pageCHLchcCipherSpec                   = " + wp.pageCHLchcCipherSpec                           + "</td></tr>");
      a2.write("<tr><td>pageCHLcbxClientAuth                   = " + wp.pageCHLcbxClientAuth                           + "</td></tr>");
      a2.write("<tr><td>pageSNDRCERTtxtSNDRLabel               = " + wp.pageSNDRCERTtxtSNDRLabel                       + "</td></tr>");
      a2.write("<tr><td>pageSNDRCERTchcSNDRDNCN                = " + wp.pageSNDRCERTchcSNDRDNCN                        + "</td></tr>");
      a2.write("<tr><td>pageSNDRCERTtxtSNDRDNOU                = " + wp.pageSNDRCERTtxtSNDRDNOU                        + "</td></tr>");
      a2.write("<tr><td>pageSNDRCERTtxtSNDRDNO                 = " + wp.pageSNDRCERTtxtSNDRDNO                         + "</td></tr>");
      a2.write("<tr><td>pageSNDRCERTtxtSNDRDNL                 = " + wp.pageSNDRCERTtxtSNDRDNL                         + "</td></tr>");
      a2.write("<tr><td>pageSNDRCERTtxtSNDRDNST                = " + wp.pageSNDRCERTtxtSNDRDNST                        + "</td></tr>");
      a2.write("<tr><td>pageSNDRCERTtxtSNDRDNC                 = " + wp.pageSNDRCERTtxtSNDRDNC                         + "</td></tr>");
      a2.write("<tr><td>pageSNDRCERTtxtSNDRExpiry              = " + wp.pageSNDRCERTtxtSNDRExpiry                      + "</td></tr>");
      a2.write("<tr><td>pageRCVRCERTtxtRCVRLabel               = " + wp.pageRCVRCERTtxtRCVRLabel                       + "</td></tr>");
      a2.write("<tr><td>pageRCVRCERTchcRCVRDNCN                = " + wp.pageRCVRCERTchcRCVRDNCN                        + "</td></tr>");
      a2.write("<tr><td>pageRCVRCERTtxtRCVRDNOU                = " + wp.pageRCVRCERTtxtRCVRDNOU                        + "</td></tr>");
      a2.write("<tr><td>pageRCVRCERTtxtRCVRDNO                 = " + wp.pageRCVRCERTtxtRCVRDNO                         + "</td></tr>");
      a2.write("<tr><td>pageRCVRCERTtxtRCVRDNL                 = " + wp.pageRCVRCERTtxtRCVRDNL                         + "</td></tr>");
      a2.write("<tr><td>pageRCVRCERTtxtRCVRDNST                = " + wp.pageRCVRCERTtxtRCVRDNST                        + "</td></tr>");
      a2.write("<tr><td>pageRCVRCERTtxtRCVRDNC                 = " + wp.pageRCVRCERTtxtRCVRDNC                         + "</td></tr>");
      a2.write("<tr><td>pageRCVRCERTtxtRCVRExpiry              = " + wp.pageRCVRCERTtxtRCVRExpiry                      + "</td></tr>");
      a2.write("<tr><td>pageCEAUMODELradType                   = " + wp.pageCEAUMODELradType                           + "</td></tr>");
      a2.write("<tr><td>pageCEAUWHEREradType                   = " + wp.pageCEAUWHEREradType                           + "</td></tr>");
      a2.write("<tr><td>pageCEAUtxtCEAUHost                    = " + wp.pageCEAUtxtCEAUHost                            + "</td></tr>");
      a2.write("<tr><td>pageCEAUchcCEAUPlatform                = " + wp.pageCEAUchcCEAUPlatform                        + "</td></tr>");
      a2.write("<tr><td>pageCEAUZOStxtCEAUID                   = " + wp.pageCEAUZOStxtCEAUID                           + "</td></tr>");
      a2.write("<tr><td>pageCEAUZOStxtCEAUKeyringZOS           = " + wp.pageCEAUZOStxtCEAUKeyringZOS                   + "</td></tr>");
      a2.write("<tr><td>pageCEAUUNIXWINtxtCEAUKeyringUNIXWIN   = " + wp.pageCEAUUNIXWINtxtCEAUKeyringUNIXWIN           + "</td></tr>");
      a2.write("<tr><td>pageCEAUCERTtxtCEAULabel               = " + wp.pageCEAUCERTtxtCEAULabel                       + "</td></tr>");
      a2.write("<tr><td>pageCEAUCERTtxtCEAUDNCN                = " + wp.pageCEAUCERTtxtCEAUDNCN                        + "</td></tr>");
      a2.write("<tr><td>pageCEAUCERTtxtCEAUDNOU                = " + wp.pageCEAUCERTtxtCEAUDNOU                        + "</td></tr>");
      a2.write("<tr><td>pageCEAUCERTtxtCEAUDNO                 = " + wp.pageCEAUCERTtxtCEAUDNO                         + "</td></tr>");
      a2.write("<tr><td>pageCEAUCERTtxtCEAUDNL                 = " + wp.pageCEAUCERTtxtCEAUDNL                         + "</td></tr>");
      a2.write("<tr><td>pageCEAUCERTtxtCEAUDNST                = " + wp.pageCEAUCERTtxtCEAUDNST                        + "</td></tr>");
      a2.write("<tr><td>pageCEAUCERTtxtCEAUDNC                 = " + wp.pageCEAUCERTtxtCEAUDNC                         + "</td></tr>");
      a2.write("<tr><td>pageCEAUCERTtxtCEAUExpiry              = " + wp.pageCEAUCERTtxtCEAUExpiry                      + "</td></tr>");
      a2.write("</table>");

      a2.write("<h3>Test Output</h3>");
      a2.write("<p>");
      String ins = new String();
      ins = wp.generateIntructions();
      a2.write(ins);
      a2.write("</p>");


      a2.write("</body></html>");
      a2.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    
    String t = _t.concat("<a href=" + tname + "_" + outputfilenum + ".html>" + tname + "_" + outputfilenum + ".html</A>");
    t = t.concat("</table>");

    try {
      a.write(t);
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println ("File end");
    System.out.println ("");
  }

  private MQTLSSSLWizardParms200 calcCertNames(MQTLSSSLWizardParms200 wp) {
    // Sender cert label
    if (wp.pageSNDRradCS == true) {
      wp.pageSNDRCERTtxtSNDRLabel  = "ibmwebspheremq" + wp.pageSNDRtxtSNDRUser.toLowerCase();
    }
    else {
      if (wp.pageSNDRchcSNDRPlatform.equals(PLATFORM_ZOS)) {
        wp.pageSNDRCERTtxtSNDRLabel = "ibmWebSphereMQ" + wp.pageSNDRtxtSNDRQmgrName.toUpperCase();
      }
      else {
        wp.pageSNDRCERTtxtSNDRLabel = "ibmwebspheremq" + wp.pageSNDRtxtSNDRQmgrName .toLowerCase();
      }
    }

    // Receiver cert label
    if (wp.pageRCVRchcRCVRPlatform.equals(PLATFORM_ZOS)) {
      wp.pageRCVRCERTtxtRCVRLabel  = "ibmWebSphereMQ" + wp.pageRCVRtxtRCVRQmgrName .toUpperCase();
    }
    else {
      wp.pageRCVRCERTtxtRCVRLabel  = "ibmwebspheremq" + wp.pageRCVRtxtRCVRQmgrName .toLowerCase();
    }

    return wp;
  }

  private String AddtextTitle(String t, String title) {
    return t.concat("<tr><td>" + title + "</td></tr>");
  }

}

