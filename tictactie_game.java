import java.util.Scanner;
public class tictactie_game {
    private static char[][] board = new char[4][4];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        initializeBoard();

        Scanner scanner = new Scanner(System.in);

        boolean playAgain = true;
        System.out.println("_________________________________________________________________\n");

        System.out.println("*************** Welcome To Tic Tac Tie Game ***************");
        System.out.println("_________________________________________________________________");

        while (playAgain) {
            initializeBoard();
            boolean gameOver = false;
            int moves = 0;

            while (!gameOver) {
                displayBoard();
                int row, col;

                do {
                    System.out.println("Player " + currentPlayer + ", enter row (1, 2, 3) and column (1, 2, 3): ");
                    row = scanner.nextInt();
                    col = scanner.nextInt();
                } while (!isValidMove(row, col));

                board[row][col] = currentPlayer;
                moves++;

                if (checkWin(row, col)) {
                    displayBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameOver = true;
                } else if (moves == 9) {
                    displayBoard();
                    System.out.println("It's a tie!");
                    gameOver = true;
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainResponse = scanner.next().toLowerCase();
            if (!playAgainResponse.equals("yes")) {
                playAgain = false;
            }
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }

    private static void initializeBoard() {
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void displayBoard() {
        System.out.println("-------------");

        for (int i = 1; i < 4; i++) {
            System.out.print("| ");
            for (int j = 1; j < 4; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    private static boolean isValidMove(int row, int col) {
        if (row < 1 || row >= 4 || col < 1 || col >= 4) {
            System.out.println("Invalid move. Row and column must be between 1 and 3.");
            return false;
        }
        if (board[row][col] != ' ') {
            System.out.println("Invalid move. Cell is already occupied.");
            return false;
        }
        return true;
    }

    private static boolean checkWin(int row, int col) {
        boolean rowWin = true;
        boolean colWin = true;
        boolean mainDiagonalWin = true;
        boolean antiDiagonalWin = true;

        
        for (int i = 1; i < 4; i++) {
            if (board[row][i] != currentPlayer) {
                rowWin = false;
            }
            if (board[i][col] != currentPlayer) {
                colWin = false;
            }
            if (board[i][i] != currentPlayer) {
                mainDiagonalWin = false;
            }
            if (board[i][4 - i] != currentPlayer) {
                antiDiagonalWin = false;
            }
        }

        return rowWin || colWin || mainDiagonalWin || antiDiagonalWin;
    }
}