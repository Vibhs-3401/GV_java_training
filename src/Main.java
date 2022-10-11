/*

	Project 1 : Keyboard based Input - Banking Application ( Core java only - Do not use any web tech)

	Accept user input from keyboard atleast for 3 customer details and generatr 8 digit account number for them and dispaly them back to console

	Display the functionality sumamry : on console like : Balance Transfer, Account Summary, Deposit,

	Ask user to perform either of these

based on user input proceede accordingly.

	if user say balance transfer -
		ask user to enter accoutn number From account and money
		Project 1- Banking Application - Prototype

		if mony is not sufficent throw eception and end program
		if money is sufficient, ask TO account number
		validate the account number agianst Map (as part of previous entry)
		if account not avialble - throw exception

		if account valid - perform transfer.

		After transfer successfull - display the summary for both accounts. use printf for formating the out put

		Prompt user to ask if they want to export their sumamry
		if Yes - write the same summary in file



		Hints :
			use OOP concepts,
			define classes, interface,
			handle exception,
			use java 8 - declarative approach where ever possible,
			thing about multi thread,
			defien your class and interface as generics
			use colelction api to store your data in memory
			for file operation - use java nio2 for Path

*/


/*user 3 vaar login kre khota credentials thi to ene kevanu you reached the limit thank you please try later*/



import entity.Customer;

import java.util.Scanner;

public class Main {
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            int action = printActions();
            switch (action) {
                case 0: System.out.println("Quitting...!");
                    scan.close();
                    return;
                case 1: Customer.createCustomer(); break;
                case 2: Customer.customerDetailsList(); break;
                case 3: Customer.balanceTransfer(); break;
                case 4: Customer.deposit();break;
                case 5: Customer.printAccountSummary(); break;
                case 6: printActions(); break;
            }
        }
    }

    private static int printActions() {
        System.out.println();
        System.out.println("*** Welcome to our Banking Application..! ***");
        System.out.println("-----------------------------------------");
        System.out.println("Available actions are:");
        System.out.println("0 - Quit\n" +
                "1 - Open account for new customer\n" +
                "2 - Get existing customer details\n" +
                "3 - Balance Transfer\n" +
                "4 - Deposit\n" +
                "5 - View Account summary" +
                "6 - View options");
        System.out.println("-----------------------------------------");
        System.out.println("Choose any number for according action: ");
        int option = scan.nextInt();
        scan.nextLine();
        if (option >= 0 && option < 7) {
            return option;
        } else {
            System.out.println("Invalid option!");
            return 6;
        }
    }
}