package demo.userregistry.user;

import demo.userregistry.feign.CardDto;
import demo.userregistry.feign.CardFeign;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService<Integer, UserDto> {
    private final UserDao userDao;
    private final UserMapper userMapper;
    private final CardFeign cardFeign;

    @Override
    public ResponseEntity<String> create(UserDto dto) {
        var user = this.userMapper.toEntity(dto);
        user.setCreatedAt(LocalDateTime.now());
        this.userDao.save(user);
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserDto> get(Integer id) {
        var optional = this.userDao.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return new ResponseEntity<>(new UserDto(), HttpStatus.BAD_REQUEST);
        }
        UserDto dto = this.userMapper.toDto(optional.get());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(UserDto dto, Integer id) {
        User user = this.userDao.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new RuntimeException("User is not found"));
        user.setUpdatedAt(LocalDateTime.now());
        this.userMapper.update(user, dto);
        this.userDao.save(user);
        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> delete(Integer id) {
        User user = this.userDao.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new RuntimeException("User is not found"));
        user.setDeletedAt(LocalDateTime.now());
        this.userDao.delete(user);

        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserDto>> getAll() {
        List<User> list = this.userDao.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(List.of(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(list.stream().map(this.userMapper::toDto).toList(), HttpStatus.OK);
    }

    @Override
    @CircuitBreaker(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
    public ResponseEntity<UserDto> getAllCardsByUserId(Integer userId) {
        List<CardDto> getAllCardsByUserId = this.cardFeign.getAllCardsByUserId(userId).getBody();
        Optional<User> optional = this.userDao.findByIdAndDeletedAtIsNull(userId);
        if (optional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var dto = this.userMapper.toDto(optional.get());
        dto.setCards(getAllCardsByUserId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    public String companyBreakerFallback(NoSuchMethodException e) {
        return "Dummy";
    }
}
