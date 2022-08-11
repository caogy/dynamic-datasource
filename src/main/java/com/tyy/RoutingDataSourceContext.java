package com.tyy;

public class RoutingDataSourceContext implements AutoCloseable {

    private static final String MASTER_DATASOURCE = "master";

    private static final ThreadLocal<String> threadLocalDataSourceKey = new ThreadLocal<>();

    public static String getDataSourceRoutingKey() {
        String key = threadLocalDataSourceKey.get();
        return key == null ? MASTER_DATASOURCE : key;
    }

    public static void setDataSourceRoutingKey(String key) {
        threadLocalDataSourceKey.set(key);
    }

    public RoutingDataSourceContext(String key) {
        threadLocalDataSourceKey.set(key);
    }

    @Override
    public void close() throws Exception {
        threadLocalDataSourceKey.remove();
    }
}