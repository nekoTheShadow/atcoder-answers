fn main() {
    let (n, k) = {
        let mut s = String::new();
        std::io::stdin().read_line(&mut s).ok();
        let mut i = s.split_whitespace();
        (i.next().unwrap().parse::<usize>().unwrap(), i.next().unwrap().parse::<u64>().unwrap())
    };
    let a: Vec<u64> = {
        let mut s = String::new();
        std::io::stdin().read_line(&mut s).ok();
        s.split_whitespace().map(|e| e.parse().unwrap()).collect()
    };

    let mut x = 0usize;
    let mut y = 0usize;
    let mut sum = 0u64;
    let mut ans = 0usize;
    while x < n {
        while y < n && sum < k {
            sum += a[y];
            y += 1;
        }

        if sum < k {
            break;
        }
        
        ans += n - y + 1;
        sum -= a[x];
        x += 1;
    }

    println!("{}", ans);
}
