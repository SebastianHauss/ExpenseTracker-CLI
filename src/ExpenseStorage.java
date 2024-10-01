import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

public class ExpenseStorage {
    private static final String FILE_PATH = "expenses.json";
    private static List<Expense> expenses = new ArrayList<>();
    private static int nextId = 1;

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    static {
        loadExpenses();
    }

    // Reads existing expenses from a file ("expenses.json") and loads them into the expenses list.
    public static void loadExpenses() {
        File file = new File(FILE_PATH);

        // Check if file exists, if not, create empty file
        if (!file.exists()) {
            try (FileWriter writer = new FileWriter(FILE_PATH)) {
                writer.write("[]"); // Initialize the file with an empty JSON array
                expenses = new ArrayList<>();
                System.out.println("File created: " + FILE_PATH);

            } catch (IOException e) {
                System.out.println("Error creating the file.");
                return;
            }
        }

        // Load expenses from the file
        try (FileReader reader = new FileReader(FILE_PATH)) {
            expenses = gson.fromJson(reader, new TypeToken<List<Expense>>() {
            }.getType());
            if (expenses == null) {
                expenses = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("Error reading the file");
        }

        for (Expense expense : expenses) {
            if (expense.getId() >= nextId) {
                nextId = expense.getId() + 1;
            }
        }
    }

    public static void addExpense(Expense expense) {
        expense.setId(expenses.size() + 1);
        expenses.add(expense);
        renumberIds();
        saveExpenses();
    }

    public static List<Expense> getAllExpenses() {
        return expenses;
    }

    public static Expense getExpenseById(int id) {
        for (Expense expense : expenses) {
            if (expense.getId() == id) {
                return expense;
            }
        }
        return null;
    }

    public static void deleteExpenseById(int id) {
        Expense expense = getExpenseById(id);
        if (expense != null) {
            expenses.remove(expense);  // Remove by object, not index
            renumberIds();
            saveExpenses();
            System.out.println("Expense deleted successfully.");
        } else {
            System.out.println("Error: Expense with ID " + id + " not found.");
        }
    }

    public static void saveExpenses() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(expenses, writer);
        } catch (IOException e) {
            System.out.println("Error saving expenses.");
        }
    }

    public static double getMonthlyExpenseSummary(int month) {
        double total = 0;
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        for (Expense expense : expenses) {
            LocalDate expenseDate = LocalDate.parse(expense.getDate(), DateTimeFormatter.ofPattern("dd.MM.yy"));
            if (expenseDate.getMonthValue() == month && expenseDate.getYear() == currentYear) {
                total += expense.getAmount();
            }
        }
        return total;
    }

    public static void renumberIds() {
        for (int i = 0; i < expenses.size(); i++) {
            expenses.get(i).setId(i + 1);
        }
    }
}
