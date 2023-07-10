

import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private boolean devMode;
    private TransactionsService service;
    private Scanner console;
    {
        devMode = false;
        service = new TransactionsService();
        console = new Scanner(System.in);
    }
    public Menu(){}
    public void showMenu() {
        int optionCounter = 1;
        System.out.println(optionCounter++ + ". Add a user");
        System.out.println(optionCounter++ + ". View user balances");
        System.out.println(optionCounter++ + ". Perform a transfer");
        System.out.println(optionCounter++ + ". View all transactions for a specific user");
        if (devMode) {
            System.out.println(optionCounter++ + ". DEV - remove a transfer by ID");
            System.out.println(optionCounter++ + ". DEV - check transfer validity");
        }
        System.out.println(optionCounter + ". Finish execution");
    }
    public void DevMode(boolean devMode) {
        this.devMode = devMode;
    }
    public void startMenu() throws Exception{
        while (true) {
            this.showMenu();
            while (!console.hasNextInt()) {
                if (isAnyMeaningSymbol(console.nextLine())) {
                    System.out.println("Enter a numeric.");
                }
            }
            this.handelEnteredMenuOption(console.nextInt());
            System.out.println("---------------------------------------------------------");
        }
    }
    private boolean isAnyMeaningSymbol(String string) {
        char [] stringArray = string.toCharArray();
        for (char c : stringArray) {
            if (c != ' '
                    && c != '\t'
                    && c != '\r'
                    && c != '\n') {
                return true;
            }
        }
        return false;
    }

    public void handelEnteredMenuOption(int menuOption) throws Exception {
        int innerMenuOption = menuOption;
        if (!devMode && innerMenuOption > 4) {
            innerMenuOption += 2;
        }
        switch (innerMenuOption) {
            case 1:
                addNewUser();
                break;
            case 2:
                vievUserBalances();
                break;
            case 3:
                performTransfer();
                break;
            case 4:
                viewAllTransactionForUser();
                break;
            case 5:
                removeTransferByIdentifier();
                break;
            case 6:
                checkTransferValidity();
                break;
            case 7:
                System.exit(0);
            default:
                System.out.println(menuOption + " is not a option in the menu.");
        }
    }
    private void addNewUser() {
        String temp = new String("");
        Integer balance = 0;
        boolean success = false;
        System.out.println("Enter a user name and a balance");
        while (!success) {
            temp = console.nextLine();
            while (!isAnyMeaningSymbol(temp)) {
                temp = console.nextLine();
            }
            String[] args = temp.split(" ");
            if (args.length != 2) {
                System.out.println("Wrong argument amount");
                continue;
            }
            try {
                balance = Integer.parseInt(args[1]);
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Balance is not a numeric");
                continue;
            }
            if (balance < 0) {
                System.out.println("Balance is negative");
                continue;
            }
            temp = args[0];
            success = true;
        }
        service.addUser(temp, balance);
        System.out.println("User: " + temp + " is added");
    }
    private void vievUserBalances() throws Exception {
        String temp;
        Integer idetifier = -1;
        System.out.println("Enter a user ID");
        while (true) {
            temp = console.nextLine();
            while (!isAnyMeaningSymbol(temp)) {
                temp = console.nextLine();
            }
            String[] args = temp.split(" ");
            if (args.length != 1) {
                System.out.println("Wrong argument amount");
                continue;
            }
            try {
                idetifier = Integer.parseInt(args[0]);
            } catch (NumberFormatException numberFormatException) {
                System.out.println("ID is not a numeric");
                continue;
            }
            if (idetifier < 0) {
                System.out.println("ID is negative");
                continue;
            }
            System.out.println(service.getuserbyid(idetifier).getname() + " - " + service.getuserbyid(idetifier).getmoney());
            return;
        }
    }
    private void performTransfer() throws Exception{
        String temp;
        int sender = -1;
        int recipient = -1;
        int transferAmount = -1;
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        while (true) {
            temp = console.nextLine();
            while (!isAnyMeaningSymbol(temp)) {
                temp = console.nextLine();
            }
            String[] args = temp.split(" ");
            if (args.length != 3) {
                System.out.println("Wrong argument amount");
                continue;
            }
            try {
                sender = Integer.parseInt(args[0]);
                recipient = Integer.parseInt(args[1]);
                transferAmount = Integer.parseInt(args[2]);
            } catch (NumberFormatException numberFormatException) {
                System.out.println("One of the arguments is not a numeric");
                continue;
            }
            if (sender == recipient) {
                System.out.println("User can't made transaction to himself");
                continue;
            }
            if (transferAmount < 0) {
                System.out.println("Transfer amount is negative");
                continue;
            }
            service.makeTransaction(sender, recipient, transferAmount, "DEBIT");
            System.out.println("The transfer is completed");
            return;
        }
    }
    private void viewAllTransactionForUser() throws Exception {
        String temp;
        Integer idetifier = -1;
        System.out.println("Enter a user ID");
        while (true) {
            temp = console.nextLine();
            while (!isAnyMeaningSymbol(temp)) {
                temp = console.nextLine();
            }
            String[] args = temp.split(" ");
            if (args.length != 1) {
                System.out.println("Wrong argument amount");
                continue;
            }
            try {
                idetifier = Integer.parseInt(args[0]);
            } catch (NumberFormatException numberFormatException) {
                System.out.println("ID is not a numeric");
                continue;
            }
            if (idetifier < 0) {
                System.out.println("ID is negative");
                continue;
            }
            service.getUserTransactions(idetifier);
            return;
        }
    }
    private void removeTransferByIdentifier() throws Exception {
        String temp = new String("");
        Integer userIdentifier = -1;
        UUID transactionUUID = UUID.randomUUID();
        boolean success = false;
        System.out.println("Enter a user ID and a transfer ID");
        while (!success) {
            temp = console.nextLine();
            while (!isAnyMeaningSymbol(temp)) {
                temp = console.nextLine();
            }
            String[] args = temp.split(" ");
            if (args.length != 2) {
                System.out.println("Wrong argument amount");
                continue;
            }
            try {
                userIdentifier = Integer.parseInt(args[0]);
            } catch (NumberFormatException numberFormatException) {
                System.out.println("User ID is not a numeric");
                continue;
            }
            try {
                transactionUUID = UUID.fromString(args[1]);
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println("Transfer ID in bad format");
                continue;
            }
            if (userIdentifier < 0) {
                System.out.println("User ID is negative");
                continue;
            }
            success = true;
        }
        service.getuserbyid(userIdentifier).getTL().removebyuuid(transactionUUID);
        System.out.println("Transfer " + transactionUUID + " removed");
        service.getTL().removebyuuid(transactionUUID);
    }
    private void checkTransferValidity () {
        System.out.println("Check results:");
        service.getTL().printer();
    }
}