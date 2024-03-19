package demo.userregistry.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("CARD-SERVICE")
public interface CardFeign {
    @GetMapping("api/card/getByUserId/{id}")
    ResponseEntity<List<CardDto>> getAllCardsByUserId(@PathVariable("id") Integer userId);
}
