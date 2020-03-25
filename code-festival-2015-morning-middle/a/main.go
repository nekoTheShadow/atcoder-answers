package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"sort"
	"strconv"
)

func exec(stdin *Stdin, stdout *Stdout) {
	n := stdin.ReadInt()
	k := stdin.ReadInt()
	m := stdin.ReadInt()
	r := stdin.ReadInt()
	s := make([]int, n-1)
	for i := 0; i < n-1; i++ {
		s[i] = stdin.ReadInt()
	}

	sort.Sort(sort.Reverse(sort.IntSlice(s)))
	sum := 0
	for i := 0; i < k-1; i++ {
		sum += s[i]
	}

	// どうあがてもだめ
	diff := r*k - sum
	if m < diff {
		stdout.Println(-1)
		return
	}

	if n == k {
		if diff > 0 {
			stdout.Println(diff)
		} else {
			stdout.Println(0)
		}
	} else {
		if diff <= s[k-1] {
			stdout.Println(0)
		} else {
			stdout.Println(diff)
		}
	}
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
