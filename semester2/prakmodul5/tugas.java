package com.semester2.prakmodul5;
import java.util.Scanner;

public class tugas{
    public final static int d = 256;
    Scanner input = new Scanner(System.in);
    int NO_OF_CHARS = 256;

    void Naive(String txt, String pat){
        int teks = pat.length();
        int cari = txt.length();
        for (int i = 0; i <= cari - teks; i++){
            int j;
            for (j = 0; j < teks; j++) {
                if (txt.charAt(i + j) != pat.charAt(j)){
                    break;
                }
            }
            if (j == teks){
                System.out.println("Teks ditemukan pada indeks ke - " + i);
            }
        }
    }

    void RabinKarp(String pat, String txt, int q){
        int h = 1, i, j, M = pat.length(), N = txt.length(), p = 0, t = 0;
        for (i = 0; i < M - 1; i++){
            h = (h * d) % q;
        }
        for (i = 0; i < M; i++){
            p = (d * p + pat.charAt(i)) % q;
            t = (d * t + txt.charAt(i)) % q;
        }
        for (i = 0; i <= N - M; i++){
            if (p == t){
                for (j = 0; j < M; j++){
                    if (txt.charAt(i + j) != pat.charAt(j)){
                        break;
                    }
                }
                if (j == M){
                    System.out.println("Teks ditemukan pada indeks ke - " + i);
                }
            }
            if (i < N - M){
                t = (d * (t - txt.charAt(i) * h) + txt.charAt(i + M)) % q;
                if (t < 0){
                    t = (t + q);
                }
            }
        }
    }

    int getNextState(char[] pat, int M, int state, int x){
        if (state < M && x == pat[state]){
            return state + 1;
        }
        int ns, i;
        for (ns = state; ns > 0; ns--){
            if (pat[ns - 1] == x){
                for (i = 0; i < ns - 1; i++){
                    if (pat[i] != pat[state - ns + 1 + i]){
                        break;
                    }
                }
                if (i == ns - 1){
                    return ns;
                }
            }
        }
        return 0;
    }

    void computeTF(char[] patF, int M, int TF[][]){
        int state, x;
        for (state = 0; state <= M; ++state){
            for (x = 0; x < NO_OF_CHARS; ++x){
                TF[state][x] = getNextState(patF, M, state, x);
            }
        }
    }

    void FiniteState(char[] patF, char txtF[]) {
        int M = patF.length;
        int N = txtF.length;
        int[][] TF = new int[M + 1][NO_OF_CHARS];
        computeTF(patF, M, TF);
        int i, state = 0;
        for (i = 0; i < N; i++){
            state = TF[state][txtF[i]];
            if (state == M){
                System.out.println("Teks ditemukan pada indeks ke - " + (i - M + 1));
            }
        }
    }

    void KMP(String pat, String txt){
        int i = 0, j = 0, M = pat.length(), N = txt.length(), lps[] = new int[M];
        computeLPSArray(pat, M, lps);
        while (i < N){
            if (pat.charAt(j) == txt.charAt(i)){
                j++;
                i++;
            }
            if (j == M){
                System.out.println("Teks ditemukan pada indeks ke - " + (i - j));
                j = lps[j - 1];
            }else if (i < N && pat.charAt(j) != txt.charAt(i)){
                if (j != 0){
                    j = lps[j - 1];
                }else{
                    i = i + 1;
                }
            }
        }
    }

    void computeLPSArray(String pat, int M, int lps[]){
        int i = 1, len = 0;
        lps[0] = 0;
        while (i < M){
            if (pat.charAt(i) == pat.charAt(len)){
                len++;
                lps[i] = len;
                i++;
            }else{
                if (len != 0){
                    len = lps[len - 1];
                }else{
                    lps[i] = len;
                    i++;
                }
            }
        }
    }

    void exit(){
        System.out.println("Program Selesai!\nTerima Kasih.");
        System.exit(0);
    }

    public void menu(){
        Scanner input = new Scanner(System.in);
        int pilih = 0, q = 256;
        System.out.print("Masukkan Teks                   : ");
        String txt = input.nextLine();
        System.out.print("Masukkan Teks yang Ingin Dicari : ");
        String pat = input.nextLine();
        char[] patF = pat.toCharArray();
        char[] txtF = txt.toCharArray();
        System.out.println("Pilih Algoritma Pencarian :");
        System.out.println("1. Naive");
        System.out.println("2. Rabin Karp");
        System.out.println("3. Finite State Automata");
        System.out.println("4. Knuth Morris Pratt");
        System.out.println("5. Exit");
        System.out.print("Metode yang dipilih : ");
        pilih = input.nextInt();
        switch(pilih){
            case 1:
                System.out.println("====================================");
                Naive(txt, pat);
                System.out.println("====================================");
                break;
            case 2:
                System.out.println("====================================");
                RabinKarp(pat, txt, q);
                System.out.println("====================================");
                break;
            case 3:
                System.out.println("====================================");
                FiniteState(patF, txtF);
                System.out.println("====================================");
                break;
            case 4:
                System.out.println("====================================");
                KMP(pat, txt);
                System.out.println("====================================");
                break;
            case 5:
                exit();
                break;
            default:
                menu();
        }
    }

    public static void main(String[] args){
        tugas object = new tugas();
        object.menu();
    }  
}