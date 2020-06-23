package com.company;
import java.io.*;
import java.sql.*;

public class Main {

    private static Statement stmt;
    private static ResultSet rs;

    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 1521;
        String sid = "orcl";
        String user = "SYS as sysdba ";
        String pwd = "65";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver is not found");
            e.printStackTrace();

        }
        String url = String.format("jdbc:oracle:thin:@%s:%d:%s", host, port, sid);

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException e) {
            System.out.println("Connection Failed : " + e.getMessage());

        }
        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }


        String query = "select Animals from Test";
        String path = "C:\\notes3.txt";
//        String path = "src/notes3.txt";
        FileWriter writerdel = new FileWriter(path);
        FileWriter writerdel2 = new FileWriter(path, true);
        writerdel2.write('E');
  //      writerdel2.flush();
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(path));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line+" no");
        }

        System.out.println(1234);
        try {

            // getting Statement object to execute query
            stmt = connection.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery(query);
            while (rs.next()) {
//                int id = rs.getInt(1);
                String id = rs.getString(1);
//                String name = rs.getString(2);
//                String author = rs.getString(3);
//                String itog = id + name + author;
//                System.out.println( id + name + author);
//                String itog = id ;
                System.out.println(id);


                // запись всей строки
//                    writerdel2.write(id);
                // запись по символам

                writerdel2.append(id +System.lineSeparator());
//                    writer.append('E');
//                    writer.append('\n');

                writerdel2.flush();

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                connection.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                stmt.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                rs.close();
            } catch (SQLException se) { /*can't do anything */ }
        }

    }

}


