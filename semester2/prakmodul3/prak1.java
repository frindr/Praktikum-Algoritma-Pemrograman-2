package com.semester2.prakmodul3;

public class prak1 {
    public static void main(String[] args) {
        int[] data = {99,20,17,8,27,5,21,10,41,11};
        int cari = 41;
        int i;
        boolean ditemukan = false;
        for (i = 0; i < data.length; i++){
            if (data[i] == cari){
                ditemukan = true;
                break;
            }
        }
        if (ditemukan) {
            System.out.println("Data : " + cari + " Ditemukan Pada Index : " + i + ".");
        } else {
            System.out.println("Data tidak di temukan.");
        }
    }
}