package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.domain.Medication;
import com.solvd.hospital.domain.exception.CreateException;
import com.solvd.hospital.persistence.ConnectionPool;
import com.solvd.hospital.persistence.MedicationRepository;

import java.sql.*;

public class MedicationRepositoryImpl implements MedicationRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Medication medication) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "insert into Medications (name,form,dosage,price) values (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,medication.getName());
            preparedStatement.setString(2,medication.getForm());
            preparedStatement.setDouble(3,medication.getDosage());
            preparedStatement.setBigDecimal(4,medication.getPrice());

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while ((resultSet.next())){
                medication.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new CreateException("Can't create medication");
        }

    }
}
