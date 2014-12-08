
Abstract
========
The IBM MQ TLS/SSL Wizard is a utility designed to ease the administration 
of a basic TLS/SSL channel.

Description
===========
The MQ TLS/SSL Wizard takes input in series of entry panels and then generates
a set of instructions to enable the user to define and start a TLS/SSL channel. 
The instructions generated include both the platform specific commands for creating
the certificates (e.g. using RACF or GSKit) and the MQSC commands used to define 
and start the MQ channel.

The MQ channel can either be an MCA channel between two queue managers or a client 
connection channel to a queue manager. Sample source and binaries are included 
for clients written in C, Java and JMS.

The Wizard generates instructions for z/OS, UNIX and Windows, although some 
instructions (e.g. MQSC) may be run on other platforms.

Possible Uses
=============
The Wizard can be used as a learning tool for users new to IBM MQ TLS/SSL 
or as an administration assist tool for more advanced users.

Possible enhancements
=====================
This first release to GitHub has not been functionally enhanced from its 2010
version. This means that there are several changes that ought to be handled. 
These include:
* Use the version-independent command for gskit operations (runmqakm) instead of gsk7cmd
* Support the IBM MQ V8 option to name the certificate label
* Update the list of CipherSuites and CipherSpecs for different environments
* Recognise that MQ may not be installed in fixed locations even on Unix systems   

History
=======
Original author: Ian Vanstone, IBM Hursley
First released as SupportPac MO04: 02 Sep 2005
Last updated in SupportPac: 04 Jan 2010
Released to GitHub: Dec 2014
