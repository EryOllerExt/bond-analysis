import org.bond_analysis.model.InvestmentAccount;
import org.bond_analysis.model.Bond;
import org.bond_analysis.model.Waste;
import org.bond_analysis.service.MenuService;
import org.junit.Test;


import java.time.LocalDate;

public class CalculateTest {

    @Test
    public void checkCalculateObligation() {
        MenuService menuService = new MenuService();

        Bond bond = new Bond();

        bond.setCountBond(100); // количество облигаций
        bond.setFixedCouponAmount(50.6); // фиксированный размер купона
        bond.setCurrentCost(990); // текущая стоимость
        bond.setFirstCost(1000); // изначальная стоимость
        bond.setEstimatedSalePrice(998); // предполагаемая цена продажи
        bond.setReleaseDate(LocalDate.of(2024, 9, 17)); // дата выпуска
        bond.setRepaymentDate(LocalDate.of(2029, 9, 17)); // дата погашения
        bond.setPurchaseDate(LocalDate.of(2025, 5, 11)); // дата покупки
        bond.setSaleDate(LocalDate.of(2026, 5, 11)); // дата продажи
        bond.setCountCouponForYear(4); // количество купонов за год

        Waste waste = new Waste();
        waste.setTax(0.13);
        waste.setCommissionBroker(0.01);
        waste.setCommissionExchange(0.01);

        InvestmentAccount investmentAccount =  new InvestmentAccount();
        investmentAccount.setAmountDepositForYear(105_000);

        menuService.printParameterCalculation(bond, waste, investmentAccount);
    }
}
