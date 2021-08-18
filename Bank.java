import java.util.*;
import java.io.*;
import java.lang.Exception;
class Bank extends BankAccount {
    ArrayList<String> m = new ArrayList<>();
    int id ;
    int pinNum ;
    Bank(){
    }
    Bank( int id , int pinNum , ArrayList<String> m ){
        this.id = id ;
        this.pinNum = pinNum ;
        this.m = m ;
    }
    public boolean check(){  
        for (int i = 0 ; i < m.size() ; i++){
            String [] str = m.get(i).split(",");
            if( str[0].equals("" + id) && str[1].equals( "" + pinNum )){
                return true ;
            }     
        }
        return false ;
    }
    public double getBalance( int indexBalance ){
        int index = indexCustomer() ;
        String [] str = m.get(index).split(",");
        return Double.parseDouble( str[indexBalance] ); 
    }
    public static void depositOperation( BankAccount bankAccount  , double amount ){
        bankAccount.deposit( amount ) ;
    }
    public static void withDrawalOperation( BankAccount bankAccount , double amount ){
        bankAccount.withDrowal( amount ) ;
    }
    public int indexCustomer(){
        int index = 0 ;
        for (int i = 0 ; i < m.size() ; i++){
            String [] str = m.get(i).split(",");
            if( str[0].equals("" + id) && str[1].equals( "" + pinNum)){
                index = i  ;
            }     
        }
        return index ;
    }
    public void changeLine( BankAccount checkingAccount ,  BankAccount savingAccount , File file )throws FileNotFoundException{
        PrintWriter output = new PrintWriter(file) ;
        int index =  indexCustomer() ;
        m.set( index , (id + "," + pinNum + "," + checkingAccount.getBalance() + "," + savingAccount.getBalance() ) );
        for(int i = 0 ; i < m.size() ; i++){
            output.println(m.get(i) ) ;
        }
        output.close() ;
    }
}