package entity;

import Generator.AccountNoGenerator;
import enums.AccountType;
import validations.Validator;

import java.util.ArrayList;
import java.util.Scanner;

public class BankAccountDetails {

    private String accNo;

    private AccountType accountType;

    private Double balance;

    private static final Scanner scan = new Scanner(System.in);



    public BankAccountDetails() {
    }

    public BankAccountDetails(String accNo, AccountType accountType, Double balance) {
        this.accNo = accNo;
        this.accountType = accountType;
        this.balance = balance;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public static BankAccountDetails createAccount(ArrayList<Customer> customers) {

        System.out.println("Account type: ");
        System.out.println("Please enter according no. of account type: \n1.Saving\n2.Current\n3.Salary");
        int accountType = scan.nextInt();
        while (!Validator.validateAccountType(accountType)) {accountType = scan.nextInt();}
        AccountType accType = setAccType(accountType);

        System.out.println("Initial balance(minimum 100 rs.): ");
        double balance = scan.nextDouble();
        while (!Validator.validateInitialBalance(balance)) {balance = scan.nextDouble();}

        String accNo = AccountNoGenerator.generateAccNo(customers);

        return new BankAccountDetails(accNo, accType, balance);

    }

    private static AccountType setAccType(int accountType) {
        if (accountType == 1) {
            return AccountType.SAVING;
        } else if (accountType == 2) {
            return AccountType.CURRENT;
        } else if (accountType == 3) {
            return AccountType.SALARY;
        } else {
            return null;
        }
    }


    public static void transferBalance(Customer from, Customer to, double amount) {
        double fromBalance = from.getBankAccountDetails().getBalance();
        double toBalance = to.getBankAccountDetails().getBalance();

        fromBalance = fromBalance - amount;
        toBalance = toBalance + amount;

        from.getBankAccountDetails().setBalance(fromBalance);
        to.getBankAccountDetails().setBalance(toBalance);
    }
    public void withdraw(Customer customer, int amount) {
        double balance = customer.getBankAccountDetails().getBalance();

        if(balance >= amount) {
            System.out.println(customer.getFirstName() + " withdrawn " + amount);
            balance = balance - amount;
            customer.getBankAccountDetails().setBalance(balance);
            System.out.println("Balance after withdrawal: " + balance);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            System.out.println("You can not withdraw " + amount);
            System.out.println("Your balance is: " + balance);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void deposit(Customer customer, double amount) {
        double balance = customer.getBankAccountDetails().getBalance();
        balance = balance + amount;
        customer.getBankAccountDetails().setBalance(balance);
        System.out.println(customer.getFirstName() + " deposited " + amount);
        System.out.println("Balance after deposit: " + balance);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "accNo = " + accNo + '\n' +
                "accountType = " + accountType + '\n' +
                "balance = " + balance + '\n' +
                '}';
    }


}
