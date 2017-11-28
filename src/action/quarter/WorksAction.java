package action.quarter;

import com.opensymphony.xwork2.Action;
import common.Constants;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.query.Query;
import pojo.UserEntity;
import pojo.quarter.*;
import service.quarter.QuarterServiceImpl;
import utils.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static common.Constants.TOKEN_TIME;

public class WorksAction implements Action {

    //登录
    private String mobile;
    private String password;
    private String regType;
    private String gender;//0 男 1 女
    private String icon;//第三方登录icon
    private String userId;//第三方登录id
    private String nickname;//第三方登录nickname

    private String oldPassword;
    private String newPassword;


    private String token;
    private String appVersion;
    private String source;

    private String type;//推荐、关注

    private File videoFile;
    private File coverFile;

    //文件名称
    private String videoFileFileName;

    //文件类型
    private String videoFileContentType;
    //文件名称
    private String coverFileFileName;

    //文件类型
    private String coverFileContentType;
    
    private List<File> jokeFiles;
    private String[] jokeFilesFileName; //文件名称
    private String[] jokeFilesContentType; //文件类型
    private List<String> jokeUrls = new ArrayList<>();

    private String latitude;
    private String longitude;
    private String videoDesc;

    private String uid;
    private String content;
    private int page = 1;
    private QuarterServiceImpl quarterService;
    private Map<String, Object> jsonData;

    private String followId;

    private String wid;//作品id

    public String getFollowId() {
        return followId;
    }

