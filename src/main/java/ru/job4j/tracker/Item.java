package ru.job4j.tracker;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name = "items")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
    private String name;
    private LocalDateTime created = LocalDateTime.now();
}