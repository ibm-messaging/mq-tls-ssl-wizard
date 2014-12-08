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
/********************************************************************/
/*                                                                  */
/* Program name: SSLSampleJMS                                       */
/*                                                                  */
/* Description: Sample JMS program that demonstrates how to         */
/*              specify SSL client connection information for a     */
/*              MQQueueConnectionFactory connection.                */
/*                                                                  */
/* <START_COPYRIGHT>                                                */
/* Licensed Materials - Property of IBM                             */
/*                                                                  */
/* (C) Copyright IBM Corp. 2006, 2009 All Rights Reserved.          */
/*                                                                  */
/* US Government Users Restricted Rights - Use, duplication or      */
/* disclosure restricted by GSA ADP Schedule Contract with          */
/* IBM Corp.                                                        */
/* <END_COPYRIGHT>                                                  */
/*                                                                  */
/********************************************************************/
/*                                                                  */
/* Overview:                                                        */
/*                                                                  */
/*   This sample is provided with WebSphere MQ SupportPac MO04 -    */
/*   WebSphere MQ SSL Wizard. The wizard will generate command line */
/*   options to be used with this program.                          */
/*                                                                  */
/*   It is assumed that the SSL server connection channel and other */
/*   SSL administration, as instructed by the wizard, has been      */
/*   completed before running this program.                         */
/*                                                                  */
/*   If the SSL connection is successful the program should output: */
/*                                                                  */
/*      "Connection Successful!"                                    */
/*                                                                  */
/********************************************************************/
/*                                                                  */
/* Function:                                                        */
/*                                                                  */
/*   SSLSampleJMS is a sample Java program that demonstrates how to */
/*   supply SSL information for a client connection on a            */
/*   MQQueueConnectionFactory connection.                           */
/*                                                                  */
/*   The sample simply connects to the queue manager.               */
/*                                                                  */
/********************************************************************/
/*                                                                  */
/* Usage:                                                           */
/*                                                                  */
/*   SSLSampleJMS has 7 parameters, all of which are mandatory:     */
/*                                                                  */
/*     java SSLSampleJMS Conname Port SvrconnChannelName            */
/*             QMgrName SSLCiph SSLKeyr SSLKeyrPassword             */
/*                                                                  */
/*   The parameters are:                                            */
/*                                                                  */
/*     Conname     - the connection name of the server queue        */
/*                   manager in the same format as the CONNAME      */
/*                   parameter on the MQSC DEFINE CHANNEL command,  */
/*                   but without the port specified.                */
/*                                                                  */
/*     Port        - the connection port of the server queue        */
/*                   manager.                                       */
/*                                                                  */
/*     SvrconnChannelName                                           */
/*                 - the name of the server connection channel      */
/*                   on the server queue manager with which the     */
/*                   sample program will try to connect.            */
/*                                                                  */
/*     QMgrName    - the name of the server queue manager.          */
/*                                                                  */
/*     SSLCiph     - the SSL CipherSpec.                            */
/*                                                                  */
/*     SSLKeyr     - the name of a single store, which is both the  */
/*                   keystore and truststore.                       */
/*                                                                  */
/*     SSLKeyrPassword                                              */
/*                 - the SSL key repository password.               */
/*                                                                  */
/*   For example:                                                   */
/*                                                                  */
/*     java SSLSampleJMS myhost1 1414 SSL.SVRCONN QM1               */
/*                           NULL_MD5 C:\mq\ssl\client.kdb password */
/*                                                                  */
/********************************************************************/
import javax.jms.QueueConnection;
import javax.jms.QueueSession;
import javax.jms.Session;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.common.CommonConstants;

public class SSLSampleJMS {
  private static String conname ;
  private static String port    ;
  private static String channel ;
  private static String qmgr    ;
  private static String sslciph ;
  private static String sslkeyr ;
  private static String sslpass ;
  private  MQQueueConnectionFactory qcf;
  private  QueueConnection queueCon;
  private QueueSession queueSession;
  
  public static void main(String args[]) {
    /**************************************************************/
    /* Check for correct number of arguments                      */
    /**************************************************************/
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
  
    new SSLSampleJMS().runSample();     
  }

  public void runSample() {
    //System.setProperty("javax.net.debug", "true");

    /****************************************************************/
    /* Utilise the arguments                                        */
    /****************************************************************/
    System.setProperty("javax.net.ssl.trustStore", sslkeyr );
	 	System.setProperty("javax.net.ssl.keyStore", sslkeyr );
	 	System.setProperty("javax.net.ssl.keyStorePassword", sslpass );

    try {
      /**************************************************************/
      /* Utilise the arguments                                      */
      /**************************************************************/
      qcf = new MQQueueConnectionFactory();
      qcf.setHostName(conname);
      qcf.setPort(Integer.parseInt(port));
      qcf.setQueueManager(qmgr);
      qcf.setChannel(channel);
      qcf.setTransportType(CommonConstants.WMQ_CM_CLIENT);
      qcf.setSSLCipherSuite(sslciph);
      
      /**************************************************************/
      /* Print out parms                                            */
      /**************************************************************/
      System.out.println("Connecting to:");
      System.out.println("  Conname = " + qcf.getHostName());
      System.out.println("  Port = " + qcf.getPort());
      System.out.println("  Channel = " + qcf.getChannel());
      System.out.println("  Qmgr = " + qcf.getQueueManager());
      System.out.println("  SSLCiph = "+ qcf.getSSLCipherSuite());
      System.out.println("  SSLTrustStore = "+ System.getProperty("javax.net.ssl.trustStore"));
      System.out.println("  SSLKeyStore = "+ System.getProperty("javax.net.ssl.keyStore"));
      System.out.println("  SSLKeyStorePassword = "+ System.getProperty("javax.net.ssl.keyStorePassword"));
      
      /**************************************************************/
      /* Connect to queue manager                                   */
      /**************************************************************/
      queueCon = qcf.createQueueConnection();
      queueSession = queueCon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
      System.out.println("Connection Successful!"     );
      
    } catch(Exception e){
      e.printStackTrace();
    }
  }
}









