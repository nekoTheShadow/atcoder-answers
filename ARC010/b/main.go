package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"sort"
	"strconv"
	"strings"
	"time"
)

func exec(stdin *Stdin, stdout *Stdout) {
	holidays := map[time.Time]bool{}
	for t := time.Date(2012, 1, 1, 0, 0, 0, 0, time.Local); t.Year() == 2012; t = t.AddDate(0, 0, 1) {
		if t.Weekday() == time.Sunday || t.Weekday() == time.Saturday {
			holidays[t] = true
		}
	}

	vacations := []time.Time{}
	n := stdin.ReadInt()
	for i := 0; i < n; i++ {
		line := strings.Split(stdin.Read(), "/")
		m, _ := strconv.Atoi(line[0])
		d, _ := strconv.Atoi(line[1])

		vacation := time.Date(2012, time.Month(m), d, 0, 0, 0, 0, time.Local)
		vacations = append(vacations, vacation)
	}

	// sort.Slice(vacations, func(i int, j int) bool {
	// 	return vacations[i].Before(vacations[j])
	// })
	sort.Sort(Times(vacations))

	for _, vacation := range vacations {
		for {
			if _, ok := holidays[vacation]; ok {
				vacation = vacation.AddDate(0, 0, 1)
			} else {
				holidays[vacation] = true
				break
			}
		}
	}

	ans := 0
	c := 0
	for t := time.Date(2012, 1, 1, 0, 0, 0, 0, time.Local); t.Year() == 2012; t = t.AddDate(0, 0, 1) {
		if _, ok := holidays[t]; ok {
			c++
		} else {
			c = 0
		}
		ans = Max(ans, c)
	}

	stdout.Println(ans)
}

func main() {
	stdout := NewStdout()
	exec(NewStdin(bufio.ScanWords), stdout)
	stdout.Flush()
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

func Max(a ...int) int {
	v := a[0]
	for i := 1; i < len(a); i++ {
		if v < a[i] {
			v = a[i]
		}
	}
	return v
}

type Times []time.Time

func (t Times) Len() int {
	return len(t)
}

func (t Times) Less(i, j int) bool {
	return t[i].Before(t[j])
}

func (t Times) Swap(i, j int) {
	t[i], t[j] = t[j], t[i]
}
