package com.example.community.community.provider;

import cn.ucloud.ufile.UfileClient;
import cn.ucloud.ufile.api.object.ObjectConfig;
import cn.ucloud.ufile.auth.BucketAuthorization;
import cn.ucloud.ufile.auth.ObjectAuthorization;
import cn.ucloud.ufile.auth.UfileBucketLocalAuthorization;
import cn.ucloud.ufile.auth.UfileObjectLocalAuthorization;
import cn.ucloud.ufile.bean.PutObjectResultBean;
import cn.ucloud.ufile.exception.UfileClientException;
import cn.ucloud.ufile.exception.UfileServerException;
import cn.ucloud.ufile.http.OnProgressListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;

@Service
public class UcloudProvider {
    @Value("${ucloud.ufile.public.key}")
    private String publicKey;
    @Value("${ucloud.ufile.private.key}")
    private String privateKey;
    private String bucketName="qilaoshi";

    public String upload(InputStream fileStream, String mimeType,String originalFileName){
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        String fileName = System.currentTimeMillis() + "." + type;

        try {
            // 对象相关API的授权器
            ObjectAuthorization OBJECT_AUTHORIZER = new UfileObjectLocalAuthorization(
                    publicKey, privateKey);
            ObjectConfig config = new ObjectConfig("cn-bj", "ufileos.com");
            PutObjectResultBean response = UfileClient.object(OBJECT_AUTHORIZER, config)
                    .putObject(fileStream, mimeType)
                    .nameAs(fileName)
                    .toBucket("qilaoshi")
                    /**
                     * 是否上传校验MD5, Default = true
                     */
                    //  .withVerifyMd5(false)
                    /**
                     * 指定progress callback的间隔, Default = 每秒回调
                     */
                    //  .withProgressConfig(ProgressConfig.callbackWithPercent(10))
                    /**
                     * 配置进度监听
                     */
                    .setOnProgressListener(new OnProgressListener() {
                        @Override
                        public void onProgress(long bytesWritten, long contentLength) {

                        }
                    })
                    .execute();
                    if (response!=null&&response.getRetCode()==0){
                        String url = UfileClient.object(OBJECT_AUTHORIZER, config)
                                .getDownloadUrlFromPrivateBucket(fileName, bucketName, 24*60*60*365*10)
                                .createUrl();
                        return url;
                    }
                    else {
                        return "上传失败";
                    }
        } catch (UfileClientException e) {
            e.printStackTrace();
            return "上传失败";
        } catch (UfileServerException e) {
            e.printStackTrace();
            return "上传失败";
        }
    }
}
