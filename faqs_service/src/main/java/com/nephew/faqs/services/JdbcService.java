package com.nephew.faqs.services;

import com.nephew.faqs.entities.Company;
import com.nephew.faqs.entities.FAQs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class JdbcService {

    private static final Logger logger = Logger.getLogger(JdbcService.class.getName());

    private String company_id = "company_id";
    private String id = "id";
    private String answer = "answer";
    private String question = "question";
    private String questionIsAnswered = "question_is_answered";


    @Autowired
    private ConnectionService connectionService;

    public Company findCompanyByUrl(String companyUrl) {
         try {
            Connection connection = connectionService.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM company WHERE company_url=?");
             preparedStatement.setString(1, companyUrl);
            ResultSet resultSet = preparedStatement.executeQuery();
            Company company;
            if (resultSet.next()) {
                company = new Company(resultSet.getLong(id));
            } else {
                throw new SQLException("Company with url " + companyUrl + " was not found.");
            }
            closeResources(resultSet, preparedStatement);
            return company;
        } catch (SQLException e) {
            logger.warning("Something went wrong while attempting to find the company url: " + companyUrl);
             return null;
        }
    }

    public FAQs findFaqById(long faqId) throws SQLException {
        Connection connection = connectionService.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM faqs WHERE id=" + faqId);
        ResultSet resultSet = preparedStatement.executeQuery();
        FAQs faq;
        if(resultSet.next()) {
            faq = assignResultSetToFaq(resultSet);
        } else {
            throw new SQLException("FAQ with id " + id + " was not found.");
        }
        closeResources(resultSet, preparedStatement);
        return faq;
    }

    public void deleteFaqById(long faqId) {
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = connectionService.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM faqs WHERE id=?");
            preparedStatement.setLong(1, faqId);
            preparedStatement.executeUpdate();
            closeResources(connection, preparedStatement);
        } catch (SQLException e) {
            logger.warning("Something went wrong while attempting to delete faq with id: " + faqId);
        } finally {
            if (preparedStatement != null) {
                closeResources(preparedStatement);
            }
        }
    }

    public FAQs assignResultSetToFaq(ResultSet resultSet) throws SQLException {
        FAQs faq = new FAQs();
        faq.setId(resultSet.getLong(id));
        faq.setCompany(new Company(resultSet.getLong(company_id)));
        faq.setAnswer(resultSet.getString(answer));
        faq.setQuestion(resultSet.getString(question));
        faq.setQuestionIsAnswered(resultSet.getBoolean(questionIsAnswered));
        return faq;
    }

    public List<FAQs> findAllFaqsByCompanyUrl(String companyUrl) throws SQLException {
        Company company = findCompanyByUrl(companyUrl);
        if(company == null) {
            logger.warning("Company with url: " + companyUrl + " was not found.");
            return new ArrayList<>();
        }
        Connection connection = connectionService.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM faqs WHERE company_id=? ORDER BY id");
        preparedStatement.setLong(1, company.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        List<FAQs> faqsList = new ArrayList<>();
        while (resultSet.next()) {
            FAQs faqs = assignResultSetToFaq(resultSet);
            faqsList.add(faqs);
        }
        closeResources(resultSet, preparedStatement);
        return faqsList;
    }

    public List<FAQs> findAnsweredFaqsByCompanyUrl(String companyUrl) throws SQLException {
        Company company = findCompanyByUrl(companyUrl);
        if(company == null) {
            logger.warning("Company with url: " + companyUrl + " was not found.");
            return new ArrayList<>();
        }
        Connection connection = connectionService.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM faqs WHERE company_id=? AND question_is_answered=true ORDER BY id;");
        preparedStatement.setLong(1, company.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        List<FAQs> faqsList = new ArrayList<>();
        while (resultSet.next()) {
            FAQs faqs = assignResultSetToFaq(resultSet);
            faqsList.add(faqs);
        }
        closeResources(resultSet, preparedStatement);
        return faqsList;
    }

    public void insertFaq(FAQs faq, String companyUrl) {
        if (faq == null || faq.getQuestion() == null) {
            logger.warning("Attempted to insert a null FAQs. Returning without conducting insertion.");
            return;
        }

        if (faq.getAnswer() == null) {
            logger.info("FAQs does not contain an answer. Setting FAQs question_is_answered to false.");
            faq.setQuestionIsAnswered(false);
        }

        if (faq.getCompany() == null) {
            logger.info("The provided faq does not have a company. Getting company by companyUrl: " + companyUrl);
            faq.setCompany(findCompanyByUrl(companyUrl));
        }

        try (Connection connection = connectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO faqs (question, answer, question_is_answered, company_id) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setString(1, faq.getQuestion());
            preparedStatement.setString(2, faq.getAnswer());
            preparedStatement.setBoolean(3, faq.isQuestionIsAnswered());
            preparedStatement.setLong(4, faq.getCompany().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.warning("Something went wrong while attempting to insert faq: " + faq.toString());
            e.printStackTrace();
        }
    }

    public void insertCompany(Company egi) {

    }

    private void closeConnection(Connection connection) {
        try {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            logger.info("The connection was closed.");
        }
        } catch (SQLException e) {
            logger.warning("Something went wrong while attempting to close the connection.");
            e.printStackTrace();
        }
    }

    private void closePreparedStatement(PreparedStatement preparedStatement) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
                logger.info("The prepared statement was closed.");
            }
        } catch (SQLException e) {
            logger.warning("Something went wrong while attempting to close the prepared statement.");
            e.printStackTrace();
        }
    }

    private void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
                logger.info("The result set was closed.");
            }
        } catch (SQLException e) {
            logger.warning("Something went wrong while attempting to close the result set.");
            e.printStackTrace();
        }
    }

    private void closeResources(Connection connection, ResultSet resultSet, PreparedStatement preparedStatement) {
        closeResultSet(resultSet);
        closePreparedStatement(preparedStatement);
        closeConnection(connection);
    }

    private void closeResources(Connection connection, PreparedStatement preparedStatement) {
        closePreparedStatement(preparedStatement);
        closeConnection(connection);
    }

    private void closeResources(ResultSet resultSet, PreparedStatement preparedStatement) {
        closeResultSet(resultSet);
        closePreparedStatement(preparedStatement);
    }

    private void closeResources(PreparedStatement preparedStatement) {
        closePreparedStatement(preparedStatement);
    }

}
