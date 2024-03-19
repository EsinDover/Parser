package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Parser {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Dosya ismini giriniz: ");
        String dosya = scan.nextLine();

        try {
            if (checkSyntax(dosya)) {
                System.out.println("Yazım hatası yoktur sorunsuz çalışıyor.");
            } else {
                System.out.println("Yazım hatası vardır.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scan.close();
        }
    }

    private static boolean checkSyntax(String dosyaYolu) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(dosyaYolu))) {
            String satir;
            int satırnumarası = 0;

            while ((satir = br.readLine()) != null) {
                satırnumarası++;
                if (!checkGrammar(satir, satırnumarası)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkGrammar(String satir, int satirNo) {
        String[] tokens = satir.split("\\s+");

        if (tokens.length < 2) {
            System.out.println("Yazım hatası satır " + satirNo + ": " + satir);
            return false; 
        }

        String komut = tokens[0];
        String[] operandlar = tokens[1].split(",");

       
        if (!isValidCommand(komut)) {
            System.out.println("Yazım hatası satır " + satirNo + ": " + satir);
            return false;
        }

        if (operandlar.length > 2) {
            System.out.println("Yazım hatası satır " + satirNo + ": " + satir);
            return false;
        }

        if (komut.equals("D") && tokens.length == 2 && tokens[1].equals("ETIKET")) {

            return true;
        }
   
        return true;
    }

    private static boolean isValidCommand(String komut) {
       
        return komut.equals("MOV") || komut.equals("ADD") || komut.equals("SUB")
            || komut.equals("TOP") || komut.equals("CRP") || komut.equals("BOL")
            || komut.equals("CIK") || komut.equals("D") || komut.equals("DB")
            || komut.equals("DK") || komut.equals("DKE") || komut.equals("DBE")
            || komut.equals("DED") || komut.equals("DE") || komut.equals("HRK")
            || komut.equals("VE") || komut.equals("VEY") || komut.equals("DEG")
            || komut.equals("OKU") || komut.equals("YAZ");
    }
}
    

