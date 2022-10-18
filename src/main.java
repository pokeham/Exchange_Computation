import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class main {
    /**
     * The main function prompts the to create an account, set the balance and the exchange rate, then prompts
     * them with a menu with four options: withdrawal,deposits,closing an account, and exiting.
     * @param args the argument for running main
     */
    public static void main(String[] args){
        boolean running = true;
        Scanner in = new Scanner(System.in);
        boolean running3 = true;
        while(running){
            //initializing bank prompt
            bank account = new bank();
            boolean running_2 = true;
            do {
                System.out.println("welcome press 1 to create a bank account or 0 to quit: ");
                String choice = in.nextLine();
                if (choice.equals("0")) {
                    running = false;
                    return;
                } else if (choice.equals("1")) {
                    running_2 = false;
                } else {
                    System.out.println("You have entered an invalid entry please try again!");
                }
            }while(running_2 && running);
            //get bank amount

            System.out.println("Please enter the inital balance of the account in USD or enter 0 to quit");
            String choice = in.nextLine();

            if(choice.equals("0")){
                return;
            }else{
                BigDecimal amount = new BigDecimal(choice);
                account.setBalance(amount);
            }

            System.out.println("Please enter the Exchange rate (1 USD is equal to ? SWD): ");
            choice = in.nextLine();
            Cashier cashier = new Cashier();
            cashier.setExchangeRate(Float.parseFloat(choice));
            System.out.println("Exchange Rate: " + cashier.getExchangeRate());

            running_2 = true;
            do{
                System.out.println("Menu \n Current Balance:" + account.getBalance() + "\n Press 1 for: Withdrawal \n Press 2 for: Deposits \n Press 3 for: Close this Account \n Press 4 for: Quit");
                choice = in.nextLine();
                if(choice.equals("4")){
                    return;
                }else if(choice.equals("3")){
                    return;
                }else if(choice.equals("2")){

                    do{
                        System.out.println("Press 1 for SWD deposits or 0 for USD deposits:");
                        String SWD = in.nextLine();
                        System.out.println("Enter Deposit Amount: ");
                        choice = in.nextLine();
                        if(SWD.equals("0")){
                            System.out.println("Balance before Deposit: " + account.getBalance());
                            if(cashier.transaction(Float.parseFloat(choice),account,false,1) == 1){
                                System.out.println("Current Balance: " + account.getBalance());
                                System.out.println("Would you like to make another Deposit?(1:yes,0:no): ");
                                choice = in.nextLine();
                                if(choice.equals("0")) {
                                    running3 = false;
                                }
                            }
                        }else {
                            System.out.println("Balance before Deposit: " + account.getBalance());
                            if (cashier.transaction(Float.parseFloat(choice), account, true, 1) == 1) {
                                System.out.println("Current Balance: " + account.getBalance());
                                System.out.println("Would you like to make another Deposit?(1:yes,0:no): ");
                                choice = in.nextLine();
                                if (choice.equals("0")) {
                                    running3 = false;
                                }
                            }
                        }

                    }while(running3);

                }else if(choice.equals("1")){
                    do{
                        System.out.println("Press 1 for SWD Withdrawals or 0 for USD withdrawals:");
                        String SWD = in.nextLine();
                        System.out.println("Enter Withdrawal Amount: ");
                        choice = in.nextLine();
                        if(SWD.equals("0")){
                            System.out.println("Balance before withdrawl: " + account.getBalance());
                            if(cashier.transaction(Float.parseFloat(choice),account,false,0) == 1){
                                System.out.println("Current Balance: " + account.getBalance());
                                System.out.println("Change: ");
                                for(Map.Entry<BigDecimal,Integer> entry : cashier.calculateChange(new BigDecimal(Float.parseFloat(choice)),false).entrySet()){
                                    if(entry.getKey().compareTo(new BigDecimal(0.99)) == 1){
                                        System.out.println(entry.getValue() + ": " + entry.getKey() + " USD Bills");
                                    }else{
                                        System.out.println(entry.getValue() + ": " + entry.getKey() + " USD coins");
                                    }
                                }
                                System.out.println("Would you like to make another Withdrawal?(1:yes,0:no): ");
                                choice = in.nextLine();
                                if(choice.equals("0")){
                                    running3 = false;
                                }
                            }else{
                                System.out.println("The amount you wanted to Withdrawal is " + Float.parseFloat(choice) + "\n The account Balance is " + account.getBalance());
                                System.out.println("Would you like to Withdrawal Again?(1:yes,0:no):");
                                choice = in.nextLine();
                                if(choice.equals("0"))
                                    running3= false;
                            }
                        }else {
                            System.out.println("Balance before Withdrawal: " + account.getBalance());
                            if (cashier.transaction(Float.parseFloat(choice), account, true, 0) == 1) {
                                System.out.println("Current Balance: " + account.getBalance());
                                for(Map.Entry<BigDecimal,Integer> entry : cashier.calculateChange(new BigDecimal(Float.parseFloat(choice)),true).entrySet()){
                                    if(entry.getKey().compareTo(new BigDecimal(0.99)) == 1){
                                        System.out.println(entry.getValue() + ": " + entry.getKey() + " SWD Bills");
                                    }else{
                                        System.out.println(entry.getValue() + ": " + entry.getKey() + " SWD coins");
                                    }
                                }
                                System.out.println("Would you like to make another Withdrawal?(1:yes,0:no): ");
                                choice = in.nextLine();
                                if (choice.equals("0")) {
                                    running3 = false;
                                }
                            }else {
                                System.out.println("The amount you wanted to Withdrawal is " + Float.parseFloat(choice) + "\n The account Balance is " + account.getBalance());
                                System.out.println("Would you like to Withdrawal Again?(1:yes,0:no):");
                                choice = in.nextLine();
                                if (choice.equals("0")) break;
                            }
                        }

                    }while(running3);
                }else{

                }
            }while(running_2);
        }
    }
}

