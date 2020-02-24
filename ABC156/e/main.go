package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
)

func exec(stdin *Stdin, stdout *Stdout) {
	n := stdin.ReadInt()
	k := stdin.ReadInt()

	if k == 1 {
		stdout.Println(n * (n - 1) % MOD)
	} else if n <= k {
		stdout.Println(H(n, n))
	} else {
		ans := 0
		for m := 0; m <= k; m++ {
			ans += C(n, m) * H(n-m, m)
			ans %= MOD
		}
		stdout.Println(ans)
	}

}

const MOD = 1000000007

var FCT = []int{1}

func ModPow(x, y int) int {
	z := 1
	for y > 0 {
		if y%2 == 0 {
			x = (x * x) % MOD
			y /= 2
		} else {
			z = (z * x) % MOD
			y--
		}
	}
	return z
}

func ModInv(x int) int {
	return ModPow(x, MOD-2)
}

func C(n, r int) int {
	for i := len(FCT); i <= n; i++ {
		FCT = append(FCT, FCT[i-1]*i%MOD)
	}
	return FCT[n] * (ModInv(FCT[n-r]) * ModInv(FCT[r]) % MOD) % MOD
}

func H(n, r int) int {
	return C(n+r-1, r)
}

func main() {
	stdout := NewStdout()
	defer stdout.Flush()
	exec(NewStdin(bufio.ScanWords), stdout)
}

type Stdin struct {
	stdin *bufio.Scanner
}

func NewStdin(split bufio.SplitFunc) *Stdin {
	s := Stdin{bufio.NewScanner(os.Stdin)}
	s.stdin.Split(split)
	s.stdin.Buffer(make([]byte, bufio.MaxScanTokenSize), int(math.MaxInt32))
	return &s
}

func (s *Stdin) Read() string {
	s.stdin.Scan()
	return s.stdin.Text()
}

func (s *Stdin) ReadInt() int {
	n, _ := strconv.Atoi(s.Read())
	return n
}

func (s *Stdin) ReadFloat64() float64 {
	n, _ := strconv.ParseFloat(s.Read(), 64)
	return n
}

type Stdout struct {
	stdout *bufio.Writer
}

func NewStdout() *Stdout {
	return &Stdout{bufio.NewWriter(os.Stdout)}
}

func (s *Stdout) Flush() {
	s.stdout.Flush()
}

func (s *Stdout) Println(a ...interface{}) {
	fmt.Fprintln(s.stdout, a...)
}
