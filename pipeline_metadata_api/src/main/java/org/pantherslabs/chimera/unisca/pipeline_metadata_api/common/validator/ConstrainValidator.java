package org.pantherslabs.chimera.unisca.pipeline_metadata_api.common.validator;

import org.pantherslabs.chimera.unisca.pipeline_metadata_api.common.exception.FailedValidationException;
import org.pantherslabs.chimera.unisca.pipeline_metadata_api.common.exception.UserException;

public interface ConstrainValidator<T> {

  void validate(T obj) throws FailedValidationException, UserException;
}