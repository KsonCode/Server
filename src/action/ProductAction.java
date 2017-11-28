package action;

import pojo.*;
import com.opensymphony.xwork2.Action;
import org.hibernate.query.Query;
import service.UserImpl;
import utils.AccountValidatorUtil;
import utils.ArithUtils;
import utils.Utils;

import java.util.*;

public class ProductAction implements Action {

    private String source;

    public static long categoryCount = 0;
    public static long subcategoryCount = 0;
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
    private String page = "1";

    //订单
    private String price;
    private String orderId;
    private String status = "-1";

    //搜索
    private String keywords;
    private String sort = "0";

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSort() {
        return sort;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 获取分类列表
     *
     * @return
     */
    public String getCatagory() {
        categoryCount++;
        System.out.println("===============获取分类列表请求===============");

        jsonData = new HashMap<>();
        List<CatagoryEntity> list = getUser().getSf().openSession().createQuery("from CatagoryEntity").list();
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
        subcategoryCount++;
        jsonData = new HashMap<>();
        System.out.println("===============获取子分类列表：cid：" + cid + "===============");

        if (Utils.isEmpty(cid)) {
            jsonData.put("code", "0");
            jsonData.put("msg", "天呢！分类id不能为空");
            return SUCCESS;
        }
        List<ProductCatagory> list = new ArrayList<>();
        String sql1 = "from ProductcatagoryEntity where cid = :cid";
        Query query = getUser().getSf().openSession().createQuery(sql1);
        query.setParameter("cid", Long.parseLong(cid));
        List<ProductcatagoryEntity> productcatagoryEntities = query.list();
//        List<ProductcatagoryEntity> productcatagoryEntities = getUser().getSf().openSession().createQuery("from ProductcatagoryEntity where cid = '" + cid + "'").list();


        if (productcatagoryEntities != null && productcatagoryEntities.size() > 0) {

            for (ProductcatagoryEntity productcatagoryEntity : productcatagoryEntities) {
                ProductCatagory productCatagory = new ProductCatagory();
                productCatagory.setCid(cid);
                productCatagory.setPcid(String.valueOf(productcatagoryEntity.getPcid()));
                productCatagory.setName(productcatagoryEntity.getName());
                System.out.println("cname:" + productcatagoryEntity.getName());

                String sql2 = "from ProductsubcatagoryEntity where pcid = :pcid";
                Query query1 = getUser().getSf().openSession().createQuery(sql2);
                query1.setParameter("pcid", (int) productcatagoryEntity.getPcid());
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

        } else {
            jsonData.put("code", "0");
            jsonData.put("msg", "获取子分类成功");
            jsonData.put("data", list);
            System.out.println("===============获取子分类成功：cid：" + cid + "===============");

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

        if (Utils.isEmpty(source)) {
            jsonData.put("code", "1");
            jsonData.put("msg", "请上传公共参数");

            source = null;


            return SUCCESS;
        }

        if (!"android".equals(source)){
            jsonData.put("code", "1");
            jsonData.put("msg", "公共参数值不正确");

            source = null;


            return SUCCESS;
        }

        if (Utils.isEmpty(pid)) {
            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！商品id不能为空");
            return SUCCESS;
        }
        String sql = "from ProductEntity where pid = :pid";
        Query query = getUser().getSf().openSession().createQuery(sql);
        query.setParameter("pid", Long.parseLong(pid));
        List<ProductEntity> productEntityList = query.list();
//        List<ProductEntity> productEntityList = getUser().getSf().openSession().createQuery("from ProductEntity where pid = '" + pid + "'").list();

        if (productEntityList != null && productEntityList.size() > 0) {
            ProductEntity productEntity = productEntityList.get(0);


            String sql1 = "from SellerEntity where sellerid = :sellerid";
            Query query1 = getUser().getSf().openSession().createQuery(sql1);
            query1.setParameter("sellerid", productEntity.getSellerid());
            List<SellerEntity> sellerEntityList = query1.list();
            jsonData.put("code", "0");
            jsonData.put("msg", "");
            jsonData.put("data", productEntity);
            jsonData.put("seller", sellerEntityList.get(0));


            System.out.println("===============商品详情成功：pid：" + pid + "===============");

        }

        pid = null;
        source = null;
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

        if (Utils.isEmpty(pscid)) {

            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！分类id不能为空");

            return SUCCESS;

        }
        Query query = null;
        if (!Utils.isEmpty(sort)) {

            if (AccountValidatorUtil.isInteger(sort)) {

                String sql = "";
                if (Integer.parseInt(sort) == 0) {//moren
                    sql = "from ProductEntity where pscid = :pscid";
                } else if (Integer.parseInt(sort) == 1) {//xiaoliang
                    sql = "from ProductEntity where pscid = :pscid order by salenum desc ";
                } else if (Integer.parseInt(sort) == 2) {//jiage
                    sql = "from ProductEntity where pscid = :pscid order by price";
                }
                query = getUser().getSf().openSession().createQuery(sql);
                query.setParameter("pscid", Long.parseLong(pscid));
                query.setMaxResults(10);
                System.out.println("page:" + page);
                if (page != null) {

                    if (AccountValidatorUtil.isInteger(page)) {

                        if (page.equals(1)) {
                            query.setFirstResult(0);
                        } else {
                            query.setFirstResult((Integer.parseInt(page) - 1) * 10);
                        }
                    } else {
                        jsonData.put("code", "1");
                        jsonData.put("msg", "God！page必须是数字");

                        return SUCCESS;
                    }
                }
            } else {
                jsonData.put("code", "1");
                jsonData.put("msg", "天呢！sort必须是数字格式");

                return SUCCESS;
            }

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
        page = "1";
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
        if (Utils.isEmpty(source)) {
            jsonData.put("code", "1");
            jsonData.put("msg", "请上传公共参数");

            source = null;


            return SUCCESS;
        }

        if (!"android".equals(source)){
            jsonData.put("code", "1");
            jsonData.put("msg", "公共参数值不正确");

            source = null;


            return SUCCESS;
        }

        if (Utils.isEmpty(uid)) {
            jsonData.put("msg", "天呢！用户未登录或用户id不能为空");
            jsonData.put("code", "1");

            return SUCCESS;
        }
        if (Utils.isEmpty(pid)) {
            jsonData.put("msg", "天呢！商品id不能为空");
            jsonData.put("code", "1");

            return SUCCESS;
        }

//        if (Utils.isEmpty(sellerid)) {
//            jsonData.put("msg", "天呢！商家id不能为空");
//            jsonData.put("code", "1");
//            return SUCCESS;
//        }

        String sql = "from CarEntity where pid = :pid and uid = :uid";

        Query query = getUser().getSf().openSession().createQuery(sql);
        query.setParameter("pid", Long.parseLong(pid));
        query.setParameter("uid", Long.parseLong(uid));
        List<CarEntity> carEntityList = query.list();

        String sql2 = "from ProductEntity where pid = :pid";
        double price = -1;
        long selerid = -1;
        Query query2 = getUser().getSf().openSession().createQuery(sql2);
        query2.setParameter("pid", Long.parseLong(pid));
        List<ProductEntity> productList = query2.list();
        if (productList != null && productList.size() > 0) {
            price = productList.get(0).getBargainPrice();
            selerid = productList.get(0).getSellerid();
        }

//        List<CarEntity> carEntityList = getUser().getSf().openSession().createQuery("from CarEntity where pid = '" + pid + "' and uid = '"+uid+"'").list();

        if (carEntityList != null && carEntityList.size() > 0) {
            CarEntity carEntity = carEntityList.get(0);
            if (carEntity != null) {
                int num = carEntity.getNum() + 1;
                carEntity.setNum(num);
                carEntity.setPrice(ArithUtils.mul(price, num));
                System.out.println("num=====================================:" + num);
                System.out.println("price=====================================:" + ArithUtils.mul(price, num));
                getUser().updateCar(carEntity);
            }
        } else {
            CarEntity carEntity = new CarEntity();
            carEntity.setNum(1);
            if (price != -1) {
                carEntity.setPrice(price);
            } else {
                carEntity.setPrice(0);
            }
            carEntity.setPid(Long.parseLong(pid));
            if (selerid != -1) {
                carEntity.setSellerid(selerid);
            }
            carEntity.setUid(Long.parseLong(uid));
            getUser().addCar(carEntity);
        }


        jsonData.put("msg", "加购成功");
        jsonData.put("code", "0");
        System.out.println("===============添加购物车成功：uid：" + uid + "   pid：" + pid + "  sellerid" + sellerid + "===============");


        uid = null;
//        sellerid = null;
        source = null;
        pid = null;


        return SUCCESS;


    }

    /**
     * 添加购物车
     *
     * @return
     */
    public String deleteCart() {
        jsonData = new HashMap<>();
        System.out.println("===============添加购物车请求：uid：" + uid + "   pid：" + pid + "  sellerid" + sellerid + "===============");


        if (Utils.isEmpty(uid)) {
            jsonData.put("msg", "天呢！用户未登录或用户id不能为空");
            jsonData.put("code", "1");

            return SUCCESS;
        }
        if (Utils.isEmpty(pid)) {
            jsonData.put("msg", "天呢！商品id不能为空");
            jsonData.put("code", "1");

            return SUCCESS;
        }

//        if (Utils.isEmpty(sellerid)) {
//            jsonData.put("msg", "天呢！商家id不能为空");
//            jsonData.put("code", "1");
//            return SUCCESS;
//        }

        String sql = "from CarEntity where pid = :pid and uid = :uid";

        Query query = getUser().getSf().openSession().createQuery(sql);
        query.setParameter("pid", Long.parseLong(pid));
        query.setParameter("uid", Long.parseLong(uid));
        List<CarEntity> carEntityList = query.list();


//        List<CarEntity> carEntityList = getUser().getSf().openSession().createQuery("from CarEntity where pid = '" + pid + "' and uid = '"+uid+"'").list();

        if (carEntityList != null && carEntityList.size() > 0) {
            CarEntity carEntity = carEntityList.get(0);
            if (carEntity != null) {

                getUser().deleteCar(carEntity);
                jsonData.put("msg", "删除购物车成功");
                jsonData.put("code", "0");
                System.out.println("===============删除购物车成功：uid：" + uid + "   pid：" + pid + "  sellerid" + sellerid + "===============");


            }
        } else {
            jsonData.put("msg", "购物车数据不存在");
            jsonData.put("code", "1");
        }


        uid = null;
//        sellerid = null;
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

        if (Utils.isEmpty(source)) {
            jsonData.put("code", "1");
            jsonData.put("msg", "请上传公共参数");

            source = null;


            return SUCCESS;
        }

        if (!"android".equals(source)){
            jsonData.put("code", "1");
            jsonData.put("msg", "公共参数值不正确");

            source = null;


            return SUCCESS;
        }
        if (Utils.isEmpty(uid)) {

            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！用户id不能为空");

            return SUCCESS;

        }

        String usersql = "from UserEntity where uid = :uid";

        Query userquery = getUser().getSf().openSession().createQuery(usersql);
        userquery.setParameter("uid", Long.parseLong(uid));
        List<UserEntity> userEntityList = userquery.list();
        if (userEntityList != null && userEntityList.size() == 0) {
            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！用户不存在");

            return SUCCESS;
        }

        List<Cart> cartList = new ArrayList<>();

        String sql = "from CarEntity where uid = :uid group by sellerid";

        Query query = getUser().getSf().openSession().createQuery(sql);
        query.setParameter("uid", Long.parseLong(uid));
        List<CarEntity> carEntityList = query.list();

//        List<CarEntity> carEntityList = getUser().getSf().openSession().createQuery("from CarEntity where uid = '" + uid + "' group by sellerid").list();


        if (carEntityList != null && carEntityList.size() > 0) {

            for (CarEntity carEntity : carEntityList) {

                Cart cart = new Cart();
                cart.setSellerName(getCartName(String.valueOf(carEntity.getSellerid())));
                cart.setSellerid(String.valueOf(carEntity.getSellerid()));

                System.out.println("CAR:" + carEntity.getSellerid());

                List<Product> productEntityList = new ArrayList<>();

                String sql2 = "from CarEntity where sellerid = :sellerid and uid = :uid";
                Query query1 = getUser().getSf().openSession().createQuery(sql2);
                query1.setParameter("sellerid", carEntity.getSellerid());
                query1.setParameter("uid", Long.parseLong(uid));
                List<CarEntity> carEntityList1 = query1.list();
//                List<CarEntity> carEntityList1 = getUser().getSf().openSession().createQuery("from CarEntity where sellerid = '" + carEntity.getSellerid() + "' and uid = '"+uid+"'").list();

                if (carEntityList1 != null && carEntityList1.size() > 0) {

                    for (CarEntity entity : carEntityList1) {

                        System.out.println("selleid" + entity.getSellerid());
                        System.out.println("pid:" + entity.getPid());
                        ProductEntity productEntity = getProduct(String.valueOf(entity.getPid()));
                        if (productEntity != null) {

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

                    }
                    cart.setList(productEntityList);
                    cartList.add(cart);
                }
            }
            if (cartList != null) {
                jsonData.put("code", "0");
                jsonData.put("msg", "请求成功");
                jsonData.put("data", cartList);
                uid= null;
                System.out.println("===============获取购物车成功：uid：" + uid + "===============");
                return SUCCESS;
            }
        } else {
            jsonData.put("code", "0");
            jsonData.put("msg", "请求成功");
            jsonData.put("data", "[]");
        }

        uid = null;
        jsonData = null;
        source = null;
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

        if (Utils.isEmpty(uid)) {
            jsonData.put("msg", "天呢！用户未登录或用户id不能为空");
            jsonData.put("code", "1");

            return SUCCESS;
        }
        if (Utils.isEmpty(pid)) {
            jsonData.put("msg", "天呢！商品id不能为空");
            jsonData.put("code", "1");

            return SUCCESS;
        }

        if (Utils.isEmpty(sellerid)) {
            jsonData.put("msg", "天呢！商家id不能为空");
            jsonData.put("code", "1");
            return SUCCESS;
        }
        if (Utils.isEmpty(selected)) {
            jsonData.put("msg", "天呢！选中状态不能为空");
            jsonData.put("code", "1");
            return SUCCESS;
        }
        if (AccountValidatorUtil.isInteger(num)) {

            if (Utils.isEmpty(num)) {
                jsonData.put("msg", "天呢！商品数量不能为空");
                jsonData.put("code", "1");
                return SUCCESS;
            }
        } else {
            jsonData.put("msg", "天呢！商品数量必须是数字");
            jsonData.put("code", "1");
            return SUCCESS;
        }

        String sql = "from CarEntity where sellerid = :sellerid and uid = :uid and pid = :pid";
        Query query = getUser().getSf().openSession().createQuery(sql);
        query.setParameter("sellerid", Long.parseLong(sellerid));
        query.setParameter("uid", Long.parseLong(uid));
        query.setParameter("pid", Long.parseLong(pid));
        List<CarEntity> carEntityList = query.list();

        String sql2 = "from ProductEntity where pid = :pid";
        double price = -1;
        Query query2 = getUser().getSf().openSession().createQuery(sql2);
        query2.setParameter("pid", Long.parseLong(pid));
        List<ProductEntity> productList = query2.list();
        if (productList != null && productList.size() > 0) {
            price = productList.get(0).getBargainPrice();
        }

//        List<CarEntity> carEntityList = getUser().getSf().openSession().createQuery("from CarEntity where sellerid = '" + sellerid + "' and uid = '"+uid+"' and pid = '"+pid+"'").list();

        if (carEntityList != null && carEntityList.size() > 0) {

            CarEntity carEntity = carEntityList.get(0);
            if (carEntity != null) {
                carEntity.setNum(Integer.parseInt(num));
                if (price != -1) {
                    carEntity.setPrice(ArithUtils.mul(price, Double.parseDouble(num)));
                }
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

        String sql = "from SellerEntity where sellerid = :sellerid";
        Query query = getUser().getSf().openSession().createQuery(sql);
        query.setParameter("sellerid", Long.parseLong(id));

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

        String sql = "from ProductEntity where pid = :pid";
        Query query = getUser().getSf().openSession().createQuery(sql);
        query.setParameter("pid", Long.parseLong(id));
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

        if (Utils.isEmpty(uid)) {
            jsonData.put("msg", "天呢！用户未登录或用户id不能为空");
            jsonData.put("code", "1");

            return SUCCESS;
        }
        if (Utils.isEmpty(price)) {
            jsonData.put("msg", "天呢！实付价格不能为空");
            jsonData.put("code", "1");
            return SUCCESS;
        }

        String sql = "from CarEntity where uid = :uid";
        Query query = getUser().getSf().openSession().createQuery(sql);
        query.setParameter("uid", Long.parseLong(uid));
        List<CarEntity> carEntityList = query.list();

        if (carEntityList != null && carEntityList.size() > 0) {
            double mprice = 0;
            List<Double> prices = new ArrayList<>();
//            double[] values = new double[carEntityList.size()];
            for (int i = 0; i < carEntityList.size(); i++) {
                if (carEntityList.get(i).getSelected() == 1) {
                    prices.add(carEntityList.get(i).getPrice());
                }
//                values[i] = carEntityList.get(i).getPrice();
            }
            if (prices != null && prices.size() > 0) {
                System.out.println("pricesize:" + prices.size());
                mprice = ArithUtils.add(prices);
                if (mprice != Double.parseDouble(price)) {
                    jsonData.put("msg", "天呢！有钱人，总价不正确！");
                    jsonData.put("code", "1");
                    return SUCCESS;
                }
            }
            OrdersEntity orderEntity = new OrdersEntity();
            orderEntity.setPrice(Double.parseDouble(price));
            orderEntity.setUid(Long.parseLong(uid));
            orderEntity.setStatus(0);

            getUser().createOrder(orderEntity);


            jsonData.put("msg", "订单创建成功");
            jsonData.put("code", "0");
            System.out.println("===============订单创建成功：uid：" + uid + "   price：" + price + "===============");

        } else {
            jsonData.put("msg", "购物车不能为空");
            jsonData.put("code", "1");
            System.out.println("===============订单创建失败，购物车为空：uid：" + uid + "   price：" + price + "===============");

        }


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
        if (Utils.isEmpty(uid)) {
            jsonData.put("msg", "天呢！用户未登录或用户id不能为空");
            jsonData.put("code", "1");
            return SUCCESS;
        }

        if (!Utils.isEmpty(status)) {
            if ("-1".equals(status)){

                jsonData.put("msg", "请传入status参数，为0、1或2");
                jsonData.put("code", "1");
                return SUCCESS;
            }
        }

        if (!Utils.isEmpty(orderId)) {

            if (!AccountValidatorUtil.isInteger(orderId)) {
                jsonData.put("msg", "天呢！订单id必须是数字");
                jsonData.put("code", "1");
                return SUCCESS;
            }

        } else {
            jsonData.put("msg", "天呢！订单id不能为空");
            jsonData.put("code", "1");

            return SUCCESS;
        }
        if (!Utils.isEmpty(status)) {

            if (!AccountValidatorUtil.isInteger(status)) {
                jsonData.put("msg", "天呢！订单状态必须是数字");
                jsonData.put("code", "1");
                return SUCCESS;
            }
        } else {
            jsonData.put("msg", "天呢！订单状态不能为空");
            jsonData.put("code", "1");
            return SUCCESS;
        }


        String sql = "from OrdersEntity where orderid = :orderid";
        Query query = getUser().getSf().openSession().createQuery(sql);
        query.setParameter("orderid", Long.parseLong(orderId));
        List<OrdersEntity> orderEntityList = query.list();
//        List<OrdersEntity> orderEntityList = getUser().getSf().openSession().createQuery("from OrdersEntity where orderid = '" + orderId + "'").list();


        if (orderEntityList != null && orderEntityList.size() > 0) {

            OrdersEntity orderEntity = orderEntityList.get(0);
            if (orderEntity != null) {
                orderEntity.setStatus(Integer.parseInt(status));
                getUser().updateOrder(orderEntity);

                jsonData.put("msg", "订单状态修改成功");
                jsonData.put("code", "0");
                System.out.println("===============订单状态修改成功：uid：" + uid + "   orderId：" + orderId + "===============");

            }

        }

        uid = null;
        status = "-1";

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

        if (Utils.isEmpty(uid)) {

            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！用户id不能为空");

            return SUCCESS;

        }


        if (AccountValidatorUtil.isInteger(status)) {

            String sql = "";
            System.out.println("page:" + page + " status:" + status);
            if (Integer.parseInt(status) == -1) {//moren
                sql = "from OrdersEntity where uid = :uid";
            } else if (Integer.parseInt(status) == 0) {//moren
                sql = "from OrdersEntity where uid = :uid and status = :status";
            } else if (Integer.parseInt(status) == 1) {//xiaoliang
                sql = "from OrdersEntity where uid = :uid and status = :status ";
            } else if (Integer.parseInt(status) == 2) {//jiage
                sql = "from OrdersEntity where uid = :uid and status = :status";
            }
            Query query = getUser().getSf().openSession().createQuery(sql);
            query.setParameter("uid", Long.parseLong(uid));
            if (!"-1".equals(status)){

                query.setParameter("status", Integer.parseInt(status));
            }
            query.setMaxResults(10);
            System.out.println("page:" + page);
            if (page != null) {

                if (AccountValidatorUtil.isInteger(page)) {

                    if (page.equals(1)) {
                        query.setFirstResult(0);
                    } else {
                        query.setFirstResult((Integer.parseInt(page) - 1) * 10);
                    }
                } else {
                    jsonData.put("code", "1");
                    jsonData.put("msg", "God！page必须是数字");

                    return SUCCESS;
                }
            }
            if (page != null) {

                if (AccountValidatorUtil.isInteger(page)) {

                    if (page.equals(1)) {
                        query.setFirstResult(0);
                    } else {
                        query.setFirstResult((Integer.parseInt(page) - 1) * 10);
                    }
                } else {
                    jsonData.put("code", "1");
                    jsonData.put("msg", "God！page必须是数字");

                    return SUCCESS;
                }
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
        }


        uid = null;
        status = "-1";
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

        if (Utils.isEmpty(source)) {
            jsonData.put("code", "1");
            jsonData.put("msg", "请上传公共参数");

            source = null;


            return SUCCESS;
        }
        if (Utils.isEmpty(keywords)) {

            jsonData.put("code", "1");
            jsonData.put("msg", "天呢！关键词不能为空");

            return SUCCESS;

        }
        Query query = null;
        if (!Utils.isEmpty(sort)) {

            if (AccountValidatorUtil.isInteger(sort)) {
                String sql = "";
                if (Integer.parseInt(sort) == 0) {//moren
                    sql = "from ProductEntity where title like '%" + keywords + "%'";
                } else if (Integer.parseInt(sort) == 1) {//xiaoliang
                    sql = "from ProductEntity where title like '%" + keywords + "%' order by salenum desc ";
                } else if (Integer.parseInt(sort) == 2) {//jiage
                    sql = "from ProductEntity where title like '%" + keywords + "%' order by price";
                }
                query = getUser().getSf().openSession().createQuery(sql);
                query.setMaxResults(10);
            } else {
                jsonData.put("code", "1");
                jsonData.put("msg", "天呢！sort必须是数字格式");

                return SUCCESS;
            }

        }


        System.out.println("page:" + page);
        if (!Utils.isEmpty(page)) {

            if (AccountValidatorUtil.isInteger(page)) {

                if (page.equals(1)) {
                    query.setFirstResult(0);
                } else {
                    query.setFirstResult((Integer.parseInt(page) - 1) * 10);
                }
            } else {
                jsonData.put("code", "1");
                jsonData.put("msg", "God！page必须是数字");

                return SUCCESS;
            }
        } else {
            page = "1";
            query.setFirstResult(0);
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
        page = "1";
        keywords = null;
        source = null;
        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }
}
