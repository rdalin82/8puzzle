import static org.junit.Assert.*;

import org.junit.Test;

import edu.princeton.cs.algs4.MinPQ;


public class Boardtest {

    @Test
    public void test() {
        int[][] blocks = new int[3][3];
        blocks[0][0] = 1;
        blocks[0][1] = 2;
        blocks[0][2] = 3;
        blocks[1][0] = 4;
        blocks[1][1] = 5;
        blocks[1][2] = 0;
        blocks[2][0] = 6;
        blocks[2][1] = 7;
        blocks[2][2] = 8;
        int[][] blocks2 = new int[3][3];
        blocks2[0][0] = 2;
        blocks2[0][1] = 1;
        blocks2[0][2] = 3;
        blocks2[1][0] = 4;
        blocks2[1][1] = 5;
        blocks2[2][0] = 7;
        blocks2[2][1] = 8;
        blocks2[2][2] = 0;
        blocks2[1][2] = 0;
        int[][] goalblocks = new int[3][3];
        goalblocks[0][0] = 1;
        goalblocks[0][1] = 2;
        goalblocks[0][2] = 3;
        goalblocks[1][0] = 4;
        goalblocks[1][1] = 5;
        goalblocks[1][2] = 6;
        goalblocks[2][0] = 7;
        goalblocks[2][1] = 8;
        goalblocks[2][2] = 0;
        Board b = new Board(blocks);
        Board same = new Board(blocks);
        Board diff = new Board(blocks2);
        Board goal = new Board(goalblocks);
        assertEquals(3, b.hamming());
        assertEquals(5, b.manhattan());
        assertEquals(true, b.equals(b));
        assertEquals(true, b.equals((Object) same));
        assertEquals(false, b.equals((Object) diff));
        assertEquals(true, goal.isGoal());
        System.out.println("Start: " + b);
        System.out.println("neighbors: " + b.neighbors());
        System.out.println("Twins: " + b.twin());
    }
    
    @Test
    public void goalTest(){
        int[][] blocks = new int[3][3];
        blocks[0][0] = 1;
        blocks[0][1] = 2;
        blocks[0][2] = 3;
        blocks[1][0] = 4;
        blocks[1][1] = 5;
        blocks[1][2] = 6;
        blocks[2][0] = 7;
        blocks[2][1] = 8;
        blocks[2][2] = 0;
        Board goal = new Board(blocks);
        assertEquals(true, goal.isGoal());
    }

}
