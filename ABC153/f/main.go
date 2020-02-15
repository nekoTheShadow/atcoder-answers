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
	d := stdin.ReadInt()
	a := stdin.ReadInt()

	monsters := []Monster{}
	for i := 0; i < n; i++ {
		x := stdin.ReadInt()
		h := stdin.ReadInt()
		monsters = append(monsters, Monster{X: x, H: h})
	}
	sort.Sort(Monsters(monsters))

	q := NewDeque()
	c := 0
	ans := 0
	for _, monster := range monsters {
		for !q.IsEmpty() {
			rng := q.PeekFirst().(*Range)
			if rng.Left <= monster.X && monster.X <= rng.Right {
				break
			}

			c -= rng.Count
			q.PopFirst()
		}

		if c*a < monster.H {
			var count int
			if (monster.H-c*a)%a == 0 {
				count = (monster.H - c*a) / a
			} else {
				count = (monster.H-c*a)/a + 1
			}

			c += count
			ans += count
			rng := &Range{
				Left:  monster.X,
				Right: monster.X + 2*d,
				Count: count,
			}
			q.AppendLast(rng)
		}
	}

	stdout.Println(ans)
}

type Monster struct {
	X int
	H int
}

type Monsters []Monster

func (ms Monsters) Len() int           { return len(ms) }
func (ms Monsters) Less(i, j int) bool { return ms[i].X < ms[j].X }
func (ms Monsters) Swap(i, j int)      { ms[i], ms[j] = ms[j], ms[i] }

type Range struct {
	Left  int
	Right int
	Count int
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

func (d *Deque) PeekFirst() interface{} {
	return d.deque[0]
}

func (d *Deque) PeekLast() interface{} {
	return d.deque[len(d.deque)-1]
}

func (d *Deque) IsEmpty() bool {
	return len(d.deque) == 0
}

