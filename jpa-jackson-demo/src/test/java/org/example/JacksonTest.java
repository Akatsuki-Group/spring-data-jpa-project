package org.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JacksonTest {
    @Test
    public void test1() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Userx userx = new Userx(null, "Tom", 10, new Date(), 120, "sam");
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userx);
        System.out.println(jsonString);
    }

    @Test
    public void test2() throws IOException {
        String jsonString="{\n" +
                "  \"last_name\" : \"sam\",\n" +
                "  \"age\" : 10,\n" +
                "  \"date\" : \"2021-09-03 15:42:57\",\n" +
                "  \"user_name\" : \"Tom\"\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper();
        Userx userx1 = mapper.readValue(jsonString, Userx.class);
        System.out.println(userx1);
    }

    @Test
    public void test3() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // 造数据
        Map<String, Object> map = new HashMap<>();
        map.put("user_name", "Tom");
        map.put("date", "2020-07-26 19:28:44");
        map.put("age", 100);
        map.put("last_name", "100");
//        map.put("demoKey", "demoValue");
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
        System.out.println(jsonString);
        System.out.println("反序列化");
        Userx user = mapper.readValue(jsonString, Userx.class);
        System.out.println(user.toString());
        System.out.println("序列化");
        jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        System.out.println(jsonString);
    }
}

// 用于类,指定属性在序列化时 json 中的顺序
//@JsonPropertyOrder({"date", "user_name"})
// 批量忽略属性，不进行序列化
//@JsonIgnoreProperties(value = {"other"})
// 用于序列化与反序列化时的驼峰命名与小写字母命名转换
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
//@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
class Userx {
    public Userx(Map<String, Object> other, String userName, Integer age, Date date, int height, String lastName) {
        this.other = other;
        this.userName = userName;
        this.age = age;
        this.date = date;
        this.height = height;
        this.lastName = lastName;
    }

    //    @JsonIgnore
    private Map<String, Object> other = new HashMap<>();

    // 正常case
    @JsonProperty("user_name")
    private String userName;
    private String lastName;
    // 空对象case
//    @JsonProperty
    private Integer age;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    // 日期转换case
    private Date date;
    // 默认值case
    private int height;

    public Userx() {
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
// 反序列化执行构造方法
//    @JsonCreator
//    public Userx(@JsonProperty("user_name") String userName) {
//        System.out.println("@JsonCreator 注解使得反序列化自动执行该构造方法 " + userName);
//        // 反序列化需要手动赋值
//        this.userName = userName;
//    }

    //    @JsonAnySetter
    public void set(String key, Object value) {
        System.out.println("::" + key + ":" + value);
        other.put(key, value);
    }

    //    @JsonAnyGetter
    public Map<String, Object> any() {
        return other;
    }
    // 本文默认省略getter、setter方法

    @Override
    public String toString() {
        return "Userx{" +
                "other=" + other +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", date=" + date +
                ", height=" + height +
                ", lastName=" + lastName +
                '}';
    }
}