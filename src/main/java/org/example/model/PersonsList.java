package org.example.model;
import org.example.model.Person;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PersonsList {
    private final String FILE_PATH_ROUTE = "src/main/java/resources/";
    private ArrayList<Person> persons;
    // Constructor initializes the persons list
    public PersonsList() {
        this.persons = new ArrayList<>();
    }
    // Method to add a person to the list
    public void addPerson(Person person) {
        this.persons.add(person);
    }
    // Method to remove a person from the list
    public void removePerson(Person person) {
        this.persons.remove(person);
    }
    // Method to get List of persons
    public ArrayList<Person> getPersons() {
        return persons;
    }
    // Method to get a person by name
    public Person findPersonByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (name.split(" ").length != 2) {
            throw new IllegalArgumentException("Name must contain only one space");
        }
        for (Person person : persons) {
            if (person.getName().equalsIgnoreCase(name)) {
                return person;
            }
        }
        return null;
    }

    // Method to save a person to a file
    public void savePersonToFile(Person person, String filePath) {
        if(person == null) throw new IllegalArgumentException("Person cannot be null");
        filePath = (filePath != null)
                        ? FILE_PATH_ROUTE + filePath.trim()
                        : FILE_PATH_ROUTE + "persons.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(person.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "PersonsList{" +
                "persons=" + persons +
                '}';
    }
}