import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Expense {

    private int id;
    private String date;
    private String description;
    private double amount;
    private String category;

    public Expense(int id, String date, String description, double amount, String category) {
        this.id = id;
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yy"));
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Date: " + date + ", Description: " + description + ", Amount: " + amount + ", Category: " + category;
    }
}
