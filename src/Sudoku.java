
public class Sudoku {
    public static void main(String[] args) {
        int[][] sudoku = { { 0, 9, 0, 0, 5, 7, 0, 0, 0 }, { 2, 0, 0, 0, 0, 0, 0, 0, 5 }, { 0, 0, 0, 0, 0, 4, 0, 0, 6 },
                { 0, 6, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 5, 0, 6, 3, 4, 0, 0 }, { 3, 0, 0, 9, 0, 8, 0, 0, 0 },
                { 0, 3, 0, 0, 7, 0, 0, 8, 0 }, { 0, 2, 7, 0, 0, 9, 0, 0, 0 }, { 0, 4, 0, 8, 0, 1, 0, 5, 0 } };


        System.out.println("Ratkaisematon:");
        sudokuPrint(sudoku);
        solver(sudoku);
        System.out.println("");
        System.out.println("Ratkaistu:");
        sudokuPrint(sudoku);
    }

    //Ratkaisija joka kayttaa backtracking algoritmia. Jotta algoritmi toimii, taytyy ratkaisijan palauttaa totuusarvo.
    public static boolean solver(int[][] sudokuToSolve) {

        //etsii tyhjän
        Cordinate empty = findEmpty(sudokuToSolve);
        if (empty == null) {
            //jos ei ole tyhjää ruutua, palauttaa true, jolloin loop-purkautuu
            return true;
        }

        int row = empty.getY();
        int column = empty.getX();

        for (int i = 1; i <= 9; i++) {
            sudokuToSolve[row][column] = i;
            if (acceptable(sudokuToSolve, row, column, i)) {

                //Jos luku sopii, kutsuu ratkaisijaa uudestaan, jolloin ratkaisija siirtyy ratkaisemaan seuraavaa tyhjää ruutua
                if (solver(sudokuToSolve)) {
                    return true;
                }
                sudokuToSolve[row][column] = 0;
            } else {
                sudokuToSolve[row][column] = 0;
            }
        }
        //jos luku ei sovi, palauttaa false, jolloin palataan edelliseen ruutuun
        return false;
    }

    // Katsoo onko taulukkoon laitettava arvo hyvaksyttava
    public static boolean acceptable(int[][] board, int r, int c, int num) {

        // varmistaa onko rivilla samaa numeroa
        for (int i = 0; i < 9; i++) {
            if (board[i][c] == num && i != r) {
                return false;
            }
        }

        // varmistaa onko sarakkeessa samaa numeroa
        for (int i = 0; i < 9; i++) {
            if (board[r][i] == num && i != c) {
                return false;
            }
        }

        // varmistaa onko sudokun 3x3 laatikossa samaa lukua
        for (int i = 0; i < 3; i++) {
            int ybox = (r / 3) * 3 + i;
            for (int a = 0; a < 3; a++) {
                int xbox = (c / 3) * 3 + a;
                if (board[ybox][xbox] == num && r != ybox && c != xbox) {
                    return false;
                }
            }
        }
        //Jos luku täyttää ehdot, palauttaa true
        return true;
    }

    // Etsii tyhjän ruudun (ruudun jossa on 0)
    public static Cordinate findEmpty(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int a = 0; a < 9; a++) {
                if (board[i][a] == 0) {
                    return new Cordinate(i, a);
                }
            }
        }
        return null;
    }

    // tulostaa sudokun
    public static void sudokuPrint(int[][] printSudoku) {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                System.out.print(printSudoku[row][column] + "\t");
            }
            System.out.println();
        }
    }
}