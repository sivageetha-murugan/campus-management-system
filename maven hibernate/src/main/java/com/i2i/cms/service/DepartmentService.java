package com.i2i.cms.service;

import com.i2i.cms.dao.DepartmentDao;
import com.i2i.cms.model.Department;

/*
 * This inserts the department details such as department name, HOD name, no of teachers and students.
 */

public class DepartmentService {
    
    private DepartmentDao departmentDao = new DepartmentDao();

    /*
     * <p>
     * This inserts the department details such department name, hod, no of teachers and students.
     * </p>
     *
     * @param department
     *        The department contains the department name, hod, no of teachers and students.
     * @return inserted department details with the generated department id.
     */
    public Department addDepartment(Department department) {
        return departmentDao.insertDepartment(department);
    }
}
