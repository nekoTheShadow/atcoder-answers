package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
)

func exec(stdin *Stdin, stdout *Stdout) {
	inf := int(math.MaxInt32)/2 - 1

	n := stdin.ReadInt()
	d := make([][]int, n)
	for i := 0; i < n; i++ {
		d[i] = make([]int, n)
		for j := 0; j < n; j++ {
			if i == j {
				d[i][j] = 0
			} else {
				d[i][j] = inf
			}
		}
	}

	m := stdin.ReadInt()
	for i := 0; i < m; i++ {
		a := stdin.ReadInt() - 1
		b := stdin.ReadInt() - 1
		t := stdin.ReadInt()
		d[a][b] = t
		d[b][a] = t
	}

	for k := 0; k < n; k++ {
		for i := 0; i < n; i++ {
			for j := 0; j < n; j++ {
				d[i][j] = Min(d[i][j], d[i][k]+d[k][j])
			}
		}
	}

	ans := inf
	for _, v := range d {
		max := Max(v...)
		ans = Min(ans, max)
	}
	stdout.Println(ans)
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
