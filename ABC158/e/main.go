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
	p := stdin.ReadInt()
	s := stdin.Read()

	if p == 2 || p == 5 {
		sum := 0
		for i := 0; i < n; i++ {
			if int(s[i]-'0')%p == 0 {
				sum += i + 1
			}
		}
		stdout.Println(sum)
		return
	}

	t := make([]int, n)
	for i := 0; i < n; i++ {
		t[n-i-1] = int(s[i] - '0')
	}

	d := make([]int, p)
	d[0] = 1
	mod := 0
	fct := 1
	sum := 0
	for i := 0; i < n; i++ {
		mod = (mod + t[i]*fct) % p
		sum += d[mod]
		d[mod]++
		fct = (fct * 10) % p
	}

	stdout.Println(sum)
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
