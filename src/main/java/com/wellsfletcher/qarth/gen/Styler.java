package com.wellsfletcher.qarth.gen;

// QR code generation
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

// graphics
import java.awt.Color;
import java.util.*;

/**
 * Used to apply specific style types to collections of QRNodes.
 */
public class Styler {
    private Styler() {}
    private static int CLOVER = 0xFF108001;
    private static int SPRING = 0xFF21FF06;
    private static int SALMON = 0xFFFD6666;
    private static int FLORA = 0xFF66FF66;
    private static int SKY = 0xFF66CCFF;
    private static int BUBBLEGUM = 0xFFFD66FF;

    private static int BLACK = 0xFF000000;
    private static int WHITE = 0xFFFFFFFF;
    private static int GREY = 0xFF999999;

    private static class Rubik {
        static int GREEN = 0xFF009B48;
        static int RED = 0xFFB90000;
        static int BLUE = 0xFF0045AD;
        static int ORANGE = 0xFFFF5900;
        static int WHITE = 0xFFFFFFFF;
        static int YELLOW = 0xFFFFD500;

        public static List<Integer> colors() {
            List<Integer> result = new ArrayList<>();
            result.add(GREEN);
            result.add(RED);
            result.add(BLUE);
            result.add(ORANGE);
            result.add(WHITE);
            result.add(YELLOW);
            return result;
        }
    }

    public static class Palettes {
        public static List<Integer> stoplight() {
            return new ArrayList<Integer>(Arrays.asList(0xFFF18C8E, 0xFFF0B7A4, 0xFFF1D1B5, 0xFF568EA6, 0xFF305F72));
        }

        public static List<Integer> settingTones() {
            return new ArrayList<Integer>(Arrays.asList(0xFFFAFAEB, 0xFFFAF2E6, 0xFFF5D7CE, 0xFFC5C5C9, 0xFFDCE8E4, 0xFFE6F2EE));
        }

        public static List<Integer> pastel() {
            return new ArrayList<Integer>(Arrays.asList(0xFFE1DCEA, 0xFF9BB8D5, 0xFF9CC4AB, /*0xFFB1D5C2,*/ 0xFFCCE4C3, 0xFFF0EBC8));
        }

        public static List<Integer> rgb() {
            return new ArrayList<Integer>(Arrays.asList(SALMON, FLORA, SKY, WHITE));
        }

        public static List<Integer> rubiks() {
            int GREEN = 0xFF009B48;
            int RED = 0xFFB90000;
            int BLUE = 0xFF0045AD;
            int ORANGE = 0xFFFF5900;
            int WHITE = 0xFFFFFFFF;
            int YELLOW = 0xFFFFD500;
            List<Integer> result = new ArrayList<>();
            result.add(GREEN);
            result.add(RED);
            result.add(BLUE);
            result.add(ORANGE);
            result.add(WHITE);
            result.add(YELLOW);
            return result;
        }
    }

    private static Random random = new Random();

    private static int modulo(int a, int b) {
        return (a % b + b) % b;
    }

    public static QRNode[][] blueCorners(QRNode[][] grid) {
        int color = SKY;
        return solidCorners(grid, color);
    }

    public static QRNode[][] redCorners(QRNode[][] grid) {
        int color = SALMON;
        return solidCorners(grid, color);
    }

    public static QRNode[][] solidCorners(List<QRNode> codes, int columns, int color) {
        QRNode[][] grid = QRNode.fromGridList(codes, columns);
        return solidCorners(grid, color);
    }

    public static QRNode[][] solidCorners(QRNode[][] grid, int color) {
        return coloredCorners(grid, color, color, color, color);
    }

    public static QRNode[][] rgbCorners(List<QRNode> codes, int columns) {
        QRNode[][] grid = QRNode.fromGridList(codes, columns);
        return rgbCorners(grid);
    }

    public static QRNode[][] rgbCorners(QRNode[][] grid) {
        /*
        int R = 0xFFFF0000;
        int G = 0xFF00FF00;
        int B = 0xFF0000FF;
        int A = 0xFF999999;
        */
        int R = SALMON;
        int G = SPRING;
        int B = SKY;
        int A = 0xFF999999;
        // int A = BUBBLEGUM;
        return coloredCorners(grid, R, G, B, A);
    }

    public static QRNode[][] coloredCorners(List<QRNode> codes, int columns, int NE, int NW, int SW, int SE) {
        QRNode[][] grid = QRNode.fromGridList(codes, columns);
        return coloredCorners(grid, NE, NW, SW, SE);
    }

    /**
     * Colors the corners of the given grid with the given colors.
     * @param grid
     * @return
     */
    public static QRNode[][] coloredCorners(QRNode[][] grid, int NE, int NW, int SW, int SE) {
        boolean setBackgroundColor = true;
        int COLUMN_COUNT = grid[0].length;
        int ROW_COUNT = grid.length;
        QRNode cornerNE = grid[0][0];
        QRNode cornerNW = grid[0][COLUMN_COUNT - 1];
        QRNode cornerSW = grid[ROW_COUNT - 1][COLUMN_COUNT - 1];
        QRNode cornerSE = grid[ROW_COUNT - 1][0];
        if (!setBackgroundColor) {
            cornerNE.style.setColor(NE);
            cornerNW.style.setColor(NW);
            cornerSW.style.setColor(SW);
            cornerSE.style.setColor(SE);
        } else {
            cornerNE.style.setBackgroundColor(NE);
            cornerNW.style.setBackgroundColor(NW);
            cornerSW.style.setBackgroundColor(SW);
            cornerSE.style.setBackgroundColor(SE);
        }
        return grid;
    }

