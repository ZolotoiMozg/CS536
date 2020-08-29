class test {
    void f() {}
    void f(int x) { f(); f(1); }
    void main() {
        f();
        f(1);
    }
}
