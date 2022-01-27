import java.util.Random;
import java.util.Scanner;

public class HomeWorkApp {
    final static int LENGTH = 5;
    final static char DOT_EMPTY = '.';
    final static char DOT_X = 'x';
    final static char DOT_O = 'o';
    final static Scanner scanner = new Scanner(System.in);
    final static Random random = new Random();
    static String result;

    final static char[][] map = new char[LENGTH][LENGTH];

    public static void main(String[] args) {

        initMap();
        printMap();

        while (true) {
            turnHuman();
            printMap();

            if (checkMap()) {
                result = "Ничья";
                break;
            }
            if (checkWin(DOT_X)) {
                result = " Победа за человеком";
                break;
            }

            turnComputer();
            printMap();

            if (checkMap()) {
                result = "Ничья";
                break;
            }
            if (checkWin(DOT_O)) {
                result = " Победа за компьютером";
                break;
            }
        }
        System.out.println("Игра закончена" + result);
    }

    private static boolean checkWin(char field) {
        int diag1, diag2, hor, ver;
        for (int i = 0; i < LENGTH; i++) {
            hor = 0; ver = 0;
            for (int j = 0; j < LENGTH; j++) {
                if (map[i][j] == field) {
                    hor++;
                }
                if (map[j][i] == field) {
                    ver++;
                }
            }
            if (hor == LENGTH - 1 || ver == LENGTH - 1) {
                return true;
            }
        }
        diag1 = 0; diag2 = 0;
        for (int i = 0; i < LENGTH; i++) {
            if (map[i][i] == field) {
                diag1++;
            }
            if (map[i][LENGTH - i - 1] == field) {
                diag2++;
            }
        }
        if (diag1 == LENGTH - 1 || diag2 == LENGTH - 1) {
            return true;
        }
        return false;
    }

    private static boolean checkMap(){
        for (int i = 0; i < LENGTH; i++){
            for (int j = 0; j < LENGTH; j++){
                if (map[i][j] == DOT_EMPTY) return false;

            }
        }
        return true;
    }

    private static void turnComputer() {
        int x;
        int y;

        do {
            x = random.nextInt(LENGTH);
            y = random.nextInt(LENGTH);

            System.out.println("Компьютер сделал свой ход с координатами: " + (y + 1) + " " + (x + 1));
        } while (isNotValid(x, y));

        map[x][y] = DOT_O;

    }

    private static void turnHuman() {
        int x;
        int y;
        do {
            System.out.println("Введите координаты своего хода по оси Х и Y");
            y = scanner.nextInt() - 1;
            x = scanner.nextInt() - 1;

        } while (isNotValid(x, y));

        map[x][y] = DOT_X;
    }

    private static boolean isNotValid(int x, int y) {
        if (x < 0 || y < 0 || x >= LENGTH || y >= LENGTH) return true;
        if (map[x][y] == DOT_X || map[x][y] == DOT_O) return true;
        return false;
    }

    private static void printMap() {
        for (int i = 1; i <= LENGTH; i++) {
            System.out.print(" " + i + " ");
        }
        System.out.println();

        for (int i = 0; i < LENGTH; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < LENGTH; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void initMap() {
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }
}
// не смогла прописать интелект в задании 4***