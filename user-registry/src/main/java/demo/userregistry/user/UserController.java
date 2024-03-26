package demo.userregistry.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
@Tag(name = "USER-SERVICE END PINTS")
public class UserController implements UserService<Integer, UserDto> {

    private final UserServiceImpl userServiceImpl;

    // http://localhost:8080/api/user/create -> user
    // http://localhost:8080/api/card/create -> card

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
    @Operation(
            description = "Get all cards by userId end point for user",
            summary = "Summary for get end point")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDto.class
                            ))
            })
    })

    public ResponseEntity<UserDto> getAllCardsByUserId(@PathVariable("id") Integer userId) {
        return userServiceImpl.getAllCardsByUserId(userId);
    }
}

