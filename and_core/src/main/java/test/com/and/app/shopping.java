package test.com.and.app;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * Created by lh on 2017/12/28.
 */

public final class Shopping {

     public static Configurator init(Context context){
         getConfigurator().put(ConfigType.APPLICATION_CONTEXT.name(),context);
         return Configurator.getInstance();
     }

     private static WeakHashMap<String,Object> getConfigurator(){
         return Configurator.getInstance().getShopConfings();
     }
}
