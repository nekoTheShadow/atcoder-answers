fn main() {
    let (n, x): (usize, u64) = {
        let mut s = String::new();
        std::io::stdin().read_line(&mut s).ok();
        let mut i = s.split_whitespace();
        (i.next().unwrap().parse().unwrap(), i.next().unwrap().parse().unwrap())
    };
    let l: Vec<u64> = {
        let mut s = String::new();
        std::io::stdin().read_line(&mut s).ok();
        s.split_whitespace().map(|e| e.parse().unwrap()).collect()
    };

    let mut d: Vec<u64> = vec![0; n + 1];
    for i in 0..n {
        d[i + 1] = d[i] + l[i]
    };

    let mut ans = 0;
    for e in d.into_iter() {
        if e <= x {
            ans += 1;
        }
    }
    println!("{}", ans);
}
