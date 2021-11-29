package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.domain.Supplier;
import com.solvd.hospital.domain.exception.CreateException;
import com.solvd.hospital.domain.exception.DeleteException;
import com.solvd.hospital.domain.exception.SelectException;
import com.solvd.hospital.domain.exception.UpdateException;
import com.solvd.hospital.persistence.ConnectionPool;
import com.solvd.hospital.persistence.SupplierRepository;

import java.sql.*;

public class SupplierRepositoryImpl implements SupplierRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Supplier supplier){
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "insert into Suppliers (name,country) values(?,?)";
        try {
            PreparedStatement preparedStatement =  connection.
                    prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,supplier.getName());
            preparedStatement.setString(2,supplier.getCountry());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while(resultSet.next()){
                supplier.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new CreateException("Can't create supplier");
        }finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    public void update(){
        Connection connection = CONNECTION_POOL.getConnection();
        String update = "update suppliers set country = ? where name = ?";
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement(update);
            preparedStatement.setString(1,"Egypt");
            preparedStatement.setString(2,"Trenker");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateException("Can't update");
        }finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    public void selectAll(){
        Connection connection = CONNECTION_POOL.getConnection();
        String select = "select * from suppliers";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while(resultSet.next()){
                System.out.println(resultSet.getLong("id") + " "
                        + resultSet.getString("name") + " " + resultSet.getString("country"));
            }
        } catch (SQLException e) {
            throw new SelectException("Can't select data");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    public void delete(){
        Connection connection = CONNECTION_POOL.getConnection();
        String delete = "delete from suppliers where name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setString(1,"Trenker");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteException("Can't delete");
        }finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
