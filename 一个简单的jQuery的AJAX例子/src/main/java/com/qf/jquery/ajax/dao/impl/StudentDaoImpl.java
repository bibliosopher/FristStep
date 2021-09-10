package com.qf.jquery.ajax.dao.impl;

import com.qf.jquery.ajax.dao.StudentDao;
import com.qf.jquery.ajax.pojo.Student;
import com.qf.jquery.ajax.utils.BeanUtils;
import com.qf.jquery.ajax.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    private QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());

    @Override
    public List<Student> load() {

        String sql = BeanUtils.getSql("sql.load");
        try {
            List<Student> studentList = queryRunner.query(sql, new BeanListHandler<>(Student.class));
            return studentList;
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return new ArrayList<>();
    }


    @Override
    public boolean deStuById(String stuId) {
        String sql = BeanUtils.getSql("sql.deById");
        try {
            int count = queryRunner.update(sql, stuId);
            if(count==1){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 我们在之后的调试中发现,stuUpdate()和stuAdd()并未按照我的想法执行我想要的结果
     * 我们的设想:当我们传入一个不合法的数据导致程序出现异常的时候,这个函数应该可以返回一个false的操作,
     * 这个时候我们会将这个结果返回给页面,页面会根据这个结果来执行是否更新domTree,来并提醒用户我们没有成功
     * 但是实际上:我们出错了,当我们传入一个会让数据库产生异常的结果的时候,并没有返回一个false,并且页面的domTree
     * 成功更新了页面
     *
     * 于是这个时候我产生了一个新的问题,当我们传入数据进行对象转换的时候是不是也会出错,我们这个时候是不是也需要
     * 传回给页面一个false,并不执行这个操作,让用户更改
     *
     */

    @Override
    public boolean stuUpdate(Student student) {
        String sql = BeanUtils.getSql("sql.update");
        try {
            int result = queryRunner.update(sql, student.getStuName(), student.getGradeId(),
                    student.getBornDate(), student.getStuGender(), student.getStuPhone(), student.getStuEmail(),
                    student.getStuAddress(), student.getStuPassword(),student.getStuId());
            if(result==0){
                return false;
            }else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean stuAdd(Student student) {
        String sql = BeanUtils.getSql("sql.add");

        try {
            int result = queryRunner.update(sql,student.getStuId(),student.getStuName(), student.getGradeId(),
                    student.getBornDate(), student.getStuGender(), student.getStuPhone(), student.getStuEmail(),
                    student.getStuAddress(), student.getStuPassword());
            if(result==0){
                return false;
            }else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
