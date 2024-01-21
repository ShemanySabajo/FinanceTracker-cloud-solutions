package sr.unasat.bp24.hibernate.menus;

import sr.unasat.bp24.hibernate.entity.Budget;
import sr.unasat.bp24.hibernate.entity.Expense;
import sr.unasat.bp24.hibernate.entity.Income;
import sr.unasat.bp24.hibernate.entity.User;
import sr.unasat.bp24.hibernate.services.BudgetService;
import sr.unasat.bp24.hibernate.services.ExpenseService;
import sr.unasat.bp24.hibernate.services.IncomeService;

import static sr.unasat.bp24.hibernate.FinanceTracker.*;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ReportsMenu {
    private final User user;
    private final BudgetMenu budgetMenu;
    private final IncomeMenu incomeMenu;
    private final ExpensesMenu expensesMenu;
    ExpenseService expenseService;

    public ReportsMenu(User user, BudgetMenu budgetMenu, IncomeMenu incomeMenu, ExpensesMenu expensesMenu, List<Expense> expenseList) {
        this.user = user;
        this.budgetMenu = budgetMenu;
        this.incomeMenu = incomeMenu;
        this.expensesMenu = expensesMenu;
        this.expenseService = new ExpenseService();
    }

    public void Menu() {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println(""
                    + CYAN_BOLD + "╔════════════════════╗\r\n"
                    + CYAN_BOLD + "        Reports\r\n"
                    + CYAN_BOLD + "╚════════════════════╝\r\n"
                    + CYAN_BOLD + "1. Monthly Report\r\n"
                    + CYAN_BOLD + "2. Expense History Report\r\n"
                    + CYAN_BOLD + "3. Top 10 expenses for the month report\r\n"
                    + RED_BOLD + "4. Back to Home Menu" + ANSI_RESET);

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    monthlyReport();
                    break;
                case 2:
                    expenseHistoryReport();
                    break;
                case 3:
                    getTop10Expenses();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Returning to Home Menu");
            }
        }
    }

    private void getTop10Expenses() {

        System.out.println("Top 10 expenses for the month report:");

        List<Expense> expenseList = expenseService.getTopExpensesForTheMonth(user.getUserId());

        System.out.printf("%-10s%-20s%-10s%-20s%-15s%-15s%-15s\n", "#", "Transaction ID", "Amount", "Description",
                "Month", "ExpenseDate", "Payment method");
        System.out.println("--------------------------------------------------------------------------------------------");

        String format = "%-10d%-20s%-10.2f%-20s%-15s%-15s%-15s";

        double totalExpenses = 0.0;

        for (int i = 0; i < expenseList.size(); i++) {

            Expense expense = expenseList.get(i);

            String transactionsInfo = expense.getTransactions() != null
                    ? expense.getTransactions().getTransactionId().toString()
                    : "No entry";

            String descriptionInfo = expense.getDescription() != null
                    ? expense.getDescription()
                    : "No entry";

            String paymentInfo = expense.getPaymentMethod() != null
                    ? expense.getPaymentMethod()
                    : "No entry";

            System.out.printf(format, i + 1, transactionsInfo, expense.getAmount(),
                    descriptionInfo, expense.getExpenseDate().getMonth(), expense.getExpenseDate(), paymentInfo);
            System.out.println(); // Move to the next line for the next record

            totalExpenses += expense.getAmount();
        }

        System.out.println("\nTotal Expenses: " + totalExpenses);
    }


    private void monthlyReport() {
        // Display user information
        System.out.println("User: " + user.getUserName());

        // Display budget information
        System.out.println("\nBudget Information:");
        budgetMenu.viewMonthlyBudget();

        // Display income information
        System.out.println("\nIncome Information:");
        incomeMenu.viewTotalIncome();

        // Display expenses information
        System.out.println("\nExpenses Information:");
        expensesMenu.viewTotalExpenses();

        BudgetService budgetService = new BudgetService();
        Budget monthlyBudget = budgetService.getMonthlyBudget(user.getUserId());

        IncomeService incomeService = new IncomeService();
        double totalIncomeAmount = incomeService.getTotalIncomeAmount(user.getUserId());

        ExpenseService expenseService = new ExpenseService();
        double totalExpensesAmount = expenseService.getTotalExpensesAmount(user.getUserId());

        double balance = totalIncomeAmount - totalExpensesAmount;

        boolean withinBudget = false;

        if (balance > 0) {
            withinBudget = true;
        }

        if (balance == 0) {
            withinBudget = true;
        }

        String budgetOverUnderText = "";

        if (withinBudget) {
            budgetOverUnderText = "with in your budget";
        } else {
            budgetOverUnderText = "over your budget";
        }

        System.out.println("\n\n"
                + CYAN_BOLD + "Balance for the month: SRD " + monthlyBudget.getBudgetMonth().getMonth() + " is " + balance + "\r\n"
                + CYAN_BOLD + "Budget for the month: " + monthlyBudget.getBudgetMonth().getMonth() + " is " + monthlyBudget.getAmount() + "\r\n\n"
                + CYAN_BOLD + "You are " + budgetOverUnderText + "\r\n" + ANSI_RESET);
    }

    private void expenseHistoryReport() {
        System.out.println("Expense History Report:");

        List<Expense> expenseList = expenseService.getTotalMonthlyExpenses(user.getUserId());

        System.out.printf("%-10s%-20s%-10s%-20s%-15s%-15s%-15s\n", "ExpenseId", "Transaction ID", "Amount", "Description",
                "Month", "ExpenseDate", "Payment method");
        System.out.println("--------------------------------------------------------------------------------------------");

        String format = "%-10d%-20s%-10.2f%-20s%-15s%-15s%-15s";

        double totalExpenses = 0.0;

        for (Expense expense : expenseList) {

            String transactionsInfo = expense.getTransactions() != null
                    ? expense.getTransactions().getTransactionId().toString()
                    : "No entry";

            String descriptionInfo = expense.getDescription() != null
                    ? expense.getDescription()
                    : "No entry";

            String paymentInfo = expense.getPaymentMethod() != null
                    ? expense.getPaymentMethod()
                    : "No entry";

            System.out.printf(format, expense.getExpenseId(), transactionsInfo, expense.getAmount(),
                    descriptionInfo , expense.getExpenseDate().getMonth(), expense.getExpenseDate(), paymentInfo);
            System.out.println(); // Move to the next line for the next record

            totalExpenses += expense.getAmount();
        }

        System.out.println("\nTotal Expenses: " + totalExpenses);

    }

}
