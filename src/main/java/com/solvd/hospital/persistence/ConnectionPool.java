package com.solvd.hospital.persistence;

import com.solvd.hospital.domain.exception.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ConnectionPool {

    private volatile static ConnectionPool instance;

    private volatile static  List<Connection> connections;
    private volatile static  List<Connection> runningConnections = new ArrayList<>();
    private static final Integer poolSize = Config.getPoolSize();

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    try {
                        Class.forName(Config.getDRIVER());
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    instance = new ConnectionPool();
                    List<Connection> connections = new ArrayList<>();
                    IntStream.range(0,poolSize)
                            .forEach(index -> {
                                    connections.add(createConnection());
                            });
                    ConnectionPool.connections = connections;
                }
            }
        }
        return instance;
    }

    public static Connection createConnection(){
        Connection connection;
        try {
            connection = DriverManager.getConnection(Config.getURL(),Config.getUSER(),Config.getPASSWORD());
        } catch (SQLException e) {
            throw new ConnectionException("Can't create connection. " + e.getMessage());
        }
        return connection;
    }

    public synchronized Connection getConnection(){
        Connection connection = connections
                .remove(connections.size() - 1);
        runningConnections.add(connection);
        return connection;
    }

    public synchronized void releaseConnection(Connection connection){
        connections.add(connection);
        runningConnections.remove(connection);
    }
}
