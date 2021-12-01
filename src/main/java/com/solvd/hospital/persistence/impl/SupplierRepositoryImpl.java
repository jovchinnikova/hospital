package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.domain.Supplier;
import com.solvd.hospital.domain.exception.ProcessingException;
import com.solvd.hospital.persistence.ConnectionPool;
import com.solvd.hospital.persistence.SupplierRepository;

import java.sql.*;
import java.util.Optional;

public class SupplierRepositoryImpl implements SupplierRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Supplier supplier) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "insert into Suppliers (name,country) values(?,?)";
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, supplier.getName());
            preparedStatement.setString(2, supplier.getCountry());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                supplier.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessingException("Can't create supplier " + e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(Supplier supplier, String country) {
        Connection connection = CONNECTION_POOL.getConnection();
        String update = "update suppliers set country = ? where name = ?";
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement(update);
            preparedStatement.setString(1, country);
            preparedStatement.setString(2, supplier.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ProcessingException("Can't update supplier " + e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(String name) {
        Connection connection = CONNECTION_POOL.getConnection();
        String delete = "delete from suppliers where name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ProcessingException("Can't delete supplier " + e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Supplier> getByName(String name) {
        Supplier result = null;

        Connection connection = CONNECTION_POOL.getConnection();
        String selectByName = "select id from suppliers where name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectByName);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = new Supplier();
                result.setId(resultSet.getLong("id"));
            }
        } catch (SQLException e) {
            throw new ProcessingException("Can't select supplier by name " + e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.ofNullable(result);
    }
}
