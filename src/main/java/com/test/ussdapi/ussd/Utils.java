package com.test.ussdapi.ussd;

public class Utils {
    public static final Double KWS_CONVERSION = 6.10;
    public static final Double MWK_CONVERSION = 42.50;

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isValidThirdStageEntry(String str) {
        return str.equals("1");
    }

    public static boolean isValidSecondStageEntry(String str) {
        return !str.isBlank() && (str.equals("1") || str.equals("2"));
    }

    public static String getMessageByStage(int stage) {
        switch (stage) {
            case 1:
                return "Welcome to Mama Money! Where would you like to send money to?\n1) Kenya\n2) Malawi";
            case 2:
                return "How much money (in Rands) would you like to send to?\n<CountryName>?";
            case 3:
                return "The person you are sending to will receive: <Amount> <ForeignCurrencyCode> \n 1)Ok";
            case 4:
                return "Thank you for using Mama Money!";
            default:
                return "";
        }
    }

    public static String getCountryNameByEntry(String entry) {
        if (entry.equals("1")) {
            return "Kenya";
        } else if (entry.equals("2")) {
            return "Malawi";
        }
        return "";
    }

    public static String getCurrencyCodeFromEntry(String entry) {
        if (entry.equals("1")) {
            return "KWS";
        } else if (entry.equals("2")) {
            return "MWK";
        }
        return "";
    }

    public static String getForexAmount(Double amount, String countryCode) {
        Double forexAmount = 0.0;
        if (countryCode.equals("1")) {
            forexAmount = amount * Utils.KWS_CONVERSION;
        } else if (countryCode.equals("2")) {
            forexAmount = amount * Utils.MWK_CONVERSION;
        }
        return forexAmount.toString();
    }
}
