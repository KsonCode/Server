package action;

import bean.UserEntity;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.query.Query;
import service.UserImpl;
import utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UploadAction extends ActionSupport{
    private String uid;
    private File file; //上传的文件
    private String fileName; //文件名称

    private String savePath;

    private UserImpl user;

    private Map<String, Object> jsonData;

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setUser(UserImpl user) {
        this.user = user;
    }

    public UserImpl getUser() {
        return user;
    }

    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }

    public Map<String, Object> getJsonData() {
        return jsonData;
    }

    /**
     * 上传图片
     *
     * @return
     */
    public String upload() {
        jsonData = new HashMap<>();

        if (file!=null&&!file.isFile()){
            jsonData.put("code", "1");
            jsonData.put("msg", "文件格式不正确");
            return SUCCESS;
        }
        System.out.println("===============头像上传请求：file：" + file + "   uid：" + uid + "===============");
        if (file == null) {
            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！文件不能为空");
            return SUCCESS;
        }
        if (Utils.isEmpty(uid)) {
            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！用户id不能为空");
            return SUCCESS;
        }


        String filename = uid + ".jpg";

        String realpath = ServletActionContext.getServletContext().getRealPath("/images");
        if (file != null) {
            System.out.println("file:" + file);
            try {
                String path = realpath + "/" + filename;
                FileUtils.copyFile(file, new File(path));
                String sql = "from UserEntity where uid = :uid";
                Query query = getUser().getSf().openSession().createQuery(sql);
                query.setParameter("uid", Long.parseLong(uid));
                UserEntity userEntity = (UserEntity) query.list().get(0);
//                UserEntity userEntity = (UserEntity) getUser().getSf().openSession().createQuery("from UserEntity where uid = '" + uid + "'").list().get(0);
                userEntity.setIcon("http://120.27.23.105/images/" + filename);
                user.update(userEntity);
                jsonData.put("code", "0");
                jsonData.put("msg", "文件上传成功");
                System.out.println("===============头像上传成功：uid：" + uid + "    file：" + path + "===============");
                return SUCCESS;
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！文件不能为空");
            System.out.println("===============文件不能为空：uid：" + uid + "   file：" + file + "===============");

        }
        /**
         * 若要存入数据库
         * fileName是在entity实体类中声明存放文件名称的变量
         * yu.setFileName(imageFileName) 这样将文件名称存入数据库
         * 文件路径为：savefile
         */
        file = null;
        uid = null;
        return SUCCESS;

    }
}
