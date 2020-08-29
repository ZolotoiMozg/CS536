class test {
    void f(int a, char b, bool c, int aa[], char ba[], bool bc[]) {
        int ma;
        char mb;
        bool mc;
        int maa[10];
        char mba[10];
        bool mbc[10];
        const ci = 1;
        const cc = '1';
        const cb = false;
        f(ma, mb, mc, maa, mba, mbc);
        f(ci, cc, cb, maa, mba, mbc);
    }
    void main() {
        int ma;
        char mb;
        bool mc;
        int maa[10];
        char mba[10];
        bool mbc[10];
        const ci = 1;
        const cc = '1';
        const cb = false;
        f(ma, mb, mc, maa, mba, mbc);
        f(ci, cc, cb, maa, mba, mbc);
    }
}
