package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"sort"
	"strconv"
)

func exec(stdin *Stdin) {
	n := stdin.ReadInt()
	s := []int{}
	for i := 0; i < n; i++ {
		w := stdin.ReadInt()
		x := sort.Search(len(s), func(x int) bool {
			return s[x] >= w
		})
		if x < len(s) {
			s[x] = w
		} else {
			s = append(s, w)
		}
	}
	fmt.Println(len(s))
}

func main() {
	exec(NewStdin())
}

type Stdin struct {
	stdin *bufio.Scanner
}

func NewStdin() *Stdin {
	s := Stdin{bufio.NewScanner(os.Stdin)}
	s.stdin.Split(bufio.ScanWords)
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
