package org.zgy.test_redis.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.Map;

public class TestJSON {
    public static void main(String[] args) {
        Map<String, Student> maps = new HashMap<String, Student>();
        Student s1 = new Student("s1", 16);

        maps.put("s1", s1);
        maps.put("s2", s1);

        SerializerFeature feature = SerializerFeature.DisableCircularReferenceDetect;

        byte[] bytes = JSON.toJSONBytes(maps, feature);

        System.out.println(new String(bytes));
    }
}

class Student{
    String name;
    Integer age;
    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
