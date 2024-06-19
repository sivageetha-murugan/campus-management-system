package com.i2i.cms.service;

import com.i2i.cms.dao.DepartmentDao;
import com.i2i.cms.model.Department;

public class DepartmentService {
    
    private DepartmentDao departmentDao = new DepartmentDao();

    public Department addDepartment(Department department) {
        return departmentDao.insertDepartment(department);
    }
}
