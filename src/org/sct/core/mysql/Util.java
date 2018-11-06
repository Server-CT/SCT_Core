package org.sct.core.mysql;

import org.sct.core.Core;
import org.sct.core.mysql.Dao.BasicDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Util {

    /**
     * 读取数据库内的int型信息，通常用于等级等等查询
     * @param sql 数据库语法
     * @return 查询到的信息
     */
    public static int getIntFromSql(String sql) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        int result = 0;

        try {
            connection = BasicDao.getConnection();
            ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();

            if(resultSet.next()) {
                result = resultSet.getInt(1);
            }
        }catch (Exception e) {
            Core.info("错误" + e.getMessage());
        }
        return result;
    }

    /**
     * 读取数据库内的String型信息，例如玩家名字
     * @param sql
     * @return
     */
    public static String getStringFromSql(String sql) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        String result = null;

        try {
            connection = BasicDao.getConnection();
            ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();

            if(resultSet.next()) {
                result = resultSet.getString(1);
            }
        }catch (Exception e) {
            Core.info("错误" + e.getMessage());
        }
        return result;
    }

    /**
     * 读取数据库内的double型信息，例如血量
     * @param sql
     * @return
     */
    public static double getDoubleFromSql(String sql) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        double result = 0.0;

        try {
            connection = BasicDao.getConnection();
            ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();

            if(resultSet.next()) {
                result = resultSet.getDouble(1);
            }
        }catch (Exception e) {
            Core.info("错误" + e.getMessage());
        }
        return result;
    }

    /**
     * 设置数据库特定的信息的时候可以使用，例如设置储存在数据库内的玩家等级
     * @param sql 插入数据库的语法
     */
    public static void normalConnect(String sql) {
        Connection connection = null;
        PreparedStatement ps = null;
        String result = null;
        try {
            connection = BasicDao.getConnection();
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            Core.info("错误: " + e.getMessage());
        }
    }

}
