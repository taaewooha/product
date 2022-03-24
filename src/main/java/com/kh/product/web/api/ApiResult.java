package com.kh.product.web.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResult<T> {

    private  String rtcd;   //서버 처리 상태 코드
    private  String rtmsg;  //서버 처리 상태 메시지
    private T data;         //실제 전송되는 데이터

}
