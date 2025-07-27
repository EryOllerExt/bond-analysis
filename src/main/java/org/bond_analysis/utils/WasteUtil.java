package org.bond_analysis.utils;

import org.bond_analysis.model.Bond;
import org.bond_analysis.model.Waste;

public class WasteUtil {

    public static double getWasteOnCommission(Bond bond, Waste waste) {
        double wasteOnCommission = getWasteOnCommissionUntilFullRepayment(bond, waste);
        if (!bond.getRepaymentDate().equals(bond.getSaleDate())) {
            wasteOnCommission += bond.getEstimatedSalePrice() * (waste.getCommissionBroker() + waste.getCommissionExchange());
        }
        return wasteOnCommission;
    }

    public static double getWasteOnCommissionUntilFullRepayment(Bond bond, Waste waste) {
        return bond.getCurrentCost() * (waste.getCommissionBroker() + waste.getCommissionExchange());
    }
}
