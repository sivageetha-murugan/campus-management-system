package com.i2i.cms.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.i2i.cms.exception.DepartmentException;
import com.i2i.cms.helper.HibernateConnection;
import com.i2i.cms.model.Department;

/*
 * This class inserts the department details such as department name, HOD name, no of teachers and students.
 */

public class DepartmentDao {

    /*
     * <p>
     * This inserts the department details such department name, hod, no of teachers and students.
     * </p>
     *
     * @param department
     *        The department contains the department name, hod, no of teachers and students.
     * @return inserted department details with the generated department id.
     * @throws DepartmentException when the department can not be inserted.
     */
    public Department insertDepartment(Department department) {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(department);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DepartmentException("Unable to insert department: " + department.getDepartmentName(), e);
        }
        return department;
    }
}
