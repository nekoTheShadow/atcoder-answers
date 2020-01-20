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
	m := stdin.ReadInt()

	d := [][]string{}
	for i := 0; i < n; i++ {
		d = append(d, []string{})
	}

	for i := 0; i < m; i++ {
		p := stdin.ReadInt() - 1
		s := stdin.Read()
		d[p] = append(d[p], s)
	}

	a := 0
	b := 0
	for i := 0; i < n; i++ {
		c := 0
		for j := 0; j < len(d[i]); j++ {
			if d[i][j] == "AC" {
				a++
				b += c
				break
			}

			if d[i][j] == "WA" {
				c++
			}
		}
	}

	stdout.Println(fmt.Sprintf("%d %d", a, b))
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
