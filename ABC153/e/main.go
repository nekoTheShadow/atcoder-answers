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
	n := stdin.ReadInt()
	a := []int{}
	b := []int{}
	for i := 0; i < n; i++ {
		a = append(a, stdin.ReadInt())
		b = append(b, stdin.ReadInt())
	}

	dp := [][]int{}
	inf := int(math.MaxInt32)
	for i := 0; i < n+1; i++ {
		row := []int{}
		for j := 0; j < h+1; j++ {
			row = append(row, inf)
		}
		row[0] = 0
		dp = append(dp, row)
	}

	for i := 0; i < n; i++ {
		for j := 0; j < h+1; j++ {
			dp[i+1][j] = Min(dp[i+1][j], dp[i][j])
			dp[i+1][Min(h, j+a[i])] = Min(dp[i+1][Min(h, j+a[i])], dp[i+1][j]+b[i])
		}
	}
	stdout.Println(dp[n][h])
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
