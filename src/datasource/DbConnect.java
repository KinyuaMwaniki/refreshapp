package datasource;

import java.sql.*;

public class DbConnect {

    private static DbConnect dbConnect = null;
    private static final String CONNECTIONSTRING = "jdbc:sqlite:budgeter.sqlite";
    

    private DbConnect() {

        getConnection();
        createBudgetTable();
    }
    
    
    public static DbConnect getInstance(){
    
        
        if (dbConnect == null) {
            
            dbConnect = new DbConnect();
        }
        
        return dbConnect;
    }
    

    public static Connection getConnection() {

        try {
            return DriverManager.getConnection(CONNECTIONSTRING);

        } catch (SQLException e) {

            System.out.println("getConnection()" + e.getMessage());
        }

        return null;
    }
    
        private boolean createBudgetTable(){
    
        String table = "budget";
        String createStatement = "CREATE TABLE " + table + " (rent INTEGER, deductions INTEGER, garbage INTEGER,"
                + "electricity INTEGER, gas INTEGER, shopping INTEGER, airtime INTEGER, tv INTEGER, "
                + "transport INTEGER, food INTEGER, entertainment INTEGER)";
        
        String insertStartData = "INSERT INTO budget(rent, deductions, garbage, electricity, gas, shopping, airtime, tv, transport, food, entertainment) "
                + "VALUES (200, 300, 400, 500, 600, 300, 200, 100, 800, 1000, 200)";

        System.out.println(createStatement);
        try {

            var connection = DbConnect.getConnection();
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null, null, table.toUpperCase(), null);
            
            if(resultSet.next()) {
            
                System.out.println("Table " + table + " exists.");
                connection.close();
            } else {
            
                Statement statement = connection.createStatement();
                statement.execute(createStatement);
                Statement insertStatement = connection.createStatement();
                insertStatement.execute(insertStartData);
                connection.close();
                return true;
            }
            
        } catch (Exception e) {
            
            System.out.println("createDataTable():"  + e.getMessage());
            return false;
        }

        return false;
    }
}
