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
	p := []int{}
	q := []int{}
	for i := 0; i < n; i++ {
		p = append(p, stdin.ReadInt())
	}
	for i := 0; i < n; i++ {
		q = append(q, stdin.ReadInt())
	}

	permutations := Permutation(p)
	sort.Sort(Matrix(permutations))

	a := 0
	b := 0
	for i, permutation := range permutations {
		if eql(permutation, p) {
			a = i
		}

		if eql(permutation, q) {
			b = i
		}
	}

	stdout.Println(Abs(a - b))
}

func Permutation(digits []int) [][]int {
	digits = clone(digits)
	permutations := [][]int{}
	permutations = append(permutations, clone(digits))

	n := len(digits)
	p := []int{}
	for i := 0; i < n+1; i++ {
		p = append(p, i)
	}

	for i := 1; i < n; {
		p[i]--
		var j int
		if i%2 == 0 {
			j = 0
		} else {
			j = p[i]
		}

		digits[i], digits[j] = digits[j], digits[i]
		permutations = append(permutations, clone(digits))
		for i = 1; p[i] == 0; i++ {
			p[i] = i
		}
	}

	return permutations
}

func Abs(x int) int {
	if x > 0 {
		return x
	} else {
		return x * -1
	}
}

func clone(digits []int) []int { return append([]int{}, digits...) }

func eql(a, b []int) bool {
	for i := 0; i < len(a); i++ {
		if a[i] != b[i] {
			return false
		}
	}
	return true
}

type Matrix [][]int

func (m Matrix) Len() int      { return len(m) }
func (m Matrix) Swap(i, j int) { m[i], m[j] = m[j], m[i] }
func (m Matrix) Less(i, j int) bool {
	for x := 0; x < len(m[i]); x++ {
		if m[i][x] == m[j][x] {
			continue
		}
		return m[i][x] < m[j][x]
	}
	return false
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
