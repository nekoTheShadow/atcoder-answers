package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
)

const INFINITY = math.MaxInt64/2 - 1

func exec(stdin *Stdin, stdout *Stdout) {
	a1 := stdin.ReadInt()
	a2 := stdin.ReadInt()
	a3 := stdin.ReadInt()

	n := Max(a1, a2, a3)
	b := CreateMatrix(3, n)
	for i := 0; i < 3; i++ {
		for j := 0; j < n; j++ {
			b[i][j] = INFINITY
		}
	}

	ans := 0
	for _, perms := range Permutations(a1 + a2 + a3) {
		x := 0
		for i := 0; i < a1; i++ {
			b[0][i] = perms[x]
			x++
		}
		for i := 0; i < a2; i++ {
			b[1][i] = perms[x]
			x++
		}
		for i := 0; i < a3; i++ {
			b[2][i] = perms[x]
			x++
		}

		ok := true
		for i := 0; i < 3; i++ {
			for j := 0; j < n-1; j++ {
				if b[i][j] > b[i][j+1] {
					ok = false
				}
			}
		}

		for j := 0; j < n; j++ {
			for i := 0; i < 2; i++ {
				if b[i][j] > b[i+1][j] {
					ok = false
				}
			}
		}

		if ok {
			ans++
		}
	}

	stdout.Println(ans)
}

// [0..n)の順列を生成する。
func Permutations(n int) [][]int {
	type e struct {
		bit uint
		a   []int
	}

	q := []*e{}
	for i := 0; i < n; i++ {
		q = append(q, &e{bit: 1 << uint(i), a: []int{i}})
	}

	p := [][]int{}
	for x := 0; x < len(q); x++ {
		cur := q[x]
		if cur.bit == (1<<uint(n) - 1) {
			p = append(p, cur.a)
			continue
		}

		for i := 0; i < n; i++ {
			if cur.bit&(1<<uint(i)) != 0 {
				continue
			}

			m := len(cur.a) + 1
			b := make([]int, m)
			copy(b, cur.a)
			b[m-1] = i
			q = append(q, &e{bit: cur.bit | (1 << uint(i)), a: b})
		}
	}
	return p
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
	s.stdin.Buffer(make([]byte, bufio.MaxScanTokenSize), INFINITY)
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

func Min(a int, b ...int) int {
	for _, v := range b {
		if v < a {
			a = v
		}
	}
	return a
}

func Max(a int, b ...int) int {
	for _, v := range b {
		if a < v {
			a = v
		}
	}
	return a
}

func Abs(x int) int {
	if x > 0 {
		return x
	} else {
		return x * -1
	}
}

func Pow(x, y int) int {
	z := 1
	for y > 0 {
		if y%2 == 0 {
			x *= x
			y /= 2
		} else {
			z *= x
			y -= 1
		}
	}
	return z
}

func CreateMatrix(x, y int) [][]int {
	matrix := make([][]int, x)
	for i := 0; i < x; i++ {
		matrix[i] = make([]int, y)
	}
	return matrix
}
