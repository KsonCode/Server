package action;

import bean.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.query.Query;
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
    private String pscid;
    private String uid;
    private String sellerid;
    private String num;
    private String selected;

    //分页
    private int pageNum = 10;
    private int page = 1;

    //订单
    private String price;
    private String orderId;
    private int status;

    //搜索
    private String keywords;
    private int sort = 0;

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getSort() {
        return sort;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

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
     *
     * @return
     */
    public String getCatagory() {
        System.out.println("===============获取分类列表请求===============");

        jsonData = new HashMap<>();
        List<Properties> list = getUser().getSf().openSession().createQuery("from CatagoryEntity ").list();
        if (list != null) {
            jsonData.put("code", "0");
            jsonData.put("msg", "");
            jsonData.put("data", list);
            System.out.println("===============获取分类列表成功===============");

        }

        return SUCCESS;
    }


    /**
     * 获取子分类列表
     *
     * @return
     */
    public String getProductCatagory() {
        jsonData = new HashMap<>();
        System.out.println("===============获取子分类列表：cid：" + cid + "===============");

        if (cid == null) {
            jsonData.put("code", "0");
            jsonData.put("msg", "天呢！分类id不能为空");
            return SUCCESS;
        }
        List<ProductCatagory> list = new ArrayList<>();
        String sql1 = "from ProductcatagoryEntity where cid = ?";
        Query query = getUser().getSf().openSession().createQuery(sql1);
        query.setParameter(0, Long.parseLong(cid));
        List<ProductcatagoryEntity> productcatagoryEntities = query.list();
//        List<ProductcatagoryEntity> productcatagoryEntities = getUser().getSf().openSession().createQuery("from ProductcatagoryEntity where cid = '" + cid + "'").list();


        if (productcatagoryEntities != null && productcatagoryEntities.size() > 0) {

            for (ProductcatagoryEntity productcatagoryEntity : productcatagoryEntities) {
                ProductCatagory productCatagory = new ProductCatagory();
                productCatagory.setCid(cid);
                productCatagory.setPcid(String.valueOf(productcatagoryEntity.getPcid()));
                productCatagory.setName(productcatagoryEntity.getName());
                System.out.println("cname:" + productcatagoryEntity.getName());

                String sql2 = "from ProductsubcatagoryEntity where pcid = ?";
                Query query1 = getUser().getSf().openSession().createQuery(sql2);
                query1.setParameter(0, (int) productcatagoryEntity.getPcid());
                List<ProductsubcatagoryEntity> productsubcatagoryEntityList = query1.list();
//                List<ProductsubcatagoryEntity> productsubcatagoryEntityList = getUser().getSf().openSession().createQuery("from ProductsubcatagoryEntity where pcid = '" + productcatagoryEntity.getPcid() + "'").list();
                ;
                productCatagory.setList(productsubcatagoryEntityList);
                list.add(productCatagory);
            }

            if (list != null) {
                jsonData.put("code", "0");
                jsonData.put("msg", "获取子分类成功");
                jsonData.put("data", list);
                System.out.println("===============获取子分类成功：cid：" + cid + "===============");

                return SUCCESS;

            }

        }


        cid = null;

        return SUCCESS;
    }

    /**
     * 获取产品详情
     *
     * @return
     */
    public String getProductDetail() {
        jsonData = new HashMap<>();
        System.out.println("===============商品详情请求：pid：" + pid + "===============");

        if (pid == null) {
            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！商品id不能为空");
            return SUCCESS;
        }
        String sql = "from ProductEntity where pid = ?";
        Query query = getUser().getSf().openSession().createQuery(sql);
        query.setParameter(0, Long.parseLong(pid));
        List<ProductEntity> productEntityList = query.list();
//        List<ProductEntity> productEntityList = getUser().getSf().openSession().createQuery("from ProductEntity where pid = '" + pid + "'").list();

        if (productEntityList != null && productEntityList.size() > 0) {
            ProductEntity productEntity = productEntityList.get(0);
            jsonData.put("code", "0");
            jsonData.put("msg", "");
            jsonData.put("data", productEntity);
            System.out.println("===============商品详情成功：cid：" + cid + "===============");

        }

        pid = null;
        return SUCCESS;
    }


    /**
     * 获取某分类下的商品列表
     *
     * @return
     */
    public String getProducts() {
        jsonData = new HashMap<>();
        System.out.println("===============获取某分类商品列表请求：pscid：" + pscid + "===============");

        if (pscid == null) {

            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！分类id不能为空");

            return SUCCESS;

        }

        String sql = "";
        if (sort == 0) {//moren
            sql = "from ProductEntity where pscid = ?";
        } else if (sort == 1) {//xiaoliang
            sql = "from ProductEntity where pscid = ? order by salenum desc ";
        } else if (sort == 2) {//jiage
            sql = "from ProductEntity where pscid = ? order by price";
        }
        Query query = getUser().getSf().openSession().createQuery(sql);
        query.setParameter(0, Long.parseLong(pscid));
        query.setMaxResults(10);
        System.out.println("page:" + page);
        if (page == 1) {
            query.setFirstResult(0);
        } else {
            query.setFirstResult((page - 1) * 10);
        }

        List<ProductEntity> productEntityList = query.list();

//        List<ProductEntity> productEntityList = getUser().getSf().openSession().createQuery("from ProductEntity where pscid = '" + pscid + "'").list();


        if (productEntityList != null) {
            jsonData.put("code", "0");
            jsonData.put("msg", "请求成功");
            jsonData.put("page", page);
            jsonData.put("data", productEntityList);
            System.out.println("===============获取某分类商品列表成功：pscid：" + pscid + "===============");


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
        System.out.println("===============添加购物车请求：uid：" + uid + "   pid：" + pid + "  sellerid" + sellerid + "===============");


        if (uid == null) {
            jsonData.put("msg", "天呢！用户未登录或用户id不能为空");
            jsonData.put("code", "1");

            return SUCCESS;
        }
        if (pid == null) {
            jsonData.put("msg", "天呢！商品id不能为空");
            jsonData.put("code", "1");

            return SUCCESS;
        }

        if (sellerid == null) {
            jsonData.put("msg", "天呢！商家id不能为空");
            jsonData.put("code", "1");
            return SUCCESS;
        }

        String sql = "from CarEntity where pid = ? and uid = ?";

        Query query = getUser().getSf().openSession().createQuery(sql);
        query.setParameter(0, Long.parseLong(pid));
        query.setParameter(1, Long.parseLong(uid));
        List<CarEntity> carEntityList = query.list();

//        List<CarEntity> carEntityList = getUser().getSf().openSession().createQuery("from CarEntity where pid = '" + pid + "' and uid = '"+uid+"'").list();

        if (carEntityList != null && carEntityList.size() > 0) {
            CarEntity carEntity = carEntityList.get(0);
            if (carEntity != null) {
                carEntity.setNum(carEntity.getNum() + 1);
                getUser().updateCar(carEntity);
            }
        } else {
            CarEntity carEntity = new CarEntity();
            carEntity.setNum(1);
            carEntity.setPid(Long.parseLong(pid));
            carEntity.setSellerid(Long.parseLong(sellerid));
            carEntity.setUid(Long.parseLong(uid));
            getUser().addCar(carEntity);
        }


        jsonData.put("msg", "加购成功");
        jsonData.put("code", "0");
        System.out.println("===============添加购物车成功：uid：" + uid + "   pid：" + pid + "  sellerid" + sellerid + "===============");


        uid = null;
        sellerid = null;
        pid = null;


        return SUCCESS;


    }

    /**
     * 获取用户的购物车数据
     *
     * @return
     */
    public String getCarts() {
        jsonData = new HashMap<>();
        System.out.println("===============获取购物车请求：uid：" + uid + "===============");

        if (uid == null) {

            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！用户id不能为空");

            return SUCCESS;

        }
        List<Cart> cartList = new ArrayList<>();

        String sql = "from CarEntity where uid = ? group by sellerid";

        Query query = getUser().getSf().openSession().createQuery(sql);
        query.setParameter(0, Long.parseLong(uid));
        List<CarEntity> carEntityList = query.list();

//        List<CarEntity> carEntityList = getUser().getSf().openSession().createQuery("from CarEntity where uid = '" + uid + "' group by sellerid").list();


        if (carEntityList != null && carEntityList.size() > 0) {

            for (CarEntity carEntity : carEntityList) {

                Cart cart = new Cart();
                cart.setSellerName(getCartName(String.valueOf(carEntity.getSellerid())));
                cart.setSellerid(String.valueOf(carEntity.getSellerid()));

                System.out.println("CAR:" + carEntity.getSellerid());

                List<Product> productEntityList = new ArrayList<>();

                String sql2 = "from CarEntity where sellerid = ? and uid = ?";
                Query query1 = getUser().getSf().openSession().createQuery(sql2);
                query1.setParameter(0, carEntity.getSellerid());
                query1.setParameter(1, Long.parseLong(uid));
                List<CarEntity> carEntityList1 = query1.list();
//                List<CarEntity> carEntityList1 = getUser().getSf().openSession().createQuery("from CarEntity where sellerid = '" + carEntity.getSellerid() + "' and uid = '"+uid+"'").list();


                for (CarEntity entity : carEntityList1) {

                    System.out.println("selleid" + entity.getSellerid());
                    System.out.println("pid:" + entity.getPid());
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


            if (cartList != null) {
                jsonData.put("code", "0");
                jsonData.put("msg", "请求成功");
                jsonData.put("data", cartList);
                System.out.println("===============获取购物车成功：uid：" + uid + "===============");

                return SUCCESS;
            }


        }


        uid = null;
        jsonData = null;
        return SUCCESS;
    }


    /**
     * 更新购物车
     *
     * @return
     */
    public String updateCarts() {
        System.out.println("===============更新购物车请求：uid：" + uid + "   pid：" + pid + "  sellerid：" + sellerid + "===============");

        jsonData = new HashMap<>();

        if (uid == null) {
            jsonData.put("msg", "天呢！用户未登录或用户id不能为空");
            jsonData.put("code", "1");

            return SUCCESS;
        }
        if (pid == null) {
            jsonData.put("msg", "天呢！商品id不能为空");
            jsonData.put("code", "1");

            return SUCCESS;
        }

        if (sellerid == null) {
            jsonData.put("msg", "天呢！商家id不能为空");
            jsonData.put("code", "1");
            return SUCCESS;
        }
        if (selected == null) {
            jsonData.put("msg", "天呢！选中状态不能为空");
            jsonData.put("code", "1");
            return SUCCESS;
        }
        if (num == null) {
            jsonData.put("msg", "天呢！商品数量不能为空");
            jsonData.put("code", "1");
            return SUCCESS;
        }

        String sql = "from CarEntity where sellerid = ? and uid = ? and pid = ?";
        Query query = getUser().getSf().openSession().createQuery(sql);
        query.setParameter(0, Long.parseLong(sellerid));
        query.setParameter(1, Long.parseLong(uid));
        query.setParameter(2, Long.parseLong(pid));
        List<CarEntity> carEntityList = query.list();

//        List<CarEntity> carEntityList = getUser().getSf().openSession().createQuery("from CarEntity where sellerid = '" + sellerid + "' and uid = '"+uid+"' and pid = '"+pid+"'").list();

        if (carEntityList != null && carEntityList.size() > 0) {

            CarEntity carEntity = carEntityList.get(0);
            if (carEntity != null) {
                carEntity.setNum(Integer.parseInt(num));
                carEntity.setSelected(Integer.parseInt(selected));

                getUser().updateCar(carEntity);
                jsonData.put("code", "0");
                jsonData.put("msg", "success");
                System.out.println("===============更新购物车成功：uid：" + uid + "   pid：" + pid + "  sellerid:" + sellerid + "===============");


            }
        }


        return SUCCESS;
    }

    /**
     * 获取商家名称
     *
     * @param id
     */
    private String getCartName(String id) {

        String name = "";

        String sql = "from SellerEntity where sellerid = ?";
        Query query = getUser().getSf().openSession().createQuery(sql);
        query.setParameter(0, Long.parseLong(id));

        List<SellerEntity> sellerEntityList = query.list();
//        List<SellerEntity> sellerEntityList = getUser().getSf().openSession().createQuery("from SellerEntity where sellerid = '" + id + "'").list();

        if (sellerEntityList != null && sellerEntityList.size() > 0) {
            SellerEntity sellerEntity = sellerEntityList.get(0);
            name = sellerEntity.getName();

            return name;
        }
        return name;

    }

    /**
     * 获取商品详情
     *
     * @param id
     */
    private ProductEntity getProduct(String id) {

        String sql = "from ProductEntity where pid = ?";
        Query query = getUser().getSf().openSession().createQuery(sql);
        query.setParameter(0, Long.parseLong(id));
        List<ProductEntity> productEntityList = query.list();
//        List<ProductEntity> productEntityList = getUser().getSf().openSession().createQuery("from ProductEntity where pid = '" + id + "'").list();

        if (productEntityList != null && productEntityList.size() > 0) {
            return productEntityList.get(0);
        }
        return null;

    }


    /**
     * 创建订单
     *
     * @return
     */
    public String createOrder() {
        jsonData = new HashMap<>();
        System.out.println("===============创建订单请求：uid：" + uid + "   price：" + price + "===============");

        if (uid == null) {
            jsonData.put("msg", "天呢！用户未登录或用户id不能为空");
            jsonData.put("code", "1");

            return SUCCESS;
        }
        if (price == null) {
            jsonData.put("msg", "天呢！实付价格不能为空");
            jsonData.put("code", "1");

            return SUCCESS;
        }

        OrdersEntity orderEntity = new OrdersEntity();
        orderEntity.setPrice(Double.parseDouble(price));
        orderEntity.setUid(Long.parseLong(uid));
        orderEntity.setStatus(0);

        getUser().createOrder(orderEntity);


        jsonData.put("msg", "订单创建成功");
        jsonData.put("code", "0");
        System.out.println("===============订单创建成功：uid：" + uid + "   price：" + price + "===============");


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
        System.out.println("===============更新订单：uid：" + uid + "   orderId：" + orderId + "===============");

        if (uid == null) {
            jsonData.put("msg", "天呢！用户未登录或用户id不能为空");
            jsonData.put("code", "1");

            return SUCCESS;
        }
        if (orderId == null) {
            jsonData.put("msg", "天呢！订单id不能为空");
            jsonData.put("code", "1");

            return SUCCESS;
        }


        String sql = "from OrdersEntity where orderid = ?";
        Query query = getUser().getSf().openSession().createQuery(sql);
        query.setParameter(0, Long.parseLong(orderId));
        List<OrdersEntity> orderEntityList = query.list();
//        List<OrdersEntity> orderEntityList = getUser().getSf().openSession().createQuery("from OrdersEntity where orderid = '" + orderId + "'").list();


        if (orderEntityList != null && orderEntityList.size() > 0) {

            OrdersEntity orderEntity = orderEntityList.get(0);
            if (orderEntity != null) {
                orderEntity.setStatus(status);
                getUser().updateOrder(orderEntity);

                jsonData.put("msg", "订单状态修改成功");
                jsonData.put("code", "0");
                System.out.println("===============订单状态修改成功：uid：" + uid + "   orderId：" + orderId + "===============");

            }

        }

        uid = null;
        status = 0;

        return SUCCESS;


    }

    /**
     * 订单列表
     *
     * @return
     */
    public String getOrders() {
        jsonData = new HashMap<>();
        System.out.println("===============订单列表：uid：" + uid + "===============");

        if (uid == null) {

            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！用户id不能为空");

            return SUCCESS;

        }

        String sql = "from OrdersEntity where uid = ?";
        Query query = getUser().getSf().openSession().createQuery(sql);
        query.setParameter(0, Long.parseLong(uid));
        query.setMaxResults(10);
        System.out.println("page:" + page);
        if (page == 1) {
            query.setFirstResult(0);
        } else {
            query.setFirstResult((page - 1) * 10);
        }
        List<OrdersEntity> ordersEntities = query.list();

//        List<OrdersEntity> ordersEntities = getUser().getSf().openSession().createQuery("from OrdersEntity where uid = '" + uid + "'").list();


        if (ordersEntities != null) {
            jsonData.put("code", "0");
            jsonData.put("msg", "请求成功");
            jsonData.put("page", page);
            jsonData.put("data", ordersEntities);
            System.out.println("===============订单列表成功：uid：" + uid + "===============");

        }
        uid = null;
        return SUCCESS;
    }

    /**
     * 订单列表
     *
     * @return
     */
    public String searchProducts() {
        jsonData = new HashMap<>();
        System.out.println("===============商品搜索请求：keywords：" + keywords + "===============");

        if (keywords == null) {

            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！关键词不能为空");

            return SUCCESS;

        }
        String sql = "";
        if (sort == 0) {//moren
            sql = "from ProductEntity where title like '%" + keywords + "%'";
        } else if (sort == 1) {//xiaoliang
            sql = "from ProductEntity where title like '%" + keywords + "%' order by salenum desc ";
        } else if (sort == 2) {//jiage
            sql = "from ProductEntity where title like '%" + keywords + "%' order by price";
        }
        Query query = getUser().getSf().openSession().createQuery(sql);
        query.setMaxResults(10);
        System.out.println("page:" + page);
        if (page == 1) {
            query.setFirstResult(0);
        } else {
            query.setFirstResult((page - 1) * 10);
        }
        List<ProductEntity> productEntityList = query.list();

//        List<OrdersEntity> ordersEntities = getUser().getSf().openSession().createQuery("from OrdersEntity where uid = '" + uid + "'").list();


        if (productEntityList != null) {
            jsonData.put("code", "0");
            jsonData.put("msg", "查询成功");
            jsonData.put("page", page);
            jsonData.put("data", productEntityList);
            System.out.println("===============商品搜索成功：keywords：" + keywords + "===============");

        }
        keywords = null;
        return SUCCESS;
    }
}
