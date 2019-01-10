package com.main.controller;

import com.main.common.MainReturn;
import com.main.controller.viewobject.UserVO;
import com.main.error.EmError;
import com.main.error.FailException;
import com.main.service.TestService;
import com.main.service.model.UserModel;
import com.main.util.DateUtil;
import com.main.util.RedisUtil;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.applet.Main;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author long
 * @date 2018/12/18 16:04
 */

@RestController
@RequestMapping("/test")
public class TestController {

    private static Logger log = Logger.getLogger(TestController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private TestService testService;

    @Resource
    private RedisUtil redisUtil;

    public static void main(String[] args) {
        System.out.println(1);
    }

    @RequestMapping("/redis")
    public MainReturn redis() {

        Map<String, Object> map = new HashMap<>();
        map.put("key", "value");

        redisUtil.set("key", "value");
        redisUtil.set("user", new UserModel());
        redisUtil.set("map", map);
        Object value = redisUtil.get("key");
        Map omap = (Map) redisUtil.get("map");
        System.out.println(omap);

        UserModel userModel = (UserModel) redisUtil.get("user");
        System.out.println(userModel);

        return MainReturn.success(value);
    }

    @RequestMapping("/date")
    public MainReturn date() {
        String dateString = "2018-01-01 00:00:00";

        Map<String, Object> data = new HashMap<>();
        data.put("date", DateUtil.getDateString());
        data.put("timestamp", DateUtil.getTimestamp());
        data.put("dateString", dateString);

        dateString = "2018112203303";
        data.put(dateString, DateUtil.strToTimestamp(dateString));
        dateString = "2018112203304";
        data.put(dateString, DateUtil.dateStringFormat(dateString));

        return MainReturn.success(data, "日期工具类");
    }

    @RequestMapping("/insert")
    public MainReturn insert() {
        testService.insertMore();
        return MainReturn.success();
    }

    @RequestMapping("/sqlerror")
    public MainReturn sqlError() {
        testService.sqlError();
        return MainReturn.success();
    }

    @RequestMapping("/test")
    public MainReturn test() {
        return MainReturn.success();
    }

    @RequestMapping("/json")
    public MainReturn json() {
        return MainReturn.success("json-data", "提示消息");
    }

    @RequestMapping("/fail")
    public MainReturn fail() throws FailException {
        if (true) {
            throw new FailException(EmError.FAIL, "业务失败");
        }
        return MainReturn.success("test");
    }

    @RequestMapping("/error")
    public MainReturn error() {

        //java.lang.ArithmeticException: / by zero
        int a = 1 / 0;

        //java.lang.ArrayIndexOutOfBoundsException: 3
        Integer[] i = {1, 2, 3};
        for (int j = 1; j <= 3; j++) {
            System.out.println(i[j]);
        }
        return MainReturn.success("error", "异常消息");
    }

    @RequestMapping("/request")
    public MainReturn request() {
        System.out.println(request.getRequestURI());
        return MainReturn.success(request.getRequestURI(), "request对象");
    }

    @RequestMapping("/param")
    public MainReturn param(@RequestParam(name = "param") String value) {
        return MainReturn.success(value, "request对象");
    }

    @RequestMapping("/service")
    public MainReturn service() {
        boolean flag = testService.test();
        return MainReturn.success(flag, "service");
    }

    @RequestMapping("/shiwu")
    public MainReturn tran() throws FailException {
        testService.transactional();
        return MainReturn.success("事物");
    }

    @RequestMapping("/getuser")
    public MainReturn getUser(@RequestParam(name = "id") Integer id) throws FailException {
        UserModel userModel = testService.getUser(id);
        UserVO userVO = this.convertFromModel(userModel);
        return MainReturn.success(userVO, "通过ID查询用户");
    }

    @RequestMapping("/log")
    public MainReturn log() {
        //控制台输出 和 日志入库
        log.debug(1);
        log.info(2);
        log.error("error 日志配置为直接入库");
        return MainReturn.success("LOG4J 日志");
    }

    private UserVO convertFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        userVO.setGmtCreate(userModel.getGmtCreate().toLocalDate().toString());
        return userVO;
    }

}
