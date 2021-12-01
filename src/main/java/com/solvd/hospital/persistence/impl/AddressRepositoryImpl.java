package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.domain.Address;
import com.solvd.hospital.domain.exception.ProcessingException;
import com.solvd.hospital.persistence.AddressRepository;
import com.solvd.hospital.persistence.ConnectionPool;

import java.sql.*;

public class AddressRepositoryImpl implements AddressRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Address address) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "insert into Addresses (city,street,building_number) values (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, address.getCity());
            preparedStatement.setString(2, address.getStreet());
            preparedStatement.setString(3, address.getBuildingNumber());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                address.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessingException("Can't create address  " + e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
