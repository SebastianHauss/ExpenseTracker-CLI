# ExpenseTracker

**ExpenseTracker** is a simple command-line application for tracking your expenses. It allows users to add, view, update and delete expenses. You can also view summaries of your expense and track your spending by month.

## Features
- Add an expense with a description, amount, category, and date.
- Delete or update existing expenses by their ID.
- View a summary of all expenses.
- View a summary of expenses for a specific month.
- Set a monthly budget and get a warning when you exceed it.
- Export expenses to a CSV file.

## Installation

### Prerequisites
Make sure you have the following installed:
- [Java 8+](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Git](https://git-scm.com/)

### Steps
1. **Clone the repository:**
   ```bash
   git clone https://github.com/SebastianHauss/ExpenseTracker.git
   ```

2. **Navigate to the project directory:**
   ```bash
   cd ExpenseTracker
   ```

3. **Compile the project:**
   ```bash
   javac -d bin src/*.java
   ```

4. **Run the application:**
   ```bash
   java -cp bin ExpenseTracker
   ```

## Usage

Once the application is running, you can use the following commands to interact with the ExpenseTracker:

### Adding an Expense
To add an expense, use the following command:
```bash
$ expense-tracker add
Enter description: Lunch
Enter amount: 15.50
Enter category (optional): Food
```
You can leave the category empty, and it will default to "general". After adding, the expense will be assigned an ID.

### Listing All Expenses
To view all expenses:
```bash
$ expense-tracker list
```
This will display a list of all your expenses with their respective IDs, descriptions, amounts, categories, and dates.

### Updating an Expense
To update an existing expense:
```bash
$ expense-tracker update --id <expenseId>
```
You'll be prompted to enter a new description, amount, or category.

### Deleting an Expense
To delete an expense by ID:
```bash
$ expense-tracker delete --id <expenseId>
```
This removes the expense from your list.

### Viewing Summary of Expenses
To view a summary of all expenses:
```bash
$ expense-tracker summary
```
To view a summary for a specific month:
```bash
$ expense-tracker summary --month <monthNumber>
```
For example:
```bash
$ expense-tracker summary --month 8
```
This will show the summary for August.

## File Storage

- The application saves all expense data in a JSON file, allowing you to track expenses across multiple sessions.
- The data is automatically saved to `expenses.json` in the project directory.

## License
This project is licensed under the MIT License - see the [LICENSE](./LICENSE.md) file for details.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you'd like to change.
