package sr.unasat.bp24.hibernate.desingPatterns.observer;

import sr.unasat.bp24.hibernate.entity.Budget;
import sr.unasat.bp24.hibernate.entity.User;
import sr.unasat.bp24.hibernate.services.BudgetService;
import sr.unasat.bp24.hibernate.services.ExpenseService;
import sr.unasat.bp24.hibernate.services.IncomeService;

public class ExpenseObserver implements Observer {

    private final User user;

    public ExpenseObserver(User user) {
        this.user = user;
    }

    @Override
    public void update(Subject subject) {

        BudgetService budgetService = new BudgetService();
        Budget monthlyBudget = budgetService.getMonthlyBudget(user.getUserId());

        IncomeService incomeService = new IncomeService();
        double totalIncomeAmount = incomeService.getTotalIncomeAmount(user.getUserId());

        ExpenseService expenseService = new ExpenseService();
        double totalExpensesAmount = expenseService.getTotalExpensesAmount(user.getUserId());

        double balance = totalIncomeAmount - totalExpensesAmount;

        boolean withinBudget = false;

        if (monthlyBudget.getAmount() > totalExpensesAmount) {
            withinBudget = true;
        }

        if (monthlyBudget.getAmount() == totalExpensesAmount) {
            withinBudget = true;
        }

        String budgetOverUnderText = "";

        if (withinBudget) {
            budgetOverUnderText = "within your budget";
            System.out.println("You are " + budgetOverUnderText);
        } else {
            budgetOverUnderText = "over your budget !";

            System.out.println("You are " + balance + " " + budgetOverUnderText);

            System.out.println("Your monthly budget for the month " +
                    monthlyBudget.getBudgetMonth().getMonth() + " is " + monthlyBudget.getAmount());

            System.out.println("You total expenses for the month " + monthlyBudget.getBudgetMonth().getMonth() + " are "
                    + totalExpensesAmount);
        }

    }
}