    public static QRNode[][] randomSettingTones(QRNode[][] grid) {
        List<Integer> palette = Palettes.settingTones();
        return randomColorsNonTouching(grid, palette);
    }

    public static QRNode[][] randomStoplight(QRNode[][] grid) {
        List<Integer> palette = Palettes.stoplight();
        return randomColorsNonTouching(grid, palette);
    }

    public static QRNode[][] randomRubiks(QRNode[][] grid) {
        List<Integer> palette = Rubik.colors();
        return randomColorsNonTouching(grid, palette);
    }

    public static QRNode[][] randomRgb(QRNode[][] grid) {
        List<Integer> palette = new ArrayList<>();
        palette.add(SALMON);
        palette.add(FLORA);
        palette.add(SKY);
        // palette.add(GREY);
        return randomColorsNonTouching(grid, palette);
    }

    public static QRNode[][] randomColors(List<QRNode> codes, int columns, List<Integer> palette) {
        // assert palette must be none empty
        QRNode[][] grid = QRNode.fromGridList(codes, columns);
        return randomColors(grid, palette);
    }

    public static QRNode[][] randomColors(QRNode[][] grid, List<Integer> palette) {
        // assert palette must be none empty
        int length = palette.size();
        // Random random = new Random();
        for (QRNode[] row : grid) {
            for (QRNode code : row) {
                int index = random.nextInt(length);
                int color = palette.get(index);
                code.style.setBackgroundColor(color);
            }
        }
        return grid;
    }

    private static List<Integer> getNENeighbors(int row, int column, int[][] grid) {
        int COLUMN_COUNT = grid[0].length;
        int ROW_COUNT = grid.length;
        List<Integer> result = new ArrayList<>();
        int i;
        int k;

        i = modulo(row - 1, ROW_COUNT);
        k = modulo(column - 1, COLUMN_COUNT);
        result.add(grid[i][k]);

        i = modulo(row - 1, ROW_COUNT);
        k = modulo(column, COLUMN_COUNT);
        result.add(grid[i][k]);
        i = modulo(row, ROW_COUNT);
        k = modulo(column - 1, COLUMN_COUNT);
        result.add(grid[i][k]);
        return result;
    }

    private static Integer getRandomElement(List<Integer> collection) {
        int length = collection.size();
        int index = random.nextInt(length);
        Integer element = collection.get(index);
        return element;
    }

    private static List<Integer> difference(List<Integer> a, List<Integer> b) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> ad = new HashSet<Integer>(a);
        Set<Integer> bd = new HashSet<Integer>(b);
        ad.removeAll(bd);
        for (Integer element : ad) {
            result.add(element);
        }
        return result;
    }

    public static int[][] randomNonTouchingColorGrid(final int rows, final int columns, List<Integer> palette) {
        int[][] grid = new int[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                List<Integer> neighbors = getNENeighbors(row, column, grid);
                List<Integer> uniqueColors = difference(palette, neighbors);
                int color = getRandomElement(uniqueColors);
                grid[row][column] = color;
            }
        }

        return  grid;
    }

    public static QRNode[][] randomColorsNonTouching(List<QRNode> codes, int columns, List<Integer> palette) {
        QRNode[][] grid = QRNode.fromGridList(codes, columns);
        return randomColorsNonTouching(grid, palette);
    }

    public static QRNode[][] randomColorsNonTouching(QRNode[][] grid, List<Integer> palette) {
        /*
        // assert palette must have at least N colors
        int COLUMN_COUNT = grid[0].length;
        int ROW_COUNT = grid.length;
        // iterate through each cell
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int column = 0; column < COLUMN_COUNT; column++) {

            }
        }
        */
        int COLUMN_COUNT = grid[0].length;
        int ROW_COUNT = grid.length;
        return colorGrid(grid, randomNonTouchingColorGrid(COLUMN_COUNT, ROW_COUNT, palette));
    }

    public static QRNode[][] colorGrid(QRNode[][] grid, int[][] colors) {
        int columns = grid[0].length;
        int rows = grid.length;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                int color = colors[row][column];
                grid[row][column].style.setBackgroundColor(color);
            }
        }

        return grid;
    }

    public static void spiral(QRNode[][] grid, List<Integer> palette) {
        int columns = grid[0].length;
        int rows = grid.length;
        int i, k = 0, l = 0;
        int m = rows;
        int n = columns;
        /*  k - starting row index
        m - ending row index
        l - starting column index
        n - ending column index
        i - iterator
        */

        while (k < m && l < n) {
            // Print the first row from the remaining rows
            for (i = l; i < n; ++i) {
                grid[k][i].style.setBackgroundColor(palette.get(0)); // palette.next()
                // System.out.print(a[k][i] + " ");
            }
            k++;

            // Print the last column from the remaining columns
            for (i = k; i < m; ++i) {
                grid[i][n - 1].style.setBackgroundColor(palette.get(0));
                // System.out.print(a[i][n - 1] + " ");
            }
            n--;

            // Print the last row from the remaining rows */
            if (k < m) {
                for (i = n - 1; i >= l; --i) {
                    grid[m - 1][i].style.setBackgroundColor(palette.get(0));
                    // System.out.print(a[m - 1][i] + " ");
                }
                m--;
            }

            // Print the first column from the remaining columns */
            if (l < n) {
                for (i = m - 1; i >= k; --i) {
                    grid[i][l].style.setBackgroundColor(palette.get(0));
                    // System.out.print(a[i][l] + " ");
                }
                l++;
            }
        }

    }

    public static QRNode[][] testColors(QRNode[][] grid) {
        List<Integer> palette = Palettes.rgb();
        return randomColorsNonTouching(grid, palette);
    }
}
