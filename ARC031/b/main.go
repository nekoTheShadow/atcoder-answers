package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
)

func main() {
	a := create()
	for i := 0; i < 10; i++ {
		s := read()
		for j := 0; j < 10; j++ {
			a[i][j] = s[j:j+1] == "o"
		}
	}

	for i := 0; i < 10; i++ {
		for j := 0; j < 10; j++ {
			before := a[i][j]
			a[i][j] = true
			if ok(a, i, j) {
				fmt.Println("YES")
				return
			}
			a[i][j] = before
		}
	}

	fmt.Println("NO")
}

type tuple struct {
	i int
	j int
}

func ok(a [][]bool, x int, y int) bool {
	visited := create()
	stack := []tuple{{x, y}}
	diffs := []tuple{{0, 1}, {0, -1}, {1, 0}, {-1, 0}}
	for len(stack) > 0 {
		t := stack[0]
		stack = stack[1:]

		visited[t.i][t.j] = true
		for _, diff := range diffs {
			nx := t.i + diff.i
			ny := t.j + diff.j
			if 0 <= nx && nx < 10 && 0 <= ny && ny < 10 && a[nx][ny] && !visited[nx][ny] {
				stack = append(stack, tuple{nx, ny})
			}
		}
	}

	for i := 0; i < 10; i++ {
		for j := 0; j < 10; j++ {
			if a[i][j] && !visited[i][j] {
				return false
			}
		}
	}
	return true
}

func create() [][]bool {
	a := make([][]bool, 10)
	for i := 0; i < 10; i++ {
		a[i] = make([]bool, 10)
	}
	return a
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
