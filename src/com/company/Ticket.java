package com.company;

import java.util.Date;

public class Ticket {

    private int priority;
    private String reporter; //Stores person or department who reported issue
    private String description;
    private Date dateReported;
    protected Date resolvedDate;

    //TODO Problem 1: explain the role of ticketIdCounter and ticketID

    //STATIC Counter - one variable, shared by all Ticket objects.
    //If any Ticket object modifies this counter, all Ticket objects will have the modified value
    //Make it private - only Ticket objects should have access
    private static int ticketIdCounter = 1;

    protected String resolution ;
    protected String reason;

    //The ID for each ticket - an instance variable. Each Ticket will
    // have it's own ticketID variable
    protected int ticketID;


// TODO problem 6: tickets need to store the resolution date and a string describing the resolution
    // Either add them to this class or create another class called ResolvedTicket - which
    // do you think is the better approach?



    public Ticket(String desc, int p, String rep, Date date ) {
        this.description = desc;
        this.priority = p;
        this.reporter = rep;
        this.dateReported = date;

        this.ticketID = ticketIdCounter;

        ticketIdCounter++;
    }

    protected int getPriority() {
        return priority;
    }
    protected String getDescription(){return  description;}

    public int getTicketID() {
        return ticketID;
    }



    public String toString(){
        return("ID: " + this.ticketID + " Issue: " + this.description + " Priority: " + 					this.priority + " Reported by: "
                + this.reporter + " Reported on: " + this.dateReported);
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setResolvedDate(Date resolvedDate) {
        this.resolvedDate = resolvedDate;
    }
}

