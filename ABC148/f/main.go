package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
)

func main() {
	n := readInt()
	u := readInt() - 1
	v := readInt() - 1
	nexts := make([][]int, n)
	for i := 0; i < n; i++ {
		nexts[i] = make([]int, 0)
	}

	for i := 0; i < n-1; i++ {
		a := readInt() - 1
		b := readInt() - 1
		nexts[a] = append(nexts[a], b)
		nexts[b] = append(nexts[b], a)
	}

	ans := 0
	d1 := f(n, u, nexts)
	d2 := f(n, v, nexts)
	for i := 0; i < n; i++ {
		if d1[i] < d2[i] && ans < d2[i] {
			ans = d2[i] - 1
		}
	}

	fmt.Println(ans)
}

func f(n int, x int, nexts [][]int) []int {
	d := make([]int, n)
	for i := 0; i < n; i++ {
		d[i] = 10000000
	}
	d[x] = 0
	q := []int{x}
	for len(q) > 0 {
		current := q[0]
		q = q[1:]
		for _, next := range nexts[current] {
			if d[current]+1 < d[next] {
				q = append(q, next)
				d[next] = d[current] + 1
			}
		}
	}
	return d
}

var stdin *bufio.Scanner

func read() string {
	if stdin == nil {
		stdin = bufio.NewScanner(os.Stdin)
		stdin.Split(bufio.ScanWords)
		stdin.Buffer(make([]byte, bufio.MaxScanTokenSize), int(math.MaxInt32))
	}
	stdin.Scan()
	return stdin.Text()
}

func readInt() int {
	n, _ := strconv.Atoi(read())
	return n
}

func readFloat64() float64 {
	n, _ := strconv.ParseFloat(read(), 64)
	return n
}
