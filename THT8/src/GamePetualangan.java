import java.util.*;

class Karakter {
    String nama;
    double darah = 100.0;
    double mana = 50.0;
    int level = 1;

    public Karakter(String nama) {
        this.nama = nama;
    }

    public void tampilStatus() {
        System.out.println("Karakter: " + nama);
        System.out.println("Darah: " + darah);
        System.out.println("Mana: " + mana);
        System.out.println("Level: " + level);
    }
}

class Pemain extends Karakter {
    List<String> tas = new ArrayList<>(Collections.nCopies(10, "-"));
    int ramuan = 3;

    public Pemain(String nama) {
        super(nama);
    }

    public void tampilStatus() {
        System.out.println("\n==== Status Pemain ====");
        System.out.println("Nama: " + nama);
        System.out.println("Darah: " + darah);
        System.out.println("Mana: " + mana);
        System.out.println("Level: " + level);
        System.out.println("Ramuan: " + ramuan);
    }

    public void gunakanRamuan() {
        if (ramuan > 0) {
            darah += 30;
            mana += 20;
            ramuan--;
            System.out.println("Ramuan digunakan. Darah dan mana sekarang bertambah!");
        } else {
            System.out.println("Ramuan milikmu sudah habis.");
        }
    }

    public void tambahItem(String item) {
        for (int i = 0; i < tas.size(); i++) {
            if (tas.get(i).equals("-")) {
                tas.set(i, item);
                System.out.println("Kamu menemukan item: " + item);
                return;
            }
        }
        System.out.println("Tasmu sudah penuh! Item tidak bisa ditambahkan.");
    }

    public void tampilkanTas() {
        System.out.println("\n== Isi Tas ==");
        for (int i = 0; i < tas.size(); i++) {
            System.out.println((i+1) + ". " + tas.get(i));
        }
    }
}

class Musuh extends Karakter {
    double kekuatanSerang;

    public Musuh(String nama, double darah, double mana, double kekuatanSerang) {
        super(nama);
        this.darah = darah;
        this.mana = mana;
        this.kekuatanSerang = kekuatanSerang;
    }

    public double getSerangan() {
        return kekuatanSerang;
    }

    public void tampilStatus() {
        System.out.println("\n==== Musuh dihadapi ====");
        System.out.println("Nama: " + nama);
        System.out.println("Darah: " + darah);
        System.out.println("Mana: " + mana);
        System.out.println("Serangan: " + kekuatanSerang);
    }
}

class Pengguna {
    static Map<String, String> dataPengguna = new HashMap<>();

    public static boolean daftar(String user, String pass) {
        if (dataPengguna.containsKey(user)) {
            return false;
        }
        dataPengguna.put(user, pass);
        return true;
    }

    public static boolean masuk(String user, String pass) {
        return dataPengguna.containsKey(user) && dataPengguna.get(user).equals(pass);
    }
}

public class GamePetualangan {
    static Scanner input = new Scanner(System.in);
    static Random rand = new Random();
    static Pemain pemain;

    public static void main(String[] args) {
        System.out.println("=== Selamat Datang di Dunia Petualangan ===");

        while (true) {
            menuLogin();
            if (pemain != null) {
            mulaiGame();
                break;
            }
        }
    }
    
    static void menuLogin() {
        System.out.println("\n1. Masuk");
        System.out.println("2. Daftar");
        System.out.println("3. Keluar");
        System.out.print("Pilihan kamu: ");
        int pilih = input.nextInt(); input.nextLine();

        System.out.print("Username: ");
        String user = input.nextLine();
        System.out.print("Password: ");
        String pass = input.nextLine();

        switch (pilih) {
            case 1:
                if (Pengguna.masuk(user, pass)) {
                    pemain = new Pemain(user);
                } else {
                    System.out.println("Login gagal. Coba lagi.");
                }
                break;
            case 2:
                if (Pengguna.daftar(user, pass)) {
                    System.out.println("Pendaftaran berhasil. Silakan login.");
                } else {
                    System.out.println("Username sudah digunakan.");
                }
                break;
            case 3:
                System.out.println("Terima kasih sudah mencoba.");
                System.exit(0);
            default:
                System.out.println("Pilihan tidak valid.");
        }
    }

    static void mulaiGame() {
        while (true) {
            pemain.tampilStatus();
            System.out.println("\nApa yang ingin kamu lakukan sekarang?");
            System.out.println("1. Maju");
            System.out.println("2. Mundur");
            System.out.println("3. Gunakan Ramuan");
            System.out.println("4. Lihat Tasmu");
            System.out.println("5. Keluar");
            System.out.print("Pilih: ");
            int aksi = input.nextInt(); input.nextLine();

            switch (aksi) {
                case 1:
                case 2:
                    peristiwaAcak();
                    break;
                case 3:
                    pemain.gunakanRamuan();
                    break;
                case 4:
                    pemain.tampilkanTas();
                    break;
                case 5:
                    System.out.println("Sampai jumpa, petualang!");
                    return;
                default:
                    System.out.println("Pilihan tidak dikenal.");
            }
        }
    }

    static void peristiwaAcak() {
        int jenis = rand.nextInt(3);

        switch (jenis) {
            case 0:
                String[] itemList = {"Perisai Kristal", "Ramuan Penyembuh", "Jubah Angin", "Panah Api"};
                pemain.tambahItem(itemList[rand.nextInt(itemList.length)]);
                break;
            case 1:
                Musuh musuh = new Musuh("Goblin Liar", 40 + rand.nextInt(30), 20, 10 + rand.nextInt(10));
                musuh.tampilStatus();
                System.out.println("1. Lawan");
                System.out.println("2. Kabur");
                System.out.print("Pilih: ");
                int pilihan = input.nextInt(); input.nextLine();
                if (pilihan == 1) {
                    bertarung(musuh);
                } else {
                    System.out.println("Kamu sudah berhasil melarikan diri.");
                }
                break;
            default:
                System.out.println("Perjalananmu tenang, tidak ada apapun yang terjadi.");
        }
    }

    static void bertarung(Musuh musuh) {
        while (musuh.darah > 0 && pemain.darah > 0) {
            double serangPemain = 15 + rand.nextInt(10);
            musuh.darah -= serangPemain;
            System.out.println("Kamu menyerang: -" + serangPemain + " darah");

            if (musuh.darah <= 0) {
                System.out.println("Musuh berhasil dikalahkan! Level kamu sekarang naik.");
                pemain.level++;
                return;
            }

            double serangMusuh = musuh.getSerangan();
            pemain.darah -= serangMusuh;
            System.out.println("Musuh menyerang: -" + serangMusuh + " darah");

            if (pemain.darah <= 0) {
                System.out.println("Kamu kalah dalam pertempuran ini. Game over.");
                System.exit(0);
            }
        }
    }
}