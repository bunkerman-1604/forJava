package main

import (
	"bufio"
	"fmt"
	"io"
	"net"
	"net/http"
	"os"
	"p1"
)

func main() {
	fmt.Println("1_PrintString:================================")
	fmt.Println("basic")
	fmt.Println("2_PrintNumberic:================================")
	fmt.Println(p1.Add(2, 9))
	/*tcpConnection*/
	fmt.Println("3_PrintTCPconnection:================================")
	conn, err := net.Dial("tcp", "180.149.132.47:80")
	p1.CheckErr(err)
	_, err = conn.Write([]byte("HEAD / HTTP/1.0\r\n\r\n"))
	p1.CheckErr(err)
	result, err := p1.Readtcp(conn)
	fmt.Println(string(result))

	/*httpRequest*/
	fmt.Println("4_PrintHTTPconnection:================================")
	res, _ := http.Get("http://baidu.com")
	io.Copy(os.Stdout, res.Body)

	/*fileToread
	fmt.Println("7_ReadBytesFromfile:================================")
	b := make([]byte,512)
	file, _ := os.Open("/home/ubuntu/test.txt")
	file.Read(b)
	fmt.Println(len(b),string(b))
	*/

	/*bufinfo*/
	//Write string to file
	fmt.Println("5_WriteStringtofile:================================")
	file, _ := os.Create("/home/sam/out.txt")
	file.WriteString("String to be Writed.\nSo many people all around world !\nAnd i only want one get familiar.")
	//Read string from file
	fmt.Println("6_ReadLineFromfile:================================")
	file, _ = os.Open("/home/sam/out.txt")
	br := bufio.NewReader(file)
	count := 1
	for {
		line, _, err := br.ReadLine()
		if err == io.EOF {
			break
		}
		fmt.Println(count, string(line))
		count++
	}

}
