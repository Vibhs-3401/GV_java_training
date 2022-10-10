package Generator;

import entity.Customer;

import java.util.ArrayList;
import java.util.Random;

public class AccountNoGenerator {

    public static final String firstDigit = "123456789";

    public static final String numbers = "1234567890";

    public static final int length = 8;

    public static String generateAccNo(ArrayList<Customer> customers) {
        Random random = new Random();

        char[] accountNo = new char[length];
        accountNo[0] = firstDigit.charAt(random.nextInt(firstDigit.length()));

        for (int i = 1; i < length; i++) {
            accountNo[i] = numbers.charAt(random.nextInt(numbers.length()));
        }
        String accNo = new String(accountNo);
        if(!uniqueAccNo(accNo, customers)) {
            accNo = generateAccNo(customers);
        }
        return accNo;
    }

    private static boolean uniqueAccNo(String accNo, ArrayList<Customer> customers) {
        for (int i=0; i<customers.size(); i++) {
            if(customers.get(i).getBankAccountDetails().getAccNo().equals(accNo)) {
                return false;
            }
        }
        return true;
    }

}
