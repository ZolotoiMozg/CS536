class queens {
    int solCount;

    void printSolution(int n, int result[]) {
        int i = 0;
        int j;
        while (i < n) {
            j = 0;
            while (j < n) {
                if (j == result[i])
                    print('x');
                else
                    print('.');
                j++;
            }
            print('\n');
            i++;
        }
        print('\n');
    }

    void solve(int n, int i, bool colUsed[],
                bool diagUsed[], bool antiDiagUsed[], int result[]) {
        int j;

        if (i == n) {
            solCount++;
            printSolution(n, result);
            return;
        }

        j = 0;
        while (j < n) {
            if (!colUsed[j] && !diagUsed[i + j] && !antiDiagUsed[i - j + n - 1]) {
                colUsed[j] = true;
                diagUsed[i + j] = true;
                antiDiagUsed[i - j + n - 1] = true;
                result[i] = j;
                solve(n, i + 1, colUsed, diagUsed, antiDiagUsed, result);
                antiDiagUsed[i - j + n - 1] = false;
                diagUsed[i + j] = false;
                colUsed[j] = false;
            }
            j++;
        }
    }

    void main() {
        bool colUsed[8];
        bool diagUsed[15];
        bool antiDiagUsed[15];
        int result[8];
        solCount = 0;
        solve(8, 0, colUsed, diagUsed, antiDiagUsed, result);
        print("Number of solutions: ", solCount, "\n");
    }
}
