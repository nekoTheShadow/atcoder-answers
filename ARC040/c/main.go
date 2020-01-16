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
	m := make([][]bool, n)
	for i := 0; i < n; i++ {
		m[i] = make([]bool, n)
		s := stdin.Read()
		for j := 0; j < n; j++ {
			m[i][j] = s[j] == 'o'
		}
	}

	c := 0
	for i := 0; i < n; i++ {
		for j := n - 1; j >= 0; j-- {
			if m[i][j] {
				continue
			}

			c++
			for x := 0; x <= j; x++ {
				m[i][x] = true
			}

			if i+1 < n {
				for x := j; x < n; x++ {
					m[i+1][x] = true
				}
			}
		}
	}

	stdout.Println(c)
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
