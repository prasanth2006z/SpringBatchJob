package com.springbatch.config;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.springbatch.listener.SpringBatchExecutionListener;
import com.springbatch.listener.SpringBatchListener;
import com.springbatch.partitioner.SpringBatchPartitioner;
import com.springbatch.processor.SpringBatchProcessor;
import com.springbatch.reader.SpringBatchListReader;
import com.springbatch.service.BatchJobService;

/**
 * Created by prasanth.p on 01/07/17.
 */
@Configuration
@EnableBatchProcessing
@ComponentScan(basePackages = "com.springbatch")
public class BatchConfig {
  /**
   * 
   */
  @Autowired
  private JobBuilderFactory jobs;
  /**
   * 
   */
  @Autowired
  private StepBuilderFactory stepBuilderFactory;
  /**
   * 
   */
  protected static final List<String> OVERRIDEN_BY_EXPRESSION_VALUE = null;

  /**
   * 
   * @return
   */
  @Bean
  public BatchJobService batchJobService() {
    return new BatchJobService();
  }

  /**
   * 
   * @param masterStep
   * @return
   */
  @Bean
  public Job job(Step masterStep) {
    return jobs.get("job").listener(SpringBatchExecutionListener())
      .flow(masterStep).end()
      .build();
  }

  /**
   * 
   * @return
   */
  @Bean
  public Step masterStep() {
    return stepBuilderFactory.get("masterStep")
      .partitioner(start())
      .partitioner("start", springBatchPartitioner())
      .taskExecutor(taskExecutor())
      .gridSize(20)
      .build();
  }

  /**
   * 
   * @return
   */
  @Bean
  public SpringBatchExecutionListener SpringBatchExecutionListener() {
    return new SpringBatchExecutionListener();

  }

  /**
   * 
   * @return
   */
  @Bean
  public TaskExecutor taskExecutor() {
    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    taskExecutor.setMaxPoolSize(5);
    taskExecutor.afterPropertiesSet();
    return taskExecutor;
  }

  /**
   * 
   * @return
   */
  @Bean
  public Step start() {
    return stepBuilderFactory.get("start")
      .listener(springBatchListener())
      .chunk(1)
      .reader(springBatchListReader(OVERRIDEN_BY_EXPRESSION_VALUE))
      .processor(springBatchProcessor())
      .build();
  }

  /**
   * 
   * @param list
   * @return
   */
  @Bean
  @StepScope
  public ItemReader<String> springBatchListReader(@Value("#{stepExecutionContext[list]}") List<String> list) {
    return new SpringBatchListReader(list);
  }

  /**
   * 
   * @return
   */
  @Bean
  @StepScope
  public SpringBatchProcessor springBatchProcessor() {
    return new SpringBatchProcessor();
  }

  /**
   * 
   * @return
   */
  @Bean
  @StepScope
  public SpringBatchListener springBatchListener() {
    return new SpringBatchListener();
  }

  /**
   * 
   * @return
   */
  @Bean
  @StepScope
  public SpringBatchPartitioner springBatchPartitioner() {
    return new SpringBatchPartitioner();
  }
}
