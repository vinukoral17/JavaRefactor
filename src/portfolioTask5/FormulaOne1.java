package portfolioTask5;

import java.util.Arrays;
import java.util.List;

public class FormulaOne1 {
    public static void main(String[] args) {
        List<Team> table = Arrays.asList(
            new Team(1, "Mercedes", 22, 16, 2, 4, 573, 398, 175, 48),
            new Team(2, "Red Bull Racing", 22, 12, 5, 5, 529, 394, 135, 43),
            new Team(3, "Ferrari", 22, 9, 4, 9, 448, 382, 66, 30),
            new Team(4, "McLaren", 22, 9, 3, 10, 435, 426, 9, 30),
            new Team(5, "Renault", 22, 8, 5, 9, 438, 414, 24, 29),
            new Team(6, "AlphaTauri", 22, 3, 6, 13, 314, 439, -125, 15),
            new Team(7, "Racing Point", 22, 2, 6, 14, 294, 453, -159, 8),
            new Team(8, "Alfa Romeo Racing", 22, 2, 4, 16, 221, 486, -265, 8),
            new Team(9, "Haas", 22, 1, 4, 17, 167, 519, -352, 7),
            new Team(10, "Williams", 22, 0, 1, 21, 107, 557, -450, 1)
        );

        table.forEach(x -> System.out.println(x));
    }
}
