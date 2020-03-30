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
	a := make([]int, n)
	for i := 0; i < n; i++ {
		a[i] = stdin.ReadInt()
	}

	c := NewCounter()
	for i := 0; i < n; i++ {
		c.Increment(a[i])
	}

	sum := 0
	for _, k := range c.Keys() {
		v := c.Get(k)
		sum += v * (v - 1) / 2
	}

	for i := 0; i < n; i++ {
		v := c.Get(a[i])
		count := sum
		count -= v * (v - 1) / 2
		count += (v - 1) * (v - 2) / 2
		stdout.Println(count)
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
