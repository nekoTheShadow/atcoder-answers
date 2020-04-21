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
	x0 := stdin.ReadFloat64()
	y0 := stdin.ReadFloat64()
	n := stdin.ReadInt()
	x := make([]float64, n)
	y := make([]float64, n)
	for i := 0; i < n; i++ {
		x[i] = stdin.ReadFloat64()
		y[i] = stdin.ReadFloat64()
	}

	x = append(x, x[0])
	y = append(y, y[0])
	ans := math.MaxFloat64
	for i := 0; i < n; i++ {
		x1 := x[i]
		y1 := y[i]
		x2 := x[i+1]
		y2 := y[i+1]

		numer := (y2-y1)*x0 - (x2-x1)*y0 + x2*y1 - y2*x1
		denom := math.Pow(y2-y1, 2) + math.Pow(x2-x1, 2)
		d := math.Abs(numer) / math.Sqrt(denom)
		if d < ans {
			ans = d
		}
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

func (s *Stdin) ReadIntSlice(n int) []int {
	a := make([]int, n)
	for i := 0; i < n; i++ {
		a[i] = s.ReadInt()
	}
	return a
}

func (s *Stdin) ReadStringSlice(n int) []string {
	a := make([]string, n)
	for i := 0; i < n; i++ {
		a[i] = s.Read()
	}
	return a
}

func (s *Stdin) ReadFloat64Slice(n int) []float64 {
	a := make([]float64, n)
	for i := 0; i < n; i++ {
		a[i] = s.ReadFloat64()
	}
	return a
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
