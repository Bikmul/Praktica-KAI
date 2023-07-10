
import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private TransactionStruct head = null;

    public void add(Transaction transaction){
        TransactionStruct newtransaction = new TransactionStruct(transaction);
        if (head != null){
            head.setnext(newtransaction);
        }
        newtransaction.setnext(null);
        newtransaction.setprevious(head);
        head = newtransaction;
    }
    public void removebyuuid(UUID id) throws Exception{
        TransactionStruct step = head;
        while(step.getnext() != null)
            step = step.getnext();
        while(step.getprevious() != null){
            if(step.getdata().getUuid() == id){
                if(step.getnext() != null)
                    step.getprevious().setnext(step.getnext());
                else
                    step.getprevious().setnext(null);

                if(step.getprevious() != null)
                    step.getnext().setprevious(step.getprevious());
                else
                    step.getnext().setprevious(null);
                return;
            }
            step = step.getprevious();
        }
        throw (new Exception("Transaction not Found")); 
    }
    public Transaction[] toarray(){
        TransactionStruct step  = head;
        int len= 0;
        while(step != null){
            len++;
            step = step.getprevious();
        }
        Transaction[] array = new Transaction[len];
        step = head;
        while(step != null){
            array[array.length-len] = step.getdata();
            len--;
            step = step.getprevious();
        }
        return array;
    }
    public void printer() {
        Transaction[] arr = this.toarray();
        System.out.println("List: =========================== ");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i].getSender().getname() + " to " + arr[i].getRecipient().getname() + ": " + arr[i].getamount() + " " + arr[i].gettype() + " UUid:" + arr[i].getUuid());
        }
        System.out.println("================================= ");
    }
}
