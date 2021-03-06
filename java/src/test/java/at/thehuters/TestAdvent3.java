package at.thehuters;

import at.thehuters.advent2.SecurityGuideline;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


class TestAdvent3 {

    @Test
    void testCase1() {
        // right 3 down 1
        System.out.println(getNumberOfTreeForSlope(1,3));
    }

    @Test
    void testCase2() {
        System.out.println(
                        getNumberOfTreeForSlope(1,1) *
                        getNumberOfTreeForSlope(1,3) *
                        getNumberOfTreeForSlope(1,5) *
                        getNumberOfTreeForSlope(1,7) *
                        getNumberOfTreeForSlope(2,1)

        );
    }

    long getNumberOfTreeForSlope(int down, int right){
        int xLocation = 0;
        int lineGrabber = 0;
        int encounters = 0;
        for(String s : dataCase1()){
            if(lineGrabber % down == 0){
                if(s.charAt(xLocation) == '#')
                    encounters++;
                xLocation = (xLocation + right) % s.length();
            }
            lineGrabber++;
        }
        return encounters;
    }


    List<String> dataCase1(){
        return Arrays.asList(("..#......###....#...##..#.#....\n" +
                ".#.#.....#.##.....###...##...##\n" +
                "..#.#..#...........#.#..#......\n" +
                "..#......#..........###........\n" +
                "...#..###..##.#..#.......##..##\n" +
                "......#.#.##...#...#....###....\n" +
                "..........##.....##..##......#.\n" +
                "......#...........#............\n" +
                "#....#..........#..............\n" +
                ".#........##.............###.##\n" +
                "....#.........#.......#.#....##\n" +
                "#.#..#..#..#.......#...#....##.\n" +
                ".#........#......#.##.......#..\n" +
                "..#.....#####.....#....#..#..##\n" +
                ".......#..##.......#......#.###\n" +
                "..#.#...#......#.##...#........\n" +
                "##...................#...##..#.\n" +
                "......#...#.##...##.#......#..#\n" +
                ".#.................#..##...#...\n" +
                "...#.....#.......##.....#.#....\n" +
                ".......#.#......#.....#..#..##.\n" +
                "..........#........#...........\n" +
                "..#.#..........................\n" +
                ".#.##..#.#...#...#.........#...\n" +
                ".....#....#.....#..#.....#.....\n" +
                "...#.#.#.....#.#..#.......#..#.\n" +
                ".....#...###...##...#......##..\n" +
                "#.###......#.#...#.#.#..###....\n" +
                "#.....#..##......#..........#.#\n" +
                "#...............#........#.#..#\n" +
                ".....#..#.........#......##.#..\n" +
                ".....#.##.##..#..##............\n" +
                "...#......##...............#.#.\n" +
                ".#..#.#............##.#........\n" +
                "#.....#..###.............##.#..\n" +
                "...##..#.#..#...........#..#...\n" +
                "#....#.........#.#.............\n" +
                "##.#.........#..###......#.#..#\n" +
                "...#...#......#.#.#.##..#.##...\n" +
                ".....##............#.##.##..#..\n" +
                "....#................#.##..#..#\n" +
                "...#..#.......#...#..#........#\n" +
                "....#...#...#................#.\n" +
                "....##...............#.#...#...\n" +
                ".#.....###...#.......#.##......\n" +
                "....######.#..............###.#\n" +
                ".#..#.........##...............\n" +
                "................##.#..#....###.\n" +
                ".......#............#.#..#..#..\n" +
                "......#.#...............##.#...\n" +
                "...#..####.#...#..#..#......#..\n" +
                "....#.#...#.....#.........#..##\n" +
                ".##..#...#......##....##.#.#...\n" +
                ".##.#.........##...#....#......\n" +
                "..#.#..#...#.#..#.......#...#.#\n" +
                ".........#..#.....##..#........\n" +
                "..#......#..##.....#..#...###..\n" +
                "..#...#....#.#..#..#.#.#..#.#..\n" +
                "...#..#####.....#......#.......\n" +
                "#.#............#......#..#...#.\n" +
                ".........#..........###.......#\n" +
                "......#....#..#.##.#......#..#.\n" +
                "...........##.#....#.#..#......\n" +
                "..#...................#..#.#...\n" +
                "#....##.............##....#...#\n" +
                "##..#....#.........#..........#\n" +
                "....#.#.#...#..#........#.##..#\n" +
                "...............#...#..##..#....\n" +
                ".##.......#.......#...........#\n" +
                "#.........................##...\n" +
                "#........#.#..#..##..####.#....\n" +
                "...................##.....###..\n" +
                ".#.......#..#......#......#...#\n" +
                "..#.........#...#..........#...\n" +
                "..........#......#....#........\n" +
                ".#......#..#...#..#...##....##.\n" +
                "...#.#..#..#......#.....##.####\n" +
                ".......#.#....#.......#........\n" +
                "#...#.#...##..##.#......#......\n" +
                ".#.........#...................\n" +
                "...#..........#.#......#.......\n" +
                "...#.....##....#..........#....\n" +
                ".#..........##..#..#..##....#.#\n" +
                ".##.#..........#...#.##.......#\n" +
                "#...###....#..#.#...#..#.......\n" +
                "..................##...........\n" +
                "..#...##.#...........#....#.##.\n" +
                "..#......#..##..#....##..#...#.\n" +
                "..#....#.....#.##..#.......#..#\n" +
                "#...#....#..#.#....#......##...\n" +
                ".......##..#..........#........\n" +
                "..#.............##.#.....#...#.\n" +
                "...............#....#...#...##.\n" +
                "##...........#.......#.##......\n" +
                "#..#...........#.........#.....\n" +
                "....###.............###.##..##.\n" +
                ".........#.#.....###.......#...\n" +
                "..#.##....#.#..........#....#..\n" +
                "#........#....##...#..#........\n" +
                "......#..........###..#.#......\n" +
                ".....#.#......##.....#..##...#.\n" +
                ".#.......#......#...#...#...#.#\n" +
                ".#..........##.......#.....##.#\n" +
                "###.#...#....#.....#...#......#\n" +
                "..#.#.#..#.##.#..#.............\n" +
                ".....#.........................\n" +
                ".#..###..#...#...#..#..#...#.#.\n" +
                "#................##...##.##....\n" +
                "......#...#...#..........#...#.\n" +
                "..........#.....##.............\n" +
                "..#.#......#........#.......#..\n" +
                "........##.............#.......\n" +
                ".......#......#.##.#..#........\n" +
                "#.#.#....#........#..........#.\n" +
                "##..##......#..#..#.....#.#..##\n" +
                "##..#..........#...............\n" +
                "#.....##...#.#......#.......#.#\n" +
                "#.....#...#....#..#.....##.....\n" +
                "##..........#.#.....#....#...##\n" +
                "..##.###..#.....#.......#...#..\n" +
                ".#.#.......#......###........#.\n" +
                ".#..............#.#..###.......\n" +
                ".#....#..##.........#..#.#.....\n" +
                "....#....#.#....#..#.......##.#\n" +
                "#.......#.......#.........#....\n" +
                "...#....#....#.....##..#..#.#.#\n" +
                "........#....#...........#.....\n" +
                ".#......##.#.#.##..............\n" +
                "#..#.#.....##........#........#\n" +
                "##...#.#.......##.......#...#..\n" +
                "#...#.....#.##...##.#.....#....\n" +
                "....#..##...#........#.#...#...\n" +
                "...#....#.#.#..###...##.#.....#\n" +
                "......#..#.....#..#........##..\n" +
                ".......#.....#.#.........#.#..#\n" +
                "..#.......#.#.#.#.#....#.##...#\n" +
                ".#...#........#..##..#......#..\n" +
                ".#..#............#...#..#.#....\n" +
                "...##......#......#............\n" +
                "..#...#.#.....#.....#..##.#....\n" +
                ".#......#.#......#..#.#........\n" +
                "..#..........##...#.#.....#..#.\n" +
                "#...#.....#..#...#.............\n" +
                "..##.................#....#....\n" +
                ".#....#.......#..##....#......#\n" +
                ".#....###............##....##.#\n" +
                "##..#........#..#...#.......#..\n" +
                ".....#.....#.#.#.##.........#..\n" +
                ".......#..#....#...#...#.......\n" +
                "...#...#...#.#.#..#.#.....#....\n" +
                "#.#........#..#.##..#..###.....\n" +
                "..................#..#.........\n" +
                "#.#.....#..##.........#.......#\n" +
                "###..#.......#..............#..\n" +
                "......#..#.....###..........#..\n" +
                "....#.#...#..#...........#.#...\n" +
                "...#.....#.......#.....#.#.....\n" +
                "#.....##..#......##...........#\n" +
                "#...###...........##..#...#.##.\n" +
                "......##.##.#...#..#....#......\n" +
                "...#.#......##.#......##....#.#\n" +
                "..............#.#.###.......#..\n" +
                "........#....#.......##..#..###\n" +
                "...#.....##.#....#......##..#.#\n" +
                "..##........#.....#.#..#...#...\n" +
                ".#..#.##.........#.....#...#..#\n" +
                "..#..#....#...........#........\n" +
                ".#...#....................#....\n" +
                "##.....##....#.............#.#.\n" +
                "....#.#..#.#..#.#.#..........##\n" +
                ".............##.#.....#..#..#..\n" +
                ".#....#.....##...#.###.........\n" +
                "..#........#........#.#..###...\n" +
                ".##....#...#...#.......#...#.#.\n" +
                "..#...#...#..##........#..#....\n" +
                "..##.#..#..#.....#......#.#..#.\n" +
                ".#........#..#....#..#.........\n" +
                "..#.#.....#.##..#........###.#.\n" +
                ".....#.##.....##.#.............\n" +
                "#.........#.......#...##...#...\n" +
                "..#.##.#..#..#............#....\n" +
                ".##....#..#............#.....#.\n" +
                "###........##.....##.#...#.....\n" +
                "#......##..##.#.#.#.#.#.#..##..\n" +
                ".....###.....#....#......#....#\n" +
                "........#.........##...#....#.#\n" +
                ".#.#.....#.#..#..##......#...#.\n" +
                "...#.##....#..#.###..#..##.....\n" +
                "....#..........##..#..#..#..#..\n" +
                "...#..#.##..#..#....#.........#\n" +
                ".....#..###.#.....#.....#..#...\n" +
                "......#...#....#.##...#.#......\n" +
                ".#.###..##.....##.##......##...\n" +
                ".....#.#...........#.#.........\n" +
                "#........#...#..#......##.#....\n" +
                "..#.......##....##....#.##.#..#\n" +
                "...###.#.........#......#.....#\n" +
                "..#.##..#....#.....##...#.##...\n" +
                "....##.##.............#...#....\n" +
                "##..#...#..#..#..#.............\n" +
                ".....#.....#.....#.............\n" +
                "...#.##.......#..#.#.....#....#\n" +
                "#.....##.........#......##.....\n" +
                ".....##..........#..#...#..#...\n" +
                "#...###....#.......#...##......\n" +
                ".#....#..#......#.....#...#.#..\n" +
                "#........#.#.#...#.....###.#.##\n" +
                "##...#...##..#..#....#.........\n" +
                "....#............#..#.....#....\n" +
                "#......#.........##....#.......\n" +
                ".#..#..#........#.............#\n" +
                ".##..........#......#.......#..\n" +
                "#............#..#....#.........\n" +
                "....#.#.....#.##...#.....#.#...\n" +
                "...#.#..#...##..#...#.#.#......\n" +
                "#....#..#.........##..#.#.#..##\n" +
                ".#...#..............#.......#..\n" +
                "#...#.....#.#........##......##\n" +
                "...#....##.####.#.........#.#.#\n" +
                "....###.#..#............#.#..#.\n" +
                "....#......#...#......##.#.#.#.\n" +
                ".....#..#.#.##.#...##..........\n" +
                "##..#...#.#...###.............#\n" +
                "....#...#..#.....#.#..#..#..#..\n" +
                "#..........####......#.....###.\n" +
                ".........#........#.##.#...#...\n" +
                ".........#..........#.#..###...\n" +
                ".....##........##.........#...#\n" +
                "..##....#...#.......##.........\n" +
                ".....#.#......##....#...#...#..\n" +
                ".##..#..##.....................\n" +
                ".......#...#..#..#...##....#...\n" +
                ".#...#.......###...#..#..#.....\n" +
                ".......#.....##.##.#.......#..#\n" +
                ".##......#...#....#..#......##.\n" +
                ".##....#..#....#...#...#.......\n" +
                ".........##..#..#.#.#.....##...\n" +
                "...#..............#..#.....####\n" +
                ".#.#.#..#.......#.......#......\n" +
                "..#.#......#..........#........\n" +
                ".#...#.#..#.......#..#..#..#...\n" +
                ".......##.#...#..#....#.....#..\n" +
                ".##...##....##...#........####.\n" +
                "....#.#..##....#...#....#.#....\n" +
                ".....#.....#..#..#.#.##..#.....\n" +
                "..#....#..............#....#...\n" +
                "..#.#.#.....##.#.....#..##.....\n" +
                "....#.....#....#...#...#..#.#..\n" +
                "#...#...........#..#..#........\n" +
                "...#.#..#.........##.#...#..##.\n" +
                "......#.#.........#.#...#......\n" +
                "......#..##.###......##.#....#.\n" +
                ".....#...#..#.......#..........\n" +
                ".#...#.......#.....###......#..\n" +
                "...........##.....#..#..#....#.\n" +
                "..#....#..#...#......#.......#.\n" +
                "..#...#...#.#..#....#...#......\n" +
                ".......#....###.####...###.#...\n" +
                "#.##.#.......#.......#....#.#.#\n" +
                ".##..........#.....#..###......\n" +
                ".....#...........#.##..#....#..\n" +
                "........##.....#.#........##...\n" +
                "#..#..#..................##....\n" +
                "#...###..........#.............\n" +
                ".......#.#.......#.#.......##..\n" +
                ".....#.#...#....#...####.....#.\n" +
                "..##.....##.......#....#.......\n" +
                "##..........#...#..##....##....\n" +
                "..........#..#......#........#.\n" +
                "##..#....#..#....#.....##....#.\n" +
                "##.##.....#...##.##.......#....\n" +
                "..#..#.###.#..##.#..#..#...#...\n" +
                ".#..#.....#........#...##.#....\n" +
                "..#..#.....#.#......##.#.#.....\n" +
                ".#..##...#.#....#...#...#.#.##.\n" +
                ".........#...#....###.#.....#..\n" +
                "...........###.#.#..#..#...#.#.\n" +
                "##...#......##...........#..#..\n" +
                ".........##..#...#.......#.....\n" +
                "#......#.#..........#..#.......\n" +
                "...#.................#....#....\n" +
                "#....#......................##.\n" +
                "##.......#..#......#.#...###.#.\n" +
                "..#....#..#.#......#...........\n" +
                "...#...........###.#.#.........\n" +
                "..#..##.....#.....##...##......\n" +
                "..#..#.#.#.#..#..#..##....#...#\n" +
                "#......##.....##..##.##...#....\n" +
                "#.....#.....#.#........#.......\n" +
                ".#.....#.................#....#\n" +
                ".###....#...#............#.#.#.\n" +
                ".#...#.#......#.#..............\n" +
                "....#...#......#.....#.......#.\n" +
                "........#.....#..........#....#\n" +
                "#..#......#...#...#.........#..\n" +
                "#....#......#...##.#...#...#...\n" +
                "#...#....#....#..#..#.....#..#.\n" +
                "#......##..#..#.#.#..#.#.......\n" +
                "..#..#...............#...##...#\n" +
                "............#..............#.##\n" +
                ".#.#.#......##.......#.......#.\n" +
                "....#.........##.......#...###.\n" +
                ".......#.#...#.#.#.......#.....\n" +
                "....#..#..#...#....#.##.#.##...\n" +
                "...##.##.#...#......#..........\n" +
                "#.....#...#.#...#.##..##.#.....\n" +
                ".......#.....#...#.#...##.#....\n" +
                ".#.............#.....#....##..#\n" +
                "##......#.......#...#....#.....\n" +
                ".###......#.................#..\n" +
                "#.#......##.........##..#......\n" +
                "...#....#..........#.#.........\n" +
                "..##..#.........#..............\n" +
                ".....#...#..................#.#\n" +
                ".............#.........#...#..#\n" +
                "....#....#......#.#.......#...#\n" +
                "#..#............#.#.......#...#\n" +
                "..#.....#............#.........\n" +
                ".#.....................###....#\n" +
                "........#.####.........#.#.#...\n" +
                "#...........##...#.........#..#\n" +
                "...........#..#......#...#.#...\n" +
                "....##...##.....#.....#........").split("\n"));
    }
}
