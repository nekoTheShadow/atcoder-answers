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
	w := make([]string, n)
	for i := 0; i < n; i++ {
		w[i] = stdin.Read()
	}

	a := map[string]bool{}
	a[w[0]] = true
	for i := 1; i < n; i++ {
		if w[i-1][len(w[i-1])-1] != w[i][0] {
			if i%2 == 0 {
				stdout.Println("LOSE")
			} else {
				stdout.Println("WIN")
			}
			return
		}

		if _, ok := a[w[i]]; ok {
			if i%2 == 0 {
				stdout.Println("LOSE")
			} else {
				stdout.Println("WIN")
			}
			return
		}
		a[w[i]] = true
	}

	stdout.Println("DRAW")
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
