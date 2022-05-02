/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package praktikum.tugas_1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author ROCKY
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int inp;
        Scanner sc = new Scanner(System.in);
        Random rdm = new Random();
        ArrayList<Integer> hasil = new ArrayList<>();
        
        do {
            System.out.println("===Memory Game===");
            System.out.println("1. Play");
            System.out.println("2. History");
            System.out.println("0. Exit");
            System.out.println(">> ");
            inp = sc.nextInt();
            
            if (inp == 1) {
                int tinggi, lebar, score = 0;
                do {
                    System.out.println("Tinggi Board : ");
                    tinggi = sc.nextInt();
                    System.out.println("Lebar Board : ");
                    lebar = sc.nextInt();
                    if ((tinggi*lebar)%2 == 1) {
                        System.out.println("Ukuran Board tidak boleh ganjil!");
                    }
                } while ((tinggi*lebar)%2 == 1);
                
                int life = tinggi*lebar/2;
                char [][] papan = new char[tinggi+2][lebar+2];
                int [][] board = new int[tinggi][lebar];
                
                for (int i = 0; i < tinggi+2; i++) {
                    for (int j = 0; j < lebar+2; j++) {
                        if ((i == 0 || i == tinggi+1) && (j == 0 || j == lebar+1)) {
                            papan[i][j] = '+';
                        } else if (i == 0 || i == tinggi+1) {
                            papan[i][j] = '-';
                        } else if (j == 0 || j == lebar+1) {
                            papan[i][j] = '|';
                        } else {
                            papan[i][j] = '*';
                        }
                    }
                }
                
                int nilai = 0;
                for (int i = 0; i < tinggi; i++) {
                    for (int j = 0; j < lebar; j++) {
                        board[i][j] = (nilai++)%life+1;
                    }
                }
                for (int i = 0; i < tinggi; i++) {
                    for (int j = 0; j < lebar; j++) {
                        int x = rdm.nextInt(lebar);
                        int y = rdm.nextInt(tinggi);
                        int temp = board[i][j];
                        board[i][j] = board[y][x];
                        board[y][x] = temp;
                    }
                }
                
                boolean cheat = false;
                do {
                    int x1 = 0,y1 = 0,x2 = 0,y2 = 0;
                    
                    for (int k = 0; k < 3; k++) {
                        for (int i = 0; i <= tinggi+2; i++) {
                            for (int j = 0; j < lebar+2; j++) {
                                if (i == 0) {
                                    System.out.print((j == 0 || j == lebar+1) ? "  " : j + " ");
                                } else if (x1 != 0 && y1 != 0 && !cheat && x1 == j && y1+1 == i) {
                                    System.out.print(board[y1-1][x1-1] + " ");
                                } else if (x2 != 0 && y2 != 0 && !cheat && x2 == j && y2+1 == i) {
                                    System.out.print(board[y2-1][x2-1] + " ");
                                } else if (cheat && i >= 2 && i <= tinggi+1 && j >= 1 && j <= lebar && papan[i-1][j] != ' ') {
                                    System.out.print(board[i-2][j-1] + " ");
                                } else {
                                    System.out.print(papan[i-1][j] + " ");
                                }
                            }
                            System.out.println((i < 2 || i > tinggi+1) ? "" : i-1);
                        }
                        System.out.println("Life : " + life);
                        System.out.println("Score : " + score);
                        
                        switch (k) {
                            case 0:
                                do {
                                    System.out.println("Y : ");
                                    y1 = sc.nextInt();
                                    System.out.println("X : ");
                                    x1 = sc.nextInt();
                                    
                                    if (x1 == -1 && y1 == -1) {
                                        cheat = true;
                                        x1 = 0; y1 = 0; k--;
                                        break;
                                    }
                                    if (x1 <= 0 || y1 <= 0 || x1 > lebar || y1 > tinggi) {
                                        System.out.println("Masukkan koordinat yang berbeda!");
                                    } else if (papan[y1][x1] == ' ') {
                                        System.out.println("Masukkan koordinat yang berbeda!");
                                    } else {break;}
                                    
                                } while (true);
                                break;
                            case 1:
                                do {
                                    System.out.println("Y : ");
                                    y2 = sc.nextInt();
                                    System.out.println("X : ");
                                    x2 = sc.nextInt();
                                    
                                    if (x2 == -1 && y2 == -1) {
                                        cheat = true;
                                        x2 = 0; y2 = 0; k--;
                                        break;
                                    }
                                    if ((x2 == x1 && y2 == y1) || (x2 <= 0 || y2 <= 0 || x2 > lebar || y2 > tinggi)) {
                                        System.out.println("Masukkan koordinat yang berbeda!");
                                    } else if (papan[y2][x2] == ' ') {
                                        System.out.println("Masukkan koordinat yang berbeda!");
                                    } else {break;}
                                    
                                } while (true);
                                if (x2 != 0 && y2 != 0) {
                                    if (board[y1-1][x1-1] == board[y2-1][x2-1]) {score += 2;} else {life--;}
                                }
                                break;
                            case 2:
                                if (board[y1-1][x1-1] == board[y2-1][x2-1]) {
                                    System.out.println("Kartu berpasangan!");
                                    papan[y1][x1] = ' ';
                                    papan[y2][x2] = ' ';
                                }else{
                                    System.out.println("Kartu tidak berpasangan!");
                                }
                                //x1 = 0; x2 = 0; y1 = 0; y2 = 0;
                                System.out.println("Tekan enter untuk lanjut!");
                                String tmp = sc.nextLine();
                                tmp = sc.nextLine();
                        }
                    }
                } while (life != 0 && score != tinggi*lebar);
                hasil.add(score);
            }else if (inp == 2) {
                System.out.println("===Histori===");
                for (int i = 0; i < hasil.size(); i++) {
                    System.out.println(i+1 + ". " + hasil.get(i));
                }
                String tmp = sc.nextLine();
                tmp = sc.nextLine();
            }
        } while (inp != 0);
    }
    
}
