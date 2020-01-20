package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"sort"
	"strconv"
)

func exec(stdin *Stdin, stdout *Stdout) {
	n := stdin.ReadInt()
	k := stdin.ReadInt()
	a := []int{}
	for i := 0; i < n; i++ {
		a = append(a, stdin.ReadInt())
	}

	ans := 0

	sort.Sort(sort.Reverse(sort.IntSlice(a)))
	for i := 0; i < n; i++ {
		s := n - i - 1
		if k-1 <= s {
			ans = (ans + (a[i] * C(s, k-1) % MOD)) % MOD
		}
	}

	sort.Sort(sort.IntSlice(a))
	for i := 0; i < n; i++ {
		s := n - i - 1
		if k-1 <= s {
			ans = (ans - (a[i] * C(s, k-1) % MOD)) % MOD
		}
	}

	if ans < 0 {
		ans += MOD
	}

	stdout.Println(ans)
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
