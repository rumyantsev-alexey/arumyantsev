package ru.job4j.frog;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = "parent")
@EqualsAndHashCode(exclude = "parent")
public class Point {

    @NonNull
    @Getter
    private int x;

    @NonNull
    @Getter
    private int y;

    @Getter
    @Setter
    private Point parent;

}
