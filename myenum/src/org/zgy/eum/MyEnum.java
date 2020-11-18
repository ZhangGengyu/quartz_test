package org.zgy.eum;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum MyEnum {
    ONE, TWO, THREE, FOUR
}

enum MyEnum2 {
    FIVE("第五个"), SIX("第六个"), SEVEN("第七个"), EIGHT("第八个");

    private String value;

    MyEnum2(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

enum MyEnum3 {
    NINE("第九个", 9), TEN("第十个", 10);

    private String value;
    private int index;

    MyEnum3(String value, int index) {
        this.value = value;
        this.index = index;
    }

    public String getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "MyEnum3{" +
                "value='" + value + '\'' +
                ", index=" + index +
                '}';
    }
}

enum MyEnum4 {
    A("a"){
        public void getStr() {
            System.out.println("aaa");
        }
    };

    String value;
    public String getValue() {
        return value;
    }
    private MyEnum4(String value) {
        this.value = value;
    }
}

class Test {
    public static void main(String[] args) {
        for (MyEnum3 e : MyEnum3.values()) {
            System.out.println(e.name() + "-" + e.getValue() + "-" + e.toString() + ", ");
        }
    }
}

class Test02 {
    public static void main(String[] args) {
        List<Integer> collect = Stream.of(1, 2, 3).collect(Collectors.toList());
        collect.forEach(i -> {
            System.out.println("aaaa");
        });
    }
}

class StringTest {
    public static void main(String[] args) {
        MyEnum4 a = MyEnum4.A;

        System.out.println();
    }
}



















