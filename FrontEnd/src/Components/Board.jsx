import Tile from './Tile.jsx'
import './Board.css';
import { createBoard } from '../Utils/boardUtils.jsx'


const Board = () => {
    const tiles = createBoard();


    return (
        <>
            <div className="boardContainer">
                <p>Board here</p>
                <div className="tilesContainer">
                    {tiles}
                </div>
            </div>

        </>
    )
}

export default Board;