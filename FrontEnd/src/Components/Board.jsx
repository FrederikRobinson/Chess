import Tile from './Tile.jsx'
import './Board.css';
import { createBoard, emptyBoard } from '../Utils/boardUtils.jsx'
import { useState } from 'react';


const Board = () => {
    const [board, setBoard] = useState(emptyBoard);
    const tiles = createBoard(board);


    return (
        <>
            <div className="boardContainer">
                <div className="tilesContainer">
                    {tiles}
                </div>
            </div>

        </>
    )
}

export default Board;