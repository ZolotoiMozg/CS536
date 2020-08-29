class test {
    void f(int x, int y) {
    }

    void main() {
        label: while (true) {}
        f(1, label);
    }
}
