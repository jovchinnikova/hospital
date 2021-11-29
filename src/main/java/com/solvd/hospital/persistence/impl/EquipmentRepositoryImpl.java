package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.domain.Equipment;
import com.solvd.hospital.domain.exception.CreateException;
import com.solvd.hospital.persistence.ConnectionPool;
import com.solvd.hospital.persistence.EquipmentRepository;

import java.sql.*;

public class EquipmentRepositoryImpl implements EquipmentRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Equipment equipment) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "insert into Equipments (name,price) values (?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,equipment.getName());
            preparedStatement.setBigDecimal(2,equipment.getPrice());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                equipment.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new CreateException("Can't create equipment");
        } finally{
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
