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
    private String num ;
    private String selected ;

    //订单
    private String price;
    private String orderId;
    private int status;

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getSelected() {
        return selected;
    }

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

    public void setNum(String num) {
        this.num = num;
    }

    public String getNum() {
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
                cart.setSellerid(String.valueOf(carEntity.getSellerid()));

                System.out.println("CAR:"+carEntity.getSellerid());

                List<Product> productEntityList = new ArrayList<>();


                List<CarEntity> carEntityList1 = getUser().getSf().openSession().createQuery("from CarEntity where sellerid = '" + carEntity.getSellerid() + "' and uid = '"+uid+"'").list();


                for (CarEntity entity : carEntityList1) {

                    System.out.println("selleid"+entity.getSellerid());
                    System.out.println("pid:"+entity.getPid());
                    ProductEntity productEntity = getProduct(String.valueOf(entity.getPid()));
                    Product product = new Product();
                    product.setBargainPrice(productEntity.getBargainPrice());
                    product.setCreatetime(productEntity.getCreatetime());
                    product.setDetailUrl(productEntity.getDetailUrl());
                    product.setImages(productEntity.getImages());
                    product.setNum(entity.getNum());
                    product.setPid(entity.getPid());
                    product.setPrice(productEntity.getPrice());
                    product.setPscid(productEntity.getPscid());
                    product.setSelected(entity.getSelected());
                    product.setSellerid(productEntity.getSellerid());
                    product.setSubhead(productEntity.getSubhead());
                    product.setTitle(productEntity.getTitle());

                    productEntityList.add(product);

                }

                cart.setList(productEntityList);
                cartList.add(cart);

            }


            if (cartList!=null){
                jsonData.put("code","0");
                jsonData.put("msg","请求成功");
                jsonData.put("data",cartList);
                return SUCCESS;
            }



        }




        uid = null;
        jsonData = null;
        return SUCCESS;
    }


    /**
     * 更新购物车
     * @return
     */
    public String updateCarts(){

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
        if (selected==null){
            jsonData.put("msg","选中状态不能为空");
            jsonData.put("code","1");
            return SUCCESS;
        }
        if (num==null){
            jsonData.put("msg","商品数量不能为空");
            jsonData.put("code","1");
            return SUCCESS;
        }


        List<CarEntity> carEntityList = getUser().getSf().openSession().createQuery("from CarEntity where sellerid = '" + sellerid + "' and uid = '"+uid+"' and pid = '"+pid+"'").list();

        if (carEntityList!=null&&carEntityList.size()>0){

            CarEntity carEntity = carEntityList.get(0);
            if (carEntity!=null){
                carEntity.setNum(Integer.parseInt(num));
                carEntity.setSelected(Integer.parseInt(selected));

                getUser().updateCar(carEntity);
                jsonData.put("code","0");
                jsonData.put("msg","success");

            }
        }


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

    /**
     * 获取商品详情
     * @param id
     */
    private ProductEntity getProduct(String  id){
        List<ProductEntity> productEntityList = getUser().getSf().openSession().createQuery("from ProductEntity where pid = '" + id + "'").list();

        if (productEntityList!=null&&productEntityList.size()>0){
            return productEntityList.get(0);
        }
        return  null;

    }


    /**
     * 创建订单
     *
     * @return
     */
    public String createOrder() {
        jsonData = new HashMap<>();

        if (uid==null){
            jsonData.put("msg","用户未登录或用户id为空");
            jsonData.put("code","1");

            return SUCCESS;
        }
        if (price==null){
            jsonData.put("msg","实付价格不能为空");
            jsonData.put("code","1");

            return SUCCESS;
        }

        OrdersEntity orderEntity  = new OrdersEntity();
        orderEntity.setPrice(Double.parseDouble(price));
        orderEntity.setUid(Long.parseLong(uid));
        orderEntity.setStatus(0);

        getUser().createOrder(orderEntity);


        jsonData.put("msg", "订单创建成功");
        jsonData.put("code", "0");


        uid = null;
        price = null;

        return SUCCESS;


    }

    /**
     * 更新订单
     *
     * @return
     */
    public String updateOrder() {
        jsonData = new HashMap<>();

        if (uid==null){
            jsonData.put("msg","用户未登录或用户id为空");
            jsonData.put("code","1");

            return SUCCESS;
        }
        if (orderId==null){
            jsonData.put("msg","订单id不能为空");
            jsonData.put("code","1");

            return SUCCESS;
        }

        List<OrdersEntity> orderEntityList = getUser().getSf().openSession().createQuery("from OrdersEntity where orderid = '" + orderId + "'").list();


        if (orderEntityList!=null&&orderEntityList.size()>0){

            OrdersEntity orderEntity = orderEntityList.get(0);
            if (orderEntity!=null){
                orderEntity.setStatus(status);
                getUser().updateOrder(orderEntity);

                jsonData.put("msg", "订单状态修改成功");
                jsonData.put("code", "0");
            }

        }

        uid = null;
        status = 0;

        return SUCCESS;


    }

    /**
     * 获取某分类下的商品列表
     * @return
     */
    public String getOrders(){
        jsonData = new HashMap<>();
        if (uid==null){

            jsonData.put("code","1");
            jsonData.put("msg","用户id不能为空");

            return SUCCESS;

        }

        List<OrdersEntity> ordersEntities = getUser().getSf().openSession().createQuery("from OrdersEntity where uid = '" + uid + "'").list();


        if(ordersEntities!=null){
            jsonData.put("code","0");
            jsonData.put("msg","请求成功");
            jsonData.put("data",ordersEntities);

        }
        uid = null;
        return SUCCESS;
    }
}
