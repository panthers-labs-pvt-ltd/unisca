package org.pantherslabs.chimera.unisca.pipeline_metadata_api.controller;

public class PipelineControllerTest {

 /*   @Mock
    private PipelineService pipelineService;

    @InjectMocks
    private PipelineController pipelineController;

    @BeforeEach
    void setUp() {
        // Initialize mock objects and the controller
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePipeline() {
        // Given: Pipeline details
        Pipeline pipeline = new Pipeline(
                0, // ID will be generated by service
                "Daily Pipeline",
                LocalDateTime.now(),
                LocalDateTime.now(),
                "Daily",
                "08:00 AM"
        );

        // When: Create pipeline
        // Mock the service call (we don't expect a return value)
        doNothing().when(pipelineService).insertPipeline(pipeline);

        ResponseEntity<String> response = pipelineController.createPipeline(pipeline);

        // Then: Verify response status and body
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Pipeline created successfully with ID: 0", response.getBody());

        // Verify that the insertPipeline method was called with the correct pipeline
        verify(pipelineService, times(1)).insertPipeline(pipeline);
    }

    @Test
    void testGetAllPipelines() {
        // Given: A list of pipelines
        Pipeline pipeline1 = new Pipeline(1, "Pipeline 1", LocalDateTime.now(), LocalDateTime.now(), "Weekly", "10:00 AM");
        Pipeline pipeline2 = new Pipeline(2, "Pipeline 2", LocalDateTime.now(), LocalDateTime.now(), "Monthly", "09:00 AM");
        List<Pipeline> pipelines = Arrays.asList(pipeline1, pipeline2);

        // When: Retrieve all pipelines
        when(pipelineService.getAllPipelines()).thenReturn(pipelines);

        ResponseEntity<List<Pipeline>> response = pipelineController.getAllPipelines();

        // Then: Verify response status, body, and pipeline list size
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetPipelineById() {
        // Given: A pipeline with ID 1
        Pipeline pipeline = new Pipeline(1, "Pipeline 1", LocalDateTime.now(), LocalDateTime.now(), "Weekly", "10:00 AM");

        // When: Retrieve pipeline by ID
        when(pipelineService.getAllPipelines()).thenReturn(List.of(pipeline));

        ResponseEntity<Pipeline> response = pipelineController.getPipelineById(1);

        // Then: Verify response status and the pipeline details
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Pipeline 1", response.getBody().getName());
    }

    @Test
    void testUpdatePipeline() {
        // Given: A pipeline with ID 1
        Pipeline existingPipeline = new Pipeline(1, "Pipeline 1", LocalDateTime.now(), LocalDateTime.now(), "Weekly", "10:00 AM");
        Pipeline updatedPipeline = new Pipeline(1, "Updated Pipeline 1", LocalDateTime.now(), LocalDateTime.now(), "Daily", "07:00 AM");

        // When: Update the pipeline
        doNothing().when(pipelineService).updatePipeline(1, updatedPipeline); // No return value

        ResponseEntity<String> response = pipelineController.updatePipeline(1, updatedPipeline);

        // Then: Verify response status and the update message
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Pipeline updated", response.getBody());

        // Verify that the updatePipeline method was called with the correct parameters
        verify(pipelineService, times(1)).updatePipeline(1, updatedPipeline);
    }*/
}
