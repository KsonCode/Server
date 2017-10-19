package utils;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 网站在线人数监听器
 */
public class MySessionListener  implements HttpSessionListener {

    private long onlineCount;

    public void sessionCreated(HttpSessionEvent event) {
        // TODO Auto-generated method stub
        this.onlineCount=this.onlineCount+1;
        //保存在application作用域
        event.getSession().getServletContext().setAttribute("onlineCount", onlineCount);
    }

    public void sessionDestroyed(HttpSessionEvent event) {
        // TODO Auto-generated method stub
        this.onlineCount=this.onlineCount-1;
        event.getSession().getServletContext().setAttribute("onlineCount", onlineCount);
    }

}