package sr.unasat.bp24.hibernate.services;

import sr.unasat.bp24.hibernate.repository.ExpenseRepo;
import sr.unasat.bp24.hibernate.repository.TransactionRepo;
import sr.unasat.bp24.hibernate.entity.Expense;
import sr.unasat.bp24.hibernate.entity.Transaction;

import java.util.List;

public class ExpenseService {
    ExpenseRepo expenseRepo;

    public ExpenseService() {
        expenseRepo = new ExpenseRepo();
    }

    public void addMonthlyExpense(Transaction transaction) {
        TransactionRepo transactionRepo = new TransactionRepo();
        transactionRepo.addMonthlyExpense(transaction);
    }

    public List<Expense> getTotalMonthlyExpenses(Long userId) {
        return expenseRepo.getTotalMonthlyExpenses(userId);
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
        return expenseRepo.getTopExpensesForTheMonth(userId);
    }

    public List<Expense> getTotalExpenses(Long userId) {
        return expenseRepo.getTotalExpenses(userId);
    }

    public Expense getExpenseById(Long selectedExpenseId) {
        return expenseRepo.getExpenseById(selectedExpenseId);
    }
    public Transaction getTransactionById(Long transactionId) {
        return expenseRepo.getTransactionById(transactionId);
    }
    public void updateTransactionAndExpense(Transaction transaction, Expense expense) {
        TransactionRepo transactionRepo = new TransactionRepo();
        transactionRepo.updateTransaction(transaction);
        // Since the Expense is associated with the Transaction, it will be updated automatically when the Transaction is updated
        expenseRepo.updateExpense(expense);
    }
    public void deleteExpenseAndTransaction(Transaction transaction) {
        try {
            // Fetch the associated expense from the transaction
            Expense expense = transaction.getExpense();

            // Delete the expense and the transaction
            expenseRepo.deleteExpenseAndTransaction(expense);

        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }
}
