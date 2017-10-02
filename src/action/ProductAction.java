package action;

import bean.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import service.UserImpl;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.*;

public class ProductAction extends ActionSupport {

    private String pid;
    private UserImpl user;
    private Map<String, Object> jsonData;
    private String cid;
    private String  pscid;
    private String uid;
    private String sellerid;
    private int num = 1;

    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }

    public Map<String, Object> getJsonData() {
        return jsonData;
    }

    public void setUser(UserImpl user) {
        this.user = user;
    }

    public UserImpl getUser() {
        return user;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCid() {
        return cid;
    }

    public void setPscid(String pscid) {
        this.pscid = pscid;
    }

    public String getPscid() {
        return pscid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    /**
     * 获取分类列表
     * @return
     */
    public String getCatagory() {
        jsonData = new HashMap<>();
        List<Properties> list = getUser().getSf().openSession().createQuery("from CatagoryEntity ").list();
        if (list != null) {
            jsonData.put("code", "0");
            jsonData.put("msg", "");
            jsonData.put("data", list);
        }

        return SUCCESS;
    }


    /**
     * 获取子分类列表
     * @return
     */
    public String getProductCatagory() {
        jsonData = new HashMap<>();
        if (cid==null){
            jsonData.put("code", "0");
            jsonData.put("msg", "分类id不能为空");
            return SUCCESS;
        }
        List<ProductCatagory> list = new ArrayList<>();
        List<ProductcatagoryEntity> productcatagoryEntities = getUser().getSf().openSession().createQuery("from ProductcatagoryEntity where cid = '" + cid + "'").list();

        if (productcatagoryEntities!=null&&productcatagoryEntities.size()>0){

            for (ProductcatagoryEntity productcatagoryEntity : productcatagoryEntities) {
                ProductCatagory productCatagory = new ProductCatagory();
                productCatagory.setCid(cid);
                productCatagory.setPcid(String.valueOf(productcatagoryEntity.getPcid()));
                productCatagory.setName(productcatagoryEntity.getName());
                System.out.println("cname:"+productcatagoryEntity.getName());

                List<ProductsubcatagoryEntity> productsubcatagoryEntityList = getUser().getSf().openSession().createQuery("from ProductsubcatagoryEntity where pcid = '" + productcatagoryEntity.getPcid() + "'").list();
                ;
                productCatagory.setList(productsubcatagoryEntityList);




                list.add(productCatagory);
            }

            if (list!=null){
                jsonData.put("code", "0");
                jsonData.put("msg", "获取子分类成功");
                jsonData.put("data", list);

                return SUCCESS;

            }

        }


        cid = null;

        return SUCCESS;
    }

    /**
     * 获取产品详情
     * @return
     */
    public String getProductDetail() {
        jsonData = new HashMap<>();
        if (pid==null){
            jsonData.put("code", "1");
            jsonData.put("msg", "商品id不能为空");
            return SUCCESS;
        }
        List<ProductEntity> productEntityList = getUser().getSf().openSession().createQuery("from ProductEntity where pid = '" + pid + "'").list();

        if (productEntityList != null&&productEntityList.size()>0) {
            ProductEntity productEntity = productEntityList.get(0);
            jsonData.put("code", "0");
            jsonData.put("msg", "");
            jsonData.put("data", productEntity);
        }

        pid = null;
        return SUCCESS;
    }


    /**
     * 获取某分类下的商品列表
     * @return
     */
    public String getProducts(){
        jsonData = new HashMap<>();
        if (pscid==null){

            jsonData.put("code","1");
            jsonData.put("msg","分类id不能为空");

            return SUCCESS;

        }

        List<ProductEntity> productEntityList = getUser().getSf().openSession().createQuery("from ProductEntity where pscid = '" + pscid + "'").list();


        if(productEntityList!=null){
            jsonData.put("code","0");
            jsonData.put("msg","请求成功");
            jsonData.put("data",productEntityList);

        }
        pscid = null;
        return SUCCESS;
    }



    /**
     * 添加购物车
     *
     * @return
     */
    public String addCart() {
        jsonData = new HashMap<>();

        if (uid==null){
            jsonData.put("msg","用户未登录或用户id为空");
            jsonData.put("code","1");

            return SUCCESS;
        }
        if (pid==null){
            jsonData.put("msg","商品id不能为空");
            jsonData.put("code","1");

            return SUCCESS;
        }

        if (sellerid==null){
            jsonData.put("msg","商家id不能为空");
            jsonData.put("code","1");
            return SUCCESS;
        }


        List<CarEntity> carEntityList = getUser().getSf().openSession().createQuery("from CarEntity where pid = '" + pid + "' and uid = '"+uid+"'").list();

        if (carEntityList!=null&&carEntityList.size()>0){
            CarEntity carEntity = carEntityList.get(0);
            if (carEntity!=null){
                carEntity.setNum(carEntity.getNum()+1);
                getUser().updateCar(carEntity);
            }
        }else{
            CarEntity carEntity = new CarEntity();
            carEntity.setNum(1);
            carEntity.setPid(Long.parseLong(pid));
            carEntity.setSellerid(Long.parseLong(sellerid));
            carEntity.setUid(Long.parseLong(uid));
            getUser().addCar(carEntity);
        }


                jsonData.put("msg", "加购成功");
                jsonData.put("code", "0");


       uid = null;
       sellerid = null;
       pid = null;


        return SUCCESS;


    }

    /**
     * 获取用户的购物车数据
     * @return
     */
    public String getCarts(){
        jsonData = new HashMap<>();
        if (uid==null){

            jsonData.put("code","1");
            jsonData.put("msg","用户id不能为空");

            return SUCCESS;

        }
        List<Cart> cartList = new ArrayList<>();

        List<CarEntity> carEntityList = getUser().getSf().openSession().createQuery("from CarEntity where uid = '" + uid + "' group by sellerid").list();


        if(carEntityList!=null&&carEntityList.size()>0){

            for (CarEntity carEntity : carEntityList) {

                Cart cart = new Cart();
                cart.setSellerName(getCartName(String.valueOf(carEntity.getSellerid())));

                System.out.println("CAR:"+carEntity.getSellerid());


                cartList.add(cart);

            }

            jsonData.put("code","0");
            jsonData.put("msg","请求成功");
            jsonData.put("data",cartList);

        }
       uid = null;
        return SUCCESS;
    }

    /**
     * 获取商家名称
     * @param id
     */
    private String getCartName(String id){

        String name = "";

        List<SellerEntity> sellerEntityList = getUser().getSf().openSession().createQuery("from SellerEntity where sellerid = '" + id + "'").list();

        if (sellerEntityList!=null&&sellerEntityList.size()>0){
            SellerEntity sellerEntity = sellerEntityList.get(0);
            name = sellerEntity.getName();

            return name;
        }
return  name;

    }
}
