package com.lovecc.userservice.exception;

import com.lovecc.common.dto.RespDTO;
import com.lovecc.common.exception.CommonException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class CommonExceptionHandler {
    /**
     * @ResponseEntity不仅可以返回json结果，还可以定义返回的HttpHeaders和HttpStatus
     * @param e
     * @return
     */
    @ExceptionHandler(CommonException.class)
    public ResponseEntity<RespDTO> handleException(Exception e) {
        RespDTO resp = new RespDTO();
        CommonException taiChiException = (CommonException) e;
        resp.code = taiChiException.getCode();
        resp.error = e.getMessage();
        return new ResponseEntity(resp, HttpStatus.OK);
    }
}
