import java.util.*;

class Solution {
    
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static int[][] visited;
    public static int answer = 0;
    
    public int solution(int[][] maps) {
        
        visited = new int[maps.length][maps[0].length];
        
        bfs(maps);
    
        answer = visited[maps.length-1][maps[0].length-1]; 
				// 마지막 visited의 값이 답이 된다.
        
        if (answer == 0) { answer = -1; }
        
        return answer;
    }
    
    public void bfs (int[][] maps) {
        
        int x = 0;
        int y = 0;
        visited[x][y] = 1;
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {x, y});
        
        while(!q.isEmpty()){
            int[] tmp = q.poll();
            int cx = tmp[0];
            int cy = tmp[1];
            
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                if (nx < 0 || nx > maps.length-1 || ny < 0 || ny > maps[0].length-1)
                    continue;
                
                if (visited[nx][ny] == 0 && maps[nx][ny] == 1){
                    visited[nx][ny] = visited[cx][cy] + 1;
                    q.add(new int[] {nx, ny});
                }

								// if (nx >= 0 && nx < maps.length && ny >= 0 && ny < maps[0].length
                //     && visited[nx][ny] == 0 && maps[nx][ny] == 1) {
                //     visited[nx][ny] = visited[x][y] + 1;
                //     q.add(new int[]{nx, ny});
                // }
            }
            
        }
    }
}
