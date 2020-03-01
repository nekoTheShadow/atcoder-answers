package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
	"strings"
)

func exec(stdin *Stdin, stdout *Stdout) {
	n := stdin.ReadInt()
	s := stdin.Read()

	bits := map[string]*BIT{}
	for c := 'a'; c <= 'z'; c++ {
		bits[string(c)] = NewBIT(n)
	}

	chars := strings.Split(s, "")
	for i, c := range chars {
		bits[c].Add(i+1, 1)
	}

	q := stdin.ReadInt()
	for i := 0; i < q; i++ {
		query := stdin.ReadInt()
		if query == 1 {
			x := stdin.ReadInt()
			c := stdin.Read()
			if chars[x-1] != c {
				bits[chars[x-1]].Add(x, -1)
				chars[x-1] = c
				bits[chars[x-1]].Add(x, 1)
			}
		}

		if query == 2 {
			l := stdin.ReadInt()
			r := stdin.ReadInt()
			ans := 0
			for _, bit := range bits {
				d := bit.Sum(r) - bit.Sum(l-1)
				if d > 0 {
					ans++
				}
			}
			stdout.Println(ans)
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
