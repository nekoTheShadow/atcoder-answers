package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
	"strings"
)

type Edge struct {
	From int
	To   int
	Cost int
}

func exec(stdin *Stdin, stdout *Stdout) {
	n := stdin.ReadInt()
	m := stdin.ReadInt()

	e := make([][]Edge, n)
	for i := 0; i < n; i++ {
		e[i] = []Edge{}
	}
	for i := 0; i < m; i++ {
		l := stdin.ReadInt() - 1
		r := stdin.ReadInt() - 1
		c := stdin.ReadInt()
		e[l] = append(e[l], Edge{From: l, To: r, Cost: c})
	}
	for i := 0; i < n-1; i++ {
		e[i+1] = append(e[i+1], Edge{From: i + 1, To: i, Cost: 0})
	}

	d := make([]int, n)
	inf := 1 << 60
	for i := 0; i < n; i++ {
		d[i] = inf
	}
	d[0] = 0

	q := NewHeapq()
	q.Push(0, 0)
	for !q.isEmpty() {
		cur, _ := q.Pop().(int)
		for _, edge := range e[cur] {
			if d[cur]+edge.Cost < d[edge.To] {
				d[edge.To] = d[cur] + edge.Cost
				q.Push(d[edge.To], edge.To)
			}
		}
	}

	if d[n-1] == inf {
		stdout.Println(-1)
	} else {
		stdout.Println(d[n-1])
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

type Heapq struct {
	elements []*element
}

type element struct {
	x int
	v interface{}
}

func NewHeapq() *Heapq {
	return &Heapq{elements: []*element{nil}}
}

func (heapq *Heapq) Push(x int, v interface{}) {
	heapq.elements = append(heapq.elements, &element{x: x, v: v})
	n := len(heapq.elements) - 1
	for n > 1 {
		if heapq.elements[n/2].x < heapq.elements[n].x {
			break
		}
		heapq.elements[n/2], heapq.elements[n] = heapq.elements[n], heapq.elements[n/2]
		n /= 2
	}
}

func (heapq *Heapq) Pop() interface{} {
	head := heapq.elements[1].v
	heapq.elements[1] = heapq.elements[len(heapq.elements)-1]
	heapq.elements = heapq.elements[:len(heapq.elements)-1]

	n := 1
	for n < len(heapq.elements) {
		if n*2 >= len(heapq.elements) {
			break
		}

		var m int
		if n*2+1 >= len(heapq.elements) {
			m = n * 2
		} else {
			if heapq.elements[n*2].x < heapq.elements[n*2+1].x {
				m = n * 2
			} else {
				m = n*2 + 1
			}
		}

		if heapq.elements[n].x < heapq.elements[m].x {
			break
		}
		heapq.elements[n], heapq.elements[m] = heapq.elements[m], heapq.elements[n]
		n = m
	}

	return head
}

func (heapq *Heapq) isEmpty() bool {
	return len(heapq.elements) == 1
}

func (heapq *Heapq) Size() int {
	return len(heapq.elements) - 1
}

func (heapq *Heapq) String() string {
	s := make([]string, len(heapq.elements)-1)
	for i := 1; i < len(heapq.elements); i++ {
		s[i-1] = fmt.Sprintf("%v", heapq.elements[i].v)
	}
	return strings.Join(s, " ")
}
