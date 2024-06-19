package com.i2i.cms.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.i2i.cms.exception.DepartmentException;
import com.i2i.cms.helper.HibernateConnection;
import com.i2i.cms.model.Department;

public class DepartmentDao {

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
