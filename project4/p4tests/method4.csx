class test {
    int f() {
        const b = 0;
        int a[2];
        if (true)
            return 1;
        if (true)
            return a[0];
        if (true)
            return b;
    }
    void g() {
        return;
    }
    void main() {
    }
}
