fn main() {
    let (w, h, x, y) = {
        let mut s = String::new();
        std::io::stdin().read_line(&mut s).ok();
        let mut i = s.split_whitespace().map(|e| e.parse::<f64>().unwrap());
        (i.next().unwrap(), i.next().unwrap(), i.next().unwrap(), i.next().unwrap())
    };

    let ok = if x + x == w && y + y == h {
        1
    } else {
        0
    };

    println!("{} {}", w * h / 2f64, ok);
}
