package com.springbatch.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

/**
 * Created by prasanth.p on 01/07/17.
 */
public class SpringBatchListener implements StepExecutionListener, ItemReadListener {

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
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("processed rows=="+stepExecution.getReadCount());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }
}
