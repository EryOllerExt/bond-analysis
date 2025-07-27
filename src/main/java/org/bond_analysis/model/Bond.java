package org.bond_analysis.model;

import java.time.LocalDate;

public class Bond {
    private int countBond; // количество облигаций
    private double fixedCouponAmount; // фиксированный размер купона
    private double currentCost; // текущая стоимость
    private double firstCost; // изначальная стоимость
    private double estimatedSalePrice; // предполагаемая цена продажи
    private LocalDate releaseDate; // дата выпуска
    private LocalDate repaymentDate; // дата погашения

    private LocalDate purchaseDate; // дата покупки
    private LocalDate saleDate; // дата продажи

    private int countCouponForYear = 1; // количество купонов за год

    public double getFirstCost() {
        return firstCost;
    }

    public void setFirstCost(double firstCost) {
        this.firstCost = firstCost;
    }

    public double getFixedCouponAmount() {
        return fixedCouponAmount;
    }

    public void setFixedCouponAmount(double fixedCouponAmount) {
        this.fixedCouponAmount = fixedCouponAmount;
    }

    public double getCurrentCost() {
        return currentCost;
    }

    public void setCurrentCost(double currentCost) {
        this.currentCost = currentCost;
    }

    public double getEstimatedSalePrice() {
        return estimatedSalePrice;
    }

    public void setEstimatedSalePrice(double estimatedSalePrice) {
        this.estimatedSalePrice = estimatedSalePrice;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LocalDate getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(LocalDate repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public int getCountCouponForYear() {
        return countCouponForYear;
    }

    public void setCountCouponForYear(int countCouponForYear) {
        this.countCouponForYear = countCouponForYear;
    }

    public int getCountBond() {
        return countBond;
    }

    public void setCountBond(int countBond) {
        this.countBond = countBond;
    }
}
