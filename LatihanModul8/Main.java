package LatihanModul8;

import java.util.Date;

class Animal {
    String name;
    HealthRecord healthRecord;

    public Animal(String name, Date checkupDate) {
        this.name = name;
        this.healthRecord = new HealthRecord(checkupDate);
    }

    public void makeSound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    public Dog(Date checkupDate) {
        super("", checkupDate); 
    }

    public void makeSound() {
        System.out.println("Guk-Guk!");
    }
}

class Cat extends Animal {
    public Cat(Date checkupDate) {
        super("", checkupDate); 
    }

    public void makeSound() {
        System.out.println("Meong!");
    }
}

class HealthRecord {
    private Date checkupDate;

    public HealthRecord(Date checkupDate) {
        this.checkupDate = checkupDate;
    }

    public Date getCheckupDate() {
        return checkupDate;
    }
}

class Owner {
    private String name;
    private Animal pet;

    public Owner(String name, Animal pet) {
        this.name = name;
        this.pet = pet;
    }

    public void showInfo() {
        System.out.println("Nama Pemilik: " + name);
        System.out.print("Suara Hewan: ");
        pet.makeSound();
        System.out.println("Tanggal Rekam Medis: " + pet.healthRecord.getCheckupDate());

        if (pet instanceof Dog) {
            System.out.println("Jenis Hewan: Dog");
        } else if (pet instanceof Cat) {
            System.out.println("Jenis Hewan: Cat");
        } else {
            System.out.println("Jenis Hewan: Unknown");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Owner owner1 = new Owner("Maria", new Dog(new Date()));
        Owner owner2 = new Owner("Lisa", new Cat(new Date()));

        owner1.showInfo();
        System.out.println();
        owner2.showInfo();
    }
}