package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
	"strings"
)

func main() {
	n := readInt()
	c := read()

	s := strings.Split("ABXY", "")
	for _, o := range s {
		for _, p := range s {
			for _, q := range s {
				for _, r := range s {
					d := strings.Replace(c, o+p, "L", -1)
					e := strings.Replace(d, q+r, "R", -1)
					if len(e) < n {
						n = len(e)
					}
				}
			}
		}
	}
	fmt.Println(n)
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
