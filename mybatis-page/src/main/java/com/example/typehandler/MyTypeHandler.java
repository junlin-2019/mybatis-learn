package com.example.typehandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyTypeHandler implements TypeHandler {

    //private static String KEY = "123456";

    /**
     * 通过preparedStatement对象设置参数，将T类型的数据存入数据库。
     *
     * @param ps
     * @param i
     * @param parameter
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        try {
            String encrypt = EncryptUtil.encode(((String) parameter).getBytes());
            ps.setString(i, encrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 通过列名或者下标来获取结果数据，也可以通过CallableStatement获取数据。
    @Override
    public Object getResult(ResultSet rs, String columnName) throws SQLException {
        String result = rs.getString(columnName);
        if (result != null && result != "") {
            try {
                return EncryptUtil.decode(result.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
        String result = rs.getString(columnIndex);
        if (result != null && result != "") {
            try {
                return EncryptUtil.decode(result.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String result = cs.getString(columnIndex);
        if (result != null && result != "") {
            try {
                return EncryptUtil.decode(result.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}