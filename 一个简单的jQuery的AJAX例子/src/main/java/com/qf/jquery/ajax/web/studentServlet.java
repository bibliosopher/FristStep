package com.qf.jquery.ajax.web;

import com.alibaba.fastjson.JSON;
import com.qf.jquery.ajax.pojo.Student;
import com.qf.jquery.ajax.service.StudentService;
import com.qf.jquery.ajax.service.impl.StudentServiceImpl;
import com.qf.jquery.ajax.utils.StudentUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/student")
public class studentServlet extends HttpServlet {

    private StudentService service = new StudentServiceImpl();

    /**
     * 由于get和post函数执行的是一个操作,所以他们的方法是一样的
     * 我们规定了从页面传输过来一个叫做option的数据,这个数据中传递了我即将执行的操作,
     * 于是doGet和doPost方法变成了一个枢纽站,不执行具体的操作,而只对前端传入的业务进行判断
     * 将具体的执行操作交给了具体的执行方法
     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String option = req.getParameter("option");
        switch (option) {
            case "load":
                loadStu(req, resp);
                break;
            case "delete":
                deleteStu(req,resp);
                break;
            case "revise":
                reviseStu(req, resp);
                break;
            case "add":
                addStu(req, resp);
                break;
            default:
                break;
        }
    }

    /**
     * 这个函数是用来在页面初始化的时候加载界面的
     * 具体操作:
     * 1.查询数据库中的所有学生信息
     * 2.将数据转化json格式
     * 3.将学生信息以一个ajax请求发送出去
     * @param req
     * @param resp
     */
    private void loadStu(HttpServletRequest req, HttpServletResponse resp) {
        //1.查询数据库中的所有学生信息
        List<Student> studentList = service.load();

        //2.将数据转换为json格式的
        String jsonString = JSON.toJSONString(studentList);

        //3.将得到的数据返回给页面
        try {
            resp.getWriter().write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 这是函数是用来删除数据库中的学生的,这个函数要求我们在页面传入一个stuId,
     * 通过这个stuId删除相应的数据库中的数据
     * 同时应该返回给页面一个删除成功的判断条件
     * @param req
     * @param resp
     */
    private void deleteStu(HttpServletRequest req, HttpServletResponse resp) {
        String stuId = req.getParameter("stuId");
        boolean result = service.deStuById(stuId);

        try {
            resp.getWriter().write(result+"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 这个函数是用来修改一个学生的具体信息的
     * 要求页面传输一个完整的学生对象,传输的name和Student里面的name一致
     * 我们需要将数据更新之后返回给页面结果,
     * TODO 页面应该传输一个完整的对象还是更改的数据,根据前端做的所有操作都是不可信的,那么这里应该传输一个完整的对象
     * @param req
     * @param resp
     */
    private void reviseStu(HttpServletRequest req, HttpServletResponse resp){

        Student student = StudentUtils.reqForStudent(req);
        boolean result = service.stuUpdate(student);
        try {
            resp.getWriter().write(result+"");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 这个函数是用来添加一个学生的具体信息的
     * 要求页面传输一个完整的学生对象,传输的name和student对象的name一样
     * 我们需要处理之后江结果返回给页面
     * @param req
     * @param resp
     */
    private void addStu(HttpServletRequest req, HttpServletResponse resp){
        Student student = StudentUtils.reqForStudent(req);
        boolean result = service.stuAdd(student);
        try {
            resp.getWriter().write(result+"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
