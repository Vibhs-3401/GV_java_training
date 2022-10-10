package entity;

import validations.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Customer {

    private String firstName;

    private String lastName;

    private String contact;

    private String email;

    private BankAccountDetails bankAccountDetails;

    public Customer() {
    }

    static Map<String, Customer> customerList = new HashMap<>();

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
        while (!Validator.validateName(firstName)) {firstName = scan.nextLine().trim();}

        System.out.println("Last name: ");
        String lastName = scan.nextLine().trim();
        while (!Validator.validateName(lastName)) {lastName = scan.nextLine().trim();}

        System.out.println("Contact number: ");
        String contact = scan.nextLine().trim();
        while (!Validator.validatePhone(contact)) {contact = scan.nextLine().trim();}

        System.out.println("Email address: ");
        String email = scan.nextLine().trim();
        while(!Validator.validateEmail(email)) {email = scan.nextLine().trim();}

        BankAccountDetails bankAccDetails = BankAccountDetails.createAccount(customerList.keySet());
        Customer customer = new Customer(firstName, lastName, contact, email, bankAccDetails);
        customerList.put(bankAccDetails.getAccNo(), customer);
        System.out.println(customer.toString());
    }


    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", bankAccountDetails=" + bankAccountDetails +
                '}';
    }
}
