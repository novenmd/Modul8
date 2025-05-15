class Engine {
    void start() {
        System.out.println("Mesin nyala");
    }
}

class Car {
    Engine engine = new Engine();
    void drive() {
        engine.start();
        System.out.println("Mobil Jalan");
    }
}

public class Main2 {
    public static void main(String[] args) {
        Car car = new Car();
        car.drive();
    }
}
