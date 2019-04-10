package com.zr.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/20 12:03
 * @Description:
 * 容器会为每一个连接创建一个EndPoint的实例，需要利用实例变量来保存一些状态信息
 * 下面的端点在收到文本消息时，将前一次收到的消息回复给其他的端点
 */
@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {
    private Session session;//代表着服务端点与远程客户端点的一次会话。

    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        log.info("【websocket消息】有新的连接, 总数:{}", webSocketSet.size());
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        log.info("【websocket消息】连接断开, 总数:{}", webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("【websocket消息】收到客户端发来的消息:{}", message);
    }

    public void sendMessage(String message) {
        for (WebSocket webSocket: webSocketSet) {
            log.info("【websocket消息】广播消息, message={}", message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
//    public static void sendInfo(String message,@PathParam("sid") String sid) throws IOException {
//        log.info("推送消息到窗口"+sid+"，推送内容:"+message);
//        for (WebSocket item : webSocketSet) {
//            try {
//                //这里可以设定只推送给这个sid的，为null则全部推送
//                if(sid==null) {
//                    item.sendMessage(message);
//                }else if(item.sid.equals(sid)){
//                    item.sendMessage(message);
//                }
//            } catch (IOException e) {
//                continue;
//            }
//        }
//    }
}
