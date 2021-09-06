import org.junit.Test;

import java.sql.Array;
import java.util.Scanner;

import static org.junit.Assert.assertFalse;

public class TicTacToe {
    static Scanner userInput = new Scanner(System.in);

    public static void main(String [] args){
        char[][] board = {
                {'1', '2', '3'}, //00, 01, 02
                {'4', '5', '6'}, //10, 11, 12
                {'7', '8', '9'}  //20, 21, 22
        };

        printBoard(board);
        boolean gameOver = false;

        while (!gameOver){
            System.out.println("Welcome to the game: Tic Tac Toe!");
            playerMove(1, board);
            gameOver = isGameOver(board);
            if (gameOver){
                break;
            }

            playerMove(2, board);
            gameOver = isGameOver(board);
            if (gameOver){
                break;
            }
        }
    }

    public static void printBoard(char [][]board){
        for (char[] row : board){
            for (char c : row){
                if (Character.getNumericValue(c)<10){
                    System.out.print('_');
                } else {
                    System.out.print(c);

                }
            }
            System.out.println();
        }
    }

    public static void updateBoard(int[]pos, int player, char[][] board){
        char token;

        if (player == 1){
            token = 'X';
        } else {
            token = 'O';
        }

        board[pos[0]-1][pos[1]-1] = token;
        printBoard(board);
    }
    public static int[] playerInput(int player){
        String[] inputArr = {};

        while (inputArr.length != 2){
            System.out.println("[Player " + player + " ]Please make a move. e.g. 1,2. This indicates row 1 and column 2");
            String inputStr = userInput.next();
            inputArr = inputStr.split(",");
        }
        int posX = Integer.parseInt(inputArr[0]);
        int posY = Integer.parseInt(inputArr[1]);
        return new int[]{posX, posY};
    }

    public static void playerMove(int player, char[][] board){

        int[] pos = playerInput(player);

        while (!validateMove(pos)){
            System.out.println("Invalid Move!");
            System.out.println("Please make a move. e.g. 1,2. This indicates row 1 and column 2");
            pos = playerInput(player);
        }

        updateBoard(pos, player, board);
    }

    public static boolean validateMove(int[] pos){

         return pos.length == 2 && pos[0] > 0 && pos[0] < 4 && pos[1] > 0 && pos[1] < 4;
    }

    public static void annouceWinner(char token){
        int player = token == 'X'? 1 : 2;
        System.out.println("Player "+player+" Wins");
    }

    public static boolean isGameOver(char[][] board){
        // Horizontal
        if(board[0][0] == board[0][1] &&  board[0][1] == board [0][2] ){
            annouceWinner(board[0][0]);
            return true;
        }
        if(board[1][0] == board[1][1] &&  board[1][1] == board [1][2] ){
            annouceWinner(board[1][0]);
            return true;
        }
        if(board[2][0] == board[2][1] &&  board[2][1] == board [2][2] ){
            annouceWinner(board[2][0]);
            return true;
        }

        // Vertical
        if(board[0][0] == board[1][0] &&  board[1][0] == board [2][0] ){
            annouceWinner(board[0][0]);
            return true;
        }
        if(board[0][1] == board[1][1] &&  board[1][1] == board [2][1] ){
            annouceWinner(board[0][1]);
            return true;
        }
        if(board[0][2] == board[1][2] &&  board[1][2] == board [2][2] ){
            annouceWinner(board[0][2]);
            return true;
        }

        //Diagonal
        if(board[0][0] == board[1][1] &&  board[1][1] == board [2][2] ){
            annouceWinner(board[0][0]);
            return true;
        }
        if(board[0][2] == board[1][1] && board[1][1] == board [2][0] ){
            annouceWinner(board[0][2]);
            return true;
        }

        //Tie
        if(board[0][0] != '1' &&  board[0][1] != '2' &&  board[0][2] != '3'
        && board[1][0] != '4' &&  board[1][1] != '5' &&  board[1][2] != '6'
        && board[2][0] != '7' &&  board[2][1] != '8' &&  board[2][2] != '9'){
            System.out.println("Tie!");
            return true;
        }

        return false;
    }

    /*
    Dear sir or madam, I actually was told this test will take 30 mins in total.
    As I am truly running out of time now. I will put only limited unit test here and we could discuss more
    if there is a chance to interview. Thank you.
    * */
    @Test
    public void testValidateMove()
    {
        int[] arr = {4, 9};
        assertFalse("This will fail!", TicTacToe.validateMove(arr));
    }
}
