package org.example.tallerpoo.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {

    private Connection con;
    private PreparedStatement pStatement = null;
    private Statement statement = null;
    private static DB DBInstance;

    private DB() {
        try {
            setCon(con = DriverManager.getConnection("jdbc:mysql://localhost/TallerPoo", "admin","admin"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DB getDBInstance() {
        if(DBInstance == null){
            DBInstance = new DB();
        }
        return DBInstance;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public void insertRegistry(String name, String desc, String employeeName, String employeeLastName, String category, String date, String price){

        try {
            pStatement = con.prepareStatement("insert into register(name, description, id_employee, id_category, work_date, price) values (?,?,?,?,?,?)");

            pStatement.setString(1, name);
            pStatement.setString(2,desc);
            pStatement.setString(3, String.valueOf(getEmployeeByName(employeeName, employeeLastName)));
            pStatement.setString(4, String.valueOf(getCategoryByName(category)));
            pStatement.setString(5,date);
            pStatement.setString(6,price);


            pStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertEmployee(String name, String lastName){
        try {
            pStatement = con.prepareStatement("insert into employee(name, lastname)" +" values (?,?)");
            pStatement.setString(1,name);
            pStatement.setString(2,lastName);

            pStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertCategory(String name){
        try {
            pStatement = con.prepareStatement("insert into category(name)" + " values (?)");
            pStatement.setString(1,name);
            pStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getEmployeeByName(String name, String lastName){

        int id = -1;

        try {
            statement = con.createStatement();

            ResultSet resultSet = statement.executeQuery("select id as empleado from employee where name = '" + name + "' and lastName = '" + lastName + "';");

            if (resultSet.next()) {
                id = resultSet.getInt("empleado");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return id;

    }

    public int getCategoryByName(String name){
        int id = -1;

        try {
            statement = con.createStatement();

            ResultSet resultSet = statement.executeQuery("select id from category where name = '" + name + "'");

            if(resultSet.next()){
                id = resultSet.getInt("id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();

        ResultSet rs = null;

        try {
            rs = con.createStatement().executeQuery("select name from category");

            while (rs.next()) {
                categories.add(rs.getString("name"));

            }
        }catch(SQLException e){
                throw new RuntimeException(e);
            }

        return categories;
    }

    public List<String> getChoresByEmployee(String employee, String firstDate, String lastDate){

        List<String> list = new ArrayList<>();

        ResultSet rs = null;

        try {
            rs = con.createStatement().executeQuery("select e.name as name, e.lastName as lastName, r.name as chore, r.description, c.name as category_name, r.work_date as 'date' " +
                    "from register as r " +
                    "inner join employee as e on r.id_employee = e.id " +
                    "inner join category as c on c.id = r.id_category " +
                    "where work_date between '" + firstDate + "' and '" + lastDate + "'" +
                    "order by e.lastName;");

            while (rs.next()) {
                list.add("empleado: " + rs.getString("name") + " " + rs.getString("lastName") + ", tarea: " + rs.getString("chore") + ", descripción: " + rs.getString("description") + ", categoria: " + rs.getString("category_name") + ", fecha: " + rs.getString("date"));

            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return list;

    }

    public List<String> getChoresByCategory(String category){

        List<String> list = new ArrayList<>();

        ResultSet rs = null;

        try {
            rs = con.createStatement().executeQuery("select e.name, count(r.id) as 'count'" +
                    "from register as r " +
                    "inner join employee as e on r.id_employee = e.id " +
                    "inner join category as c on c.id = r.id_category " +
                    "where c.id = '" + getCategoryByName(category) + "'" +
                    "group by r.id_employee;");

            while (rs.next()) {
                list.add("Empleado:" + rs.getString("name") + ", Cantidad" + rs.getString("count"));

            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return list;

    }

}
