fn main() {
    let mut line = String::new();
    std::io::stdin().read_line(&mut line).ok();
    let v: Vec<i32> = line.trim().split_whitespace().map(|e| e.parse().unwrap()).collect();

    let mut ans = std::i32::MAX;
    for i in 0..3 {
        for j in (i + 1)..3 {
            ans = std::cmp::min(ans, v[i] + v[j])
        }
    }
    
    println!("{}", ans);
}
