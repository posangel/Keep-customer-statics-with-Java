package project;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.ListIterator;


public class CustomerList {

    
    public static void addCustomer(LinkedList<CustomerNode> customerlist, CustomerNode newNode) {
        ListIterator<CustomerNode> iterator = customerlist.listIterator();

        while (iterator.hasNext()) {
            CustomerNode current = iterator.next();
            if (current.customer_number == newNode.customer_number) {
                // Ayn� m��teri numaras�na sahip bir d���m zaten varsa, g�ncelle ve i�lemi sonland�r
                current.customerdata = newNode.customerdata;
                return;
            } else if (current.customer_number > newNode.customer_number) {
                // Yeni m��teri numaras�, mevcut m��teri numaras�ndan k���kse
                // Yeni d���m� bu konuma ekle ve i�lemi sonland�r
                iterator.previous();
                iterator.add(newNode);
                return;
            }
        }

        // Listenin sonuna yeni d���m� ekle
        iterator.add(newNode);
    }



   public static void addCustomerFromConsole(LinkedList<CustomerNode> customerlist, int customerNumber, String name, String surname, String country, String city, String occupation) {
        Scanner scanner = new Scanner(System.in);

 
        CustomerData customerData = new CustomerData(name, surname, country, city, occupation);
        CustomerNode newNode = new CustomerNode(customerNumber, customerData);

        ListIterator<CustomerNode> iterator = customerlist.listIterator();

        while (iterator.hasNext()) {
            CustomerNode current = iterator.next();
            if (current.customer_number == newNode.customer_number) {
                // Ayn� m��teri numaras�na sahip bir d���m zaten varsa, g�ncelle ve i�lemi sonland�r
                current.customerdata = newNode.customerdata;
                return;
            } else if (current.customer_number > newNode.customer_number) {
                // Yeni m��teri numaras�, mevcut m��teri numaras�ndan k���kse
                // Yeni d���m� bu konuma ekleyin ve i�lemi sonland�r�n
                iterator.previous();
                iterator.add(newNode);
                return;
            }
            
        }
         iterator.add(newNode);
    }


    public static void printLL(LinkedList<CustomerNode> customerlist) {
        ListIterator<CustomerNode> iterator = customerlist.listIterator();
        System.out.println("Customer List: ");
        while (iterator.hasNext()) {
            CustomerNode current = iterator.next();
            System.out.println("---------------------------");
            System.out.println("Customer number : " + current.customer_number);
            System.out.println(current.customerdata.toString());
            System.out.println("---------------------------");
        }
    }

}