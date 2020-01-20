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
	s := []string{}
	for i := 0; i < h; i++ {
		s = append(s, stdin.Read())
	}

	diffs := []*Tuple{
		&Tuple{x: 0, y: 1},
		&Tuple{x: 0, y: -1},
		&Tuple{x: 1, y: 0},
		&Tuple{x: -1, y: 0},
	}
	ans := 0
	for x := 0; x < h; x++ {
		for y := 0; y < w; y++ {
			if s[x][y] == '#' {
				continue
			}

			score := [][]int{}
			for i := 0; i < h; i++ {
				score = append(score, []int{})
				for j := 0; j < w; j++ {
					score[i] = append(score[i], 1000000)
				}
			}
			score[x][y] = 0

			stack := NewDeque()
			stack.AppendLast(&Tuple{x: x, y: y})

			for !stack.IsEmpty() {
				t, _ := stack.PopLast().(*Tuple)
				for _, diff := range diffs {
					nx := t.x + diff.x
					ny := t.y + diff.y
					if 0 <= nx && nx < h && 0 <= ny && ny < w && s[nx][ny] == '.' && score[t.x][t.y]+1 < score[nx][ny] {
						score[nx][ny] = score[t.x][t.y] + 1
						stack.AppendLast(&Tuple{x: nx, y: ny})
					}
				}
			}

			max := 0
			for i := 0; i < h; i++ {
				for j := 0; j < w; j++ {
					if s[i][j] == '.' {
						max = Max(max, score[i][j])
					}
				}
			}

			ans = Max(max, ans)
		}
	}

	stdout.Println(ans)
}

type Tuple struct {
	x int
	y int
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

const MOD = 1000000007

var FCT = []int{1}

func ModPow(x, y int) int {
	z := 1
	for y > 0 {
		if y%2 == 0 {
			x = (x * x) % MOD
			y /= 2
		} else {
			z = (z * x) % MOD
			y--
		}
	}
	return z
}

func ModInv(x int) int {
	return ModPow(x, MOD-2)
}

func C(n, r int) int {
	for i := len(FCT); i <= n; i++ {
		FCT = append(FCT, FCT[i-1]*i%MOD)
	}
	return FCT[n] * (ModInv(FCT[n-r]) * ModInv(FCT[r]) % MOD) % MOD
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

func Max(a ...int) int {
	v := a[0]
	for i := 1; i < len(a); i++ {
		if v < a[i] {
			v = a[i]
		}
	}
	return v
}
