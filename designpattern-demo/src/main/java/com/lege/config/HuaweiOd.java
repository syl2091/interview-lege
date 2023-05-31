package com.lege.config;

import java.util.Scanner;

public class HuaweiOd {
    public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            // 注意 hasNext 和 hasNextLine 的区别
            while (in.hasNextInt()) { // 注意 while 处理多个 case
                System.out.println(in.nextInt()>>2);
            }
        }

}
