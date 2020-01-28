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
	k := stdin.ReadInt()
	a := []int{}
	for i := 0; i < n; i++ {
		a = append(a, stdin.ReadInt()-1)
	}

	s := make([]int, n+1)
	s[0] = 0
	for i := 0; i < n; i++ {
		s[i+1] = (s[i] + a[i]) % k
	}

	ans := 0
	c := map[int]int{}
	for i := 0; i < len(s); i++ {
		ans += get(c, s[i])
		c[s[i]] = get(c, s[i]) + 1
		if i-k+1 >= 0 {
			c[s[i-k+1]] = get(c, s[i-k+1]) - 1
		}
	}

	stdout.Println(ans)
}

func get(d map[int]int, k int) int {
	if v, ok := d[k]; ok {
		return v
	} else {
		d[k] = 0
		return 0
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
