package Repository;

import java.util.List;
import model.Expense;
import model.User;

public interface PersistenceRepository {

    List<User> readAll();
    List<Expense> getExpensesForUser(String userName);
    void write(User user);

}
