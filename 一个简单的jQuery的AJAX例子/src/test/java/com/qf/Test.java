package com.qf;

import com.qf.jquery.ajax.pojo.Student;
import com.qf.jquery.ajax.service.StudentService;
import com.qf.jquery.ajax.service.impl.StudentServiceImpl;

import java.util.Date;
import java.util.List;

public class Test {
    private static StudentService service = new StudentServiceImpl();
    public static void main(String[] args) {
//        testDeStuById("201726260208");
        testAdd();

    }
    private static void testLoad(){
        List<Student> studentList = service.load();
        System.out.println(studentList);
    }
    private static void testDeStuById(String stuId) {
        boolean b = service.deStuById(stuId);
        System.out.println(b);
    }
    private static void testAdd(){
        Student student = new Student();
        student.setStuId("1234566");
        student.setStuName("张三");
        student.setGradeId(1);
        student.setBornDate(new Date());
        student.setStuGender("男");
        student.setStuPhone("14587495821");
        student.setStuEmail("das@da.com");
        student.setStuAddress("北京");
        student.setStuPassword("dadasdsa");

        service.stuAdd(student);
    }
}
