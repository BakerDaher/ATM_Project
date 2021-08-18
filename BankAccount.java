class BankAccount {
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
}