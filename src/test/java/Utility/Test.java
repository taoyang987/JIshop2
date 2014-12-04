package Utility;

import java.sql.*;
import java.util.HashMap;

public class Test {

    public static void main(String args[]) throws Throwable {

    }

    public static HashMap<String, String> GetHashVaule(String sql) {
        HashMap<String, String> result = new HashMap<>();
        String url = "jdbc:sqlserver://192.168.6.125:1433;databaseName=NG0004;user=sa;password=####admin#123456";//sa身份连接
        // Declare the JDBC objects.
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url);

            // Create and execute an SQL statement that returns some data.
            stmt = con.createStatement();
//            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sql);

            ResultSetMetaData rsmd = rs.getMetaData();
            int count = rsmd.getColumnCount();
            String[] name = new String[count];
            for (int i = 0; i < count; i++) {
                name[i] = rsmd.getColumnName(i + 1);
            }
//            rs.last();
//            int rows = rs.getRow(); //获取resultSet的大小
//            rs.beforeFirst();
            // Iterate through the data in the result set and display it.
//            while (rs.next()) {
//                for (int i = 0; i < count; i++) {
//                    result.put(name[i], rs.getString(i+1));
//                }
//                return result;
//            }
            rs.next();
            for (int i = 0; i < count; i++) {
                result.put(name[i], rs.getString(i + 1));
            }
            return result;
        }

        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (Exception ignored) {
                }
            if (stmt != null)
                try {
                    stmt.close();
                } catch (Exception ignored) {
                }
            if (con != null)
                try {
                    con.close();
                } catch (Exception ignored) {
                }
        }
        return result;
    }

    public static Object GetVaule(String sql, String fieldName) {
        Object result = null;
        String url = "jdbc:sqlserver://192.168.6.125:1433;databaseName=NG0004;user=sa;password=####admin#123456";//sa身份连接
        // Declare the JDBC objects.
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url);

            // Create and execute an SQL statement that returns some data.
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                result = rs.getObject(fieldName);
            }
        }

        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (Exception ignored) {
                }
            if (stmt != null)
                try {
                    stmt.close();
                } catch (Exception ignored) {
                }
            if (con != null)
                try {
                    con.close();
                } catch (Exception ignored) {
                }
        }
        return result;
    }

    //    public static ResultSet GetResultSet(String sql) {
//        Object result = null;
//        String url = "jdbc:sqlserver://192.168.6.125:1433;databaseName=NG0004;user=sa;password=####admin#123456";//sa身份连接
//        // Declare the JDBC objects.
//        Connection con = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//
//        try {
//            // Establish the connection.
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            con = DriverManager.getConnection(url);
//
//            // Create and execute an SQL statement that returns some data.
//            stmt = con.createStatement();
//            rs = stmt.executeQuery(sql);
//
//            // Iterate through the data in the result set and display it.
//            while (rs.next()) {
//                return rs;
//            }
//        }
//
//        // Handle any errors that may have occurred.
//        catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (rs != null)
//                try {
//                    rs.close();
//                } catch (Exception e) {
//                }
//            if (stmt != null)
//                try {
//                    stmt.close();
//                } catch (Exception e) {
//                }
//            if (con != null)
//                try {
//                    con.close();
//                } catch (Exception e) {
//                }
//        }
//        return rs;
//    }
    public static Object GetVaule(String sql) {
        Object result = null;
        String url = "jdbc:sqlserver://192.168.6.125:1433;databaseName=NG0004;user=sa;password=####admin#123456";//sa身份连接
        // Declare the JDBC objects.
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url);

            // Create and execute an SQL statement that returns some data.
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                result = rs.getObject(1);
            }
        }

        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (Exception ignored) {
                }
            if (stmt != null)
                try {
                    stmt.close();
                } catch (Exception ignored) {
                }
            if (con != null)
                try {
                    con.close();
                } catch (Exception ignored) {
                }
        }
        return result;
    }

    public static Object ExeSQL(String sql) {
        String url = "jdbc:sqlserver://192.168.6.125:1433;databaseName=NG0004;user=sa;password=####admin#123456";//sa身份连接
        // Declare the JDBC objects.
        Connection con = null;
        Statement stmt = null;

        try {
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url);

            // Create and execute an SQL statement that returns some data.
            stmt = con.createStatement();
            stmt.execute(sql);

        }

        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        } finally {
//            if (rs != null)
//                try {
//                    rs.close();
//                } catch (Exception e) {
//                }
            if (stmt != null)
                try {
                    stmt.close();
                } catch (Exception ignored) {
                }
            if (con != null)
                try {
                    con.close();
                } catch (Exception ignored) {
                }
        }
        return null;
    }

    public static Object GetIntValue(String sql, String fieldName) {
        return GetVaule(sql, fieldName);
    }

    public static Integer GetIntValue(String sql) {
        return (Integer) GetVaule(sql);
    }

    public static Short GetshtValue(String sql) {
        return (Short) GetVaule(sql);
    }

    public static String GetStrValue(String sql) {
        return (String) GetVaule(sql);
    }

    public static String GetOrderIDByreliid(String reliid) {
        return GetStrValue("SELECT orderid from dbo.ec_eordermst WHERE relid='" + reliid + "';");
    }

    public static String GetMstidByReliid(String reliid) {
        return GetStrValue("SELECT mstid FROM dbo.wsp_ordermst WHERE relid='" + reliid + "'");
    }
}