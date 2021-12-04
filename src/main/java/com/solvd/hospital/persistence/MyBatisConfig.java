package com.solvd.hospital.persistence;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisConfig {

    private static SqlSessionFactory SQL_SESSION_FACTORY;

    static{
        try {
            SQL_SESSION_FACTORY = MyBatisConfig.createSessionFactory();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static SqlSessionFactory createSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static SqlSessionFactory getSessionFactory(){
        return SQL_SESSION_FACTORY;
    }
}
