package com.example.employee_hibernate.repository.impl;

import com.example.employee_hibernate.model.Employee;
import com.example.employee_hibernate.repository.IEmployeeRepository;
import com.mysql.cj.Session;
import com.mysql.cj.xdevapi.SessionFactory;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.security.auth.login.Configuration;
import javax.transaction.Transaction;
import java.util.ArrayList;

@Repository
public class EmployeeRepositoryImpl implements IEmployeeRepository {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration().configure("hibernate.conf.xml").buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Employee> findAll() {
        // SELECT * FROM employee
        String queryStr = "SELECT p FROM Employee AS p";
        TypedQuery<Employee> query = entityManager.createQuery(queryStr, Employee.class);
        return (ArrayList<Employee>) query.getResultList();
    }

    @Override
    public Employee findEmployeeById(int id) {
        // SELECT * FROM employee WHERE id = ?
        // String queryStr = "SELECT * FROM employee WHERE employee_id = ?";
        String queryStr = "FROM Employee AS p WHERE p.id = :id";
        TypedQuery<Employee> query = entityManager.createQuery(queryStr, Employee.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        Transaction transaction = null;
        Employee origin;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if (employee.getId() != 0) {
                origin = findEmployeeById(employee.getId());
                origin.setName(employee.getName());
                origin.setAge(employee.getAge());
                origin.setImage(employee.getImage());
            } else {
                origin = employee;
            }
            session.saveOrUpdate(origin);
            transaction.commit();
            return origin;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }

    @Override
    public Employee deleteEmployee(int id) {
        Transaction transaction = null;
        Employee origin = findEmployeeById(id);
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if (origin != null) {
                session.delete(origin);
            }
            transaction.commit();
            return origin;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }
}
