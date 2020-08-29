class test {
    int f() {
        return 0;
    }

    char g() {
        return '0';
    }

    bool h() {
        return false;
    }

    void t(int bb[], char ba2[], char ba3[], int sc) {
        int a;
        int aa[2];
        int cc[2];
        char b;
        char ba[3];
        bool c;
        const con = 10;
        a = 1;
        a = f();
        aa[0] = 0;
        sc = 1;
        a = con;
        b = '1';
        b = g();
        c = true;
        c = h();

        aa = cc;
        aa = bb;
        bb = aa;

        ba = "aaa";
        ba = "aa\n";
        ba2 = "bbb0";

        ba2 = ba3;
    }

    void main() {}
}
