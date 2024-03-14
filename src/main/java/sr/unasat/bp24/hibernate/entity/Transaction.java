package sr.unasat.bp24.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private Long transactionId;

    @JsonIgnore
    private LocalDate date;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")  // This is the foreign key column in the Transactions table
    private User user;

    @ManyToOne
    @JoinColumn(name ="category_id")
    @JsonIgnore
    private Category category;

    private Double amount;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "income_id")
    private Income income;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "expense_id")
    private Expense expense;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }
}
