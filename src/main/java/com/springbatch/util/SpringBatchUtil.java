package com.springbatch.util;

import java.util.ArrayList;
import java.util.List;

public class SpringBatchUtil {

  public static List<String> getList() {
    List<String> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add("added" + i);
    }
    return list;

  }

}
