import NewGame from "./NewGame";
import Board from "./Board";
import JoinGame from "./JoinGame.jsx";
import './Board.css';
import PropTypes from "prop-types"
import { Client } from '@stomp/stompjs'
import { createBoard, emptyBoard, nextPlayer, getColourFromTurnNumber } from '../Utils/boardUtils.jsx'
import { useEffect, useState } from 'react';
import { makeMove, createGame, getGame, joinGame } from '../Utils/apiCalls.js';
import { Routes, Route, useNavigate } from "react-router-dom";

const BoardController = ({ handleSetErrorDisplay, userId }) => {

    const navigate = useNavigate();

    const [gameId, setGameId] = useState(0);
    const [board, setBoard] = useState(emptyBoard);
    const [player, setPlayer] = useState('X');
    const [currentPlayer, setCurrentPlayer] = useState('X');
    const [selectedTile, setSelectedTile] = useState([-1, -1]);
    const [connected, setConnected] = useState(false);



    const handleCreateGame = async () => {
        const res = await createGame(userId);
        if (!res.error) {
            // console.dir(res);
            setGameId(res.gameId);
            // handleJoinGame(userId, res.gameId);
            setBoard(res.board);
            setSelectedTile([-1, -1]);
            setPlayer(getColourFromTurnNumber(res.playerNumber))
            setCurrentPlayer("W");
        }
        if (res.error) {
            handleSetErrorDisplay("Unable to create game")
        }

    }

    const handleMakeMove = async (startXPos, startYPos, endXPos, endYPos, playerColour, gameId) => {
        const res = await makeMove(startXPos, startYPos, endXPos, endYPos, playerColour, gameId);
        if (res.error) {
            handleSetErrorDisplay("Unable to make move")
        }
        else {
            if (res.playerTurn === currentPlayer) {
                handleSetErrorDisplay("Invalid move")
            }
            setBoard(res.updatedBoard);
            setSelectedTile([-1, -1]);
            setCurrentPlayer(res.playerTurn);
        }

    }
    const handleTileSelection = (xPos, yPos) => {
        selectedTile[0] == -1
            ?
            setSelectedTile([xPos, yPos])
            :
            handleMakeMove(selectedTile[0], selectedTile[1], xPos, yPos, currentPlayer, gameId);

    }

    const isTileSelected = (xPos, yPos) => { return xPos == selectedTile[0] && yPos == selectedTile[1] ? "Highlighted" : ""; }
    const functionsForTiles = { handleTileSelection: handleTileSelection, isTileSelected }
    const tiles = createBoard(board, functionsForTiles);
    return (
        <>
            <div className="boardController">
                {gameId === 0 && <NewGame handleCreateGame={handleCreateGame} />}
                {gameId !== 0 && <Board tiles={tiles} currentPlayer={currentPlayer} selectedTile={selectedTile} />}

            </div>
        </>
    )
}

BoardController.propTypes = {
    userId: PropTypes.number,
    handleSetErrorDisplay: PropTypes.func
}
export default BoardController;





// const [stompClient, setStompClient] = useState(new Client({
//     brokerURL: 'ws://localhost:8080/gameConnection',
//     onConnect: () => {
//         stompClient.subscribe('/topic/' + gameId, (res) => {
//             // if (!connected) {
//             //     setBoard(res.board);
//             //     setSelectedTile([-1, -1]);
//             //     setPlayer(getColourFromTurnNumber(res.playerNumber))
//             //     setCurrentPlayer(res.playerTurn);
//             //     setConnected(true)
//             console.log("ITS WORKING");
//             // }
//         })
//     }
// }));


//<Tile xPos={xPos} yPos={yPos} piece={piece} key={`${xPos} ${yPos}`} / >
// useEffect(() => {
//     setBoard
// }, [])
// useEffect(() => {
//     if (currentPlayer !== player) {
//         handleGetBoard();
//     }
// }, [currentPlayer])
// const delay = ms => new Promise(res => setTimeout(res, ms));
// const handleGetBoard = async () => {
//     console.log("It begins");
//     while (currentPlayer !== player) {
//         let res = await getGame(gameId)
//         if (res.playerTurn === currentPlayer) {
//             await delay(2000);
//             console.log("This shouldn't be called a lot");
//         }
//     }
//     console.log("it ends");
// }

// const handleJoinGame = async (gameCode) => {
//     try {
//         setGameId(gameCode)
//         stompClient.onStompError = function (frame) {
//             // Will be invoked in case of error encountered at Broker
//             // Bad login/passcode typically will cause an error
//             // Complaint brokers will set `message` header with a brief message. Body may contain details.
//             // Compliant brokers will terminate the connection after any error
//             console.log('Broker reported error: ' + frame.headers['message']);
//             console.log('Additional details: ' + frame.body);
//         };
//         stompClient.activate();
//         // await stompClient.subscribe('/topic/' + gameId, (res) => {
//         //     if (!connected) {
//         //         setBoard(res.board);
//         //         setSelectedTile([-1, -1]);
//         //         setPlayer(getColourFromTurnNumber(res.playerNumber))
//         //         setCurrentPlayer(res.playerTurn);
//         //         setConnected(true)
//         //         console.log("ITS WORKING");
//         // navigate("/play/game");

//         // }
//         // });
//         // await stompClient.publish({
//         //     destination: "/app/joinGame",
//         //     body: JSON.stringify({ userId, gameId })
//         // });
//         // await stompClient.publish({
//         //     destination: "/app/makeMove",
//         //     body: JSON.stringify({ startXPos, startYPos, endXPos, endYPos, playerColour, gameId })
//         // });

//     }
//     catch {

//     }
// }
/*
            <Routes>
                <Route path="/joinGame" element={<JoinGame handleJoinGame={handleJoinGame} />} />
                <Route path="/game" element={
                    <>
                        {gameId === 0 && <NewGame handleCreateGame={handleCreateGame} />}
                        {gameId !== 0 && <Board tiles={tiles} currentPlayer={currentPlayer} selectedTile={selectedTile} />}
                    </>} />
            </Routes> */