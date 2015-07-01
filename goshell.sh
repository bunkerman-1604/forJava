echo "please choose setup style--1:vim-go   2:Other"
read style
if [${style} -eq 1]; then
	apt-get install
	mkdir -p ~/.vim/bundle
	git clone https://github.com/gmarik/Vundle.vim.git ~/.vim/bundle/Vundle.vim
	echo "set nocompatible" >						~/.vimrc
	echo "filetype off" >>							~/.vimrc
	echo "set rtp+=~/.vim/bundle/Vundle.vim" >>				~/.vimrc
	echo "call vundle#begin()" >>						~/.vimrc
	echo "Plugin 'fatih/vim-go'" >>						~/.vimrc
	echo "Plugin 'gmarik/Vundle.vim'" >>					~/.vimrc
	echo "following line" >>						~/.vimrc
	echo "call vundle#end()" >>						~/.vimrc
	echo "filetype plugin indent on" >>					~/.vimrc
	
	echo "You should type \":PluginInstall\" in next program yourself! Press Enter to go !"
	read tmp
	vim
fi
if [${style} -eq 2]; then
	mkdir -p $HOME/.vim/ftdetect mkdir -p $HOME/.vim/syntax
	mkdir -p $HOME/.vim/autoload/go
	ln -s $GOROOT/misc/vim/ftdetect/gofiletype.vim $HOME/.vim/ftdetect/
	ln -s $GOROOT/misc/vim/syntax/go.vim $HOME/.vim/syntax
	ln -s $GOROOT/misc/vim/autoload/go/complete.vim $HOME/.vim/autoload/go
	echo "syntax on" >> $HOME/.vimrc 
fi
