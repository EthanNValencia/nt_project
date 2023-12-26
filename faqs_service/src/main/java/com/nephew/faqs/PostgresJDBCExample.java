package com.nephew.faqs;

import com.nephew.faqs.entities.Company;
import com.nephew.faqs.entities.FAQs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Going to swap this ms over to jdbc.
public class PostgresJDBCExample {

    public static void main(String[] args) {
       // JDBC URL, username, and password of PostgreSQL server
        String url = "jdbc:postgresql://127.0.0.1:5432/nt_db";
        String user = "nt_user";
        String password = "password";

        // JDBC variables for opening, closing, and managing connection
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Open a connection
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
            Company company = findCompanyByUrl("npt", connection);
            // Define the SELECT query
            String selectQuery = "SELECT * FROM faqs WHERE company_id=" + company.getId();

            // Create a PreparedStatement
            preparedStatement = connection.prepareStatement(selectQuery);

            // Execute the query and get the result set
            resultSet = preparedStatement.executeQuery();

            // Process the result set
            List<FAQs> faqsList = new ArrayList<>();
            while (resultSet.next()) {
                boolean isQuestionAnswered = resultSet.getBoolean("question_is_answered");
                long id = resultSet.getLong("id");
                long companyId = resultSet.getLong("company_id");
                String question = resultSet.getString("question");
                String answer = resultSet.getString("answer");
                FAQs faqs = new FAQs(id, isQuestionAnswered, question, answer, company);
                faqsList.add(faqs);
                System.out.println(faqs);
            }
        } catch (SQLException e) {
            System.out.println("Query execution failure.");
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                    System.out.println("Connection closed.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private static Company findCompanyByUrl(String companyUrl, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM company WHERE company_url='" + companyUrl + "'");
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            long id = resultSet.getLong("id");
            return new Company(id);
        }
        throw new SQLException("Company with url " + companyUrl + " was not found.");
    }

}
