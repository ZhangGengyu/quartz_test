package org.zgy.nio;

import java.nio.IntBuffer;

/**
 * 举例说明Buffer的使用
 */
public class BasicBuffer {
    public static void main(String[] args) {
        // 创建一个Buffer，大小为5，即可以存放5个int
        IntBuffer intBuffer = IntBuffer.allocate(5);
        // 向buffer中存放数据
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);
        }
        // 将buffer转换，读写切换
        intBuffer.flip();
        // 取数据
        while (intBuffer.hasRemaining()) {
            System.out.print(intBuffer.get() + ", ");
        }
    }
}

class Test{
    public static void main(String[] args) {
        int result = 0;
        int i = 2;
        switch (i) {
            case 1:
                result = result + i;
            case 2:
                result = result + i *2;
            case 3:
                result = result + i * 3;
        }
        System.out.println(result);
    }
}
