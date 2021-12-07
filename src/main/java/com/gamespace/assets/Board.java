package com.gamespace.assets;

public class Board  {

    private final int[][] board;
    private final int size;


    public Board(int size){
        this.size = size;
        board = new int[size][size];
    }

    public int[][] getBoard(){
        return board;
    }

    public void makeMove(Player player, int pos_x, int pos_y){
        if (pos_x > board.length)
            throw new IllegalArgumentException();
        else if (pos_y > board.length)
            throw new IllegalArgumentException();
        else if (board[pos_x-1][pos_y-1] != 0)
            throw new IllegalArgumentException();
        board[pos_x-1][pos_y-1] = player.getCharacter();
    }

    public void displayBoard()
    {
        for (int x = 0; x < this.board.length; x++)
        {
            for (int y = 0; y < this.board[0].length; y++)
                System.out.print(this.board[x][y] + " ");
            System.out.println();
        }
    }

    public int getSize() {
        return size;
    }
}
