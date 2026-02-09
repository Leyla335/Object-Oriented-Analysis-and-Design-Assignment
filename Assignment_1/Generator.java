// Leyla Aliyeva
// Assignment 1
// Object Oriented Analysis and Design - CRN: 20966
// 09.02.2026

package Assignment_1;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/* 
Class Generator - here we define a class called Generator.
The work of the class is to use three different types of random generators
and gain statistical data from it. 
*/
public class Generator {

    // Class Attribute
    // Example of accessibility type: private.
    // The variable random can be accessed only within the class in which it is declared.

     private Random random = new Random();

    /* 
    Method Populate - takes two input parameters - n and randNumGen.
    Here n stands for the number of double values that will be generated and
    randNumGen is specifying which random number generator will be used.
    Depending on the value of randNumGen, the method uses java.util.Random,
    Math.random(), or java.util.concurrent.ThreadLocalRandom to generate n random values in the range [0, 1).
    All generated values are stored in an ArrayList<Double>, which is then returned by the method. 
    */ 
    public ArrayList<Double> populate(int n, int randNumGen) {

        ArrayList<Double> valuesList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            double number;

            if (randNumGen == 1) {

                number = random.nextDouble();

            } else if (randNumGen == 2) {

                number = Math.random();

            } else if (randNumGen == 3) {

                number = ThreadLocalRandom.current().nextDouble();

            } else {
                System.out.println("Invalid random Number Generator!");
                break;
            }

            valuesList.add(number);
        }
        return valuesList;
    }

    /*
    statistics(ArrayList<Double> randomValues)

    This method is used to calculate the statistics for a given list of random values.
    It computes the number of elements (n), the mean, the sample standard deviation, 
    the minimum value, and the maximum value.
    The calculated statistics are stored in an ArrayList<Double> and returned in the given order:
    [n, mean, standard deviation, minimum, maximum]. */

    // Accessibility - public. Because the function is public, it can be accessed from anywhere.
    public ArrayList<Double> statistics(ArrayList<Double> randomValues) {
        ArrayList<Double> results = new ArrayList<>();

        int n = randomValues.size();
        double sum = 0;
        double min = randomValues.get(0);
        double max = randomValues.get(0);

        for (double a : randomValues) {
            sum += a;
            if (a < min)
                min = a;
            if (a > max)
                max = a;
        }

        double mean = sum / n;

        double x = 0.0;

        for (double b : randomValues) {
            x += Math.pow(b - mean, 2);
        }

        double standDev = Math.sqrt(x / (n - 1));

        results.add((double) n);
        results.add(mean);
        results.add(standDev);
        results.add(min);
        results.add(max);

        return results;
    }

    /*
    display(ArrayList<Double> results, boolean headerOn)

    This method displays the calculated statistical results in a table form on the 
    system terminal.  
    If the headerOn parameter is set to true, a header row describing each column 
    is printed before the results.
    The method formats and prints the values of n, mean, standard 
    deviation, minimum, and maximum to comparison. 
    */
    public void display(ArrayList<Double> results, boolean headerOn) {

        if (headerOn) {
            System.out.printf("%-8s %-12s %-12s %-12s %-12s%n",
                    "n", "Mean", "StdDev", "Min", "Max");
        }

        System.out.printf("%-8.0f %-12.5f %-12.5f %-12.5f %-12.5f%n",
                results.get(0),
                results.get(1),
                results.get(2),
                results.get(3),
                results.get(4));

        System.out.println();
    }

    /*
    execute()

    This method controls the general execution of the program.
    It uses all three random number generation tools for every 
    sample size as it iterates through specified sample sizes.
    It produces and presents the statistical outcomes for every 
    combination by calling the populate, statistics, and display methods.
     */
    public void execute() {

        int[] nValues = {77, 222, 1012};
    

        for (int n : nValues) {
            boolean headerPrinted = false;
            for (int gen = 1; gen <= 3; gen++) {

                ArrayList<Double> data = populate(n, gen);
                ArrayList<Double> stats = statistics(data);

                display(stats, !headerPrinted);
                headerPrinted = true;
            }
        }
    }

    /*
    main(String[] args)
    The main method is needed as the entry point of the program.
    To launch the program, it calls the execute() 
    method and creates an instance of the Generator class. 
    */
    public static void main(String[] args) {

        // Object instantiation - we create an instance of Generator class and store its reference in g.
        Generator g = new Generator();
        g.execute();
    }
}