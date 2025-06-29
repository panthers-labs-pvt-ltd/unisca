package org.pantherslabs.chimera.unisca.pipeline_metadata_api.common.exception;

import java.io.Serial;

/**
 * The Class DatabaseException.
 */
public class DatabaseException extends Exception {

  /**
   * The Constant serialVersionUID.
   */
  @Serial
  private static final long serialVersionUID = 23489572345789L;

  /**
   * Constructor.
   *
   * @param message Message
   * @param e       Cause
   */
  public DatabaseException(String message, Throwable e) {
    super(message, e);
  }
}
