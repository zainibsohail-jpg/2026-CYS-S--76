
import java.util.Scanner;

class Maze {

    boolean isWall(int r, int c) {

        if (r == 0 || r == 4 || c == 0 || c == 6) {
            return true;
        }

        return false;
    }

    void display(Pacman p, Enemy e, Food f) {

        for (int i = 0; i < 5; i++) {

            for (int j = 0; j < 7; j++) {

                if (p.row == i && p.col == j) {
                    System.out.print("P ");
                }
                else if (e.row == i && e.col == j) {
                    System.out.print("E ");
                }
                else if (f.row == i && f.col == j && !f.collected) {
                    System.out.print("* ");
                }
                else if (isWall(i, j)) {
                    System.out.print("# ");
                }
                else {
                    System.out.print("  ");
                }
            }

            System.out.println();
        }
    }
}

class Pacman {

    int row = 1;
    int col = 1;

    void move(char key, Maze m) {

        if (key == 'w' && !m.isWall(row - 1, col)) {
            row--;
        }
        else if (key == 's' && !m.isWall(row + 1, col)) {
            row++;
        }
        else if (key == 'a' && !m.isWall(row, col - 1)) {
            col--;
        }
        else if (key == 'd' && !m.isWall(row, col + 1)) {
            col++;
        }
    }
}

class Enemy {

    int row = 3;
    int col = 5;

    void move(Pacman p, Maze m) {

        if (row < p.row && !m.isWall(row + 1, col)) {
            row++;
        }
        else if (row > p.row && !m.isWall(row - 1, col)) {
            row--;
        }
        else if (col < p.col && !m.isWall(row, col + 1)) {
            col++;
        }
        else if (col > p.col && !m.isWall(row, col - 1)) {
            col--;
        }
    }
}

class Food {

    int row = 1;
    int col = 5;

    boolean collected = false;

    void check(Pacman p) {

        if (p.row == row && p.col == col) {
            collected = true;
        }
    }
}

public class p1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Maze maze = new Maze();
        Pacman pacman = new Pacman();
        Enemy enemy = new Enemy();
        Food food = new Food();

        boolean game = true;

        System.out.println("===== PACMAN GAME =====");
        System.out.println("P = Pacman");
        System.out.println("E = Enemy");
        System.out.println("* = Food");
        System.out.println("W = Up");
        System.out.println("S = Down");
        System.out.println("A = Left");
        System.out.println("D = Right");
        System.out.println("Q = Quit");

        while (game) {

            maze.display(pacman, enemy, food);

            System.out.print("Enter your move: ");
            char key = sc.next().toLowerCase().charAt(0);

            if (key == 'q') {
                System.out.println("Game Quit!");
                break;
            }

            pacman.move(key, maze);

            food.check(pacman);

            if (pacman.row == enemy.row &&
                    pacman.col == enemy.col) {

                System.out.println("Enemy caught Pacman!");
                game = false;
            }
            else if (food.collected) {

                System.out.println("You collected the food!");
                System.out.println("YOU WIN!");
                game = false;
            }
            else {

                enemy.move(pacman, maze);

                if (pacman.row == enemy.row &&
                        pacman.col == enemy.col) {

                    System.out.println("Enemy caught Pacman!");
                    game = false;
                }
            }
        }

        sc.close();
    }
}
