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

package tlswizard.samples;

import com.ibm.mq.*; //Include the WebSphere MQ classes for Java package
import com.ibm.mq.constants.MQConstants; 

public class SSLSample {

  // define the parms
  private static String conname ;
  private static String port    ;
  private static String channel ;
  private static String qmgr    ;
  private static String sslciph ;
  private static String sslkeyr ;
  private static String sslpass ;
  
  public static void main(String args[]) {
    /****************************************************************/
    /* Check for correct number of arguments                        */
    /****************************************************************/
    if (args.length == 7) {
      conname = args[0];
      port    = args[1];
      channel = args[2];
      qmgr    = args[3];
      sslciph = args[4];
      sslkeyr = args[5];
      sslpass = args[6];
    }
    else {
      System.out.println("Usage parms: Conname Port Channel Qmgr SSLCiph SSLStore SSLKeyStorePassword");
      System.out.println("     NOTE - SSLStore is the name of a single store, which is both the keystore and truststore.");
      return;
    }
  
    new SSLSample().runSample();     
  }

  public void runSample() {
    //System.setProperty("javax.net.debug", "true");

    /****************************************************************/
    /* Utilise the arguments                                        */
    /****************************************************************/
    System.setProperty("javax.net.ssl.trustStore", sslkeyr );
    System.setProperty("javax.net.ssl.keyStore", sslkeyr );
    System.setProperty("javax.net.ssl.keyStorePassword", sslpass );
    MQEnvironment.hostname       = conname;
    MQEnvironment.port           = Integer.parseInt(port);
    MQEnvironment.channel        = channel;
    MQEnvironment.properties.put(MQConstants.SSL_CIPHER_SUITE_PROPERTY,sslciph); 

    /****************************************************************/
    /* Print out parms                                              */
    /****************************************************************/
    System.out.println("Connecting to:");
    System.out.println("  Conname = " + MQEnvironment.hostname);
    System.out.println("  Port = " + MQEnvironment.port);
    System.out.println("  Channel = " + MQEnvironment.channel);
    System.out.println("  Qmgr = " + qmgr);
    System.out.println("  SSLCiph = "+ MQEnvironment.properties.get(MQConstants.SSL_CIPHER_SUITE_PROPERTY));
    System.out.println("  SSLTrustStore = "+ System.getProperty("javax.net.ssl.trustStore"));
    System.out.println("  SSLKeyStore = "+ System.getProperty("javax.net.ssl.keyStore"));
    System.out.println("  SSLKeyStorePassword = "+ System.getProperty("javax.net.ssl.keyStorePassword"));

    try {

      /**************************************************************/
      /* Connect to queue manager                                   */
      /**************************************************************/
      System.out.println("Connecting...");
      MQQueueManager qMgr = new MQQueueManager(qmgr);
      System.out.println("Connection successful!");

      /**************************************************************/
      /* Disconnect from queue manager                              */
      /**************************************************************/
      System.out.println("Disconnecting from the Queue Manager");
      qMgr.disconnect();
      System.out.println("Done!");
    }
    catch (MQException ex) {
      System.out.println("A WebSphere MQ Error occured : Completion Code "
                + ex.completionCode + " Reason Code " + ex.reasonCode);
    }
  }
} 