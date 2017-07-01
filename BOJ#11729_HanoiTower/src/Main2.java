import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * BOJ#11729 하노이 탑 이동 순서
 * https://www.acmicpc.net/problem/11729
 *
 * bfs + 비트마스크로 최소 이동 횟수 구하기
 */

public class Main2 {

    static final int EMPTY = -1;

    public static void main(String[] args) throws IOException {

        int N;              // 원판의 개수
        long state = 0L;    // 현재 상태
        long endState = 0L; // 목표 상태
        HashSet<Long> discovered = new HashSet<>(); // 정점 중복 방문 처리

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        // 장대는 0번부터 2번까지 있다 (3개)
        // 원판은 0번부터 N-1번까지 있다 (N개)

        // 목표 상태 : 모든 원판이 2번 장대에 있는 경우
        for (int i = 0; i < N; i++) {

            endState <<= 2;
            endState |= 2;
        }

        // solve - bfs
        int cnt = -1;
        Queue<Long> queue = new LinkedList<>();

        queue.add(state);
        discovered.add(state);

        while (!queue.isEmpty()) {

            cnt++;

            int size = queue.size();
            for (int i = 0; i < size; i++) {

                long curState = queue.poll();

                // 종료조건
                if (curState == endState) {

                    System.out.println(cnt);
                    return;
                }

                // 게임판 변환
                // 각 장대의 Top에 위치한 원판(Piece)을 구한다
                int[] top = {EMPTY, EMPTY, EMPTY};
                for (int j = N - 1; j >= 0; j--) {

                    top[(int) get(curState, j)] = j;
                }

                // 탐색
                // j번 장대의 top을 k번 장대로 옮긴다
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {

                        // 두 장대가 같은 경우
                        if (j == k) continue;
                        // j번 장대에 원판이 없는 경우
                        if (top[j] == EMPTY) continue;

                        // j번 장대의 top이 k번 장대의 top보다 큰 경우
                        if (top[k] != EMPTY && top[j] > top[k]) continue;

                        long nextState = set(curState, top[j], k);

                        // 이미 방문한 정점인 경우
                        if (discovered.contains(nextState)) continue;

                        queue.add(nextState);
                        discovered.add(nextState);
                    }
                }
            }
        }

    } // ~ main

    static long get(long state, int index) {

        // (index)번 원판의 위치를 반환
        return (state >> (index * 2)) & 3;
    }

    static long set(long state, int index, int value) {

        // (index)번 원판의 위치를 지우고 -> (index)원판의 위치를 value로 기록한다
        return (state & ~(3 << (index * 2))) | (value << (index * 2));
    }
}
