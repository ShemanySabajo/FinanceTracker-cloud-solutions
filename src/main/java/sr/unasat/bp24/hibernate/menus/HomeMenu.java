package sr.unasat.bp24.hibernate.menus;

import sr.unasat.bp24.hibernate.desingPatterns.observer.ExpenseObserver;
import sr.unasat.bp24.hibernate.desingPatterns.observer.ExpenseSubject;
import sr.unasat.bp24.hibernate.desingPatterns.observer.Subject;
import sr.unasat.bp24.hibernate.entity.Expense;
import sr.unasat.bp24.hibernate.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static sr.unasat.bp24.hibernate.FinanceTracker.*;

public class HomeMenu {

    private final User user;


    public HomeMenu(User user) {
        this.user = user;

    }

    public void menu() {
        System.out.println("Welcome " + user.getUserName());
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        BalanceMenu balanceMenu = new BalanceMenu(user);  // Initialize outside the switch
        BudgetMenu budgetMenu = new BudgetMenu(user);  // Initialize to null
        IncomeMenu incomeMenu = new IncomeMenu(user);  // Initialize to null
        ExpensesMenu expensesMenu = new ExpensesMenu(user);  // Initialize to null
        List<Expense> expenseList = new ArrayList<>();

        while (!exit) {
            System.out.println(""
                    + CYAN_BOLD + "╔═══════════════════╗\r\n"
                    + CYAN_BOLD + "  Financial Tracker\r\n"
                    + CYAN_BOLD + "╚═══════════════════╝\r\n"
                    + CYAN_BOLD + "1. Check Balance\r\n"
                    + CYAN_BOLD + "2. Budget\r\n"
                    + CYAN_BOLD + "3. Income\r\n"
                    + CYAN_BOLD + "4. Expenses\r\n"
                    + CYAN_BOLD + "5. Reports\r\n"
                    + RED_BOLD + "6. Exit" + ANSI_RESET);

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    balanceMenu.menu();
                    break;
                case 2:
                    budgetMenu.menu();
                    break;
                case 3:
                    incomeMenu.menu();
                    break;
                case 4:
                    expensesMenu.menu();
                    break;
                case 5:
                    ReportsMenu reportsMenu = new ReportsMenu(user, budgetMenu, incomeMenu, expensesMenu,expenseList);
                    reportsMenu.Menu();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Returning to Login Menu");
            }
        }
    }
}
