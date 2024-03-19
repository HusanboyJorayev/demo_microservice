package demo.userregistry.user;

import demo.userregistry.feign.CardDto;
import demo.userregistry.feign.CardFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController implements UserService<Integer, UserDto> {
    private final UserServiceImpl userServiceImpl;
    private final CardFeign cardFeign;

    @Override
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody UserDto dto) {
        return this.userServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/get/{id}")
    public ResponseEntity<UserDto> get(@PathVariable("id") Integer id) {
        return this.userServiceImpl.get(id);
    }

    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody UserDto dto, @PathVariable("id") Integer id) {
        return this.userServiceImpl.update(dto, id);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        return this.userServiceImpl.delete(id);
    }

    @Override
    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getAll() {
        return this.userServiceImpl.getAll();
    }

    @GetMapping("/getAllCardsByUserId/{id}")
    public ResponseEntity<List<CardDto>> getAllCardsByUserId(@PathVariable("id") Integer userId) {
        return cardFeign.getAllCardsByUserId(userId);
    }
}

