package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.domain.Employee;
import com.solvd.hospital.domain.exception.ProcessingException;
import com.solvd.hospital.persistence.ConnectionPool;
import com.solvd.hospital.persistence.EmployeeRepository;

import java.sql.*;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void createHead(Employee employee, Long specializationId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "insert into Employees (first_name, last_name, specialization_id, position) values (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setLong(3, specializationId);
            preparedStatement.setString(4, employee.getPosition().getName());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                employee.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessingException("Can't create head employee");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    public void create(Employee employee, Long specializationId, Long departmentId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "insert into Employees (first_name,last_name,specialization_id,department_id,qualification) values (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setLong(3, specializationId);
            preparedStatement.setLong(4, departmentId);
            preparedStatement.setInt(5, employee.getQualification());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                employee.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessingException("Can't create employee " + e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
