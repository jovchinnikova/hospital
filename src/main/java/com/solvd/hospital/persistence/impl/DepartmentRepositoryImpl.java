package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.domain.Department;
import com.solvd.hospital.domain.exception.ProcessingException;
import com.solvd.hospital.persistence.ConnectionPool;
import com.solvd.hospital.persistence.DepartmentRepository;
import com.solvd.hospital.service.WardService;
import com.solvd.hospital.service.impl.WardServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRepositoryImpl implements DepartmentRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private final WardService wardService = new WardServiceImpl();

    @Override
    public void create(Department department, Long departmentHeadId, Long hospitalId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "insert into Departments (title, department_head_id, hospital_id) values (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, department.getTitle());
            preparedStatement.setLong(2, departmentHeadId);
            preparedStatement.setLong(3, hospitalId);
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                department.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessingException("Can't create department " + e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    public List<Department> getAll(){
        List<Department> departments = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();
        String select = "select id, title from departments";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Department department = new Department();
                department.setId(resultSet.getLong("id"));
                department.setTitle(resultSet.getString("title"));
                department.setWards(wardService.getById(department.getId()));
                departments.add(department);
            }
        } catch (SQLException e) {
            throw new ProcessingException("Can't select departments " + e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return departments;
    }
}
