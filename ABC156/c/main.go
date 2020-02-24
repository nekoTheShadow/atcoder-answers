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
	x := make([]int, n)
	for i := 0; i < n; i++ {
		x[i] = stdin.ReadInt()
	}

	sum := 0
	for _, v := range x {
		sum += v
	}

	if sum%n == 0 {
		avg := sum / n
		ans := 0
		for _, v := range x {
			ans += (avg - v) * (avg - v)
		}
		stdout.Println(ans)
	} else {
		avg1 := sum / n
		avg2 := sum/n + 1
		ans1 := 0
		ans2 := 0
		for _, v := range x {
			ans1 += (avg1 - v) * (avg1 - v)
			ans2 += (avg2 - v) * (avg2 - v)
		}
		stdout.Println(Min(ans1, ans2))
	}
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
