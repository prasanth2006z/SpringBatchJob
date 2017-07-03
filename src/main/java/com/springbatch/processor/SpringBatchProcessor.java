package com.springbatch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by prasanth.p on 01/07/17.
 */
public class SpringBatchProcessor implements ItemProcessor<Object, Object>{

    @Value("#{stepExecutionContext[name]}")
    private String threadName;

    @Override
    public Object process(Object o) throws Exception {
        String name=o.toString();
        System.out.println("Reading value '"+name+"' by :"+threadName.toString());
        return o;
    }
}
