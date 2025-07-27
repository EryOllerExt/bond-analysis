package org.example;

import org.example.service.MenuService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Инвестиционный калькулятор");
        /*
         * Сумма инвестирования
         * Срок инвестирования
         * Процент инвестирования
         * период реинвестиции
         * дополнительные вложения
         */
        MenuService menuService = new MenuService();
        menuService.run();
    }

}