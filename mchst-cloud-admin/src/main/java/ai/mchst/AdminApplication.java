package ai.mchst;/*
 * @Auther:xueya
 * @Date:2023/12/16
 * @Description:
 */

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
@EnableKnife4j
public class AdminApplication {

    public static void main(String[] args) {

        SpringApplication.run(AdminApplication.class,args);
    }
}
