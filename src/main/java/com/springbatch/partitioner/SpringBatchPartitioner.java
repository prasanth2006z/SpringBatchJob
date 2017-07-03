package com.springbatch.partitioner;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by prasanth.p on 02/07/17.
 */
public class SpringBatchPartitioner implements Partitioner {


    private List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("Prasanth");
        list.add("ramya");
        list.add("ramyaPrash");
        return list;

    }
    @Override
    public Map<String, ExecutionContext> partition(int recordCount) {

        Map<String, ExecutionContext> partitionMap= new HashMap<>();

        int count=1;

        for(int i=0;i<recordCount;i++){
            ExecutionContext context = new ExecutionContext();
            List<String> abc=new ArrayList<>();
            context.put("list", getList().get(i));
            context.put("name", "Thread" + i);
            partitionMap.put("partitionCode"+i,context);
        }



//        int range = 10;
//        int fromId = 1;
//        int toId = range;
//
//        for (int i = 1; i <= recordCount; i++) {
//            ExecutionContext value = new ExecutionContext();
//            System.out.println("recordCount=="+recordCount);
//
//            System.out.println("\nStarting : Thread" + i);
//            System.out.println("fromId : " + fromId);
//            System.out.println("toId : " + toId);
//
//            value.putInt("fromId", fromId);
//            value.putInt("toId", toId);
//
//            // give each thread a name, thread 1,2,3
//            value.putString("name", "Thread" + i);
//
//            result.put("partition" + i, value);
//
//            fromId = toId + 1;
//            toId += range;
//
//        }


        return partitionMap;
    }
}
