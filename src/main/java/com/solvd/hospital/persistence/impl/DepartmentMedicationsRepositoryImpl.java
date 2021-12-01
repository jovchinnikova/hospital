package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.domain.Medication;
import com.solvd.hospital.domain.exception.ProcessingException;
import com.solvd.hospital.persistence.ConnectionPool;
import com.solvd.hospital.persistence.DepartmentMedicationsRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DepartmentMedicationsRepositoryImpl implements DepartmentMedicationsRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Medication medication, Long departmentId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "insert into Department_medications (department_id, medication_id, quantity) values (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setLong(1, departmentId);
            preparedStatement.setLong(2, medication.getId());
            preparedStatement.setInt(3, medication.getQuantity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ProcessingException("Can't create medications for department " + e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
