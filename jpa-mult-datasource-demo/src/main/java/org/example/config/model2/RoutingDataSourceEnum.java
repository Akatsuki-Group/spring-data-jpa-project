package org.example.config.model2;

/**
 * 定义一个数据源的枚举类
 */
public enum RoutingDataSourceEnum {
   DB1, //实际工作中枚举的语义可以更加明确一点；
   DB2;
   public static RoutingDataSourceEnum findByCode(String dbRouting) {
      for (RoutingDataSourceEnum e : values()) {
         if (e.name().equals(dbRouting)) {
            return e;
         }
      }
      return DB1;//没找到的情况下，默认返回数据源1
   }
}