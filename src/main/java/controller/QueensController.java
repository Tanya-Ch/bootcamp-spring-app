package controller;

import model.Queens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.QueensRepository;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class QueensController {
    @Autowired
    QueensRepository queensRepository;
    @GetMapping("/queens")
    public ResponseEntity<List<Queens>> getAllQueens(@RequestParam(required = false) String coord) {
        try {
            List<Queens> queens = new ArrayList<>();
            if (coord == null)
                queensRepository.findAll().forEach(queens::add);
            else
                queensRepository.findByCoordinates(coord).forEach(queens::add);
            if (queens.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(queens, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/queens/{id}")
    public ResponseEntity<Queens> getQueenById(@PathVariable("id") long id) {
        Queens queens = queensRepository.findById(id);
        if (queens != null) {
            return new ResponseEntity<>(queens, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/queen")
    public ResponseEntity<String> createQueen(@RequestBody Queens queens) {
        try {
            queensRepository.save(new Queens(queens.getCoord(), queens.getValue()));
            return new ResponseEntity<>("Tutorial was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/queens/{id}")
    public ResponseEntity<String> updateQueen(@PathVariable("id") long id, @RequestBody Queens queens) {
        Queens _queens = queensRepository.findById(id);
        if (_queens != null) {
            _queens.setId(id);
            _queens.setCoord(queens.getCoord());
            _queens.setValue(queens.getValue());
            queensRepository.update(_queens);
            return new ResponseEntity<>("Queen was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find Queen with id=" + id, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/queens/{id}")
    public ResponseEntity<String> deleteQueen(@PathVariable("id") long id) {
        try {
            int result = queensRepository.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find Queen with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Queen was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete queen.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/queens")
    public ResponseEntity<String> deleteAllQueens() {
        try {
            int numRows = queensRepository.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " Queen(s) successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete quens.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/queens/coord")
    public ResponseEntity<List<Queens>> findByCoordinates(String coord) {
        try {
            List<Queens> queens = queensRepository.findByCoordinates(coord);
            if (queens.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(queens, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
