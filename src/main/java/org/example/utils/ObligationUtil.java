package org.example.utils;

import org.example.model.Bond;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class ObligationUtil {

    /**
     * Список дат выплаты купонов
     * @param bond
     * @return
     */
    public static List<LocalDate> getPaymentDatesCoupon(Bond bond) {
        int countMonthsInYear = 12;
        List<LocalDate> paymentDates = new ArrayList<>();

        int countMountBetweenGettingCoupon = countMonthsInYear / bond.getCountCouponForYear();

        if (nonNull(bond.getReleaseDate())) {
            for (LocalDate startDate = bond.getReleaseDate().plusMonths(countMountBetweenGettingCoupon);
                 startDate.isBefore(bond.getRepaymentDate()) || startDate.isEqual(bond.getRepaymentDate());
                 startDate = startDate.plusMonths(countMountBetweenGettingCoupon)) {
                if (startDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
                    paymentDates.add(
                            LocalDate.of(startDate.getYear(),
                                    startDate.getMonth(),
                                    startDate.minusDays(1).getDayOfMonth())
                    );
                } else if (startDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                    paymentDates.add(
                            LocalDate.of(startDate.getYear(),
                                    startDate.getMonth(),
                                    startDate.minusDays(2).getDayOfMonth())
                    );
                } else {
                    paymentDates.add(startDate);
                }
            }
        }

        return paymentDates;
    }

//    /**
//     * Получить накопленный купонный доход за день
//     * @return
//     */
//    public static float getPriceAccumulatedCouponIncomeForDay(Obligation obligation) {
//        // написать алгоритм по вычислению весоконсного года
//        long periodGettingCoupon = 365 / obligation.getCountCouponForYear(); // количество дней между купонами
//        return (obligation.getFixedCouponAmount() / periodGettingCoupon) * obligation.getCountObligation();
//    }
}
