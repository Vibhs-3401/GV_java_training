package entity;

import Generator.AccountNoGenerator;
import enums.AccountType;
import validations.Validator;

import java.util.Scanner;
import java.util.Set;

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

    public static BankAccountDetails createAccount(Set<String> accNoSet) {

        System.out.println("Account type: ");
        System.out.println("Please enter according no. of account type: \n1.Saving\n2.Current\n3.Salary");
        int accountType = scan.nextInt();
        while (!Validator.validateAccountType(accountType)) {accountType = scan.nextInt();}
        AccountType accType = setAccType(accountType);

        System.out.println("Initial balance(minimum 100 rs.): ");
        double balance = scan.nextDouble();
        while (!Validator.validateInitialBalance(balance)) {balance = scan.nextDouble();}

        String accNo = AccountNoGenerator.generateAccNo(accNoSet);

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

    public void withdraw(String accNo, int amount) {
//        TODO: get balance from accNo

        double balance = 100;

        if(balance >= amount) {
//            System.out.println(name + " withdrawn " + amount);
            balance = balance - amount;
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

    public void deposit(String accNo, int amount) {
//        TODO: get acc details from acc no
        int balance = 100;
        balance = balance + amount;
//        System.out.println(name + " deposited " + amount);
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
                "accNo='" + accNo + '\'' +
                ", accountType=" + accountType +
                ", balance=" + balance + '\'' +
                '}';
    }


}
