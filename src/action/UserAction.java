package action;

import bean.AddrEntity;
import bean.UserEntity;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import service.UserImpl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAction extends ActionSupport {

    private String uid;
    private String mobile;
    private String password;

    private File file; //上传的文件
    private String fileName; //文件名称

    private String savePath;

    private UserImpl user;

    private Map<String, Object> jsonData;

    private String nickname;

    private String name;
    private String addr;
    private String status;
    private String addrid;

    public void setAddrid(String addrid) {
        this.addrid = addrid;
    }

    public String getAddrid() {
        return addrid;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getAddr() {
        return addr;
    }

    public void setUser(UserImpl user) {
        this.user = user;
    }

    public UserImpl getUser() {
        return user;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Map<String, Object> getJsonData() {
        return jsonData;
    }

    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    /**
     * 注册
     *
     * @return
     */
    public String register() {
        jsonData = new HashMap<>();

        if (mobile!=null && password!=null) {
            if (user.isExist(mobile)) {

                jsonData.put("msg", "用户已注册");
                jsonData.put("code", "1");
                jsonData.put("data", "{}");

            } else {

                UserEntity userEntity = new UserEntity();
                userEntity.setMobile(mobile);
                userEntity.setPassword(password);
                userEntity.setUsername(mobile);
                user.add(userEntity);

                jsonData.put("msg", "注册成功");
                jsonData.put("code", "0");
                jsonData.put("data", userEntity);
            }
        } else {
            jsonData.put("msg", "用户名或密码不能为空");
            jsonData.put("code", "1");
        }

        mobile =null;
        password = null;


        return SUCCESS;


    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public String getUserInfo() {
        jsonData = new HashMap<>();

        if (uid!=null) {

            List<UserEntity> userEntityList = getUser().getSf().openSession().createQuery("from UserEntity where uid = '" + uid + "'").list();
            if (userEntityList!=null){
                if (userEntityList.size()>0){
                    UserEntity userEntity = userEntityList.get(0);

                    System.out.println("session:" + ActionContext.getContext().getSession());
                    if (userEntity != null) {
                        jsonData.put("code", "0");
                        jsonData.put("msg", "获取用户信息成功");
                        jsonData.put("data", userEntity);

                    }
                }
            }


        } else {
            jsonData.put("code", "1");
            jsonData.put("msg", "用户名不能为空");
        }

        uid = null;

        return SUCCESS;


    }

    /**
     * 上传图片
     *
     * @return
     */
    public String updateNickName() {
        jsonData = new HashMap<>();
        System.out.println("uid===="+uid);
        if (uid==null) {
            jsonData.put("code", "1");
            jsonData.put("msg", "用户id不能为空");
            return SUCCESS;
        }
             UserEntity userEntity = (UserEntity) getUser().getSf().openSession().createQuery("from UserEntity where uid = '" + uid + "'").list().get(0);

             if (userEntity!=null){
                 userEntity.setNickname(nickname);
                 user.update(userEntity);
                 jsonData.put("code", "0");
                 jsonData.put("msg", "昵称修改成功");
             }
        uid = null;
        return SUCCESS;

    }

    /**
     * 登录
     *
     * @return
     */
    public String login() {
        jsonData = new HashMap<>();

        if (mobile!=null && password!=null) {
            //当前对象是否有记录
            List<UserEntity> list = getUser().getSf().openSession().createQuery("from UserEntity where mobile = '" + mobile + "'").list();

            if (list != null && list.size() > 0) {
                UserEntity user = list.get(0);
                System.out.println("mobile:" + mobile + " password:" + password);
                if (mobile.equals(user.getMobile()) && user.getPassword().equals(password)) {
                    jsonData.put("code", "0");
                    jsonData.put("msg", "登录成功");
                    jsonData.put("data", user);

                } else {
                    jsonData.put("code", "1");
                    jsonData.put("msg", "用户名或密码错误");
                }
            } else {
                jsonData.put("code", "1");
                jsonData.put("msg", "用户不存在");
            }
        } else {
            if (mobile==null&&password!=null) {
                jsonData.put("msg", "用户名不能为空");
                jsonData.put("code", "1");
            }
            if (password==null&&mobile!=null) {
                jsonData.put("msg", "密码不能为空");
                jsonData.put("code", "1");
            }



        }

        mobile = null;
        password = null;

        return SUCCESS;
    }


    public String add() {


        return SUCCESS;
    }


    /**
     * 上传图片
     *
     * @return
     */
    public String upload() {
        jsonData = new HashMap<>();
        System.out.println("uid===="+uid);
        System.out.println("file=======================:"+file);
        if (file == null) {
            jsonData.put("code", "1");
            jsonData.put("msg", "文件不能为空");
            return SUCCESS;
        }
        if (uid==null) {
            jsonData.put("code", "1");
            jsonData.put("msg", "用户id不能为空");
            return SUCCESS;
        }



        String filename = String.valueOf(System.currentTimeMillis())+file.getName();

        String realpath = ServletActionContext.getServletContext().getRealPath("/images");
        if (file != null) {
            System.out.println("file:" + file);
            try {
                String path = realpath + "/" + filename;
                FileUtils.copyFile(file, new File(path));
                UserEntity userEntity = (UserEntity) getUser().getSf().openSession().createQuery("from UserEntity where uid = '" + uid + "'").list().get(0);
                userEntity.setIcon("http://120.27.23.105/images/" + filename);
                user.update(userEntity);
                jsonData.put("code", "0");
                jsonData.put("msg", "文件上传成功");
                return SUCCESS;
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            jsonData.put("code", "1");
            jsonData.put("msg", "文件不能为空");
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

    /**
     * 增加地址i
     *
     * @return
     */
    public String addAddr() {
        jsonData = new HashMap<>();

        if (uid==null) {
            jsonData.put("code","1");
            jsonData.put("msg","用户id不能为空");
            return SUCCESS;

        }
        if (addr==null) {
            jsonData.put("code","1");
            jsonData.put("msg","详细地址不能为空");
            return SUCCESS;
        }
        if (mobile==null) {
            jsonData.put("code","1");
            jsonData.put("msg","手机号不能为空");
            return SUCCESS;
        }

        if (name==null) {
            jsonData.put("code","1");
            jsonData.put("msg","姓名不能为空");
            return SUCCESS;
        }

        AddrEntity addrEntity = new AddrEntity();

                addrEntity.setAddr(addr);
                addrEntity.setMobile(Long.parseLong(mobile));
                addrEntity.setName(name);
                addrEntity.setStatus(0);
                addrEntity.setUid(Long.parseLong(uid));
                getUser().addAddress(addrEntity);


                jsonData.put("msg", "添加成功");
                jsonData.put("code", "0");
                jsonData.put("data", addrEntity);


        uid =null;
        addr = null;
        mobile = null;
        name = null;


        return SUCCESS;


    }

    /**
     * xiugai地址i
     *
     * @return
     */
    public String updateAddr() {
        jsonData = new HashMap<>();

        if (uid==null) {
            jsonData.put("code","1");
            jsonData.put("msg","用户id不能为空");
            return SUCCESS;
        }

        if (addrid==null) {
            jsonData.put("code","1");
            jsonData.put("msg","地址id不能为空");
            return SUCCESS;
        }

        List<AddrEntity> list = getUser().getSf().openSession().createQuery("from AddrEntity where uid = '" + uid + "' and addrid = '"+addrid+"'").list();

        if (list!=null&&list.size()>0){
            AddrEntity addrEntity = list.get(0);
            if (status!=null){
                if (status.equals("1")){
                    addrEntity.setStatus(Integer.parseInt(status));
                }

            }
            if (addr!=null){
                addrEntity.setAddr(addr);
            }
            if (mobile!=null){
                addrEntity.setMobile(Integer.parseInt(mobile));
            }

            if (name!=null){
                addrEntity.setName(name);
            }
            getUser().updateAddress(addrEntity);
            jsonData.put("msg", "更新成功");
            jsonData.put("code", "0");
            jsonData.put("data", addrEntity);

        }




        uid =null;
        addr = null;
        mobile = null;
        name = null;


        return SUCCESS;


    }

    /**
     * xiugai地址i
     *
     * @return
     */
    public String getAddrs() {
        jsonData = new HashMap<>();

        if (uid==null) {
            jsonData.put("code","1");
            jsonData.put("msg","用户id不能为空");
            return SUCCESS;
        }

        List<AddrEntity> list = getUser().getSf().openSession().createQuery("from AddrEntity where uid = '" + uid + "'").list();

        if (list!=null){

            jsonData.put("msg", "地址列表请求成功");
            jsonData.put("code", "0");
            jsonData.put("data", list);

        }

        uid =null;

        return SUCCESS;


    }

}
