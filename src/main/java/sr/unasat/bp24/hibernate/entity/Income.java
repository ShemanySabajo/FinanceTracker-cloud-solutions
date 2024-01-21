package sr.unasat.bp24.hibernate.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Income {
    @Id
    @GeneratedValue
    @Column(name = "income_id")
    private Long incomeId;
    @OneToOne(mappedBy = "income")
    private Transaction transaction;
    private Double amount;
    private String description;
    @Column(name = "income_date")
    private LocalDate incomeDate;

    @ManyToOne
    @JoinColumn(name = "user_id")  // This is the foreign key column in the Transactions table
    private User user;

    public Long getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(Long incomeId) {
        this.incomeId = incomeId;
    }

    public Transaction getTransaction() {
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

    public LocalDate getIncomeDate() {
        return incomeDate;
    }
    public void setIncomeDate(LocalDate incomeDate) {
        this.incomeDate = incomeDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
