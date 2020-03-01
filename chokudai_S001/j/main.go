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
	a := make([]int, n)
	for i := 0; i < n; i++ {
		a[i] = stdin.ReadInt()
	}

	sum := 0
	bit := NewBIT(n)
	for _, v := range a {
		sum += bit.Sum(n) - bit.Sum(v)
		bit.Add(v, 1)
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

// Binary Indexed Tree (1 based indexed)
type BIT struct {
	size int
	tree []int
}

func NewBIT(size int) *BIT {
	bit := BIT{
		size: size,
		tree: make([]int, size+1),
	}
	return &bit
}

func (bit *BIT) Sum(i int) int {
	sum := 0
	for i > 0 {
		sum += bit.tree[i]
		i -= i & -i
	}
	return sum
}

func (bit *BIT) Add(i, x int) {
	for i <= bit.size {
		bit.tree[i] += x
		i += i & -i
	}
}
