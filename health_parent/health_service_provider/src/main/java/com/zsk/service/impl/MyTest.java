package com.zsk.service.impl;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.zsk.dao.CheckItemDao;
import com.zsk.pojo.CheckItem;
import com.zsk.utils.QiNiuUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.net.www.content.image.png;

public class MyTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
        SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) context.getBean("sqlSessionFactory");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CheckItem checkItem = new CheckItem();
        checkItem.setName("11");
        checkItem.setCode("11");
        CheckItemDao mapper = sqlSession.getMapper(CheckItemDao.class);
        System.out.println(mapper);
        mapper.add(checkItem);
    }

    @Test
    public void utilsTest() {
//        QiNiuUtils.upload("孙悟空.png", "G:\\360MoveData\\Users\\Y7000\\Pictures\\壁纸\\七龙珠\\903993.png");
        QiNiuUtils.delete("孙悟空.png");
    }

    //上传
    @Test
    public void upload() {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region2());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        //...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "jXj4oumxfrB8lp4g9q2JGEfAccv9vTP7SKcQN8DD";
        String secretKey = "4D5eu490batU5EMTsw_-CPTgzfXYmtTmzhcm9S1E";
        String bucket = "zskhealth";
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "G:\\360MoveData\\Users\\Y7000\\Pictures\\壁纸\\七龙珠\\904443 (1).png";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = "孙悟空.jpg";

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    @Test
    public void deleteOne() {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region2());
        //...其他参数参考类注释

        String accessKey = "jXj4oumxfrB8lp4g9q2JGEfAccv9vTP7SKcQN8DD";
        String secretKey = "4D5eu490batU5EMTsw_-CPTgzfXYmtTmzhcm9S1E";
        String bucket = "zskhealth";
        String key = "孙悟空.jpg";

        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }

    //删除
    @Test
    public void deleteSome() {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region2());
        //...其他参数参考类注释
        String accessKey = "jXj4oumxfrB8lp4g9q2JGEfAccv9vTP7SKcQN8DD";
        String secretKey = "4D5eu490batU5EMTsw_-CPTgzfXYmtTmzhcm9S1E";
        String bucket = "zskhealth";

        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);

        try {
            //单次批量请求的文件数量不得超过1000
            String[] keyList = new String[]{
                    "孙悟空.jpg"
            };
            BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
            batchOperations.addDeleteOp(bucket, keyList);
            Response response = bucketManager.batch(batchOperations);
            BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);

            for (int i = 0; i < keyList.length; i++) {
                BatchStatus status = batchStatusList[i];
                String key = keyList[i];
                System.out.print(key + "\t");
                if (status.code == 200) {
                    System.out.println("delete success");
                } else {
                    System.out.println(status.data.error);
                }
            }
        } catch (QiniuException ex) {
            System.err.println(ex.response.toString());
        }
    }
}
