/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softserve.puzzle;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Nubaseg
 */
public class PuzzleRunner {

    private static final int size = 4;
    private static final Random RND = new Random();

    public static void main(String[] args) {

        boolean[][] test = new boolean[size][size];

        test[0][0] = true;
        test[0][1] = true;
        test[0][2] = false;
        test[0][3] = false;
        test[1][0] = false;
        test[1][1] = true;
        test[1][2] = true;
        test[1][3] = true;
        test[2][0] = false;
        test[2][1] = true;
        test[2][2] = true;
        test[2][3] = true;
        test[3][0] = false;
        test[3][1] = true;
        test[3][2] = true;
        test[3][3] = true;

//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                test[i][j] = RND.nextBoolean();
//            }
//        }
        Puzzle puzzle = new Puzzle(test, 8);
        puzzle.solve();
        List result = puzzle.shortest;
        System.out.println(result.size());

        for (int i = 0; i < result.size(); i++) {
            int[] d = (int[]) result.get(i);
            System.out.println(d[0] + " " + d[1]);
        }

    }
}
