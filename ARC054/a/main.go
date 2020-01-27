package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
)

func exec(stdin *Stdin, stdout *Stdout) {
	l := stdin.ReadFloat64()
	x := stdin.ReadFloat64()
	y := stdin.ReadFloat64()
	s := stdin.ReadFloat64()
	d := stdin.ReadFloat64()

	if s <= d {
		if x < y {
			t1 := (d - s) / (x + y)
			t2 := (l - (d - s)) / (y - x)
			stdout.Println(Min(t1, t2))
		} else {
			t1 := (d - s) / (x + y)
			t2 := (d - s) / (x - y)
			stdout.Println(Min(t1, t2))
		}
	} else {
		if x < y {
			t1 := (l - (s - d)) / (x + y)
			t2 := (s - d) / (y - x)
			stdout.Println(Min(t1, t2))
		} else {
			t1 := (l - (s - d)) / (x + y)
			t2 := (l - (s - d)) / (x - y)
			stdout.Println(Min(t1, t2))
		}
	}
}

func Min(a ...float64) float64 {
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
