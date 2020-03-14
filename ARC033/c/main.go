package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
)

func exec(stdin *Stdin, stdout *Stdout) {
	n := 200000
	bit := NewBIT(n)

	q := stdin.ReadInt()
	for i := 0; i < q; i++ {
		t := stdin.ReadInt()
		x := stdin.ReadInt()

		if t == 1 {
			bit.Add(x, 1)
		}

		if t == 2 {
			ng := -1
			ok := n + 1
			for Abs(ok-ng) > 1 {
				mid := (ok + ng) / 2
				if bit.Sum(mid) >= x {
					ok = mid
				} else {
					ng = mid
				}
			}
			stdout.Println(ok)
			bit.Add(ok, -1)
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

func Abs(x int) int {
	if x > 0 {
		return x
	} else {
		return x * -1
	}
}
