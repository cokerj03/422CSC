/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.petdatabase;
/**
 *
 * @author jamiecoker
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class Pet {
    private String name;
    private int age;

    public Pet(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %4d", name, age);
    }
}

public class PetDatabase {
    private static List<Pet> pets = new ArrayList<>(); // Moved to static list
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Pet Database Program:");
            System.out.println("1) View all pets");
            System.out.println("2) Add a pet");
            System.out.println("3) Update a pet");
            System.out.println("4) Remove a pet");
            System.out.println("5) Search pets by name");
            System.out.println("6) Search pets by age");
            System.out.println("7) Exit");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    viewPets();
                    break;
                case 2:
                    addPet();
                    break;
                case 3:
                    updatePet();
                    break;
                case 4:
                    removePet();
                    break;
                case 5:
                    searchByName();
                    break;
                case 6:
                    searchByAge();
                    break;
                case 7:
                    exitProgram();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void viewPets() {
        System.out.println("+----------------------+");
        System.out.println("| ID | NAME      | AGE |");
        System.out.println("+----------------------+");
        for (int i = 0; i < pets.size(); i++) {
            System.out.printf("| %2d | %-10s | %4d |\n", i, pets.get(i).getName(), pets.get(i).getAge());
        }
        System.out.println("+----------------------+");
        System.out.println(pets.size() + " rows in set.");
    }

    private static void addPet() {
        System.out.print("Enter pet name: ");
        String name = scanner.nextLine();
        System.out.print("Enter pet age: ");
        int age = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        pets.add(new Pet(name, age));
        System.out.println("Pet added.");
    }

    private static void updatePet() {
        viewPets();
        System.out.print("Enter pet ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        if (id < 0 || id >= pets.size()) {
            System.out.println("Invalid ID.");
            return;
        }
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new age: ");
        int newAge = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        pets.get(id).setName(newName);
        pets.get(id).setAge(newAge);
        System.out.println("Pet updated.");
    }

    private static void removePet() {
        viewPets();
        System.out.print("Enter pet ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        if (id < 0 || id >= pets.size()) {
            System.out.println("Invalid ID.");
            return;
        }
        pets.remove(id);
        System.out.println("Pet removed.");
    }

    private static void searchByName() {
        System.out.print("Enter the name to search for: ");
        String name = scanner.nextLine().toLowerCase();
        System.out.println("+----------------------+");
        System.out.println("| ID | NAME      | AGE |");
        System.out.println("+----------------------+");
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getName().toLowerCase().equals(name)) {
                System.out.printf("| %2d | %-10s | %4d |\n", i, pets.get(i).getName(), pets.get(i).getAge());
            }
        }
        System.out.println("+----------------------+");
    }

    private static void searchByAge() {
        System.out.print("Enter the age to search for: ");
        int age = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.println("+----------------------+");
        System.out.println("| ID | NAME      | AGE |");
        System.out.println("+----------------------+");
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getAge() == age) {
                System.out.printf("| %2d | %-10s | %4d |\n", i, pets.get(i).getName(), pets.get(i).getAge());
            }
        }
        System.out.println("+----------------------+");
    }

    private static void exitProgram() {
        savePetsToFile();
        System.out.println("Goodbye! Pet data saved successfully.");
    }

    private static void savePetsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("pets.txt"))) {
            for (Pet pet : pets) {
                writer.write(pet.getName() + " " + pet.getAge());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving pet data.");
            e.printStackTrace();
        }
    }
}
