fn main() {
    let (n, m) = {
        let mut line = String::new();
        std::io::stdin().read_line(&mut line).ok();
        let mut iter = line.trim().split_whitespace().map(|e| e.parse::<usize>().unwrap());
        (iter.next().unwrap(), iter.next().unwrap())
    };

    let mut a: std::collections::HashSet<usize> = std::collections::HashSet::new();
    for _ in 0..m {
        let mut line = String::new();
        std::io::stdin().read_line(&mut line).ok();
        a.insert(line.trim().parse().unwrap());
    }

    let mut dp = vec![0; n + 1];
    dp[0] = 1;
    for i in 0..(n + 1) {
        if i + 1 <= n && !a.contains(&(i + 1)) {
            dp[i + 1] = (dp[i + 1] + dp[i]) % 1000000007;
        }
        
        if i + 2 <= n && !a.contains(&(i + 2)) {
            dp[i + 2] = (dp[i + 2] + dp[i]) % 1000000007;
        }
    }

    println!("{}", dp[n]);
}
