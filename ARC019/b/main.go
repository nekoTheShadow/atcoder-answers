package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
)

func exec(stdin *Stdin, stdout *Stdout) {
	a := stdin.Read()
	n := len(a)

	count := 0
	for i := 0; i < n; i++ {
		if a[i] == a[n-i-1] {
			count++
		}
	}

	ans := 0
	if n%2 == 0 {
		for i := 0; i < n; i++ {
			if count == n {
				ans += 25
			} else if n-count == 2 && a[i] != a[n-i-1] {
				ans += 24
			} else {
				ans += 25
			}
		}
	} else {
		for i := 0; i < n; i++ {
			if i == n/2 {
				if count != n {
					ans += 25
				}
			} else {
				if count == n {
					ans += 25
				} else if n-count == 2 && a[i] != a[n-i-1] {
					ans += 24
				} else {
					ans += 25
				}
			}
		}
	}

	stdout.Println(ans)
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
