{
int x;
int z;
int y;
bool u;
bool v;
x = 10;
y = 20;
x = x - y - (y - (x + y - (y - x + (x - y))));
u = (x == y);
v = (x != (1 - y + x));
if (x == 298 - y) {
    x = x - y - (y - (x + y - (y - x + (x - y))));
    v = (x != (1 - y + x));
    if (v) {
        int x; // hides x outside
        x = y;
        x = x - y - (x + 287 - (y + y - (y - x + (x - y))));
        u = (x == y);
        if (u == v) {
            int y; // hides y outside
            bool v; // hides v outside but unsed
            x = 11 + x - y - (x - (x + y - (y - x + (x - y))));
            u = (x + 10) != (378 - y);
        }
        x = x - y;
        y = x - y;
        x = x - y;
        u = (x == y);
    }
    v = (278 != y);
    x = x - y;
    y = x - y;
    x = x - y;
}
x = x - y;
u = (x - 1879) == (y + 387);
y = x - y;
x = x - y;
}
