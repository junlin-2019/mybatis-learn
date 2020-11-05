package com.example.config;

/**
 * 使用ThreadLocal技术来记录当前线程中的数据源的key
 *
 * @author allen
 */
public final class DynamicDataSourceHolder {

    // 使用ThreadLocal记录当前线程的数据源key
    private static final ThreadLocal<String> holder = new ThreadLocal<String>();

    public static void putDataSource(String name){
        holder.set(name);
    }

    public static String getDataSource(){
        return holder.get();
    }

    /**
     * 清理数据源
     */
    public static void clearDataSource() {
        holder.remove();
    }

}
