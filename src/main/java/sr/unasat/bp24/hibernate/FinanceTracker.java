package sr.unasat.bp24.hibernate;

import sr.unasat.bp24.hibernate.entity.User;
import sr.unasat.bp24.hibernate.menus.HomeMenu;
import sr.unasat.bp24.hibernate.services.AuthenticationService;
import sr.unasat.bp24.hibernate.services.ExtraFeature;
import sr.unasat.bp24.hibernate.services.UserService;

import java.util.Scanner;

public class FinanceTracker {
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String BLACK_BOLD = "\033[1;30m";
    public static final String RED_BOLD = "\033[1;31m";
    public static final String GREEN_BOLD = "\033[1;32m";
    public static final String YELLOW_BOLD = "\033[1;33m";
    public static final String BLUE_BOLD = "\033[1;34m";
    public static final String PURPLE_BOLD = "\033[1;35m";
    public static final String CYAN_BOLD = "\033[1;36m";
    public static final String WHITE_BOLD = "\033[1;37m";


    public static void main(String[] args) {
        UserService userService = new UserService();
        Scanner sc=new Scanner(System.in);
        while(true) {
            System.out.println(""
                    +CYAN_BOLD+ "╔═══════════════════╗\r\n"
                    +CYAN_BOLD+ "  Financial Tracker\r\n"
                    +CYAN_BOLD+ "╚═══════════════════╝\r\n"
                    +CYAN_BOLD+ "1. Login\r\n"
                    +CYAN_BOLD+ "2. Register\r\n"
                    +RED_BOLD+ "3. Exit"+ANSI_RESET);

            int choice=sc.nextInt();
            AuthenticationService authenticationService = new AuthenticationService();
            switch(choice) {
                case 1:
                    User user = authenticationService.userLoginAuthentication();

                    HomeMenu homeMenu = new HomeMenu(user);
                    homeMenu.menu();
                    break;
                case 2:
                    userService.registerUser();
                    break;
                case 3:
                    ExtraFeature.thankYou();
                    System.exit(0);
            }
        }

    }
}
