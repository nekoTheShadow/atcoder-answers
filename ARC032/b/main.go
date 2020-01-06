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

	uf := NewUnionFind(n)
	for i := 0; i < m; i++ {
		a := stdin.ReadInt() - 1
		b := stdin.ReadInt() - 1
		uf.Union(a, b)
	}

	set := map[int]bool{}
	for i := 0; i < n; i++ {
		set[uf.Find(i)] = true
	}
	stdout.Println(len(set) - 1)
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

type UnionFind struct {
	parents []int
}

func NewUnionFind(n int) *UnionFind {
	uf := UnionFind{}
	uf.parents = []int{}
	for i := 0; i < n; i++ {
		uf.parents = append(uf.parents, i)
	}
	return &uf
}

func (uf *UnionFind) Find(x int) int {
	if uf.parents[x] == x {
		return x
	}

	uf.parents[x] = uf.Find(uf.parents[x])
	return uf.parents[x]
}

func (uf *UnionFind) Union(x, y int) {
	x = uf.Find(x)
	y = uf.Find(y)
	if x != y {
		uf.parents[y] = x
	}
}
