class pi {
    void print4d(int x) {
        if (x < 1000)
            print(0);
        if (x < 100)
            print(0);
        if (x < 10)
            print(0);
        print(x);
    }

    void main() {
        int a = 10000;
        int b = 0;
        int c = 2800;
        int d = 0;
        int e = 0;
        int f[2801];
        int g = 0;

        while (b - c != 0) {
            f[b] = a / 5;
            b++;
        }

        outer: while (true) {
            d = 0;
            g = c * 2;
            if (g == 0)
                break outer;

            b = c;
            inner: while (true) {
                d = d + f[b] * a;
                g--;
                f[b] = d - d / g * g;
                d = d / g;
                g--;
                b--;
                if (b == 0)
                    break inner;
                d = d * b;
            }
            
            c = c - 14;
            print4d(e + d / a);
            e = d - d / a * a;
        }
        print('\n');
    }
}
