package test.com.and.app;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * Created by lh on 2017/12/28.
 */

public final class shopping {

     public static configurator init(Context context){
         getConfigurator().put(ConfigType.APPLICATION_CONTEXT.name(),context);
         return configurator.getInstance();
     }

     public static WeakHashMap<String,Object> getConfigurator(){
         return configurator.getInstance().getShopConfings();
     }

     public static Context getApplication(){
         return (Context) getConfigurator().get(ConfigType.APPLICATION_CONTEXT.name());
     }
}
