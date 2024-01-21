package sr.unasat.bp24.hibernate.services;

import sr.unasat.bp24.hibernate.dao.IncomeDao;
import sr.unasat.bp24.hibernate.dao.TransactionDao;
import sr.unasat.bp24.hibernate.entity.Income;
import sr.unasat.bp24.hibernate.entity.Transaction;

import java.util.List;

public class IncomeService {

    IncomeDao incomeDao;

    public IncomeService() {
        incomeDao = new IncomeDao();
    }

    public void addMonthlyIncome(Transaction transaction) {
        TransactionDao transactionDao = new TransactionDao();
        transactionDao.addMonthlyIncome(transaction);
    }

    public List<Income> getTotalIcome(Long userId) {
       return incomeDao.getTotalIcome(userId);
    }

    public double getTotalIncomeAmount (Long userId) {

        List<Income> incomeList = getTotalIcome(userId);

        double totalIcome = 0.0;

        for (Income income : incomeList) {
            totalIcome += income.getAmount();
        }

        return totalIcome;
    }

    public Income getIncomeById(int selectedIncome) {
       return incomeDao.getIncomeById(selectedIncome);
    }

    public Income updateIcome(Income income) {
        return incomeDao.updateIcome(income);
    }

    public void delete(Income selectedIncome) {
        incomeDao.delete(selectedIncome);
    }
}
