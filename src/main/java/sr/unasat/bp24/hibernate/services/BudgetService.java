package sr.unasat.bp24.hibernate.services;

import sr.unasat.bp24.hibernate.dao.BudgetDao;
import sr.unasat.bp24.hibernate.entity.Budget;

public class BudgetService {

    BudgetDao budgetDao;

    public BudgetService() {
        budgetDao = new BudgetDao();
    }

    public Budget addMonthlyBudget(Budget budget) {
        return budgetDao.createMonthlyBudget(budget);
    }

    public Budget getMonthlyBudget(Long userId) {
        return budgetDao.getMonthlyBudget(userId);
    }
}
