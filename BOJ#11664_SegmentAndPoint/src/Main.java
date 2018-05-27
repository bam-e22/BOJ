import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 선데이코딩 베타라운드#1 - G
 * BOJ#11664 선분과 점
 * https://www.acmicpc.net/problem/11664
 */

public class Main {

    static final boolean DEBUG = false;

    public static void main(String[] args) throws IOException {

        int Ax, Ay, Az, Bx, By, Bz, Cx, Cy, Cz;
        double minDistance = 0.0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Ax = Integer.parseInt(st.nextToken());
        Ay = Integer.parseInt(st.nextToken());
        Az = Integer.parseInt(st.nextToken());
        Bx = Integer.parseInt(st.nextToken());
        By = Integer.parseInt(st.nextToken());
        Bz = Integer.parseInt(st.nextToken());
        Cx = Integer.parseInt(st.nextToken());
        Cy = Integer.parseInt(st.nextToken());
        Cz = Integer.parseInt(st.nextToken());

        // AC
        int x1 = Cx - Ax;
        int y1 = Cy - Ay;
        int z1 = Cz - Az;

        // CB
        int x2 = Cx - Bx;
        int y2 = Cy - By;
        int z2 = Cz - Bz;

        double vectorX = (double) ((y1 * z2 - z1 * y2));
        double vectorY = (double) ((x1 * z2 - z1 * x2));
        double vectorZ = (double) ((x1 * y2 - x2 * y1));

        if (DEBUG) System.out.println("vectorX " + vectorX);
        if (DEBUG) System.out.println("vectorY " + vectorY);
        if (DEBUG) System.out.println("vectorZ " + vectorZ);

        double lengthAC = Math.sqrt((Ax - Cx) * (Ax - Cx) + (Ay - Cy) * (Ay - Cy) + (Az - Cz) * (Az - Cz));
        double lengthBC = Math.sqrt((Bx - Cx) * (Bx - Cx) + (By - Cy) * (By - Cy) + (Bz - Cz) * (Bz - Cz));

        double areaTriangle = Math.sqrt(vectorX * vectorX + vectorY * vectorY + vectorZ * vectorZ) / 2.0;

        if (DEBUG) System.out.println("areaTriangle " + areaTriangle);
        if (areaTriangle == 0.0) {

            // C가 AB 직선 위에 위차한다면
            if (((Bx - Ax) == 0 ? 0 : ((Cx - Ax) / (Bx - Ax))) == ((By - Ay) == 0 ? 0 : ((Cy - Ay) / (By - Ay)))
                    && (((By - Ay) == 0 ? 0 : ((Cy - Ay) / (By - Ay))) == ((Bz - Az) == 0 ? 0 : ((Cz - Az) / (Bz - Az))))
                    && (Cx >= Ax) && (Cx <= Bx) && (Cy >= Ay) && (Cy <= By) && (Cz >= Az) && (Cz <= Bz)) {

                System.out.printf("%.10f", 0.0);

                return;
            }

            // C가 AB 직선 밖에 위치한다면
            System.out.printf("%.10f", Math.min(lengthAC, lengthBC));

            return;
        }

        double lengthAB = Math.sqrt((Bx - Ax) * (Bx - Ax) + (By - Ay) * (By - Ay) + (Bz - Az) * (Bz - Az));

        if (DEBUG) System.out.println("lengthAB " + lengthAB);

        if (((Cx >= Ax) && (Cx <= Bx)) || ((Cy >= Ay) && (Cy <= By)) || ((Cz >= Az) && (Cz <= Bz))) {

            minDistance = (areaTriangle * 2.0) / lengthAB;

            System.out.printf("%.10f", minDistance);
        } else {

            System.out.printf("%.10f", Math.min(lengthAC, lengthBC));
        }
    }
}
