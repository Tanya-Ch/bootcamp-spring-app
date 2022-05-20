package model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Slf4j
@Entity
@AllArgsConstructor
public class Queens {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String coord;

    private int value;

    public Queens(String coord, int value) { }

    public Queens() {

    }
}
