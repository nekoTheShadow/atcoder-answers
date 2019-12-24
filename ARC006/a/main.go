package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
)

func exec(stdin *Stdin, stdout *Stdout) {
	e := make(map[int]bool, 6)
	l := make([]int, 6)
	for i := 0; i < 6; i++ {
		e[stdin.ReadInt()] = true
	}
	b := stdin.ReadInt()
	for i := 0; i < 6; i++ {
		l[i] = stdin.ReadInt()
	}

	c := 0
	for _, k := range l {
		if _, ok := e[k]; ok {
			c++
		}
	}

	if c == 6 {
		stdout.Println(1)
	} else if c == 5 {
		for _, k := range l {
			if _, ok := e[k]; !ok {
				if k == b {
					stdout.Println(2)
				} else {
					stdout.Println(3)
				}
			}
		}
	} else if c == 4 {
		stdout.Println(4)
	} else if c == 3 {
		stdout.Println(5)
	} else {
		stdout.Println(0)
	}
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
