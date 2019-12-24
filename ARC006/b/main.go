package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
	"strings"
)

func exec(stdin *Stdin, stdout *Stdout) {
	first := strings.SplitN(stdin.Read(), " ", -1)
	n, _ := strconv.Atoi(first[0])
	l, _ := strconv.Atoi(first[1])

	a := make([]int, n)
	for i := 0; i < n; i++ {
		a[i] = i + 1
	}

	for i := 0; i < l; i++ {
		w := strings.SplitN(stdin.Read(), "|", -1)
		for j := 1; j < n; j++ {
			if w[j] == "-" {
				a[j-1], a[j] = a[j], a[j-1]
			}
		}
	}

	w := stdin.Read()
	stdout.Println(a[strings.Index(w, "o")/2])
}

func main() {
	stdout := NewStdout()
	exec(NewStdin(), stdout)
	stdout.Flush()
}

type Stdin struct {
	stdin *bufio.Scanner
}

func NewStdin() *Stdin {
	s := Stdin{bufio.NewScanner(os.Stdin)}
	s.stdin.Split(bufio.ScanLines)
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
