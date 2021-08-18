import java.util.*;
class Customer extends BankAccount {
    Scanner input = new Scanner(System.in);

    public String chaeckingAccount( BankAccount bankAccount ){        
        return print (bankAccount);  
    }
    public String savingAccount( BankAccount bankAccount ){
        return print (bankAccount);
    }

    private String print(BankAccount bankAccount){
        return ("\nBalance = " + bankAccount.getBalance() + "  \n" + "A = Deposit, B = Withdrawal, C = Cancel: " ) ;        
    }  
}