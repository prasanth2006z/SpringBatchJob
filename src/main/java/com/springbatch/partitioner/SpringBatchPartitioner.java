package com.springbatch.partitioner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

import com.springbatch.util.SpringBatchUtil;

/**
 * Created by prasanth.p on 02/07/17.
 */
public class SpringBatchPartitioner implements Partitioner {
  /**
   * 
   */
  @Override
  public Map<String, ExecutionContext> partition(int recordCount) {
    return simpleRead();
  }

  /**
   * 
   * @return
   */
  private Map<String, ExecutionContext> simpleRead() {
    Map<String, ExecutionContext> partitionMap = new HashMap<>();

    for (int i = 0; i < SpringBatchUtil.getList().size(); i++) {
      ExecutionContext context = new ExecutionContext();
      context.put("list", SpringBatchUtil.getList().get(i));
      context.put("name", "Thread-" + i);
      partitionMap.put("partitionCode" + i, context);
    }
    return partitionMap;
  }

  /**
   * 
   * @return
   */
  private Map<String, ExecutionContext> readByRange() {

    Map<String, ExecutionContext> partitionMap = new HashMap<>();

    List<String> firstList = new ArrayList<>();
    List<String> secondList = new ArrayList<>();
    List<String> thirdList = new ArrayList<>();

    for (int i = 0; i < 2; i++) {
      firstList.add(SpringBatchUtil.getList().get(i));
    }

    for (int i = 2; i < 6; i++) {
      secondList.add(SpringBatchUtil.getList().get(i));
    }

    for (int i = 6; i < 10; i++) {
      thirdList.add(SpringBatchUtil.getList().get(i));
    }

    for (int i = 0; i < 3; i++) {
      ExecutionContext context = new ExecutionContext();
      if (i == 0) {
        context.put("list", firstList);
      }
      if (i == 1) {
        context.put("list", secondList);
      }
      if (i == 2) {
        context.put("list", thirdList);
      }

      context.put("name", "Thread" + i);
      partitionMap.put("partitionCode" + i, context);
    }
    return partitionMap;
  }

}
