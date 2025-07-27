package org.bond_analysis;

import org.bond_analysis.service.MenuService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Инвестиционный калькулятор");
        MenuService menuService = new MenuService();
        menuService.run();
    }
}