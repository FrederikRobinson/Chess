// const stompClient = new StompJs.Client({
//     brokerURL: 'ws://localhost:8080/gameConnection'
// });
stompClient.subscribe('/topic/' + gameId, (res) => {
    //Display the board from the response;
});

stompClient.activate();
stompClient.publish({
    destination: "/app/makeMove",
    body: JSON.stringify({ startXPos, startYPos, endXPos, endYPos, playerColour, gameId })
});

const connectToGame = (userId, gameId, stompClient) => {
    stompClient.activate();
    stompClient.subscribe('/topic/' + gameId, (res) => {
        //Display the board from the response;
    });
    stompClient.publish({
        destination: "/app/makeMove",
        body: JSON.stringify({ startXPos, startYPos, endXPos, endYPos, playerColour, gameId })
    });
}