package org.example.model;

public class InvestmentAccount {
    private String typeAccount; // Тип счета
//    private float amount; // текущее Количество наличных в портфеле на которую можно купить бумаги
    private double amountDepositForYear; // сумма пополнения счета

    public String getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(String typeAccount) {
        this.typeAccount = typeAccount;
    }

//    public float getAmount() {
//        return amount;
//    }
//
//    public void setAmount(float amount) {
//        this.amount = amount;
//    }

    public double getAmountDepositForYear() {
        return amountDepositForYear;
    }

    public void setAmountDepositForYear(double amountDepositForYear) {
        this.amountDepositForYear = amountDepositForYear;
    }
}
