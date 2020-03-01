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
	a := []int{}
	for i := 0; i < n; i++ {
		s := stdin.Read()
		for _, ch := range s {
			a = append(a, int(ch-'0'))
		}
	}

	m := len(a)
	b := make([]int, m)
	for i := 0; i < m; i++ {
		b[i] = a[m-i-1]
	}

	mod := 1000000007
	fct := make([]int, m)
	fct[0] = 1
	for i := 1; i < m; i++ {
		fct[i] = fct[i-1] * 10 % mod
	}

	sum := 0
	for i := 0; i < m; i++ {
		sum += b[i] * fct[i] % mod
		sum %= mod
	}

	stdout.Println(sum)

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
