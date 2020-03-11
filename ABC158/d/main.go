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
	s := stdin.Read()
	q := stdin.ReadInt()

	d := NewDeque()
	for i := 0; i < len(s); i++ {
		d.AppendLast(s[i : i+1])
	}

	normal := true
	for i := 0; i < q; i++ {
		query := stdin.ReadInt()
		if query == 1 {
			normal = !normal
		} else {
			f := stdin.ReadInt()
			c := stdin.Read()
			if normal {
				if f == 1 {
					d.AppendFirst(c)
				} else {
					d.AppendLast(c)
				}
			} else {
				if f == 1 {
					d.AppendLast(c)
				} else {
					d.AppendFirst(c)
				}
			}
		}
	}

	t := []string{}
	for !d.IsEmpty() {
		if normal {
			c, _ := d.PopFirst().(string)
			t = append(t, c)
		} else {
			c, _ := d.PopLast().(string)
			t = append(t, c)
		}
	}
	stdout.Println(strings.Join(t, ""))
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
	head []interface{}
	tail []interface{}
}

func NewDeque() *Deque {
	return &Deque{
		head: []interface{}{},
		tail: []interface{}{},
	}
}

func (d *Deque) AppendLast(v interface{}) {
	d.tail = append(d.tail, v)
}

func (d *Deque) AppendFirst(v interface{}) {
	d.head = append(d.head, v)
}

func (d *Deque) PopLast() interface{} {
	if len(d.tail) == 0 {
		v := d.head[0]
		d.head = d.head[1:]
		return v
	} else {
		v := d.tail[len(d.tail)-1]
		d.tail = d.tail[:len(d.tail)-1]
		return v
	}
}

func (d *Deque) PopFirst() interface{} {
	if len(d.head) == 0 {
		v := d.tail[0]
		d.tail = d.tail[1:]
		return v
	} else {
		v := d.head[len(d.head)-1]
		d.head = d.head[:len(d.head)-1]
		return v
	}
}

func (d *Deque) IsEmpty() bool {
	return len(d.head) == 0 && len(d.tail) == 0
}

func (d *Deque) PeekFirst() interface{} {
	if len(d.head) == 0 {
		return d.tail[0]
	} else {
		return d.head[len(d.head)-1]
	}
}

func (d *Deque) PeekLast() interface{} {
	if len(d.tail) == 0 {
		return d.head[0]
	} else {
		return d.tail[len(d.tail)-1]
	}
}
