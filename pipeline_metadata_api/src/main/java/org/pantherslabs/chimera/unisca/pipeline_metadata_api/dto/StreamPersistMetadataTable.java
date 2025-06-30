package org.pantherslabs.chimera.unisca.pipeline_metadata_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter
@EqualsAndHashCode  // Include superclass fields in equals/hashCode
public class StreamPersistMetadataTable {

  private String kafkaTopic;

  private String kafkaKey;

  private String kafkaMessage;

}
