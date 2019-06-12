fn main() {
    let n: usize = {
        let mut line = String::new();
        std::io::stdin().read_line(&mut line).ok();
        line.trim().parse().unwrap()
    };
    let w: Vec<i32> = {
        let mut line = String::new();
        std::io::stdin().read_line(&mut line).ok();
        line.trim().split_whitespace().map(|e| e.parse().unwrap()).collect()
    };
    
    let mut ans = std::i32::MAX;
    for t in 0..(n - 1) {
        let s1 = (0..(t + 1)).fold(0, |sum, i| sum + w[i]);
        let s2 = ((t + 1)..n).fold(0, |sum, i| sum + w[i]);
        ans = std::cmp::min(ans, (s1 - s2).abs());
    }

    println!("{}", ans);
}
