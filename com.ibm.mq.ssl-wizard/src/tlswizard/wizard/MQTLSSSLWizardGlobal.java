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

/**
 * @author metaylor
 * 
 */
public class MQTLSSSLWizardGlobal implements Serializable {
  

  /****************************************************************/
  /* Platform constants */
  /****************************************************************/
  public static final String PLATFORM_UNIX = "UNIX";
  public static final String PLATFORM_ZOS = "z/OS";
  public static final String PLATFORM_WIN = "Windows";

  /****************************************************************/
  /* Command constants */
  /****************************************************************/
  public static final String COMMAND_CAPI = "gsk7capicmd";
  public static final String COMMAND_UNIX = "gsk7cmd";
  public static final String COMMAND_WIN = "runmqckm";

  /****************************************************************/
  /* Sigalg constants */
  /****************************************************************/
  public static final String SIGALG_SHA1 = "sha1";
  public static final String SIGALG_SHA224 = "sha224";
  public static final String SIGALG_SHA256 = "sha256";
  public static final String SIGALG_SHA384 = "sha384";
  public static final String SIGALG_SHA512 = "sha512";
}
