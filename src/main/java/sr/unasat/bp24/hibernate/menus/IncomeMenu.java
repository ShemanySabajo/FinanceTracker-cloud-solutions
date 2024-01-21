package sr.unasat.bp24.hibernate.menus;

import sr.unasat.bp24.hibernate.entity.Category;
import sr.unasat.bp24.hibernate.entity.Income;
import sr.unasat.bp24.hibernate.entity.Transaction;
import sr.unasat.bp24.hibernate.entity.User;
import sr.unasat.bp24.hibernate.services.CategoryService;
import sr.unasat.bp24.hibernate.services.IncomeService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static sr.unasat.bp24.hibernate.FinanceTracker.*;

public class IncomeMenu {
    private final User user;
    private double totalIncome;  // Field to store the total income
    private List<Income> incomeList;

    IncomeService incomeService;

    public IncomeMenu(User user) {
        this.incomeService = new IncomeService();
        this.user = user;
        this.incomeList = new ArrayList<>();
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println(""
                    + CYAN_BOLD + "╔═══════════════════╗\r\n"
                    + CYAN_BOLD + "       Income\r\n"
                    + CYAN_BOLD + "╚═══════════════════╝\r\n"
                    + CYAN_BOLD + "1. Add Income\r\n"
                    + CYAN_BOLD + "2. View Total Income\r\n"
                    + CYAN_BOLD + "3. Update Income\r\n"
                    + CYAN_BOLD + "4. Delete Income\r\n"
                    + RED_BOLD + "5. Home Menu" + ANSI_RESET);

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addMonthlyIncome(sc);
                    break;
                case 2:
                    viewTotalIncome();
                    break;
                case 3:
                    updateIncome(sc);
                    break;
                case 4:
                    deleteIncome(sc);
                    break;
                case 5:
                    exit = true;
                    System.out.println("Home Menu");
            }
        }
    }

    private void deleteIncome(Scanner sc) {

        viewTotalIncome();

        System.out.println("Enter a income id:");

        int selectedIncome = sc.nextInt();

        Income savedIncome = incomeService.getIncomeById(selectedIncome);

        incomeService.delete(savedIncome);

        System.out.println("\nIncome deleted successfully");
    }

    private void updateIncome(Scanner sc) {

        viewTotalIncome();

        System.out.println("Enter a income id:");
        int selectedIncome = sc.nextInt();

        Income savedIncome = incomeService.getIncomeById(selectedIncome);

        System.out.println("Enter update details:");

        System.out.print("Source: ");

        String source = sc.next();

        double amount = 0.0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Amount: ");
                sc.nextLine();
                amount = sc.nextDouble();

                savedIncome.setAmount(amount);
                savedIncome.setDescription(source);
                savedIncome.setUser(user);

                Income updatedIcome = incomeService.updateIcome(savedIncome);

                System.out.println("\nIncome updated successfully: " + updatedIcome.getDescription() + " - SRD " + updatedIcome.getAmount());

                validInput = true;

            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid numeric amount.");
                sc.next(); // Consume the invalid input to avoid an infinite loop
            }
        }

    }

    private void addMonthlyIncome(Scanner sc) {

        System.out.println("Enter income details:");
        sc.nextLine();
        System.out.print("Source: ");
        String source = sc.next();

        double amount = 0.0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Amount: ");
                amount = sc.nextDouble();

                System.out.println("Select a Category");
                Category selectCategory = CategoryService.selectCategory();

                Transaction transaction = new Transaction();
                transaction.setAmount(amount);
                transaction.setDate(LocalDate.now());
                transaction.setUser(user);
                transaction.setCategory(selectCategory);

                Income income = new Income();
                income.setAmount(amount);
                income.setDescription(source);
                income.setUser(user);

                income.setTransactions(transaction);
                transaction.setIncome(income);

                incomeService.addMonthlyIncome(transaction);

                validInput = true;

            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid numeric amount.");
                sc.next(); // Consume the invalid input to avoid an infinite loop
            }
        }

        System.out.println("Income added successfully: " + source + " - SRD " + amount);
    }

    void viewTotalIncome() {
        List<Income> incomeList = incomeService.getTotalIcome(user.getUserId());

        System.out.printf("%-10s%-20s%-10s%-20s%-15s\n", "IncomeId", "Transactions", "Amount", "Description", "IncomeDate");
        System.out.println("--------------------------------------------------------------------------------------------");

        String format = "%-10d%-20s%-10.2f%-20s%-15s";

        double totalIcome = 0.0;

        for (Income income : incomeList) {

            String transactionsInfo = income.getTransaction() != null
                    ? income.getTransaction().getTransactionId().toString()
                    : "N/A";

            System.out.printf(format, income.getIncomeId(), transactionsInfo, income.getAmount(),
                    income.getDescription(), income.getIncomeDate());
            System.out.println(); // Move to the next line for the next record

            totalIcome += income.getAmount();
        }
        System.out.println("\nTotal Income: " + totalIcome);
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public List<Income> getIncomeList() {
        return incomeList;
    }

}
