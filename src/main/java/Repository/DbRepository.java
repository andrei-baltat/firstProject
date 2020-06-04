package Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Expense;
import model.User;

public class DbRepository implements PersistenceRepository {

    private final String USERNAME = "root";
    private final String PASSWORD = "andrei123";
    private final String CONNECTION_URL = "jdbc:mysql://localhost:3306/expense_project?serverTimezone=UTC";

    @Override
    public List<Expense> getExpensesForUser(final String userName) {
        List<Expense> expenses = new ArrayList<>();
        Integer userId = null;
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE username = ?");
            statement.setString(1, userName);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                userId = resultSet.getInt(1);
            }

            if(userId == null) {
                return null;
            }

            PreparedStatement expenseStatement = connection.prepareStatement("Select * from expense where id_user = ?");
            expenseStatement.setInt(1, userId);

            resultSet = expenseStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                int cost = resultSet.getInt(2);
                String type = resultSet.getString(3);
                String comment = resultSet.getString(4);
                Date date = resultSet.getDate(6);
                expenses.add(new Expense(id,date,cost,type,comment,userId));
            }

            return expenses;



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void write(final User user) {

    }
}
/*
S
O
L
I
D
*/