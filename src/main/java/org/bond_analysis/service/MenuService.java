package org.bond_analysis.service;

import org.bond_analysis.model.InvestmentAccount;
import org.bond_analysis.model.Bond;
import org.bond_analysis.model.Waste;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import static org.bond_analysis.utils.IncomeUtil.*;
import static org.bond_analysis.utils.WasteUtil.getWasteOnCommission;
import static org.bond_analysis.utils.WasteUtil.getWasteOnCommissionUntilFullRepayment;

public class MenuService {

    public void startApplication() {
        System.out.println("Добро пожаловать в приложение по расчету доходности облигаций!");
    }

    public void printHelp() {
        System.out.println("""
                Для расчета доходности необходимо заполнить следующие параметры:
                         * Стоимость облигации
                         * Размер фиксированного купона
                         * Текущая стоимость облигации
                         * Дата выпуска облигации
                         * Дата погашения облигации
                         * Планируемая дата покупки облигации
                         * Планируемая дата продажи облигации
                         * Частота выплат купонов по облигации в год,
                
                а также процент налога и различных коммиссий:
                         * Комиссия брокера
                         * Комиссия биржи
                         * Налог на прибыль.
                """);

        System.out.println("""
                ТЕРМИНЫ И ОПРЕДЕЛЕНИЯ:
                         * Облигация - это долговая бумага (ценная бумага), которая торгуется на фондовом рынке.
                         Аналог выдачи кредита банком заемщику, где Вы выступаете в роле банка, а эмитент- заемщиком.
                
                         * Фондовый рынок (биржа) - организация выступающая посредником между участниками фондового рынка.
                
                         * Эмитент - организация (участник фондового рынка) выпустившая ценную бумагу.
                
                         * Купон - выплата за использование денежных средств эмитентом.
                            Типы купона:
                            -Фиксированный
                                Аналог процентной ставки по вкладу в банке.
                            -Плавающий
                                Аналог фиксированного купона с прибавлением процента официального уровня инфляции 
                            -С частичным погашением
                                Аналог кредита в банке, где возвращают выплату за использование денежных средств и
                                часть стоимости самой облигации
                
                         * Брокер - организация выступающая посредником между свои клиентом и фондовым рынком
                
                         * Комиссия брокера - плата за операции над ценными бумагами брокеру.
                
                         * Коммиссия биржи  - плата за операции над ценными бумагами с участником биржи.
                """);
    }

    public void printCommandsForAction() {
        System.out.println("""
                 Введите номер пункта, для действия.
                \t 1 - Заполнить данные для расчета
                \t 2 - Произвести расчет
                \t 3 - Помощь
                
                \t 4 - Выход
                """);
    }

    public void printCommandsForInput(Bond bond, Waste waste, InvestmentAccount investmentAccount) {
        System.out.println(" Введите номер пункта, по которому нужно заполнить данные.\n" +
                "Параметры по облигации:\n" +
                "\t 1 - Стоимость облигации (текущее значение = " + bond.getFirstCost() + ")\n" +
                "\t 2 - Размер фиксированного купона (текущее значение = " + bond.getFixedCouponAmount() + ")\n" +
                "\t 3 - Текущая стоимость облигации (текущее значение = " + bond.getCurrentCost() + ")\n" +
                "\t 4 - Предполагаемая цена продажи (текущее значение = " + bond.getEstimatedSalePrice() + ")\n" +
                "\t 5 - Дата выпуска облигации (текущее значение = " + bond.getReleaseDate() + ")\n" +
                "\t 6 - Дата погашения облигации (текущее значение = " + bond.getRepaymentDate() + ")\n" +
                "\t 7 - Планируемая дата покупки облигации (текущее значение = " + bond.getPurchaseDate() + ")\n" +
                "\t 8 - Планируемая дата продажи облигации (текущее значение = " + bond.getSaleDate() + ")\n" +
                "\t 9 - Частота выплат купонов по облигации в год (текущее значение = " + bond.getCountCouponForYear() + ")\n" +
                "\t 10 - Количество облигаций (текущее значение = " + bond.getCountBond() + ")\n\n" +

                "Комиссии:\n" +
                "\t 11 - Комиссия брокера (текущее значение = " +  waste.getCommissionBroker() + ")\n" +
                "\t 12 - Комиссия биржи (текущее значение = " +  waste.getCommissionExchange() + ")\n\n" +

                "Налог:\n" +
                "\t 13 - Налог на прибыль (текущее значение = " +  waste.getTax() + ")\n\n" +

                "Инвестиционный счет:\n" +
                "\t 14 - Сумма пополнения инвестиционного счета (текущее значение = " +  investmentAccount.getAmountDepositForYear() + ")\n\n\n" +

                "\t 15 - Выход из заполнения");
    }

