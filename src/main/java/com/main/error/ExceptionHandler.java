package com.main.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.common.MainReturn;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LX
 * @date 2018/12/14 16:28
 * 全局统一异常处理
 */
@Component
public class ExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception ex) {

        Map<String, Object> responseData = new HashMap<>();
        if (ex instanceof MainException) {
            MainException businessException = (MainException) ex;
            responseData.put("errCode", businessException.getErrCode());
            responseData.put("errMsg", businessException.getErrMsg());
        } else {
            responseData.put("errCode", EmMainError.UNKNOW_ERROR.getErrCode());
            responseData.put("errMsg", EmMainError.UNKNOW_ERROR.getErrMsg());
            responseData.put("stackTrace", ex);
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();

            MainReturn result = MainReturn.create(responseData, "fail");
            String json =  mapper.writeValueAsString(result);

            writer.write(json);
            writer.flush();
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
