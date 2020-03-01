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
	a := make([]int, n)
	for i := 0; i < n; i++ {
		a[i] = -1
	}

	m := stdin.ReadInt()
	for i := 0; i < m; i++ {
		s := stdin.ReadInt() - 1
		c := stdin.ReadInt()
		if a[s] == -1 || a[s] == c {
			a[s] = c
		} else {
			stdout.Println(-1)
			return
		}
	}

	if n == 1 {
		if a[0] == -1 {
			stdout.Println(0)
		} else {
			stdout.Println(a[0])
		}
		return
	}

	if a[0] == 0 {
		stdout.Println(-1)
		return
	}

	for i := 0; i < n; i++ {
		if a[i] == -1 {
			if i == 0 {
				a[i] = 1
			} else {
				a[i] = 0
			}
		}
	}

	ans := 0
	d := 1
	for i := n - 1; i >= 0; i-- {
		ans += a[i] * d
		d *= 10
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
