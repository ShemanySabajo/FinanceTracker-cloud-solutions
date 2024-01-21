package sr.unasat.bp24.hibernate.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Budget {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "budget_month")
    private LocalDate budgetMonth;
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "user_id")  // This is the foreign key column in the Transactions table
    private User user;

    public Budget() {
    }

    public LocalDate getBudgetMonth() {
        return budgetMonth;
    }

    public void setBudgetMonth(LocalDate budgetMonth) {
        this.budgetMonth = budgetMonth;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
