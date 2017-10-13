package action;

import bean.AddrEntity;
import bean.UserEntity;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.query.Query;
import service.UserImpl;
import utils.AccountValidatorUtil;
import utils.Utils;

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
        System.out.println("===============用户注册请求：mobile：" + mobile + "   password：" + password + "===============");

        if (!Utils.isEmpty(mobile) && !Utils.isEmpty(password)) {
//            if (!AccountValidatorUtil.isMobile(mobile)){
//                jsonData.put("msg", "请输入正确的手机号码");
//                jsonData.put("code", "1");
//                return SUCCESS;
//            }
//            if (!AccountValidatorUtil.isPassword(password)){
//                jsonData.put("msg", "密码格式有问题，不能少于6位数");
//                jsonData.put("code", "1");
//                return SUCCESS;
//            }

                if (user.isExist(mobile)) {
                    jsonData.put("msg", "天呢！用户已注册");
                    jsonData.put("code", "1");
                    jsonData.put("data", "{}");
                    System.out.println("===============用户已注册：mobile：" + mobile + "===============");


                } else {
                    UserEntity userEntity = new UserEntity();
                    userEntity.setMobile(mobile);
                    userEntity.setPassword(password);
                    userEntity.setUsername(mobile);
                    user.add(userEntity);
                    System.out.println("===============用户注册成功：mobile：" + mobile + "   password：" + password + "===============");

                    jsonData.put("msg", "注册成功");
                    jsonData.put("code", "0");
                    jsonData.put("data", userEntity);
                }


        } else {
            jsonData.put("msg", "天呢！用户名或密码不能为空");
            jsonData.put("code", "1");
            System.out.println("===============用户名或密码不能为空：mobile：" + mobile + "   password：" + password + "===============");

        }

        mobile = null;
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
        System.out.println("===============获取用户信息请求：uid：" + uid + "===============");


        if (!Utils.isEmpty(uid)) {
            String sql = "from UserEntity where uid = :uid";
            Query query = getUser().getSf().openSession().createQuery(sql);
            query.setParameter("uid", Long.parseLong(uid));
            List<UserEntity> userEntityList = query.list();
//            List<UserEntity> userEntityList = getUser().getSf().openSession().createQuery("from UserEntity where uid = '" + uid + "'").list();
            if (userEntityList != null) {
                if (userEntityList.size() > 0) {
                    UserEntity userEntity = userEntityList.get(0);

                    System.out.println("session:" + ActionContext.getContext().getSession());
                    if (userEntity != null) {
                        System.out.println("===============获取用户信息成功：uid：" + uid + "===============");
                        jsonData.put("code", "0");
                        jsonData.put("msg", "获取用户信息成功");
                        jsonData.put("data", userEntity);

                    }
                }
            }


        } else {
            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！用户名不能为空");
        }

        uid = null;

        return SUCCESS;


    }

    /**
     * 修改用户昵称
     *
     * @return
     */
    public String updateNickName() {
        jsonData = new HashMap<>();
        System.out.println("===============修改昵称请求：uid：" + uid + "   nickname：" + nickname + "===============");

        if (Utils.isEmpty(uid)) {
            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！用户id不能为空");
            return SUCCESS;
        }
        String sql = "from UserEntity where uid = :uid";
        Query query = getUser().getSf().openSession().createQuery(sql);
        query.setParameter("uid", Long.parseLong(uid));
        UserEntity userEntity = (UserEntity) query.list().get(0);
//             UserEntity userEntity = (UserEntity) getUser().getSf().openSession().createQuery("from UserEntity where uid = '" + uid + "'").list().get(0);

        if (userEntity != null) {
            userEntity.setNickname(nickname);
            System.out.println("===============修改用户昵称成功：uid：" + uid + "   nickname：" + nickname + "===============");
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
        System.out.println("===============用户登录请求：mobile：" + mobile + "   password：" + password + "===============");

        if (!Utils.isEmpty(mobile)&&!Utils.isEmpty(password)) {
//            if (!AccountValidatorUtil.isMobile(mobile)){
//                jsonData.put("msg", "请输入正确的手机号码");
//                jsonData.put("code", "1");
//                return SUCCESS;
//            }
//            if (!AccountValidatorUtil.isPassword(password)){
//                jsonData.put("msg", "密码格式有问题，不能少于6位数");
//                jsonData.put("code", "1");
//                return SUCCESS;
//            }
            String sql = "from UserEntity where mobile = :mobile";
            Query query = getUser().getSf().openSession().createQuery(sql);
            query.setParameter("mobile", mobile);
            List<UserEntity> list =  query.list();
            //当前对象是否有记录
//            List<UserEntity> list = getUser().getSf().openSession().createQuery("from UserEntity where mobile = '" + mobile + "'").list();

            if (list != null && list.size() > 0) {
                UserEntity user = list.get(0);
                if (user!=null){

                    if (mobile.equals(user.getMobile()) && user.getPassword().equals(password)) {
                        jsonData.put("code", "0");
                        jsonData.put("msg", "登录成功");
                        jsonData.put("data", user);
                        System.out.println("===============用户登录成功：mobile：" + mobile + "   password：" + password + "===============");
                    } else {
                        if (mobile.equals(user.getMobile())&&!password.equals(user.getPassword())){
                            jsonData.put("code", "1");
                            jsonData.put("msg", "天呢！密码错误");

                            System.out.println("===============密码错误：mobile：" + mobile + "   password：" + password + "===============");
                        }else if (!mobile.equals(user.getMobile())&&password.equals(user.getPassword())){
                            jsonData.put("code", "1");
                            jsonData.put("msg", "天呢！用户名错误");

                            System.out.println("===============用户名错误：mobile：" + mobile + "   password：" + password + "===============");
                        }
                    }
                }
            } else {
                jsonData.put("code", "1");
                jsonData.put("msg", "天呢！用户不存在");
                System.out.println("===============用户不存在：mobile：" + mobile + "   password：" + password + "===============");

            }
        } else {
            if (Utils.isEmpty(mobile) && !Utils.isEmpty(password)) {
                jsonData.put("msg", "天呢！用户名不能为空");
                jsonData.put("code", "1");
                System.out.println("===============用户名不能为空：mobile：" + mobile + "   password：" + password + "===============");

            }
            if (Utils.isEmpty(password) && !Utils.isEmpty(mobile)) {
                jsonData.put("msg", "天呢！密码不能为空");
                jsonData.put("code", "1");
                System.out.println("===============密码不能为空：mobile：" + mobile + "   password：" + password + "===============");

            }
            if (Utils.isEmpty(password) &&Utils.isEmpty(mobile)) {
                jsonData.put("msg", "天呢！用户名和密码怎么都是空");
                jsonData.put("code", "1");
                System.out.println("===============用户名和密码都为空：mobile：" + mobile + "   password：" + password + "===============");

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
     * 增加地址i
     *
     * @return
     */
    public String addAddr() {
        jsonData = new HashMap<>();
        System.out.println("===============增加地址请求：uid：" + uid + "   addr：" + addr + "    mobile" + mobile + "    name：" + name + "===============");


        if (Utils.isEmpty(uid)) {
            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！用户id不能为空");
            return SUCCESS;

        }
        if (Utils.isEmpty(addr)) {
            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！详细地址不能为空");
            return SUCCESS;
        }
        if (Utils.isEmpty(mobile)) {
            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！手机号不能为空");
            return SUCCESS;
        }

        if (Utils.isEmpty(name)) {
            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！姓名不能为空");
            return SUCCESS;
        }

        AddrEntity addrEntity = new AddrEntity();

        addrEntity.setAddr(addr);
        addrEntity.setMobile(Long.parseLong(mobile));
        addrEntity.setName(name);
        addrEntity.setStatus(0);
        addrEntity.setUid(Long.parseLong(uid));
        getUser().addAddress(addrEntity);

        System.out.println("===============添加地址成功：uid：" + uid + "===============");

        jsonData.put("msg", "添加成功");
        jsonData.put("code", "0");
        jsonData.put("data", addrEntity);


        uid = null;
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
        System.out.println("===============更新地址成功：mobile：" + uid + "   addrid：" + addrid + "===============");

        if (Utils.isEmpty(uid)) {
            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！用户id不能为空");
            return SUCCESS;
        }

        if (Utils.isEmpty(addrid)) {
            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！地址id不能为空");
            return SUCCESS;
        }

        String sql = "from AddrEntity where uid = :uid and addrid = :addrid";
        Query query = getUser().getSf().openSession().createQuery(sql);
        query.setParameter("uid", Long.parseLong(uid));
        query.setParameter("addrid", Long.parseLong(addrid));
        List<AddrEntity> list = query.list();
//        List<AddrEntity> list = getUser().getSf().openSession().createQuery("from AddrEntity where uid = '" + uid + "' and addrid = '"+addrid+"'").list();

        if (list != null && list.size() > 0) {
            AddrEntity addrEntity = list.get(0);
            if (addr != null) {
                addrEntity.setAddr(addr);
            }
            if (mobile != null) {
                addrEntity.setMobile(Integer.parseInt(mobile));
            }

            if (name != null) {
                addrEntity.setName(name);
            }
            getUser().updateAddress(addrEntity);
            jsonData.put("msg", "更新成功");
            jsonData.put("code", "0");
            jsonData.put("data", addrEntity);
            System.out.println("===============更新地址成功：uid：" + uid + "   addrid：" + addrid + "===============");


        }
        uid = null;
        addr = null;
        mobile = null;
        name = null;
        return SUCCESS;


    }

    /**
     * 设为默认
     *
     * @return
     */
    public String setAddr() {
        jsonData = new HashMap<>();
        System.out.println("===============设为默认地址请求：uid：" + uid + "   addrid：" + addrid + "===============");

        if (Utils.isEmpty(uid)) {
            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！用户id不能为空");
            return SUCCESS;
        }

        if (Utils.isEmpty(addrid)) {
            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！地址id不能为空");
            return SUCCESS;
        }

        String sql = "from AddrEntity where uid = :uid";
        Query query = getUser().getSf().openSession().createQuery(sql);
        query.setParameter("uid", Long.parseLong(uid));
        List<AddrEntity> list = query.list();

//        List<AddrEntity> list = getUser().getSf().openSession().createQuery("from AddrEntity where uid = '" + uid + "'").list();

        if (list != null && list.size() > 0) {
            for (AddrEntity addrEntity : list) {

                if (addrEntity.getAddrid() == Long.parseLong(addrid)) {
                    addrEntity.setStatus(1);
                } else {
                    addrEntity.setStatus(0);
                }
                getUser().updateAddress(addrEntity);

            }

            jsonData.put("msg", "更新成功");
            jsonData.put("code", "0");

            System.out.println("===============设为默认成功：uid：" + uid + "   addrid：" + addrid + "===============");


        }
        uid = null;
        addrid = null;
        return SUCCESS;
    }

    /**
     * 设为默认
     *
     * @return
     */
    public String getDefaultAddr() {
        jsonData = new HashMap<>();
        System.out.println("===============获取默认地址请求：uid：" + uid + "===============");

        if (Utils.isEmpty(uid)) {
            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！用户id不能为空");
            return SUCCESS;
        }

        String sql = "from AddrEntity where uid = :uid";
        Query query = getUser().getSf().openSession().createQuery(sql);
        query.setParameter("uid", Long.parseLong(uid));
        List<AddrEntity> list = query.list();

//        List<AddrEntity> list = getUser().getSf().openSession().createQuery("from AddrEntity where uid = '" + uid + "'").list();

        if (list != null && list.size() > 0) {
            for (AddrEntity addrEntity : list) {

                if (addrEntity.getStatus() == 1) {
                    jsonData.put("msg", "请求成功");
                    jsonData.put("code", "0");
                    jsonData.put("data", addrEntity);
                    System.out.println("===============获取默认地址成功：uid：" + uid + "===============");

                }


            }


        }
        uid = null;
        return SUCCESS;
    }

    /**
     * 常用地址列表哦
     *
     * @return
     */
    public String getAddrs() {
        jsonData = new HashMap<>();
        System.out.println("===============常用地址列表请求：uid：" + uid + "===============");

        if (Utils.isEmpty(uid)) {
            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！用户id不能为空");
            return SUCCESS;
        }

        String sql = "from AddrEntity where uid = :uid";
        Query query = getUser().getSf().openSession().createQuery(sql);
        query.setParameter("uid", Long.parseLong(uid));
        List<AddrEntity> list = query.list();
//        List<AddrEntity> list = getUser().getSf().openSession().createQuery("from AddrEntity where uid = '" + uid + "'").list();

        if (list != null) {

            jsonData.put("msg", "地址列表请求成功");
            jsonData.put("code", "0");
            jsonData.put("data", list);
            System.out.println("===============地址列表请求成功：uid：" + uid + "===============");
        }

        uid = null;

        return SUCCESS;


    }

}
