package demo.cardservice.balanced;

import feign.Feign;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;

@LoadBalancerClient(name = "CARD-SERVICE",configuration = CustomLoadBalanced.class)
public class CardBalancer {

    @Bean
    @LoadBalanced
    public Feign.Builder feignBuilder(){
        return Feign.builder();
    }

}
