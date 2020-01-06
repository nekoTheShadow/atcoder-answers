package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
)

func exec(stdin *Stdin, stdout *Stdout) {
	r := stdin.ReadInt()
	c := stdin.ReadInt()
	d := stdin.ReadInt()
	a := [][]int{}
	for i := 0; i < r; i++ {
		a = append(a, []int{})
		for j := 0; j < c; j++ {
			a[i] = append(a[i], stdin.ReadInt())
		}
	}

	ans := 0
	for i := 0; i < r; i++ {
		for j := 0; j < c; j++ {
			if i+j <= d && (d-(i+j))%2 == 0 {
				ans = Max(ans, a[i][j])
			}
		}
	}

	stdout.Println(ans)
}

func main() {
	stdout := NewStdout()
	exec(NewStdin(bufio.ScanWords), stdout)
	stdout.Flush()
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

func Min(a ...int) int {
	v := a[0]
	for i := 1; i < len(a); i++ {
		if a[i] < v {
			v = a[i]
		}
	}
	return v
}

func Max(a ...int) int {
	v := a[0]
	for i := 1; i < len(a); i++ {
		if v < a[i] {
			v = a[i]
		}
	}
	return v
}

func Abs(x int) int {
	if x > 0 {
		return x
	} else {
		return x * -1
	}
}
