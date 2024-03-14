package sr.unasat.bp24.hibernate.services;

import sr.unasat.bp24.hibernate.repository.IncomeRepo;
import sr.unasat.bp24.hibernate.repository.TransactionRepo;
import sr.unasat.bp24.hibernate.entity.Income;
import sr.unasat.bp24.hibernate.entity.Transaction;

import java.util.List;

public class IncomeService {

    IncomeRepo incomeRepo;

    public IncomeService() {
        incomeRepo = new IncomeRepo();
    }

    public void addMonthlyIncome(Transaction transaction) {
        TransactionRepo transactionRepo = new TransactionRepo();
        transactionRepo.addMonthlyIncome(transaction);
    }

    public List<Income> getTotalIncome(Long userId) {
       return incomeRepo.getTotalIncome(userId);
    }

    public double getTotalIncomeAmount (Long userId) {

        List<Income> incomeList = getTotalIncome(userId);

        double totalIncome = 0.0;

        for (Income income : incomeList) {
            totalIncome += income.getAmount();
        }

        return totalIncome;
    }

    public Income getIncomeById(Long selectedIncome) {
       return incomeRepo.getIncomeById(Math.toIntExact(selectedIncome));
    }

    public void updateMonthlyIncome(Transaction transaction) {
        TransactionRepo transactionRepo = new TransactionRepo();
        transactionRepo.updateTransaction(transaction);
    }
    
    public void delete(Income selectedIncome) {
        incomeRepo.delete(selectedIncome);
    }

    public Transaction updateTransaction(Transaction transaction) {
        TransactionRepo transactionRepo = new TransactionRepo();
        return transactionRepo.updateTransaction(transaction);
    }

    public Transaction getTransactionById(Long transactionId) {
        TransactionRepo transactionRepo = new TransactionRepo();
        return transactionRepo.getTransactionById(transactionId);
    }

    public Income updateIncome(Income income) {
        IncomeRepo incomeRepo = new IncomeRepo();
        return incomeRepo.updateIncome(income);
    }
    public void updateTransactionAndIncome(Transaction transaction, Income income) {
        TransactionRepo transactionRepo = new TransactionRepo();
        transactionRepo.updateTransaction(transaction);

        IncomeRepo incomeRepo = new IncomeRepo();
        incomeRepo.updateIncome(income);
    }
    public void deleteIncomeAndTransaction(Income selectedIncome) {
        Transaction transaction = selectedIncome.getTransaction();

        // Call the delete method in TransactionRepo to delete the associated transaction
        TransactionRepo transactionRepo = new TransactionRepo();
        transactionRepo.deleteTransaction(transaction);

        // Now, delete the income from the IncomeRepo
        incomeRepo.delete(selectedIncome);
    }
}
