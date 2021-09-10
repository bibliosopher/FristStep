package com.qf.jquery.ajax.service.impl;

import com.qf.jquery.ajax.dao.StudentDao;
import com.qf.jquery.ajax.dao.impl.StudentDaoImpl;
import com.qf.jquery.ajax.pojo.Student;
import com.qf.jquery.ajax.service.StudentService;
import com.qf.jquery.ajax.utils.StringUtils;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao = new StudentDaoImpl();

    @Override
    public List<Student> load() {
        return studentDao.load();
    }

    @Override
    public boolean deStuById(String stuId) {
        if (StringUtils.isEmpty(stuId)) {
            return false;
        }else {
            return studentDao.deStuById(stuId);
        }
    }

    @Override
    public boolean stuUpdate(Student student) {
        return studentDao.stuUpdate(student);
    }

    @Override
    public boolean stuAdd(Student student) {
        return studentDao.stuAdd(student);
    }
}
