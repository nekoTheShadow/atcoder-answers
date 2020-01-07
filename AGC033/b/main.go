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
	n := stdin.ReadInt()
	sr := stdin.ReadInt()
	sc := stdin.ReadInt()
	s := stdin.Read() // Takahashi: 取り除く
	t := stdin.Read() // Aoki: 残す

	var x int

	// 右に逃げる戦略
	x = sc
	for i := 0; i < n; i++ {
		if s[i] == 'R' {
			x++
			if !(1 <= x && x <= w) {
				stdout.Println("NO")
				return
			}
		}
		if t[i] == 'L' && 1 <= x-1 && x-1 <= w {
			x--
		}
	}

	// 左に逃げる戦略
	x = sc
	for i := 0; i < n; i++ {
		if s[i] == 'L' {
			x--
			if !(1 <= x && x <= w) {
				stdout.Println("NO")
				return
			}
		}
		if t[i] == 'R' && 1 <= x+1 && x+1 <= w {
			x++
		}
	}

	// 上に逃げる戦略
	x = sr
	for i := 0; i < n; i++ {
		if s[i] == 'U' {
			x--
			if !(1 <= x && x <= h) {
				stdout.Println("NO")
				return
			}
		}
		if t[i] == 'D' && 1 <= x+1 && x+1 <= h {
			x++
		}
	}

	// 下に逃げる戦略
	x = sr
	for i := 0; i < n; i++ {
		if s[i] == 'D' {
			x++
			if !(1 <= x && x <= h) {
				stdout.Println("NO")
				return
			}
		}
		if t[i] == 'U' && 1 <= x-1 && x-1 <= h {
			x--
		}
	}

	stdout.Println("YES")
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
