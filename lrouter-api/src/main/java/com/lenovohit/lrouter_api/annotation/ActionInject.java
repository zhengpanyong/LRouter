package com.lenovohit.lrouter_api.annotation;

import com.lenovohit.lrouter_api.annotation.ioc.Action;
import com.lenovohit.lrouter_api.base.LRouterAppcation;
import com.lenovohit.lrouter_api.core.LRAction;
import com.lenovohit.lrouter_api.core.LRProvider;
import com.lenovohit.lrouter_api.core.LocalRouter;
import com.lenovohit.lrouter_api.exception.LRException;
import com.lenovohit.lrouter_api.utils.ILRLogger;
import com.lenovohit.lrouter_api.utils.LRLoggerFactory;

import java.lang.reflect.Method;

/**
 * 用于action的注解注入
 * Created by yuzhijun on 2017/6/2.
 */
public class ActionInject {
    private static final String TAG = "ActionInject";
    private static final String REGISTER_ACTION = "registerAction";

    public static void injectAction(LRAction lrAction){
        try{
            if (null == lrAction){
                throw new LRException("注入的action不能为空");
            }

            Class<? extends LRAction> clazz = lrAction.getClass();
            Action action = clazz.getAnnotation(Action.class);
            if (null != action){
                String name = action.name();
                String provider = action.provider();
                LRProvider lrProvider = LocalRouter.getInstance(LRouterAppcation.getInstance()).findProvider(provider);
                if (null != lrProvider){
                    //对provider进行实例化
                    Class<? extends LRProvider> providerClazz = lrProvider.getClass();
                    //进行调用
                    Method method = providerClazz.getMethod(REGISTER_ACTION,String.class,LRAction.class);
                    method.setAccessible(true);
                    method.invoke(lrProvider,name,lrAction);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            LRLoggerFactory.getLRLogger(TAG).log("注入Action失败", ILRLogger.LogLevel.ERROR);
        }
    }
}
