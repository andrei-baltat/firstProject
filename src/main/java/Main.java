import Repository.JsonRepository;
import Repository.PersistenceRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Expense;
import model.User;
import util.ExpenseTypes;

public class Main {
    private static final String FORMAT = "dd/MM/yyyy";
    public static List<Expense> expenseList;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static PersistenceRepository persistence = new JsonRepository();

    public static void main(String[] args) throws IOException {
        System.out.print("Log in with username: ");
        String username = reader.readLine();

        expenseList = persistence.getExpensesForUser(username);
        if(expenseList == null) {
            expenseList = new ArrayList<>();
        }
        System.out.println(expenseList);

        boolean continueWhile = true;
        while(continueWhile) {
            System.out.println();
            System.out.println("    -> 1. Add new expense");
            System.out.println("    -> 2. Delete by date");
            System.out.println("    -> 3. Sum of expense type");
            System.out.println("    -> 9. Exit");
            System.out.println();

            int option = Integer.parseInt(reader.readLine());
            switch (option) {
                case 1:
                    addExpense();
                    break;

                case 2:
                    deleteByDate();
                    break;

                case 3:
                    System.out.println("Total: " + choiceOfFiltering().intValue());
                    break;
                case 9:
                    User user = new User(username, expenseList);
                    persistence.write(user);
                    continueWhile = false;
                    break;
            }

        }
    }

    private static void deleteByDate() throws IOException {
        System.out.println("Insert a date: ");
        Date dateToPop = getDateFromKeyboard(FORMAT);

        expenseList.stream().forEach(item -> item.getDate());
    }

    public static void addExpense() throws IOException {
        Date date = getDateFromKeyboard(FORMAT);

        Integer amount;
        while (true){
            System.out.print("Insert an expense amount: ");

            try {
                amount = Integer.parseInt(reader.readLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Wrong number");
            }
        }

        System.out.print("Insert expense type ("
                + ExpenseTypes.FOOD + ", " + ExpenseTypes.INTERNET + ", "
                + ExpenseTypes.OTHER + ", " + ExpenseTypes.TRANSPORT + ", "
                + ExpenseTypes.UTILITIES + "): ");

        String type = getExpenseTypeFromInput();

        System.out.print("Write a comment: ");
        String comment = reader.readLine();

        expenseList.add(new Expense(date,amount,type,comment));

    }

    private static Date getDateFromKeyboard(String format) throws IOException {
        System.out.println("Insert date with format '" + format + "': ");
        Date date = new Date();

        while(true) {
            try{
                String dateString = reader.readLine();
                SimpleDateFormat formatter = new SimpleDateFormat(format);
                date = formatter.parse(dateString);
                break;
            } catch (ParseException e) {
                System.out.print("Wrong format! Insert again: ");
            }
        }

        return date;
    }

    private static Integer choiceOfFiltering() throws IOException {
//        System.out.println("    -> Would you like to filter?(Y/N)");
        boolean flag = true;

        while (flag){
            System.out.println("    -> Would you like to filter?(Y/N)");
            String yesOrNo = reader.readLine().toLowerCase();
            if (yesOrNo.equals("y")){

                System.out.println("    -> 1 or equal or =");
                System.out.println("    -> 2 or less or <");
                System.out.println("    -> 3 or bigger or >");

                while (true){
                    String choice = reader.readLine();
                    if(choice.equals("1")
                            || choice.equals("equal")
                            || choice.equals("=")) {
                        flag = false;
                        String expenseType = getExpenseTypeFromInput();
                        int value = valueToFilterBy();
                        return expenseList.stream()
                                .filter(item -> item.getCost() == value && item.getType().equals(expenseType))
                                .mapToInt(item -> item.getCost()).sum();

                    }else if(choice.equals("2")
                            || choice.equals("less")
                            || choice.equals("<")) {
                        flag = false;
                        String expenseType = getExpenseTypeFromInput();
                        int value = valueToFilterBy();
                        return expenseList.stream()
                                .filter(item -> item.getCost() < value && item.getType().equals(expenseType))
                                .mapToInt(item -> item.getCost()).sum();

                    }else if(choice.equals("3")
                            || choice.equals("bigger")
                            || choice.equals(">")) {
                        flag = false;
                        String expenseType = getExpenseTypeFromInput();
                        int value = valueToFilterBy();
                        return expenseList.stream()
                                .filter(item -> item.getCost() > value && item.getType().equals(expenseType))
                                .mapToInt(item -> item.getCost()).sum();

                    }
                    System.out.println("Incorrect input. Found: " + choice + ".Try again");
                }
            }

            if (yesOrNo.equals("n")){
                return expenseList.stream().mapToInt(item -> item.getCost()).sum();
            }

            System.out.println("Incorrect input. Found: " + yesOrNo + ".Try again");
        }

        return null;
    }


    private static int valueToFilterBy(){
        System.out.println("What would be the value after which you want to filter?");
        while (true){
            try {
                return Integer.valueOf(reader.readLine());
            } catch (IOException e) {
                System.out.println("Try again. I found");
            }
        }
    }

    private static String getExpenseTypeFromInput() throws IOException {
        System.out.println("What would be the expense after which you want to filter?");
        System.out.print(ExpenseTypes.FOOD + "\t" + ExpenseTypes.INTERNET + "\t" + ExpenseTypes.TRANSPORT + "\n"
                            + ExpenseTypes.UTILITIES + "\t" + ExpenseTypes.OTHER + "\n");
        while (true){
            String type = reader.readLine();
            if(!type.equals(ExpenseTypes.FOOD) && !type.equals(ExpenseTypes.INTERNET)
                    && !type.equals(ExpenseTypes.OTHER) && !type.equals(ExpenseTypes.TRANSPORT)
                    && !type.equals(ExpenseTypes.UTILITIES)){
                System.out.println("Insert correct type of expense");
            } else {
                return type;
            }

        }
    }


}
