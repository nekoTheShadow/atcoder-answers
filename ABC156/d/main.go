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
	a := stdin.ReadInt()
	b := stdin.ReadInt()

	ans := ModPow(2, n) - 1
	ans -= NoCacheC(n, a)
	ans += MOD
	ans %= MOD
	ans -= NoCacheC(n, b)
	ans += MOD
	ans %= MOD
	stdout.Println(ans)
}

const MOD = 1000000007

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

func NoCacheC(n, r int) int {
	numer := 1
	denom := 1
	for i := 0; i < r; i++ {
		numer = numer * (n - i) % MOD
		denom = denom * (i + 1) % MOD
	}
	return numer * ModInv(denom) % MOD
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
