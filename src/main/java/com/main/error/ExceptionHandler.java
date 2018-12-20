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
 * @author long
 * @date 2018/12/14 16:28
 * 全局统一异常处理
 */
@Component
public class ExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception ex) {

        MainReturn result = new MainReturn();
        Map<String, Object> responseData = new HashMap<>();
        if (ex instanceof FailException) {
            // 业务异常
            FailException failException = (FailException) ex;
            result = MainReturn.fail(responseData, failException.getErrCode(), failException.getErrMsg());
        } else {
            // 程序异常
            responseData.put("message", ex.getMessage());
            responseData.put("localizedMessage", ex.getLocalizedMessage());
            result = MainReturn.error(responseData, EmMainError.ERROR_UNKNOWN.getErrCode(), EmMainError.ERROR_UNKNOWN.getErrMsg());
            // 记录日志
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            String json = mapper.writeValueAsString(result);

            writer.write(json);
            writer.flush();
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
