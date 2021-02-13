package com.example.dp.facade;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {
    public static void main(String[] args) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        //generate sales pdf report and logistics csv report using facade
        Facade.generateReport(Facade.Types.SALE, Facade.ReportTypes.PDF, date);
        Facade.generateReport(Facade.Types.LOGISTIC, Facade.ReportTypes.PDF, date);
    }
}
