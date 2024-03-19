package demo.cardservice.card;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService<Integer, CardDto> {
    private final CardDao cardDao;
    private final CardMapper cardMapper;

    @Override
    public ResponseEntity<String> create(CardDto dto) {
        var card = this.cardMapper.toEntity(dto);
        card.setCreatedAt(LocalDateTime.now());
        this.cardDao.save(card);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CardDto> get(Integer id) {
        var optional = this.cardDao.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return new ResponseEntity<>(new CardDto(), HttpStatus.BAD_REQUEST);
        }
        var card = optional.get();
        return new ResponseEntity<>(this.cardMapper.toDto(card), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(CardDto dto, Integer id) {
        var optional = this.cardDao.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return new ResponseEntity<>("User is not found", HttpStatus.BAD_REQUEST);
        }
        var card = optional.get();
        card.setUpdatedAt(LocalDateTime.now());
        this.cardMapper.update(card, dto);
        this.cardDao.save(card);
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> delete(Integer id) {
        var optional = this.cardDao.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return new ResponseEntity<>("User is not deleted", HttpStatus.BAD_REQUEST);
        }
        var card = optional.get();
        card.setDeletedAt(LocalDateTime.now());
        this.cardDao.delete(card);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CardDto>> getAllCardsByUserId(Integer userId) {
        List<Card> list = this.cardDao.findByUserId(userId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(List.of(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(list.stream().map(this.cardMapper::toDto).toList(), HttpStatus.OK);
    }
}
