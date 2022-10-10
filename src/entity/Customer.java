package entity;

import validations.Validator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Customer {

    private String firstName;

    private String lastName;

    private String contact;

    private String email;

    private BankAccountDetails bankAccountDetails;

    public Customer() {
    }

    static ArrayList<Customer> customerList = new ArrayList<>();

    private static final Scanner scan = new Scanner(System.in);

    public Customer(String firstName, String lastName, String contact, String email, BankAccountDetails bankAccountDetails) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = contact;
        this.email = email;
        this.bankAccountDetails = bankAccountDetails;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BankAccountDetails getBankAccountDetails() {
        return bankAccountDetails;
    }

    public void setBankAccountDetails(BankAccountDetails bankAccountDetails) {
        this.bankAccountDetails = bankAccountDetails;
    }

    public static void createCustomer() {
        System.out.println("Please enter below details: \n");
        System.out.println("First name: ");
        String firstName = scan.nextLine().trim();
        while (!Validator.validateName(firstName)) {
            firstName = scan.nextLine().trim();
        }

        System.out.println("Last name: ");
        String lastName = scan.nextLine().trim();
        while (!Validator.validateName(lastName)) {
            lastName = scan.nextLine().trim();
        }

        System.out.println("Contact number: ");
        String contact = scan.nextLine().trim();
        while (!Validator.validatePhone(contact)) {
            contact = scan.nextLine().trim();
        }

        System.out.println("Email address: ");
        String email = scan.nextLine().trim();
        while (!Validator.validateEmail(email)) {
            email = scan.nextLine().trim();
        }

        BankAccountDetails bankAccDetails = BankAccountDetails.createAccount(customerList);
        Customer customer = new Customer(firstName, lastName, contact, email, bankAccDetails);
        customerList.add(customer);
        System.out.println(customer.toString());
    }

    public static void customerDetailsList() {
        if (customerList.size() == 0) {
            System.out.println("No data found!");
            return;
        }
        for (int i = 0; i < customerList.size(); i++) {
            System.out.println(customerList.get(i).toString());
        }
    }

    public static void balanceTransfer() {
        System.out.println("Please provide below details:");
        System.out.println("Account no. from which to transfer: ");
        String accNoFrom = scan.nextLine();
        while (!accountNumExist(accNoFrom)) {
            accNoFrom = scan.nextLine();
        }
        Customer from = customerDetailsFromAccNo(accNoFrom);

        System.out.println("Amount to transfer: ");
        double amount = scan.nextDouble();
        scan.nextLine();
        while (amount <= 0) {
            System.out.println("invalid amount");
            amount = scan.nextDouble();
            scan.nextLine();
        }
        sufficientAmount(amount, from);

        System.out.println("Account no. to which to transfer: ");
        String accNoTo = scan.nextLine();
        if(!accountNumExist(accNoTo)) {
            throw new RuntimeException("Invalid account no.");
        }
        Customer to = customerDetailsFromAccNo(accNoTo);
        BankAccountDetails.transferBalance(from, to, amount);

        String printFrom = printSummary(from);
        String printTo = printSummary(to);
        System.out.println(printFrom + "\n" + printTo);
        System.out.println("Do you want to export the summary to file? y/n: ");
        String yesNo = scan.nextLine();
        if(yesNo.equalsIgnoreCase("y")) {
            writeToFile(printFrom + "\n\n" + printTo + "\n\n\n");
        }
    }

    public static void deposite() {
        System.out.println("Please enter account no.:");
        String accNo = scan.nextLine();
        if(accountNumExist(accNo)) {
            System.out.println("Please enter deposit amount:");
            double amount = scan.nextDouble();
            scan.nextLine();
            while (amount <= 0) {
                System.out.println("Invalid amount");
                amount = scan.nextDouble();
                scan.nextLine();
            }
            Customer customer = customerDetailsFromAccNo(accNo);
            BankAccountDetails.deposit(customer, amount);
        }
    }

    public static void printAccountSummary() {
        System.out.println("Please enter account no.:");
        String accNo = scan.nextLine();
        if(accountNumExist(accNo)) {
            Customer customer = customerDetailsFromAccNo(accNo);
            printSummary(customer);
        } else {
            System.out.println("Account does not exist.");
        }
    }

    private static void writeToFile(String summary){
        try {
            FileWriter file = new FileWriter("summaryFile.txt");
            BufferedWriter writer = new BufferedWriter(file);
            writer.write(summary);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String printSummary(Customer customer) {
        return "First name: " + customer.getFirstName() +
                "\nAccountNo: " + customer.getBankAccountDetails().getAccNo() +
                "\nBalance: " + customer.getBankAccountDetails().getBalance();
    }

    private static boolean sufficientAmount(double amount, Customer from) {
        if (amount < from.getBankAccountDetails().getBalance()) {
            return true;
        }
        System.out.println("Insufficient amount");
        throw new RuntimeException("Insufficient amount");
    }

    private static boolean accountNumExist(String accNo) {
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getBankAccountDetails().getAccNo().equals(accNo)) {
                return true;
            }
        }
        System.out.println("Provided account no. does not exist");
        return false;
    }

    private static Customer customerDetailsFromAccNo(String accNo) {
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).bankAccountDetails.getAccNo().equals(accNo)) {
                return customerList.get(i);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName = " + firstName + '\n' +
                "lastName = " + lastName + '\n' +
                "contact = " + contact + '\n' +
                "email = " + email + '\n' +
                "bankAccountDetails = " + bankAccountDetails +
                '}';
    }
}
