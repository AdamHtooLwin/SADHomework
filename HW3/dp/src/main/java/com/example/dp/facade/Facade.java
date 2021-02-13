package com.example.dp.facade;

import java.sql.Connection;
import java.util.Date;

public class Facade {
    public static enum Types {
        SALE, LOGISTIC;
    }

    public static enum ReportTypes {
        PDF, CSV;
    }

    public static void generateReport(Types type, ReportTypes reportType, Date date){
        Connection conn = null;
        switch (type) {
            case SALE:
                conn = SalesReportHelper.getSaleDBConnection();
                SalesReportHelper saleHelper = new SalesReportHelper();
                switch(reportType) {
                    case CSV:
                        saleHelper.generateCSVReport(date);
                        break;
                    case PDF:
                        saleHelper.generatePDFReport(date);
                }

                break;
            case LOGISTIC:
                conn = LogisticsReportHelper.getLogisticsDBConnection();
                LogisticsReportHelper logisticHelper = new LogisticsReportHelper();
                switch(reportType){
                    case CSV:
                        logisticHelper.generateCSVReport(date);
                        break;
                    case PDF:
                        logisticHelper.generatePDFReport(date);
                        break;
                }

                break;
        }
    }
}
