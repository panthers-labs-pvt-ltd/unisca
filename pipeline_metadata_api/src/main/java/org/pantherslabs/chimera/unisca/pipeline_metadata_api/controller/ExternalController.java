package org.pantherslabs.chimera.unisca.pipeline_metadata_api.controller;

import org.pantherslabs.chimera.unisca.pipeline_metadata_api.service.ExternalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExternalController {

  private final ExternalService externalService;

  public ExternalController(ExternalService externalService) {
    this.externalService = externalService;
  }

  @GetMapping("/fetch-data")
  public String fetchData() {
    return externalService.callExternalApi();
  }
}
