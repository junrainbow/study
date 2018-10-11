package com.jun.mapper.type.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-08-24 10:39
 */
public class StringTypeHandler implements TypeHandler<String> {
    public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        String val = parameter+"来自StringTypeHandler-setParameter方法";
        ps.setString(i,val);
    }

    public String getResult(ResultSet rs, String columnName) throws SQLException {
        String retVal = rs.getString(columnName);
        retVal = retVal+"来自StringTypeHandler-getResult";
        return retVal;
    }

    public String getResult(ResultSet rs, int columnIndex) throws SQLException {
        String retVal = rs.getString(columnIndex);
        retVal = retVal+"来自StringTypeHandler-getResult";
        return retVal;
    }

    public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
