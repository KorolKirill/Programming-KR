package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    private Connection connection;
    private  final String url = "jdbc:mysql://localhost:3306";
    private  final String user = "root";
    private final String password = "root";

    public DaoFactory getDaoFactory() {
        return new DaoFactory(this);
    }


    public DataBase()  {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,password);
            init();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("cannot connect to DB");
            e.printStackTrace();
        }
    }

    private void init() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("create table if not exists lotsdatabase.users " +
                    "(id INT key NOT NULL AUTO_INCREMENT," +
                    "login VARCHAR(50) not null," +
                    "password VARCHAR(50) not null," +
                    "balance float," +
                    "products varchar(400)," +
                    "lots varchar(400)," +
                    "admin bool)"
            );
            statement.execute("" +
                    "create table if not exists lotsdatabase.products (\n" +
                    "    id INT key NOT NULL AUTO_INCREMENT," +
                    "    name varchar(100) not null,\n" +
                    "    owner varchar(400) not null,\n" +
                    "    information text\n" +
                    ")");
            statement.execute("CREATE table if not exists lotsdatabase.lots (" +
                    "id INT key NOT NULL AUTO_INCREMENT," +
                    "name varchar(100) not null," +
                    "owner varchar(400) not null,"+
                    "information text,"+
                    "currentPrice float,"+
                    "betStep float,"+
                    "dateCreation DATETIME,"+
                    "winner varchar(400)"+
                    ")");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
