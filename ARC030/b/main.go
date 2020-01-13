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
	x := stdin.ReadInt() - 1
	jewels := map[int]bool{}
	for i := 0; i < n; i++ {
		h := stdin.ReadInt()
		if h == 1 {
			jewels[i] = true
		}
	}

	nexts := [][]int{}
	for i := 0; i < n; i++ {
		nexts = append(nexts, []int{})
	}
	for i := 0; i < n-1; i++ {
		a := stdin.ReadInt() - 1
		b := stdin.ReadInt() - 1
		nexts[a] = append(nexts[a], b)
		nexts[b] = append(nexts[b], a)
	}

	search := map[int]bool{}
	q := NewDeque()
	q.AppendLast(Tuple{history: map[int]bool{}, current: x})
	for !q.IsEmpty() {
		tuple, _ := q.PopFirst().(Tuple)

		if _, ok := jewels[tuple.current]; ok {
			search[tuple.current] = true
			for v := range tuple.history {
				search[v] = true
			}
		}

		for _, next := range nexts[tuple.current] {
			if _, ok := tuple.history[next]; ok {
				continue
			}
			q.AppendLast(tuple.Copy(next))
		}
	}

	cost := 0
	q.AppendLast(Tuple{history: map[int]bool{}, current: x})
	for !q.IsEmpty() {
		tuple, _ := q.PopFirst().(Tuple)
		for _, next := range nexts[tuple.current] {
			if _, ok := tuple.history[next]; ok {
				continue
			}
			if _, ok := search[next]; !ok {
				continue
			}

			cost++
			q.AppendLast(tuple.Copy(next))
		}
	}

	stdout.Println(cost * 2)
}

type Tuple struct {
	history map[int]bool
	current int
}

func (t Tuple) Copy(next int) Tuple {
	newHistory := map[int]bool{}
	newHistory[t.current] = true
	for k, v := range t.history {
		newHistory[k] = v
	}
	return Tuple{history: newHistory, current: next}
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

type Deque struct {
	deque []interface{}
}

func NewDeque() *Deque {
	return &Deque{deque: []interface{}{}}
}

func (d *Deque) AppendFirst(v interface{}) {
	e := make([]interface{}, len(d.deque)+1)
	copy(e[1:], d.deque)
	d.deque = e
}

func (d *Deque) AppendLast(v interface{}) {
	d.deque = append(d.deque, v)
}

func (d *Deque) PopLast() interface{} {
	n := len(d.deque)
	v := d.deque[n-1]
	d.deque = d.deque[0 : n-1]
	return v
}

func (d *Deque) PopFirst() interface{} {
	v := d.deque[0]
	d.deque = d.deque[1:]
	return v
}

func (d *Deque) IsEmpty() bool {
	return len(d.deque) == 0
}
