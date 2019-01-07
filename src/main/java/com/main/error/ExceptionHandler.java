package com.main.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.common.MainReturn;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author long
 * @date 2018/12/14 16:28
 * 全局统一异常处理
 */
@Component
public class ExceptionHandler implements HandlerExceptionResolver {

    private Logger log = Logger.getLogger(ExceptionHandler.class);

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
            String trance = getStackTrace(ex);
            responseData.put("msg", ex.toString());
            responseData.put("trace", trance.replaceAll("<","[").replaceAll(">","]"));
            result = MainReturn.error(responseData, EmError.ERROR_UNKNOWN.getErrCode(), EmError.ERROR_UNKNOWN.getErrMsg());
            // 记录日志
            log.error(trance);
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

    private static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            throwable.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }
}
