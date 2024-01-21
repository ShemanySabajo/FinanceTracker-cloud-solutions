package sr.unasat.bp24.hibernate.services;

import sr.unasat.bp24.hibernate.dao.ExpenseDao;
import sr.unasat.bp24.hibernate.dao.TransactionDao;
import sr.unasat.bp24.hibernate.entity.Expense;
import sr.unasat.bp24.hibernate.entity.Transaction;

import java.util.List;

public class ExpenseService {
    ExpenseDao expenseDao;

    public ExpenseService() {
        expenseDao = new ExpenseDao();
    }

    public void addMonthlyExpense(Transaction transaction) {
        TransactionDao transactionDao = new TransactionDao();
        transactionDao.addMonthlyExpense(transaction);
    }

    public List<Expense> getTotalMonthlyExpenses(Long userId) {
        return expenseDao.getTotalMonthlyExpenses(userId);
    }

    public double getTotalExpensesAmount (Long userId) {

        List<Expense> expenses = getTotalMonthlyExpenses(userId);

        double totalExpenses = 0.0;

        for (Expense expense : expenses) {
            totalExpenses += expense.getAmount();
        }

        return totalExpenses;
    }

    public List<Expense> getTopExpensesForTheMonth(Long userId) {
        return expenseDao.getTopExpensesForTheMonth(userId);
    }

    public List<Expense> getTotalExpenses(Long userId) {
        return expenseDao.getTotalExpenses(userId);
    }
}
