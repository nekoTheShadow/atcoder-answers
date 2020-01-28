package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"sort"
	"strconv"
	"strings"
)

func exec(stdin *Stdin, stdout *Stdout) {
	n := stdin.ReadInt()
	m := stdin.ReadInt()
	s := stdin.Read()

	a := []int{}
	for i := 0; i < n+1; i++ {
		if s[i] == '0' {
			a = append(a, i)
		}
	}

	b := make([]int, n+1)
	for i := n; i >= 0; i-- {
		j := sort.Search(len(a), func(x int) bool {
			return a[x] >= i-m
		})
		b[i] = a[j]
	}

	c := []int{}
	x := n
	for x > 0 {
		if x == b[x] {
			stdout.Println(-1)
			return
		}
		c = append(c, x-b[x])
		x = b[x]
	}

	d := []string{}
	for i := len(c) - 1; i >= 0; i-- {
		d = append(d, strconv.Itoa(c[i]))
	}
	stdout.Println(strings.Join(d, " "))
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
