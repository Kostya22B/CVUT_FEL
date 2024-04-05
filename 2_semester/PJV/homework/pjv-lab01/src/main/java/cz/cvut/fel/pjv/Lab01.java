package cz.cvut.fel.pjv;

import java.util.Scanner;

public class Lab01 {

   public void start() {
      homework();
   }

   public void homework() {
      Scanner console = new Scanner(System.in);
      System.out.println("Vyber operaci (1-soucet, 2-rozdil, 3-soucin, 4-podil):");
      int vyber = console.nextInt();
      if (vyber == 1) { //soucet
         System.out.println("Zadej scitanec: ");
         double x = console.nextDouble();
         System.out.println("Zadej scitanec: ");
         double y = console.nextDouble();//kek
         System.out.println("Zadej pocet desetinnych mist: ");
         int numberOfDecimalPlaces = console.nextInt();
         if (numberOfDecimalPlaces < 0) {
            System.out.println("Chyba - musi byt zadane kladne cislo!");
         } else {
            String numberFormat = "%." + numberOfDecimalPlaces + "f";
            double result = x + y;
            System.out.printf(numberFormat + " + " + numberFormat + " = " + numberFormat + "%n", x, y, result);
         }
      }
      else if (vyber == 2) { //rozdil
         System.out.println("Zadej mensenec: ");
         double x = console.nextDouble();
         System.out.println("Zadej mensitel: ");
         double y = console.nextDouble();
         System.out.println("Zadej pocet desetinnych mist: ");
         int numberOfDecimalPlaces = console.nextInt();
         if (numberOfDecimalPlaces < 0) {
            System.out.println("Chyba - musi byt zadane kladne cislo!");
         } else {
            String numberFormat = "%." + numberOfDecimalPlaces + "f";
            double result = x - y;
            System.out.printf(numberFormat + " - " + numberFormat + " = " + numberFormat + "%n", x, y, result);
         }
      }
      else if (vyber == 3) { //soucin
         System.out.println("Zadej cinitel: ");
         double x = console.nextDouble();
         System.out.println("Zadej cinitel: ");
         double y = console.nextDouble();
         System.out.println("Zadej pocet desetinnych mist: ");
         int numberOfDecimalPlaces = console.nextInt();
         if (numberOfDecimalPlaces < 0) {
            System.out.println("Chyba - musi byt zadane kladne cislo!");
         } else {
            String numberFormat = "%." + numberOfDecimalPlaces + "f";
            double result = x * y;
            System.out.printf(numberFormat + " * " + numberFormat + " = " + numberFormat + "%n", x, y, result);
         }
      }
      else if (vyber == 4) { //soucin
         System.out.println("Zadej delenec: ");
         double x = console.nextDouble();
         System.out.println("Zadej delitel: ");
         double y = console.nextDouble();
         if (y==0){
            System.out.println("Pokus o deleni nulou!");
         } else {
            System.out.println("Zadej pocet desetinnych mist: ");
            int numberOfDecimalPlaces = console.nextInt();
            if (numberOfDecimalPlaces < 0) {
               System.out.println("Chyba - musi byt zadane kladne cislo!");
            } else {
               String numberFormat = "%." + numberOfDecimalPlaces + "f";
               double result = x / y;
               System.out.printf(numberFormat + " / " + numberFormat + " = " + numberFormat + "%n", x, y, result);
            }
         }
      }
      else {
         System.out.println("Chybna volba!");
      }
   }
}
