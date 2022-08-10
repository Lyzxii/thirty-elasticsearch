package com.elasticSearch.thirty.elasticSearch;

import com.alibaba.fastjson.JSON;
import org.apache.catalina.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * @author ：yanpeidong371
 * @description：
 * @date : 2022年03月11日
 * @since: 1.0.0
 */
public class ElasticSearchApplication {

    public static void main(String[] args) throws IOException {
        //doIndex();
        doDocument();

    }

    private void buildClient() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("111.231.139.97", 9200, "http")));
        System.out.println(client);
        // 关闭客户端连接
        client.close();

    }

    /**
     * 索引操作
     * 索引-文档 映射
     */
    private static void doIndex() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("111.231.139.97", 9200, "http")));

        // 创建索引
        CreateIndexRequest request = new CreateIndexRequest("share");
        CreateIndexResponse response = client.indices().create(request,
                RequestOptions.DEFAULT);
        boolean acknowledged = response.isAcknowledged();
        System.out.println("操作状态 = " + acknowledged);

        //查询索引
        GetIndexRequest request1 = new GetIndexRequest("share");
        GetIndexResponse response1 = client.indices().get(request1,
                RequestOptions.DEFAULT);
        System.out.println("response:"+ JSON.toJSONString(response1));


        // 删除索引
        DeleteIndexRequest request2 = new DeleteIndexRequest("share");
        AcknowledgedResponse response2 = client.indices().delete(request2,RequestOptions.DEFAULT);
        System.out.println("操作结果 ： " + response2.isAcknowledged());

    }

    /**
     * 文档操作
     */
    private static void doDocument() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("111.231.139.97", 9200, "http")));
        // 新增文档 - 请求对象
        IndexRequest request = new IndexRequest();

        // 创建数据对象
        User user = null;
//        user.setName("jack");
//        user.setAge(30);
//        user.setSex("男");

        // 设置索引及唯一性标识
        request.index("user").id("1001");
        // 添加文档数据，数据格式为 JSON 格式
        request.source(JSON.toJSONString(user), XContentType.JSON);
        // 客户端发送请求，获取响应对象
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        System.out.println("response:" + JSON.toJSONString(response));

    }
}
