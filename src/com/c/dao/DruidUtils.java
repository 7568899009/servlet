package com.c.dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DruidUtils {

    private static DataSource dataSource = null;
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    private static PreparedStatement preparedStatement = null;

    //私有构造函数,防止实例化对象
    private DruidUtils() {
    }

    //    配置文件
    static {
        Properties properties = new Properties();
        try {
            //获取配置文件
            properties.load(DruidUtils.class.getClassLoader().getResourceAsStream("Druid.properties"));
            //通过工厂来获取数据库连接池对象
            dataSource = DruidDataSourceFactory.createDataSource(properties);
            //获取连接
            connection = dataSource.getConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取连接
    public static Connection connection() throws SQLException {
        return dataSource.getConnection();
    }


    /**
     * 释放资源
     *
     * @param statement
     */
    public static void closeStatement(Statement statement) {
        if ( statement != null ) {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 释放资源
     *
     * @param preparedStatement
     */
    public static void closePerparedStatement(PreparedStatement preparedStatement) {
        if ( preparedStatement != null ) {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 释放资源
     *
     * @param resultSet
     */
    public static void closeResultSet(ResultSet resultSet) {
        if ( resultSet != null ) {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 归还连接
     *
     * @param connection
     */
    public static void closeConnection(Connection connection) {
        if ( connection != null ) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 释放资源归还连接
     *
     * @param connection
     * @param statement
     * @param resultSet
     * @param preparedStatement
     */
    public static void closeCSRP(Connection connection, Statement statement, ResultSet resultSet, PreparedStatement preparedStatement) {
        closeConnection(connection);
        closeStatement(statement);
        closeResultSet(resultSet);
        closePerparedStatement(preparedStatement);
    }

    /**
     * 获取连接池的方法
     *
     * @return
     */
    public static DataSource getDataSource() {
        return dataSource;
    }
}
