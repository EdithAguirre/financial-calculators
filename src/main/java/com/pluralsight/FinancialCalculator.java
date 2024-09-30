/*
An application for a financial organization that wants to provide
a set of financial calculators for their clients.
 */
package com.pluralsight;
import java.util.Scanner;

public class FinancialCalculator {
    public static void main(String[] args) {

        // Create a scanner object
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to select which calculator they would like to use.
        System.out.println("Which financial calculator would you like to use?");
        System.out.print("(M) Mortgage Calculator \n" +
                "(C) CD Calculator \n" +
                "(O) Ordinary Annuity Calculator \n" +
                "Select an option (M or C or O): ");

        String choice = scanner.nextLine();

        System.out.print("Enter the interest rate in percent form (ex. 5): ");
        float interestRate = scanner.nextFloat();
        System.out.print("Enter the duration (in years): ");
        int loanLength = scanner.nextInt();

        // Conditional statement to go to the method for the calculator selected
        if(choice.equalsIgnoreCase("M")){
            System.out.print("Enter the principal amount: ");
            float principal = scanner.nextFloat();
            mortgageCalculator(principal,interestRate,loanLength);
        }
        else if(choice.equalsIgnoreCase("C")){
            System.out.print("Enter the deposit amount: ");
            float principal = scanner.nextFloat();
            cDcalculator(principal,interestRate,loanLength);
        }
        else if(choice.equalsIgnoreCase("O")){
            System.out.print("Enter the monthly payout amount: ");
            float principal = scanner.nextFloat();
            annuityCalculator(principal,interestRate,loanLength);
        }
        else{
            System.out.print("Select a valid choice (M or C or O):");
        }

    }
    /*
    A mortgage calculator- It is used to calculate out how much a monthly payment for a loan
     would be (minus any insurance or taxes), as well as how much interest you would pay over the
     life of the loan
     */
    public static void mortgageCalculator(float principal, float interestRate, int loanLength){
        // Declare variables needed
        float monthlyInterest = (interestRate / 100) / 12;
        float numOfPayments = loanLength * 12;

        // Calculate the mortgage
        double mortgage = principal * (monthlyInterest * Math.pow((1 + monthlyInterest),numOfPayments)) /
                (Math.pow((1 + monthlyInterest),numOfPayments) - 1);

        // calculate how much interest would be paid over the life of the loan
        double totalInterestPaid = (mortgage * numOfPayments) - principal;

        // Display the monthly payment and the total interest paid
        System.out.printf("Your monthly payment is $%.2f with a total interest of $%.2f", mortgage, totalInterestPaid);
    }

    /*
    CD Calculator- A calculator that determines the future value of a one-time deposit assuming
    compound interest - it is used to help you decide how much a CD will be worth when it matures
     */
    public static void cDcalculator(float deposit, float interestRate, int cDLength){
        // Declare variables needed
        float interestAsDecimal = interestRate / 100;
        float compoundPerYear = 365;

        // Calculate the CD
        double futureValue = deposit * (Math.pow(1 + (interestAsDecimal/compoundPerYear),compoundPerYear * cDLength));
        double interestEarned = futureValue - deposit;

        // Display the CD interest
        System.out.printf("Your CD's ending balance will be $%.2f and you would have earned $%.2f in interest",
                futureValue,interestEarned);
    }

    /*
    Annuity Calculator- A calculator that determines the present value of an ordinary annuity
     */
    public static void annuityCalculator(float monthlyPayout, float interestRate, int years){
        // Declare variables needed
        float numOfPeriods = 365 * years;
        float periodInterestRate = (interestRate / 100) / 365;

        // Calculate present value annuity


        double amountToInvest = monthlyPayout * (1 - Math.pow((1 + periodInterestRate),-numOfPeriods )) /
                (periodInterestRate);
//        double amountToInvest = monthlyPayout * (1 - (1 / Math.pow((1 + periodInterestRate),numOfPeriods)) /
//                periodInterestRate);

        // Display the amount to invest
        System.out.printf("You would need to invest $%.2f today", amountToInvest);

    }

}
