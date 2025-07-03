package org.pantherslabs.chimera.examples.services;


import org.pantherslabs.chimera.examples.mapper.CustomDataPipelineMapper;
import org.pantherslabs.chimera.examples.mapper.CustomMetaDataPipelineMapper;
import org.pantherslabs.chimera.examples.mapper.generated.*;
import org.pantherslabs.chimera.examples.model.generated.ContractCustomer;
import org.pantherslabs.chimera.examples.model.generated.DataPipeline;
import org.pantherslabs.chimera.examples.model.generated.MetaDataPipeline;
import org.pantherslabs.chimera.examples.model.generated.UserProfile;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Service
public class ChimeraUserProfileService {

  @Autowired private UserProfileMapper userProfileMapper;

  @Autowired
  private ContractCustomerMapper contractCustomerMapper;

  @Autowired
  private CustomerMapper customerMapper;

  @Autowired
  private DataPipelineMapper dataPipelineMapper;

  @Autowired
  private CustomDataPipelineMapper customDataPipelineMapper;

  @Autowired
  MetaDataPipelineMapper metaDataPipelineMapper;

  @Autowired
  private CustomMetaDataPipelineMapper customMetaDataPipelineMapper;

  @Autowired
  private PipelineMapper pipelineMapper;


  public void createUserProfile(UserProfile profile) {
    int insert = userProfileMapper.insert(profile);
  }

  public List<UserProfile> getAllUserProfile() {
    return userProfileMapper.select(SelectDSLCompleter.allRows());
  }


  @Transactional
  public void createContractCustomer(ContractCustomer contractCustomer){
    contractCustomerMapper.insert(contractCustomer);
  }

  @Transactional
  public void createDataPipeLine(DataPipeline dataPipeline){
    customDataPipelineMapper.customInsert(dataPipeline);
  }

  @Transactional
  public void createMetaDataPipeLine(MetaDataPipeline dataPipeline){
    customMetaDataPipelineMapper.insert(dataPipeline);
  }

  public List<DataPipeline> getAllDataPipeLines(){
    return dataPipelineMapper.select(SelectDSLCompleter.allRows());
  }

  public List<MetaDataPipeline> getAllMetaDataPipeLines(){
    return metaDataPipelineMapper.select(SelectDSLCompleter.allRows());
  }

  public void deleteFromMetaDataPipeline(long id){
     customMetaDataPipelineMapper.customDelete(id);
  }

  public void deleteFromDataPipelineMapper(long id){
    customDataPipelineMapper.customDelete(id);
  }

  public void deletePipeline(long id){
    pipelineMapper.delete(c ->
            c.where(MetaDataPipelineDynamicSqlSupport.id, isEqualTo(id)));
  }

}
