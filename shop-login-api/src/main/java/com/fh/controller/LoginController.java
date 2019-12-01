package com.fh.controller;

import com.fh.JWT.JwtUtils;
import com.fh.beans.po.UserBean;
import com.fh.enumUtil.EnumService;
import com.fh.enumUtil.ResponseServer;
import com.fh.service.LoginServcie;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginServcie loginServcie;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 短信登录
     *
     * @param phonenum
     * @param code
     * @param session
     * @return
     */
    @PostMapping
    @CrossOrigin(maxAge = 3000, origins = "http://localhost:8080", methods = RequestMethod.POST)
    public ResponseServer loginCheck(@RequestParam("phonenum") String phonenum,
                                     @RequestParam("code") String code,
                                     HttpSession session) {
        System.out.println("传进来的手机号" + phonenum + "验证码是" + code);

        //判断验证码和手机号是否为空
        if (StringUtils.isBlank(phonenum) || StringUtils.isBlank(code)) {
            /*验证码为空*/
            return ResponseServer.error(EnumService.LOGIN_CODE_IS_NULL);
        }

        //判断验证码是一致
        String o = (String) redisTemplate.opsForValue().get("user_" + phonenum);
        if (!code.equals(o)) {
            /*验证码无效*/
            return ResponseServer.error(EnumService.LOGIN_CODE_IS_LOSE);
        }
        //删除验证码
        redisTemplate.delete("user_" + phonenum);

        //是否已经注册否则注册 信息放到redis中
        UserBean us = loginServcie.registerUser(phonenum);
        redisTemplate.opsForValue().set("user_" + phonenum, us);
        redisTemplate.opsForValue().set("usercart_" + phonenum, us.getCartid());
        //生成token值
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("phonenum", us.getTelnum());
        String token = JwtUtils.createToken(map);

        return ResponseServer.success(token);
    }

    /**
     * 账户密码登录
     *
     * @param account
     * @param pwd
     * @return
     */
    @RequestMapping("/accesslogin")
    @CrossOrigin(maxAge = 3000, origins = "http://localhost:8080", methods = RequestMethod.POST)
    public ResponseServer accesslogin(@RequestParam("account") String account,
                                      @RequestParam("pwd") String pwd) {
        System.out.println("传进来的账号" + account + "验证码" + pwd);

        if (!StringUtils.isNotBlank(account) && !StringUtils.isNotBlank(pwd)) {
            return ResponseServer.error(EnumService.LOGIN_ACCOUNT_PWD_ISNULL);
        }
        UserBean userBean = loginServcie.getUserByAccount(account);
        if (userBean == null) {
            return ResponseServer.error(EnumService.LOGIN_USER_NOT_EXIST);
        }
        if (!pwd.equals(userBean.getPwd())) {
            return ResponseServer.error(EnumService.LOGIN_USER_PWD_NOT_TRUE);
        }
        return ResponseServer.success();
    }

    /**
     * 发送验证码
     *
     * @param phonenum
     * @return
     */
    @GetMapping("/{phonenum}")
    @CrossOrigin(maxAge = 3000, origins = "http://localhost:8080", methods = RequestMethod.GET)
    public ResponseServer getCode(@PathVariable("phonenum") String phonenum) {
        System.out.println("传进来的手机号是" + phonenum);

        /*发送验证码*/
//        String code = null;
//        try {
//            String result = SendCode.getCode(phonenum);
//            JSONObject jsonObject = JSON.parseObject(result);
//            String code1 = jsonObject.getString("code");
//            if (!"200".equals(code1)) {
//                /*发送失败*/
//            } else {
//                code = jsonObject.getString("obj");
//                System.out.println("短信验证码是："+code);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        String code = "123456";
        redisTemplate.opsForValue().set("user_" + phonenum, code, 300, TimeUnit.SECONDS);

        return ResponseServer.success(code);
    }

}
