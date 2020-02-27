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
	s := make([]string, n)
	for i := 0; i < n; i++ {
		s[i] = stdin.Read()
	}

	c := NewCounter()
	for _, v := range s {
		c.Increment(v)
	}

	d := map[int]map[string]bool{}
	for _, key := range c.Keys() {
		count := c.Get(key)
		word, _ := key.(string)
		if _, ok := d[count]; !ok {
			d[count] = map[string]bool{}
		}

		words := d[count]
		words[word] = true
	}

	tuples := []Tuple{}
	for count, words := range d {
		tuple := Tuple{
			Count: count,
			Words: []string{},
		}
		for word := range words {
			tuple.Words = append(tuple.Words, word)
		}
		tuples = append(tuples, tuple)
	}
	sort.Sort(Tuples(tuples))

	sort.Strings(tuples[0].Words)
	for _, word := range tuples[0].Words {
		stdout.Println(word)
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

type Counter map[interface{}]int

func NewCounter() *Counter {
	return &Counter{}
}

func (c *Counter) Increment(key interface{}) {
	d := map[interface{}]int(*c)
	d[key] = c.Get(key) + 1
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
		keys = append(keys, key)
	}
	return keys
}

type Tuple struct {
	Count int
	Words []string
}

type Tuples []Tuple

func (ts Tuples) Len() int           { return len(ts) }
func (ts Tuples) Less(i, j int) bool { return ts[i].Count > ts[j].Count }
func (ts Tuples) Swap(i, j int)      { ts[i], ts[j] = ts[j], ts[i] }
