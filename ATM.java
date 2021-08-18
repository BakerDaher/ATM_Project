import java.util.*;
import java.io.*;
import java.lang.Exception;
import javafx.stage.Stage ;
import javafx.scene.Scene ;
import javafx.scene.layout.* ;
import javafx.scene.control.* ;
import javafx.geometry.Insets ;
import javafx.geometry.Pos; 
import javafx.scene.text.* ;
import javafx.application.Application;

public class ATM extends Application {
    boolean step1 = true ;
    boolean step2 = true ;
    boolean step3 = true ;
    boolean amountType = true ;
    Bank palestine = new Bank() ;
    int id  ;
    int pinNum  ;
    Customer customer = new Customer() ;           
    BankAccount checkingAccount = new BankAccount() ; 
    BankAccount savingAccount = new BankAccount() ;  
    BankAccount bankAccount ; 

    @Override     
    public void start( Stage primaryStage ) throws FileNotFoundException {
        primaryStage.setResizable( false) ;
        Scanner input = new Scanner(System.in); 
        File file = new File("test.txt") ;
        Scanner in = new Scanner (file);
        ArrayList<String> m = new ArrayList<>();
        while (in.hasNextLine()){
            String s = in.nextLine();
            m.add(s);
        }
        
        Text Labl = new Text( ) ; 
        TextField text = new TextField( ) ;
        Button but0 = new Button("0") ;
        Button but1 = new Button("1") ;
        Button but2 = new Button("2") ;
        Button but3 = new Button("3") ;
        Button but4 = new Button("4") ;
        Button but5 = new Button("5") ;
        Button but6 = new Button("6") ;
        Button but7 = new Button("7") ;
        Button but8 = new Button("8") ;
        Button but9 = new Button("9") ;
        Button butCE = new Button("CE") ;
        Button dot = new Button(".") ;
        Button butA = new Button("A") ;
        Button butB = new Button("B") ;
        Button butC = new Button("C") ;

        but0.setPrefSize(60, 30);
        but1.setPrefSize(60, 30);
        but2.setPrefSize(60, 30);
        but3.setPrefSize(60, 30);
        but4.setPrefSize(60, 30);
        but5.setPrefSize(60, 30);
        but6.setPrefSize(60, 30);
        but7.setPrefSize(60, 30);
        but8.setPrefSize(60, 30);
        but9.setPrefSize(60, 30);
        butCE.setPrefSize(60, 30);
        dot.setPrefSize(60, 30);
        butA.setPrefSize(60, 30);
        butB.setPrefSize(60, 30);
        butC.setPrefSize(60, 30);
        text.setPrefSize(190, 30);
        text.maxHeight(31) ;
        text.maxWidth(191) ;
        text.setAlignment(Pos.CENTER) ;
        text.setEditable(false);
        Labl.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 15));
        Labl.setText("\nEnter customer number : \n A=ok ") ;
        primaryStage.setMinHeight(300) ;
        primaryStage.setMinWidth(300) ;

        GridPane gridPane = new GridPane(); 
        gridPane.setGridLinesVisible(true);
        gridPane.setPadding( new Insets( 10 , 10 ,10 , 10) ); 
        gridPane.setMinSize(190, 130); 
        gridPane.setAlignment( Pos.CENTER );
        gridPane.addColumn( 0 , but7 , but4 , but1 , but0 ) ;
        gridPane.addColumn( 1 , but8 , but5 , but2 , dot ) ;
        gridPane.addColumn(2 , but9, but6 , but3 , butCE ) ;
        HBox hbox = new HBox(10, butA , butB , butC ) ;
        hbox.setAlignment(Pos.BASELINE_CENTER);
        VBox vbox = new VBox( gridPane , Labl , hbox) ;
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding( new Insets( 20 , 20 ,0 , 20) ); 
        borderPane.setMinSize( 300 ,340 ) ;
        borderPane.setTop( text );
        borderPane.setCenter(vbox);
        borderPane.setBottom(hbox);

        Scene s = new Scene( borderPane );
        primaryStage.setTitle(" ATM Sumilation ");
        primaryStage.setScene( s );
        primaryStage.show() ;

        but0.setOnAction( (e)-> { 
            text.setText(  text.getText() + "0") ;      
        }) ;
        but1.setOnAction( (e)-> { 
            text.setText( text.getText() + "1") ;    
        }) ;
        but2.setOnAction( (e)-> { 
            text.setText( text.getText() + "2") ;    
        }) ;
        but3.setOnAction( (e)-> {
            text.setText( text.getText() + "3") ;   
        }) ; 
        but4.setOnAction( (e)-> {
            text.setText( text.getText() + "4") ;   
        }) ; 
        but5.setOnAction( (e)-> { 
            text.setText( text.getText() + "5") ;
        }) ; 
        but6.setOnAction( (e)-> { 
            text.setText( text.getText() + "6") ;
        }) ; 
        but7.setOnAction( (e)-> { 
            text.setText( text.getText() + "7") ; 
        }) ; 
        but8.setOnAction( (e)-> { 
            text.setText( text.getText() + "8") ;   
        }) ; 
        but9.setOnAction( (e)-> { 
            text.setText( text.getText() + "9") ;  
        }) ; 
        dot.setOnAction( (e)-> {
            text.setText( text.getText() + ".") ;  
        }) ; 
        butCE.setOnAction( (e)-> { 
            if( text.getText().length() > 0 )
                text.setText( text.getText().substring(0 , text.getText().length() - 1 ) ) ;
        }) ;
        butA.setOnAction( (e)-> { 
            if( step1 ){
                try{
                    id = Integer.parseInt(text.getText());
                }catch( Exception ex ){}
                text.setText("") ;
                Labl.setText( "\nEnter PIN : \nB=ok" ) ;
            }
            else if ( step2 ){
                Labl.setText( customer.chaeckingAccount( checkingAccount ) );
                bankAccount = checkingAccount ;
                step2 = false ;
            } 
            else if (step3){
                Labl.setText("\nSet Amount : \nA=Ok ") ;
                step3 = false ;
            }
            else if ( !step3 && amountType ){
                Bank.depositOperation( bankAccount , Double.parseDouble(text.getText()) );
                Labl.setText("\n A = Checking, B = Savings, C = Quit: ") ;
                step2 = true ;
                step3 = true ;
                text.setText("" ) ;
            }
        }) ; 
        butB.setOnAction( (e)-> {
            if( step1 ){
                try{
                    pinNum = Integer.parseInt(text.getText());
                }catch( Exception ex ){}
                palestine = new Bank( id , pinNum , m ) ;
                text.setText("") ;
                if(palestine.check()){
                    checkingAccount.setBalance( palestine.getBalance( 2 ) ) ;
                    savingAccount.setBalance( palestine.getBalance( 3 ) ) ;
                    Labl.setText("\nA = Checking, B = Savings, C = Quit: ") ;
                    step1 = false ;
                }
                else{
                    Labl.setText( ( "\nEnter customer number :  \n A=ok ") ) ;
                    id = 0 ;
                    pinNum = 0 ;
                } 
            }
            else if(step2){
                Labl.setText(customer.savingAccount( savingAccount ) ) ;
                bankAccount = savingAccount  ;
                step2 = false ;
            }
            else if (step3){
                Labl.setText("\nSet Amount : B=Ok ") ;
                step3 = false ;
            }
            else if(!step3 && amountType){
                Bank.withDrawalOperation( bankAccount , Double.parseDouble(text.getText()) );
                Labl.setText(" \nA = Checking, B = Savings, C = Quit: ") ;
                step2 = true ;
                step3 = true ; 
                text.setText("") ;
            }
        }); 
        butC.setOnAction( (e)-> {
            if (!step1 && step2 ){
                try {
                    palestine.changeLine( checkingAccount , savingAccount , file ) ;
                }catch( FileNotFoundException ex ) {}
                id = 0 ;
                pinNum = 0 ;
                Labl.setText("\nEnter customer number : \n A=ok") ;
                step1 = true ;
            }
            else if (!step1 && !step2 ){
                step2 = true ;
                Labl.setText("\nA = Checking, B = Savings, C = Quit: ") ;
            }      
        }) ;   
    }
}
/* class BankAccount {
    private double balance ;

    public void setBalance( double balance){
        this.balance = balance ;
    }
    public double getBalance( ){
        return this.balance  ;
    }
    public void deposit( double deposit ){
        this.balance += deposit ;
    }
    public void withDrowal( double withDrowal ){
        this.balance -= withDrowal ;
    }
}*/
//*********************************************************/
/*class Customer extends BankAccount {
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
}*/
// ********************************************************* /
/*class Bank extends BankAccount {
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
}*/
/****** Thanks for your efforts our creative engineer **********/