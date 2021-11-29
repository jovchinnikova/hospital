package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.domain.Equipment;
import com.solvd.hospital.domain.exception.CreateException;
import com.solvd.hospital.persistence.ConnectionPool;
import com.solvd.hospital.persistence.DepartmentEquipmentsRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DepartmentEquipmentsRepositoryImpl implements DepartmentEquipmentsRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Equipment equipment, Long departmentId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "insert into Department_equipments (department_id, equipment_id, quantity) values (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setLong(1,departmentId);
            preparedStatement.setLong(2,equipment.getId());
            preparedStatement.setInt(3,equipment.getQuantity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new CreateException("Can't create equipments for department");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
