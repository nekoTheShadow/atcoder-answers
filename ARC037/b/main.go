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
	m := readInt()
	nexts := make([][]int, n)
	for i := 0; i < n; i++ {
		nexts[i] = make([]int, 0)
	}
	for i := 0; i < m; i++ {
		u := readInt() - 1
		v := readInt() - 1
		nexts[u] = append(nexts[u], v)
		nexts[v] = append(nexts[v], u)
	}

	ans := 0
	checked := make([]bool, n)
	for root := 0; root < n; root++ {
		if checked[root] {
			continue
		}

		stack := []tuple{{parent: -1, current: root}}
		visted := make([]bool, n)
		ok := true
		for len(stack) > 0 {
			t := stack[len(stack)-1]
			stack = stack[:len(stack)-1]
			if visted[t.current] {
				ok = false
				continue
			}
			visted[t.current] = true
			for _, next := range nexts[t.current] {
				if next != t.parent {
					stack = append(stack, tuple{parent: t.current, current: next})
				}
			}
		}

		if ok {
			ans++
		}
		for i := 0; i < n; i++ {
			if visted[i] {
				checked[i] = visted[i]
			}
		}
	}

	fmt.Println(ans)
}

type tuple struct {
	parent  int
	current int
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
