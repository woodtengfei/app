package com.wood.app.controller;

import com.dd.plist.NSDictionary;
import com.dd.plist.PropertyListParser;
import com.wood.app.entity.UDIDEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * @Description: App UDID获取
 * @Author wood
 * @Date 2020-12-09
 */

@Controller
public class AppUDIDController {

    private final Logger logger = LoggerFactory.getLogger(AppUDIDController.class);

    @RequestMapping("/udid")
    public ModelAndView getUDID(HttpServletRequest request, HttpServletResponse response) {
        UDIDEntity entity = new UDIDEntity();
        try {
            // 类型是 application/pkcs7-signature 签名的信息
            logger.info(request.getContentType());

            // 获取输入流
            InputStream stream = request.getInputStream();
            byte[] buffer = new byte[512];
            StringBuilder builder = new StringBuilder();
            while (stream.read(buffer) != -1) {
                builder.append(new String(buffer));
            }
            // 输入流的字符串
            String string = builder.toString();
            // 获取plist
            String plistString = string.substring(string.indexOf("<?xml"), string.indexOf("</plist>") + 8);
            logger.info(plistString);
            // 用google的dd-plist解析plist格式文件
            NSDictionary rootDict = (NSDictionary)PropertyListParser.parse(plistString.getBytes());
            logger.info(rootDict.toString());
            // 将获取到的内容绑定实体
            if (rootDict.containsKey("IMEI")) {
                entity.setIMEI(rootDict.get("IMEI").toString());
            }
            if (rootDict.containsKey("PRODUCT")) {
                entity.setPRODUCT(rootDict.get("PRODUCT").toString());
            }
            if (rootDict.containsKey("UDID")) {
                entity.setUDID(rootDict.get("UDID").toString());
            }
            if (rootDict.containsKey("VERSION")) {
                entity.setVERSION(rootDict.get("VERSION").toString());
            }
            logger.info(entity.toString());

        } catch (Exception e) {
            logger.info("plist解析失败");
            e.printStackTrace();
        }
        // 301之后iOS设备会自动打开safari浏览器，不设置会导致app安装描述文件失败
        RedirectView redirectView = new RedirectView("udid.html");
        redirectView.setAttributesMap(entity.toMap());
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return new ModelAndView(redirectView);
    }

}


