// The original version of the following C++ program solves Problem I of NCPC
// (Nordic Collegiate Programming Contest) 2014!
// Problem set is viewable at:
// https://ncpc.idi.ntnu.no/ncpc2014/ncpc2014problems.pdf

##include <iostream>
##include <vector>
##include <algorithm>
##include <cmath>

using namespace std;

const double eps = 1e-10;

typedef double T;
struct Pt {
    T x, y;
};

Pt operator-(const Pt ##&##a, const Pt ##&##b) {
    Pt ret;
    ret##.##x = a##.##x - b##.##x;
    ret##.##y = a##.##y - b##.##y;
    return ret;
}

struct line {
    Pt a, b;
};

T cross(const Pt ##&##a, const Pt ##&##b) {
    return a##.##x * b##.##y - a##.##y * b##.##x;
}

T dot( const Pt ##&##a, const Pt ##&##b) {
    return a##.##x * b##.##x + a##.##y * b##.##y;
}

bool feq(double x, double y) {
    return fabs(x - y) < eps;
}

bool parallel( const line ##&##x, const line ##&##y ) {
    return feq( cross(x##.##b-x##.##a,y##.##a-y##.##b), 0);
}

bool perpendicular( const line ##&##x, const line ##&##y) {
    return feq( dot(x##.##b-x##.##a,y##.##a-y##.##b), 0);
}

T dist(Pt a, Pt b) {
    return sqrt((a##.##x - b##.##x) * (a##.##x - b##.##x) + (a##.##y - b##.##y) * (a##.##y - b##.##y));
}

T distance(line l, Pt p) {
    return fabs(cross(l##.##a - p, l##.##b - p)) / dist(l##.##a, l##.##b);
}

int main() {
    int n;
    while (cin >> n) {
        vector< vector<line> > groups;
        for (int i = 0; i < n; ++i) {
            line l;
            cin >> l##.##a##.##x >> l##.##a##.##y >> l##.##b##.##x >> l##.##b##.##y;

            int gj = -1;
            for (int j = 0; gj == -1 && j < groups##.##size(); ++j) {
                if (parallel(l, groups[j][0]))
                    gj = j;
            }
            if (gj == -1)
                groups##.##push##_##back( vector<line> (1, l));
            else
                groups[gj]##.##push##_##back(l);
        }
        vector< vector<double> > distances(groups##.##size());
        for (int i = 0; i < groups##.##size(); ++i) {
            for (int j = 0; j < groups[i]##.##size(); ++j)
                for (int k = j + 1; k < groups[i]##.##size(); ++k)
                    distances[i]##.##push##_##back(distance(groups[i][j], groups[i][k]##.##a));
        }
        for (int i = 0; i < distances##.##size(); ++i)
            sort(distances[i]##.##begin(), distances[i]##.##end());
        int ans = 0;
        for (int i = 0; i < groups##.##size(); ++i) {
            for (int j = i + 1; j < groups##.##size(); ++j) {
                if (perpendicular(groups[i][0], groups[j][0])) {
                    for (int k = 0; k < distances[i]##.##size(); ++k) {
                        ans += upper##_##bound(distances[j]##.##begin(), distances[j]##.##end(), distances[i][k] + eps)
                            - lower##_##bound(distances[j]##.##begin(), distances[j]##.##end(), distances[i][k] - eps);
                    }
                }
            }
        }
        cout << ans << endl;
    }
    return 0;
}
