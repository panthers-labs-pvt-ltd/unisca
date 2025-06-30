package org.pantherslabs.chimera.unisca.pipeline_metadata_api.service;

import org.pantherslabs.chimera.unisca.logging.ChimeraLogger;
import org.pantherslabs.chimera.unisca.logging.ChimeraLoggerFactory;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalService {

  private static final ChimeraLogger logger = ChimeraLoggerFactory.getLogger(ExternalService.class);

  private final RestTemplate restTemplate;

  public ExternalService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @CircuitBreaker(name = "externalService", fallbackMethod = "fallbackForCircuitBreaker")
  @RateLimiter(name = "externalService")
  public String callExternalApi() {
    logger.logInfo("Calling external API...");
    String response = restTemplate.getForObject("https://dummyapi.online/api/movies", String.class);
    logger.logInfo("Response: {}" + response);
    return response;
  }

  public String fallbackForCircuitBreaker(Exception e) {
    logger.logInfo("Fallback executed due to: {}" + e.getMessage());
    return "We need to implement fallback";
  }
}
