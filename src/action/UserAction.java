package action;

import pojo.AddrEntity;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import org.hibernate.query.Query;
import pojo.UserEntity;
import pojo.quarter.FollowEntity;
import service.UserImpl;
import utils.*;

import java.io.File;
import java.sql.Timestamp;
import java.util.*;

public class UserAction implements Action {

    private final int TOKEN_TIME = 60*1000;//1天
//    private final int TOKEN_TIME = 60*1000*60*24;//1天

    private String token;
    private String appVersion;
    private String source;

    public static long loginCount = 0;
    public static long regCount = 0;//注册接口调用次数
    public static long userCount = 0;//获取用户信息调用次数
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


    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

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
        regCount++;
        jsonData = new HashMap<>();
        System.out.println("===============用户注册请求：mobile：" + mobile + "   password：" + password + "===============");

        if (!Utils.isEmpty(mobile)){

            if (!AccountValidatorUtil.isInteger(mobile)){
                jsonData.put("msg", "请输入正确的手机号码");
                jsonData.put("code", "1");
                jsonData.put("data", "{}");
                return SUCCESS;
            }
        }
        if (!Utils.isEmpty(mobile) && !Utils.isEmpty(password)) {
            if (!AccountValidatorUtil.isMobile(mobile)){
                jsonData.put("msg", "请输入正确的手机号码");
                jsonData.put("code", "1");
                jsonData.put("data", "{}");
                return SUCCESS;
            }
            if (!AccountValidatorUtil.isPassword(password)){
                jsonData.put("msg", "密码格式有问题，不能少于6位数");
                jsonData.put("code", "1");
                jsonData.put("data", "{}");
                return SUCCESS;
            }

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

                    String sql = "from UserEntity where mobile = :mobile";
                    Query query = getUser().getSf().openSession().createQuery(sql);
                    query.setParameter("mobile", mobile);
                    List<UserEntity> userEntityList = query.list();
                    if (userEntityList!=null&&userEntityList.size()>0){
                        UserEntity userEntity1  = userEntityList.get(0);
                        //uid md5 加密生成appkey，uid和password用aes加密

                        userEntity1.setAppkey(MD5Tools.get16Md5Value(String.valueOf(userEntity1.getUid())));
                        String encryptResultStr = AesUtils.parseByte2HexStr(AesUtils.encrypt(String.valueOf(userEntity1.getUid()),userEntity1.getPassword()));
                        userEntity1.setPassword(AesUtils.parseByte2HexStr(AesUtils.encrypt(userEntity1.getPassword(),"kson")));
                        userEntity1.setAppsecret(encryptResultStr);


                        user.update(userEntity1);
                        jsonData.put("msg", "注册成功");
                        jsonData.put("code", "0");
//                        jsonData.put("data", userEntityList.get(0));
                    }

                }


        } else {
            jsonData.put("msg", "天呢！用户名或密码不能为空");
            jsonData.put("code", "1");
            jsonData.put("data","{}");
            System.out.println("===============用户名或密码不能为空：mobile：" + mobile + "   password：" + password + "===============");

        }

        mobile = null;
        password = null;


        System.out.println("注册接口一共调用："+regCount+"次");
        return SUCCESS;


    }



    /**
     * 获取用户信息
     *
     * @return
     */
    public String getUserInfo() {
        userCount++;
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

                    String followsql = "from FollowEntity where uid = :uid";
                    Query followquery = getUser().getSf().openSession().createQuery(followsql);
                    followquery.setParameter("uid", Long.parseLong(uid));
                    List<FollowEntity> followEntities = followquery.list();
                    if (followEntities!=null){
                        userEntity.setFollow(followEntities.size());
                    }
                    String fansql = "from FollowEntity where followId = :followId";
                    Query fansquery = getUser().getSf().openSession().createQuery(fansql);
                    fansquery.setParameter("followId", Long.parseLong(uid));
                    List<FollowEntity> fansEntities = fansquery.list();
                    if (followEntities!=null){
                        userEntity.setFollow(followEntities.size());
                    }
                    if (fansEntities!=null){
                        userEntity.setFans(fansEntities.size());
                    }
                    System.out.println("session:" + ActionContext.getContext().getSession());
                    if (userEntity != null) {
                        System.out.println("===============获取用户信息成功：uid：" + uid + "===============");
                        jsonData.put("code", "0");
                        jsonData.put("msg", "获取用户信息成功");
                        jsonData.put("data", userEntity);

                    }
                }else{
                    jsonData.put("code", "1");
                    jsonData.put("msg", "没有此用户信息");
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


        System.out.println("token:"+token);

        loginCount++;
        jsonData = new HashMap<>();
        System.out.println("===============用户登录请求：mobile：" + mobile + "   password：" + password + "===============");
//       if (!Utils.isEmpty(token)){
//
//           String sql = "from UserEntity where token = :token";
//           Query query = getUser().getSf().openSession().createQuery(sql);
//           query.setParameter("token", token);
//           List<UserEntity> list =  query.list();
//           if (list!=null&&list.size()>0){
//               if (new Date().getTime()-TimeUtils.getMill(list.get(0).getCreatetime().toString())>TOKEN_TIME){
//                   jsonData.put("msg", "token超时");
//                   jsonData.put("code", "2");
//                   token = null;
//                   return SUCCESS;
//               }
//               jsonData.put("msg", "登录成功");
//               jsonData.put("code", "0");
//               jsonData.put("data",list.get(0));
//               token = null;
//               return SUCCESS;
//           }else{
//               jsonData.put("msg", "用户未登录");
//               jsonData.put("code", "1");
//               return SUCCESS;
//           }
//
//       }else{

//           if (Utils.isEmpty(source)||!source.equals("android")){
//               jsonData.put("msg", "非法地址");
//               jsonData.put("code", "1");
//               return SUCCESS;
//           }
           if (!Utils.isEmpty(mobile)) {
               if (!AccountValidatorUtil.isInteger(mobile)) {
                   jsonData.put("msg", "请输入正确的手机号码");
                   jsonData.put("code", "1");
                   return SUCCESS;
               }
           }
           if (!Utils.isEmpty(mobile)&&!Utils.isEmpty(password)) {
               if (!AccountValidatorUtil.isMobile(mobile)){
                   jsonData.put("msg", "请输入正确的手机号码");
                   jsonData.put("code", "1");
                   return SUCCESS;
               }
               if (!AccountValidatorUtil.isPassword(password)){
                   jsonData.put("msg", "密码格式有问题，不能少于6位数");
                   jsonData.put("code", "1");
                   return SUCCESS;
               }
               String sql = "from UserEntity where mobile = :mobile";
               Query query = getUser().getSf().openSession().createQuery(sql);
               query.setParameter("mobile", mobile);
               List<UserEntity> list =  query.list();
               //当前对象是否有记录
//            List<UserEntity> list = getUser().getSf().openSession().createQuery("from UserEntity where mobile = '" + mobile + "'").list();

               if (list != null && list.size() > 0) {
                   UserEntity userEntity = list.get(0);
                   if (userEntity!=null){

                       //解密
                       byte[] decryptFrom = AesUtils.parseHexStr2Byte(userEntity.getPassword());
                       byte[] decryptResult = AesUtils.decrypt(decryptFrom,"kson");//kson为密码加密密钥
                       if (decryptResult!=null){
                           System.out.println("解密后：" + new String(decryptResult));
                           String decryptPass = new String(decryptResult);
                           if (mobile.equals(userEntity.getMobile()) && decryptPass.equals(password)) {
                               userEntity.setCreatetime(Timestamp.valueOf(TimeUtils.getDateTimeFormat(new Date())));

                               //用户名和加密的密码生成token
                               String token = AesUtils.parseByte2HexStr(AesUtils.encrypt(String.valueOf(userEntity.getUid()),userEntity.getPassword()));

                               userEntity.setToken(token);
                               getUser().update(userEntity);
                               jsonData.put("code", "0");
                               jsonData.put("msg", "登录成功");
                               jsonData.put("data", userEntity);
                               System.out.println("===============用户登录成功：mobile：" + mobile + "   password：" + password + "===============");
                           } else {
                               if (mobile.equals(userEntity.getMobile())&&!password.equals(decryptPass)){
                                   jsonData.put("code", "1");
                                   jsonData.put("msg", "天呢！密码错误");
                                   System.out.println("===============密码错误：mobile：" + mobile + "   password：" + password + "===============");
//                                return  ERROR;
                               }else if (!mobile.equals(userEntity.getMobile())&&password.equals(decryptPass)){
                                   jsonData.put("code", "1");
                                   jsonData.put("msg", "天呢！用户名错误");

                                   System.out.println("===============用户名错误：mobile：" + mobile + "   password：" + password + "===============");
                               }
                           }

                       }else{

                           if (mobile.equals(userEntity.getMobile()) && userEntity.getPassword().equals(password)) {
                               userEntity.setCreatetime(Timestamp.valueOf(TimeUtils.getDateTimeFormat(new Date())));
                               System.out.println("tttt1============================:"+userEntity.getCreatetime());
                               //用户名和加密的密码生成token
                               String token = AesUtils.parseByte2HexStr(AesUtils.encrypt(String.valueOf(userEntity.getUid()),userEntity.getPassword()));

                               userEntity.setToken(token);
                               getUser().update(userEntity);
                               jsonData.put("code", "0");
                               jsonData.put("msg", "登录成功");
                               jsonData.put("data", userEntity);
                               System.out.println("===============用户登录成功：mobile：" + mobile + "   password：" + password + "===============");
                           } else {
                               if (mobile.equals(userEntity.getMobile())&&!password.equals(userEntity.getPassword())){
                                   jsonData.put("code", "1");
                                   jsonData.put("msg", "天呢！密码错误");

                                   System.out.println("===============密码错误：mobile：" + mobile + "   password：" + password + "===============");
                               }else if (!mobile.equals(userEntity.getMobile())&&password.equals(userEntity.getPassword())){
                                   jsonData.put("code", "1");
                                   jsonData.put("msg", "天呢！用户名错误");

                                   System.out.println("===============用户名错误：mobile：" + mobile + "   password：" + password + "===============");
                               }
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


//           }
       }

        mobile = null;
        password = null;
        token = null;

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

    @Override
    public String execute() throws Exception {

        return SUCCESS;
    }


}
