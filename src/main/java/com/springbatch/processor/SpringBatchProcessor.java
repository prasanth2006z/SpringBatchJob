package com.springbatch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by prasanth.p on 01/07/17.
 */
public class SpringBatchProcessor implements ItemProcessor{

    @Value("#{stepExecutionContext[name]}")
    private String threadName;

    @Override
    public Object process(Object o) throws Exception {
        System.out.println("Reading ======>"+threadName.toString());
        String name=o.toString();
        System.out.println("name===>"+name);
        return null;
    }
}
