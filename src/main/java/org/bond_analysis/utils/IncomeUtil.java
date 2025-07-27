package org.bond_analysis.utils;

import org.bond_analysis.model.InvestmentAccount;
import org.bond_analysis.model.Bond;
import org.bond_analysis.model.Waste;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IncomeUtil {
    private final static int MAX_AMOUNT_FOR_TAX_DEDUCTION = 400_000;

    /**
     * Расчитать налоговый вычет
     * @param investmentAccount
     * @param waste
     * @return
     */
    public static double getTaxDeduction(InvestmentAccount investmentAccount, Waste waste) {
        double taxDeduction;
        if (investmentAccount.getAmountDepositForYear() >= MAX_AMOUNT_FOR_TAX_DEDUCTION) {
            taxDeduction = MAX_AMOUNT_FOR_TAX_DEDUCTION * waste.getTax();
        } else {
            taxDeduction = investmentAccount.getAmountDepositForYear() * waste.getTax();
        }
        return taxDeduction;
    }

    /**
     * Вычислить накопленный купонный доход
     * @param bond
     * @return
     */
    public static double getAccumulatedCouponIncome(Bond bond, LocalDate operationDate) {

        List<LocalDate> paymentDatesWithReleaseDate = new ArrayList<>(List.of(bond.getReleaseDate()));
        paymentDatesWithReleaseDate.addAll(BondUtil.getPaymentDatesCoupon(bond));

        int nextIndexCouponDate = findIndexStartNextPaymentDate(paymentDatesWithReleaseDate, operationDate);
        int countDayBetweenCoupons = getCountDayBetweenCoupons(paymentDatesWithReleaseDate, nextIndexCouponDate);
        int countDayAccumulatedCouponIncome = paymentDatesWithReleaseDate.get(nextIndexCouponDate).getDayOfYear()
                - bond.getPurchaseDate().getDayOfYear();
        double onePercentIncome = countDayBetweenCoupons / 100.0;
        double percentAccumulatedCouponIncome = onePercentIncome * countDayAccumulatedCouponIncome / 100.0;
        return percentAccumulatedCouponIncome * bond.getFixedCouponAmount();
    }

    private static int getCountDayBetweenCoupons(List<LocalDate> paymentDatesWithReleaseDate, int nextIndexCouponDate) {
        return paymentDatesWithReleaseDate.get(nextIndexCouponDate).getDayOfYear()
                - paymentDatesWithReleaseDate.get(nextIndexCouponDate - 1).getDayOfYear();
    }

    public static double getCouponIncomeBetweenDates(Bond bond) {
        LocalDate buyDate = bond.getPurchaseDate();
        LocalDate saleDate = bond.getSaleDate();
        List<LocalDate> paymentDatesWithReleaseDate = new ArrayList<>(BondUtil.getPaymentDatesCoupon(bond));
        int nextIndexCouponDate = findIndexStartNextPaymentDate(paymentDatesWithReleaseDate, buyDate);
        int lastIndexCouponDate = findIndexLastPaymentDate(paymentDatesWithReleaseDate, saleDate);
        int paymentCount = paymentDatesWithReleaseDate.size() - (nextIndexCouponDate + 1 + paymentDatesWithReleaseDate.size() - (lastIndexCouponDate + 1));
        return paymentCount * bond.getFixedCouponAmount();
    }

    /**
     * находим дату следующей выплаты купона
     * @param paymentDatesWithReleaseDate
     * @param currentDate
     * @return
     */
    private static int findIndexStartNextPaymentDate(List<LocalDate> paymentDatesWithReleaseDate, LocalDate currentDate) {
        int indexNextPaymentDate = -1;
        for (int i = 0; i < paymentDatesWithReleaseDate.size() - 1; i++) {
            LocalDate paymentDate = paymentDatesWithReleaseDate.get(i);
            LocalDate nextPaymentDate = paymentDatesWithReleaseDate.get(i + 1);
            if (isDateInRange(paymentDate, nextPaymentDate, currentDate)) {
                indexNextPaymentDate = i + 1;
                break;
            }
        }
        return indexNextPaymentDate;
    }

    /**
     * находим дату следующей выплаты купона
     * @param paymentDatesWithReleaseDate
     * @param currentDate
     * @return
     */
    private static int findIndexLastPaymentDate(List<LocalDate> paymentDatesWithReleaseDate, LocalDate currentDate) {
        int indexLastPaymentDate = -1;
        for (int i = paymentDatesWithReleaseDate.size() - 1; i > 0; i--) {
            LocalDate paymentDate = paymentDatesWithReleaseDate.get(i);
            LocalDate lastPaymentDate = paymentDatesWithReleaseDate.get(i - 1);
            if (isDateInRange(lastPaymentDate, paymentDate, currentDate)) {
                indexLastPaymentDate = i - 1;
                break;
            }
        }

        return indexLastPaymentDate;
    }

    private static boolean isDateInRange(LocalDate startData, LocalDate endDate, LocalDate checkDate) {
        return startData.isBefore(checkDate) && (endDate.isAfter(checkDate) || startData.isEqual(checkDate));
    }
}
