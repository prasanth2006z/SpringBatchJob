package com.springbatch.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prasanth.p on 01/07/17.
 */
public class SpringBatchListReader implements ItemReader<List<String>>{

    @Value("#{stepExecutionContext[list]}")
    private List<String> list;

    @Override
    public List<String> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return list.isEmpty()?null:list;
    }
}
