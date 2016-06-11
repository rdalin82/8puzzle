import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Queue;


public class Board {
    private int N;
    private int row;
    private int hamming;
    private int manhattan;
    private int[][] board;
    

    public Board(int[][] blocks){
        if (blocks == null){
            throw new NullPointerException("no argument passed");
        }
        this.row = blocks.length;
        
        this.board = blocks;
        int N = row*row;       
        cacheManhattan();
       
    }
    public int dimension(){
        return row;
    }
    public int hamming(){
        hamming = 0;
        int[][] gb = goalboard();
        for (int x = 0; x < row; x++){
            for (int y = 0; y< row; y++){
                if (board[x][y] == 0){
                    continue;
                } else if (board[x][y] != gb[x][y]){
                    hamming++;
                }
            }
        }
        return hamming;
    }
    public int manhattan(){
        return manhattan;
    }
    public boolean isGoal(){
        
        if (hamming() ==0){
            return true;
        }
        return false;
    }
    public Board twin(){
        boolean control = true;
        Board twinBoard;
        while (control){
            int random = StdRandom.uniform(1, row*row);
            
            int adjust;
            if (random/row == 0){
                adjust = 1;
            } else if (random/row == row){
                adjust = -1;
            } else {
                adjust = -1;
            }
            if (board[random/row][random%row]!=0 && board[(random/row)+adjust][random%row]!=0){
                control = false;
                int[][] temp = copyboard(board);
                int swapvalue = board[(random/row)+adjust][random%row];
                temp[(random/row)+adjust][random%row] = board[random/row][random%row];
                temp[random/row][random%row] = swapvalue;
                twinBoard = new Board(temp);
                return twinBoard;
            }
        }
        
        return null;
        
    }
    public boolean equals(Object y){
        if (y ==null){
            return false;
        }
        if (y == this){
            return true;
        }
        if (this.getClass() != y.getClass()) return false;
        Board compare = (Board) y;
        if (compare.N != this.N){
            return false;
        }
        if (compare.board.length != this.board.length){
            return false;
        }
        if (this.N != compare.N)return false;
        for (int x = 0;x<compare.board.length;x++){
            for (int j =0;j<compare.board.length;j++){
                if (compare.board[x][j] != board[x][j]){
                    return false;
                }
            }
        }
        return true;
    }
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(row+"\n");
        String output = "";
        for (int x = 0; x < row;x++){
            for (int y =0; y< row;y++){
                s.append(String.format("%2d ", board[x][y]));
            }
            s.append("\n");
        }
        return s.toString();
    }
   
    public Iterable<Board> neighbors(){
        Queue<Board> q = new Queue<Board>();
        int[] zeroPosition = findPosition(0, board);
     
        if (zeroPosition[0]>0) q.enqueue(getSwapWithUpper(zeroPosition));
        if (zeroPosition[1]+1 < row) q.enqueue(getSwapWithRight(zeroPosition));
        if (zeroPosition[1] > 0) q.enqueue(getSwapWithLeft(zeroPosition));
        if (zeroPosition[0]+1 < row) q.enqueue(getSwapWithLower(zeroPosition));
        //upper -> right -> left -> lower
        return q;
        
    }
    
    private int[][] copyboard(int[][] origin){
        int[][] temp = new int[origin.length][origin.length];
        for (int x = 0;x<origin.length;x++){
            for (int y=0;y<origin.length;y++){
                temp[x][y] = origin[x][y];
            }
        }
        return temp;
    }
    private int[][] goalboard(){
        int[][] goalboard = new int[row][row];
        int counter =0;
        for (int x = 0; x < row;x++){
            for (int y =0; y< row;y++){
                goalboard[x][y] = counter+1;
                counter++;
            }
        }
        goalboard[row-1][row-1] = 0;
        return goalboard;
    }
    private Board getSwapWithUpper(int[] pos){
        int[][] temp = copyboard(board);
        int swapvalue = temp[pos[0]-1][pos[1]];
        temp[pos[0]-1][pos[1]] = 0;
        temp[pos[0]][pos[1]] = swapvalue;
        Board newBoard = new Board(temp);
        return newBoard;
    }
    private Board getSwapWithRight(int[] pos){
        int[][] temp = copyboard(board);
        int swapvalue = temp[pos[0]][pos[1]+1];
        temp[pos[0]][pos[1]+1] = 0;
        temp[pos[0]][pos[1]] = swapvalue;
        Board newBoard = new Board(temp);
        return newBoard;
    }
    private Board getSwapWithLeft(int[] pos){
        int[][] temp = copyboard(board);
        int swapvalue = temp[pos[0]][pos[1]-1];
        temp[pos[0]][pos[1]-1] = 0;
        temp[pos[0]][pos[1]] = swapvalue;
        Board newBoard = new Board(temp);
        return newBoard;
    }
    private Board getSwapWithLower(int[] pos){
        int[][] temp = copyboard(board);
        int swapvalue = temp[pos[0]+1][pos[1]];
        temp[pos[0]+1][pos[1]] = 0;
        temp[pos[0]][pos[1]] = swapvalue;
        Board newBoard = new Board(temp);
        return newBoard;
    }
    
    // (y, x) coordinates
    private int[] findPosition(int key, int[][] board){
        int[] position = new int[2];
        for (int y = 0; y<board.length;y++){
            for (int x=0;x<board.length;x++){
                if(key == board[y][x]){
                    position[0] = y;
                    position[1] = x;
                }
            }
        }
        return position;
    }
    private void cacheManhattan(){
        manhattan = 0;
        int[][] gb = goalboard();
        for (int x = 0; x<board.length;x++){
            for (int y =0; y<board.length;y++){
                if (board[x][y] ==0){
                    continue;
                }
                else {
                    int[] pos1 = findPosition(board[x][y], board);
                    int[] pos2 = findPosition(board[x][y], gb);
                    int distance = Math.abs((pos2[0]-pos1[0]))+Math.abs((pos2[1]-pos1[1])); 
                    manhattan = manhattan + distance;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        int[][] blocks = new int[3][3];
        blocks[0][0] = 0;
        blocks[0][1] = 1;
        blocks[0][2] = 2;
        blocks[1][0] = 4;
        blocks[1][1] = 5;
        blocks[1][2] = 3;
        blocks[2][0] = 7;
        blocks[2][1] = 8;
        blocks[2][2] = 6;
        Board b = new Board(blocks);
        System.out.println("origin board: \n" + b.toString());
        System.out.println("Hamming: " + b.hamming());
        System.out.println("Manhattan: " + b.manhattan());
        
 
        
        

    }

}
