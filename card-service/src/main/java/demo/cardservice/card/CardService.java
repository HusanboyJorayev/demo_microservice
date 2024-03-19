package demo.cardservice.card;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CardService<K, V> {
    ResponseEntity<String> create(V dto);

    ResponseEntity<V> get(K id);

    ResponseEntity<String> update(V dto, K id);

    ResponseEntity<String> delete(K id);

    ResponseEntity<List<V>> getAllCardsByUserId(K userId);
}
