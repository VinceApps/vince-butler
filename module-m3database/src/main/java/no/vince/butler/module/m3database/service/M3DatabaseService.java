package no.vince.butler.module.m3database.service;

import com.ibm.as400.access.AS400JDBCConnectionPool;
import com.ibm.as400.access.AS400JDBCConnectionPoolDataSource;
import com.ibm.as400.access.ConnectionPoolException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class M3DatabaseService
{
    private AS400JDBCConnectionPool connectionPool;

    @Value("${db.m3.serverName}")
    private String serverName;

    @Value("${db.m3.user}")
    private String user;

    @Value("${db.m3.password}")
    private String password;


    private AS400JDBCConnectionPool getConnectionPool()
    {
        if (connectionPool == null)
        {
            AS400JDBCConnectionPoolDataSource dataSource = new AS400JDBCConnectionPoolDataSource();
            dataSource.setServerName(serverName);
            dataSource.setUser(user);
            dataSource.setPassword(password);

            connectionPool = new AS400JDBCConnectionPool(dataSource);
        }
        return connectionPool;
    }

    public List<Map<String, Object>> executeQuery(String query, Object... params) throws ConnectionPoolException, SQLException
    {
        Connection connection = getConnectionPool().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        if (params != null)
        {
            int parameterIndex = 1;
            for (Object param : params)
            {
                preparedStatement.setObject(parameterIndex++, param);
            }
        }

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        ResultSet result = preparedStatement.executeQuery();
        ResultSetMetaData metaData = result.getMetaData();
        while (result.next())
        {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            for (int columnIndex = 1; columnIndex <= metaData.getColumnCount(); columnIndex++)
            {
                Object value = result.getObject(columnIndex);
                if (value instanceof String)
                {
                    value = ((String) value).trim();
                }
                map.put(metaData.getColumnName(columnIndex), value);
            }
            list.add(map);
        }
        return list;
    }
}
