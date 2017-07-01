import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * BOJ#2178_미로 탐색
 * https://www.acmicpc.net/problem/2178
 */
public class Main {

	public static void main(String[] args) throws IOException {

		int N, M;
		node[][] maze;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		maze = new node[N][M];

		for (int i = 0; i < N; i++) {

			String subMaze = br.readLine();

			for (int j = 0; j < M; j++) {

				maze[i][j] = new node(i, j, Integer.parseInt(subMaze.substring(j, j + 1)));
			}
		}

		bfs(maze);
		System.out.println(maze[N - 1][M - 1].cost);
	}

	static void bfs(node[][] maze) {

		Queue<node> q = new LinkedList<node>();

		q.offer(maze[0][0]);
		maze[0][0].cost = 1;
		maze[0][0].visited = true;

		while (!q.isEmpty()) {

			node n = q.poll();

			if (!(n.x <= 0)) {

				if (maze[n.x - 1][n.y].value == 1 && !maze[n.x - 1][n.y].visited) {

					q.offer(maze[n.x - 1][n.y]);
					maze[n.x - 1][n.y].visited = true;
					if (maze[n.x - 1][n.y].cost == -1 || maze[n.x - 1][n.y].cost > n.cost + 1)
						maze[n.x - 1][n.y].cost = n.cost + 1;
				}
			}
			if (!(n.x >= maze.length - 1)) {

				if (maze[n.x + 1][n.y].value == 1 && !maze[n.x + 1][n.y].visited) {

					q.offer(maze[n.x + 1][n.y]);
					maze[n.x + 1][n.y].visited = true;
					if (maze[n.x + 1][n.y].cost == -1 || maze[n.x + 1][n.y].cost > n.cost + 1)
						maze[n.x + 1][n.y].cost = n.cost + 1;
				}
			}
			if (!(n.y <= 0)) {

				if (maze[n.x][n.y - 1].value == 1 && !maze[n.x][n.y - 1].visited) {

					q.offer(maze[n.x][n.y - 1]);
					maze[n.x][n.y - 1].visited = true;
					if (maze[n.x][n.y - 1].cost == -1 || maze[n.x][n.y - 1].cost > n.cost + 1)
						maze[n.x][n.y - 1].cost = n.cost + 1;
				}
			}
			if (!(n.y >= maze[0].length - 1)) {

				if (maze[n.x][n.y + 1].value == 1 && !maze[n.x][n.y + 1].visited) {

					q.offer(maze[n.x][n.y + 1]);
					maze[n.x][n.y + 1].visited = true;
					if (maze[n.x][n.y + 1].cost == -1 || maze[n.x][n.y + 1].cost > n.cost + 1)
						maze[n.x][n.y + 1].cost = n.cost + 1;
				}
			}

		}

	}

}

class node {

	int x, y;
	int value;
	int cost;
	boolean visited;

	node(int x, int y, int value) {

		this.x = x;
		this.y = y;
		this.value = value;
		this.cost = -1;
		this.visited = false;
	}
}
