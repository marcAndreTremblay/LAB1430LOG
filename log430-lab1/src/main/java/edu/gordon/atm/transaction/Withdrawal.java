/* * ATM Example system - file Withdrawal.java    x * * copyright (c) 2001 - Russell C. Bjork * */ package edu.gordon.atm.transaction;import edu.gordon.atm.physical.CustomerConsole;import edu.gordon.atm.physical.CustomerConsole.Cancelled;import edu.gordon.atm.physical.CashDispenser;import edu.gordon.banking.AccountInformation;import edu.gordon.banking.Card;import edu.gordon.banking.Message;import edu.gordon.banking.Money;import edu.gordon.banking.Receipt;/** Representation for a cash withdrawal transaction */public class Withdrawal extends Transaction{		CashDispenser dispenser;    /** Constructor     *     *  @param edu.gordon.atm the ATM used to communicate with customer     *  @param session the session in which the transaction is being performed     *  @param card the customer's card     *  @param pin the PIN entered by the customer     */    public Withdrawal( Card card, int pin,CashDispenser _dispenser)    {        super(card, pin);        dispenser = _dispenser;    }        /** Get specifics for the transaction from the customer     *     *  @return message to bank for initiating this transaction     *  @exception CustomerConsole.Cancelled if customer cancelled this transaction     */    public Message getSpecificsFromCustomer(CustomerConsole console) throws CustomerConsole.Cancelled    {        from = console.readMenuChoice(            "Account to withdraw from",            AccountInformation.ACCOUNT_NAMES);        String [] amountOptions = { "$20", "$40", "$60", "$100", "$200" };        Money [] amountValues = {                                   new Money(20), new Money(40), new Money(60),                                  new Money(100), new Money(200)                                };                                          String amountMessage = "";        boolean validAmount = false;                while (! validAmount)        {            amount = amountValues [                 console.readMenuChoice(                    amountMessage + "Amount of cash to withdraw", amountOptions) ];                                        validAmount = dispenser.checkCashOnHand(amount);            if (! validAmount)                amountMessage = "Insufficient cash available\n";        }                return new Message(Message.WITHDRAWAL,                            card, pin, serialNumber, from, -1, amount);    }        /** Complete an approved transaction     *     *  @return receipt to be printed for this transaction     */    public void completeTransaction(Receipt c_receipt)    {    	dispenser.dispenseCash(amount);    	c_receipt.detailsPortion = new String[2];                c_receipt.detailsPortion[0] = "WITHDRAWAL FROM: " +                                     AccountInformation.ACCOUNT_ABBREVIATIONS[from];                c_receipt.detailsPortion[1] = "AMOUNT: " + amount.toString();    }        /** Account to withdraw from     */     private int from;        /** Amount of money to withdraw     */    private Money amount;          }