package sr.unasat.bp24.hibernate.services;

import sr.unasat.bp24.hibernate.repository.UserRepo;
import sr.unasat.bp24.hibernate.entity.User;

import java.util.Scanner;

public class UserService {
    UserRepo dao;
    public UserService() {
        dao = new UserRepo();
    }

    public void registerUser() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Firstname ");
        String firstName = sc.next();

        System.out.println("Enter Lastname ");
        String lastName = sc.next();

        System.out.println("Enter Phone number");
        Integer phoneNumber = sc.nextInt();

        System.out.println("Enter a Username");
        String userName= sc.next();

        System.out.println("Enter a Password");
        String password = sc.next();


        User user = new User(firstName, lastName,userName,password,phoneNumber);
        dao.create(user);

        /*String res = dao.registerEmployee(new Employee(fname, lname, mob, email, pass, dob, add, sal, HD, selectedDepartmentId));*/

        System.out.println(user);
        ExtraFeature.processingAnimation("Registering");
    }
}
