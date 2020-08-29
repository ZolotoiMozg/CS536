class qsort {
    void qsort(int a[], int l, int r) {
        int mid = (l + r) / 2;
        int i;
        int j;
        int k;
        int t;
        if (l >= r)
            return;

        k = a[mid];
        i = l;
        j = r;
        while (i <= j) {
            while (a[i] < k) i++;
            while (a[j] > k) j--;
            if (i <= j) {
                t = a[i];
                a[i] = a[j];
                a[j] = t;
                i++;
                j--;
            }
        }

        qsort(a, l, j);
        qsort(a, i, r);
    }

    void main() {
        int n = 10000;
        int a[10000];
        int i;
        int t;

        a[0] = 97;
        i = 1;
        while (i < n) {
            t = a[i - 1] * 32719 + 997;
            t = t - t / 16301 * 16301;
            a[i] = t;
            print(a[i], ' ');
            i++;
        }
        print('\n');
        qsort(a, 0, n - 1);
        i = 0;
        while (i < n) {
            print(a[i], ' ');
            i++;
        }
        print('\n');
    }
}
