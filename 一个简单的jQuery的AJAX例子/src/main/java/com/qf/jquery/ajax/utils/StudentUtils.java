package com.qf.jquery.ajax.utils;

import com.qf.jquery.ajax.pojo.Student;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 这个类是一个工具类
 * 这个工具类是为了将页面发送的信息包装厂一个student类的
 * TODO 但是这个工具类具有很低的代码复用性,这样做是不可取的,我应该用一个反射来处理,做成一个通用性的工具类
 * TODO 并且应该对一些传输过来可能出错的信息做一些处理,一旦出错做出处理,返回结果
 * TODO 由于时间匆忙,先在这里用一个简陋的方法实现
 */

public class StudentUtils {
    //定义一个SimpleDateFormat格式
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static Student reqForStudent(HttpServletRequest req){
        Student student = new Student();

        Date bornDate = null;
        try {
            bornDate = sdf.parse(req.getParameter("bornDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        student.setStuId(req.getParameter("stuId"));
        student.setStuName(req.getParameter("stuName"));
        student.setGradeId(Long.parseLong(req.getParameter("gradeId")));

        student.setBornDate(bornDate);

        student.setStuGender(req.getParameter("stuGender"));
        student.setStuPhone(req.getParameter("stuPhone"));
        student.setStuEmail(req.getParameter("stuEmail"));
        student.setStuAddress(req.getParameter("stuAddress"));
        student.setStuPassword(req.getParameter("stuPassword"));

        return student;
    }
}
