package ru.job4j.lombok;

import lombok.*;

@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category {
    @Getter
    @NonNull
    @EqualsAndHashCode.Include
    private int id;
    @Getter
    @Setter
    private String name;
}