    public void run() {
        int command = -1;
        Scanner scanner = new Scanner(System.in);
        Bond bond = new Bond();
        Waste waste = new Waste();
        InvestmentAccount investmentAccount = new InvestmentAccount();
        startApplication();
        while (command != 4) {
            printCommandsForAction();
            System.out.print("Выберете команду: ");

            try {
                command = scanner.nextInt();
                if (command == 1) {
                    try {
                        inputParams(scanner, bond, waste, investmentAccount);
                    } catch (Exception e) {
                        scanner.next();
                        System.out.println("Произошла ошибка при заполнении данных: " + e.getMessage());
                    }
                } else if (command == 2) {
                    try {
                        printParameterCalculation(bond, waste, investmentAccount);
                    } catch (Exception e) {
                        System.out.println("Произошла ошибка в расчетах: " + e.getMessage());
                    }
                } else if (command == 3) {
                    printHelp();
                }
            } catch (Exception e) {
                System.out.println("Неверно указана команда!");
                scanner.next();
            }

        }
        System.out.println("Выход из приложения!");
    }

    public void inputParams(Scanner scanner, Bond bond, Waste waste, InvestmentAccount investmentAccount) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        int command = -1;
        while (command != 15) {
            printCommandsForInput(bond, waste, investmentAccount);
            System.out.print("Выберете команду: ");
            command = scanner.nextInt();
            if (command == 1) {
                System.out.print("Введите стоимость облигации в формате 1000,1: ");
                bond.setFirstCost(scanner.nextDouble());
            } else if (command == 2) {
                System.out.print("Введите размер фиксированного купона в формате 8,1: ");
                bond.setFixedCouponAmount(scanner.nextDouble());
            } else if (command == 3) {
                System.out.print("Введите текущую стоимость облигации в формате 1000,1: ");
                bond.setCurrentCost(scanner.nextDouble());
            } else if (command == 4) {
                System.out.print("Введите введите предполагаемую стоимость облигации во время продажи в формате 1000,1: ");
                bond.setEstimatedSalePrice(scanner.nextDouble());
            } else if (command == 5) {
                System.out.print("Введите дату выпуска облигации в формате 01.01.1991: ");
                bond.setReleaseDate(LocalDate.parse(scanner.next(), formatter));
            } else if (command == 6) {
                System.out.print("Введите дату погашения облигации в формате 01.01.1991: ");
                bond.setRepaymentDate(LocalDate.parse(scanner.next(), formatter));
            } else if (command == 7) {
                System.out.print("Введите планиуемую дату покупки облигации в формате 01.01.1991: ");
                bond.setPurchaseDate(LocalDate.parse(scanner.next(), formatter));
            } else if (command == 8) {
                System.out.print("Введите планиуемую дату продажи облигации в формате 01.01.1991: ");
                bond.setSaleDate(LocalDate.parse(scanner.next(), formatter));
            } else if (command == 9) {
                System.out.print("Введите сколько раз в год выплачивают купоны по облигации: ");
                bond.setCountCouponForYear(scanner.nextInt());
            } else if (command == 10) {
                System.out.println("Введите количество приобретаемых облигаций: ");
                bond.setCountBond(scanner.nextInt());
            } else if (command == 11) {
                System.out.print("Введите комиссию брокера в формате 0,1: ");
                waste.setCommissionBroker(scanner.nextDouble());
            } else if (command == 12) {
                System.out.print("Введите комиссию биржи в формате 0,1: ");
                waste.setCommissionExchange(scanner.nextDouble());
            } else if (command == 13) {
                System.out.print("Введите налог на прибыль в формате 0,13: ");
                waste.setTax(scanner.nextDouble());
            } else if (command == 14) {
                System.out.println("Введите сумму пополнения инвестиционного счета в формате 50000,1: ");
                investmentAccount.setAmountDepositForYear(scanner.nextDouble());
            }
        }
        System.out.println("Ввод данных завершен");
    }

    public void printParameterCalculation(Bond bond, Waste waste, InvestmentAccount investmentAccount) {
        double amountDepositToAccount = investmentAccount.getAmountDepositForYear();
        double amountSpentOnSecurities = (bond.getCurrentCost() + getAccumulatedCouponIncome(bond, bond.getPurchaseDate())
                + getWasteOnCommission(bond, waste)) * bond.getCountBond();
        double couponIncomeForPeriodOwnershipWithIia = getCouponIncomeWithAccumulatedCouponIncome(bond) * bond.getCountBond();
        double couponIncomeForPeriodOwnershipWithoutIia = couponIncomeForPeriodOwnershipWithIia * (1 - waste.getTax());


        if ((amountDepositToAccount - amountSpentOnSecurities) >= 0) {
            System.out.println("Сумма проинвестированная в ценные бумаги: " + getAroundValue(amountDepositToAccount ));
            System.out.println("Сумма потраченная на ценные бумаги: " + getAroundValue(amountSpentOnSecurities));
            System.out.println("Количество облигаций: " + bond.getCountBond());
            System.out.println("Купонный доход вместе с накопленным купонным доходом за период владения " +
                    "без использования ИИС: " + getAroundValue(couponIncomeForPeriodOwnershipWithoutIia));
            System.out.println("Купонный доход вместе с накопленным купонным доходом за период владения " +
                    "с использованием ИИС: " + getAroundValue(couponIncomeForPeriodOwnershipWithIia));
            System.out.println("Трата на комиссиях: " + getAroundValue(getWasteOnCommission(bond, waste) * bond.getCountBond()));
            System.out.println("Сколько дней до конца погашения облигации: " + bond.getSaleDate().until(bond.getRepaymentDate(), ChronoUnit.DAYS));
            System.out.println("Трата на комиссии с учетом полного погашения облигации: " + getAroundValue(getWasteOnCommissionUntilFullRepayment(bond, waste) * bond.getCountBond()));
            System.out.println("Налоговый вычет за год за использование ИИС: " + getAroundValue(getTaxDeduction(investmentAccount, waste)));

            System.out.println("Выгода от использования ИИС: " + getAroundValue((couponIncomeForPeriodOwnershipWithIia - couponIncomeForPeriodOwnershipWithoutIia + getTaxDeduction(investmentAccount, waste))));

        } else {
            System.out.println( "Недостаточно денег для покупки " + getAroundValue(bond.getCountBond()) + " облигации(ий)");
            System.out.println(" Увеличте баланс на " + getAroundValue((amountDepositToAccount - amountSpentOnSecurities)));
        }
    }

    private double getCouponIncomeWithAccumulatedCouponIncome(Bond bond) {
        return getPartOfCouponIncome(bond) + getCouponIncomeBetweenDates(bond) + getAccumulatedCouponIncome(bond, bond.getSaleDate());
    }

    private double getPartOfCouponIncome(Bond bond) {
        return  bond.getFixedCouponAmount() - getAccumulatedCouponIncome(bond, bond.getPurchaseDate());
    }

    private double getAroundValue(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

}
