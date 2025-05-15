package PostTestModul8;
import java.util.Date;

class Vehicle {
    public String startEngine() {
        return "Mesin Nyala";
    }
}

class Car extends Vehicle {
    public String startEngine() {
        return "Mesin Mobil Nyala";
    }
}

class Motorcycle extends Vehicle {
    public String startEngine() {
        return "Mesin Motor Nyala";
    }
}

class License {
    private String licenseNumber;
    private Date validUntil;

    public License(String licenseNumber, Date validUntil) {
        this.licenseNumber = licenseNumber;
        this.validUntil = validUntil;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public Date getValidUntil() {
        return validUntil;
    }
}

class Driver {
    private String name;
    private Vehicle vehicle;
    private License license;

    public Driver(String name, Vehicle vehicle, String licenseNumber, Date validUntil) {
        this.name = name;
        this.vehicle = vehicle;
        this.license = new License(licenseNumber, validUntil);
    }

    public void showInfo() {
        System.out.println("Nama Pengemudi: " + name);

        if (vehicle instanceof Car) {
            System.out.println("Jenis Kendaraan: Mobil");
        } else if (vehicle instanceof Motorcycle) {
            System.out.println("Jenis Kendaraan: Motor");
        } else {
            System.out.println("Jenis Kendaraan: Tidak diketahui");
        }

        System.out.println("Status Mesin: " + vehicle.startEngine());
        System.out.println("Nomor Lisensi: " + license.getLicenseNumber());
        System.out.println("Tanggal Berlaku Lisensi: " + license.getValidUntil());
    }
}

public class Main {
    public static void main(String[] args) {
        Driver driver1 = new Driver(
            "Dino",
            new Car(),
            "LIC12345",
            new Date()
        );

        Driver driver2 = new Driver(
            "Fista",
            new Motorcycle(),
            "LIC67890",
            new Date()
        );

        driver1.showInfo();
        System.out.println();
        driver2.showInfo();
    }
}

