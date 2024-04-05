package cz.cvut.fel.pjv;

public class Lab02 {
   public void start() {
      homework();
   }
   public void homework()
   {
      TextIO ObjectTextIO = new TextIO();
      String nextLine = ObjectTextIO.getLine();
      int nextLineNumber = 1;
      byte counterCyclus = 0;
      double[] ListOfNumbers = new double[10];
      while (nextLine.length() > 0) {
         if (TextIO.isDouble(nextLine)) {
            ListOfNumbers[counterCyclus] = Double.parseDouble(nextLine);
            counterCyclus++;
         } else {
            System.out.println("A number has not been parsed from line " + nextLineNumber);
         }
         boolean wasPrinted = false;
         if (counterCyclus >= 10) {
            printCalculationLine(ListOfNumbers, 10);
            wasPrinted = true;
            ListOfNumbers = new double[10];
            counterCyclus = 0;
         }
         nextLine = ObjectTextIO.getLine();
         if (nextLine.length() == 0) {
            System.out.println("End of input detected!");
            if (!wasPrinted && counterCyclus > 1) {
               printCalculationLine(ListOfNumbers, counterCyclus);
            }
         }
         nextLineNumber++;
      }
   }
   public double calculateDeviation(double[] ListOfNumbers, double average, int count)
   {
      double[] squaredDeviations = new double[count];
      double otklonenie;
      double dispersion;
      for (int i = 0; i < count; i++) {
         squaredDeviations[i] = Math.pow(ListOfNumbers[i] - average, 2);
      }
      dispersion = calculateAverage(squaredDeviations, count);
      otklonenie = Math.sqrt(dispersion);
      return otklonenie;
   }
   public double calculateAverage(double[] ListOfNumbers, int count)
   {
      double result = 0;
      for (int i = 0; i < count; i++) {
         result += ListOfNumbers[i];
      }
      result = result / count;
      return result;
   }
   public void printCalculationLine(double[] ListOfNumbers, int count)
   {
      double averageNumber = calculateAverage(ListOfNumbers, count);
      double otklonenie = calculateDeviation(ListOfNumbers, averageNumber, count);
      System.out.printf("%2d %.3f %.3f%n", count, averageNumber, otklonenie);
   }
}