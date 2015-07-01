package main

import (
	"fmt"
	"io"
	"net/http"
	"os"
	"p1"
	"bufio"
)

func main() {
	fmt.Println("basic")
	fmt.Println(p1.Add(2, 9))
	/*tcpConnection
	conn, err := p1.SocketClient("tcp", "192.168.100.176:80")
	p1.CheckErr(err)
	_, err = conn.Write([]byte("HEAD / HTTP/1.0\r\n\r\n"))
	p1.CheckErr(err)
	result, err := p1.Readtcp(conn)
	fmt.Println(string(result))
	*/

	/*httpRequest*/
	res, _ := http.Get("http://baidu.com")
	io.Copy(os.Stdout, res.Body)

	/*fileToread
	b := make([]byte,512)
	file, _ := os.Open("/home/ubuntu/test.txt")
	file.Read(b)
	fmt.Println(len(b),string(b))
	*/

	/*bufinfo*/
	file,_ := os.Open("/home/ubuntu/test.txt")
	br := bufio.NewReader(file)
	count := 1
	for {
		line,_,err := br.ReadLine()
		if err == io.EOF{
			break
		}
		fmt.Println(count,string(line))
		count++
	}

	//Write string to file
	file,_ = os.Create("/home/ubuntu/out.txt")
	file.WriteString("天大地大人大8864Time\n")
}
