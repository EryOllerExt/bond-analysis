package org.example.model;

public class Waste {
    private double commissionBroker; //коммисия брокера
    private double commissionExchange; // коммиссия биржи
    private double tax; // налог

    public double getCommissionBroker() {
        return commissionBroker;
    }

    public void setCommissionBroker(double commissionBroker) {
        this.commissionBroker = commissionBroker;
    }

    public double getCommissionExchange() {
        return commissionExchange;
    }

    public void setCommissionExchange(double commissionExchange) {
        this.commissionExchange = commissionExchange;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }
}
