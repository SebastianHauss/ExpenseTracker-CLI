import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ExpenseTracker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to the Expense Tracker!");

        while (running) {
            System.out.print("$ expense tracker ");
            String input = scanner.nextLine().trim();
            String[] commandParts = input.split(" ");
            String command = commandParts.length > 0 ? commandParts[0] : "";

            switch (command) {
                case "add":
                    handleAddExpense(commandParts);
                    break;
                case "list":
                    handleListExpenses();
                    break;
                case "delete":
                    handleDeleteExpense(commandParts);
                    break;
                case "summary":
                    handleSummary(commandParts);
                    break;
                case "update":
                    handleUpdateExpense(commandParts);
                    break;
                case "exit":
                    running = false;
                    System.out.println("Exiting Expense Tracker...");
                    break;
                default:
                    System.out.println("Unknown command. Available commands: add, list, delete, summary, update, exit.");
            }
        }
        scanner.close();
    }

    private static void handleAddExpense(String[] commandParts) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter description: ");
        String description = scanner.nextLine().trim();

        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Enter category (optional): ");
        String category = scanner.nextLine().trim();
        if (category.isEmpty()) {
            category = "general";
        }

        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yy"));

        Expense expense = new Expense(0, date, description, amount, category);
        ExpenseStorage.addExpense(expense);

        System.out.println("Expense added successfully (ID: " + expense.getId() + ")");
    }

    private static void handleListExpenses() {
        List<Expense> expenses = ExpenseStorage.getAllExpenses();
        if (expenses.isEmpty()) {
            System.out.println("No expenses added yet.");
            return;
        }

        printHeader();
        for (Expense expense : expenses) {
            printExpense(expense);
        }
    }

    // Formatting
    public static void printHeader() {
        System.out.printf("%-4s %-12s %-12s %-10s %-10s%n",
                "ID", "Date", "Description", "Amount", "Category");
    }

    public static void printExpense(Expense expense) {
        System.out.printf("%-4d %-12s %-12s $ %-9.2f %-10s%n",
                expense.getId(),
                expense.getDate(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getCategory());
    }

    private static void handleDeleteExpense(String[] commandParts) {
        if (commandParts.length < 3 || !commandParts[1].equals("--id")) {
            System.out.println("Invalid command. Use: delete --id <expenseId>");
            return;
        }
        try {
            int id = Integer.parseInt(commandParts[2]);
            Expense expense = ExpenseStorage.getExpenseById(id);

            if (expense != null) {
                ExpenseStorage.deleteExpenseById(id);
            } else {
                System.out.println("ID not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid ID format.");
        }
    }

    private static void handleSummary(String[] commandParts) {
        if (commandParts.length == 1) {
            // Summary of all expenses
            List<Expense> expenses = ExpenseStorage.getAllExpenses();
            double total = expenses.stream().mapToDouble(Expense::getAmount).sum();
            System.out.println("Total expenses: € " + total);

            // Summary of monthly expenses
        } else if (commandParts.length == 3 && commandParts[1].equals("--month")) {
            try {
                int month = Integer.parseInt(commandParts[2]);
                if (month < 1 || month > 12) {
                    System.out.println("Invalid month. Please enter a month number between 1 and 12.");
                    return;
                }
                double monthlyTotal = ExpenseStorage.getMonthlyExpenseSummary(month);

                String monthName = Month.of(month).name();
                monthName = monthName.charAt(0) + monthName.substring(1).toLowerCase();

                System.out.println("Total expenses for month " + monthName + ": € " + monthlyTotal);

            } catch (NumberFormatException e) {
                System.out.println("Invalid month. Please enter a valid month number (1-12).");
            }
        } else {
            System.out.println("Invalid command. Use: summary or summary --month <monthNumber>");
        }
    }

    private static void handleUpdateExpense(String[] commandParts) {
        if (commandParts.length < 3 || !commandParts[1].equals("--id")) {
            System.out.println("Invalid command. Use: update --id <expenseId>");
            return;
        }

        int id = Integer.parseInt(commandParts[2]);
        Expense expense = ExpenseStorage.getExpenseById(id);

        if (expense == null) {
            System.out.println("Expense with ID: " + id + " not found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new description (" + expense.getDescription() + "): ");
        String newDescription = scanner.nextLine().trim();
        if (!newDescription.isEmpty()) {
            expense.setDescription(newDescription);
        }

        System.out.print("Enter new amount (" + expense.getAmount() + "): ");
        String amountInput = scanner.next().trim();
        if (!amountInput.isEmpty()) {
            double newAmount = Double.parseDouble(amountInput);
            expense.setAmount(newAmount);
        }

        System.out.print("Enter new category (" + expense.getCategory() + "): ");
        String newCategory = scanner.next().trim();
        if (!newCategory.isEmpty()) {
            expense.setCategory(newCategory);
        }

        ExpenseStorage.saveExpenses();
        System.out.println("Expense updated successfully.");
    }
}
