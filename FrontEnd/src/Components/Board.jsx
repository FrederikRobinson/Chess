import Tile from './Tile.jsx'
import './Board.css';
import { createBoard, emptyBoard, nextPlayer } from '../Utils/boardUtils.jsx'
import { useEffect, useState } from 'react';
import { makeMove } from '../Utils/apiCalls.js';


const Board = () => {
    const [board, setBoard] = useState(emptyBoard);
    const [gameId, setGameId] = useState("");
    const [player, setPlayer] = useState('X');
    const [currentPlayer, setCurrentPlayer] = useState('X');
    const [selectedTile, setSelectedTile] = useState([-1, -1]);


    //<Tile xPos={xPos} yPos={yPos} piece={piece} key={`${xPos} ${yPos}`} / >
    // useEffect(() => {
    //     setBoard
    // }, [])

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
            handleMakeMove(selectedTile[0], selectedTile[1], xPos, yPos, currentPlayer, gameId)
        }
    }
    const isTileSelected = (xPos, yPos) => { return xPos == selectedTile[0] && yPos == selectedTile[1] ? "Highlighted" : ""; }
    const functionsForTiles = { handleTileSelection: handleTileSelection, isTileSelected }
    const tiles = createBoard(board, functionsForTiles);
    return (
        <>
            <div className="boardContainer">
                <div className="tilesContainer">
                    {tiles}
                </div>
            </div>
            <div>{selectedTile}{currentPlayer}</div>
        </>
    )
}

export default Board;