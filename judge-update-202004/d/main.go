package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
)

const INFINITY = math.MaxInt64/2 - 1

func exec(stdin *Stdin, stdout *Stdout) {
	n := stdin.ReadInt()
	q := stdin.ReadInt()
	a := make([]int, n)
	s := make([]int, q)
	for i := 0; i < n; i++ {
		a[i] = stdin.ReadInt()
	}
	for i := 0; i < q; i++ {
		s[i] = stdin.ReadInt()
	}

	b := make([]int, n)
	b[0] = a[0]
	for i := 1; i < n; i++ {
		b[i] = Gcd(b[i-1], a[i])
	}
	for i := 0; i < n/2; i++ {
		b[i], b[n-i-1] = b[n-i-1], b[i]
	}

	for i := 0; i < q; i++ {
		ng := -1
		ok := n
		for Abs(ok-ng) > 1 {
			mi := (ok + ng) / 2
			if Gcd(b[mi], s[i]) > 1 {
				ok = mi
			} else {
				ng = mi
			}
		}

		if ok == 0 {
			stdout.Println(Gcd(b[0], s[i]))
		} else {
			stdout.Println(n - ok + 1)
		}
	}
}

func Gcd(x, y int) int {
	if x < y {
		x, y = y, x
	}

	for y > 0 {
		x, y = y, x%y
	}

	return x
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
	s.stdin.Buffer(make([]byte, bufio.MaxScanTokenSize), INFINITY)
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

func Min(a int, b ...int) int {
	for _, v := range b {
		if v < a {
			a = v
		}
	}
	return a
}

func Max(a int, b ...int) int {
	for _, v := range b {
		if a < v {
			a = v
		}
	}
	return a
}

func Abs(x int) int {
	if x > 0 {
		return x
	} else {
		return x * -1
	}
}

func Pow(x, y int) int {
	z := 1
	for y > 0 {
		if y%2 == 0 {
			x *= x
			y /= 2
		} else {
			z *= x
			y -= 1
		}
	}
	return z
}

func CreateMatrix(x, y int) [][]int {
	matrix := make([][]int, x)
	for i := 0; i < x; i++ {
		matrix[i] = make([]int, y)
	}
	return matrix
}
