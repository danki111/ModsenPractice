package org.example;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double exchangeRate = getExchangeRateFromFile();

        // Выводим меню
        System.out.println("Меню:");
        System.out.println("1. Конвертировать рубли в доллары");
        System.out.println("2. Конвертировать доллары в рубли");
        System.out.println("3. Сложить валюты одного типа");
        System.out.println("4. Выход");

        int choice;
        do {
            // Получаем выбор пользователя
            System.out.print("Выберите пункт меню: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println("Ошибка! Введите число.");
                choice = 4;
            }
            switch (choice) {
                case 1:
                    convertRublesToDollars(exchangeRate);
                    break;
                case 2:
                    convertDollarsToRubles(exchangeRate);
                    break;
                case 3:
                    addCurrenciesOfSameType();
                    break;
                case 4:
                    System.out.println("Программа завершена.");
                    break;
                default:
                    System.out.println("Неверный выбор. Повторите попытку.");
                    break;
            }
        } while (choice != 4);

        scanner.close();
    }

    // Метод для получения курса доллара
    private static double getExchangeRateFromFile() {
        double exchangeRate = 70.00;

        try {
            File file = new File("exchange_rate.txt");
            Scanner scanner = new Scanner(file);

            if (scanner.hasNextDouble()) {
                exchangeRate = scanner.nextDouble();
            } else {
                System.out.println("Ошибка чтения курса доллара из файла. Используется значение по умолчанию.");
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл exchange_rate.txt не найден. Используется значение по умолчанию.");
        }

        return exchangeRate;
    }

    // Метод для конвертации рублей в доллары
    private static void convertRublesToDollars(double exchangeRate) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите сумму в рублях: ");
        if (scanner.hasNextDouble()) {
            double rubles = scanner.nextDouble();
            double dollars = rubles / exchangeRate;
            System.out.printf("%.2fр = $%.2f\n", rubles, dollars);
        } else {
            System.out.println("Ошибка! Введите число.");
        }
    }

    // Метод для конвертации долларов в рубли
    private static void convertDollarsToRubles(double exchangeRate) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите сумму в долларах: ");
        if (scanner.hasNextDouble()) {
            double dollars = scanner.nextDouble();
            double rubles = dollars * exchangeRate;
            System.out.printf("$%.2f = %.2fр\n", dollars, rubles);
        } else {
            System.out.println("Ошибка! Введите число.");
        }
    }

    // Метод для сложения валют одного типа
    private static void addCurrenciesOfSameType() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Выберите тип валюты (1 - рубли, 2 - доллары): ");
        if (scanner.hasNextInt()) {
            int currencyType = scanner.nextInt();
            if (currencyType == 1 || currencyType == 2){

                System.out.print("Введите количество валюты: ");
                if (scanner.hasNextDouble()) {
                    double amount = scanner.nextDouble();

                    System.out.print("Введите количество дополнительной валюты для сложения: ");
                    if (scanner.hasNextDouble()) {
                        double additionalAmount = scanner.nextDouble();
                        if (additionalAmount >= 0) {
                            double totalAmount = amount + additionalAmount;
                            if (currencyType == 1){
                                System.out.printf("Общая сумма: %.2fр\n", totalAmount);
                            } else {
                                System.out.printf("Общая сумма: $%.2f\n", totalAmount);
                            }
                        } else {
                            System.out.println("Неверное количество дополнительной валюты.");
                        }
                    } else {
                        System.out.println("Ошибка! Введите число.");
                    }
                } else {
                    System.out.println("Ошибка! Введите число.");
                }
            }else {
                System.out.println("Ошибка! Введите верный тип.");
            }
        } else {
            System.out.println("Ошибка! Введите число.");
        }
    }

    }
