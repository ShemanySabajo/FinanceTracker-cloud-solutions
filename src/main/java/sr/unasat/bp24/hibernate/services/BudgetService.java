package sr.unasat.bp24.hibernate.services;

import sr.unasat.bp24.hibernate.repository.BudgetRepo;
import sr.unasat.bp24.hibernate.entity.Budget;

public class BudgetService {

    BudgetRepo budgetRepo;

    public BudgetService() {
        budgetRepo = new BudgetRepo();
    }

    public Budget addMonthlyBudget(Budget budget) {
        return budgetRepo.createMonthlyBudget(budget);
    }

    public Budget getMonthlyBudget(Long userId) {
        return budgetRepo.getMonthlyBudget(userId);
    }
}
