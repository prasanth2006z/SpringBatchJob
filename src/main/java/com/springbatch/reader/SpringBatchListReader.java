package com.springbatch.reader;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by prasanth.p on 01/07/17.
 */
public class SpringBatchListReader  extends ListItemReader<String>{

  public SpringBatchListReader(List<String> list) {
    super(list);
  }

  @Override
  public String read() {
      String out = (String) super.read();
      System.out.println("Reading data " + out);
      return out;
  }
/*
implements ItemReader<List<String>>{

    @Value("#{stepExecutionContext[list]}")
    private List<String> list;

    static int count=0;
    
    @Override
    public List<String> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
      
      for(String s:list) {
        System.out.println("list===="+list);
        return list;
      }
      return null;
    }*/
}
