package com.example.uniappspringboot.Controller;

import cn.hutool.json.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.uniappspringboot.Config.AliPayConfig;
import com.example.uniappspringboot.Dao.PayOrdersDao;
import com.example.uniappspringboot.Domain.AliPay;
import com.example.uniappspringboot.Domain.PayOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/alipay")
public class AliPayController {
    private static final String GATEWAY_URL = "https://openapi.alipay.com/gateway.do";
    private static final String FORMAT = "JSON";
    private static final String CHARSET = "UTF-8";
    private static final String SIGN_TYPE = "RSA2"; //签名方式
    @Resource
    private AliPayConfig aliPayConfig;
    @Autowired
    private PayOrdersDao payOrdersDao;
    @PostMapping("/pay")
    public void pay(PayOrders payOrders, HttpServletResponse httpResponse) throws Exception {
        LambdaQueryWrapper<PayOrders> lwq=new LambdaQueryWrapper<>();
        lwq.eq(PayOrders::getAlipayno,payOrders.getAlipayno()).eq(PayOrders::getOpenid,payOrders.getOpenid());
        PayOrders Order=payOrdersDao.selectOne(lwq);
        if (Order==null){

        }else {
            AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, aliPayConfig.getAppId(),
                    aliPayConfig.getAppPrivateKey(), FORMAT, CHARSET, aliPayConfig.getAlipayPublicKey(), SIGN_TYPE);
            // 2. 创建 Request并设置Request参数
            AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();  // 发送请求的 Request类
            request.setNotifyUrl(aliPayConfig.getNotifyUrl());
            JSONObject bizContent = new JSONObject();
            bizContent.set("out_trade_no", Order.getAlipayno());  // 我们自己生成的订单编号
            bizContent.set("total_amount", Order.getProductprice()); // 订单的总金额
            bizContent.set("subject", Order.getProductname());   // 支付的名称
            bizContent.set("product_code", "FAST_INSTANT_TRADE_PAY");  // 固定配置
            request.setBizContent(bizContent.toString());
            // 执行请求，拿到响应的结果，返回给浏览器
            String form = "";
            try {
                form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
            } catch (AlipayApiException e) {
                e.printStackTrace();
            }
            httpResponse.setContentType("text/html;charset=" + CHARSET);
            httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
            httpResponse.getWriter().flush();
            httpResponse.getWriter().close();
        }

    }

    @PostMapping("/notify")  // 注意这里必须是POST接口
    public String payNotify(HttpServletRequest request) throws Exception {
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
           // System.out.println("=========支付宝异步回调========");
            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
                 //System.out.println(name + " = " + request.getParameter(name));
            }

            String outTradeNo = params.get("out_trade_no");
            String gmtPayment = params.get("gmt_payment");
            String alipayTradeNo = params.get("trade_no");

            String sign = params.get("sign");
            String content = AlipaySignature.getSignCheckContentV1(params);
            boolean checkSignature = AlipaySignature.rsa256CheckContent(content, sign, aliPayConfig.getAlipayPublicKey(), "UTF-8"); // 验证签名
            // 支付宝验签
            if (checkSignature) {
                // 验签通过
//                System.out.println("交易名称: " + params.get("subject"));
//                System.out.println("交易状态: " + params.get("trade_status"));
//                System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
//                System.out.println("商户订单号: " + params.get("out_trade_no"));
//                System.out.println("交易金额: " + params.get("total_amount"));
//                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
//                System.out.println("买家付款时间: " + params.get("gmt_payment"));
//                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));

                // 查询订单
                LambdaQueryWrapper<PayOrders> queryWrapper =new LambdaQueryWrapper<>();
                queryWrapper.eq(PayOrders::getAlipayno, outTradeNo);
                PayOrders resultOrders = payOrdersDao.selectOne(queryWrapper);
                SimpleDateFormat dataT=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间
                if (resultOrders != null) {
                    resultOrders.setPaystate("已支付");
                    resultOrders.setPaytime(dataT.format(new Date()));
                    payOrdersDao.updateById(resultOrders);//
                    System.out.println("调用成功");
                }
            }
        }
        return "success";
    }
}



