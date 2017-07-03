package com.springbatch.reader;

import java.util.List;

import org.springframework.batch.item.support.ListItemReader;

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
      return out;
  }
}
