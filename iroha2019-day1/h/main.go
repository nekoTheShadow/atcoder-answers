package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"regexp"
	"strconv"
	"strings"
)

func exec(stdin *Stdin, stdout *Stdout) {
	s := stdin.Read()
	t := strings.Split(s, "")
	n, _ := strconv.Atoi(s)

	// nが一桁の場合
	if n < 10 {
		stdout.Println(10 + n - 1)
		return
	}

	// *999の場合
	if ok, _ := regexp.Match(`^[1-8]9*$`, []byte(s)); ok {
		x, _ := strconv.Atoi(t[0])
		stdout.Println(fmt.Sprintf("%d8%s", x+1, s[2:]))
		return
	}

	// 9999の場合
	if ok, _ := regexp.Match(`^9*$`, []byte(s)); ok {
		stdout.Println(fmt.Sprintf("18%s", s[1:]))
		return
	}

	f := 0
	for n > 0 {
		f += n % 10
		n /= 10
	}

	if f < 10 {
		stdout.Println(f)
		return
	}

	d := 0
	if f%9 > 0 {
		d += f % 9
	}

	for i := 0; i < f/9; i++ {
		d *= 10
		d += 9
	}

	stdout.Println(d)
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
