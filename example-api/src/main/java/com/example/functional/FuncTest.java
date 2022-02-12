package com.example.functional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhuchao
 * @date 2022/2/10 11:17 下午
 */
public class FuncTest {

    public static void main(String[] args) {
        //1、求和 传入Integer返回Integer类型
        MyFunction<Integer, Integer> myFunction1 = (x, y) -> {
            //返回总和
            return x + y;
        };
        Integer count = myFunction1.handler(5, 10);
        System.out.println("输出总和为：" + count);


        //2、求和 传入Integer返回String类型
        MyFunction<Integer, String> myFunction2 = (x, y) -> {
            //返回总和
            return x + " + " + y + " = " + (x + y);
        };
        System.out.println(myFunction2.handler(5, 10));


        //3、对象处理 过滤对象
        List<Student> students = Arrays
                .asList(new Student("小明", 3), new Student("小白", 13), new Student("小黄", 18));
        MyFunction<Integer, List<Student>> myFunction3 = (x, y) -> {
            //这里通过java8 的stream来过滤 年龄大于x 且小于y的对象
            List<Student> studentList = students.stream().filter(student -> student.getAge() > x && student.getAge() < y).collect(
                    Collectors.toList());
            return studentList;
        };
        List<Student> list = myFunction3.handler(5, 15);
        //遍历集合 输出对象
        list.forEach(x -> System.out.println(x));
    }
}
