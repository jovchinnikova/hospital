package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.domain.exception.ProcessingException;
import com.solvd.hospital.persistence.ConnectionPool;
import com.solvd.hospital.persistence.MedicationSuppliersRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MedicationSuppliersRepositoryImpl implements MedicationSuppliersRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Long medicationId, Long supplierId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "insert into Medication_suppliers (medication_id, supplier_id) values (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setLong(1, medicationId);
            preparedStatement.setLong(2, supplierId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ProcessingException("Can't create suppliers for medication " + e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
