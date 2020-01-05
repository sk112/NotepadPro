
var request = new XMLHttpRequest()

console.log("requesting...");
function eventListener(){
    request.open('GET', 'https://localhost:8081/update', true)
    request.send()
}

