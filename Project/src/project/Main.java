package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("WELCOME");
            System.out.println("*********************************************************************");
            String operations = """
                          Options
                          1- Read data from file and construct data structures (You should start with this).
                          2- Provide input for a new customer from the keyboard.
                          3- Print rating score for each product.
                          4- Print the average considering only customers whose country is "Turkey" for each product.
                          5- Print the average considering only customers whose country is not "Turkey" for each product.
                          6- Print the average considering only customers whose occupation is "Doctor" for each product.
                          7- Print customer information linked list to the screen.
                          8- Print two-dimensional array to the screen.
                          9- Press 9 to exit...
                          What is your choice: """;

            Scanner scanner = new Scanner(new File("Firma.txt"));
            int line_controller = 1;

            LinkedList<CustomerNode> customerlist = new LinkedList<>();

            int i = 0;
            int[][] customer_arr = null;
            int customer_number = 0;
            int turkey_counter = 0;
            int not_turkey_counter = 0;
            int doctor_counter = 0;
            String country = null;
            String occupation = null;
            int[] turkey_totals = null;
            int[] notTurkey_totals = null;
            int[] doctor_totals = null;
            double[] turkey_avgs;
            double[] notTurkey_avgs;
            double[] doctor_avgs;
            int productCount = 0;

            while (true) {
                System.out.print(operations);
                int option = scanner1.nextInt();
                scanner1.nextLine();

                if (option == 1) {
                    // Reading data from the file and constructing data structures
                    while (scanner.hasNextLine()) {
                        if (line_controller == 1) {
                            String line = scanner.nextLine();
                            String[] parts = line.split(",");
                            productCount = Integer.parseInt(parts[0]);
                            line_controller++;
                            customer_arr = new int[200][productCount + 1];
                            turkey_totals = new int[productCount];
                            notTurkey_totals = new int[productCount];
                            doctor_totals = new int[productCount];
                        } else if (line_controller % 2 == 0) {
                            String line = scanner.nextLine();
                            String[] parts = line.split(",");
                            customer_number = Integer.parseInt(parts[0]);
                            String name = parts[1];
                            String surname = parts[2];
                            country = parts[3];

                            if (country.equalsIgnoreCase("Turkey")) {
                                turkey_counter++;
                            } else {
                                not_turkey_counter++;
                            }
                            String city = parts[4];
                            occupation = parts[5];
                            if (occupation.equalsIgnoreCase("Doctor")) {
                                doctor_counter++;
                            }

                            CustomerData customerData = new CustomerData(name, surname, country, city, occupation);
                            CustomerNode newNode = new CustomerNode(customer_number, customerData);

                            CustomerList.addCustomer(customerlist, newNode);

                            customer_arr[i][0] = customer_number;
                            i++;
                            line_controller++;

                        } else if (line_controller % 2 == 1 && line_controller != 1) {
                            String line = scanner.nextLine();
                            String[] parts = line.split(",");

                            for (int x = 0; x < parts.length; x++) {
                                customer_arr[i - 1][x + 1] = Integer.parseInt(parts[x]);
                            }

                            if (country.equalsIgnoreCase("Turkey")) {
                                for (int j = 0; j < parts.length; j++) {
                                    turkey_totals[j] += Integer.parseInt(parts[j]);
                                }
                            } else {
                                for (int j = 0; j < parts.length; j++) {
                                    notTurkey_totals[j] += Integer.parseInt(parts[j]);
                                }
                            }

                            if (occupation.equalsIgnoreCase("Doctor")) {
                                for (int j = 0; j < parts.length; j++) {
                                    doctor_totals[j] += Integer.parseInt(parts[j]);
                                }
                            }

                            line_controller++;
                        }
                    }

                    System.out.println("The file reading has been completed. Please choose a new task...");

                } else if (option == 2) {
                    Scanner inputScanner = new Scanner(System.in);
                    String answer = "Y";
                    while (answer.equalsIgnoreCase("Y")) {
                        System.out.println("*********************************************************************");
                        System.out.println("Enter the information to add a new customer: ");
                        System.out.print("Enter the customer number: ");
                        int customerNumber = inputScanner.nextInt();
                        inputScanner.nextLine(); // Clear the buffer

                        System.out.print("Name: ");
                        String name = inputScanner.nextLine();

                        System.out.print("Surname: ");
                        String surname = inputScanner.nextLine();

                        System.out.print("Country: ");
                        country = inputScanner.nextLine();

                        System.out.print("City: ");
                        String city = inputScanner.nextLine();

                        System.out.print("Occupation: ");
                        occupation = inputScanner.nextLine();

                        CustomerList.addCustomerFromConsole(customerlist, customerNumber, name, surname, country, city, occupation);

                        System.out.println("Please enter " + (productCount - 1) + " rating values for the customer: (Click enter after each entry)");
                        int[] userRatings = new int[productCount];
                        for (i = 0; i < productCount - 1; i++) {
                            userRatings[i] = inputScanner.nextInt();
                        }
                        inputScanner.nextLine(); // Clear the buffer

                        // Similarity calculation and prediction of the last product's rating
                        int similarity_value = 0;
                        int minimum_value = Integer.MAX_VALUE;
                        double lastIndex = 0;
                        int roundedLastIndex = 0;
                        for (i = 0; i < customer_arr.length; i++) {
                            if (customer_arr[i][0] != 0) {
                                for (int j = 0; j < productCount - 1; j++) {
                                    similarity_value += Math.abs(userRatings[j] - customer_arr[i][j + 1]);
                                }
                                if (similarity_value < minimum_value) {
                                    minimum_value = similarity_value;
                                    lastIndex = customer_arr[i][productCount];
                                    roundedLastIndex = (int) lastIndex;
                                } else if (similarity_value == minimum_value) {
                                    lastIndex = (lastIndex + customer_arr[i][productCount]) / 2.0;
                                    roundedLastIndex = (int) Math.round(lastIndex);
                                }
                                similarity_value = 0;
                            }
                        }
                        System.out.println("Similarity value: " + minimum_value);
                        System.out.println("Estimated value found: " + roundedLastIndex);

                        userRatings[productCount - 1] = roundedLastIndex; // Add the predicted rating for the last product

                        if (country.equalsIgnoreCase("Turkey")) {
                            turkey_counter++;
                            for (int j = 0; j < userRatings.length; j++) {
                                turkey_totals[j] += userRatings[j];
                            }
                        } else {
                            not_turkey_counter++;
                            for (int j = 0; j < userRatings.length; j++) {
                                notTurkey_totals[j] += userRatings[j];
                            }
                        }
                        if (occupation.equalsIgnoreCase("Doctor")) {
                            doctor_counter++;
                            for (int j = 0; j < userRatings.length; j++) {
                                doctor_totals[j] += userRatings[j];
                            }
                        }

                        for (i = 0; i < customer_arr.length; i++) {
                            if (customer_arr[i][0] == 0) {
                                customer_arr[i][0] = customerNumber;

                                for (int j = 0; j < productCount; j++) {
                                    customer_arr[i][j + 1] = userRatings[j];
                                }
                                break;
                            }
                        }

                        System.out.print("Do you have another customer to enter? (Y/N): ");
                        answer = inputScanner.nextLine();
                    }
                } else if (option == 3) {
                    System.out.println("*********************************************************************");
                    printAverageRatings(customer_arr, productCount);
                    System.out.println("*********************************************************************");
                } else if (option == 4) {
                    turkey_avgs = calculateAverages(turkey_totals, turkey_counter);
                    System.out.println("*********************************************************************");
                    printAverages("Turkey", turkey_avgs);
                    System.out.println("*********************************************************************");
                } else if (option == 5) {
                    notTurkey_avgs = calculateAverages(notTurkey_totals, not_turkey_counter);
                    System.out.println("*********************************************************************");
                    printAverages("non-Turkey", notTurkey_avgs);
                    System.out.println("*********************************************************************");
                } else if (option == 6) {
                    doctor_avgs = calculateAverages(doctor_totals, doctor_counter);
                    System.out.println("*********************************************************************");
                    printAverages("Doctor", doctor_avgs);
                    System.out.println("*********************************************************************");
                } else if(option == 7){
                    CustomerList.printLL(customerlist);
                } else if (option == 8){
                    System.out.println("*********************************************************************");
                    Ratings.printArray(customer_arr);
                    System.out.println("*********************************************************************");
                } else if (option == 9){
                    return;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("In line 30, I used a path because my computer couldn't read the 'Firma.txt' file, please use path");
        }
    }

    private static double[] calculateAverages(int[] totals, int count) {
        double[] averages = new double[totals.length];
        for (int i = 0; i < totals.length; i++) {
            averages[i] = (double) totals[i] / count;
        }
        return averages;
    }

    private static void printAverages(String label, double[] averages) {
        System.out.println("Average ratings for " + label + ":");
        for (int i = 0; i < averages.length; i++) {
            System.out.printf("Product %d: %.2f\n", i + 1, averages[i]);
        }
    }

    private static void printAverageRatings(int[][] customer_arr, int productCount) {
        double[] sum = new double[productCount];
        int count = 0;
        for (int i = 0; i < customer_arr.length; i++) {
            if (customer_arr[i][0] != 0) {
                count++;
                for (int j = 1; j <= productCount; j++) {
                    sum[j-1] += customer_arr[i][j];
                }
            }
        }
        System.out.println("Average ratings for each product:");
        for (int i = 0; i < productCount; i++) {
            System.out.printf("Product %d: %.2f\n", i + 1, sum[i] / count);
        }
    }
}
