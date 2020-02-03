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
	tuples := []Tuple{}
	count := map[Key]int{}
	for i := 0; i < n; i++ {
		r := stdin.ReadInt()
		h := stdin.ReadInt() - 1
		tuple := Tuple{R: r, H: h, I: i}
		key := Key{R: r, H: h}
		tuples = append(tuples, tuple)
		if v, ok := count[key]; ok {
			count[key] = v + 1
		} else {
			count[key] = 1
		}
	}
	sort.Sort(Tuples(tuples))

	answers := make([]Ans, n)
	for _, tuple := range tuples {
		ans := Ans{
			X: sort.Search(len(tuples), func(i int) bool { return tuples[i].R >= tuple.R }) + getValue(count, Key{R: tuple.R, H: (tuple.H + 1) % 3}),
			Z: count[Key{R: tuple.R, H: (tuple.H + 0) % 3}] - 1,
		}
		ans.Y = n - ans.X - ans.Z - 1
		answers[tuple.I] = ans
	}

	for _, ans := range answers {
		line := fmt.Sprintf("%d %d %d", ans.X, ans.Y, ans.Z)
		stdout.Println(line)
	}
}

func getValue(d map[Key]int, key Key) int {
	if v, ok := d[key]; ok {
		return v
	} else {
		return 0
	}
}

type Tuple struct {
	R int
	H int
	I int
}

type Key struct {
	R int
	H int
}

type Ans struct {
	X int
	Y int
	Z int
}

type Tuples []Tuple

func (ts Tuples) Len() int           { return len(ts) }
func (ts Tuples) Less(i, j int) bool { return ts[i].R < ts[j].R }
func (ts Tuples) Swap(i, j int)      { ts[i], ts[j] = ts[j], ts[i] }

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
