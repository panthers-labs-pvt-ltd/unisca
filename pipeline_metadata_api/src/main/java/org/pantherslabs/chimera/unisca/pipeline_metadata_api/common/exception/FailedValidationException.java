package org.pantherslabs.chimera.unisca.pipeline_metadata_api.common.exception;

import org.pantherslabs.chimera.unisca.pipeline_metadata_api.common.dto.ExceptionMessage;
import org.pantherslabs.chimera.unisca.pipeline_metadata_api.common.dto.GenericResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Exception to indicate validation failures.
 */
@Getter
public class FailedValidationException extends RuntimeException {

  private final GenericResponse error;
  private final HttpStatus status;

  /**
   * Constructor.
   *
   * @param exceptionMessage
   * @param status
   * @param error
   */
  public FailedValidationException(
      final ExceptionMessage exceptionMessage,
      final HttpStatus status,
      final GenericResponse error) {
    super(exceptionMessage.getDescription());
    this.status = status;
    this.error = error;
  }

}
