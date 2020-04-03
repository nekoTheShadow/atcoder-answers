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
	a := stdin.Read()
	b := stdin.Read()

	p, _ := strconv.Atoi(a)
	q, _ := strconv.Atoi(b)
	ans := p - q

	for i := 0; i < 3; i++ {
		for j := 0; j <= 9; j++ {
			if i == 0 && j == 0 {
				continue
			}

			x, _ := strconv.Atoi(replace(a, i, j))
			y, _ := strconv.Atoi(b)
			ans = Max(ans, x-y)
		}
	}
	for i := 0; i < 3; i++ {
		for j := 0; j <= 9; j++ {
			if i == 0 && j == 0 {
				continue
			}

			x, _ := strconv.Atoi(a)
			y, _ := strconv.Atoi(replace(b, i, j))
			ans = Max(ans, x-y)
		}
	}

	stdout.Println(ans)
}

func replace(s string, x, y int) string {
	b := []byte(s)
	b[x] = []byte(strconv.Itoa(y))[0]
	return string(b)
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
