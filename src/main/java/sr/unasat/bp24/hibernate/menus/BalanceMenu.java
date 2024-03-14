package sr.unasat.bp24.hibernate.menus;

import sr.unasat.bp24.hibernate.entity.Budget;
import sr.unasat.bp24.hibernate.entity.User;
import sr.unasat.bp24.hibernate.services.BudgetService;
import sr.unasat.bp24.hibernate.services.ExpenseService;
import sr.unasat.bp24.hibernate.services.IncomeService;

import java.util.Scanner;

import static sr.unasat.bp24.hibernate.FinanceTracker.*;

public class BalanceMenu {

    private final User user;

    public BalanceMenu(User user) {
        this.user = user;
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

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

        if (monthlyBudget.getAmount()== totalExpensesAmount) {
            withinBudget = true;
        }

        String budgetOverUnderText = "";

        if(withinBudget) {
            budgetOverUnderText = "with in your budget";
        } else {
            budgetOverUnderText = "over your budget";
        }

        while (!exit) {

            System.out.println(""
                    + CYAN_BOLD + "╔═══════════════════╗\r\n"
                    + CYAN_BOLD + "     Balance\r\n"
                    + CYAN_BOLD + "╚═══════════════════╝\r\n"
                    + CYAN_BOLD + "Current Balance: SRD " + balance + "\r\n"
                    + CYAN_BOLD + "Total Monthly Income : SRD " + totalIncomeAmount + "\r\n"
                    + CYAN_BOLD + "Total Monthly Expenses: SRD " + totalExpensesAmount + "\r\n"
                    + CYAN_BOLD + "Current Month:  " + monthlyBudget.getBudgetMonth().getMonth() + "\r\n"
                    + CYAN_BOLD + "Monthly Budget: " + monthlyBudget.getAmount() + "\r\n\n"
                    + CYAN_BOLD + "You are " + budgetOverUnderText + "\r\n"
                    + CYAN_BOLD + "\r\n"
                    + RED_BOLD + "1. Exit" + ANSI_RESET);

            int choice = sc.nextInt();

            if (choice == 1) {
                exit = true;
                System.out.println("Returning to Home Menu");
            }
        }

    }
}
