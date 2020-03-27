package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
)

func exec(stdin *Stdin, stdout *Stdout) {
	h := stdin.ReadInt()
	w := stdin.ReadInt()
	c := make([][]int, h)
	for i := 0; i < h; i++ {
		c[i] = make([]int, w)
		for j := 0; j < w; j++ {
			if i%2 == j%2 {
				c[i][j] = stdin.ReadInt()
			} else {
				c[i][j] = stdin.ReadInt() * -1
			}

		}
	}

	a := make([][]int, h+1)
	for i := 0; i < h+1; i++ {
		a[i] = make([]int, w+1)
	}

	for i := 0; i < h; i++ {
		for j := 0; j < w; j++ {
			a[i+1][j+1] = a[i+1][j] + a[i][j+1] - a[i][j] + c[i][j]
		}
	}

	ans := 0
	for i1 := 0; i1 < h; i1++ {
		for j1 := 0; j1 < w; j1++ {
			for i2 := i1; i2 < h; i2++ {
				for j2 := j1; j2 < w; j2++ {
					x := a[i2+1][j2+1] - a[i2+1][j1] - a[i1][j2+1] + a[i1][j1]
					if x == 0 {
						ans = Max(ans, (i2-i1+1)*(j2-j1+1))
					}
				}
			}
		}
	}

	stdout.Println(ans)
}

func Max(a int, b ...int) int {
	for _, v := range b {
		if a < v {
			a = v
		}
	}
	return a
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
