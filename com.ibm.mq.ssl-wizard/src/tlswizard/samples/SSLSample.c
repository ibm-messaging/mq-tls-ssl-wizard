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
 
 /********************************************************************/
 /*                                                                  */
 /* Program name: SSLSample                                          */
 /*                                                                  */
 /* Description: Sample C program that demonstrates how to specify   */
 /*              SSL client connection information on MQCONNX.       */
 /*                                                                  */
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
 /*   SSLSample is a sample C program that demonstrates how to       */
 /*   supply SSL information for a client connection on the MQCONNX  */
 /*   call.                                                          */
 /*                                                                  */
 /*   The sample simply connects to the queue manager using MQCONNX  */
 /*   and then disconnects using MQDISC.                             */
 /*                                                                  */
 /*   This program is intended to be linked as an MQI client         */
 /*   application.                                                   */
 /*                                                                  */
 /********************************************************************/
 /*                                                                  */
 /* Usage:                                                           */
 /*                                                                  */
 /*   SSLSample has 5 parameters, all of which are mandatory:        */
 /*                                                                  */
 /*     SSLSample Conname SvrconnChannelName QMgrName SSLCiph SSLKeyr*/
 /*                                                                  */
 /*   The parameters are:                                            */
 /*                                                                  */
 /*     Conname     - the connection name of the server queue        */
 /*                   manager in the same format as the CONNAME      */
 /*                   parameter on the MQSC DEFINE CHANNEL command.  */
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
 /*     SSLKeyr     - the SSL key repository.                        */
 /*                                                                  */
 /*   For example:                                                   */
 /*                                                                  */
 /*     SSLSample myhost(1414) SVRCONN1 QM1 NULL_MD5                 */
 /*                                          C:\mq\ssl\client.kdb    */
 /*                                                                  */
 /********************************************************************/
 #include <stdio.h>
 #include <stdlib.h>
 #include <ctype.h>
 #include <string.h>
 #include <cmqc.h>                  /* For regular MQI definitions   */
 #include <cmqxc.h>                 /* For MQCD definition           */

 int main(int argc, char **argv)
 {
   MQSCO    SSL_options = {MQSCO_DEFAULT};
   MQCNO    Connect_options = {MQCNO_DEFAULT};
   MQCD     ClientConn = {MQCD_CLIENT_CONN_DEFAULT};
   MQOD     od = {MQOD_DEFAULT};    
   MQCHAR   QMName[MQ_Q_MGR_NAME_LENGTH]; /* name of connection qmgr */
   MQHCONN  Hcon;                         /* connection handle       */ 
   MQLONG   CompCode;                     /* completion code         */ 
   MQLONG   Reason;                       /* reason code             */ 

   /******************************************************************/
   /* Check for correct number of arguments                          */
   /******************************************************************/
   if (argc != 6)
   {
     printf("Usage:\n");
     printf("\t%s ConnName SvrconnChannelName QMgrName SSLCiph SSLKeyr\n",
            argv[0]);
     exit(99);
   }

   /******************************************************************/
   /* Print out arguments                                            */
   /******************************************************************/
   printf("Connecting to:\n");
   printf("  Conname = %s.\n", argv[1]);
   printf("  SvrconnChannelName = %s.\n", argv[2]);
   printf("  QMgrName = %s.\n", argv[3]);
   printf("  SSLCiph = %s.\n", argv[4]);
   printf("  SSLKeyr = %s.\n", argv[5]);

   /******************************************************************/
   /* Utilise the arguments                                          */
   /******************************************************************/
   strcpy(ClientConn.ConnectionName, argv[1]);
   strcpy(ClientConn.ChannelName, argv[2]);
   strcpy(QMName, argv[3]);
   memcpy(ClientConn.SSLCipherSpec,argv[4],strlen(argv[4]));
   strcpy(SSL_options.KeyRepository, argv[5]);
   ClientConn.Version = MQCD_CURRENT_VERSION;
   Connect_options.ClientConnPtr = &ClientConn;
   Connect_options.SSLConfigPtr = &SSL_options;
   Connect_options.Version = MQCNO_VERSION_4;

   /******************************************************************/
   /* Connect to queue manager                                       */
   /******************************************************************/
   MQCONNX(QMName,                 /* queue manager                  */
           &Connect_options,       /* options for connection         */
           &Hcon,                  /* connection handle              */
           &CompCode,              /* completion code                */
           &Reason);               /* reason code                    */

   if (CompCode == MQCC_FAILED)
   {
     /* Report reason and stop if it failed                          */
     printf("MQCONNX ended with reason code %d\n", Reason);
     exit( (int)Reason );
   }
   printf("Connection Successful!.\n");

   /******************************************************************/
   /* Disconnect from queue manager                                  */
   /******************************************************************/
   MQDISC(&Hcon,                   /* connection handle              */
          &CompCode,               /* completion code                */
          &Reason);                /* reason code                    */

   if (Reason != MQRC_NONE)
   {
     /* Report reason if there is one                                */
     printf("MQDISC ended with reason code %d\n", Reason);
   }

   /******************************************************************/
   /* End of SSLSample                                               */
   /******************************************************************/
   printf("SSLSample end\n");
   return(0);
 }



