package sr.unasat.bp24.hibernate.menus;

import sr.unasat.bp24.hibernate.entity.Budget;
import sr.unasat.bp24.hibernate.entity.User;
import sr.unasat.bp24.hibernate.services.BudgetService;
import sr.unasat.bp24.hibernate.services.ExtraFeature;

import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;

import static sr.unasat.bp24.hibernate.FinanceTracker.*;

public class BudgetMenu {
    private final User user;

    BudgetService budgetService;

    public BudgetMenu(User user) {
        budgetService = new BudgetService();
        this.user = user;
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println(""
                    + CYAN_BOLD + "╔═══════════════════╗\r\n"
                    + CYAN_BOLD + "     Budget\r\n"
                    + CYAN_BOLD + "╚═══════════════════╝\r\n"
                    + CYAN_BOLD + "1. Insert Monthly Budget\r\n"
                    + CYAN_BOLD + "2. View Monthly Budget\r\n"
                    + CYAN_BOLD + "\r\n"
                    + RED_BOLD + "3. Exit" + ANSI_RESET);

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    insertMonthlyBudget(sc);
                    break;
                case 2:
                    viewMonthlyBudget();
                    break;
                case 3:
                    exit = true;
                    System.out.println("Returning to Home Menu");
            }
        }
    }

    private void insertMonthlyBudget(Scanner sc) {

        System.out.println("" + CYAN_BOLD + "Insert Monthly Budget:" + ANSI_RESET);

        double monthlyBudget = sc.nextDouble();  // Store the monthly budget in the field

        Budget budget = new Budget();

        Month month = ExtraFeature.selectMonth();
        LocalDate currentDate = LocalDate.now();

        currentDate = currentDate.withMonth(month.getValue());
        currentDate = currentDate.withDayOfMonth(1); // add the 1st of the month to prevent format errors bv. 2024-02-30

        budget.setBudgetMonth(currentDate);

        budget.setUser(user);
        budget.setAmount(monthlyBudget);

        Budget savedbudget = budgetService.addMonthlyBudget(budget);

        System.out.println("Saved amount SRD "+ savedbudget.getAmount() + " for the month: "
                + savedbudget.getBudgetMonth().getMonth());

    }

    void viewMonthlyBudget() {
        Budget budget = budgetService.getMonthlyBudget(user.getUserId());
        System.out.println("Budget for the month " + budget.getBudgetMonth().getMonth() + ": " + budget.getAmount());
    }
}
