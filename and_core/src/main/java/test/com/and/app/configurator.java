package test.com.and.app;

import java.util.WeakHashMap;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by lh on 2017/12/28.
 *   p配置文件
 */

public class Configurator {

    // WeakHashMap 键值对在不用时，就会回收，比hashmip要好
    public static final WeakHashMap<String,Object> SHOP_CONFINGS = new WeakHashMap<>();


    public Configurator() {
        // 定义的是字符串，所以字符串的形式输出来，这是枚举类的方法
        // false  配置开始了，但是他还没有配置完成
        SHOP_CONFINGS.put(ConfigType.CONFIG_READY.name(),false);
    }

    // 在多线程开发中一般 懒汉模式都会有问题的

    // 静态内部类单利模式的初始化

    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }


    public final void configure(){
        SHOP_CONFINGS.put(ConfigType.CONFIG_READY.name(),true);
    }

    final WeakHashMap<String,Object> getShopConfings(){
        return SHOP_CONFINGS;
    }

    public final Configurator withApiHost(String apiHost){
        SHOP_CONFINGS.put(ConfigType.API_HOST.name(),apiHost);
        return this;
    }

    private void checkConfiguration(){
        final boolean isReady = (boolean) SHOP_CONFINGS.get(ConfigType.CONFIG_READY.name());
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
    final <T> T getConfiguration(Enum<ConfigType> key){
        checkConfiguration();
        return (T) SHOP_CONFINGS.get(key.name());
    }


}
