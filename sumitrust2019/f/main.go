package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
)

func exec(stdin *Stdin, stdout *Stdout) {
	t1 := stdin.ReadInt()
	t2 := stdin.ReadInt()
	a1 := stdin.ReadInt()
	a2 := stdin.ReadInt()
	b1 := stdin.ReadInt()
	b2 := stdin.ReadInt()

	v1 := a1 - b1
	v2 := a2 - b2

	if (v1 > 0 && v2 > 0) || (v1 < 0 && v2 < 0) {
		stdout.Println(0)
		return
	}
	if v1*t1+v2*t2 == 0 {
		stdout.Println("infinity")
		return
	}

	w1 := v1 * t1
	w2 := v1*t1 + v2*t2
	if (w1 > 0 && w2 > 0) || (w1 < 0 && w2 < 0) {
		stdout.Println(0)
		return
	}

	s := Abs(w1) / Abs(w2)
	t := Abs(w1) % Abs(w2)
	if t == 0 {
		stdout.Println(s * 2)
	} else {
		stdout.Println(s*2 + 1)
	}
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

func Abs(x int) int {
	if x > 0 {
		return x
	} else {
		return x * -1
	}
}
