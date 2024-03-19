package demo.userregistry.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService<Integer, UserDto> {
    private final UserDao userDao;
    private final UserMapper userMapper;

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
        return null;
    }

    @Override
    public ResponseEntity<String> delete(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<List<UserDto>> getAll() {
        List<User> list = this.userDao.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(List.of(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(list.stream().map(this.userMapper::toDto).toList(), HttpStatus.OK);
    }
}
