package p1

import (
	"bytes"
	"fmt"
	"io"
	"net"
	"os"
)

func Add(x, y int) (res int) {
	return x + y
}
func CheckErr(err error) {
	if err != nil {
		fmt.Fprintf(os.Stderr, "Fetal error :%s", err.Error())
		os.Exit(1)
	}
}
func SocketClient(style, addr string) (link net.Conn, err error) {
	return net.Dial(style, addr)
}
func Readtcp(conn net.Conn) ([]byte, error) {
	defer conn.Close()
	result := bytes.NewBuffer(nil)
	var buf [512]byte
	for {
		n, err := conn.Read(buf[0:])
		result.Write(buf[0:n])
		if err != nil {
			if err == io.EOF {
				break
			}
			return nil, err
		}
	}
	return result.Bytes(), nil
}
func HttpClient() {

}
