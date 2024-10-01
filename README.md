# ExpenseTracker

**ExpenseTracker** is a command-line app to manage and track expenses. You can add, view, update, delete expenses, and see spending summaries by month.

This project is part of the **[roadmap.sh](https://roadmap.sh)** community. You can view it [here](https://roadmap.sh/projects/expense-tracker).

## Features
- Add an expense with description, amount, category, and date.
- Update or delete expenses by ID.
- View all expenses or a summary for a specific month.

## Installation

### Prerequisites
Ensure you have:
- [Java 8+](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Git](https://git-scm.com/)

### Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/SebastianHauss/ExpenseTracker.git
   ```
2. Navigate to the project:
   ```bash
   cd ExpenseTracker
   ```
3. Compile the project:
   ```bash
   javac -d bin src/*.java
   ```
4. Run the app:
   ```bash
   java -cp bin ExpenseTracker
   ```

## Usage

### Add an Expense
```bash
expense-tracker add
```
Follow prompts to enter the description, amount, and category (optional).

### View Expenses
```bash
expense-tracker list
```

### Update an Expense
```bash
expense-tracker update --id <expenseId>
```

### Delete an Expense
```bash
expense-tracker delete --id <expenseId>
```

### View Expense Summary
```bash
expense-tracker summary
```
For a specific month:
```bash
expense-tracker summary --month <monthNumber>
```

## Data Storage
- Expenses are saved in a `expenses.json` file for persistence across sessions.

## License
This project is under the MIT License. See the [LICENSE](./LICENSE.md) file for more details.

## Contributing
Feel free to open issues or submit pull requests for improvements.

---
