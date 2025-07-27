package org.bond_analysis.model;

public class Income {
    private double fullIncomeUntilRepaymentDate; // полная доходность с погашением облигации по текущей стоимости облигации
    private double incomeFromCoupons; // Доход от купонов
    private double accumulatedCouponIncome; // накопленный купонный доход
    private double taxDeduction; //Налоговый вычет

    public double getFullIncomeUntilRepaymentDate() {
        return fullIncomeUntilRepaymentDate;
    }

    public void setFullIncomeUntilRepaymentDate(double fullIncomeUntilRepaymentDate) {
        this.fullIncomeUntilRepaymentDate = fullIncomeUntilRepaymentDate;
    }

    public double getIncomeFromCoupons() {
        return incomeFromCoupons;
    }

    public void setIncomeFromCoupons(double incomeFromCoupons) {
        this.incomeFromCoupons = incomeFromCoupons;
    }

    public double getAccumulatedCouponIncome() {
        return accumulatedCouponIncome;
    }

    public void setAccumulatedCouponIncome(double accumulatedCouponIncome) {
        this.accumulatedCouponIncome = accumulatedCouponIncome;
    }

    public double getTaxDeduction() {
        return taxDeduction;
    }

    public void setTaxDeduction(double taxDeduction) {
        this.taxDeduction = taxDeduction;
    }
}
