class test {
    void main() {
        int a = (int) 'a';
        int b = (int) false;
        char c = (char) 1;
        char d = (char) false;
        bool e = (bool) 2;
        bool f = (bool) '1';
        const g = 1;
        const h = '1';
        const i = false;
        a = (int) h;
        a = (int) i;
        c = (char) g;
        c = (char) i;
        e = (bool) g;
        e = (bool) h;
    }
}
