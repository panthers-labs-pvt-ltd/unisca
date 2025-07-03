package org.pantherslabs.chimera.unisca.pipeline_metadata_api.controller;

import org.pantherslabs.chimera.unisca.pipeline_metadata_api.dto.ExtractView;
import org.pantherslabs.chimera.unisca.logging.ChimeraLogger;
import org.pantherslabs.chimera.unisca.logging.ChimeraLoggerFactory;
import org.pantherslabs.chimera.unisca.pipeline_metadata_api.service.ExtractViewService;
import java.util.List;

import javax.annotation.CheckForNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/extractView")
public class ExtractViewController {

    private static final ChimeraLogger logger = ChimeraLoggerFactory.getLogger(ExtractMetadataConfigController.class);
    private final ExtractViewService extractViewService;

    @Autowired
    public ExtractViewController(ExtractViewService extractViewService) {
        this.extractViewService = extractViewService;
    }

    // GET request - Retrieve all data source connections
    @GetMapping
    public ResponseEntity<List<ExtractView>> getAllExtractView() {
        logger.logInfo("Fetching all extract metadata.");
        return ResponseEntity.ok(extractViewService.getExtractView());
    }

    // GET request - Retrieve Extract Metadata Config By Pipeline Name
    @CheckForNull
    @GetMapping("/{pipelineName}")
    public ResponseEntity<List<ExtractView>> getExtractViewByName(@PathVariable("pipelineName") String name) {
        logger.logInfo("Fetching Extract Config for pipeline: " + name);
        return ResponseEntity.ok(extractViewService.getExtractViewByPipelineName(name));
    }
}
