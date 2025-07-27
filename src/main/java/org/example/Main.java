package org.example;

import java.util.Scanner;
import org.example.model.Person;
import org.example.model.PersonsList;

public class Main {

    public static void main(String[] args) {
        PersonsList personsList = new PersonsList();
        runMenu(personsList);
    }

    public static void showMenu() {
        System.out.println("Menu Options:");
        System.out.println("1. Add a new person");
        System.out.println("2. Remove a person");
        System.out.println("3. Find a person by name");
        System.out.println("4. List all persons");
        System.out.println("5. Save a person to file");
        System.out.println("6. Exit");
    }

    public static void runMenu(PersonsList personsList) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            showMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            switch (choice) {
                case 1:
                    addPerson(personsList, scanner);
                    break;
                case 2:
                    removePerson(personsList, scanner);
                    break;
                case 3:
                    findPersonByName(personsList, scanner);
                    break;
                case 4:
                    listAllPersons(personsList);
                    break;
                case 5:
                    savePersonToFile(personsList, scanner);
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        showMenu();
    }

    public static void addPerson(PersonsList personsList, Scanner scanner) {
        try{
            System.out.println("Adding a new person:");
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            while (name.split(" ").length != 2) {
                System.out.println("Name must contain exactly one space. Please try again.");
                System.out.print("Enter name: ");
                name = scanner.nextLine();
            }
            System.out.print("Enter age: ");
            int age = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            System.out.print("Enter occupation: ");
            String occupation = scanner.nextLine();
            Person newPerson = new Person(name, age, occupation);
            personsList.addPerson(newPerson);
            System.out.println("Person added: " + newPerson);
        } catch (Exception e) {
            System.out.println("Error adding person: " + e.getMessage());
        }

    }
    public static void removePerson(PersonsList personsList, Scanner scanner) {
        try {
            System.out.print("Enter name of person to remove: ");
            String name = scanner.nextLine();
            Person personToRemove = personsList.findPersonByName(name);
            if (personToRemove != null) {
                personsList.removePerson(personToRemove);
                System.out.println("Person removed: " + personToRemove);
            } else {
                System.out.println("Person not found.");
            }
        } catch (Exception e) {
            System.out.println("Error removing person: " + e.getMessage());
        }
    }
    public static void findPersonByName(PersonsList personsList, Scanner scanner) {
        try {
            System.out.print("Enter name to search: ");
            String name = scanner.nextLine();
            Person foundPerson = personsList.findPersonByName(name);
            if (foundPerson != null) {
                System.out.println("Found person: " + foundPerson);
            } else {
                System.out.println("Person not found.");
            }
        } catch (Exception e) {
            System.out.println("Error finding person: " + e.getMessage());
        }

    }
    public static void listAllPersons(PersonsList personsList) {
        try{
            if (personsList.getPersons().isEmpty()) {
                System.out.println("No persons in the list.");
            } else {
                System.out.println("List of persons:");
                for (Person person : personsList.getPersons()) {
                    System.out.println(person);
                }
            }
        } catch (Exception e) {
            System.out.println("Error listing persons: " + e.getMessage());
        }
    }
    public static void savePersonToFile(PersonsList personsList, Scanner scanner) {
        try {
            System.out.print("Enter file path to save person: ");
            String filePath = scanner.nextLine();
            if (!personsList.getPersons().isEmpty()) {
                personsList.savePersonToFile(personsList.getPersons().get(0), filePath);
                System.out.println("Person saved to file.");
            } else {
                System.out.println("No persons to save.");
            }
        } catch (Exception e) {
            System.out.println("Error saving person to file: " + e.getMessage());
        }
    }

}