/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softserve.puzzle;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nubaseg
 */
public class Puzzle {

    /*
     * fields for recursive creation Class inst
     */
    public List current = new ArrayList(); //current way
    public int row; //current branch row
    public int col; //current branch col
    public int depth;
    Integer2 min; //expected min 
    /*
     * puzzle data
     */
    public boolean[][] locks; //current locks state
    public List shortest; //shortest way

    public Puzzle(boolean[][] locks, int min) {
        this.shortest = new ArrayList();
        this.locks = matrixCopy(locks);
        this.min = new Integer2();
        this.min.val = 8;
    }

    public Puzzle(Puzzle parentPuzzle, int row, int col) {
        this.row = row;
        this.col = col;
        this.depth = parentPuzzle.depth;
        this.current = new ArrayList(parentPuzzle.current);
        this.min = parentPuzzle.min;
        this.shortest = parentPuzzle.shortest;
        this.locks = matrixCopy(parentPuzzle.locks);
    }

    private boolean isOpen(boolean[][] locks) {
        for (boolean[] lockline : locks) {
            for (boolean lock : lockline) {
                if (!lock) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean[][] turn(boolean[][] locks, int row, int col) {

        locks[row][col] = !locks[row][col];
        for (int i = 0; i < locks.length; i++) {
            locks[i][col] = !locks[i][col];
        }
        for (int j = 0; j < locks.length; j++) {
            locks[row][j] = !locks[row][j];
        }
        return locks;
    }

    private static boolean[][] matrixCopy(boolean[][] locks) {
        boolean[][] newLocks = new boolean[locks.length][locks.length];
        for (int i = 0; i < locks.length; i++) {
            System.arraycopy(locks[i], 0, newLocks[i], 0, locks.length);
        }
        return newLocks;
    }

    public void solve() {
        for (int i = 0; i < locks.length; i++) {
            for (int j = 0; j < locks.length; j++) {
                Puzzle puzzle = new Puzzle(this, i,j);
                puzzle.recursiveSolve();
            }
        }
    }

    public void recursiveSolve() {
        
        if (!isOpen(locks)) {
            locks = turn(locks, row, col);
            current.add(new int[] { row, col });
            depth++;
            if (depth < min.val) {
                for (int i = 0; i < locks.length; i++) {
                    for (int j = 0; j < locks.length; j++) {
                        if (!(i == row && j == col)) {
                            Puzzle puzzle = new Puzzle(this, i,j);
                            puzzle.recursiveSolve();
                        }
                    }
                }
            }
        } else {
            if (min.val > depth) {
                min.val = depth;
                shortest.clear();
                shortest.addAll(current);
            }
        }
    }
}

class Integer2 {
    public int val;
}
