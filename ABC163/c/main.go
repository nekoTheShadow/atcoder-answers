package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
	"strings"
)

const INFINITY = math.MaxInt64/2 - 1

func exec(stdin *Stdin, stdout *Stdout) {
	n := stdin.ReadInt()
	a := stdin.ReadIntSlice(n - 1)

	c := NewCounter()
	for _, k := range a {
		c.Increment(k)
	}

	for k := 1; k <= n; k++ {
		stdout.Println(c.Get(k))
	}

}

type Counter map[interface{}]int

func NewCounter() *Counter {
	return &Counter{}
}

func (c *Counter) put(key interface{}, v int) {
	d := map[interface{}]int(*c)
	d[key] = v
}

func (c *Counter) Increment(key interface{}) {
	c.put(key, c.Get(key)+1)
}

func (c *Counter) Decrement(key interface{}) {
	v := c.Get(key)
	if v > 0 {
		c.put(key, v-1)
	}
}

func (c *Counter) Get(key interface{}) int {
	d := map[interface{}]int(*c)
	if v, ok := d[key]; ok {
		return v
	} else {
		return 0
	}
}

func (c *Counter) Keys() []interface{} {
	d := map[interface{}]int(*c)
	keys := []interface{}{}
	for key := range d {
		if c.Get(key) > 0 {
			keys = append(keys, key)
		}
	}
	return keys
}

func (c *Counter) String() string {
	strs := []string{}
	for _, key := range c.Keys() {
		strs = append(strs, fmt.Sprintf("%v => %d", key, c.Get(key)))
	}
	return "[" + strings.Join(strs, ", ") + "]"
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
	s.stdin.Buffer(make([]byte, bufio.MaxScanTokenSize), INFINITY)
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

func (s *Stdin) ReadIntSlice(n int) []int {
	a := make([]int, n)
	for i := 0; i < n; i++ {
		a[i] = s.ReadInt()
	}
	return a
}

func (s *Stdin) ReadStringSlice(n int) []string {
	a := make([]string, n)
	for i := 0; i < n; i++ {
		a[i] = s.Read()
	}
	return a
}

func (s *Stdin) ReadFloat64Slice(n int) []float64 {
	a := make([]float64, n)
	for i := 0; i < n; i++ {
		a[i] = s.ReadFloat64()
	}
	return a
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

func Min(a int, b ...int) int {
	for _, v := range b {
		if v < a {
			a = v
		}
	}
	return a
}

func Max(a int, b ...int) int {
	for _, v := range b {
		if a < v {
			a = v
		}
	}
	return a
}

func Abs(x int) int {
	if x > 0 {
		return x
	} else {
		return x * -1
	}
}

func Pow(x, y int) int {
	z := 1
	for y > 0 {
		if y%2 == 0 {
			x *= x
			y /= 2
		} else {
			z *= x
			y -= 1
		}
	}
	return z
}

func CreateMatrix(x, y int) [][]int {
	matrix := make([][]int, x)
	for i := 0; i < x; i++ {
		matrix[i] = make([]int, y)
	}
	return matrix
}
