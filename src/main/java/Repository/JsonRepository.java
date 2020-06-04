package Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Expense;
import model.User;

public class JsonRepository implements PersistenceRepository {

    private List<User> readAll() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            User[] users = mapper.readValue(new File("data.json"), User[].class);

            return new ArrayList<>(Arrays.asList(users));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Expense> getExpensesForUser(final String userName) {
        List<User> users = this.readAll();
        if(users == null) {
            return null;
        }

        for(User user: users) {
            if(user.getName().equals(userName)) {
                return user.getExpenses();
            }
        }

        return null;
    }

    @Override
    public void write(final User user) {
        List<User> users = this.readAll();
        if(users == null) {
            users = new ArrayList<>();
        }

        int userIndex = users.indexOf(user);
        if(userIndex == -1) {
            users.add(user);
        } else {
            users.set(userIndex, user);
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("data.json"), users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
