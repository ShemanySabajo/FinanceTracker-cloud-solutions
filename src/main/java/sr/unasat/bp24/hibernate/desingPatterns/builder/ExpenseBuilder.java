package sr.unasat.bp24.hibernate.desingPatterns.builder;

import sr.unasat.bp24.hibernate.entity.Expense;
import sr.unasat.bp24.hibernate.entity.Transaction;
import sr.unasat.bp24.hibernate.entity.User;

import java.time.LocalDate;

public class ExpenseBuilder {

    private Expense expense;

    public ExpenseBuilder() {
        this.expense = new Expense();
    }

    public ExpenseBuilder expenseId(Long expenseId) {
        expense.setExpenseId(expenseId);
        return this;
    }

    public ExpenseBuilder transaction(Transaction transaction) {
        expense.setTransactions(transaction);
        return this;
    }

    public ExpenseBuilder amount(Double amount) {
        expense.setAmount(amount);
        return this;
    }

    public ExpenseBuilder description(String description) {
        expense.setDescription(description);
        return this;
    }

    public ExpenseBuilder expenseDate(LocalDate expenseDate) {
        expense.setExpenseDate(expenseDate);
        return this;
    }

    public ExpenseBuilder paymentMethod(String paymentMethod) {
        expense.setPaymentMethod(paymentMethod);
        return this;
    }

    public ExpenseBuilder user(User user) {
        expense.setUser(user);
        return this;
    }

    public Expense build() {
        return expense;
    }

}
