import NewGame from "./NewGame";
import Board from "./Board";
import JoinGame from "./JoinGame.jsx";
import './Board.css';
import { createBoard, emptyBoard, nextPlayer, getColourFromTurnNumber } from '../Utils/boardUtils.jsx'
import { useEffect, useState } from 'react';
import { makeMove, createGame, getGame, joinGame } from '../Utils/apiCalls.js';
import { Routes, Route, useNavigate } from "react-router-dom";
const BoardController = ({ userId }) => {
    const navigate = useNavigate();
    const [stompClient, setStompClient] = useState(new StompJs.Client({
        brokerURL: 'ws://localhost:8080/gameConnection'
    }));
    const [gameId, setGameId] = useState(0);
    const [board, setBoard] = useState(emptyBoard);
    const [player, setPlayer] = useState('X');
    const [currentPlayer, setCurrentPlayer] = useState('X');
    const [selectedTile, setSelectedTile] = useState([-1, -1]);
    const [connected, setConnected] = useState(false);



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
    const handleCreateGame = async () => {
        try {
            const res = await createGame(userId);
            console.dir(res);
            setGameId(res.gameId);
            handleJoinGame(userId, res.gameId);
            // setBoard(res.board);
            // setSelectedTile([-1, -1]);
            // setPlayer(getColourFromTurnNumber(res.playerNumber))
            // setCurrentPlayer("W");
        }
        catch {

        }
    }
    const handleJoinGame = async (gameCode) => {
        try {
            await stompClient.activate();
            await stompClient.subscribe('/topic/' + gameId, (res) => {
                if (!connected) {
                    setBoard(res.board);
                    setSelectedTile([-1, -1]);
                    setPlayer(getColourFromTurnNumber(res.playerNumber))
                    setCurrentPlayer(res.playerTurn);
                    setConnected(true)
                    navigate("/play/game");

                }
            });
            await stompClient.publish({
                destination: "/app/joinGame",
                body: JSON.stringify({ userId, gameId })
            });
            // await stompClient.publish({
            //     destination: "/app/makeMove",
            //     body: JSON.stringify({ startXPos, startYPos, endXPos, endYPos, playerColour, gameId })
            // });

        }
        catch {

        }
    }
    const handleMakeMove = async (startXPos, startYPos, endXPos, endYPos, playerColour, gameId) => {
        try {
            const res = await makeMove(startXPos, startYPos, endXPos, endYPos, playerColour, gameId);
            console.dir(res);
            setBoard(res.updatedBoard);
            setSelectedTile([-1, -1]);
            setCurrentPlayer(res.playerTurn);

        }
        catch {
            //TODO error pop up for invalid move or connection error
        }
    }
    const handleTileSelection = (xPos, yPos) => {
        if (selectedTile[0] == -1) {
            setSelectedTile([xPos, yPos]);
        }
        else {
            handleMakeMove(selectedTile[0], selectedTile[1], xPos, yPos, currentPlayer, gameId);
        }
    }

    const isTileSelected = (xPos, yPos) => { return xPos == selectedTile[0] && yPos == selectedTile[1] ? "Highlighted" : ""; }
    const functionsForTiles = { handleTileSelection: handleTileSelection, isTileSelected }
    const tiles = createBoard(board, functionsForTiles);
    return (
        <>
            <Routes>
                <Route path="/joinGame" element={<JoinGame handleJoinGame={handleJoinGame} />} />
                <Route path="/game" element={
                    <>
                        {gameId === 0 && <NewGame handleCreateGame={handleCreateGame} />}
                        {gameId !== 0 && <Board tiles={tiles} currentPlayer={currentPlayer} selectedTile={selectedTile} />}
                    </>} />
            </Routes>

        </>
    )
}
export default BoardController;