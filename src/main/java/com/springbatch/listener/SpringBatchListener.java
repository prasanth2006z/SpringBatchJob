package com.springbatch.listener;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;

/**
 * Created by prasanth.p on 01/07/17.
 */
public class SpringBatchListener implements StepExecutionListener, ItemReadListener<Object>,ChunkListener {

  @Override
  public void beforeStep(StepExecution stepExecution) {
      System.out.println("processed rows=="+stepExecution.getReadCount());
  }

  @Override
  public ExitStatus afterStep(StepExecution stepExecution) {
    System.out.println("processed rows=="+stepExecution.getReadCount());
    System.out.println("Read count=="+stepExecution.getReadCount());
      return null;
  }
  
    @Override
    public void beforeRead() {
      System.out.println("Before read...");
    }

    @Override
    public void afterRead(Object o) {
      System.out.println("After read..."+o.toString());
    }

    @Override
    public void onReadError(Exception e) {

    }

    @Override
    public void afterChunk(ChunkContext arg0) {
      System.out.println("closing...");
      arg0.getStepContext().close();
      
    }

    @Override
    public void afterChunkError(ChunkContext arg0) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void beforeChunk(ChunkContext arg0) {
      // TODO Auto-generated method stub
      
    }
}
