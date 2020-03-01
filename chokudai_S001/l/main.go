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
	n := stdin.ReadInt()
	line := stdin.Read()
	a := []int{}
	b := map[int]int{}
	for i, s := range strings.Split(line, " ") {
		v, _ := strconv.Atoi(s)
		v--
		a = append(a, v)
		b[v] = i
	}

	c := 0
	for i := 0; i < len(a); i++ {
		if a[i] == i {
			continue
		}

		i1 := i
		v1 := a[i1]
		i2 := b[i]
		v2 := a[i2]

		a[i1] = v2
		a[i2] = v1
		b[v1] = i2
		b[v2] = i1

		c++
	}

	if c <= n {
		if (n-c)%2 == 0 {
			stdout.Println("YES")
		} else {
			stdout.Println("NO")
		}
	} else {
		stdout.Println("NO")
	}

}

func main() {
	stdout := NewStdout()
	defer stdout.Flush()
	exec(NewStdin(bufio.ScanLines), stdout)
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
