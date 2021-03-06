package com.lenovohit.lrouter;

import com.lenovohit.annotation.Action;
import com.lenovohit.lrouter_api.core.socket.server.LRSocketAction;

/**
 * Created by yuzhijun on 2017/6/13.
 */
@Action(name = "socketAction",provider = "main")
public class SocketAction extends LRSocketAction{
    @Override
    public String socketInvoke(String receiveStr) {
        return receiveStr;
    }

    @Override
    public int socketPort() {
        return 10000;
    }
}
