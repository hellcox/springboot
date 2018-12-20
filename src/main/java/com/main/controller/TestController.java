package com.main.controller;

import com.main.common.MainReturn;
import com.main.dao.model.UserDO;
import com.main.error.EmMainError;
import com.main.error.FailException;
import com.main.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author long
 * @date 2018/12/18 16:04
 */

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private TestService testService;

    public static void main(String[] args) {
        System.out.println(1);
    }

    @RequestMapping("/test")
    public MainReturn test() {
        return MainReturn.success();
    }

    @RequestMapping("/json")
    public MainReturn json(){
        return MainReturn.success("json-data","提示消息");
    }

    @RequestMapping("/fail")
    public MainReturn fail() throws FailException {
        if(true){
            throw new FailException(EmMainError.FAIL, "业务失败");
        }
        return MainReturn.success("test");
    }

    @RequestMapping("/error")
    public MainReturn error(){
        int a = 1/0;
        return MainReturn.success("error","异常消息");
    }

    @RequestMapping("/request")
    public MainReturn request(){
        System.out.println(request.getRequestURI());
        return MainReturn.success(request.getRequestURI(),"request对象");
    }

    @RequestMapping("/param")
    public MainReturn param(@RequestParam(name = "param") String value){
        return MainReturn.success(value,"request对象");
    }

    @RequestMapping("/service")
    public MainReturn service(){
        boolean flag = testService.test();
        return MainReturn.success(flag,"service");
    }

    @RequestMapping("/shiwu")
    public MainReturn tran() throws FailException {
        testService.transactional();
        return MainReturn.success("事物");
    }

    @RequestMapping("/getuser")
    public MainReturn getUser(@RequestParam(name = "id") Integer id){
        UserDO userDO = testService.getUser(id);
        return MainReturn.success(userDO,"查询用户");
    }

}
