package com.springbatch.config;

import com.springbatch.listener.SpringBatchListener;
import com.springbatch.partitioner.SpringBatchPartitioner;
import com.springbatch.processor.SpringBatchProcessor;
import com.springbatch.reader.SpringBatchListReader;
import com.springbatch.service.BatchJobService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by prasanth.p on 01/07/17.
 */
@Configuration
@EnableBatchProcessing
@ComponentScan(basePackages = "com.springbatch")
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobs;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public BatchJobService batchJobService() {
        return new BatchJobService();
    }

    @Bean
    public Job job(Step masterStep) {
        return jobs.get("job")
                .flow(masterStep).end()
                .build();
    }

    @Bean
    public Step masterStep() {
        return stepBuilderFactory.get("masterStep")
                .partitioner(start())
                .partitioner("start", springBatchPartitioner())
                .taskExecutor(taskExecutor())
                .gridSize(3)
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor simpleAsyncTaskExecutor=new SimpleAsyncTaskExecutor();

        //ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //simpleAsyncTaskExecutor.setMaxPoolSize(5);
        //taskExecutor.afterPropertiesSet();
        return simpleAsyncTaskExecutor;
    }


    @Bean
    public Step start() {
        return stepBuilderFactory.get("start")
                .listener(springBatchListener())
                .allowStartIfComplete(true)
                .chunk(1)
                .reader(springBatchListReader())
                .processor(springBatchProcessor())
                .build();
    }

    @Bean
    @StepScope
    public ItemReader springBatchListReader() {
        return new SpringBatchListReader();
    }

    @Bean
    @StepScope
    public SpringBatchProcessor springBatchProcessor() {
        return new SpringBatchProcessor();
    }

    @Bean
    @StepScope
    public SpringBatchListener springBatchListener() {
        return new SpringBatchListener();
    }

    @Bean
    @StepScope
    public SpringBatchPartitioner springBatchPartitioner() {
        return new SpringBatchPartitioner();
    }
}
