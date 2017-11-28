package utils;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Enumeration;
import java.util.Map;

public class RequestInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = 3244973830196015811L;
    private HttpSession session;

    public void setSession(Map<String, Object> session) {
        this.session = (HttpSession) session;
    }

    public String intercept(ActionInvocation aInvocation) throws Exception {
// 获取请求的action名称
//        String actionName = aInvocation.getInvocationContext().getName();
//        System.out.println("actionName："+actionName);
        HttpServletRequest request = ServletActionContext.getRequest();

        Enumeration paraKeys = request.getParameterNames();

        StringBuffer encodeStr = new StringBuffer();




        String currentURL = request.getRequestURI();
        System.out.println("请求Url:"+currentURL);
        if (paraKeys!=null&&paraKeys.hasMoreElements()){

            while (paraKeys.hasMoreElements()) {
                String paraKey = (String) paraKeys.nextElement();

                String paraValue = request.getParameter(paraKey);
                encodeStr.append(paraKey+"="+paraValue+"&");
//            System.out.println("key:"+paraKey+"value:"+paraValue);
            }
        }

        if (encodeStr.length()>0){

            System.out.println("拼装参数："+encodeStr.toString().substring(0,encodeStr.length()-1));
        }
// 获取action后附带参数
//        Map<String,Object> parameters = aInvocation.getInvocationContext().getParameters();
//        System.out.println("参数：:"+parameters.toString());;
//        StringBuffer sb = new StringBuffer();
//        if (parameters!=null&&parameters.size()>0){
//            Object object = parameters.get("params");
//            String[] values = (String[]) object;
//            for (int i = 0; i < values.length; i++) {
//                sb.append(values[i]);
//            }
//
//            System.out.println("参数："+sb.toString());
//
////            for (Map.Entry<String, Object> stringObjectEntry : parameters.entrySet()) {
////
////                if (stringObjectEntry.getValue() instanceof File){
////
////                }else{
////                    String value = stringObjectEntry.getValue().toString();
////                    System.out.println("参数："+stringObjectEntry.getKey()+"="+value+"&");
////                }
////                System.out.println("hhh"+ parameters.get(stringObjectEntry.getKey()));;
////
////            }
//        }

//        System.out.println("session："+ aInvocation.getInvocationContext().getSession());;
//        System.out.println("getConversionErrors："+ aInvocation.getInvocationContext().getConversionErrors());
//        System.out.println("getLocale："+ aInvocation.getInvocationContext().getLocale());
//        System.out.println("getValueStack："+ aInvocation.getInvocationContext().getValueStack());



        return aInvocation.invoke();
    }
}