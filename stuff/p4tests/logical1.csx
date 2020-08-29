class test {
    void main() {
        bool a = false;
        bool b[20];
        a = a || true;
        a = a && false;
        a = !a;
        a = (b[0] && b[1]) || !a;
    }
}
