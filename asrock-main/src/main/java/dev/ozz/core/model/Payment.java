package dev.ozz.core.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Payment {
    private IntegerProperty payment;
    private IntegerProperty infoID;
    private LongProperty pay_amount;
    private ObjectProperty<LocalDate> payment_date;
    private LongProperty balance;
    private ObjectProperty<Debtor> debtorID;

    public Payment(int payment,
            int infoID,
            long pay_amount,
            LocalDate payment_date,
            long balance,
            Debtor debtorID) {
        this.payment = new SimpleIntegerProperty(payment);
        this.infoID = new SimpleIntegerProperty(infoID);
        this.pay_amount = new SimpleLongProperty(pay_amount);
        this.payment_date = new SimpleObjectProperty<>(payment_date);
        this.balance = new SimpleLongProperty(balance);
        this.debtorID = new SimpleObjectProperty<>(debtorID);

    }

    // getters
    public int getPayment() {
        return payment.get();
    }

    public int getInfoID() {
        return infoID.get();
    }

    public long getPay_amount() {
        return pay_amount.get();
    }

    public LocalDate getPaymnet_date() {
        return payment_date.get();
    }

    public long getBalance() {
        return balance.get();
    }

    public Debtor getDebtorID() {
        return debtorID.get();
    }

    // setters
    public void setPayment(int payment) {
        this.payment.set(payment);
    }

    public void setInfoID(int infoID) {
        this.infoID.set(infoID);
    }

    public void setPay_amount(long pay_amount) {
        this.pay_amount.set(pay_amount);
    }

    public void setPayment_date(LocalDate payment_date) {
        this.payment_date.set(payment_date);
    }

    public void setBalance(long balance) {
        this.balance.set(balance);
    }

    public void setDebtorID(Debtor debtorID) {
        this.debtorID.set(debtorID);
    }

    // properties
    public IntegerProperty paymentProperty() {
        return payment;
    }

    public IntegerProperty infoIDProperty() {
        return infoID;
    }

    public LongProperty pay_amountProperty() {
        return pay_amount;
    }

    public ObjectProperty<LocalDate> payment_dateProperty() {
        return payment_date;
    }

    public LongProperty balanceProperty() {
        return balance;
    }

    public ObjectProperty<Debtor> debtorIDProperty() {
        return debtorID;
    }

}
