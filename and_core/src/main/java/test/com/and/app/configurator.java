package test.com.and.app;

import java.util.ArrayList;
import java.util.WeakHashMap;

import okhttp3.Interceptor;

/**
 * Created by lh on 2017/12/28.
 *   p配置文件
 */

public class configurator {

    // WeakHashMap 键值对在不用时，就会回收，比hashmip要好
    private static final WeakHashMap<Object,Object> SHOP_CONFINGS = new WeakHashMap<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private configurator() {
        // 定义的是字符串，所以字符串的形式输出来，这是枚举类的方法
        // false  配置开始了，但是他还没有配置完成
        SHOP_CONFINGS.put(ConfigType.CONFIG_READY,false);
    }

    // 在多线程开发中一般 懒汉模式都会有问题的

    // 静态内部类单利模式的初始化

    public static configurator getInstance(){
        return Holder.INSTANCE;
    }

    private static class Holder{
        private static final configurator INSTANCE = new configurator();
    }

    public final void configure(){
        SHOP_CONFINGS.put(ConfigType.CONFIG_READY,true);
    }

    final WeakHashMap<Object,Object> getShopConfings(){
        return SHOP_CONFINGS;
    }

    public final configurator withApiHost(String apiHost){
        SHOP_CONFINGS.put(ConfigType.API_HOST,apiHost);
        return this;
    }

    public final configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        SHOP_CONFINGS.put(ConfigType.INTERCEPTOR,INTERCEPTORS);
        return this;
    }

    public final configurator withInterceptors(ArrayList<Interceptor> interceptors){
        INTERCEPTORS.addAll(interceptors);
        SHOP_CONFINGS.put(ConfigType.INTERCEPTOR,INTERCEPTORS);
        return this;
    }

    private void checkConfiguration(){
        final boolean isReady = (boolean) SHOP_CONFINGS.get(ConfigType.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configurator is not ready,call Configure");
        }
    }

    /**
     *
     * 检查配置有没有完成
     * @param key
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key){
        checkConfiguration();
        final Object value = SHOP_CONFINGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) SHOP_CONFINGS.get(key);
    }


}
