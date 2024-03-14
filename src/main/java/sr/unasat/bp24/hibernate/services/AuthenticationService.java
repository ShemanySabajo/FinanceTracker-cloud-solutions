package sr.unasat.bp24.hibernate.services;

import sr.unasat.bp24.hibernate.repository.UserRepo;
import sr.unasat.bp24.hibernate.entity.User;

import java.util.Scanner;

import static sr.unasat.bp24.hibernate.FinanceTracker.*;

public class AuthenticationService {

    UserRepo dao;

    public AuthenticationService() {
        dao = new UserRepo();
    }

    public User userLoginAuthentication() {

        User user = new User();

        Scanner sc = new Scanner(System.in);

        boolean verified = false;
        while (!verified) {
            System.out.println(GREEN_BOLD + "Enter Your Username" + ANSI_RESET);
            String userName = sc.next();

            System.out.println(GREEN_BOLD + "Enter Your Password" + ANSI_RESET);
            String password = sc.next();

            ExtraFeature.processingAnimation("Loading");

            User savedUser = dao.verifyUser(userName, password);
            if (savedUser != null) {
                if (password.equals(savedUser.getPassword()) && userName.equals(savedUser.getUserName())) {
                    System.out.println(savedUser);
                    verified = true;
                    user = savedUser;
                } else {
                    System.out.println((RED_BOLD + "Incorrect Credentials" + ANSI_RESET));
                    System.out.println((RED_BOLD + "Please try again" + ANSI_RESET));
                }
            } else {
                System.out.println((RED_BOLD + "Incorrect Credentials" + ANSI_RESET));
                System.out.println((RED_BOLD + "Please try again" + ANSI_RESET));
            }
        }
        return user;
    }

}
