package Generator;

import java.util.Random;
import java.util.Set;

public class AccountNoGenerator {

    public static final String firstDigit = "123456789";

    public static final String numbers = "1234567890";

    public static final int length = 8;

    public static String generateAccNo(Set<String> accNoSet) {
        Random random = new Random();

        char [] accountNo = new char[length];
        accountNo[0] = firstDigit.charAt(random.nextInt(firstDigit.length()));

        for (int i = 1; i < length; i++) {
            accountNo[i] = numbers.charAt(random.nextInt(numbers.length()));
        }
        String accNo = accountNo.toString();
        while(accNoSet.contains(accNo)) {
            accNo = generateAccNo(accNoSet);
        }
        return accNo;
    }

}
