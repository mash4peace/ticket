package com.company;

import java.io.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class

TicketManager {
    private static Scanner sc = new Scanner(System.in);

    LinkedList<Ticket> ticketQueue = new LinkedList<Ticket>();

    protected void mainMenu() {

        while (true) {

            //TODO problem 4 - add two new options: Delete by Issue and Search by Issue
            //Here is to display all menu options
            System.out.println("1. Enter Ticket\n2. Delete Ticket options\n3. Display All Tickets\n4. Resolved Tickets\n5.Quit");
            // A variable to ask and refer to the menu

            int task = Input.getPositiveIntInput("Enter your selection");

            if (task == 1) {
                addTickets();
            }
            else if (task == 2) {

                deleteTicketOptions();
            }
            else if (task == 3) {
                printAllTickets();
            }
            else if(task == 4){
                ResolvedTicket();


            }
            else if ( task == 5 ) {
                System.out.println ("Quitting program");
                // TODO Problem 7 save all open tickets, and today's resolved tickets, to a file

                try {
                    BufferedWriter bf = new BufferedWriter (new FileWriter ("All_tickets.txt"));
                    for(Ticket t : ticketQueue){
                        bf.write(String.valueOf (t));

                    }
                    bf.close ();

                } catch (IOException ie) {
                    System.out.println ("There is an error in the input");
                }
                break;
            }
            else {
                //this will happen for 3 or any other selection that is a valid int
                //Default will be print all tickets
                printAllTickets();
            }
        }
        /*
        Modify your program so that Tickets can store
        another date – resolution date – and a String that
         documents why the ticket was closed – the fix or the resolution
         for the ticket.


         */
    }


    // Deleting method

    private void deleteTicketOptions() {
        System.out.println("1. delete by ID no: "+"\n"+
                "2. Delete by Issue : " +"\n"+
                "3. Search by issue : "+"\n"+
                "4. Reloading all tickets from their saved file\n"+
                "5. Return to main menu : ") ;
        // Asks user's choice

        int choice = Input.getPositiveIntInput("Enter delete option here : ");
        while (true){
            if(choice == 1){
                deleteTicketById();
                break;
            }
            else  if(choice == 2){
                deleteTicketByIssue();
                break;
            }
            else if(choice == 3){
                searchByIssue();
                break;
            }
            else if(choice == 4){
                reloadAllTicket();
                break;
            }
            else  if(choice == 5){
                return;
            }
            else {
                System.out.println("Enter a number from the menue : ");
            }

        }




    }

    private void reloadAllTicket() {
        for (Ticket t : ticketQueue){
            try {
                BufferedReader reader = new BufferedReader (new FileReader("All_tickets.txt"));
                String line = reader.readLine ();
                while(line!= null){
                    System.out.println (t);
                    line = reader.readLine ();
                }
                reader.close ();
            }
            catch (IOException io){
                System.out.println ("File reading input error!!");
            }
        }
    }

    // Resolving method
    protected  void ResolvedTicket() {
        if(ticketQueue.isEmpty ()){
            System.out.println ("No ticket at this point !");
            return;
        }
        // To remove resolved ticket from ticket queue
        Ticket resolvedTicket = ticketQueue.remove ();

        //Prompting a user's input
        String resolution = Input.getStringInput ("Enter resolution for "+
                resolvedTicket);
        String reason = Input.getStringInput ("Enter why you want " +
                "this resolution : ");
        //Seting three varibales into resilvedTickets.

        resolvedTicket.setResolution (resolution);
        resolvedTicket.setReason (reason);
        resolvedTicket.setResolvedDate (new Date ());
        // Writing into a file
        try {
            BufferedWriter bf = new BufferedWriter (new FileWriter ("close_tickets.txt"));
            bf.write (String.valueOf (resolvedTicket));

            bf.close ();
// Prints Input and output error to help me tracking the error
        } catch (IOException e) {
            e.printStackTrace ();
        }


    }
    // A printing method to show me all resolved tickets
    private  void showAllResolvedTicket(){
        System.out.println ("List of resolved tickets: ");

        if(ticketQueue.isEmpty ()){
            System.out.println ("No resolved ticket !!!");
        }
        for (Ticket t : ticketQueue){
            System.out.println (t);
        }
    }
// Searching by descriptions , please use the problem

    protected String searchDescription(String searchString) {
        // TODO problem 3: complete this method - it should return a
        // list of the tickets that contain the searchString in the description.
       // printAllTickets();
        // Return an empty list if there are no matching Tickets.
        for (Ticket t : ticketQueue){
            searchString = t.getDescription ();
            boolean found = false;
            while (! found){
                System.out.println ("The searched term is not found !!!");
                String again= Input.getStringInput ("Enter the term again : ");
                if(ticketQueue.contains (again)){
                    return again;
                }

            }
        }








            printAllTickets ();
            return searchString;


        }




        //printAllTickets();







    protected void searchByIssue() {
        // TODO problem 4 implement this method. Return a list of matching tickets.

        // Ask user for search term
        // Use searchDescription() method to get list of matching Tickets
        // display list
        System.out.println("Enter a search term here : ");
        String searchTerm = sc.nextLine();
        for (Ticket t : ticketQueue){
            if(searchTerm.contains (t.getDescription () )){
                System.out.println (t );
            }
        }
        searchDescription(searchTerm);
        //printAllTickets ();



    }




    protected void deleteTicketByIssue() {
        // TODO problem 5 implement this method
        // Ask user for string to search for
        // Use searchDescription to create list of matching Tickets
        // Ask for ID of ticket to delete
        // Delete that ticket

        System.out.println("Enter a search term here : ");
        String searchTerm = sc.nextLine();
        for (Ticket t : ticketQueue){
            if(searchTerm.contains (t.getDescription () )){
                System.out.println (t.getTicketID ()+" " +t.getDescription () +" " + t.getPriority () );
                int id = Input.getPositiveIntInput ("Enter the ID here : ");
                if(t.getTicketID () == id){
                    ticketQueue.remove (t);
                    System.out.println (String.format ("The ticket %d deleted ", id));
                    break;
                }
            }
        }
        searchDescription(searchTerm);


    }


    protected int deleteTicketById() {

        printAllTickets();   //display list for user

        if (ticketQueue.size() == 0) {    //no tickets!
            System.out.println("No tickets to delete!\n");

            return 0;
        }



        int deleteID = Input.getPositiveIntInput("Enter ID of ticket to delete");
        boolean found = false;

        //Loop over all tickets. Delete the one with this ticket ID

        for (Ticket ticket : ticketQueue) {
            if (ticket.getTicketID() == deleteID) {
                found = true;
                ticketQueue.remove(ticket);
                System.out.println(String.format("Ticket %d deleted", deleteID));
                break; //don't need the loop any more.
            }
        }
        while (!found) {

            //TODO Problem 2 re-write this method to ask for ID again if not found

                System.out.println("Ticket ID not found, no ticket deleted");
                int input = Input.getPositiveIntInput("Enter ID of ticket to delete");
                if(ticketQueue.size() == input){
                    System.out.println("Ticket ID no " + input + " is deleted ");
                    return input;
                }


        }








        printAllTickets();  //print updated list

        return deleteID;
    }


    protected void addTickets() {

        while (true) {

            Date dateReported = new Date(); //Default constructor creates Date with current date/time

            String description = Input.getStringInput("Enter problem");
            String reporter = Input.getStringInput("Who reported this issue?");
            int priority = Input.getPositiveIntInput("Enter priority of " + description);

            Ticket t = new Ticket(description, priority, reporter, dateReported);
            //ticketQueue.add(t);
            addTicketInPriorityOrder(t);

            printAllTickets();

            String more = Input.getStringInput("More tickets to add? Enter N for no, anything else to add more tickets");

            if (more.equalsIgnoreCase("N")) {
                return;
            }
        }
    }


    protected void addTicketInPriorityOrder(Ticket newTicket){

        //Logic: assume the list is either empty or sorted

        if (ticketQueue.size() == 0 ) {//Special case - if list is empty, add ticket and return
            ticketQueue.add(newTicket);
            return;
        }

        //Tickets with the HIGHEST priority number go at the front of the list. (e.g. 5=server on fire)
        //Tickets with the LOWEST value of their priority number (so the lowest priority) go at the end

        int newTicketPriority = newTicket.getPriority();

        for (int x = 0; x < ticketQueue.size() ; x++) {    //use a regular for loop so we know which element we are looking at

            //if newTicket is higher or equal priority than the this element, add it in front of this one, and return
            if (newTicketPriority >= ticketQueue.get(x).getPriority()) {
                ticketQueue.add(x, newTicket);
                return;
            }
        }

        //Will only get here if the ticket is not added in the loop
        //If that happens, it must be lower priority than all other tickets. So, add to the end.
        ticketQueue.addLast(newTicket);
    }


    protected void printAllTickets() {
        System.out.println(" ------- All open tickets ----------");

        for (Ticket t : ticketQueue ) {
            System.out.println(t); // This calls the  toString method for the Ticket object.
        }
        System.out.println(" ------- End of ticket list ----------");

    }




    /* Main is hiding down here. Create a TicketManager object, and call the mainMenu method.
    Avoids having to make all of the methods in this class static. */
    public static void main(String[] args)  {






        TicketManager manager = new TicketManager();

        //TODO problem 8 load open tickets from a file

        //TODO Problem 9 how will you know what ticket ID to start with?



        manager.mainMenu();
    }

}

