fn main() {
    let (h, w) = {
        let mut line = String::new();
        std::io::stdin().read_line(&mut line).ok();
        let mut integers = line.split_whitespace().map(|e| e.parse::<usize>().unwrap());
        (integers.next().unwrap(), integers.next().unwrap())
    };

    let mut s: Vec<Vec<char>> = Vec::new();
    for _ in 0..h {
        let mut line = String::new();
        std::io::stdin().read_line(&mut line).ok();
        s.push(line.chars().collect());
    }

    let mut l: Vec<Vec<usize>> = Vec::new();
    let mut r: Vec<Vec<usize>> = Vec::new();
    let mut u: Vec<Vec<usize>> = Vec::new();
    let mut d: Vec<Vec<usize>> = Vec::new();
    for x in 0..h {
        l.push(Vec::new());
        r.push(Vec::new());
        u.push(Vec::new());
        d.push(Vec::new());
        l[x].resize(w, 0);
        r[x].resize(w, 0);
        u[x].resize(w, 0);
        d[x].resize(w, 0);
    }

    for x in 0..h {
        for y in (0..w).rev() {
            r[x][y] = if s[x][y] == '#' {
                0
            } else {
                if y == w - 1 {
                    1
                } else {
                    r[x][y + 1] + 1
                }
            }
        }
    }

    for x in 0..h {
        for y in 0..w {
            l[x][y] = if s[x][y] == '#' {
                0
            } else {
                if y == 0 {
                    1
                } else {
                    l[x][y - 1] + 1
                }
            }
        }
    }

    for y in 0..w {
        for x in (0..h).rev() {
            u[x][y] = if s[x][y] == '#' {
                0
            } else {
                if x == h - 1 {
                    1
                } else {
                    u[x + 1][y] + 1
                }
            }
        }
    }

    for y in 0..w {
        for x in 0..h {
            d[x][y] = if s[x][y] == '#' {
                0
            } else {
                if x == 0 {
                    1
                } else {
                    d[x - 1][y] + 1
                }
            }
        }
    }

    let mut answer = 0;
    for x in 0..h {
        for y in 0..w {
            if s[x][y] == '.' {
                let t = l[x][y] + r[x][y] + u[x][y] + d[x][y] - 3;
                answer = std::cmp::max(answer, t);
            }
        }
    }

    println!("{}", answer);

}
