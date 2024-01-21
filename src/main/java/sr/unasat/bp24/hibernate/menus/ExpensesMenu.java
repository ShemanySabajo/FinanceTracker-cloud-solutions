package sr.unasat.bp24.hibernate.menus;

import sr.unasat.bp24.hibernate.desingPatterns.builder.ExpenseBuilder;
import sr.unasat.bp24.hibernate.desingPatterns.observer.ExpenseObserver;
import sr.unasat.bp24.hibernate.desingPatterns.observer.ExpenseSubject;
import sr.unasat.bp24.hibernate.desingPatterns.observer.Subject;
import sr.unasat.bp24.hibernate.entity.*;
import sr.unasat.bp24.hibernate.services.CategoryService;
import sr.unasat.bp24.hibernate.services.ExpenseService;
import sr.unasat.bp24.hibernate.services.ExtraFeature;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static sr.unasat.bp24.hibernate.FinanceTracker.*;

public class ExpensesMenu {
    private final User user;
    private ExpenseService expenseService;
    private Subject subject;
    private ExpenseObserver observer;

    public ExpensesMenu(User user) {
        this.user = user;
        this.expenseService = new ExpenseService();
        this.subject = new ExpenseSubject();
        this.observer = new ExpenseObserver(user);
        this.subject.addObserver(observer);
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println(""
                    + CYAN_BOLD + "╔════════════════════╗\r\n"
                    + CYAN_BOLD + "       Expenses\r\n"
                    + CYAN_BOLD + "╚════════════════════╝\r\n"
                    + CYAN_BOLD + "1. Add quick Expense entry\r\n"
                    + CYAN_BOLD + "2. Add detailed Expense entry\r\n"
                    + CYAN_BOLD + "3. View Total Expenses\r\n"
                    + RED_BOLD + "4. Back to Home Menu" + ANSI_RESET);

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addQuickExpense(sc);
                    break;
                case 2:
                    addDetailedExpense(sc);
                    break;
                case 3:
                    viewTotalExpenses();
                    break;
                case 4:
                    exit = true;
                    subject.removeObserver(observer);
                    System.out.println("Home Menu");
            }
        }
    }

    private void addDetailedExpense(Scanner sc) {

        System.out.println("Enter expense details:");
        String details = sc.next();

        System.out.println("Select a Category");
        Category selectCategory = CategoryService.selectCategory();

        // Capture the month and year of the expense
        LocalDate expenseDate = ExtraFeature.selectDate();

        sc.nextLine();
        System.out.println("Enter payment method:");
        String paymentMethod = sc.next();

        // Handle the possibility of invalid input for amount
        double amount = 0.0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Amount: ");
                amount = sc.nextDouble();

                Transaction transaction = new Transaction();
                transaction.setAmount(amount);
                transaction.setDate(LocalDate.now());
                transaction.setUser(user);
                transaction.setCategory(selectCategory);

                Expense expense = new ExpenseBuilder()
                        .transaction(transaction)
                        .amount(amount)
                        .description(details)
                        .expenseDate(expenseDate)
                        .paymentMethod(paymentMethod)
                        .user(user)
                        .build();

                transaction.setExpense(expense);

                expenseService.addMonthlyExpense(transaction);

                subject.notifyObservers();

                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid numeric amount.");
                sc.next(); // Consume the invalid input to avoid an infinite loop
            }
        }

        System.out.println("Expense added successfully: "  + " - SRD " + amount);
    }

    private void addQuickExpense(Scanner sc) {

        System.out.println("Select a Category");
        Category selectCategory = CategoryService.selectCategory();

        // Capture the month and year of the expense
        LocalDate expenseDate = ExtraFeature.selectDate();

        // Handle the possibility of invalid input for amount
        double amount = 0.0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Amount: ");
                amount = sc.nextDouble();

                Transaction transaction = new Transaction();
                transaction.setAmount(amount);
                transaction.setDate(LocalDate.now());
                transaction.setUser(user);
                transaction.setCategory(selectCategory);

                Expense expense = new ExpenseBuilder()
                        .transaction(transaction)
                        .amount(amount)
                        .expenseDate(expenseDate)
                        .user(user)
                        .build();

                expense.setTransactions(transaction);
                transaction.setExpense(expense);

                expenseService.addMonthlyExpense(transaction);

                subject.notifyObservers();

                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid numeric amount.");
                sc.next(); // Consume the invalid input to avoid an infinite loop
            }
        }
    }

    void viewTotalExpenses() {

        List<Expense> expenseList = expenseService.getTotalMonthlyExpenses(user.getUserId());

        System.out.printf("%-10s%-20s%-10s%-15s%-15s\n", "ExpenseId", "Transaction ID", "Amount", "Description", "ExpenseDate");
        System.out.println("--------------------------------------------------------------------------------------------");

        String format = "%-10d%-20s%-10.2f%-15s%-15s";

        double totalExpenses = 0.0;

        for (Expense expense : expenseList) {

            String transactionsInfo = expense.getTransactions() != null
                    ? expense.getTransactions().getTransactionId().toString()
                    : "No entry";

            String descriptionInfo = expense.getDescription() != null
                    ? expense.getDescription()
                    : "No entry";

            System.out.printf(format, expense.getExpenseId(), transactionsInfo, expense.getAmount(),
                    descriptionInfo, expense.getExpenseDate());
            System.out.println(); // Move to the next line for the next record

            totalExpenses += expense.getAmount();
        }

        System.out.println("\nTotal Expenses: " + totalExpenses);

    }

}
