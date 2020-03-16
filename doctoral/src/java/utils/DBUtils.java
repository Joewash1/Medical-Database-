package utils;

/**
 * DB utilities
 */
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

public abstract class DBUtils {

    
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static Connection getConnection() throws NamingException, SQLException {
        Connection conn = null;
        try {

            String url = "jdbc:mysql://" + conf.Configuration.HOST + 
                    ":" + conf.Configuration.PORT + "/" + conf.Configuration.DATABASE + "?useEncoding=true&characterEncoding=UTF-8";
            conn = DriverManager.getConnection(url, conf.Configuration.USER, conf.Configuration.PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static void closeResource(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            // do nothing
        }
    }

    public static void closeResource(PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (Exception e) {
            // do nothing
        }
    }

    public static void closeResource(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            // do nothing
        }
    }

    public static void closeResource(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (Exception e) {
            // do nothing
        }
    }

    public static void closeResource(InputStream is) {
        try {
            if (is != null) {
                is.close();
            }
        } catch (Exception e) {
            // do nothing
        }
    }

    public static void closeResource(OutputStream os) {
        try {
            if (os != null) {
                os.close();
            }
        } catch (Exception e) {
            // do nothing
        }
    }

}