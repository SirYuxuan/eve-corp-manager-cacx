package com.yuxuan66.ecmc.common.esi;

import lombok.extern.slf4j.Slf4j;
import net.troja.eve.esi.ApiCallback;
import net.troja.eve.esi.ApiException;

import java.util.List;
import java.util.Map;

/**
 * 默认的Callback实现，不做任何处理
 *
 * @author Sir丶雨轩
 * @since 2022/12/14
 */
@Slf4j
public class DefalutApiCallback implements ApiCallback<Integer> {
    @Override
    public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
        log.error("ESI请求失败,Code: {},Body: {}", e.getCode(), e.getResponseBody());
    }

    @Override
    public void onSuccess(Integer result, int statusCode, Map<String, List<String>> responseHeaders) {
        log.info("ESI请求成功,Code: {}", statusCode);
    }

    @Override
    public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

    }

    @Override
    public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

    }
}
