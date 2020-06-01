package model;

import java.util.List;
import java.util.Objects;

public class User {
    private String name;
    private List<Expense> expenses;

    public User() {}

    public User(final String name, final List<Expense> expenses) {
        this.name = name;
        this.expenses = expenses;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(final List<Expense> expenses) {
        this.expenses = expenses;
    }

    // de ce am facut override la equal?
    @Override
    public boolean equals(final Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        final User user = (User) o;
        return Objects.equals(name, user.name);
    }

    // de ce am facut override la hascode?
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
