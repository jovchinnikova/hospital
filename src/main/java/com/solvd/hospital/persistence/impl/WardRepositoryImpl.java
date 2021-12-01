package com.solvd.hospital.persistence.impl;

import com.solvd.hospital.domain.Ward;
import com.solvd.hospital.domain.exception.ProcessingException;
import com.solvd.hospital.persistence.ConnectionPool;
import com.solvd.hospital.persistence.PatientRepository;
import com.solvd.hospital.persistence.WardRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WardRepositoryImpl implements WardRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private final PatientRepository patientRepository = new PatientRepositoryImpl();

    @Override
    public void create(Ward ward, Long departmentId) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "insert into Wards (number,floor,department_id) values (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, ward.getNumber());
            preparedStatement.setInt(2, ward.getFloor());
            preparedStatement.setLong(3, departmentId);
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                ward.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new ProcessingException("Can't create ward " + e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    public List<Ward> getById(Long departmentId){
        List<Ward> result = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();
        String select = "select w.id, w.number, w.floor, p.first_name, p.last_name, p.age, p.diagnosis from Wards w left join Patients p on w.id = p.ward_id where department_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setLong(1,departmentId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Ward ward = new Ward();
                ward.setId(resultSet.getLong("id"));
                ward.setNumber(resultSet.getInt("number"));
                ward.setFloor(resultSet.getInt("floor"));
                ward.setPatients(patientRepository.getById(ward.getId()));
                result.add(ward);
            }
        } catch (SQLException e) {
            throw new ProcessingException("Can't select wards " + e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return result;
    }
}
