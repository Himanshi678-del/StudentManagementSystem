// package com.company;
import java.util.Scanner;
class library{
    public String[] availableBooks=new String[100];
    public String[] issuedBooks=new String[100];
    int availableCount=0;
    int issueCount=0;
    public void addBook(String bookName){
if(availableCount!=availableBooks.length){
    System.out.println("you can add book into library!"+bookName);
    availableBooks[availableCount++]=bookName;

}
else{
    System.out.println("library is full!");

}

    }
    public void issueBooks(String bookName){
        for(int i=0;i<availableCount;i++){
            if(availableBooks[i].equalsIgnoreCase(bookName)){
          availableBooks[availableCount--]=bookName;
          issueCount++;
          availableBooks[availableCount]=null;
                System.out.println("issued!"+bookName);
                return;
            }
            else{
                System.out.println("book is not available for issuing!"+bookName);
            }

        }

    }
    public void returnBook(String bookName){
        for(int i=0;i<issueCount;i++){
            if(issuedBooks[i].equalsIgnoreCase(bookName)){
                availableBooks[availableCount++]=bookName;
                issueCount--;
                System.out.println("returned!"+bookName);
                issuedBooks[issueCount]=null;
                return;
            }
            else{
                System.out.println("not returned!");
            }
        }


    }
    public void showAvailableBooks(){

        if(availableCount==0){
            System.out.println("no available books!");
        }

         for (int i=0;i<availableBooks.length;i++){


                 System.out.print("books: " +availableBooks[i]);
             }


    }
}

public class online_library {
    public static void main(String[] args) {
        library l=new library();
        Scanner sc=new Scanner(System.in);
        System.out.println("Welcome to ONLINE LIBRARY!");
        int choice;

        do{

            System.out.println("\n1. Add Book\n2. Issue Book\n3. Return Book\n4. Show Book\n5. Exit!");
            System.out.println("choice: ");
            choice=sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1:
                    System.out.println("book Name: ");
                    l.addBook(sc.nextLine());
                    break;
                case 2:
                    System.out.println("book Name ");
                    l.issueBooks(sc.nextLine());
                    break;
                case 3:
                    System.out.println("book Name ");
                    l.returnBook(sc.nextLine());
                    break;
                case 4:
                    l.showAvailableBooks();
                    break;
                case 5:
                    System.out.println("exiting!");
                    break;
                default:
                    System.out.println("invalid choice! ");
            }
        }
        while (choice!=5);

    }
}


    
