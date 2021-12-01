package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.domain.exception.ProcessingException;
import com.solvd.hospital.persistence.ConnectionPool;
import com.solvd.hospital.persistence.EquipmentSuppliersRepository;

import java.sql.*;

public class EquipmentSuppliersRepositoryImpl implements EquipmentSuppliersRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Long equipmentId, Long supplierId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "insert into Equipment_suppliers (equipment_id,supplier_id) values (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setLong(1, equipmentId);
            preparedStatement.setLong(2, supplierId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new ProcessingException("Can't create suppliers for equipment " + e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
