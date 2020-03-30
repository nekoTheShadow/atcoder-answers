package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
)

func exec(stdin *Stdin, stdout *Stdout) {
	h := stdin.ReadInt()
	w := stdin.ReadInt()
	k := stdin.ReadInt()
	s := make([][]int, h)
	for i := 0; i < h; i++ {
		line := stdin.Read()
		s[i] = make([]int, w)
		for j := 0; j < w; j++ {
			if line[j] == '0' {
				s[i][j] = 0
			} else {
				s[i][j] = 1
			}
		}
	}

	ans := h * w
	for _, crack := range Combinations(h-1, -1) {
		crack = append(crack, -1)

		x := 0
		cur := make([]int, len(crack)+1)
		for i := 0; i < h; i++ {
			cur[x] += s[i][0]
			if crack[x] == i {
				x++
			}
		}

		ng := false
		count := 0
		for j := 1; j < w; j++ {
			nxt := make([]int, len(crack)+1)
			for i := 0; i < len(cur); i++ {
				if k < cur[i] {
					ng = true
					break
				}
				nxt[i] = cur[i]
			}

			if ng {
				break
			}

			x = 0
			cracking := false
			for i := 0; i < h; i++ {
				nxt[x] += s[i][j]
				if k < nxt[x] {
					cracking = true
				}
				if crack[x] == i {
					x++
				}
			}

			if cracking {
				count++
				for i := 0; i < len(cur); i++ {
					nxt[i] -= cur[i]
				}
			}
			cur = nxt
		}

		if !ng {
			ans = Min(ans, count+len(crack)-1)
		}
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
		if r < 0 || len(idxs) == r {
			ans = append(ans, idxs)
		}
	}
	return ans
}

func Min(a int, b ...int) int {
	for _, v := range b {
		if v < a {
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
