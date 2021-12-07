package com.gamespace.assets;
import java.util.*;

public class Game {

    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private final Board board;
    private GameStatus gameStatus;
    private final int maxMoves;
    private int playedMoves;
    private final int[] rowSums;
    private final int[] colSums;
    private final int[] diagonalSums;

    public Game(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Board(3);
        this.gameStatus = GameStatus.UNRESOLVED;
        this.currentPlayer = player1;
        this.maxMoves = board.getBoard().length * board.getBoard().length;
        this.playedMoves = 0;
        this.rowSums = new int[3];
        this.colSums = new int[3];
        this.diagonalSums = new int[2];
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void incrementMove()
    {
        this.playedMoves = this.playedMoves + 1;
    }


    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Board getBoard() {
        return board;
    }

    public Player switchAndGetCurrentPlayer()
    {
        if (this.currentPlayer.get_id().equals(PlayerID.Player1))
            this.currentPlayer = this.player2;
        else
            this.currentPlayer = this.player1;
        return currentPlayer;
    }

    public Player getCurrentPlayer()
    {
        return this.currentPlayer;
    }

    public int updateRowColDiagSum(int x, int y, Player p)
    {
        this.rowSums[x - 1] = rowSums[x - 1] + p.getCharacter();
        this.colSums[y - 1] = colSums[y - 1] + p.getCharacter();
        if (x == y)
            diagonalSums[0] = diagonalSums[0] + p.getCharacter();
        else if (this.board.getSize() + 1 - y == x)
            diagonalSums[1] = diagonalSums[1] + p.getCharacter();
        if (this.rowSums[x - 1] == 3 || this.colSums[y-1] == 3 || this.diagonalSums[0] == 3 || this.diagonalSums[1] == 3)
            return 1;
        else if (this.rowSums[x - 1] == -3 || this.colSums[y-1] == -3 || this.diagonalSums[0] == -3 || this.diagonalSums[1] == -3)
            return -1;
        else
            return 0;
    }

    public int getMaxMoves() {
        return maxMoves;
    }
    public int getPlayedMoves()
    {
        return this.playedMoves;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Player player1 = new Player("Player1",PlayerID.Player1,"player1");
        Player player2 = new Player("Player2",PlayerID.Player2,"player2");
        Game game = new Game(player1, player2);
        System.out.println(game.getCurrentPlayer().getName());
        Player currentPlayer = game.getCurrentPlayer();
        while (game.getGameStatus().equals(GameStatus.UNRESOLVED)){
            System.out.println(currentPlayer.getName() + " Enter the position you want to place :");
            int x = sc.nextInt();
            int y = sc.nextInt();
            game.getBoard().makeMove(currentPlayer,x,y);
            game.incrementMove();
            game.getBoard().displayBoard();
            int outcome = game.updateRowColDiagSum(x,y,currentPlayer);
            if (outcome == 1)
            {
                game.setGameStatus(GameStatus.P1_WINNER);
                System.out.println("Player 1 wins");
                break;
            }
            else if (outcome == -1)
            {
                game.setGameStatus(GameStatus.P2_WINNER);
                System.out.println("Player 2 wins");
                break;
            }
            if (game.getPlayedMoves() >= game.getMaxMoves())
            {
                game.setGameStatus(GameStatus.TIE);
                System.out.println("It's a tie");
                break;
            }
            currentPlayer = game.switchAndGetCurrentPlayer();
        }
    }
}
