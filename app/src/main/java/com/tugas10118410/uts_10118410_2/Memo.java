//5 Juni 2021, 10118410, Ridwan Caesarahman Julian, IF-10
package com.tugas10118410.uts_10118410_2;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Memo implements Serializable {
    private Date date;
    private String kategori;
    private String judul;
    private String isi;

    private boolean fullDisplayed;
    private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy 'at' hh:mm aaa");

    public Memo(){
        this.date = new Date();
    }
    public Memo(long time, String kategori, String judul, String isi){
        this.date = new Date(time);
        this.kategori = kategori;
        this.judul = judul;
        this.isi = isi;

    }
    public String getDate(){
        return dateFormat.format(date);
    }
    public long getTime(){
        return date.getTime();
    }

    public void setKategori(String kategori){
        this.kategori = kategori ;
    }
    public void setJudul(String judul){
        this.judul = judul ;
    }
    public void setIsi(String isi){
        this.isi = isi ;
    }

    public String getKategori(){
        return this.kategori;
    }
    public String getJudul(){
        return this.judul;
    }
    public String getIsi(){
        return this.isi;
    }

    public String getShortKategori() {
        String temp = kategori.replaceAll("/n", " ");
        if (temp.length() > 25) {
            return temp.substring(0, 25) + "...";
        } else {
            return temp;
        }
    }


        public String getShortJudul () {
            String temp = judul.replaceAll("/n", " ");
            if (temp.length() > 25) {
                return temp.substring(0, 25) + "...";
            } else {
                return temp;
            }
        }

        public String getShortIsi () {
            String temp = isi.replaceAll("/n", " ");
            if (temp.length() > 25) {
                return temp.substring(0, 25) + "...";
            } else {
                return temp;
            }
        }



    public void setFullDisplayed(boolean fullDisplayed){
        this.fullDisplayed = fullDisplayed;
    }

    public boolean isFullDisplayed() {
        return this.fullDisplayed;
    }

    @NonNull
    @Override
    public String toString() {
        return this.kategori;
    }
}