    public void setFollowId(String followId) {
        this.followId = followId;
    }

    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }

    public Map<String, Object> getJsonData() {
        return jsonData;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public QuarterServiceImpl getQuarterService() {
        return quarterService;
    }

    public void setQuarterService(QuarterServiceImpl quarterService) {
        this.quarterService = quarterService;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    public File getvideoFile() {
        return videoFile;
    }

    public void setvideoFile(File videoFile) {
        this.videoFile = videoFile;
    }

    public File getVideoFile() {
        return videoFile;
    }

    public void setVideoFile(File videoFile) {
        this.videoFile = videoFile;
    }

    public File getCoverFile() {
        return coverFile;
    }

    public void setCoverFile(File coverFile) {
        this.coverFile = coverFile;
    }

    public String getVideoFileFileName() {
        return videoFileFileName;
    }

    public void setVideoFileFileName(String videoFileFileName) {
        this.videoFileFileName = videoFileFileName;
    }

    public String getVideoFileContentType() {
        return videoFileContentType;
    }

    public void setVideoFileContentType(String videoFileContentType) {
        this.videoFileContentType = videoFileContentType;
    }

    public String getCoverFileFileName() {
        return coverFileFileName;
    }

    public void setCoverFileFileName(String coverFileFileName) {
        this.coverFileFileName = coverFileFileName;
    }

    public String getCoverFileContentType() {
        return coverFileContentType;
    }

    public void setCoverFileContentType(String coverFileContentType) {
        this.coverFileContentType = coverFileContentType;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
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

    public String getRegType() {
        return regType;
    }

    public void setRegType(String regType) {
        this.regType = regType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public List<File> getJokeFiles() {
        return jokeFiles;
    }

    public void setJokeFiles(List<File> jokeFiles) {
        this.jokeFiles = jokeFiles;
    }

    public String[] getJokeFilesFileName() {
        return jokeFilesFileName;
    }

    public void setJokeFilesFileName(String[] jokeFilesFileName) {
        this.jokeFilesFileName = jokeFilesFileName;
    }

    public String[] getJokeFilesContentType() {
        return jokeFilesContentType;
    }

    public void setJokeFilesContentType(String[] jokeFilesContentType) {
        this.jokeFilesContentType = jokeFilesContentType;
    }

    /**
     * 发布段子
     * @return
     */
    public String publishJoke() {
        jsonData = new HashMap<>();
        System.out.println("===============发布段子===============");
        if (Utils.isEmpty(token)) {

            jsonData.put("msg", "token不能为空");
            jsonData.put("code", "1");
            return SUCCESS;

        }
        if (Utils.isEmpty(source)) {

            jsonData.put("msg", "source不能为空");
            jsonData.put("code", "1");

            return SUCCESS;

        } else {
            if (!"android".equals(source)) {
                jsonData.put("msg", "source值错误");
                jsonData.put("code", "1");

                return SUCCESS;
            }
        }
        if (Utils.isEmpty(appVersion)) {

            jsonData.put("msg", "appVersion不能为空");
            jsonData.put("code", "1");

            return SUCCESS;

        } else {
            if (!AccountValidatorUtil.isInteger(appVersion)) {
                jsonData.put("msg", "appVersion必须为整数");
                jsonData.put("code", "1");

                return SUCCESS;
            }

        }

        String sql = "from UserEntity where token = :token";
        Query query = quarterService.getSf().openSession().createQuery(sql);
        query.setParameter("token", token);
        List<UserEntity> list = query.list();
        if (list != null && list.size() > 0) {
            if (new Date().getTime() - TimeUtils.getMill(list.get(0).getCreatetime().toString()) > TOKEN_TIME) {
                jsonData.put("msg", "token超时");
                jsonData.put("code", "2");
                token = null;
                return SUCCESS;
            }
        } else {
            jsonData.put("msg", "用户未登录");
            jsonData.put("code", "1");
            token = null;
            return SUCCESS;
        }
        if (Utils.isEmpty(uid)) {

            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！用户id不能为空");

            return SUCCESS;

        }
        if (Utils.isEmpty(content)){
            jsonData.put("code", "1");
            jsonData.put("msg", "段子内容不能为空");

            return SUCCESS;
        }
        String realpath = ServletActionContext.getServletContext().getRealPath("/images/quarter");
        JokesEntity jokesEntity = new JokesEntity();
//        if (jokeFiles == null) {
//            jsonData.put("code", "1");
//            jsonData.put("msg", "天呢！文件列表对象不能为空！");
//
//            return SUCCESS;
//        }
        StringBuffer stringBuffer = new StringBuffer();
        if (jokeFiles!=null){
            File savedir=new File(realpath);
            if(!savedir.getParentFile().exists()){

                savedir.getParentFile().mkdirs();
            }
            for (int i = 0; i < jokeFiles.size(); i++) {

                try {
                    String path = realpath + "/" + jokeFilesFileName[i];
                    FileUtils.copyFile(jokeFiles.get(i), new File(path));//把图片写入到上面设置的路径里
                    jokeUrls.add(Constants.IMAGES_PATH+jokeFilesFileName[i]);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            if (jokeUrls!=null&&jokeUrls.size()>0){
                for (String jokeUrl : jokeUrls) {

                    stringBuffer.append(jokeUrl).append("|");
                }
            }
            String url = stringBuffer.toString();
            if (url!=null&&url.length()>0){
                jokesEntity.setImgUrls(url.substring(0,url.length()-1));
            }
        }


        jokesEntity.setUid(Long.parseLong(uid));
        jokesEntity.setContent(content);


        quarterService.publishJoke(jokesEntity);

        jsonData.put("code", "0");
        jsonData.put("msg", "发布成功");

        uid = null;
        source = null;
        appVersion = null;
        token = null;
        content = null;
        jokeFiles = null;
        jokeFilesFileName = null;
        jokeFilesContentType = null;

        return SUCCESS;
    }

    public String getJokes() {
        jsonData = new HashMap<>();
        System.out.println("===============获取段子列表===============");
//        if (Utils.isEmpty(token)) {
//
//            jsonData.put("msg", "token不能为空");
//            jsonData.put("code", "1");
//            return SUCCESS;
//
//        }
        if (Utils.isEmpty(source)) {

            jsonData.put("msg", "source不能为空");
            jsonData.put("code", "1");

            return SUCCESS;

        } else {
            if (!"android".equals(source)) {
                jsonData.put("msg", "source值错误");
                jsonData.put("code", "1");

                return SUCCESS;
            }
        }
        if (Utils.isEmpty(appVersion)) {

            jsonData.put("msg", "appVersion不能为空");
            jsonData.put("code", "1");

            return SUCCESS;

        } else {
            if (!AccountValidatorUtil.isInteger(appVersion)) {
                jsonData.put("msg", "appVersion必须为整数");
                jsonData.put("code", "1");

                return SUCCESS;
            }

        }

//        String sql = "from UserEntity where token = :token";
//        Query query = quarterService.getSf().openSession().createQuery(sql);
//        query.setParameter("token", token);
//        List<UserEntity> list = query.list();
//        if (list != null && list.size() > 0) {
//            if (new Date().getTime() - TimeUtils.getMill(list.get(0).getCreatetime().toString()) > TOKEN_TIME) {
//                jsonData.put("msg", "token超时");
//                jsonData.put("code", "2");
//                token = null;
//                return SUCCESS;
//            }
//        }else{
//            jsonData.put("msg", "用户未登录");
//            jsonData.put("code", "2");
//            token = null;
//            return SUCCESS;
//        }
        if (!AccountValidatorUtil.isInteger(String.valueOf(page))) {
            jsonData.put("code", "1");
            jsonData.put("msg", "page必须是整数");
            return SUCCESS;
        }
        List<Joke> episodesEntities = quarterService.getJokes(page);
        jsonData.put("code", "0");
        jsonData.put("msg", "获取段子列表成功");
        jsonData.put("data", episodesEntities);
        page = 1;
        source = null;
        appVersion = null;
        token = null;

        return SUCCESS;
    }

    public String publishVideo() {
        jsonData = new HashMap<>();
        System.out.println("===============发布作品===============");
        if (Utils.isEmpty(token)) {

            jsonData.put("msg", "token不能为空");
            jsonData.put("code", "1");
            return SUCCESS;

        }
        if (Utils.isEmpty(source)) {

            jsonData.put("msg", "source不能为空");
            jsonData.put("code", "1");

            return SUCCESS;

        } else {
            if (!"android".equals(source)) {
                jsonData.put("msg", "source值错误");
                jsonData.put("code", "1");

                return SUCCESS;
            }
        }
        if (Utils.isEmpty(appVersion)) {

            jsonData.put("msg", "appVersion不能为空");
            jsonData.put("code", "1");

            return SUCCESS;

        } else {
            if (!AccountValidatorUtil.isInteger(appVersion)) {
                jsonData.put("msg", "appVersion必须为整数");
                jsonData.put("code", "1");

                return SUCCESS;
            }

        }

        String sql = "from UserEntity where token = :token";
        Query query = quarterService.getSf().openSession().createQuery(sql);
        query.setParameter("token", token);
        List<UserEntity> list = query.list();
        if (list != null && list.size() > 0) {
            if (new Date().getTime() - TimeUtils.getMill(list.get(0).getCreatetime().toString()) > TOKEN_TIME) {
                jsonData.put("msg", "token超时");
                jsonData.put("code", "2");
                token = null;
                return SUCCESS;
            }
        } else {
            jsonData.put("msg", "用户未登录");
            jsonData.put("code", "2");
            token = null;
            return SUCCESS;
        }
        if (Utils.isEmpty(uid)) {

            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！用户id不能为空");

            return SUCCESS;

        }

        if (Utils.isEmpty(uid)) {
            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！用户id不能为空");
            return SUCCESS;
        }

        if (videoFile == null) {
            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！视频文件不能为空");
            return SUCCESS;
        }

        if (Utils.isEmpty(videoFileContentType)) {
            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！视频文件未上传，ContentType不能为null");
            return SUCCESS;
        }

        if (Utils.isEmpty(videoFileFileName)) {
            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！视频文件未上传，文件名称不能为null");
            return SUCCESS;
        }
        if (coverFile == null) {
            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！视频封面文件不能为空");
            return SUCCESS;
        }

        if (Utils.isEmpty(coverFileContentType)) {
            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！视频封面文件未上传，ContentType不能为null");
            return SUCCESS;
        }

        if (Utils.isEmpty(coverFileFileName)) {
            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！视频封面文件未上传，文件名称不能为null");
            return SUCCESS;
        }


        if (videoFile != null && !videoFile.isFile()) {
            jsonData.put("code", "1");
            jsonData.put("msg", "视频文件格式不正确");
            return SUCCESS;
        }
        if (coverFile != null && !coverFile.isFile()) {
            jsonData.put("code", "1");
            jsonData.put("msg", "视频封面文件格式不正确");
            return SUCCESS;
        }
        String realpath = ServletActionContext.getServletContext().getRealPath("/images/quarter");
        try {
            String videoPath = realpath + "/" + videoFileFileName;
            String coverPath = realpath + "/" + coverFileFileName;
            FileUtils.copyFile(videoFile, new File(videoPath));
            FileUtils.copyFile(coverFile, new File(coverPath));
            WorksEntity worksEntity = new WorksEntity();
            worksEntity.setLatitude(latitude);
            worksEntity.setLongitude(longitude);
            worksEntity.setUid(Long.parseLong(uid));
            worksEntity.setWorkDesc(videoDesc);
            worksEntity.setVideoUrl(Constants.IMAGES_PATH + videoFileFileName);
            worksEntity.setCover(Constants.IMAGES_PATH + coverFileFileName);
            quarterService.publishVideo(worksEntity);
            jsonData.put("code", "0");
            jsonData.put("msg", "发布成功");
            uid = null;
            latitude = null;
            longitude = null;
            videoFile = null;
            coverFile = null;
            coverFileFileName = null;
            videoFileFileName = null;
            videoDesc = null;
            return SUCCESS;
        } catch (IOException e) {
            e.printStackTrace();
        }


        uid = null;
        latitude = null;
        longitude = null;
        videoFile = null;
        coverFile = null;
        coverFileFileName = null;
        videoFileFileName = null;
        videoDesc = null;
        token = null;
        appVersion = null;
        source = null;


        return SUCCESS;
    }

    public String getVideos() {
        jsonData = new HashMap<>();
        System.out.println("===============获取作品列表===============");
        if (Utils.isEmpty(token)) {

            jsonData.put("msg", "token不能为空");
            jsonData.put("code", "1");
            return SUCCESS;

        }
        if (Utils.isEmpty(source)) {

            jsonData.put("msg", "source不能为空");
            jsonData.put("code", "1");

            return SUCCESS;

        } else {
            if (!"android".equals(source)) {
                jsonData.put("msg", "source值错误");
                jsonData.put("code", "1");

                return SUCCESS;
            }
        }
        if (Utils.isEmpty(appVersion)) {

            jsonData.put("msg", "appVersion不能为空");
            jsonData.put("code", "1");

            return SUCCESS;

        } else {
            if (!AccountValidatorUtil.isInteger(appVersion)) {
                jsonData.put("msg", "appVersion必须为整数");
                jsonData.put("code", "1");

                return SUCCESS;
            }

        }

        String sql = "from UserEntity where token = :token";
        Query query = quarterService.getSf().openSession().createQuery(sql);
        query.setParameter("token", token);
        List<UserEntity> list = query.list();
        if (list != null && list.size() > 0) {
            if (new Date().getTime() - TimeUtils.getMill(list.get(0).getCreatetime().toString()) > TOKEN_TIME) {
                jsonData.put("msg", "token超时");
                jsonData.put("code", "2");
                token = null;
                return SUCCESS;
            }
        } else {
            jsonData.put("msg", "用户未登录");
            jsonData.put("code", "2");
            token = null;
            return SUCCESS;
        }
        if (!AccountValidatorUtil.isInteger(String.valueOf(page))) {
            jsonData.put("code", "1");
            jsonData.put("msg", "page必须是整数");
            return SUCCESS;
        }

        List<WorkBean> workBeans = quarterService.getWorks(page, type);
        jsonData.put("code", "0");
        jsonData.put("msg", "获取作品列表成功");
        jsonData.put("data", workBeans);
        page = -1;
        source = null;
        appVersion = null;
        token = null;

        return SUCCESS;
    }


    public String getUserVideos() {
        jsonData = new HashMap<>();
        System.out.println("===============获取作品列表===============");
        if (Utils.isEmpty(token)) {

            jsonData.put("msg", "token不能为空");
            jsonData.put("code", "1");
            return SUCCESS;

        }
        if (Utils.isEmpty(source)) {

            jsonData.put("msg", "source不能为空");
            jsonData.put("code", "1");

            return SUCCESS;

        } else {
            if (!"android".equals(source)) {
                jsonData.put("msg", "source值错误");
                jsonData.put("code", "1");

                return SUCCESS;
            }
        }
        if (Utils.isEmpty(appVersion)) {

            jsonData.put("msg", "appVersion不能为空");
            jsonData.put("code", "1");

            return SUCCESS;

        } else {
            if (!AccountValidatorUtil.isInteger(appVersion)) {
                jsonData.put("msg", "appVersion必须为整数");
                jsonData.put("code", "1");

                return SUCCESS;
            }

        }

        String sql = "from UserEntity where token = :token";
        Query query = quarterService.getSf().openSession().createQuery(sql);
        query.setParameter("token", token);
        List<UserEntity> list = query.list();
        if (list != null && list.size() > 0) {
            if (new Date().getTime() - TimeUtils.getMill(list.get(0).getCreatetime().toString()) > TOKEN_TIME) {
                jsonData.put("msg", "token超时");
                jsonData.put("code", "2");
                token = null;
                return SUCCESS;
            }
        } else {
            jsonData.put("msg", "用户未登录");
            jsonData.put("code", "2");
            token = null;
            return SUCCESS;
        }
        if (!AccountValidatorUtil.isInteger(String.valueOf(page))) {
            jsonData.put("code", "1");
            jsonData.put("msg", "page必须是整数");
            return SUCCESS;
        }

        if (Utils.isEmpty(uid)) {

            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！用户id不能为空");

            return SUCCESS;

        }

        List<WorksEntity> workBeans = quarterService.getUserWorks(page, uid);
        jsonData.put("code", "0");
        jsonData.put("msg", "获取作品列表成功");
        jsonData.put("data", workBeans);
        page = -1;

        source = null;
        appVersion = null;
        token = null;

        return SUCCESS;
    }


    public String follow() {
        jsonData = new HashMap<>();
        System.out.println("===============发布段子===============");
        if (Utils.isEmpty(token)) {

            jsonData.put("msg", "token不能为空");
            jsonData.put("code", "1");
            return SUCCESS;

        }
        if (Utils.isEmpty(source)) {

            jsonData.put("msg", "source不能为空");
            jsonData.put("code", "1");

            return SUCCESS;

        } else {
            if (!"android".equals(source)) {
                jsonData.put("msg", "source值错误");
                jsonData.put("code", "1");

                return SUCCESS;
            }
        }
        if (Utils.isEmpty(appVersion)) {

            jsonData.put("msg", "appVersion不能为空");
            jsonData.put("code", "1");

            return SUCCESS;

        } else {
            if (!AccountValidatorUtil.isInteger(appVersion)) {
                jsonData.put("msg", "appVersion必须为整数");
                jsonData.put("code", "1");

                return SUCCESS;
            }

        }

        String sql = "from UserEntity where token = :token";
        Query query = quarterService.getSf().openSession().createQuery(sql);
        query.setParameter("token", token);
        List<UserEntity> list = query.list();
        if (list != null && list.size() > 0) {
            if (new Date().getTime() - TimeUtils.getMill(list.get(0).getCreatetime().toString()) > TOKEN_TIME) {
                jsonData.put("msg", "token超时");
                jsonData.put("code", "2");
                token = null;
                return SUCCESS;
            }
        } else {
            jsonData.put("msg", "用户未登录");
            jsonData.put("code", "2");
            token = null;
            return SUCCESS;
        }
        if (Utils.isEmpty(uid)) {

            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！用户id不能为空");

            return SUCCESS;

        }

        if (Utils.isEmpty(followId)) {

            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！关注用户id不能为空");

            return SUCCESS;

        }

        String sql2 = "from FollowEntity where uid = :uid and followId = :followId";
        Query query2 = getQuarterService().getSf().openSession().createQuery(sql2);
        query2.setParameter("uid", Long.parseLong(uid));
        query2.setParameter("followId", Long.parseLong(followId));
        List<FollowEntity> list2 = query.list();

        if (list2 != null && list2.size() > 0) {
            jsonData.put("code", "1");
            jsonData.put("msg", "此用户已关注");
            return SUCCESS;
        }


        FollowEntity followEntity = new FollowEntity();
        followEntity.setUid(Long.parseLong(uid));
        followEntity.setFollowId(Long.parseLong(followId));

        quarterService.follow(followEntity);

        jsonData.put("code", "0");
        jsonData.put("msg", "关注成功");

        uid = null;
        followId = null;
        token = null;
        appVersion = null;
        source = null;


        return SUCCESS;
    }

    /**
     * 获取关注用户列表
     *
     * @return
     */
    public String getFollowUsers() {
        jsonData = new HashMap<>();
        System.out.println("===============获取关注用户列表===============");
        if (Utils.isEmpty(token)) {

            jsonData.put("msg", "token不能为空");
            jsonData.put("code", "1");
            return SUCCESS;

        }
        if (Utils.isEmpty(source)) {

            jsonData.put("msg", "source不能为空");
            jsonData.put("code", "1");

            return SUCCESS;

        } else {
            if (!"android".equals(source)) {
                jsonData.put("msg", "source值错误");
                jsonData.put("code", "1");

                return SUCCESS;
            }
        }
        if (Utils.isEmpty(appVersion)) {

            jsonData.put("msg", "appVersion不能为空");
            jsonData.put("code", "1");

            return SUCCESS;

        } else {
            if (!AccountValidatorUtil.isInteger(appVersion)) {
                jsonData.put("msg", "appVersion必须为整数");
                jsonData.put("code", "1");

                return SUCCESS;
            }

        }

        String sql = "from UserEntity where token = :token";
        Query query = quarterService.getSf().openSession().createQuery(sql);
        query.setParameter("token", token);
        List<UserEntity> list = query.list();
        if (list != null && list.size() > 0) {
            if (new Date().getTime() - TimeUtils.getMill(list.get(0).getCreatetime().toString()) > TOKEN_TIME) {
                jsonData.put("msg", "token超时");
                jsonData.put("code", "2");
                token = null;
                return SUCCESS;
            }
        } else {
            jsonData.put("msg", "用户未登录");
            jsonData.put("code", "2");
            token = null;
            return SUCCESS;
        }
        if (!AccountValidatorUtil.isInteger(String.valueOf(page))) {
            jsonData.put("code", "1");
            jsonData.put("msg", "page必须是整数");
            return SUCCESS;
        }

        if (Utils.isEmpty(uid)) {

            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！用户id不能为空");

            return SUCCESS;

        }

        List<UserEntity> userEntities = quarterService.getFollowUsers(uid);
        jsonData.put("code", "0");
        jsonData.put("msg", "获取关注用户列表成功");
        jsonData.put("data", userEntities);
        page = -1;
        uid = null;
        source = null;
        appVersion = null;

        return SUCCESS;
    }

    /**
     * 收藏
     *
     * @return
     */
    public String addFavorite() {
        jsonData = new HashMap<>();
        System.out.println("===============收藏===============");
        if (Utils.isEmpty(token)) {

            jsonData.put("msg", "token不能为空");
            jsonData.put("code", "1");
            return SUCCESS;

        }
        if (Utils.isEmpty(source)) {

            jsonData.put("msg", "source不能为空");
            jsonData.put("code", "1");

            return SUCCESS;

        } else {
            if (!"android".equals(source)) {
                jsonData.put("msg", "source值错误");
                jsonData.put("code", "1");

                return SUCCESS;
            }
        }
        if (Utils.isEmpty(appVersion)) {

            jsonData.put("msg", "appVersion不能为空");
            jsonData.put("code", "1");

            return SUCCESS;

        } else {
            if (!AccountValidatorUtil.isInteger(appVersion)) {
                jsonData.put("msg", "appVersion必须为整数");
                jsonData.put("code", "1");

                return SUCCESS;
            }

        }

        String sql = "from UserEntity where token = :token";
        Query query = quarterService.getSf().openSession().createQuery(sql);
        query.setParameter("token", token);
        List<UserEntity> list = query.list();
        if (list != null && list.size() > 0) {
            if (new Date().getTime() - TimeUtils.getMill(list.get(0).getCreatetime().toString()) > TOKEN_TIME) {
                jsonData.put("msg", "token超时");
                jsonData.put("code", "2");
                token = null;
                return SUCCESS;
            }
        } else {
            jsonData.put("msg", "用户未登录");
            jsonData.put("code", "2");
            token = null;
            return SUCCESS;
        }
        if (!AccountValidatorUtil.isInteger(String.valueOf(page))) {
            jsonData.put("code", "1");
            jsonData.put("msg", "page必须是整数");
            return SUCCESS;
        }

        if (Utils.isEmpty(uid)) {

            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！用户id不能为空");

            return SUCCESS;

        }

        if (Utils.isEmpty(wid)) {

            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！作品id不能为空");

            return SUCCESS;

        }

        String sql2 = "from WorkfavoriteEntity where uid = :uid and wid = :wid";
        Query query2 = getQuarterService().getSf().openSession().createQuery(sql2);
        query2.setParameter("uid", Long.parseLong(uid));
        query2.setParameter("wid", Long.parseLong(wid));
        List<WorkfavoriteEntity> list2 = query2.list();

        if (list2 != null && list2.size() > 0) {
            jsonData.put("code", "1");
            jsonData.put("msg", "已收藏");
            return SUCCESS;
        }

        WorkfavoriteEntity workfavoriteEntity = new WorkfavoriteEntity();
        workfavoriteEntity.setUid(Long.parseLong(uid));
        workfavoriteEntity.setWid(Long.parseLong(wid));
        quarterService.addFavorite(workfavoriteEntity);

        jsonData.put("code", "0");
        jsonData.put("msg", "收藏成功");

        uid = null;
        wid = null;
        token = null;
        appVersion = null;
        source = null;

        return SUCCESS;
    }

    /**
     * 取消收藏
     *
     * @return
     */
    public String cancelFavorite() {
        jsonData = new HashMap<>();
        System.out.println("===============取消收藏===============");
        if (Utils.isEmpty(token)) {

            jsonData.put("msg", "token不能为空");
            jsonData.put("code", "1");
            return SUCCESS;

        }
        if (Utils.isEmpty(source)) {

            jsonData.put("msg", "source不能为空");
            jsonData.put("code", "1");

            return SUCCESS;

        } else {
            if (!"android".equals(source)) {
                jsonData.put("msg", "source值错误");
                jsonData.put("code", "1");

                return SUCCESS;
            }
        }
        if (Utils.isEmpty(appVersion)) {

            jsonData.put("msg", "appVersion不能为空");
            jsonData.put("code", "1");

            return SUCCESS;

        } else {
            if (!AccountValidatorUtil.isInteger(appVersion)) {
                jsonData.put("msg", "appVersion必须为整数");
                jsonData.put("code", "1");

                return SUCCESS;
            }

        }

        String sql = "from UserEntity where token = :token";
        Query query = quarterService.getSf().openSession().createQuery(sql);
        query.setParameter("token", token);
        List<UserEntity> list = query.list();
        if (list != null && list.size() > 0) {
            if (new Date().getTime() - TimeUtils.getMill(list.get(0).getCreatetime().toString()) > TOKEN_TIME) {
                jsonData.put("msg", "token超时");
                jsonData.put("code", "2");
                token = null;
                return SUCCESS;
            }
        } else {
            jsonData.put("msg", "用户未登录");
            jsonData.put("code", "2");
            token = null;
            return SUCCESS;
        }
        if (!AccountValidatorUtil.isInteger(String.valueOf(page))) {
            jsonData.put("code", "1");
            jsonData.put("msg", "page必须是整数");
            return SUCCESS;
        }

        if (Utils.isEmpty(uid)) {

            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！用户id不能为空");

            return SUCCESS;

        }

        if (Utils.isEmpty(wid)) {

            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！作品id不能为空");

            return SUCCESS;

        }

        String sql2 = "from WorkfavoriteEntity where uid = :uid and wid = :wid";
        Query query2 = getQuarterService().getSf().openSession().createQuery(sql2);
        query2.setParameter("uid", Long.parseLong(uid));
        query2.setParameter("wid", Long.parseLong(wid));
        List<WorkfavoriteEntity> list2 = query2.list();

        if (list2 != null && list2.size() > 0) {

            WorkfavoriteEntity workfavoriteEntity = list2.get(0);
            quarterService.cancelFavorite(workfavoriteEntity);

            jsonData.put("code", "0");
            jsonData.put("msg", "已取消");
            return SUCCESS;
        }


        uid = null;
        wid = null;
        token = null;
        appVersion = null;
        source = null;

        return SUCCESS;
    }


    /**
     * 点赞
     *
     * @return
     */
    public String praise() {
        jsonData = new HashMap<>();
        System.out.println("===============点赞===============");
        if (Utils.isEmpty(token)) {

            jsonData.put("msg", "token不能为空");
            jsonData.put("code", "1");
            return SUCCESS;

        }
        if (Utils.isEmpty(source)) {

            jsonData.put("msg", "source不能为空");
            jsonData.put("code", "1");

            return SUCCESS;

        } else {
            if (!"android".equals(source)) {
                jsonData.put("msg", "source值错误");
                jsonData.put("code", "1");

                return SUCCESS;
            }
        }
        if (Utils.isEmpty(appVersion)) {

            jsonData.put("msg", "appVersion不能为空");
            jsonData.put("code", "1");

            return SUCCESS;

        } else {
            if (!AccountValidatorUtil.isInteger(appVersion)) {
                jsonData.put("msg", "appVersion必须为整数");
                jsonData.put("code", "1");

                return SUCCESS;
            }

        }

        String sql = "from UserEntity where token = :token";
        Query query = quarterService.getSf().openSession().createQuery(sql);
        query.setParameter("token", token);
        List<UserEntity> list = query.list();
        if (list != null && list.size() > 0) {
            if (new Date().getTime() - TimeUtils.getMill(list.get(0).getCreatetime().toString()) > TOKEN_TIME) {
                jsonData.put("msg", "token超时");
                jsonData.put("code", "2");
                token = null;
                return SUCCESS;
            }
        } else {
            jsonData.put("msg", "用户未登录");
            jsonData.put("code", "2");
            token = null;
            return SUCCESS;
        }
        if (!AccountValidatorUtil.isInteger(String.valueOf(page))) {
            jsonData.put("code", "1");
            jsonData.put("msg", "page必须是整数");
            return SUCCESS;
        }

        if (Utils.isEmpty(uid)) {

            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！用户id不能为空");

            return SUCCESS;

        }

        if (Utils.isEmpty(wid)) {

            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！作品id不能为空");

            return SUCCESS;

        }

        String sql2 = "from WorkpraiseEntity where uid = :uid and wid = :wid";
        Query query2 = getQuarterService().getSf().openSession().createQuery(sql2);
        query2.setParameter("uid", Long.parseLong(uid));
        query2.setParameter("wid", Long.parseLong(wid));
        List<WorkpraiseEntity> list2 = query2.list();

        if (list2 != null && list2.size() > 0) {
            jsonData.put("code", "1");
            jsonData.put("msg", "已点赞过");
            return SUCCESS;
        }

        WorkpraiseEntity workpraiseEntity = new WorkpraiseEntity();
        workpraiseEntity.setUid(Long.parseLong(uid));
        workpraiseEntity.setWid(Long.parseLong(wid));
        quarterService.praise(workpraiseEntity);

        jsonData.put("code", "0");
        jsonData.put("msg", "已点赞");

        uid = null;
        wid = null;
        token = null;
        appVersion = null;
        source = null;

        return SUCCESS;
    }


    /**
     * 评论
     *
     * @return
     */
    public String comment() {
        jsonData = new HashMap<>();
        System.out.println("===============评论===============");
        if (Utils.isEmpty(token)) {

            jsonData.put("msg", "token不能为空");
            jsonData.put("code", "1");
            return SUCCESS;

        }
        if (Utils.isEmpty(source)) {

            jsonData.put("msg", "source不能为空");
            jsonData.put("code", "1");

            return SUCCESS;

        } else {
            if (!"android".equals(source)) {
                jsonData.put("msg", "source值错误");
                jsonData.put("code", "1");

                return SUCCESS;
            }
        }
        if (Utils.isEmpty(appVersion)) {

            jsonData.put("msg", "appVersion不能为空");
            jsonData.put("code", "1");

            return SUCCESS;

        } else {
            if (!AccountValidatorUtil.isInteger(appVersion)) {
                jsonData.put("msg", "appVersion必须为整数");
                jsonData.put("code", "1");

                return SUCCESS;
            }

        }

        String sql = "from UserEntity where token = :token";
        Query query = quarterService.getSf().openSession().createQuery(sql);
        query.setParameter("token", token);
        List<UserEntity> list = query.list();
        if (list != null && list.size() > 0) {
            if (new Date().getTime() - TimeUtils.getMill(list.get(0).getCreatetime().toString()) > TOKEN_TIME) {
                jsonData.put("msg", "token超时");
                jsonData.put("code", "2");
                token = null;
                return SUCCESS;
            }
        } else {
            jsonData.put("msg", "用户未登录");
            jsonData.put("code", "2");
            token = null;
            return SUCCESS;
        }


        if (Utils.isEmpty(uid)) {

            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！用户id不能为空");

            return SUCCESS;

        }

        if (Utils.isEmpty(wid)) {

            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！作品id不能为空");

            return SUCCESS;

        }
        if (Utils.isEmpty(content)) {

            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！评论内容不能为空");

            return SUCCESS;

        }


        CommentsEntity commentsEntity = new CommentsEntity();
        commentsEntity.setUid(Long.parseLong(uid));
        commentsEntity.setWid(Long.parseLong(wid));
        commentsEntity.setContent(content);
        quarterService.comment(commentsEntity);

        jsonData.put("code", "0");
        jsonData.put("msg", "评论成功");

        uid = null;
        wid = null;
        token = null;
        appVersion = null;
        source = null;

        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {


        return SUCCESS;
    }


    /**
     * 为什么直接调用不起作用
     *
     * @return
     */
    public String verifyParams() {

//        System.out.println("TOKEN================="+token);
        if (Utils.isEmpty(token)) {

            jsonData.put("msg", "token不能为空");
            jsonData.put("code", "1");
            return SUCCESS;

        }
        if (Utils.isEmpty(source)) {

            jsonData.put("msg", "source不能为空");
            jsonData.put("code", "1");

            return SUCCESS;

        }
        if (Utils.isEmpty(appVersion)) {

            jsonData.put("msg", "appVersion不能为空");
            jsonData.put("code", "1");

            return SUCCESS;

        }

        String sql = "from UserEntity where token = :token";
        Query query = quarterService.getSf().openSession().createQuery(sql);
        query.setParameter("token", token);
        List<UserEntity> list = query.list();
        if (list != null && list.size() > 0) {
            if (new Date().getTime() - TimeUtils.getMill(list.get(0).getCreatetime().toString()) > TOKEN_TIME) {
                jsonData.put("msg", "token超时");
                jsonData.put("code", "2");
                token = null;
                return SUCCESS;
            }
        }

        return SUCCESS;
    }


    /**
     * 注册
     *
     * @return
     */
    public String reg() {
        jsonData = new HashMap<>();
        System.out.println("===============用户注册请求：mobile：" + mobile + "   password：" + password + "===============");

        if (Utils.isEmpty(regType)) {
            jsonData.put("msg", "请上传登录类型regType");
            jsonData.put("code", "1");
            return SUCCESS;
        }

        if ("0".equals(regType)) {//本地注册
            if (Utils.isEmpty(mobile)) {
                jsonData.put("msg", "手机号码不能为空");
                jsonData.put("code", "1");
                return SUCCESS;
            }
            if (!AccountValidatorUtil.isInteger(mobile)) {
                jsonData.put("msg", "请输入正确的手机号码");
                jsonData.put("code", "1");
                jsonData.put("data", "{}");
                return SUCCESS;
            }
            if (Utils.isEmpty(password)) {
                jsonData.put("msg", "密码不能为空");
                jsonData.put("code", "1");
                return SUCCESS;
            }
            if (!AccountValidatorUtil.isPassword(password)) {
                jsonData.put("msg", "密码格式有问题，不能少于6位数");
                jsonData.put("code", "1");
                jsonData.put("data", "{}");
                return SUCCESS;
            }

            if (quarterService.isExist(mobile)) {
                jsonData.put("msg", "天呢！用户已注册");
                jsonData.put("code", "1");
                jsonData.put("data", "{}");
                System.out.println("===============用户已注册：mobile：" + mobile + "===============");


            } else {
                UserEntity userEntity = new UserEntity();
                userEntity.setMobile(mobile);
                userEntity.setPassword(password);
                userEntity.setUsername(mobile);
                quarterService.addUser(userEntity);
                System.out.println("===============用户注册成功：mobile：" + mobile + "   password：" + password + "===============");

                String sql = "from UserEntity where mobile = :mobile";
                Query query = quarterService.getSf().openSession().createQuery(sql);
                query.setParameter("mobile", mobile);
                List<UserEntity> userEntityList = query.list();
                if (userEntityList != null && userEntityList.size() > 0) {
                    UserEntity userEntity1 = userEntityList.get(0);
                    //uid md5 加密生成appkey，uid和password用aes加密

                    userEntity1.setAppkey(MD5Tools.get16Md5Value(String.valueOf(userEntity1.getUid())));
                    String encryptResultStr = AesUtils.parseByte2HexStr(AesUtils.encrypt(String.valueOf(userEntity1.getUid()), userEntity1.getPassword()));
                    userEntity1.setPassword(AesUtils.parseByte2HexStr(AesUtils.encrypt(userEntity1.getPassword(), "kson")));
                    userEntity1.setAppsecret(encryptResultStr);


                    quarterService.updateUser(userEntity1);
                    jsonData.put("msg", "注册成功");
                    jsonData.put("code", "0");
//                        jsonData.put("data", userEntityList.get(0));
                }

            }
        } else if ("1".equals(regType)) {//第三方登录

            if (Utils.isEmpty(userId)) {
                jsonData.put("msg", "第三方登录id不能为空");
                jsonData.put("code", "1");
                return SUCCESS;
            }
            if (Utils.isEmpty(gender)) {
                jsonData.put("msg", "性别不能为空");
                jsonData.put("code", "1");
                return SUCCESS;
            }
            if (Utils.isEmpty(nickname)) {
                jsonData.put("msg", "昵称不能为空");
                jsonData.put("code", "1");
                return SUCCESS;
            }
            if (Utils.isEmpty(icon)) {
                jsonData.put("msg", "头像不能为空");
                jsonData.put("code", "1");
                return SUCCESS;
            }

            if (quarterService.isUserExist(userId)) {
                jsonData.put("msg", "登录成功");
                jsonData.put("code", "0");
                System.out.println("===============用户已注册：mobile：" + mobile + "===============");
                gender = null;
                nickname = null;
                icon = null;
                userId = null;
                return SUCCESS;


            } else {
                UserEntity userEntity = new UserEntity();
                userEntity.setNickname(nickname);
                userEntity.setIcon(icon);
                userEntity.setGender(Byte.valueOf(gender));
                userEntity.setUserId(userId);
                quarterService.addUser(userEntity);
                System.out.println("===============用户注册成功：mobile：" + mobile + "   password：" + password + "===============");

                String sql = "from UserEntity where userId = :userId";
                Query query = quarterService.getSf().openSession().createQuery(sql);
                query.setParameter("userId", userId);
                List<UserEntity> userEntityList = query.list();
                if (userEntityList != null && userEntityList.size() > 0) {
                    UserEntity userEntity1 = userEntityList.get(0);
                    //uid md5 加密生成appkey，uid和password用aes加密

                    userEntity1.setAppkey(MD5Tools.get16Md5Value(String.valueOf(userEntity1.getUid())));
                    String encryptResultStr = AesUtils.parseByte2HexStr(AesUtils.encrypt(String.valueOf(userEntity1.getUid()), userEntity1.getUserId()));
//                    userEntity1.setPassword(AesUtils.parseByte2HexStr(AesUtils.encrypt(userEntity1.getPassword(),"kson")));
                    userEntity1.setAppsecret(encryptResultStr);


                    quarterService.updateUser(userEntity1);
                    jsonData.put("msg", "注册成功");
                    jsonData.put("code", "0");
                    jsonData.put("data", userEntityList.get(0));
                    nickname = null;
                    icon = null;
                    gender = null;
                    userId = null;
                    return SUCCESS;
                }

            }

        }

        return SUCCESS;


    }

    /**
     * 点赞
     *
     * @return
     */
    public String resetPass() {
        jsonData = new HashMap<>();
        System.out.println("===============点赞===============");
        if (Utils.isEmpty(uid)) {

            jsonData.put("msg", "用户id不能为空");
            jsonData.put("code", "1");
            return SUCCESS;

        }
        if (Utils.isEmpty(oldPassword)) {

            jsonData.put("msg", "旧密码不能为空");
            jsonData.put("code", "1");

            return SUCCESS;

        }

        String password = AesUtils.parseByte2HexStr(AesUtils.encrypt(oldPassword, "kson"));

//        //解密
//        byte[] decryptFrom = AesUtils.parseHexStr2Byte(oldPassword);
//        byte[] decryptResult = AesUtils.decrypt(decryptFrom,"kson");//kson为密码加密密钥
//            System.out.println("解密后：" + new String(decryptResult));
//            String decryptPass = new String(decryptResult);
        String sql = "from UserEntity where uid = :uid and password = :password";
        Query query = quarterService.getSf().openSession().createQuery(sql);
        query.setParameter("uid", Long.parseLong(uid));
        query.setParameter("password", password);
        List<UserEntity> list = query.list();
        if (list != null && list.size() > 0) {

            UserEntity userEntity = list.get(0);
            if (Utils.isEmpty(newPassword)) {

                jsonData.put("msg", "新密码不能为空");
                jsonData.put("code", "1");

                return SUCCESS;

            }
            userEntity.setPassword(newPassword);
            String encryptResultStr = AesUtils.parseByte2HexStr(AesUtils.encrypt(String.valueOf(userEntity.getUid()), userEntity.getPassword()));

            userEntity.setPassword(AesUtils.parseByte2HexStr(AesUtils.encrypt(userEntity.getPassword(), "kson")));
            userEntity.setAppsecret(encryptResultStr);


            quarterService.updateUser(userEntity);
            jsonData.put("msg", "密码修改成功");
            jsonData.put("code", "0");
            uid = null;
            oldPassword = null;
            newPassword = null;
            return SUCCESS;
        } else {
            jsonData.put("msg", "旧密码不正确");
            jsonData.put("code", "1");
            token = null;
            uid = null;
            oldPassword = null;
            newPassword = null;
            return SUCCESS;
        }


    }

    /**
     * 获取视频详情
     */
    public String getVideoDetail() {
        jsonData = new HashMap<>();
        if (Utils.isEmpty(wid)) {
            jsonData.put("code", "1");
            jsonData.put("msg", "wid不能为空");
            return SUCCESS;
        }

        WorksEntity worksEntity = quarterService.getVideoDetail(wid);

        jsonData.put("code", "0");
        jsonData.put("msg", "获取详情成功");
        jsonData.put("data", worksEntity);

        wid = null;
        return SUCCESS;


    }

}
