package com.qf.jquery.ajax.dao;

import com.qf.jquery.ajax.pojo.Student;

import java.util.List;

public interface StudentDao {
    //这个是一个加载操作,但实际上他是一个查询操作,查询数据库中的所有数据,并返回给上一层,直让这些数据出现在页面上
    //TODO 但这里也是一个不可取的操作,他应该是一个分页查询,由于这里数据库内容较少,所以使用了这样的操作,但这里应该可以做一个更好的优化
    List<Student> load();

    //这个操作是通过stuID来删除一个学生,由于写前端代码的时候只做了一个操作,所以将修改状态下的删除设置为不可删除
    boolean deStuById(String stuId);

    //这个函数是通过一个学生id来更新一个学生的操作,由于我们找不到依据对象,所以我设定了stuId在页面修改中不可修改,并将它作为修改对象传入,
    //TODO 这可能是一个建表问题
    boolean stuUpdate(Student student);

    //这是一个添加学生的操作,通过传入一个学生对象来添加一个学生
    boolean stuAdd(Student student);
}
