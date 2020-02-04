package main

import (
	"bufio"
	"bytes"
	"fmt"
	"math"
	"os"
	"strconv"
)

func exec(stdin *Stdin, stdout *Stdout) {
	s := stdin.Read()

	d := map[rune]rune{
		'O': '0',
		'D': '0',
		'I': '1',
		'Z': '2',
		'S': '5',
		'B': '8',
	}
	var ans bytes.Buffer

	for _, c := range s {
		if v, ok := d[c]; ok {
			ans.WriteRune(v)
		} else {
			ans.WriteRune(c)
		}
	}

	stdout.Println(ans.String())
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
