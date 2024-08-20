package com.example.rebrain.util;

public class CronValidator {
    private static final String CRON_REGEX = "^(\\*|([0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9])|\\*/([0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]))" +
            " (\\*|([0-9]|1[0-9]|2[0-3])|\\*/([0-9]|1[0-9]|2[0-3]))" +
            " (\\*|([1-9]|1[0-9]|2[0-9]|3[0-1])|\\*/([1-9]|1[0-9]|2[0-9]|3[0-1]))" +
            " (\\*|([1-9]|1[0-2])|\\*/([1-9]|1[0-2]))" +
            " (\\*|([0-6])|\\*/([0-6]))$";
    public static boolean isValidCron(String cron) {
        return cron != null && cron.matches(CRON_REGEX);
    }
}
