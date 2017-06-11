//var app = require('express')();
var express = require('express');
var app = express();

var http = require('http').Server(app);
var io = require('socket.io')(http);
const net = require('net');

app.use(express.static('public'))


app.get('/', function(req, res){
  res.sendfile('LiveBarChart.html');
});

//Whenever someone connects this gets executed
io.on('connection', function(socket){
  console.log('A user connected');
  //socket.emit('testerEvent', { description: data.toString()});
  socket.on('disconnect', function () {
    console.log('A user disconnected');
  });

});


http.listen(3000, function(){
  console.log('listening on *:3000');
});






// TCP server

net.createServer(function (socket) {
  console.log('socket connected');
  socket.on('data', function(data) {
    var line = data.toString();
    console.log('got "data"', line);
    //socket.pipe(writable);
    io.sockets.emit('newDataEvent',  data.toString()); 
		
  });
  socket.on('end', function() {
    console.log('end');
  });
  socket.on('close', function() {
    console.log('close');
  });
  socket.on('error', function(e) {
    console.log('error ', e);
  });
  socket.write('hello from tcp server');
}).listen(3080, function() {
  console.log('TCP Server is listening on port 3080');
});