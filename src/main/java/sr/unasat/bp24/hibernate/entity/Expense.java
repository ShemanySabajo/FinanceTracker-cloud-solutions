package sr.unasat.bp24.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Expense {

    @Id
    @GeneratedValue
    @Column(name = "expense_id")
    private Long expenseId;

    @OneToOne(mappedBy = "expense")
    private Transaction transaction;
    private Double amount;
    private String description;
    @Column(name = "expense_date")
    @JsonIgnore
    private LocalDate expenseDate;

    @Column(name = "payment_method")
    private String paymentMethod;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")  // This is the foreign key column in the Transactions table
    private User user;


    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public Transaction getTransactions() {
        return transaction;
    }

    public void setTransactions(Transaction transaction) {
        this.transaction = transaction;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
