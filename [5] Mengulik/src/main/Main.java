package src.main;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import src.player.Player;

class Helper{
    String[] ent = { "MANUSIA", "GAJAH", "SEMUT" };

    int menus(Scanner inp){
        System.out.println("=============================== MENU ===============================");
        System.out.println("0. Keluar");
        System.out.println("1. Daftar");
        System.out.println("2. Bermain");
        System.out.println("3. Score Permainan");
        System.out.println("4. List Pemain");
        System.out.println("====================================================================");
        System.out.print("Pilih Menu: "); return inp.nextInt();
    }

    Player daftar(Scanner inp){ String name, team; int semester;
        System.out.println("====================================================================");
        System.out.println("Silahkan Daftar terlebih dahulu");
        System.out.println("====================================================================");
        System.out.print("Masukkan Nama Anda      : "); name = inp.nextLine();
        System.out.print("Masukkan Nama Team Anda : "); team = inp.nextLine();
        System.out.print("Anda Semester berapa    : "); semester = inp.nextInt(); inp.nextLine();
        return new Player(name, team, semester);
    }

    void showList(ArrayList<Player> pl, int key){
        if(pl.size() == 0) System.out.println("Belum ada pemain saat ini...");
        else{
            if(key == 0) System.out.println("==================== DAFTAR PEMAIN ====================");
            else System.out.println("======== PAPAN SKOR SEMUA PEMAIN ========");
                for(int i = 0; i < pl.size(); i++)
                    if(key == 0)
                        System.out.printf("Pemain %d: Nama: %s || Tim: %s || Semester: %d\n", 
                        i + 1, pl.get(i).getName(), pl.get(i).getTeam(), pl.get(i).getSmt());
                    else
                        System.out.printf("Data Player Index ke-%d: %s || Menang: %d || Kalah: %d || Draw: %d\n",
                        i + 1, pl.get(i).getName(), pl.get(i).getWin(), pl.get(i).getLose(), pl.get(i).getDraw());
                System.out.println("===================================================");
        }
    }

    int suitExec(int val, Player p){ int bot = ThreadLocalRandom.current().nextInt(0, 3);
        System.out.println("==================================================");
        System.out.println("Anda Memilih " + ent[--val]);
        System.out.println("Bot Memilih " + ent[bot]);
        System.out.println("==================================================");

        System.out.println("\n==================================================");
        System.out.print("Yah ! ");
        if(val == 0 && bot == 1 || val == 1 && bot == 2 || val == 2 && bot == 0)
            System.out.printf("Maaf banget %s, Anda dikalahkan oleh Bot...", p.getName(), val = 0);
        else if(val == bot)
            System.out.printf("%s, Hasilnya seri Duhh!", p.getName(), val = 2);
        else
            System.out.printf("%s, Anda menang! Mantap!", p.getName(), val = 1);
        System.out.println("\n==================================================\n");
        return val;
    }

    void suitMain(Player p, Scanner inp){
        System.out.println("====================================================");
        System.out.printf("==== HOLLA %s!!\n== LET'S PLAY !\n", p.getName());
        do{
            System.out.println("===========================================");
            System.out.println("Pilih salah satu dari 3 entitas ini:");
            for(int i = 0; i < ent.length; i++) System.out.printf("%d. %s,\n", i + 1, ent[i]);
            System.out.print("Masukkan pilihan Anda: ");
            switch(suitExec(inp.nextInt(), p)){
                case 0: p.addLose(); break;
                case 1: p.addWin(); break;
                case 2: p.addDraw(); break;
            }
            System.out.println("====================================================");
            System.out.printf("== Papan Skor %s ==\n", p.getName());
            System.out.printf("Menang : %d Kali\n", p.getWin());
            System.out.printf("Kalah  : %d Kali\n", p.getLose());
            System.out.printf("Draw   : %d Kali\n", p.getDraw());
            System.out.println("====================================================");
            System.out.print("\nApakah Anda ingin bermain lagi ? (y/n): "); System.out.println("\n");
        }while(inp.next().charAt(0) == 'y');
    }
}

public class Main {
    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<Player>();
        Scanner inp = new Scanner(System.in);
        Helper util = new Helper();
        int o;

        while(true){
            o = util.menus(inp); inp.nextLine(); System.out.println("");
            if(o == 0) System.exit(0);

            switch(o){
                case 1: 
                    while(true){
                        players.add(util.daftar(inp));
                        System.out.print("Apakah Anda ingin menambahkan pemain lain? (y/n): ");
                        if(inp.next().charAt(0) != 'y') break;
                        inp.nextLine();
                    }
                    System.out.println("====================================================================");
                    System.out.println("Selamat! Anda berhasil mendaftarkan pemain...");
                    System.out.println("====================================================================");
                break;
                case 2:
                    System.out.println("===============================================");
                    System.out.println("---------- SELAMAT DATANG DI SEMAG ! ----------");
                    System.out.println("===============================================");
                    System.out.printf("Input index player yang ingin dimainkan(0 - %d): ", players.size());
                    util.suitMain(players.get(inp.nextInt()), inp); inp.nextLine();
                break;
                case 3: util.showList(players, 1); break;
                case 4: util.showList(players, 0); break;
            }System.out.println("");
        }
    }
}
