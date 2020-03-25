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
	a := make([]int, n)
	for i := 0; i < n; i++ {
		a[i] = stdin.ReadInt()
	}

	b := make([]int, m)
	c := make([][]int, m)
	for i := 0; i < m; i++ {
		b[i] = stdin.ReadInt()
		l := stdin.ReadInt()
		c[i] = make([]int, l)
		for j := 0; j < l; j++ {
			c[i][j] = stdin.ReadInt() - 1
		}
	}

	ans := 0
	for _, combination := range Combinations(n, 9) {
		idxs := map[int]bool{}
		for _, idx := range combination {
			idxs[idx] = true
		}

		if len(idxs) != 9 {
			continue
		}

		sum := 0
		for i, _ := range idxs {
			sum += a[i]
		}
		for i, idols := range c {
			count := 0
			for _, idol := range idols {
				if _, ok := idxs[idol]; ok {
					count++
				}
			}

			if count >= 3 {
				sum += b[i]
			}
		}

		ans = Max(ans, sum)
	}

	stdout.Println(ans)
}

func Combinations(n int, r int) [][]int {
	ans := [][]int{}
	for x := uint(0); x < (uint(1) << uint(n)); x++ {
		idxs := []int{}
		for i := uint(0); i < uint(n); i++ {
			if x&(uint(1)<<i) != 0 {
				idxs = append(idxs, int(i))
			}
		}
		if len(idxs) == r {
			ans = append(ans, idxs)
		}
	}
	return ans
}

func Max(a int, b ...int) int {
	for _, v := range b {
		if a < v {
			a = v
		}
	}
	return a
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
