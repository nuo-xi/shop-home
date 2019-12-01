package com.fh.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fh.beans.CartBean;
import com.fh.enumUtil.ResponseServer;
import com.fh.httpConnectUtils.ApiAddr;
import com.fh.httpConnectUtils.HttpConnection;
import com.fh.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service("cartService")
public class CartServiceImpl implements CartService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResponseServer addCart(String telnum, String shopId) {
        /*通过电话号码获取购物车的id */
        String usercart = (String) redisTemplate.opsForValue().get("usercart_" + telnum);

        /*调用接口 取数据*/
        String url = ApiAddr.productAddr +"/" + shopId;
        String result = HttpConnection.doGet(url);
        /*把String类型转换成  JSONObject 方便取数据  */
        JSONObject parse = (JSONObject) JSON.parse(result);
        Object data = parse.get("data");
        JSONObject parse1 = (JSONObject) JSON.parse(data.toString());

        //数据加入购物车实体bean中
        CartBean cartBean = new CartBean();
        cartBean.setShopId(parse1.getInteger("id"));
        cartBean.setShopName(parse1.getString("name"));
        cartBean.setShopImg(parse1.getString("mainImg"));
        cartBean.setShopPrice(parse1.getBigDecimal("price"));

        //判断商品是否存在购物车
        if (redisTemplate.opsForHash().hasKey(usercart, shopId)) {
            CartBean cart = (CartBean) redisTemplate.opsForHash().get(usercart, shopId);
            cartBean.setCount(cart.getCount() + 1);
        } else {
            cartBean.setCount(1);
        }
        /*新增的默认被选中*/
        cartBean.setIsCheck(true);
        /* 计算当个商品的小计 */
        BigDecimal subTotal = getSubTotal(cartBean);
        cartBean.setSubTotal(subTotal);

