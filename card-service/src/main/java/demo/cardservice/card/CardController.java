package demo.cardservice.card;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/card")
public class CardController implements CardService<Integer, CardDto> {
    private final CardServiceImpl cardServiceImpl;

    @Override
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody CardDto dto) {
        return this.cardServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/get/{id}")
    public ResponseEntity<CardDto> get(@PathVariable(name = "id") Integer id) {
        return this.cardServiceImpl.get(id);
    }

    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody CardDto dto, @PathVariable("id") Integer id) {
        return this.cardServiceImpl.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        return this.cardServiceImpl.delete(id);
    }

    @Override
    @GetMapping("/getByUserId/{id}")
    public ResponseEntity<List<CardDto>> getAllCardsByUserId(@PathVariable("id") Integer userId) {
        return this.cardServiceImpl.getAllCardsByUserId(userId);
    }
}
