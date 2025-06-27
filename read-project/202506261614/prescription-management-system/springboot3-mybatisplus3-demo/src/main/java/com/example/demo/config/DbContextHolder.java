package com.example.demo.config;

public class DbContextHolder {
    private static final ThreadLocal<DbType> contextHolder = new ThreadLocal<>();

    public static void setDbType(DbType dbType) {
        contextHolder.set(dbType);
    }

    public static DbType getDbType() {
        return contextHolder.get() == null ? DbType.PRIMARY : contextHolder.get();
    }

    public static void clear() {
        contextHolder.remove();
    }

    public enum DbType {
        PRIMARY, SLAVE
    }
}