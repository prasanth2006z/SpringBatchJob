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
public class SpringBatchListener implements StepExecutionListener, ItemReadListener<Object>, ChunkListener {

  @Override
  public void beforeStep(StepExecution stepExecution) {
  }

  @Override
  public ExitStatus afterStep(StepExecution stepExecution) {
    return ExitStatus.COMPLETED;
  }

  @Override
  public void beforeRead() {
  }

  @Override
  public void afterRead(Object o) {
  }

  @Override
  public void onReadError(Exception e) {
  }

  @Override
  public void afterChunk(ChunkContext arg0) {
    arg0.getStepContext().close();
  }

  @Override
  public void afterChunkError(ChunkContext arg0) {
  }

  @Override
  public void beforeChunk(ChunkContext arg0) {

  }
}
