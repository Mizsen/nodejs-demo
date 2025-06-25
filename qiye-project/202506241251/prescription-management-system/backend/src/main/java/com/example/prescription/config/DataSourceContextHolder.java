package com.example.prescription.config;

public class DataSourceContextHolder {
    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    public static void setDataSource(String dataSource) {
        CONTEXT.set(dataSource);
    }

    public static String getDataSource() {
        return CONTEXT.get() == null ? "primary" : CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}