        redisTemplate.opsForHash().put(usercart, shopId, cartBean);
        Long cartCount = redisTemplate.opsForHash().size(usercart);
        return ResponseServer.success(cartCount);
    }

    @Override
    public ResponseServer queryCartById(String telnum) {
        String usercart = (String) redisTemplate.opsForValue().get("usercart_" + telnum);
        Long cartCount = redisTemplate.opsForHash().size(usercart);
        return ResponseServer.success(cartCount);
    }

    @Override
    public Map<String, Object> queryCartList(String telnum) {
        /* 通过电话号码获取购物车的id */
        String usercart = (String) redisTemplate.opsForValue().get("usercart_" + telnum);
        /* 查询购物车的数据集合 */
        List<CartBean> values = redisTemplate.opsForHash().values(usercart);
        //System.out.println("values" + JSON.toJSONString(values));
        /*values[{"count":5,"shopId":4,"shopImg":"","shopName":"手机","shopPrice":213.0},{"count":7,"shopId":3,"shopImg":"","shopName":"手机","shopPrice":123.0},{"count":1,"shopId":6,"shopImg":"","shopName":"商务本","shopPrice":123.0},{"count":1,"shopId":5,"shopImg":"","shopName":"游戏本"}]
         */

        // HashMap entries = (HashMap) redisTemplate.opsForHash().entries(usercart);
        //System.out.println("hashmap"+JSON.toJSONString(entries));
        /*hashmap{"4":{"count":5,"shopId":4,"shopImg":"","shopName":"手机","shopPrice":213.0},"3":{"count":7,"shopId":3,"shopImg":"","shopName":"手机","shopPrice":123.0},"6":{"count":1,"shopId":6,"shopImg":"","shopName":"商务本","shopPrice":123.0},"5":{"count":1,"shopId":5,"shopImg":"","shopName":"游戏本"}}
         */
      /* List<Object> list = new ArrayList<Object>();
       Iterator iter = entries.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
           *//* Object val = entry.getValue();*//*
            list.add(key);
        }
        List list1 = redisTemplate.opsForHash().multiGet(usercart, list);*/

        /*list[{"count":5,"shopId":4,"shopImg":"","shopName":"手机","shopPrice":213.0},{"count":7,"shopId":3,"shopImg":"","shopName":"手机","shopPrice":123.0},{"count":1,"shopId":6,"shopImg":"","shopName":"商务本","shopPrice":123.0},{"count":1,"shopId":5,"shopImg":"","shopName":"游戏本"}]
         */
        Map<String, Object> map = new HashMap<String, Object>();
        int count = 0;
        BigDecimal sumTotal = BigDecimal.valueOf(0.00);
        /*计算总价*/
        if(values.size() > 0){
            for (CartBean cart : values) {
                if (cart.getIsCheck()) {
                    sumTotal = sumTotal.add(cart.getSubTotal());
                    count += cart.getCount();
                }
            }
        }
        map.put("sumTotal", sumTotal);
        map.put("cartList", values);
        map.put("count", count);

        return map;
    }

    @Override
    public ResponseServer updateShop(String telnum, String shopId, Integer type) {
        /*从redis中去数据 购物车 */
        String usercart = (String) redisTemplate.opsForValue().get("usercart_" + telnum);

        CartBean cartBean = null;
        /* 判断是否点击了全选  固定传来字符串 "shopAll"  */
        if (shopId.equals("shopAll")) {
            /*查询所有购物车中的数据*/
            List<CartBean> values = redisTemplate.opsForHash().values(usercart);
            /*
            * type状态，码
            * 1:需要全选
            * 2:全不选
            * */
            if(1 == type){
                for (CartBean cart : values) {
                    cart.setIsCheck(true);
                    redisTemplate.opsForHash().put(usercart, String.valueOf(cart.getShopId()), cart);
                }
                return ResponseServer.success();
            }else  {
                for (CartBean cart : values) {
                    cart.setIsCheck(false);
                    redisTemplate.opsForHash().put(usercart, String.valueOf(cart.getShopId()), cart);
                }
                return ResponseServer.success();
            }
        } else {
            /*通过购物车id，和商品id，获取对应商品的信息 */
            cartBean = (CartBean) redisTemplate.opsForHash().get(usercart, shopId);
        }

        /**
         * type为操作状态码
         *
         * 2: 修改商品的选中状态
         * 3: 增加商品的数量
         * 4: 减少商品的数量
         * 5: 删除商品
         *
         * 默认: 不处理
         */
        switch (type) {

            case 2: {
                cartBean.setIsCheck(!cartBean.getIsCheck());
                redisTemplate.opsForHash().put(usercart, shopId, cartBean);
            }
            break;

            case 3: {

                /* 通过商品id去库里面查 库存*/
                String url = ApiAddr.productAddr +"/"  + shopId;
                String result = HttpConnection.doGet(url);
                JSONObject parse = (JSONObject) JSON.parse(result);
                Object data = parse.get("data");
                JSONObject parse1 = (JSONObject) JSON.parse(data.toString());

                if(cartBean.getCount().equals(parse1.getInteger("count")) ){
                    cartBean.setCount(parse1.getInteger("count"));
                }else{
                    cartBean.setCount(cartBean.getCount() + 1);
                }

                BigDecimal subTotal = getSubTotal(cartBean);
                cartBean.setSubTotal(subTotal);
                redisTemplate.opsForHash().put(usercart, shopId, cartBean);
            }
            break;

            case 4: {

                Integer count = cartBean.getCount();
                if (count == 1) {
                    cartBean.setCount(1);
                } else {
                    cartBean.setCount(cartBean.getCount() - 1);
                }
                BigDecimal subTotal = getSubTotal(cartBean);
                cartBean.setSubTotal(subTotal);
                redisTemplate.opsForHash().put(usercart, shopId, cartBean);
            }
            break;

            case 5: {
                redisTemplate.opsForHash().delete(usercart, shopId);
            }
            break;
            default:
                break;
        }

        return ResponseServer.success();
    }

    @Override
    public ResponseServer changeShopNum(String shopId, String telnum, String sum) {
        /*从redis中去数据 购物车 */
        String usercart = (String) redisTemplate.opsForValue().get("usercart_" + telnum);
        CartBean cartBean = (CartBean) redisTemplate.opsForHash().get(usercart, shopId);
        /* 通过商品id去库里面查 库存*/
        /*
        String url = "http://localhost:8090/httpShops/" + shopId;
        String result = HttpConnection.doGet(url);
        JSONObject parse = (JSONObject) JSON.parse(result);
        Object data = parse.get("data");
        JSONObject parse1 = (JSONObject) JSON.parse(data.toString());
        Integer id = parse1.getInteger("id");
        */

        cartBean.setCount(Integer.valueOf(sum));
        BigDecimal subTotal = getSubTotal(cartBean);
        cartBean.setSubTotal(subTotal);
        redisTemplate.opsForHash().put(usercart, shopId, cartBean);

        return ResponseServer.success();
    }

    public static BigDecimal getSubTotal(CartBean cartBean){
        BigDecimal bigDecimal = BigDecimal.valueOf(0.00);
        BigDecimal bigDecimal1 = new BigDecimal(cartBean.getCount());
        bigDecimal = cartBean.getShopPrice().multiply(bigDecimal1);
        return bigDecimal;
    }
}
