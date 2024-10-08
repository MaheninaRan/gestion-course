package com.example.testeeval.Util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Util {


    public static String formatDate(String input) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = sdf.parse(input);

        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = localDate.format(formatter);

        System.out.println("Date formatée : " + formattedDate);
        return formattedDate;
    }
    public static String formatDateTime(String input) throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        java.util.Date date = inputFormat.parse(input);

        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return localDateTime.format(outputFormatter);
    }

    public static Date parsoToDate(String str_date) throws Exception {
        if (str_date == null || str_date.equals("")) {
            throw new Exception("Input date invalid");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date util_date = sdf.parse(str_date);
        return new Date(util_date.getTime());
    }

    public static Date addDays(Date date, int jours) {
        long millis = date.getTime();
        long joursInMillis = (long) jours * 24 * 60 * 60 * 1000;
        return new Date(millis + joursInMillis);
    }

    public static boolean mineur(LocalDate dtn) {
        LocalDate date = LocalDate.now();
        Period period =Period.between(dtn,date);
       return period.getYears()<18;
    }

    public static boolean majeur(LocalDate dtn) {
        LocalDate date = LocalDate.now();
        Period period =Period.between(dtn,date);
        return period.getYears()>18;
    }
    public static String formatTemps(String chrono) {
        int tempsEnSecondes = Integer.parseInt(chrono);
        int heures = tempsEnSecondes / 3600;
        int minutes = (tempsEnSecondes % 3600) / 60;
        int secondes = tempsEnSecondes % 60;
        return String.format("%02d:%02d:%02d", heures, minutes, secondes);
    }

    public static LocalDate mamadika(Date dtn) {
       return dtn.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static int calculerAge(Date dateDeNaissance) {

        Calendar aujourdhui = Calendar.getInstance();


        Calendar dateNaissance = Calendar.getInstance();
        dateNaissance.setTime(dateDeNaissance);


        int age = aujourdhui.get(Calendar.YEAR) - dateNaissance.get(Calendar.YEAR);


        if (aujourdhui.get(Calendar.DAY_OF_YEAR) < dateNaissance.get(Calendar.DAY_OF_YEAR)) {
        age--;
    }

        return age;
}

}
