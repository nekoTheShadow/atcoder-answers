package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
	"strings"
)

func exec(stdin *Stdin, stdout *Stdout) {
	n := stdin.ReadInt()
	x := stdin.ReadInt()
	w := make([]int, n)
	for i := 0; i < n; i++ {
		w[i] = stdin.ReadInt()
	}

	c1 := NewCounter()
	c2 := NewCounter()

	m := n / 2
	for _, comb := range Combinations(m) {
		k := 0
		for _, v := range comb {
			k += w[v]
		}
		c1.Increment(k)
	}
	for _, comb := range Combinations(n - m) {
		k := 0
		for _, v := range comb {
			k += w[m+v]
		}
		c2.Increment(k)
	}

	ans := 0
	for _, k := range c2.Keys() {
		v, _ := k.(int)
		ans += c1.Get(x-v) * c2.Get(v)
	}
	stdout.Println(ans)
}

func Combinations(m int) [][]int {
	lists := [][]int{[]int{}}
	for i := 0; i < m; i++ {
		buffs := [][]int{}
		for _, list := range lists {
			buff := []int{i}
			buff = append(buff, list...)
			buffs = append(buffs, buff)
		}
		lists = append(lists, buffs...)
	}
	return lists

	// a := [][]int{}
	// for i := 0; i < (1 << m); i++ {
	// 	d := []int{}
	// 	for j := 0; j < m; j++ {
	// 		if i&(1<<j) != 0 {
	// 			d = append(d, j)
	// 		}
	// 	}
	// 	a = append(a, d)
	// }
	// return a
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
