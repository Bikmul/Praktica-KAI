

public class TransactionStruct{
    private Transaction data;
    TransactionStruct next;
    TransactionStruct previous;

    TransactionStruct(Transaction data){
        this.data = data;
    }
    public Transaction getdata(){
        return data;
    }
    public TransactionStruct getnext(){
        return next;
    }
    public void setnext(TransactionStruct next){
        this.next = next;
    }
    public TransactionStruct getprevious(){{
        return previous;
    }}
    public void setprevious(TransactionStruct previous){
        this.previous = previous;
    }
}
