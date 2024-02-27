import java.io.*;
import java.util.*;
 
public class Solution {
 
    static class Node {
 
        int x, y, dir;
 
        Node() {}
 
        Node(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
 
    static int h, w;
    static Node startNode;
    static char[][] warMap;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static char[] point = {'v', '^', '>', '<'};
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int t = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            startNode = new Node();
            warMap = new char[h][w];
 
            for (int i = 0; i < h; i++) {
                String line = br.readLine();
                for (int j = 0; j < w; j++) {
                    warMap[i][j] = line.charAt(j);
 
                    if (warMap[i][j] == '>') {
                        startNode = new Node(i, j, 2);
                    } else if (warMap[i][j] == '<') {
                        startNode = new Node(i, j, 3);
                    } else if (warMap[i][j] == 'v') {
                        startNode = new Node(i, j, 0);
                    } else if (warMap[i][j] == '^') {
                        startNode = new Node(i, j, 1);
                    }
                }
            }
 
            int command = Integer.parseInt(br.readLine());
            String commands = br.readLine();
 
            for (int i = 0; i < command; i++) {
                char cmd = commands.charAt(i);
 
                if (cmd == 'S') {
                    shoot(startNode);
                } else if (cmd == 'R') {
                    startNode = new Node(startNode.x, startNode.y, 2);
                    move();
                } else if (cmd == 'L') {
                    startNode = new Node(startNode.x, startNode.y, 3);
                    move();
                } else if (cmd == 'D') {
                    startNode = new Node(startNode.x, startNode.y, 0);
                    move();
                } else {
                    startNode = new Node(startNode.x, startNode.y, 1);
                    move();
                }
            }
 
            System.out.print("#" + tc + " ");
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    System.out.print(warMap[i][j]);
                }
                System.out.println();
            }
        }
 
        br.close();
    }
 
    private static void shoot(Node curNode) {
 
        int nx = curNode.x + dx[curNode.dir];
        int ny = curNode.y + dy[curNode.dir];
 
        if (nx < 0 || nx >= h || ny < 0 || ny >= w) return;
 
        if (warMap[nx][ny] == '#') return;
        if (warMap[nx][ny] == '*') {
            warMap[nx][ny] = '.';
            return;
        }
 
        shoot(new Node(nx, ny, curNode.dir));
    }
 
    private static void move() {
        int nx = startNode.x + dx[startNode.dir];
        int ny = startNode.y + dy[startNode.dir];
 
        warMap[startNode.x][startNode.y] = point[startNode.dir];
 
        if (nx < 0 || nx >= h || ny < 0 || ny >= w) return;
 
        if (warMap[nx][ny] == '.') {
            warMap[startNode.x][startNode.y] = '.';
            warMap[nx][ny] = point[startNode.dir];
            startNode = new Node(nx, ny, startNode.dir);
        }
    }
}
