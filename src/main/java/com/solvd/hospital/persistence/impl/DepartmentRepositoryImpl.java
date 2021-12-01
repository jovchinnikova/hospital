package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.domain.Department;
import com.solvd.hospital.domain.exception.ProcessingException;
import com.solvd.hospital.persistence.ConnectionPool;
import com.solvd.hospital.persistence.DepartmentRepository;

import java.sql.*;

public class DepartmentRepositoryImpl implements DepartmentRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

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
}
