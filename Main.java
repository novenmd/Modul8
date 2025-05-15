class Animal {

}

class Mamalia extends Animal {

}

class Reptil extends Animal {

}

class Dog extends Mamalia {

}

public class Main {
    public static void main(String[] args) {
        Animal animal1 = new Animal();
        Mamalia mammal1 = new Mamalia();
        Dog dog1 = new Dog();

        System.out.println(mammal1 instanceof Animal);
        System.out.println(dog1 instanceof Animal);
        System.out.println(dog1 instanceof Mamalia);
        // System.out.println(dog1 instanceof Reptile);

        Animal animal2 = new Mamalia;

        //Dog dog2 = (Dog) animal2;

        if (animal2 instanceof Dog) {
            Dog dog2 = (Dog) animal2;
            System.out.println("objek berhasil dibuat");
        } else {
            System.out.println("gagal membuat objek");
        }
    }
}


