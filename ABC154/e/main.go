package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
)

func exec(stdin *Stdin, stdout *Stdout) {
	n := stdin.Read()
	k := stdin.ReadInt()

	m := len(n)
	ans := 0
	for x := 0; x < m; x++ {
		l := 0
		for i := 0; i < x; i++ {
			if n[i] != '0' {
				l++
			}
		}

		d := int(n[x] - '0')
		if d > 0 {
			ans += C(m-x-1, k-l-1) * Pow(9, k-l-1) * (d - 1)
			ans += C(m-x-1, k-l) * Pow(9, k-l)
		}
	}

	l := 0
	for _, ch := range n {
		if ch != '0' {
			l++
		}
	}
	if l == k {
		ans++
	}

	stdout.Println(ans)
}

func C(n, k int) int {
	if n == k || k == 0 {
		return 1
	}
	if n <= 0 || k < 0 || n < k {
		return 0
	}
	return C(n-1, k-1) + C(n-1, k)
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
