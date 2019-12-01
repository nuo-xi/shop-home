package com.fh.service;

import com.fh.enumUtil.ResponseServer;

import java.util.Map;

public interface CartService {
    ResponseServer addCart(String telnum, String shopId);

    ResponseServer queryCartById(String telnum);

    Map<String, Object> queryCartList(String telnum);

    ResponseServer updateShop(String telnum, String shopId, Integer type);

    ResponseServer changeShopNum(String shopId, String telnum, String sum);
}
