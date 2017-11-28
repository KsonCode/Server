package utils;

import action.UploadAction;
import action.UserAction;
import com.google.gson.JsonObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.StrutsStatics;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor extends AbstractInterceptor{

        @Override
        public String intercept(ActionInvocation invocation) throws Exception {

            System.out.println("-- TokenInterceptor --");

            Object action = invocation.getAction();
            if (action instanceof UserAction || action instanceof UploadAction) {

                if (invocation.getProxy().getActionName().equals("login")) {
                    System.out.println("-- TokenInterceptor -- " + "login 不需要拦截");
                    return invocation.invoke();
                }

            }

            // 取得请求相关的ActionContext实例
            ActionContext actionContext = invocation.getInvocationContext();

            HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
            HttpServletResponse response = (HttpServletResponse)actionContext.get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);

            System.out.println("REQUEST:"+request.getParameter("token"));

//            if (new TokenUtils().isVaild(request.getParameter("token"))) {
//                System.out.println("token 有效");
//                invocation.invoke();
//            }else{
//                System.out.println("token 过期");
//
//                JsonObject json = new JsonObject();
//                json.addProperty("code", "2");
//                json.addProperty("msg", "Token 过期");
//
//                response.setContentType("text/plain");
//                response.setCharacterEncoding("UTF-8");
//                response.getWriter().write(json.toString());
//                response.getWriter().flush();
//            }


            return null;
        }


}
