package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.domain.Ward;
import com.solvd.hospital.domain.exception.CreateException;
import com.solvd.hospital.persistence.ConnectionPool;
import com.solvd.hospital.persistence.WardRepository;

import java.sql.*;

public class WardRepositoryImpl implements WardRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Ward ward, Long departmentId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "insert into Wards (number,floor,department_id) values (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,ward.getNumber());
            preparedStatement.setInt(2,ward.getFloor());
            preparedStatement.setLong(3,departmentId);
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while(resultSet.next()){
                ward.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new CreateException("Can't create ward");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
