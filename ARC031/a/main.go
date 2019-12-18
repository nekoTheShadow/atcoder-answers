package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
)

func main() {
	name := read()
	size := len(name)
	for i := 0; i < size; i++ {
		if name[i:i+1] != name[size-i-1:size-i] {
			fmt.Println("NO")
			return
		}
	}
	fmt.Println("YES")
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
