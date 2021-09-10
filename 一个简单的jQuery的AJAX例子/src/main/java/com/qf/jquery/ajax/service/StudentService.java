package com.qf.jquery.ajax.service;

import com.qf.jquery.ajax.pojo.Student;

import java.util.List;

public interface StudentService {
    //加载函数,这个函数要求我们将操作传递给dao层查询,并将查询结果返回给上一层
    List<Student> load();

    //这个函数要求我们传入一个id,对这个id执行判空处理后,传递给dao层进行处理
    boolean deStuById(String stuId);

    //TODO 实际上service层应该对更新和修改层有一个更复杂的操作,比如数据格式问题,我们应该对数据格式有一个限制

    //这个函数是用来做更新操作的,实际上对应的是revise操作,我们需要将传递过来的数据发送给dao层,让其更新数据库数据,并返回结果
    boolean stuUpdate(Student student);

    //这个函数是用来做添加一个学生操作的,对应的是add操作,我们需要将这个数据发送给dao层,让其插入数据,并返回结果
    boolean stuAdd(Student student);
}
