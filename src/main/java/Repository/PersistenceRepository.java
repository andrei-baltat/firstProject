package Repository;

import java.util.List;
import model.Expense;
import model.User;

public interface PersistenceRepository {

    List<Expense> getExpensesForUser(String userName);
    void write(User user);

}
