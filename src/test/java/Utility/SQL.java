package Utility;

import java.sql.*;

public class SQL {

    private static Connection con;// = null;
    private static Statement stmt;// = null;
    private static ResultSet rs = null;

    public static Object GetValue(String sql ,String fieldName) {
        Object result = null;
        try {
            DBConnect();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                result = rs.getObject(fieldName);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
           DBClose();
        }
        if(result==null)
        {
            System.out.println("查询结果为空");
        }
        return result;
    }

    public static String GetStrValue(String sql ,String fieldName) {
        return (String) GetValue(sql, fieldName);
    }

    public static Integer GetIntValue(String sql ,String fieldName) {
        return (Integer) GetValue(sql, fieldName);
    }

    public static void ExeSQL(String sql ) {
        try {
            DBConnect();
            stmt.execute(sql);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            DBClose();
        }
    }



    private static void DBConnect()  {
        DBClose();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            String url = "jdbc:sqlserver://10.0.20.128:1433;databaseName=NG0004;user=sa;password=123456";
//            url = "jdbc:sqlserver://10.0.20.128:1433;databaseName=NG0004;user=sa;password=123456";
            Connection con2 = DriverManager.getConnection(url);
            // Create and execute an SQL statement that returns some data.
            stmt = con2.createStatement();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
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
    }
//
//
//    private static void DBConnect()  {
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            url = "jdbc:sqlserver://10.0.20.128:1433;databaseName=NG0004;user=sa;password=123456";//sa身份连接
////            url = "jdbc:sqlserver://10.0.20.128:1433;databaseName=NG0004;user=sa;password=123456";
//            con = DriverManager.getConnection(url);
//            // Create and execute an SQL statement that returns some data.
//            stmt = con.createStatement();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
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
//    }

    private static void DBClose(){
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
//        stmt=null;
//        con=null;
    }

    public static void setCon(Connection con) {
        SQL.con = con;
    }
}
